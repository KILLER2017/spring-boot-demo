package top.alvinsite.framework.springsecurity.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import top.alvinsite.demo.model.entity.Department;

import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Set;


/**
 * @author Administrator
 */
@Data
@TableName(value = "gxjg0101")
public class User implements UserDetails {

    @TableField(exist = false)
    private String password;

    @NotBlank(message = "账号不能为空")
    @TableId(value = "gh")
    private String username;

    @TableField(value = "xm")
    private String nickname;

    @TableField(exist = false)
    private Department department;

    @TableField(exist = false)
    private String userGroup;

    @TableField(exist = false)
    private String manageUnitId;

    private String[] manageUnits;

    @TableField(exist = false)
    private Set<GrantedAuthority> grantedAuthorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof User && this.username.equals(((User) object).username);
    }

    @Override
    public int hashCode() {
        return this.username.hashCode();
    }
}
