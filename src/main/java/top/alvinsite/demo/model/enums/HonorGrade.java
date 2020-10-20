package top.alvinsite.demo.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HonorGrade implements IEnum<String> {
    Grand_Prize("1", "特等奖"),
    first_prize("2", "一等奖"),
    Second_Prize("3", "二等奖"),
    third_prize("4", "三等奖"),
    fourth_prize("5", "四等奖"),
    Achievement_prize("6", "成果普及奖"),
    others("9", "其他奖"),
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
