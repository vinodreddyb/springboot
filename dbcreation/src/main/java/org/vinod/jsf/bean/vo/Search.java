package org.vinod.jsf.bean.vo;

import java.io.Serializable;

public class Search implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4322080318199706415L;
	private String searchValue;
	private String searchBy;
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getSearchBy() {
		return searchBy;
	}
	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}
	
	
}
