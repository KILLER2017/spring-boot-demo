package top.alvinsite.demo.model.params;

import lombok.Data;

@Data
public class Page {
    private int pageNum = 1;
    private int pageSize = 10;
}
