package top.alvinsite.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Data
@Alias("Department")
@AllArgsConstructor
@NoArgsConstructor
public class Department implements Serializable {
    private String id;

    @JsonValue
    private String title;

    @Override
    public String toString() {
        return this.title;
    }
}
