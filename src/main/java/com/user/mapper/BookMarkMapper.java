package com.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.user.vo.BookMarkVO;

@Repository
@Mapper
public interface BookMarkMapper {
	
	public void insert(BookMarkVO obj) throws Exception;
	public void delete(int obj) throws Exception;
	public void update(BookMarkVO obj) throws Exception;
	public BookMarkVO select(int obj) throws Exception;
	public List<BookMarkVO> selectall() throws Exception;
	public List<BookMarkVO> selectcustomerbookmark() throws Exception;

}
