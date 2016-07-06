package com.gaohuan.mybatis.entity;

import java.util.List;

/**
 * 分页工具类
 *
 * @param <T>
 * @author 化金
 */
public class Page<T> {
    // 分页参数
    private int pageNo = 1; // 当前页码
    private int pageSize = 4; // 页面大小
    private long startIndex;// 当前页开始角标
    private Long count = 0L;// 总条数
    private List<T> result;// 返回结果

    public Page(int pageNo, int pageSize) {
        super();
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public Page() {

    }

    /**
     * 返回起始角标
     *
     * @return
     * @author MingDing.Li
     */
    public long getStartIndex() {
        return ((this.pageNo - 1) * this.pageSize);
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

}
