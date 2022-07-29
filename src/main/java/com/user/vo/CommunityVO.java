package com.user.vo;

import java.util.Date;

public class CommunityVO  {
	private int pid;
	private String uid;
	private int statId;
	private String tf;
	private String cimgname;
	private Date regdate;
	private int zcode;
	private String statnm;
	
	public CommunityVO() {
	}

	public CommunityVO(int pid, String uid, int statId, String tf, String cimgname, Date regdate, int zcode,
			String statnm) {
		this.pid = pid;
		this.uid = uid;
		this.statId = statId;
		this.tf = tf;
		this.cimgname = cimgname;
		this.regdate = regdate;
		this.zcode = zcode;
		this.statnm = statnm;
	}

	public CommunityVO(int pid, String uid, int statId, String tf, String cimgname, int zcode, String statnm) {
		this.pid = pid;
		this.uid = uid;
		this.statId = statId;
		this.tf = tf;
		this.cimgname = cimgname;
		this.zcode = zcode;
		this.statnm = statnm;
	}

	// 커뮤니티 수정(업데이트) 생성자
	public CommunityVO(String tf, String cimgname, String statnm) {
		this.tf = tf;
		this.cimgname = cimgname;
		this.statnm = statnm;
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

	public int getStatId() {
		return statId;
	}

	public void setStatId(int statId) {
		this.statId = statId;
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

	public int getZcode() {
		return zcode;
	}

	public void setZcode(int zcode) {
		this.zcode = zcode;
	}

	public String getStatnm() {
		return statnm;
	}

	public void setStatnm(String statnm) {
		this.statnm = statnm;
	}

	@Override
	public String toString() {
		return "CommunityVO [pid=" + pid + ", uid=" + uid + ", statId=" + statId + ", tf=" + tf + ", cimgname="
				+ cimgname + ", regdate=" + regdate + ", zcode=" + zcode + ", statnm=" + statnm + "]";
	}
	


}