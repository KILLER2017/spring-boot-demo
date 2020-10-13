package top.alvinsite.demo.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ContractType {
    DEVELOP("01", "开发"),
    TRANSFER("02", "转让"),
    CONSULT("03", "咨询"),
    SERVICE("04", "服务"),
    TRANS("05", "培训类"),
    OTHERS("06", "非我校实际实施类"),
    ;

    private final String id;
    private final String name;

    public String getId() {
        return id;
    }

    public static ContractType getEnumById(String id) {
        for (ContractType type : ContractType.values()) {
            if (type.getId().equals(id)) {
                return type;
            }
        }
        return null;
    }
}
