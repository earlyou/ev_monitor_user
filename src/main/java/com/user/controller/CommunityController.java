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
import com.user.vo.CommunityVO;
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
	public String cm(Model m) {
		
		List<CommunityVO> list = null;
		List<CommunityVO> list2 = null;
		try {
			list = cbiz.getalladminlist();
			list2 = cbiz.getalluserlist();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center", "cm/center");
		m.addAttribute("communityadminlist", list);
		m.addAttribute("communityuserlist", list2);
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
	
	@RequestMapping("cm/addimpl")
	public String addimpl(CommunityVO obj, Model m) {
		
					try {
						cbiz.register(obj);
					} catch (Exception e) {
						
						e.printStackTrace();
					}
				
		return "redirect:/cm";
	}
	

	@RequestMapping("/cmupdate")
	public String update(Model m, int id, HttpSession session) {
		Object ss = session.getAttribute("loginmember");
		if(ss != null) {			
			try {
				CommunityVO c = cbiz.get(id);
				String uid = c.getUid();
				UsersVO users = ubiz.get(uid);
				if(ss.toString().equals(users.toString())) {					
					m.addAttribute("dpost", c);
					m.addAttribute("center", "cm/update");
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		return "index";
	}
	
	@RequestMapping("cm/updateimpl")
	public String updateimpl(int pid, CommunityVO obj) {	
					try {
						cbiz.modify(obj);
					} catch (Exception e) {
					e.printStackTrace();
					}
				
			return "redirect:detail?pid=" + obj.getPid();
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