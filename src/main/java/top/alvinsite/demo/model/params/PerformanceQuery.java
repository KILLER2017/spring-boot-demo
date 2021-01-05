package top.alvinsite.demo.model.params;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.validation.ValidationGroup1;
import top.alvinsite.demo.model.validation.ValidationGroup2;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Administrator
 */
@Data
@Alias("PerformanceQuery")
public class PerformanceQuery {
    private String nickname;
    private String account;
    private String accountScope;
    private String department;

    @NotBlank(message = "{query.departmentId.notBlank}", groups = ValidationGroup2.class)
    private String departmentId;
    private String[] departmentScope;

    private String nature;
    private String startedTime;
    private String finishedTime;

    @NotNull(message = "{query.year.notNull}", groups = {ValidationGroup1.class, ValidationGroup2.class})
    private Integer year;


    public void setYear(Integer year) {
        this.year = year;
        this.startedTime = String.format("%s0901", year);
        this.finishedTime = String.format("%s0831", year + 1);
    }
}
