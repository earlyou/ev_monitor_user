package com.user.vo;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class SearchFilterVO {
	private String searchtype; // 어떤 타입 검색을 했는지 전송하는 필드 (T:제목+내용 / L:위치)
	private String searchkeyword; 

	private int page;
	private int perPageNum;
	
	public SearchFilterVO() {
		this.page = 1;
		this.perPageNum = 10;
	}
	
	public SearchFilterVO(int page, int perPageNum) {
		super();
		this.page = page;
		this.perPageNum = perPageNum;
	}
	
	
	
	public int getDataStart() {
		return (this.page - 1) * perPageNum;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if(page < 0) {
			this.page = 1;
		}else {
			this.page = page;
		}
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}

	public String getSearchtype() {
		return searchtype;
	}

	public void setSearchtype(String searchtype) {
		this.searchtype = searchtype;
	}

	public String getSearchkeyword() {
		return searchkeyword;
	}

	public void setSearchkeyword(String searchkeyword) {
		this.searchkeyword = searchkeyword;
	}



}