package top.alvinsite.demo.model.entity.salary;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Alvin
 */
@Data
public class BaseModel {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @JsonIgnore
    private LocalDateTime createTime;

    @JsonIgnore
    private LocalDateTime updateTime;
}
