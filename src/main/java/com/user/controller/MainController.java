package com.user.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.user.biz.BookMarkBiz;
import com.user.biz.MailBiz;
import com.user.biz.StationBiz;
import com.user.biz.UserAuthorityBiz;
import com.user.biz.UsersBiz;
import com.user.vo.BookMarkVO;
import com.user.vo.StationVO;
import com.user.vo.UsersVO;

@Controller
public class MainController {

	@Autowired
	UserAuthorityBiz uabiz;
	
	@Autowired
	UsersBiz ubiz;
	
	@Autowired
	MailBiz mailbiz;
	
	@Autowired
	BookMarkBiz bookmarkbiz;
	
	@Autowired
	StationBiz stationbiz;
	
	@RequestMapping("/")
	public String main(Model m) {
		m.addAttribute("center", "homecenter");
		return "index";
	}
	
	@RequestMapping("/map") // 충전소 찾기를 클릭 시, 지도 화면으로 이동
	public String map(Model m, HttpSession session, String statid) {
		List<BookMarkVO> bookmark = null;
		List<BookMarkVO> bookmlist = null;
		StationVO stat = null;
		UsersVO user = null;
		try {
			stat = stationbiz.get(statid);
			user = (UsersVO) session.getAttribute("loginmember");
			bookmark = bookmarkbiz.getbyuid(user.getId());
			m.addAttribute("bookmark", bookmark);
			bookmlist = bookmarkbiz.getcustomerbookmark(user.getId());
			m.addAttribute("bookmlist", bookmlist);
		} catch (Exception e) {
			
		}
		if (stat != null) {
			m.addAttribute("bmstation", stat);
		}
		m.addAttribute("left", "map/sideleftbar");
		m.addAttribute("center", "map/mapcenter");
		m.addAttribute("session", session.getAttribute("loginmember"));
		return "map/map";
	}
	
	@RequestMapping("/example/elements")
	public String elements() {
		return "example/elements";
	}
	
	@RequestMapping("/example/generic")
	public String generic() {
		return "example/generic";
	}
	
	@RequestMapping("/example/index")
	public String index() {
		return "example/index";
	}
	
	@RequestMapping("forgetcustid")
	public String forgetcustid(Model m, String msg) {
		m.addAttribute("center", "forgetcustid");
		return "index";
	}
	
	@ResponseBody
	@RequestMapping("/findidimpl")
	public String findidimpl(String name, String email) { // 아이디 찾기
		String chid = null;
		try {
			chid = ubiz.getselectchid(name, email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chid;
	}
	
	@RequestMapping("forgetcustpwd")
	public String forgetcustpwd(Model m) {
		m.addAttribute("center", "forgetcustpwd");
		return "index";
	}	
	
	@ResponseBody
	@RequestMapping("/findpwdimpl")
	public String findpwdimpl(String name, String id, String email) { // 비밀번호 찾기
		
		String pwdresult = null;
		try {
			pwdresult = ubiz.getselectchpwd(name, id, email);
			if(pwdresult != "0") {
				String pwdvalue = ubiz.getselectchpwdvalue(name, id, email);
				mailbiz.sendMail(pwdresult, pwdvalue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		return pwdresult;
	}
	
	@RequestMapping("/ctichargingtypeinfo") // 충전기 타입 안내 페이지
	public String chargingtypeinfo(Model m) {
		m.addAttribute("center", "/cti/chargingtypeinfo");
		return "index";
	}
	
	@RequestMapping("/evielectricvehicleinfo") // 전기차 모델 안내 페이지
	public String electricvehicleinfo(Model m) {
		m.addAttribute("center", "/evi/electricvehicleinfo");
		m.addAttribute("evicenter", "/evi/evicenter");		
		return "index";
	}
	
	@RequestMapping("/kiacar") 
	public String kiacar(Model m) { // 기아 자동차 안내 페이지
		m.addAttribute("center", "/evi/electricvehicleinfo");
		m.addAttribute("evicenter", "/evi/kiacar");		
		return "index";
	}
	
	@RequestMapping("/hyundaicar") 
	public String hyundaicar(Model m) {// 현대 자동차 안내 페이지
		m.addAttribute("center", "/evi/electricvehicleinfo");
		m.addAttribute("evicenter", "/evi/hyundaicar");		
		return "index";
	}
	
	@RequestMapping("/genesis") 
	public String genesis(Model m) { // 제네시스 자동차 안내 페이지
		m.addAttribute("center", "/evi/electricvehicleinfo");
		m.addAttribute("evicenter", "/evi/genesis");		
		return "index";
	}
	
	@RequestMapping("/sycar") 
	public String sycar(Model m) { // 쌍용 자동차 안내 페이지
		m.addAttribute("center", "/evi/electricvehicleinfo");
		m.addAttribute("evicenter", "/evi/sycar");		
		return "index";
	}	
	
	@RequestMapping("/samsungcar") 
	public String samsungcar(Model m) { // 르노삼성 자동차 안내 페이지
		m.addAttribute("center", "/evi/electricvehicleinfo");
		m.addAttribute("evicenter", "/evi/samsungcar");		
		return "index";
	}
	
	@RequestMapping("/chevroletcar") 
	public String chevroletcar(Model m) {
		m.addAttribute("center", "/evi/electricvehicleinfo");
		m.addAttribute("evicenter", "/evi/chevroletcar");		
		return "index";
	}
	
	@RequestMapping("/teslacar") 
	public String teslacar(Model m) { // 테슬라 자동차 안내 페이지
		m.addAttribute("center", "/evi/electricvehicleinfo");
		m.addAttribute("evicenter", "/evi/teslacar");		
		return "index";
	}
	
	@RequestMapping("/benzcar") 
	public String benzcar(Model m) { // 벤츠 자동차 안내 페이지
		m.addAttribute("center", "/evi/electricvehicleinfo");
		m.addAttribute("evicenter", "/evi/benzcar");		
		return "index";
	}

	@RequestMapping("/bmwcar") 
	public String bmwcar(Model m) {
		m.addAttribute("center", "/evi/electricvehicleinfo");
		m.addAttribute("evicenter", "/evi/bmwcar");		
		return "index";
	}
	
	@RequestMapping("/audicar") 
	public String audicar(Model m) { // 아우디 자동차 안내 페이지
		m.addAttribute("center", "/evi/electricvehicleinfo");
		m.addAttribute("evicenter", "/evi/audicar");		
		return "index";
	}	
	
	@RequestMapping("/minicar") 
	public String minicar(Model m) { // 미니 자동차 안내 페이지
		m.addAttribute("center", "/evi/electricvehicleinfo");
		m.addAttribute("evicenter", "/evi/minicar");		
		return "index";
	}
	
	@RequestMapping("/nissancar") 
	public String nissancar(Model m) { // 닛산 자동차 안내 페이지
		m.addAttribute("center", "/evi/electricvehicleinfo");
		m.addAttribute("evicenter", "/evi/nissancar");		
		return "index";
	}	

	@RequestMapping("volvocar") 
	public String volvocar(Model m) { // 볼보 자동차 안내 페이지
		m.addAttribute("center", "/evi/electricvehicleinfo");
		m.addAttribute("evicenter", "/evi/volvocar");		
		return "index";
	}
	
	@RequestMapping("/customercenter") // 고객센터
	public String customercenter(Model m) {
		m.addAttribute("center", "customercenter");
		return "index";
	}
	
	@RequestMapping("/introintroduce") // 소개 페이지
	public String introduce(Model m) {
		m.addAttribute("center", "/intro/introduce");
		m.addAttribute("introcenter", "/intro/introcenter");	
		return "index";
	}
	
	@RequestMapping("/introsummary") // 개요 페이지
	public String summary(Model m) {
		m.addAttribute("center", "/intro/introduce");
		m.addAttribute("introcenter", "/intro/summary");	
		return "index";
	}
	
	@RequestMapping("/introintroev") // 환경적 측면 소개 페이지
	public String introev(Model m) {
		m.addAttribute("center", "/intro/introduce");
		m.addAttribute("introcenter", "/intro/introev");	
		return "index";
	}
	
	@RequestMapping("/introintroec") // 경제적 측면 소개 페이지
	public String introec(Model m) {
		m.addAttribute("center", "/intro/introduce");
		m.addAttribute("introcenter", "/intro/introec");	
		return "index";
	}
	
	@RequestMapping("/introintroindu") // 산업적 측면 소개 페이지
	public String introindu(Model m) {
		m.addAttribute("center", "/intro/introduce");
		m.addAttribute("introcenter", "/intro/introindu");	
		return "index";
	}
	
	@RequestMapping("/safetyinstructions") // 안전 수칙 페이지
	public String safetyinstructions(Model m) {
		m.addAttribute("center", "safetyinstructions");
		return "index";
	}
	
}