package com.user.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.user.biz.CarModelBiz;
import com.user.biz.CommunityBiz;
import com.user.biz.UsersBiz;
import com.user.vo.CarModelVO;
import com.user.vo.UsersVO;

@Controller
public class UserController {
	
	@Autowired
	UsersBiz ubiz;
	
	@Autowired
	CarModelBiz cmbiz;
	
	@Autowired
	CommunityBiz combiz;
	
	@RequestMapping("/login") // 로그인 화면
	public String login(Model m, String msg) {
		m.addAttribute("center", "/login");
		return "/main";
	}
	
	@RequestMapping("loginimpl") // 로그인 인증 절차
	public String loginimpl(Model m, String id, String pwd, HttpSession session) {

		UsersVO customer = null;
		try {
			customer = ubiz.get(id);
			if(customer == null) {
				throw new Exception();
			}			
			if(customer.getPwd().equals(pwd) && customer.getUsertypeid()==100) {
				session.setAttribute("loginmember", customer);
				m.addAttribute("center","center");	
				return "main";
			}else if(customer.getPwd().equals(pwd) && customer.getUsertypeid()!=100){	
				m.addAttribute("msg","사용자 계정이 아닙니다.");
				m.addAttribute("center","login");		
				return "main";	
			}else{
				m.addAttribute("msg"," 아이디 또는 비밀번호를 잘못 입력했습니다. ");
				m.addAttribute("center","login");		
				return "main";							
			}		
		} catch (Exception e) {
		
			m.addAttribute("msg"," 아이디 또는 비밀번호를 잘못 입력했습니다. ");
			m.addAttribute("center","login");		
			return "main";
		}
	}
	
	@RequestMapping("/logout")  // 로그아웃
	public String logout(Model m, HttpSession session) {
		if(session != null) {
			session.invalidate();
		}
		return "/main";
	}
	
	@RequestMapping("/register") // 회원등록
	public String register(Model m) {
		
		List<CarModelVO> cmlist = null;
		
		try {
			cmlist = cmbiz.get();
			m.addAttribute("cmlist", cmlist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("center", "/register");
		return "/main";
	}
	
	@RequestMapping("tos") // 이용약관
	public String tos(Model m) {
		m.addAttribute("center", "tos");
		return "main";
	}
	
	@RequestMapping("addimpl") // 회원등록 인증 절차
	public String addimpl(Model m, UsersVO obj) {
		
		try {
			obj.setUsertypeid(100);
			ubiz.register(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/main";
	}
	

}
