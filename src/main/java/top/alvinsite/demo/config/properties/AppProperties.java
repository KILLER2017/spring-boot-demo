package top.alvinsite.demo.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("app")
public class AppProperties {

    private String domain = "127.0.0.1:8080";
}
