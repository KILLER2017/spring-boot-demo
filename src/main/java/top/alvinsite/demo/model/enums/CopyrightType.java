package top.alvinsite.demo.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CopyrightType implements IEnum<Integer> {
    CODE_1(1, "类型1"),
    CODE_2(2, "类型2"),
    CODE_3(3, "类型3"),
    ;


    @EnumValue
    private final Integer value;

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
