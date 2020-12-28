package top.alvinsite.demo.service.performance;

import top.alvinsite.demo.model.dto.performance.ResearcherPerformance;
import top.alvinsite.demo.model.entity.performance.BaseEntity;
import top.alvinsite.demo.model.params.PerformanceQuery;

import java.util.List;

/**
 * @author Alvin
 */
public interface BasePerformanceService<T extends BaseEntity>  {

    /**
     * 获取绩效项目列表
     * @param performanceQuery 过滤条件
     * @return 绩效项目列表
     */
    List<T> findAll(PerformanceQuery performanceQuery);

    /**
     * 获取项目所属年度
     * @param project 绩效项目
     * @return 填充项目年度后的绩效项目
     */
    T getAnnualYear(T project);

    /**
     * 获取项目绩效排序
     * @param project
     * @return
     */
     T getOrder(T project);

    /**
     * 获取项目总人数
     * @param project 绩效项目
     * @return 填充总人数后的绩效项目
     */
    default T getProjectMemberNum(T project) {
        return project;
    };

    /**
     * 获取项目绩效分数
     * @param project 绩效项目
     * @return 填充绩效分数后的绩效项目
     */
    default T calcProjectPoints(T project) {
        return project;
    };

    /**
     * 只有和一号成员同单位的成员才能获取分数
     * @param project 绩效项目
     * @return
     */
    T filterDepartment(T project);

    /**
     * 判断是否和一号成员同单位
     * @param project 绩效项目
     * @return
     */
    boolean isUniformDepartment(T project);
    /**
     * 设置用户绩效项目年度总分数
     * @param researcherPerformance 用户年度绩效分数
     * @param totalPoints 年度分数
     */
    void setTotalPoints(ResearcherPerformance researcherPerformance, float totalPoints);

    /**
     * 计算用户绩效项目年度总分数
     * @param researcherPerformance 用户年度绩效分数
     * @return 填充绩效项目年度总分数后的用户年度绩效分数
     */
    default ResearcherPerformance calcTotalPoints(ResearcherPerformance researcherPerformance) {
        PerformanceQuery query = new PerformanceQuery();
        query.setAccount(researcherPerformance.getAccount());
        query.setYear(researcherPerformance.getYear());

        List<T> list = findAll(query);
        float totalPoints = 0;
        for (T awarded : list) {
            this.calcProjectPoints(awarded);
            totalPoints += awarded.getScore();
        }

        setTotalPoints(researcherPerformance, totalPoints);
        return researcherPerformance;
    };
}
