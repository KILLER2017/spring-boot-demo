package top.alvinsite.framework.springsecurity.service;

import com.auth0.jwt.JWT;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
import top.alvinsite.util.JwtUtils;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Alvin
 */
@Slf4j
@Service
public class JwtUserService implements UserDetailsService {

    @Resource
    private RedisTemplate<String, UserDetails> redisTemplate;

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findOneByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        // 设置密码
        user.setPassword("linzhou");

        // 获取用户组：系统管理员、机构管理员
        Set<GrantedAuthority> authorities = new HashSet<>();
        AdminDTO adminDTO = adminDao.findOneByAccount(user.getUsername());
        ManagerDTO managerDTO = managerDao.findOneByAccount(user.getUsername());

        if (adminDTO != null) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            user.setUserGroup("admin");
        } else if (managerDTO != null) {
            authorities.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
            user.setUserGroup("manager");
            user.setManageUnitId(managerDTO.getDepartment());

            String[] unitIds = managerDao.findUnitIdsByAccount(user.getUsername());
            user.setManageUnits(unitIds);
        } else {
            user.setUserGroup("user");
        }
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        user.setGrantedAuthorities(authorities);

        return user;
    }

    public void createUser(String username, String password) {
        String encryptPwd = passwordEncoder.encode(password);
        /**
         * @todo 保存用户名和加密后密码到数据库
         */
    }

    public String saveUserLoginInfo(UserDetails user) {
        String token = JwtUtils.sign(user.getUsername());
        String key = "login-user:" + JWT.decode(token).getKeyId();
        redisTemplate.opsForValue().set(key, user);
        return token;
    }

    /**
     * Jwt校验时，通过该方法获取用户信息
     * @param key
     * @return
     */
    public UserDetails getUserLoginInfo(@NonNull String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        return redisTemplate.opsForValue().get("login-user:" + key);
    }

    public void deleteUserLoginInfo(@NonNull String key) {
        /**
         * @todo 清除数据库或者缓存中登录salt
         */
        if (StringUtils.isBlank(key)) {
            return;
        }
        redisTemplate.delete(key);
    }
}
