package top.alvinsite.demo.model.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Data
@Alias("Teacher")
public class Teacher implements Serializable {
    private String account;
    private String nickname;
    private Department department;
    private String phone;
}
