package com.teamtek.core.vo;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;

/**
* @ClassName: DataGrid
* @Description: 分页表格实体
* @author shenzhihao 
* @email zhihao@teamtek.cn
* @date 2017年12月29日 下午3:38:50
*
*/ 
public class DataGrid<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int totalCount;

    private int pageSize;

    private int totalPage;

    private int currPage;

    private List<T> list;

    /**
     * 分页
     * 
     * @param list
     *            列表数据
     * @param totalCount
     *            总记录数
     * @param pageSize
     *            每页记录数
     * @param currPage
     *            当前页数
     */
    public DataGrid(List<T> list, int totalCount, int pageSize, int currPage) {
	this.list = list;
	this.totalCount = totalCount;
	this.pageSize = pageSize;
	this.currPage = currPage;
	this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
    }

    /**
    * <p>Title: 根据MybatisPlus内置的分页辅助类构造</p>
    * <p>Description: </p>
    * @param page
    */
    public DataGrid(Page<T> page){
	this.list = page.getRecords();
	this.totalCount = page.getTotal();
	this.pageSize = page.getSize();
	this.currPage = page.getCurrent();
	this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
    }
    
    public int getTotalCount() {
	return totalCount;
    }

    public void setTotalCount(int totalCount) {
	this.totalCount = totalCount;
    }

    public int getPageSize() {
	return pageSize;
    }

    public void setPageSize(int pageSize) {
	this.pageSize = pageSize;
    }

    public int getTotalPage() {
	return totalPage;
    }

    public void setTotalPage(int totalPage) {
	this.totalPage = totalPage;
    }

    public int getCurrPage() {
	return currPage;
    }

    public void setCurrPage(int currPage) {
	this.currPage = currPage;
    }

    public List<T> getList() {
	return list;
    }

    public void setList(List<T> list) {
	this.list = list;
    }
}
