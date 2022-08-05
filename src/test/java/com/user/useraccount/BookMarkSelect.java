package com.user.useraccount;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.user.biz.BookMarkBiz;
import com.user.vo.BookMarkVO;

@SpringBootTest
class BookMarkSelect {
	
	@Autowired
	BookMarkBiz bbiz;
	
	@Test
	void contextLoads() {
		List<BookMarkVO> uid = null;
		
		try {
			uid = bbiz.getcustomerbookmark("id02");
			System.out.println(uid);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
