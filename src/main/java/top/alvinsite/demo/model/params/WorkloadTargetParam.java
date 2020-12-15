package top.alvinsite.demo.model.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkloadTargetParam {
    private String department;
    private String level;
    private String postType;

    @Override
    public String toString() {
        return String.format("目标工作量（级别：%s，类型岗位：%s）", level, postType);
    }
}
