package top.alvinsite.demo.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProjectLevel implements IEnum<Integer> {
    COUNTRY_LEVEL(1, "国家级"),
    PROVINCE_LEVEL(2, "省部级"),
    CITY_LEVEL(3, "市厅级"),
    SCHOOL_LEVEL(4, "校级"),
    OTHERS(5, "其他");

    @EnumValue
    private final int value;
    @JsonValue
    private final String name;

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
