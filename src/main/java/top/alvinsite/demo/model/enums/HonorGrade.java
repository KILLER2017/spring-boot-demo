package top.alvinsite.demo.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HonorGrade {
    Grand_Prize("1", "特等奖"),
    first_prize("2", "一等奖"),
    Second_Prize("3", "二等奖"),
    third_prize("4", "三等奖"),
    fourth_prize("5", "四等奖"),
    Achievement_prize("6", "成果普及奖"),
    others("9", "其他奖"),
        ;

    private final String id;
    private final String name;

    public String getId() {
        return id;
    }

    public static HonorGrade getEnumById(String id) {
        for (HonorGrade type : HonorGrade.values()) {
            if (type.getId().equals(id)) {
                return type;
            }
        }
        return null;
    }
}
