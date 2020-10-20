package top.alvinsite.demo.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductMode implements IEnum<String> {

    INVENTION("1", "发明专利"),
    NEW_MODEL("2", "实用新型"),
    APPEARANCE_DESIGN("3", "实用新型"),
    OTHERS("4", "其他知识产权")
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
