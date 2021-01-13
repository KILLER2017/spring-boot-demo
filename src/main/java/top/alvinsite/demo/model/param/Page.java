package top.alvinsite.demo.model.param;


/**
 * @author Alvin
 */
public class Page {
    private int pageNum = 1;
    private int pageSize = 10;
    private final static int MIN_PAGE_NUM = 1;
    private final static int MAX_PAGE_SIZE = 10000;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = Math.max(pageNum, MIN_PAGE_NUM);
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = Math.min(pageSize, MAX_PAGE_SIZE);
    }
}
