package com.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.user.vo.SSEVO;

@Repository
@Mapper
public interface SSEMapper{
	public void insert(SSEVO obj) throws Exception;
	public void delete(String obj) throws Exception;
	public void update(SSEVO obj) throws Exception;
	public SSEVO select(String obj) throws Exception;
	public List<SSEVO> selectall() throws Exception;
}
