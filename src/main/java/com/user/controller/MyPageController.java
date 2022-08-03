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
import com.user.vo.StationVO;
import com.user.vo.UserAuthorityVO;
import com.user.vo.UsersVO;

@Controller
@RequestMapping("/mypage")
public class MyPageController {
	
	@Autowired
	UsersBiz ubiz;
	
	@Autowired
	CarModelBiz cmbiz;
	
	@Autowired
	CommunityBiz combiz;
	
	@Autowired
	BookMarkBiz bmbiz;
	
	@Autowired
	StationBiz stbiz;
	
	@RequestMapping("myprofile")
	public String select(Model m, String id) {
		List<UsersVO> list = null;
		try {
			list = ubiz.get();
			m.addAttribute("mlist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("center", "/mypage/myprofile");
		return "/main";
	}
	
	@RequestMapping("myprofiledetail")
	public String myprofiledetail(Model m, String id) {
		UsersVO obj = null;
		List<UserAuthorityVO> list = null;
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
		return "/main";
	}
	
	@RequestMapping("/upcheckpwd")
	public String upcheckpwd(Model m, String pwd, String id, HttpSession session) {
		m.addAttribute("center", "/mypage/upcheckpwd");
		return "/main";
	}
	
	@RequestMapping("/wdcheckpwd")
	public String wdcheckpwd(Model m, String pwd, String id, HttpSession session) {
		m.addAttribute("center", "/mypage/wdcheckpwd");
		return "main";
	}
	
	@RequestMapping("/update")
	public String update(Model m, UsersVO obj) {
		
		try {
			obj.setUsertypeid(100);
			ubiz.modify(obj);
		} catch (Exception e) {		
			e.printStackTrace();
		}
		
		return "redirect:/mypage/myprofiledetail?id="+obj.getId();
	}
	
	@RequestMapping("/delete")
	public String delete(String id, Model m, HttpSession session) {
		try {
			ubiz.remove(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("mycomminity") // 내 커뮤니티
	public String mycomminity(Model m, Integer pid) {
		List<CommunityVO> comlist = null;
		try {
			comlist = combiz.get();
			m.addAttribute("comlist", comlist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("center", "/mypage/mycomminity");
		return "main";
	}

	@RequestMapping("mybookmark") // 내 즐겨찾기
	public String mybookmark(Model m, Integer bsid) {
		List<BookMarkVO> bookmlist = null;
		List<StationVO> stmlist = null;
		try {
			bookmlist = bmbiz.get();
			m.addAttribute("bookmlist", bookmlist);
			stmlist = stbiz.get();
			m.addAttribute("stmlist", stmlist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("center", "/mypage/mybookmark");
		return "main";
	}
}
