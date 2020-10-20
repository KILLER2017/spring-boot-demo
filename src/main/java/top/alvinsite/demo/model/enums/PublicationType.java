package top.alvinsite.demo.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PublicationType implements IEnum<String> {

    NORMAL_PERIODICAL("14", "一般期刊"),
    IMPORTANT_PERIODICAL("16", "重要报刊理论版"),
    SCI_1("2c9081f93b509967013b50b18c580064", "SCI一区"),
    SCI_2("2c9081f93b509967013b50c225320071", "SCI二区"),
    SCI_3("2c9081f93b509967013b50b1c5e00065", "SCI三区"),
    SCI_4("2c9081f93b509967013b50b209570066", "SCI四区"),
    SCHOOL_PERIODICAL("2c9081f93b509967013b50b209570066", "东莞理工学院学报"),
    // SCI_4("2c9081f93b509967013b50b209570066", "《人民日报》理论版"),
    // SCI_4("2c9081f93b509967013b50b209570066", "《光明日报》理论版"),
    // SCI_4("2c9081f93b509967013b50b209570066", "EI期刊"),
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
