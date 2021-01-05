package top.alvinsite.demo.model.dto.performance;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.support.Excel;

/**
 * @author Administrator
 */
@Data
@Alias("ResearcherPerformance")
public class ResearcherPerformance {

    @Excel(name = "姓名", col = 1)
    private String nickname;

    @Excel(name = "工号", col = 2)
    private String account;

    private Integer year;

    @Excel(name = "所属单位", col = 3)
    private String department;

    @Excel(name = "科研总分", col = 4)
    private Float totalPoints = 0f;

    @Excel(name = "纵向项目", col = 5)
    private Float longitudinalPoint = 0f;

    @Excel(name = "横向项目", col = 6)
    private Float crossingPoint = 0f;

    @Excel(name = "论文成果", col = 7)
    private Float paperPoint = 0f;

    @Excel(name = "著作成果", col = 8)
    private Float literaturePoint = 0f;

    @Excel(name = "专利成果", col = 9)
    private Float patentPoint = 0f;

    @Excel(name = "著作权", col = 10)
    private Float copyrightPoint = 0f;

    @Excel(name = "科研获奖", col = 11)
    private Float awardedPoint = 0f;

}
