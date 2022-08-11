package com.user.vo;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

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
	private String location;
	private String title;
	private String tf;
	private String cimgname;
	private Date regdate;
	
	private MultipartFile mf;
	
	// join용 필드
	private String statNm;
	private int usertypeid;
	
  
	// insert
	public CommunityVO(String uid, String statid, String location, String title, String tf, String cimgname) {
		super();
		this.uid = uid;
		this.statid = statid;
		this.location = location;
		this.title = title;
		this.tf = tf;
		this.cimgname = cimgname;
	}


	// update
	public CommunityVO(int pid, String statid, String location, String title, String tf, String cimgname) {
		super();
		this.pid = pid;
		this.statid = statid;
		this.location = location;
		this.title = title;
		this.tf = tf;
		this.cimgname = cimgname;
	}


	// 글목록
	public CommunityVO(int pid, String uid, String statid, String location, String title, Date regdate, String statNm,
			int usertypeid) {
		super();
		this.pid = pid;
		this.uid = uid;
		this.statid = statid;
		this.location = location;
		this.title = title;
		this.regdate = regdate;
		this.statNm = statNm;
		this.usertypeid = usertypeid;
	}

	
}
	