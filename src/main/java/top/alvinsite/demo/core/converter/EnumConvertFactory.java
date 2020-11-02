package top.alvinsite.demo.core.converter;


import com.baomidou.mybatisplus.annotation.IEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;
import top.alvinsite.demo.utils.EnumUtil;

/**
 * @author Alvin
 */
@Component
public class EnumConvertFactory implements ConverterFactory<String, IEnum> {
    @Override
    public <T extends IEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return new stringToIEum<>(targetType);
    }

    private static class stringToIEum<T extends IEnum> implements Converter<String, T> {
        private Class<T> targetType;

        /**
         * Instantiates a new String to i eum.
         *
         * @param targetType the target type
         */
        public stringToIEum(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Override
        public T convert(String source) {
            if (StringUtils.isBlank(source)) {
                return null;
            }
            return (T) EnumUtil.getIEnum(this.targetType, source);
        }
    }
}

