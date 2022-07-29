package com.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.user.vo.CarModelVO;

@Repository
@Mapper
public interface CarModelMapper {
	public void insert(CarModelVO o) throws Exception;
	public void delete(int id) throws Exception;
	public void update(CarModelVO o) throws Exception;
	public CarModelVO select(int id) throws Exception;
	public List<CarModelVO> selectall() throws Exception;
}
