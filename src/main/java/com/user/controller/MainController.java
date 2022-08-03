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
import com.user.biz.UserAuthorityBiz;
import com.user.biz.UsersBiz;
import com.user.vo.BookMarkVO;
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
	
	@RequestMapping("/")
	public String main(Model m) {
		return "/main";
	}
	
	@RequestMapping("/map") // 충전소 찾기를 클릭 시, 지도 화면으로 이동
	public String map(Model m, HttpSession session) {
		m.addAttribute("left", "sideleftbar");
		m.addAttribute("center", "mapcenter");
		m.addAttribute("session", session.getAttribute("loginmember"));
		List<BookMarkVO> bookmark = null;
		try {
			UsersVO user = (UsersVO) session.getAttribute("loginmember");
			bookmark = bookmarkbiz.getbyuid(user.getId());
			m.addAttribute("bookmark", bookmark);
		} catch (Exception e) {
			
		}
		return "map";
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
	
	@RequestMapping("forgetcustpwd")
	public String forgetcustpwd(Model m) {
		m.addAttribute("center", "forgetcustpwd");
		return "main";
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
}