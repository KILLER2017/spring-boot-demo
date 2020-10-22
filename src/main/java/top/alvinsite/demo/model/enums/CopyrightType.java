package top.alvinsite.demo.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;





@Getter
@AllArgsConstructor
public enum CopyrightType implements IEnum<String> {
    AUDIO_OR_VIDEO("1", "音像制品"),
    SOFTWARE("2", "软件制品"),
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
