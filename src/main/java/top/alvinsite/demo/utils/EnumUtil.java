package top.alvinsite.demo.utils;

import com.baomidou.mybatisplus.annotation.IEnum;

public class EnumUtil {

    /**
     * Gets i enum.
     *
     * @param <T>        the type parameter
     * @param targetType the targer type
     * @param source     the source
     * @return the i enum
     */
    public static <T extends IEnum> Object getIEnum(Class<T> targetType, String source) {
        for (T enumObj : targetType.getEnumConstants()) {
            if (source.equals(enumObj.getValue())) {
                return enumObj;
            }
        }
        return null;

    }
}
