package top.alvinsite.demo.model.params;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("PerformanceQuery")
public class PerformanceQuery {
    private String nickname;
    private String account;
    private String department;
    private String departmentId;
    private String[] departmentScope;
    private String nature;
    private int year = 2020;

}
