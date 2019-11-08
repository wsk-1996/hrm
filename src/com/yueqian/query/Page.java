package com.yueqian.query;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class Page {
    private List list;
    private Integer beginPage=1;
    private Integer endPage;
    private Integer prevPage;
    private Integer nextPage;
    private Integer totalCount;
    private Integer pageSize;
    private Integer currentPage;
    public Page(List list, Integer totalCount, Integer pageSize,Integer currentPage) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.prevPage=this.currentPage-1<=0?beginPage:this.currentPage-1;
        this.endPage=(this.totalCount%this.pageSize)<=0?this.totalCount/this.pageSize:(this.totalCount/this.pageSize)+1;
        this.nextPage=this.currentPage+1>=this.endPage?this.endPage:this.currentPage+1;
    }
}
