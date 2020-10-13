package top.alvinsite.demo.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProjectLevel {
    COUNTRY_LEVEL(1, "国家级"),
    PROVINCE_LEVEL(2, "省部级"),
    CITY_LEVEL(3, "市厅级"),
    SCHOOL_LEVEL(4, "校级"),
    OTHERS(5, "其他");

    private final int id;
    private final String name;

    public Integer getId() {
        return id;
    }

    public static ProjectLevel getEnumById(int id) {
        for (ProjectLevel type : ProjectLevel.values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }
}
