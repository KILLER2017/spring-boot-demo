package top.alvinsite.demo.model.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DataCheck implements IEnum<String> {
    UNCHECKED("0", "未审核"),
    INSTITUTION_AGREE("1", "未审核"),
    SCHOOL_AGREE("2", "学校通过"),
    INSTITUTION_DISAGREE("3", "机构不通过"),
    SCHOOL_DISAGREE("4", "学校不通过"),
    ;

    private final String value;
    private final String name;

    @Override
    public String getValue() {
        return value;
    }

    public static DataCheck getEnumById(String id) {
        for (DataCheck type : DataCheck.values()) {
            if (type.getValue() == id) {
                return type;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
