package top.alvinsite.demo.model.entity.type;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("ProjectType")
public class ProjectType {
    private String id;

    @JsonValue
    private String title;
}
