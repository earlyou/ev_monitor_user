package com.user.useraccount;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.user.biz.UsersBiz;

@SpringBootTest
class findidtest {
	
	@Autowired
	UsersBiz ubiz;
	
	@Test
	void contextLoads() {
		String findid = null;
		
		try {
			findid = ubiz.getselectchid("이순신","lss@gmail.com");
			System.out.println(findid);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
