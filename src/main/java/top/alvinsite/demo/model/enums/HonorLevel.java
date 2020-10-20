package top.alvinsite.demo.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HonorLevel implements IEnum<String> {
    NATION("10", "国家级"),
    Ministry("11", "部级奖"),
    PROVINCE("20", "省级奖"),
    CITY("30", "地市级"),
    SCHOOL("40", "学校级"),
    OTHERS("50", "其它"),
            ;

    @EnumValue
    private final String value;

    @JsonValue
    private final String name;

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
