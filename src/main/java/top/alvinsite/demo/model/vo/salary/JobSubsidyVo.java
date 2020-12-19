package top.alvinsite.demo.model.vo.salary;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @author Alvin
 */
@Data
@Alias("JobSubsidyVo")
public class JobSubsidyVo {
    private Integer id;

    private String nickname;

    private String job;

    private String evaluationResult;

    private Double subsidyFactor;

    private Integer length;

    private Double subsidy;

    private String remarks;
}
