package top.alvinsite.demo.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DataCheck {
    UNCHECKED("0", "未审核"),
    INSTITUTION_AGREE("1", "未审核"),
    SCHOOL_AGREE("2", "学校通过"),
    INSTITUTION_DISAGREE("3", "机构不通过"),
    SCHOOL_DISAGREE("4", "学校不通过"),
    ;

    private final String id;
    private final String name;

    public String getId() {
        return id;
    }

    public static DataCheck getEnumById(String id) {
        for (DataCheck type : DataCheck.values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }
}
