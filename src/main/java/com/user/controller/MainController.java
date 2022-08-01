package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.user.biz.MailBiz;
import com.user.biz.UserAuthorityBiz;
import com.user.biz.UsersBiz;

@Controller
public class MainController {

	@Autowired
	UserAuthorityBiz uabiz;
	
	@Autowired
	UsersBiz ubiz;
	
	@Autowired
	MailBiz mailbiz;
	
	@RequestMapping("/")
	public String main(Model m) {
		return "/main";
	}
	
	@RequestMapping("/map") // 충전소 찾기를 클릭 시, 지도 화면으로 이동
	public String map(Model m) {
		m.addAttribute("sideleftbar", "sideleftbar");
		m.addAttribute("mapcenter", "mapcenter");
		return "/map";
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
		return "main";
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
}