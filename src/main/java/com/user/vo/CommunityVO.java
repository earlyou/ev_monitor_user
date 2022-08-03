package com.user.vo;

import java.util.Date;

public class CommunityVO  {
	private int pid;
	private String uid;
	private String location;
	private String title;
	private String tf;
	private String cimgname;
	private Date regdate;
	
	public CommunityVO() {
	}

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

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTf() {
		return tf;
	}

	public void setTf(String tf) {
		this.tf = tf;
	}

	public String getCimgname() {
		return cimgname;
	}

	public void setCimgname(String cimgname) {
		this.cimgname = cimgname;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "CommunityVO [pid=" + pid + ", uid=" + uid + ", location=" + location + ", title=" + title + ", tf=" + tf
				+ ", cimgname=" + cimgname + ", regdate=" + regdate + "]";
	}
	
}
	