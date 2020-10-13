package top.alvinsite.demo.model.entity;

import lombok.Data;

@Data
public class Researcher {
    private String id;
    private String nickname;
    private String account;
    private Integer departmentCode;
    private String departmentTitle;
}
