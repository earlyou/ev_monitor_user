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
public class StationFareVO {
	private int cid;
	private String busiId;
	private String bnm;
	private String cs;
	private String memYN;
	private String apartYN;
	private String time;
	private String seasons;
	private String cf;
	
	public StationFareVO(String busiId, String bnm, String cs, String memYN, String apartYN, String time,
			String seasons, String cf) {
		this.busiId = busiId;
		this.bnm = bnm;
		this.cs = cs;
		this.memYN = memYN;
		this.apartYN = apartYN;
		this.time = time;
		this.seasons = seasons;
		this.cf = cf;
	}
	
	
	
}
	