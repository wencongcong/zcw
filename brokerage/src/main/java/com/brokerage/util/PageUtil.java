package com.brokerage.util;

import java.util.ArrayList;
import java.util.List;

public class PageUtil<T> {
	private int currentPage;
	private int pageSize;
	private int startRow;
	private int totalCount;
	private int totalPage;
	private List<T> lists = new ArrayList<T>();

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		if (currentPage <= 0) {
			currentPage = 1;
		} else if (currentPage > this.getTotalPage()) {
			if (this.getTotalPage() == 0) {
				currentPage = 1;
			} else {
				currentPage = this.getTotalPage();
			}
		}
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize <= 0) {
			pageSize = 5;
		}
		this.pageSize = pageSize;
	}

	public int getStartRow() {
		return (this.getCurrentPage() - 1) * this.getPageSize();
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		if (this.getTotalCount() % this.getPageSize() == 0) {
			return this.getTotalCount() / this.getPageSize();
		} else {
			return this.getTotalCount() / this.getPageSize() + 1;
		}
	}

	public List<T> getLists() {
		return lists;
	}

	public void setLists(List<T> lists) {
		this.lists = lists;
	}

}
