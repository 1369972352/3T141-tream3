package cn.smbms.entity;

import java.util.List;

/**
 * 分页实体
 * @param <T>
 */
public class Pager<T> {
    private Integer currentPageNo ; //当前页码-来自于用户输入
    private Integer pageNo ; //每页行数
    private Integer totalRows;//总行数
    private Integer totalPageCount ; //总页数
    private List<T>datas;//分页数据集合

    public Integer getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(Integer currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
    }

    public Integer getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(Integer totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }
}
