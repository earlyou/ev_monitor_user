package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.user.biz.UsersBiz;
import com.user.vo.UsersVO;

@Controller
public class MyPageController {
	
	@Autowired
	UsersBiz ubiz;
	
	@RequestMapping("/myprofile")
	public String select(Model m, String id) {
		List<UsersVO> list = null;
		try {
			list = ubiz.get();
			m.addAttribute("mlist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("left", "left");
		m.addAttribute("center", "/myprofile");
		return "/index";
	}

}
