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
	
	private String sstatNm;
	private String saddr;
	private double slat;
	private double slng;
	
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
