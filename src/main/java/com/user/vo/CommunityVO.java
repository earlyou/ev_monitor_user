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
	private String location;
	private String title;
	private String statid;
	private String tf;
	private String cimgname;
	private Date regdate;
	
  
	public CommunityVO(int pid, String uid, String location, String title, String tf, String cimgname, Date regdate) {
		this.pid = pid;
		this.uid = uid;
		this.location = location;
		this.title = title;
		this.tf = tf;
		this.cimgname = cimgname;
		this.regdate = regdate;
	}
	// insert

	public CommunityVO(String uid, String location, String title, String tf, String cimgname) {
		this.uid = uid;
		this.location = location;
		this.title = title;
		this.tf = tf;
		this.cimgname = cimgname;
	}
	// update

	public CommunityVO(int pid, String uid, String location, String title, String tf, String cimgname) {
		this.pid = pid;
		this.uid = uid;
		this.location = location;
		this.title = title;
		this.tf = tf;
		this.cimgname = cimgname;
	}
	
	public CommunityVO(String uid, String statid, String tf, String cimgname, Date regdate) {
		this.uid = uid;
		this.statid = statid;
		this.tf = tf;
		this.cimgname = cimgname;
		this.regdate = regdate;
	}
	
}
	