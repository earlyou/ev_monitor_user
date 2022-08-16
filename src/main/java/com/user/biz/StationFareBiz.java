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

	
	/*요금 안내 페이지 부분*/
	public List<StationFareVO> piget() throws Exception {
		return sfdao.piselect();
	}
	
	public List<StationFareVO> meget() throws Exception {
		return sfdao.meselect();
	}

	public List<StationFareVO> evget() throws Exception {
		return sfdao.evselect();
	}
	
	public List<StationFareVO> stget() throws Exception {
		return sfdao.stselect();
	}	
	
	public List<StationFareVO> cvget() throws Exception {
		return sfdao.cvselect();
	}
	
	public List<StationFareVO> heget() throws Exception {
		return sfdao.heselect();
	}
	
	public List<StationFareVO> epget() throws Exception {
		return sfdao.epselect();
	}
	
	public List<StationFareVO> gnget() throws Exception {
		return sfdao.gnselect();
	}
	
	public List<StationFareVO> hdget() throws Exception {
		return sfdao.hdselect();
	}
	
	public List<StationFareVO> sfget() throws Exception {
		return sfdao.sfselect();
	}
	
	public List<StationFareVO> keget() throws Exception {
		return sfdao.keselect();
	}
	
	public List<StationFareVO> gsget() throws Exception {
		return sfdao.gsselect();
	}
	
	public List<StationFareVO> skget() throws Exception {
		return sfdao.skselect();
	}
	
	public List<StationFareVO> btget() throws Exception {
		return sfdao.btselect();
	}
	
	public List<StationFareVO> jeget() throws Exception {
		return sfdao.jeselect();
	}
	
	public List<StationFareVO> kpget() throws Exception {
		return sfdao.kpselect();
	}
	
	public List<StationFareVO> tdget() throws Exception {
		return sfdao.tdselect();
	}
	
	public List<StationFareVO> obget() throws Exception {
		return sfdao.obselect();
	}
	
	public List<StationFareVO> klget() throws Exception {
		return sfdao.klselect();
	}
	
	public List<StationFareVO> lhget() throws Exception {
		return sfdao.lhselect();
	}
	
	public List<StationFareVO> hmget() throws Exception {
		return sfdao.hmselect();
	}
	
	public List<StationFareVO> ssget() throws Exception {
		return sfdao.ssselect();
	}
	
	public List<StationFareVO> cuget() throws Exception {
		return sfdao.cuselect();
	}
	
	public List<StationFareVO> plget() throws Exception {
		return sfdao.plselect();
	}
	
	public List<StationFareVO> ekget() throws Exception {
		return sfdao.ekselect();
	}
	
	public List<StationFareVO> ecget() throws Exception {
		return sfdao.ecselect();
	}
}
