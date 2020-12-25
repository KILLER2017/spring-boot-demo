package top.alvinsite.demo.service.performance.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.entity.performance.BaseEntity;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.service.performance.BasePerformanceService;
import top.alvinsite.demo.service.rule.ScoreDistributionService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alvin
 */
public abstract class AbstractPerformanceService<M extends BaseMapper<T>, T extends BaseEntity> extends ServiceImpl<M, T> implements BasePerformanceService<T> {

    @Resource
    private RedisTemplate<String, Integer> redisTemplate;

    @Autowired
    protected ScoreDistributionService scoreDistributionService;

    /**
     * 获取当前绩效类型
     * @return 绩效类型
     */
    protected abstract String getPerformance();

    /**
     * 获取项目成员列表
     * @param project 绩效项目
     * @return 项目成员列表
     */
    protected abstract List<ManagerUserDTO> getMembers(T project);

    /**
     * 计算项目成员排位
     * @param project 绩效项目
     * @return 排位
     */
    protected Integer calcOrder(T project) {
        int order = 1;
        for (ManagerUserDTO item : getMembers(project)) {
            if (item.getAccount() != null && item.getAccount().equals(project.getAccount())) {
                break;
            } else {
                order++;
            }
        }
        return order;
    }

    @Override
    public T filterDepartment(T project) {
        String userDepartment = project.getDepartment().getId();
        String firstMemberDepartment = getMembers(project).get(0).getDepartment();
        if (!userDepartment.equals(firstMemberDepartment)) {
            project.setScore(0);
        }
        return project;
    }

    /**
     * 从数据持久层获取数据
     * @param performanceQuery 查询参数
     * @return 项目列表
     */
    protected abstract List<T> beforeFindAll(PerformanceQuery performanceQuery);

    @Override
    public List<T> findAll(PerformanceQuery performanceQuery) {
        List<T> papers = beforeFindAll(performanceQuery);
        papers.stream()
                .map(this::getAnnualYear)
                .map(this::getProjectMemberNum)
                .map(this::getOrder)
                .map(this::calcProjectPoints)
                .map(this::filterDepartment)
                .collect(Collectors.toList());
        return papers;
    }

    @Override
    public T getOrder(T project) {
        String key = String.format("%s:%s:%s", getPerformance(), project.getAccount(), project.getId());
        Integer order = redisTemplate.opsForValue().get(key);
        if (order == null) {
            order = calcOrder(project);
            redisTemplate.opsForValue().set(key, order);
        }
        project.setSignedOrder(order);
        return project;
    }

    @Override
    public T getAnnualYear(T project) {
        return project;
    }

    @Override
    public T getProjectMemberNum(T project) {
        project.setMemberNum(getMembers(project).size());
        return project;
    }
}
