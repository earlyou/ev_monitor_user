package com.user.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FilterVO {
	private String filter; // 어떤 필터를 선택했는지 전송하는 필드 (R:지역 C:차모델 A:어댑터 B:사업자 S:속도 T:상태 P:주차비)
//	private String region; // 지역 선택
//	private double maxlat;
//	private double minlat;
//	private double maxlng;
//	private double minlng;
	private Integer carmodelid; // id에 저장되어있는 차모델(충전가능한 충전기를 분류할 수 있도록 함)
	private String adapter; // SM3ZE, 테슬라 차량 어댑터(1:5 to 7(SM3), 2:5 to 테슬라(테슬라, AC3상 급속에서 완속으로 사용가능) 3: 차데모, 4:DC콤보)
	private String bnm; // 충전소 사업자 이름
	private String chargespeed; // chgerType으로 1차 분류 후 -> 급속(output 40이상)/초급속(output 100이상)/완속
	private String chargerstat; // 사용가능한 충전기
	private String parkingFree; // 주차료 (Y: 무료, N:유료, C: 현장확인(테이블에서는 공백임))

//	// Constructors
//	public FilterVO(String filter, String region, Integer carmodelid, String adapter, String bnm, String chargespeed,
//			String chargerstat, String parkingFree) {
//		this.filter = filter;
//		this.region = region;
//		this.carmodelid = carmodelid;
//		this.adapter = adapter;
//		this.bnm = bnm;
//		this.chargespeed = chargespeed;
//		this.chargerstat = chargerstat;
//		this.parkingFree = parkingFree;
//	}
//	public FilterVO(String filter, double maxlat, double minlat, double maxlng, double minlng, Integer carmodelid, String adapter,
//			String bnm, String chargespeed, String chargerstat, String parkingFree) {
//		this.filter = filter;
//		this.maxlat = maxlat;
//		this.minlat = minlat;
//		this.maxlng = maxlng;
//		this.minlng = minlng;
//		this.carmodelid = carmodelid;
//		this.adapter = adapter;
//		this.bnm = bnm;
//		this.chargespeed = chargespeed;
//		this.chargerstat = chargerstat;
//		this.parkingFree = parkingFree;
//	}
	
	// 필터 타입 데이터를 아이디 변환한 뒤 배열에 넣음
	public String[] getFilterArr() {
		return filter == null ? new String[]{} : filter.split("");
	}
	
	public String[] getAdapterArr() {
		return adapter == null ? new String[]{} : adapter.split(",");
	}
	
	public String[] getBnmArr() {
		return bnm == null ? new String[]{} : bnm.split(",");
	}
	
	public String[] getChargespeedArr() {
		return chargespeed == null ? new String[]{} : chargespeed.split(",");
	}

	public String[] getChargerstatArr() {
		return chargerstat == null ? new String[]{} : chargerstat.split(",");
	}
	
	public String[] getParkingFreeArr() {
		return parkingFree == null ? new String[]{} : parkingFree.split(",");
	}

}