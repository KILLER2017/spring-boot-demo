package top.alvinsite.demo;


import com.fasterxml.jackson.databind.SerializationFeature;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.config.LoginConfig;

@EnableConfigurationProperties(LoginConfig.class)
// @SpringBootApplication(scanBasePackages = {"top.alvinsite"}, exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication(scanBasePackages = {"top.alvinsite"})
// 定义MyBatis的扫描
@MapperScan(
        // 指定扫描包
        basePackages = "top.alvinsite.demo.dao",
        // 限定注解
        annotationClass = Repository.class
)
public class DemoApplication extends SpringBootServletInitializer {
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer(){
        return builder -> builder.featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
    }
    public static void main(String[] args) {
        new WebMvcAutoConfiguration();
        SpringApplication.run(DemoApplication.class, args);
    }

}
