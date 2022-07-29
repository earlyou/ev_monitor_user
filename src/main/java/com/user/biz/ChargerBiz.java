package com.user.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.frame.Biz;
import com.user.mapper.ChargerMapper;
import com.user.vo.ChargerVO;

@Service("chargerbiz")
public class ChargerBiz implements Biz<Integer, ChargerVO> {

	@Autowired
	ChargerMapper dao;
	
	@Override
	public void register(ChargerVO v) throws Exception {
		dao.insert(v);
	}

	@Override
	public void modify(ChargerVO v) throws Exception {
		dao.update(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		dao.delete(k);
	}

	@Override
	public ChargerVO get(Integer k) throws Exception {
		return dao.select(k);
	}

	@Override
	public List<ChargerVO> get() throws Exception {
		return dao.selectall();
	}

}
