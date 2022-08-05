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
		try {
			list = cbiz.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center", "cm/center");
		m.addAttribute("communitylist", list);
		return "cm/index";
	}
	

	@RequestMapping("/cmadd")
	public String add(Model m, HttpSession session) {
		if(session.getAttribute("loginmember") == null) {
			return "redirect:/login";
		}else {
			m.addAttribute("center", "cm/add");
			return "cm/index";
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
	

	@RequestMapping("cm/update")
	public String update(Model m, int id, HttpSession session) {
		Object ss = session.getAttribute("logincust");
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
	
	@RequestMapping("cm/delete")
	public String delete(Model m, int pid, HttpSession session) {
		Object ss = session.getAttribute("logincust");
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