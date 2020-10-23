package top.alvinsite.demo.model.enums;

import java.util.List;

public interface BasicEnum{
    List<BasicEnum> getAll();

    String getTitle();



    String getId();

    int ordinal();
}
