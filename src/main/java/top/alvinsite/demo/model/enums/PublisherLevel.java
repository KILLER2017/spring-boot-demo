package top.alvinsite.demo.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Administrator
 */

@Getter
@AllArgsConstructor
public enum PublisherLevel implements IEnum<String> {

    A("1", "A类"),
    B("2", "B类"),
    C("3", "C类"),
    NOT_PUBLISH("4", "未出版"),
    ;

    @EnumValue
    private final String id;

    @JsonValue
    private final String title;

    @Override
    public String toString() {
        return this.title;
    }

    @Override
    public String getValue() {
        return id;
    }
}
