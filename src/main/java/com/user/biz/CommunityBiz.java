package com.user.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.frame.Biz;
import com.user.mapper.CommunityMapper;
import com.user.vo.CommunityVO;

@Service("communitybiz")
public class CommunityBiz implements Biz<Integer, CommunityVO> {
	@Autowired
	CommunityMapper commudao;
	
	@Override
	public void register(CommunityVO v) throws Exception {
		commudao.insert(v);
		
	}

	@Override
	public void modify(CommunityVO v) throws Exception {
		commudao.update(v);
		
	}

	@Override
	public void remove(Integer k) throws Exception {
		commudao.delete(k);
		
	}

	@Override
	public CommunityVO get(Integer k) throws Exception {
		return commudao.select(k);
	}

	@Override
	public List<CommunityVO> get() throws Exception {
		return commudao.selectall();
	}


	
}