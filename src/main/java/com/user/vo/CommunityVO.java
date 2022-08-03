package com.user.vo;

import java.util.Date;

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
public class CommunityVO  {
	private int pid;
	private String uid;
	private String statid;
	private String tf;
	private String cimgname;
	private Date regdate;
	
	
	public CommunityVO(String uid, String statid, String tf, String cimgname, Date regdate) {
		this.uid = uid;
		this.statid = statid;
		this.tf = tf;
		this.cimgname = cimgname;
		this.regdate = regdate;
	}
	


}