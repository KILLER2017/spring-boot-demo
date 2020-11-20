package top.alvinsite.demo.model.params;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotNull;

/**
 * @author Administrator
 */
@Data
@Alias("PerformanceQuery")
public class PerformanceQuery {
    private String nickname;
    private String account;
    private String department;
    private String departmentId;
    private String[] departmentScope;
    private String nature;
    private String startedTime;
    private String finishedTime;

    @NotNull(message = "年份不能为空")
    private Integer year;


    public void setYear(Integer year) {
        this.year = year;
        this.startedTime = String.format("%s0901", year);
        this.finishedTime = String.format("%s0831", year + 1);
    }
}
