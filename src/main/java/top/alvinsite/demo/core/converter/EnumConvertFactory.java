package top.alvinsite.demo.core.converter;


import com.baomidou.mybatisplus.annotation.IEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;
import top.alvinsite.demo.utils.EnumUtil;

@Component
public class EnumConvertFactory implements ConverterFactory<String, IEnum> {
    @Override
    public <T extends IEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToIEum<>(targetType);
    }

    private static class StringToIEum<T extends IEnum> implements Converter<String, T> {
        private Class<T> targerType;

        /**
         * Instantiates a new String to i eum.
         *
         * @param targerType the targer type
         */
        public StringToIEum(Class<T> targerType) {
            this.targerType = targerType;
        }

        @Override
        public T convert(String source) {
            if (StringUtils.isBlank(source)) {
                return null;
            }
            return (T) EnumUtil.getIEnum(this.targerType, source);
        }
    }
}

