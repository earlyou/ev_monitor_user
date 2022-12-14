package com.user.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.frame.Biz;
import com.user.mapper.StationMapper;
import com.user.vo.FilterVO;
import com.user.vo.StationVO;

@Service
public class StationBiz implements Biz<String, StationVO>{

	@Autowired
	StationMapper sdao;

	@Override
	public void register(StationVO v) throws Exception {
		sdao.insert(v);
		
	}

	@Override
	public void modify(StationVO v) throws Exception {
		sdao.update(v);
		
	}

	@Override
	public void remove(String k) throws Exception {
		sdao.delete(k);
		
	}

	@Override
	public StationVO get(String k) throws Exception {
		return sdao.select(k);
	}

	@Override
	public List<StationVO> get() throws Exception {
		return sdao.selectall();
	}
	
	public List<StationVO> searchstat(String search) throws Exception{
		return sdao.searchstat(search);
	}

	public List<StationVO> selectfilter(FilterVO obj) throws Exception{
		return sdao.selectfilter(obj);
	}

	public StationVO selectoutput(String k) throws Exception {
		return sdao.selectoutput(k);
	}
	
}
