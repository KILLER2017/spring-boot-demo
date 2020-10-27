package top.alvinsite.demo.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum SubsidizeFrom implements IEnum<String>,BasicEnum {
    COUNTRY("6", "国家出版基金"),
    PROVINCE("2", "省出版基金"),
    SCHOOL("3", "学校专项"),
    NONE("4", "无"),
    ;

    @EnumValue
    private final String id;

    @JsonValue
    private final String title;

    @Override
    public List<BasicEnum> getAll(){
        return Arrays.asList(values());
    }

    @Override
    public String toString() {
        return this.title;
    }

    @Override
    public String getValue() {
        return id;
    }
}
