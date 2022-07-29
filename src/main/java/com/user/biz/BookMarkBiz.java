package com.user.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.frame.Biz;
import com.user.mapper.BookMarkMapper;
import com.user.vo.BookMarkVO;

@Service
public class BookMarkBiz implements Biz<Integer, BookMarkVO>{

	@Autowired
	BookMarkMapper bdao;
	
	@Override
	public void register(BookMarkVO v) throws Exception {
		bdao.insert(v);
	}

	@Override
	public void modify(BookMarkVO v) throws Exception {
		bdao.update(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		bdao.delete(k);
	}

	@Override
	public BookMarkVO get(Integer k) throws Exception {
		return bdao.select(k);
	}

	@Override
	public List<BookMarkVO> get() throws Exception {
		return bdao.selectall();
	}
	
	public List<BookMarkVO> getcustomerbookmark() throws Exception {
		return bdao.selectcustomerbookmark();
	}

}
