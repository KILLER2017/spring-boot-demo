package top.alvinsite.demo.model.vo;


import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class EnumVO {
    private String id;
    private String title;


    public EnumVO(int id, String title) {
        this.id = String.valueOf(id);
        this.title = title;
    }

    public EnumVO(String id, String title) {
        this.id = id;
        this.title = title;
    }
}
