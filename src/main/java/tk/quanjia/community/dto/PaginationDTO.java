package tk.quanjia.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 展示页面下面的分页导航
 */
@Data
public class PaginationDTO<T> {
    private List<T> data = null;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer totalPage;
    private Integer currentPage;
    private List<Integer> pagesList = new ArrayList<>();//存放所有的页

    /**
     * @param totalCount 问题列表  一共多少页
     * @param page       当前展示的页面
     * @param size       每一页一共 展示的问题数量
     */
    public void setPagination(Integer totalCount, Integer page, Integer size) {
        Integer totalPageNum;
        if (totalCount % size == 0) {
            totalPageNum = totalCount / size;
        } else {
            totalPageNum = totalCount / size + 1;
        }

        if (page < 1) {
            page = 1;
        }
        if (page > totalPageNum) {
            page = totalPageNum;
        }
        if (page == 0){
            page = 1;
        }
        this.currentPage = page;
        this.totalPage = totalCount;

        //当前页前面有三页
        int prePage = 3;
        for (int i = 1; i < page; i++) {
            pagesList.add(i);
            if (--prePage <= 0) {
                break;
            }
        }
        pagesList.add(page);
        //当前页后面有三页
        int nextPage = 3;
        for (int i = page + 1; i <= totalPageNum; i++) {
            pagesList.add(i);
            if (--nextPage <= 0) {
                break;
            }
        }


        //是否展示上一页
        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }

        //是否展示最后一页
        if (page.equals(totalPageNum)) {
            showNext = false;
        } else {
            showNext = true;
        }

        //是否展示首页
        if (pagesList.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }

        //是否展示最后一页
        if (pagesList.contains(totalPageNum)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }
}
