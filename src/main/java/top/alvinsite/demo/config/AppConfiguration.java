package top.alvinsite.demo.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import top.alvinsite.demo.config.properties.AppProperties;

/**
 * @author Alvin
 */
@Configuration
@EnableConfigurationProperties(AppProperties.class)
public class AppConfiguration {

    AppProperties appProperties;

    public AppConfiguration(AppProperties appProperties) {
        this.appProperties = appProperties;
    }
}
