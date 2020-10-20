package top.alvinsite.demo.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PatentScope implements IEnum<String> {
    DOMESTIC("1", "国内"),
    OVERSEA("2", "国外"),
    USA_JP_EN("3", "美国、日本、欧盟国家"),
    OTHERS("4", "其他发达国家或地区（港澳台除外）"),
    HK_MC_TW("5", "港澳台"),
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
