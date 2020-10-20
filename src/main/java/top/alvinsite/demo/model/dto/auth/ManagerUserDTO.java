package top.alvinsite.demo.model.dto.auth;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Repository;

@Data
@Alias("ManagerUserDTO")
public class ManagerUserDTO {

    private String nickname;
    private String account;

    @Override
    public String toString() {
        return nickname;
    }
}
