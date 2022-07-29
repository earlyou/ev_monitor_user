package com.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.user.vo.StationVO;

@Repository
@Mapper
public interface StationMapper {
	public void insert(StationVO obj) throws Exception;
	public void delete(int obj) throws Exception;
	public void update(StationVO obj) throws Exception;
	public StationVO select(int obj) throws Exception;
	public List<StationVO> selectall() throws Exception;

}
