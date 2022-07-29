package com.user.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.biz.ChgerstatusBiz;
import com.user.biz.StationBiz;
import com.user.vo.ChgerstatusVO;
import com.user.vo.FilterVO;
import com.user.vo.StationVO;

@RestController
public class AjaxController {
	
	@Autowired
	StationBiz sbiz;
	
	@Autowired
	ChgerstatusBiz cbiz;
	
	@RequestMapping("/getstation")
	public String getstation(Model m, String filter, Integer carmodelid, 
			String adapter, String bnm, String chargespeed, String chargerstat, String parkingFree) {

		if(adapter=="") {
			adapter = null;
		}
		if(bnm=="") {
			bnm = null;
		}
		if(chargespeed=="") {
			chargespeed = null;
		}
		if(chargerstat=="") {
			chargerstat = null;
		}
		if(parkingFree=="") {
			parkingFree = null;
		}
		
		FilterVO f = new FilterVO(filter, carmodelid, adapter, bnm, chargespeed,chargerstat,parkingFree);
		List<StationVO> slist = null;
		try {
			slist = sbiz.selectfilter(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray arr = new JSONArray();
		for (StationVO o : slist) {
			JSONObject json = new JSONObject();
			json.put("statId", o.getStatId());
			json.put("statNm", o.getStatNm());
			json.put("addr", o.getAddr());
			json.put("location", o.getLocation());
			json.put("zcode", o.getZcode());
			json.put("lat", o.getLat());
			json.put("lng", o.getLng());
			json.put("useTime", o.getUseTime());
			json.put("parkingFree", o.getParkingFree());
			json.put("note", o.getNote());
			json.put("limitYn", o.getLimitYn());
			json.put("limitDetail", o.getLimitDetail());
			json.put("busiId", o.getBusiId());
			json.put("bnm", o.getBnm());
			json.put("busiNm", o.getBusiNm());
			json.put("busiCall", o.getBusiCall());
			arr.put(json);
		}
		return arr.toString();
	}
	
	@RequestMapping("getchger")
	public String getchger() {
		
		List<ChgerstatusVO> list = null;
		try {
			list = cbiz.get();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		JSONArray arr = new JSONArray();
		for (ChgerstatusVO o : list) {
			JSONObject json = new JSONObject();
			json.put("chargerId", o.getChgerId());
			json.put("statId", o.getStatId());
			json.put("chgerId", o.getChgerId());
			json.put("stat", o.getStat());
			json.put("method", o.getMethod());
			json.put("output", o.getOutput());
			json.put("statupdDt", o.getStatUpdDt());
			json.put("lastTsdt", o.getLastTsdt());
			json.put("lastTedt", o.getLastTedt());
			json.put("nowTsdt", o.getNowTsdt());
			json.put("delYn", o.getDelYn());
			json.put("delDetail", o.getDelDetail());
			json.put("chgerType", o.getChgerType());
			arr.put(json);
		}
		return arr.toString();
	}
}
