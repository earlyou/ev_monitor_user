package com.user.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.frame.Biz;
import com.user.mapper.StationFareMapper;
import com.user.vo.StationFareVO;

@Service("stationfarebiz")
public class StationFareBiz implements Biz<Integer, StationFareVO> {

	@Autowired
	StationFareMapper sfdao;
	
	@Override
	public void register(StationFareVO v) throws Exception {
		sfdao.insert(v);
		
	}

	@Override
	public void modify(StationFareVO v) throws Exception {
		sfdao.update(v);
		
	}

	@Override
	public void remove(Integer k) throws Exception {
		sfdao.delete(k);
		
	}

	@Override
	public StationFareVO get(Integer k) throws Exception {
		return sfdao.select(k);
	}

	@Override
	public List<StationFareVO> get() throws Exception {
		return sfdao.selectall();
	}



}
