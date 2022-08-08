package com.user.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.user.biz.BookMarkBiz;
import com.user.biz.CarModelBiz;
import com.user.biz.CommunityBiz;
import com.user.biz.StationBiz;
import com.user.biz.UsersBiz;
import com.user.vo.BookMarkVO;
import com.user.vo.CarModelVO;
import com.user.vo.CommunityVO;
import com.user.vo.UsersVO;

@Controller
public class MyPageController {
	
	@Autowired
	UsersBiz ubiz;
	
	@Autowired
	CarModelBiz cmbiz;
	
	@Autowired
	CommunityBiz combiz;
	
	@Autowired
	BookMarkBiz bookmarkbiz;
	
	@Autowired
	StationBiz stbiz;
	
	@RequestMapping("/mypagemyprofile")
	public String mypagemyprofile(Model m, String id) {
		List<UsersVO> list = null;
		try {
			list = ubiz.get();
			m.addAttribute("mlist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("center", "/mypage/myprofile");
		return "index";
	}
	
	@RequestMapping("/myprofiledetail")
	public String myprofiledetail(Model m, String id) {
		UsersVO obj = null;
//		List<UserAuthorityVO> list = null;
		List<CarModelVO> cmlist = null;
		try {
			obj = ubiz.get(id);
			m.addAttribute("members", obj);
			cmlist = cmbiz.get();
			m.addAttribute("cmlist", cmlist);
		} catch (Exception e) {	
			e.printStackTrace();
		}
		m.addAttribute("center", "/mypage/myprofiledetail");
		return "index";
	}
	
	@RequestMapping("/mypageupcheckpwd")
	public String upcheckpwd(Model m, String pwd, String id, HttpSession session) {
		m.addAttribute("center", "/mypage/upcheckpwd");
		return "index";
	}
	
	@RequestMapping("/mypagewdcheckpwd")
	public String wdcheckpwd(Model m, String pwd, String id, HttpSession session) {
		m.addAttribute("center", "/mypage/wdcheckpwd");
		return "index";
	}
	
	@RequestMapping("/update") // 프로필 업데이트
	public String update(Model m, UsersVO obj) {
		
		try {
			obj.setUsertypeid(100);
			ubiz.modify(obj);
		} catch (Exception e) {		
			e.printStackTrace();
		}
		
		return "redirect:/myprofiledetail?id="+obj.getId();
	}
	
	@RequestMapping("/mypagedelete") // 회원탈퇴
	public String delete(String id, Model m, HttpSession session) {
		try {
			ubiz.remove(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("/mypagemycomminity") // 내 커뮤니티
	public String mycomminity(Model m, Integer pid) {
		List<CommunityVO> comlist = null;
		try {
			comlist = combiz.get();
			m.addAttribute("comlist", comlist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("center", "/mypage/mycomminity");
		return "index";
	}

	@RequestMapping("/mypagemybookmark") // 내 즐겨찾기 목록
	public String mybookmark(Model m, String uid, HttpSession session) {
		
		List<BookMarkVO> bookmlist = null;
		m.addAttribute("session", session.getAttribute("loginmember"));
		
		try {
			UsersVO user = (UsersVO) session.getAttribute("loginmember");	
			bookmlist = bookmarkbiz.getcustomerbookmark(user.getId());
			m.addAttribute("bookmlist", bookmlist);	
			
//			System.out.println(bookmlist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("center", "/mypage/mybookmark");
		return "index";
	}
	


}
