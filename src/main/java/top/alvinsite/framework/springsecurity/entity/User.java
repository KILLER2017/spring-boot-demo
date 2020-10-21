package top.alvinsite.framework.springsecurity.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;


@Data
@TableName(value = "auth_user")
public class User implements UserDetails {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String password;

    private LocalDateTime lastLogin;

    @TableField(value = "is_superuser")
    private Boolean superuser;

    @NotBlank(message = "账号不能为空")
    private String username;
    private String firstName;
    private String lastName;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式错误")
    private String email;

    @TableField(value = "is_staff")
    private Boolean staff;

    @TableField(value = "is_active")
    private Boolean active;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime dateJoined;

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
        return object instanceof User ? this.username.equals(((User) object).username) : false;
    }

    @Override
    public int hashCode() {
        return this.username.hashCode();
    }
}
