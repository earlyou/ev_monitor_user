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
	public List<CommunityVO> selectcustomercommdetail(String uid) throws Exception;

	public List <CommunityVO> selectalladminlist() throws Exception;
	public List <CommunityVO> selectalluserlist() throws Exception;
	
	public CommunityVO selectdetail(int pid) throws Exception;
	public List <CommunityVO> selectdetailother(String obj) throws Exception;
}
