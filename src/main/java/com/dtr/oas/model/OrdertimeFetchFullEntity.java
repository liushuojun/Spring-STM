package com.dtr.oas.model;

import java.util.List;

public class OrdertimeFetchFullEntity {
	List<OrderFetchFullEntity> reslist;
	
	int totalNum;
	
	long loadTime;

	public List<OrderFetchFullEntity> getReslist() {
		return reslist;
	}

	public void setReslist(List<OrderFetchFullEntity> reslist) {
		this.reslist = reslist;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public long getLoadTime() {
		return loadTime;
	}

	public void setLoadTime(long loadTime) {
		this.loadTime = loadTime;
	}
	
	
}
