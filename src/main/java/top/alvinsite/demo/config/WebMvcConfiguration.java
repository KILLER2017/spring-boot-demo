package top.alvinsite.demo.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.alvinsite.demo.core.converter.EnumConvertFactory;
import top.alvinsite.demo.core.converter.StringToUserInfoConverter;

/**
 * @author Alvin
 */
@Slf4j
@Configuration
@ComponentScan(basePackages = "top.alvinsite.demo.controller")
public class WebMvcConfiguration implements WebMvcConfigurer {

    StringToUserInfoConverter stringToUserInfoConverter;

    EnumConvertFactory enumConvertFactory;

    @Autowired
    public WebMvcConfiguration(StringToUserInfoConverter stringToUserInfoConverter, EnumConvertFactory enumConvertFactory) {
        this.stringToUserInfoConverter = stringToUserInfoConverter;
        this.enumConvertFactory = enumConvertFactory;
    }

    @Override
    public void addFormatters(@NonNull FormatterRegistry registry) {
        // StringToCollectionConverter stringToCollectionConverter;
    }
}
