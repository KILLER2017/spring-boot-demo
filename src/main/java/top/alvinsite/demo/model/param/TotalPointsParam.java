package top.alvinsite.demo.model.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@Alias("TotalPointsParam")
@AllArgsConstructor
@NoArgsConstructor
public class TotalPointsParam {
    private String account;
    private Integer year;
}
