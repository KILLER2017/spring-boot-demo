package top.alvinsite.demo.model.support;

import java.lang.annotation.*;

/**
 * 自定义实体类所需要的bean(Excel属性标题、位置等)
 *
 * @author Lynch
 *
 */

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Excel {

    /**
     * Excel列标题
     */
    String name() default "";

    /**
     * Excel从左往右排列位置
     */
    int col() default 0;

    /**
     * 导出时在excel中每个列的高度 单位为字符
     */
    double height() default 14;

    /**
     * 导出时在excel中每个列的宽 单位为字符
     */
    int width() default 20;
}
