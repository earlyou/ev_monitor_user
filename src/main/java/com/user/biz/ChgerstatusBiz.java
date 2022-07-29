package com.user.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.frame.Biz;
import com.user.mapper.ChgerstatusMapper;
import com.user.vo.ChgerstatusVO;

@Service
public class ChgerstatusBiz implements Biz<Integer, ChgerstatusVO>{

	@Autowired
	ChgerstatusMapper ctdao;

	@Override
	public void register(ChgerstatusVO v) throws Exception {
		ctdao.insert(v);
	}

	@Override
	public void modify(ChgerstatusVO v) throws Exception {
		ctdao.update(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		ctdao.delete(k);
	}

	@Override
	public ChgerstatusVO get(Integer k) throws Exception {
		return ctdao.select(k);
	}

	@Override
	public List<ChgerstatusVO> get() throws Exception {
		return ctdao.selectall();
	}

	

}
