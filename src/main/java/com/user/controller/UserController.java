package com.user.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	public String login(Model m, String msg, HttpServletResponse response, HttpServletRequest request) {
		
		// 쿠키에 이전페이지 링크 저장
		String referer = request.getHeader("Referer");
		Cookie myCookie = new Cookie("referer", referer);
	    myCookie.setMaxAge(60*10);
	    myCookie.setPath("/evcsmonitor"); // 모든 경로에서 접근 가능 하도록 설정		
		
	    response.addCookie(myCookie);
	    
	    m.addAttribute("center", "/login");
		return "index";
	}
	
	@RequestMapping("loginimpl") // 로그인 인증 절차
	public String loginimpl(Model m, String id, String pwd, HttpSession session, HttpServletResponse response, HttpServletRequest request) {
		
		UsersVO customer = null;
		try {
			customer = ubiz.get(id);
			if(customer == null) {
				throw new Exception();
			}			
			if(customer.getPwd().equals(pwd) && customer.getUsertypeid()==100) {
				session.setAttribute("loginmember", customer);
				m.addAttribute("center", "homecenter");

				// 쿠키에 저장된 이전 페이지 불러오기
				String myreferer = null;
				Cookie[] myCookies = request.getCookies();

				for (Cookie c : myCookies) {
					System.out.println(c.getName());
					System.out.println(c.getValue());
					if (c.getName().equals("referer")) {
						myreferer = c.getValue();
						break;
					}
				}

				// 쿠키 삭제
				Cookie myCookie = new Cookie("referer", null); // 쿠키 값을 null로 설정
				myCookie.setMaxAge(0); // 남은 만료시간을 0으로 설정
				response.addCookie(myCookie);

				System.out.println("로그인성공");
				return "redirect:" + myreferer;
			}else if(customer.getPwd().equals(pwd) && customer.getUsertypeid()!=100){	
				m.addAttribute("msg","사용자 계정이 아닙니다.");
				m.addAttribute("center","login");		
				return "index";	
			}else{
				m.addAttribute("msg"," 아이디 또는 비밀번호를 잘못 입력했습니다. ");
				m.addAttribute("center","login");		
				return "index";							
			}		
		} catch (Exception e) {
		
			m.addAttribute("msg"," 아이디 또는 비밀번호를 잘못 입력했습니다. ");
			m.addAttribute("center","login");
			return "index";
		}
	}
	
	@RequestMapping("/logout")  // 로그아웃
	public String logout(Model m, HttpSession session) {
		if(session != null) {
			session.invalidate();
		}
		m.addAttribute("center","homecenter");
		return "index";
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
		return "register";
	}
	
	@RequestMapping("tos") // 이용약관
	public String tos(Model m) {
		m.addAttribute("center", "tos");
		return "index";
	}
	
	@RequestMapping("/registerimpl") // 회원등록 인증 절차
	public String registerimpl(Model m, UsersVO obj) {
		
		try {
			obj.setUsertypeid(100);
			ubiz.register(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}
	

}
