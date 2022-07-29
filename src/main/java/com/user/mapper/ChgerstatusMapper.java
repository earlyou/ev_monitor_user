package com.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.user.vo.ChgerstatusVO;

@Repository
@Mapper
public interface ChgerstatusMapper {
	public void insert(ChgerstatusVO obj) throws Exception;
	public void delete(int obj) throws Exception;
	public void update(ChgerstatusVO obj) throws Exception;
	public ChgerstatusVO select(int obj) throws Exception;
	public List<ChgerstatusVO> selectall() throws Exception;

}
