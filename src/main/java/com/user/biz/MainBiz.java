package com.user.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.mapper.MainMapper;
import com.user.vo.CarModelVO;
import com.user.vo.ChargerVO;
import com.user.vo.ChartVO;
import com.user.vo.StationFareVO;
import com.user.vo.UsersVO;

@Service
public class MainBiz {
	
	@Autowired
	MainMapper dao;
	
	public List<UsersVO> searchcustomer(String str) throws Exception {
		return dao.searchcustomer(str);	
	}
	
	public List<UsersVO> searchadmin(String str) throws Exception {
		return dao.searchadmin(str);	
	}
	
	public List<CarModelVO> searchev(String str) throws Exception {
		return dao.searchev(str);	
	}
	
	public List<StationFareVO> searchfare(String str) throws Exception {
		return dao.searchfare(str);	
	}
	
	public List<ChargerVO> searchcharger(String str) throws Exception {
		return dao.searchcharger(str);	
	}
	
	public int getCustomerCnt() throws Exception {
		return dao.getcustomercnt();
	}
	
	public int getAdminCnt() throws Exception {
		return dao.getadmincnt();
	}
	
	public int getChargerCnt() throws Exception {
		return dao.getchargercnt();
	}
	
	public int getCarModelCnt() throws Exception {
		return dao.getcarmodelcnt();
	}
	
	public int getCommunityCnt() throws Exception {
		return dao.getcommunitycnt();
	}
	
	public int getStationCnt() throws Exception {
		return dao.getstationcnt();
	}
	
	public List<ChartVO> getmemberschartdata() throws Exception {
		return dao.getmemberschartdata();		
	}	
}
