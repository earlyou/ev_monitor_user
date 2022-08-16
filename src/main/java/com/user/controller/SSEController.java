package com.user.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.user.biz.EmitterBiz;
import com.user.biz.SSEBiz;
import com.user.biz.StationBiz;
import com.user.biz.UsersBiz;
import com.user.vo.SSEVO;
import com.user.vo.StationVO;
import com.user.vo.UsersVO;

@RestController
public class SSEController {
	
	@Autowired
	UsersBiz ubiz;
	
	@Autowired
	StationBiz stbiz;
	
	@Autowired
	SSEBiz sbiz;
	
	@Autowired
	EmitterBiz ebiz;
	
	@RequestMapping("setalarm") 
	public void deletecombtn(String uid, String statid, int remainfuel, Model m) {
		// 각종 계산
		UsersVO user = null;
		StationVO station = null;
		
		try {
			user = ubiz.alarmbattery(uid);
			station = stbiz.selectoutput(statid);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		double setalarmtimeh = (user.getBatterysize()*((100-remainfuel)*0.01))/station.getOutput();
		System.out.println("1: "+(user.getBatterysize()*((100-remainfuel)*0.01))/station.getOutput());
		
		System.out.println("2: "+setalarmtimeh);
		
		double setalarmtimes = setalarmtimeh*3600;
		System.out.println("3: "+setalarmtimes);
		
		
		LocalDateTime nowDate = LocalDateTime.now();
		// 알람시간
		LocalDateTime alarmtime = nowDate.plusSeconds(Math.round(setalarmtimes));
		
		String parsedalarmtime = alarmtime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		
		
		SSEVO v = new SSEVO(uid+nowDate, uid, statid, parsedalarmtime, "충전을 시작했습니다.", "aset");
		
		try {
			sbiz.register(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// login한 유저에 sse 연결
	@GetMapping(value = "/subscribe/{id}", produces = "text/event-stream")
	public SseEmitter subscribe(@PathVariable String id,
	                            @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId) {
	    return ebiz.subscribe(id, lastEventId);
	}

	
}
