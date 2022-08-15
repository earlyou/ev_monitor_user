package com.user.community;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.user.biz.CommunityBiz;
import com.user.vo.CommunityVO;
import com.user.vo.SearchFilterVO;

@SpringBootTest
class PagingTest {
	
	@Autowired
	CommunityBiz biz;
	
	@Test
	void contextLoads() {
		SearchFilterVO v = new SearchFilterVO();
		
		List<CommunityVO> list = null;
		try {
			list = biz.selectfilteruserlist(v);
			for (CommunityVO c : list) {
				System.out.println(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
