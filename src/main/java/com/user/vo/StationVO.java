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
public class StationVO {
	// Attributes
	private String statId;
	private String statNm;
	private String addr;
	private String location;
	private String zcode;
	private String lat;
	private String lng;
	private String useTime;
	private String parkingFree;
	private String note;
	private String limitYn;
	private String limitDetail;
	private String busiId;
	private String bnm;
	private String busiNm;
	private String busiCall;
	
	
	public StationVO(String statNm, String addr, String location, String zcode, String lat, String lng, String useTime,
			String parkingFree, String note, String limitYn, String limitDetail, String busiId, String bnm,
			String busiNm, String busiCall) {
		this.statNm = statNm;
		this.addr = addr;
		this.location = location;
		this.zcode = zcode;
		this.lat = lat;
		this.lng = lng;
		this.useTime = useTime;
		this.parkingFree = parkingFree;
		this.note = note;
		this.limitYn = limitYn;
		this.limitDetail = limitDetail;
		this.busiId = busiId;
		this.bnm = bnm;
		this.busiNm = busiNm;
		this.busiCall = busiCall;
	}


	public StationVO(String statId, String statNm, String addr) {
		super();
		this.statId = statId;
		this.statNm = statNm;
		this.addr = addr;
	}
	
	

	

}
