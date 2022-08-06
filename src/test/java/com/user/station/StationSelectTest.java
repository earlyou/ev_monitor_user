package com.user.station;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.user.biz.StationBiz;
import com.user.vo.StationVO;

@SpringBootTest
class StationSelectTest {
	
	@Autowired
	StationBiz stationbiz;
	
	@Test
	void contextLoads() {
		StationVO obj = null;
		try {
			obj = stationbiz.get("KP004278");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(obj);
	}

}
