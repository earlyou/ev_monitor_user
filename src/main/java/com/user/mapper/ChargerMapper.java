package com.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.user.vo.ChargerVO;

@Repository
@Mapper
public interface ChargerMapper {
	public void insert(ChargerVO o) throws Exception;
	public void delete(int id) throws Exception;
	public void update(ChargerVO o) throws Exception;
	public ChargerVO select(int id) throws Exception;
	public List<ChargerVO> selectall() throws Exception;
}
