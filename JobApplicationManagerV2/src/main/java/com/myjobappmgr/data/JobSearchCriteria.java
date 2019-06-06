package com.myjobappmgr.data;

public class JobSearchCriteria {
	
	private String searchTerm;
	private String isSearchByDate;
	private String fromDate;
	private String toDate;
	
	
	public JobSearchCriteria() {
	}
	public JobSearchCriteria(String searchTerm) {
		this.searchTerm = searchTerm;
	}
	public String getSearchTerm() {
		return searchTerm;
	}
	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
	public String getIsSearchByDate() {
		return isSearchByDate;
	}
	public void setIsSearchByDate(String isSearchByDate) {
		this.isSearchByDate = isSearchByDate;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
}
