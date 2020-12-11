package top.alvinsite.demo.model.dto.auth;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("ManagerUserDTO")
public class ManagerUserDTO {

    private String nickname;
    private String account;
    private String department;

    @Override
    public String toString() {
        return nickname;
    }
}
