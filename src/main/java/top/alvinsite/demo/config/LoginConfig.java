package top.alvinsite.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Alvin
 */
@ConfigurationProperties(
        prefix = "cas.dgut"
)
public class LoginConfig {
    static String homeUrl = "";
    static String adminUrl = "";
    static Integer expiredTime = 3600;
}
