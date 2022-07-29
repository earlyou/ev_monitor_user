package com.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.user.vo.UserAuthorityVO;

@Repository
@Mapper
public interface UserAuthorityMapper {
	public void insert(UserAuthorityVO obj) throws Exception;
	public void delete(int obj) throws Exception;
	public void update(UserAuthorityVO obj) throws Exception;
	public UserAuthorityVO select(int obj) throws Exception;
	public List<UserAuthorityVO> selectall() throws Exception;
}
