package top.alvinsite.demo.model.dto.performance;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.support.ExcelColumn;

@Data
@Alias("ResearcherPerformance")
public class ResearcherPerformance {

    @ExcelColumn(value = "姓名", col = 1)
    private String nickname;
    private String account;

    @ExcelColumn(value = "所属单位", col = 2)
    private String department;

    @ExcelColumn(value = "激励绩效分值", col = 3)
    private Float totalPoints;

    @ExcelColumn(value = "纵向项目", col = 4)
    private Float longitudinalPoint;

    @ExcelColumn(value = "横向项目", col = 5)
    private Float crossingPoint;

    @ExcelColumn(value = "论文成果", col = 6)
    private Float paperPoint;

    @ExcelColumn(value = "著作成果", col = 7)
    private Float literaturePoint;

    @ExcelColumn(value = "专利成果", col = 8)
    private Float patentPoint;

    @ExcelColumn(value = "著作权", col = 9)
    private Float copyrightPoint;

    @ExcelColumn(value = "科研获奖", col = 10)
    private Float awardedPoint;

}
