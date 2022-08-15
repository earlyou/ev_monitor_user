package com.user.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.user.biz.CommunityBiz;
import com.user.biz.UsersBiz;
import com.user.frame.Util;
import com.user.vo.CommunityVO;
import com.user.vo.PageMakerVO;
import com.user.vo.SearchFilterVO;
import com.user.vo.UsersVO;

@Controller
public class CommunityController {
	
	@Value("${admindir}")
	String admindir;
	
	@Value("${userdir}")
	String userdir;
	
	@Autowired
	CommunityBiz cbiz;

	@Autowired
	UsersBiz ubiz;
	
	
	@RequestMapping("/cm")
	public String cm(Model m, SearchFilterVO v) {
		List<CommunityVO> list = null;
		List<CommunityVO> list2 = null;
		PageMakerVO pageMake = null;
		int total = 0;
		try {
			list = cbiz.getalladminlist();
			list2 = cbiz.selectfilteruserlist(v);
			
			total = cbiz.gettotal();
			pageMake = new PageMakerVO(v, total);
			System.out.println(pageMake);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center", "cm/center");
		m.addAttribute("communityadminlist", list);
		m.addAttribute("communityuserlist", list2);
		m.addAttribute("pageMaker", pageMake);
		return "index";
	}
	
	@RequestMapping("/cm_details")
	public String cm_details(Model m, int pid, String statid) {
		CommunityVO com_detail;
		List<CommunityVO> other_list = null;
		try {
			com_detail = cbiz.selectdetail(pid);
			other_list = cbiz.selectdetailother(statid);
			m.addAttribute("communitydetail", com_detail);
			m.addAttribute("otherlist", other_list);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		m.addAttribute("center", "cm/cm_details");
		
		return "index";
	}
	

	@RequestMapping("/cmadd")
	public String add(Model m, HttpSession session) {
		if(session.getAttribute("loginmember") == null) {
			return "redirect:/login";
		}else {
			m.addAttribute("center", "cm/add");
			return "index";
		}	
	}
	
	@RequestMapping("/addimpl")
	public String addimpl(CommunityVO obj, Model m) {
		
		String cimgname = obj.getMf().getOriginalFilename();
		System.out.println(cimgname);
		try {
			obj.setCimgname(cimgname);
			cbiz.register(obj);
			Util.saveFile(obj.getMf(), admindir, userdir);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
				
		return "redirect:/cm";
	}
	

	@RequestMapping("/cmupdate")
	public String update(Model m, int pid, HttpSession session) {
		Object ss = session.getAttribute("loginmember");
		if(ss != null) {			
			try {
				CommunityVO c = cbiz.selectdetail(pid);
				String uid = c.getUid();
				UsersVO users = ubiz.get(uid);
				if(ss.toString().equals(users.toString())) {					
					m.addAttribute("dpost", c);
					m.addAttribute("center", "cm/update");
				}				
			} catch (Exception e) {
				System.out.println("오류");
				e.printStackTrace();
			}			
		}else {
			//return "redirect:/login";
			m.addAttribute("center", "/login");
		}
		return "index";
		
	}
	
	@RequestMapping("/updateimpl")
	public String updateimpl(CommunityVO obj) {	
			String iname = obj.getMf().getOriginalFilename();
			
			if(!(iname.equals(""))) {
				obj.setCimgname(iname);
				Util.saveFile(obj.getMf(), admindir, userdir);
			}else {
				try {
					CommunityVO newvo = cbiz.selectdetail(obj.getPid());
					obj.setCimgname(newvo.getCimgname());  
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			try {
				cbiz.modify(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "redirect:cm_details?pid=" + obj.getPid() + "&statid=" +obj.getStatid();
	}
	
	@RequestMapping("/cmdelete")
	public String delete(Model m, int pid, HttpSession session) {
		Object ss = session.getAttribute("loginmember");
		if(ss != null) {
			CommunityVO cv;
			try {
				cv = cbiz.get(pid);
				String uid = cv.getUid();
				UsersVO users = ubiz.get(uid);
				if(users.toString().equals(ss.toString())) {
					cbiz.remove(pid);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
				return "redirect:/cm";
		}else {
			m.addAttribute("center", "/login");
			return "index";
		}					
	}

}