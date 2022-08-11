package com.user.controller;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.gson.Gson;
import com.user.biz.StationBiz;
import com.user.vo.StationVO;
 
@SpringBootTest
public class AutoCompleteControllerTest 
{
	
       
	@Autowired
	StationBiz sbiz;
 
      @Test
      public void autocomplete() throws Exception{
		Gson gson = new Gson();
		Object gsonlist = null;
  		try {
  			List<StationVO> list = sbiz.searchstat("강남대로");
  			gsonlist = gson.toJsonTree(list).toString();
  			
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
  		System.out.println(gsonlist); 
	
      }
}