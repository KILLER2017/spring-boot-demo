package top.alvinsite.demo.model.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.alvinsite.demo.model.entity.performance.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreDistributionParam {
    private String department;
    private String performance;
    private Integer year;
    private Integer totals;
    private Integer position;

    public static ScoreDistributionParam build(Awarded project, String performance) {
        return new ScoreDistributionParam(
                project.getDepartment().getId(),
                performance,
                project.getApprovalProjectYear(),
                project.getMemberNum(),
                project.getSignedOrder());
    }

    public static ScoreDistributionParam build(Copyright project, String performance) {
        return new ScoreDistributionParam(
                project.getDepartment().getId(),
                performance,
                project.getApprovalProjectYear(),
                project.getMemberNum(),
                project.getSignedOrder());
    }

    public static ScoreDistributionParam build(CrossingProject project, String performance) {
        return new ScoreDistributionParam(
                project.getDepartment().getId(),
                performance,
                project.getApprovalProjectYear(),
                project.getMemberNum(),
                project.getSignedOrder());
    }

    public static ScoreDistributionParam build(Literature project, String performance) {
        return new ScoreDistributionParam(
                project.getDepartment().getId(),
                performance,
                project.getApprovalProjectYear(),
                project.getMemberNum(),
                project.getSignedOrder());
    }

    public static ScoreDistributionParam build(LongitudinalProject project, String performance) {
        return new ScoreDistributionParam(
                project.getDepartment().getId(),
                performance,
                project.getApprovalProjectYear(),
                project.getMemberNum(),
                project.getSignedOrder());
    }

    public static ScoreDistributionParam build(Paper project, String performance) {
        return new ScoreDistributionParam(
                project.getDepartment().getId(),
                performance,
                project.getApprovalProjectYear(),
                project.getMemberNum(),
                project.getSignedOrder());
    }

    public static ScoreDistributionParam build(Patent project, String performance) {
        return new ScoreDistributionParam(
                project.getDepartment().getId(),
                performance,
                project.getApprovalProjectYear(),
                project.getMemberNum(),
                project.getSignedOrder());
    }
}
