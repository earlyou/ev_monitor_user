package com.user.community;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.user.biz.CommunityBiz;
import com.user.vo.PageMakerVO;
import com.user.vo.SearchFilterVO;

@SpringBootTest
class PageMakerTest {
	
	@Autowired
	CommunityBiz biz;
	
	@Test
	void contextLoads() {
		SearchFilterVO v = new SearchFilterVO();
		PageMakerVO pageMake = null;
		
		int total = 0;
		try {
			total = biz.gettotal(v);
			pageMake = new PageMakerVO(v, total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(pageMake);
	}

}
