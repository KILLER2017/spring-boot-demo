package top.alvinsite.demo.model.entity.salary;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Alvin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ClassFeesStandard extends BaseModel {

    /**
     * 年份
     */
    private Integer year;

    /**
     * 职称级别
     */
    private String technicalPostsLevel;

    /**
     * 每节课时费
     */
    private Double factor;

}
