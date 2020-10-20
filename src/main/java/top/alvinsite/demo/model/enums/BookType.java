package top.alvinsite.demo.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookType implements IEnum<String> {
    TEACHING_MATERIAL("2", "教材"),
    TREATISE("3", "专著"),
    TRANSLATION("4", "译著"),
    DEVELOPMENT_REPORT("400001", "皮书/发展报告"),
    POPULAR_SCIENCE_READING("400002", "科普读物"),
    ANTHOLOGY("5b5e3f775929813e0159447d1e1b0901", "论文集/摄影集/画册"),
    COMPILE("6", "编著"),
    REFERENCE_BOOKS("7", "工具书"),
    ;

    @EnumValue
    private final String value;


    @JsonValue
    private final String name;


    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
