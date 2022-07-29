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
public class StationVO {
	
	private int statId;
	private String statNm;
	private String addr;
	private String location;
	private int zcode;
	private Double lat;
	private Double lng;
	private String useTime;
	private String parkingFree;
	private String note;
	private String limitYn;
	private String limitDetail;
	private String busiId;
	private String bnm;
	private String busiNm;
	private String busiCall;
	
	
	public StationVO(String statNm, String addr, String location, int zcode, Double lat, Double lng, String useTime,
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
	
	

	

}
