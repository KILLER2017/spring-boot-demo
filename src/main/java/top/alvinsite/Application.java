package top.alvinsite;


import com.fasterxml.jackson.databind.SerializationFeature;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.config.LoginConfig;

/**
 * @author Alvin
 */ // @ConfigurationPropertiesScan
@EnableConfigurationProperties(LoginConfig.class)
// @SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication
// 定义MyBatis的扫描
@MapperScan(
        // 指定扫描包
        basePackages = {"top.alvinsite.demo.dao", "top.alvinsite.framework.*.dao"},
        // 限定注解
        annotationClass = Repository.class
)
public class Application extends SpringBootServletInitializer  {

    private static ConfigurableApplicationContext CONTEXT;

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer(){
        return builder -> builder.featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
    }
    public static void main(String[] args) {
        CONTEXT = SpringApplication.run(Application.class, args);
    }

    /**
     * Restart Application.
     */
    public static void restart() {
        ApplicationArguments args = CONTEXT.getBean(ApplicationArguments.class);

        Thread thread = new Thread(() -> {
            CONTEXT.close();
            CONTEXT = SpringApplication.run(Application.class, args.getSourceArgs());
        });

        thread.setDaemon(false);
        thread.start();
    }


}
