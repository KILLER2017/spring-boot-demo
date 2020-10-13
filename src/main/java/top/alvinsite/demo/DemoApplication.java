package top.alvinsite.demo;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.config.LoginConfig;

@EnableConfigurationProperties(LoginConfig.class)
@SpringBootApplication(scanBasePackages = {"top.alvinsite.demo"})
// 定义MyBatis的扫描
@MapperScan(
        // 指定扫描包
        basePackages = "top.alvinsite.demo.dao",
        // 限定注解
        annotationClass = Repository.class
)
public class DemoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        new WebMvcAutoConfiguration();
        SpringApplication.run(DemoApplication.class, args);
    }

}
