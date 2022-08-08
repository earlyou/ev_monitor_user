package com.user.useraccount;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.user.biz.UsersBiz;
import com.user.vo.UsersVO;

@SpringBootTest
class UsersCarSelectTest {
	
	@Autowired
	UsersBiz ubiz;
	
	@Test
	void contextLoads() {
		UsersVO users = null;
		
		try {
			users = ubiz.clpget("35마 6548");
			System.out.println(users);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
