package com.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.user.vo.CommunityVO;

@Repository
@Mapper

public interface CommunityMapper {
	public void insert(CommunityVO commu) throws Exception;
	public void delete(int pid) throws Exception;
	public void update(CommunityVO commu) throws Exception;
	public CommunityVO select(int pid) throws Exception;
	public List <CommunityVO> selectall() throws Exception;

}
