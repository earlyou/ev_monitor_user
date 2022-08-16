package com.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.user.vo.StationFareVO;

@Repository
@Mapper
public interface StationFareMapper {
	public void insert(StationFareVO stationfare) throws Exception;
	public void delete(int cid) throws Exception;
	public void update(StationFareVO stationfare) throws Exception;
	public StationFareVO select(int cid) throws Exception;
	public List <StationFareVO> selectall() throws Exception;
	
	public List <StationFareVO> piselect() throws Exception;
	public List <StationFareVO> meselect() throws Exception;
	public List <StationFareVO> evselect() throws Exception;
	public List <StationFareVO> stselect() throws Exception;
	public List <StationFareVO> cvselect() throws Exception;
	public List <StationFareVO> heselect() throws Exception;
	public List <StationFareVO> epselect() throws Exception;
	public List <StationFareVO> gnselect() throws Exception;
	
	public List <StationFareVO> hdselect() throws Exception;
	public List <StationFareVO> sfselect() throws Exception;
	public List <StationFareVO> keselect() throws Exception;
	public List <StationFareVO> gsselect() throws Exception;
	public List <StationFareVO> skselect() throws Exception;
	public List <StationFareVO> btselect() throws Exception;
	public List <StationFareVO> jeselect() throws Exception;
	public List <StationFareVO> kpselect() throws Exception;
	
	public List <StationFareVO> tdselect() throws Exception;
	public List <StationFareVO> obselect() throws Exception;
	public List <StationFareVO> klselect() throws Exception;
	public List <StationFareVO> lhselect() throws Exception;
	public List <StationFareVO> hmselect() throws Exception;
	public List <StationFareVO> ssselect() throws Exception;
	public List <StationFareVO> cuselect() throws Exception;
	public List <StationFareVO> plselect() throws Exception;
	
	public List <StationFareVO> ekselect() throws Exception;
	public List <StationFareVO> ecselect() throws Exception;
	
}
