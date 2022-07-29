package com.user.vo;

import org.springframework.web.multipart.MultipartFile;

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
public class ChargerVO {
	private int ptid;
	private String chgernm;
	private String pinimg;
	private String ec;
	private String vol;
	private String power;
	private String clevel;
	
	private MultipartFile mf;

	public ChargerVO(int ptid, String chgernm, String pinimg, String ec, String vol, String power, String clevel) {
		this.ptid = ptid;
		this.chgernm = chgernm;
		this.pinimg = pinimg;
		this.ec = ec;
		this.vol = vol;
		this.power = power;
		this.clevel = clevel;
	}
	
	
	
}
