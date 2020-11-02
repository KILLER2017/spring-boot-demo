package top.alvinsite.demo.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Alvin
 */
@Data
@ConfigurationProperties(AppProperties.PREFIX)
public class AppProperties {

    public static final String PREFIX = "app";

    /**
     * 域名
     */
    private String domain;

    /**
     * 是否启用账号密码登录
     */
    private boolean enableUsernamePasswordLogin;

    /**
     * 是否开启未知异常邮件告警
     */
    private boolean enableUnknownExceptionMailAlert;
}
