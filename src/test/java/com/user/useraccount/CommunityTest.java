package com.user.useraccount;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.user.biz.CommunityBiz;
import com.user.vo.CommunityVO;

@SpringBootTest
class CommunityTest {
	
	@Autowired
	CommunityBiz biz;
	
	@Test
	void contextLoads() {
		//CommunityVO com = new CommunityVO(4, "test2", "test2", "test2", "test2", "test2");
		CommunityVO com = null;
		List<CommunityVO> list = null;
		try {
			//biz.register(com);
			//biz.modify(com);
			//biz.remove(4);
			//com = biz.get(3);
			list = biz.getalladminlist();
			for (CommunityVO c : list) {
				System.out.println(c);
			}
			
			//System.out.println(com);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
