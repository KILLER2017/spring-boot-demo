package top.alvinsite.demo.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("DepartmentVO")
public class DepartmentVO implements Serializable {
    private String id;


    private String title;

}
