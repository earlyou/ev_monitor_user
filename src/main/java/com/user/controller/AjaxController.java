package com.user.controller;

import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.biz.BookMarkBiz;
import com.user.biz.ChgerstatusBiz;
import com.user.biz.StationBiz;
import com.user.biz.UserAuthorityBiz;
import com.user.biz.UsersBiz;
import com.user.vo.BookMarkVO;
import com.user.vo.ChgerstatusVO;
import com.user.vo.FilterVO;
import com.user.vo.StationVO;
import com.user.vo.UsersVO;

@RestController
public class AjaxController {
	
	@Autowired
	UsersBiz ubiz;
	
	@Autowired
	UserAuthorityBiz uabiz;
	
	@Autowired
	StationBiz sbiz;
	
	@Autowired
	ChgerstatusBiz cbiz;
	
	@Autowired
	BookMarkBiz bookmarkbiz;
	
  
	@RequestMapping("getstation")
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
	
	@RequestMapping("checkid") // 회원가입시 ID 유효성 검사
	public String checkid(String id) {		
		String result = "";
		UsersVO uvo = null;
		
		if(!Pattern.matches("^[0-9a-zA-Z]*$",id)) { 
			return "2"; 
		}		
		try {
			uvo = ubiz.get(id);
			if(uvo == null) {
				result = "0";
			}else{
				result = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	@RequestMapping("addbookmark")
	public void addbookmark(String statId, String uid) {
		BookMarkVO bm = new BookMarkVO(statId, uid);
		
		try {
			bookmarkbiz.register(bm);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("rmbookmark")
	public void rmbookmark(String statId, String uid) {
		BookMarkVO bm = new BookMarkVO(statId, uid);
		
		try {
			bookmarkbiz.rmbookmark(bm);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("checkclp") // 번호판 검사
	public String checkclp(String clp) {		
		String result = "";
		UsersVO uvo = null;
						
		try {
			uvo = ubiz.clpget(clp);
			if(uvo == null) {
				result = "0";
			}else{
				result = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	@RequestMapping("/delete") // 즐겨찾기 삭제
	public String deletebtn(int bsid, Model m, HttpSession session, BookMarkVO obj) {
		try {
			bookmarkbiz.remove(bsid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:mybookmark?bsid="+bsid;
	}
	/*
	@RequestMapping("/movemap") // 즐겨찾기에서 지도 이동
	public String movemap(int bsid, Model m, HttpSession session, BookMarkVO obj) {
		try {
			bookmarkbiz.get(bsid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:mybookmark?bsid="+bsid;
	}
	*/
}
