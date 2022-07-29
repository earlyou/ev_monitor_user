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
public class CarModelVO {
	private int carmodelid;
	private String mname;
	private String mbrand;
	private String carimg;
	private double crange;
	private double batterysize;
	private int fptid;
	private int sptid;
	
	private MultipartFile mf;

	public CarModelVO(int carmodelid, String mname, String mbrand, String carimg, double crange, double batterysize,
			int fptid, int sptid) {
		this.carmodelid = carmodelid;
		this.mname = mname;
		this.mbrand = mbrand;
		this.carimg = carimg;
		this.crange = crange;
		this.batterysize = batterysize;
		this.fptid = fptid;
		this.sptid = sptid;
	}
	
	
	
}
