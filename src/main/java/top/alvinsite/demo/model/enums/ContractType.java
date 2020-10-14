package top.alvinsite.demo.model.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ContractType implements IEnum<String> {
    DEVELOP("01", "开发"),
    TRANSFER("02", "转让"),
    CONSULT("03", "咨询"),
    SERVICE("04", "服务"),
    TRANS("05", "培训类"),
    OTHERS("06", "非我校实际实施类"),
    ;

    private final String value;
    private final String name;

    @Override
    public String getValue() {
        return value;
    }

    public static ContractType getEnumById(String id) {
        for (ContractType type : ContractType.values()) {
            if (type.getValue().equals(id)) {
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
