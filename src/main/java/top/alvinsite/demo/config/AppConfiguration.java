package top.alvinsite.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import top.alvinsite.demo.config.properties.AppProperties;

/**
 * @author Alvin
 */
@Configuration
@EnableConfigurationProperties(AppProperties.class)
public class AppConfiguration {

    @Autowired
    AppProperties appProperties;


}
