package com.jmh.model;

import java.util.List;

public class BaseListResult extends BaseResult{
	private List<?> results;
	private int total;
	
	public List<?> getResults() {
		return results;
	}
	public void setResults(List<?> results) {
		this.results = results;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
}
