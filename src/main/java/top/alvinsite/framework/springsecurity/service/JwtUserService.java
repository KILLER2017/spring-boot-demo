package top.alvinsite.framework.springsecurity.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.auth.AdminDao;
import top.alvinsite.demo.dao.auth.ManagerDao;
import top.alvinsite.demo.model.dto.auth.AdminDTO;
import top.alvinsite.demo.model.dto.auth.ManagerDTO;
import top.alvinsite.framework.springsecurity.dao.UserDao;
import top.alvinsite.framework.springsecurity.entity.User;

import java.util.Date;

/**
 * @author Alvin
 */
@Service
public class JwtUserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private ManagerDao managerDao;

    private PasswordEncoder passwordEncoder;

    public JwtUserService() {
        // 默认使用 bcrypt， strength=10
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public UserDetails getUserLoginInfo(String username) {
        String salt = "123456ef";
        /**
         * @todo 从数据库或者缓存中取出jwt token生成时用的salt
         * salt = redisTemplate.opsForValue().get("token:"+username);
         */
        UserDetails user = loadUserByUsername(username);
        //将salt放到password字段返回
        // return User.builder().username(user.getUsername()).password(salt).authorities(user.getAuthorities()).build();
        return user;
    }

    public String saveUserLoginInfo(UserDetails user) {
        String salt = "123456ef";
        // BCrypt.gensalt();  正式开发时可以调用该方法实时生成加密的salt

        /**
         * @todo 将salt保存到数据库或者缓存中
         * redisTemplate.opsForValue().set("token:"+username, salt, 3600, TimeUnit.SECONDS);
         */
        Algorithm algorithm = Algorithm.HMAC256(salt);

        // 设置1小时后过期
        Date date = new Date(System.currentTimeMillis()+3600*1000);
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(date)
                .withIssuedAt(new Date())
                .sign(algorithm);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getUsername, username));

        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        // 获取用户组：系统管理员、机构管理员
        AdminDTO adminDTO = adminDao.findOneByAccount(user.getUsername());
        ManagerDTO managerDTO = managerDao.findOneByAccount(user.getUsername());


        if (adminDTO != null) {
            user.setUserGroup("admin");
        } else if (managerDTO != null) {
            user.setUserGroup("manager");
            user.setManageUnitId(managerDTO.getDepartment());

            String[] unitIds = managerDao.findUnitIdsByAccount(user.getUsername());
            user.setManageUnits(unitIds);
        }

        return user;
    }

    public void createUser(String username, String password) {
        String encryptPwd = passwordEncoder.encode(password);
        /**
         * @todo 保存用户名和加密后密码到数据库
         */
    }

    public void deleteUserLoginInfo(String username) {
        /**
         * @todo 清除数据库或者缓存中登录salt
         */
    }
}
