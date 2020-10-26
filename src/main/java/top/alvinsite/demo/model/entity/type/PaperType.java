package top.alvinsite.demo.model.entity.type;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @author Alvin
 */
@Alias("PaperType")
@Data
@TableName(value = "DM_PAPER_TYPE")
public class PaperType {

    @TableId(value = "ID", type = IdType.AUTO)
    private String id;

    @TableField(value = "NAME")
    @JsonValue
    private String title;

}
