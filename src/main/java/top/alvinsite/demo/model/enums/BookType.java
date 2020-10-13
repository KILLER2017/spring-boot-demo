package top.alvinsite.demo.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookType {
    TEACHING_MATERIAL("2", "教材"),
    TREATISE("3", "专著"),
    TRANSLATION("4", "译著"),
    DEVELOPMENT_REPORT("400001", "皮书/发展报告"),
    POPULAR_SCIENCE_READING("400002", "科普读物"),
    ANTHOLOGY("5b5e3f775929813e0159447d1e1b0901", "论文集/摄影集/画册"),
    COMPILE("6", "编著"),
    REFERENCE_BOOKS("7", "工具书"),
    ;

    private final String id;
    private final String name;

    public String getId() {
        return id;
    }

    public static BookType getEnumById(String id) {
        for (BookType type : BookType.values()) {
            if (type.getId().equals(id)) {
                return type;
            }
        }
        return null;
    }
}
