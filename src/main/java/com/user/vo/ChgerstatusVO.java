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
public class ChgerstatusVO {
	
	private String chargerId;
	private String statId;
	private String chgerId;
	private String stat;
	private String method;
	private String output;
	private String statUpdDt;
	private String lastTsdt;
	private String lastTedt;
	private String nowTsdt;
	private String delYn;
	private String delDetail;
	private String chgerType;
	
	public ChgerstatusVO(String statId, String stat, String method, String output, String statUpdDt, String lastTsdt,
			String lastTedt, String nowTsdt, String delYn, String delDetail, String chgerType) {
		this.statId = statId;
		this.stat = stat;
		this.method = method;
		this.output = output;
		this.statUpdDt = statUpdDt;
		this.lastTsdt = lastTsdt;
		this.lastTedt = lastTedt;
		this.nowTsdt = nowTsdt;
		this.delYn = delYn;
		this.delDetail = delDetail;
		this.chgerType = chgerType;
	}
	
	
}
