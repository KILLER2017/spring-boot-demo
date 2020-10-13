package top.alvinsite.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(
        prefix = "cas.dgut"
)
public class LoginConfig {
    static String homeUrl = "";
    static String adminUrl = "";
    static Integer expiredTime = 3600;
}
