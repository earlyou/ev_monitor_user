package com.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.user.vo.CarModelVO;
import com.user.vo.ChargerVO;
import com.user.vo.ChartVO;
import com.user.vo.StationFareVO;
import com.user.vo.UsersVO;

@Repository
@Mapper
public interface MainMapper {
	public List<UsersVO> searchcustomer(String str) throws Exception;
	public List<UsersVO> searchadmin(String str) throws Exception;
	public List<CarModelVO> searchev(String str) throws Exception;
	public List<StationFareVO> searchfare(String str) throws Exception;
	public List<ChargerVO> searchcharger(String str) throws Exception;
	
	public int getcustomercnt() throws Exception;
	public int getadmincnt() throws Exception;
	public int getchargercnt() throws Exception;
	public int getcarmodelcnt() throws Exception;
	public int getcommunitycnt() throws Exception;
	public int getstationcnt() throws Exception;
	
	public List<ChartVO> getmemberschartdata() throws Exception;
	
}
