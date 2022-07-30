package com.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	
	@RequestMapping("/")
	public String main(Model m) {
		m.addAttribute("left", "left");
		m.addAttribute("center", "center");
		return "index";
	}
	
	@RequestMapping("/login")
	public String login(Model m) {
		m.addAttribute("center", "login");
		return "index";
	}

	@RequestMapping("/logout")
	public String logout(Model m, HttpSession session) {
		if (session != null) {
			session.invalidate();
		}
		return "index";
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

}