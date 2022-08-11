package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.user.biz.StationBiz;
import com.user.vo.StationVO;

@RestController
public class AutocompleteController {
	
	@Autowired
	StationBiz sbiz;
	
	@RequestMapping("/autocomplete") // 커뮤니티 삭제
	public Object autocomplete(String searchValue, Model m) {
		
		Gson gson = new Gson();
		Object gsonlist = null;
  		try {
  			List<StationVO> list = sbiz.searchstat(searchValue);
  			gsonlist = gson.toJsonTree(list).toString();
  			
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
  		return gsonlist;
	}
}
