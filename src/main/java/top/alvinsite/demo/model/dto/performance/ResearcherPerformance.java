package top.alvinsite.demo.model.dto.performance;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.support.ExcelColumn;

/**
 * @author Administrator
 */
@Data
@Alias("ResearcherPerformance")
public class ResearcherPerformance {

    @ExcelColumn(value = "姓名", col = 1)
    private String nickname;

    @ExcelColumn(value = "工号", col = 2)
    private String account;

    private Integer year;

    @ExcelColumn(value = "所属单位", col = 3)
    private String department;

    @ExcelColumn(value = "科研总分", col = 4)
    private Float totalPoints = 0f;

    @ExcelColumn(value = "纵向项目", col = 5)
    private Float longitudinalPoint = 0f;

    @ExcelColumn(value = "横向项目", col = 6)
    private Float crossingPoint = 0f;

    @ExcelColumn(value = "论文成果", col = 7)
    private Float paperPoint = 0f;

    @ExcelColumn(value = "著作成果", col = 8)
    private Float literaturePoint = 0f;

    @ExcelColumn(value = "专利成果", col = 9)
    private Float patentPoint = 0f;

    @ExcelColumn(value = "著作权", col = 10)
    private Float copyrightPoint = 0f;

    @ExcelColumn(value = "科研获奖", col = 11)
    private Float awardedPoint = 0f;

}
