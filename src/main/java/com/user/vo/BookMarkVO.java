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
	private int statid;
	private String uid;
	private int zcode;
	private String statnm;

	public BookMarkVO(int statid, String uid, int zcode, String statnm) {
		this.statid = statid;
		this.uid = uid;
		this.zcode = zcode;
		this.statnm = statnm;
	}
	
	

}
