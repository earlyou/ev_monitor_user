package com.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.user.vo.UsersVO;

@Repository
@Mapper
public interface UsersMapper {
	public void insert(UsersVO obj) throws Exception;
	public void delete(String obj) throws Exception;
	public void update(UsersVO obj) throws Exception;
	public UsersVO select(String obj) throws Exception;
	public UsersVO clpselect(String obj) throws Exception;
	public List<UsersVO> selectall() throws Exception;
	public int selectcustomer() throws Exception;
	public int selectadmin() throws Exception;
	public List<UsersVO> selectcustomerauthoritydetail() throws Exception;
	public String selectchid(String name, String email) throws Exception;
	public String selectchpwd(String name, String id, String email) throws Exception;
	public String selectchpwdvalue(String name, String id, String email) throws Exception;	
}


