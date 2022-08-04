package com.user.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookMarkVO {
	
	private int bsid;
	private String statid;
	private String uid;
	
	private String uname;
	private String unickname;
	private String uclp;
	private int ucarmodelid;
	
	public BookMarkVO(int bsid, String statid, String uid) {
		this.bsid = bsid;
		this.statid = statid;
		this.uid = uid;
	}
	
	public BookMarkVO( String statid, String uid) {
		this.statid = statid;
		this.uid = uid;
	}
}
