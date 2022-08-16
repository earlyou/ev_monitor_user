package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.user.biz.StationFareBiz;
import com.user.vo.StationFareVO;

@Controller
public class ChargingFareController {
	
	@Autowired
	StationFareBiz sfbiz;

	@RequestMapping("/cfichargingfareinfo") // 충전 요금 안내 페이지
	public String chargingfareinfo(Model m) {
		List<StationFareVO> list = null;
		
		try {
			list = sfbiz.meget();
			m.addAttribute("melist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("center", "/cfi/chargingfareinfo");
		m.addAttribute("chargingfarecenter", "/cfi/chargingfarecenter");
		
		return "index";
	}
	
	@RequestMapping("/meinfo") // (환경부)충전 요금 안내 페이지
	public String meinfo(Model m) {
		List<StationFareVO> list = null;

		try {
			list = sfbiz.meget();
			m.addAttribute("melist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		m.addAttribute("center", "/cfi/chargingfareinfo");
		m.addAttribute("chargingfarecenter", "/cfi/meinfo");
		return "index";
	}
	
	@RequestMapping("/evinfo") // (에버온)충전 요금 안내 페이지
	public String evinfo(Model m) {
		
		List<StationFareVO> list = null;
		
		try {
			list = sfbiz.evget();
			m.addAttribute("evlist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		m.addAttribute("center", "/cfi/chargingfareinfo");
		m.addAttribute("chargingfarecenter", "/cfi/evinfo");
		return "index";
	}	
	
	@RequestMapping("/piinfo") // (차지비)충전 요금 안내 페이지
	public String piinfo(Model m) {
		List<StationFareVO> list = null;

		try {
			list = sfbiz.piget();
			m.addAttribute("pilist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center", "/cfi/chargingfareinfo");
		m.addAttribute("chargingfarecenter", "/cfi/piinfo");
		return "index";
	}	
	
	@RequestMapping("/stinfo") // (에스트래픽)충전 요금 안내 페이지
	public String stinfo(Model m) {
		List<StationFareVO> list = null;

		try {
			list = sfbiz.stget();
			m.addAttribute("stlist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center", "/cfi/chargingfareinfo");
		m.addAttribute("chargingfarecenter", "/cfi/stinfo");
		return "index";
	}
	
	@RequestMapping("/cvinfo") // (대영채비)충전 요금 안내 페이지
	public String cvinfo(Model m) {
		List<StationFareVO> list = null;

		try {
			list = sfbiz.cvget();
			m.addAttribute("cvlist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center", "/cfi/chargingfareinfo");
		m.addAttribute("chargingfarecenter", "/cfi/cvinfo");
		return "index";
	}
	
	@RequestMapping("/heinfo") // (해피차져)충전 요금 안내 페이지
	public String heinfo(Model m) {
		List<StationFareVO> list = null;

		try {
			list = sfbiz.heget();
			m.addAttribute("helist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center", "/cfi/chargingfareinfo");
		m.addAttribute("chargingfarecenter", "/cfi/heinfo");
		return "index";
	}
	
	@RequestMapping("/epinfo") // (이카플러그)충전 요금 안내 페이지
	public String epinfo(Model m) {
		List<StationFareVO> list = null;

		try {
			list = sfbiz.epget();
			m.addAttribute("eplist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center", "/cfi/chargingfareinfo");
		m.addAttribute("chargingfarecenter", "/cfi/epinfo");
		return "index";
	}	
	
	
	@RequestMapping("/gninfo") // (지커넥트)충전 요금 안내 페이지
	public String gninfo(Model m) {
		List<StationFareVO> list = null;

		try {
			list = sfbiz.gnget();
			m.addAttribute("gnlist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center", "/cfi/chargingfareinfo");
		m.addAttribute("chargingfarecenter", "/cfi/gninfo");
		return "index";
	}
	
	@RequestMapping("/hdinfo") // (현대자동차)충전 요금 안내 페이지
	public String hdinfo(Model m) {
		List<StationFareVO> list = null;

		try {
			list = sfbiz.hdget();
			m.addAttribute("hdlist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center", "/cfi/chargingfareinfo");
		m.addAttribute("chargingfarecenter", "/cfi/hdinfo");
		return "index";
	}
	
	@RequestMapping("/sfinfo") // (스타코프)충전 요금 안내 페이지
	public String sfinfo(Model m) {
		List<StationFareVO> list = null;

		try {
			list = sfbiz.sfget();
			m.addAttribute("sflist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center", "/cfi/chargingfareinfo");
		m.addAttribute("chargingfarecenter", "/cfi/sfinfo");
		return "index";
	}
	
	@RequestMapping("/keinfo") // (한국전기차인프라기술)충전 요금 안내 페이지
	public String keinfo(Model m) {
		List<StationFareVO> list = null;

		try {
			list = sfbiz.keget();
			m.addAttribute("kelist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center", "/cfi/chargingfareinfo");
		m.addAttribute("chargingfarecenter", "/cfi/keinfo");
		return "index";
	}
	
	@RequestMapping("/gsinfo") // (GS칼텍스)충전 요금 안내 페이지
	public String gsinfo(Model m) {
		List<StationFareVO> list = null;

		try {
			list = sfbiz.gsget();
			m.addAttribute("gslist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center", "/cfi/chargingfareinfo");
		m.addAttribute("chargingfarecenter", "/cfi/gsinfo");
		return "index";
	}
	
	@RequestMapping("/skinfo") // (SK에너지)충전 요금 안내 페이지
	public String skinfo(Model m) {
		List<StationFareVO> list = null;

		try {
			list = sfbiz.skget();
			m.addAttribute("sklist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center", "/cfi/chargingfareinfo");
		m.addAttribute("chargingfarecenter", "/cfi/skinfo");
		return "index";
	}
	
	@RequestMapping("/btinfo") // (보타리 에너지)충전 요금 안내 페이지
	public String btinfo(Model m) {
		List<StationFareVO> list = null;

		try {
			list = sfbiz.btget();
			m.addAttribute("btlist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center", "/cfi/chargingfareinfo");
		m.addAttribute("chargingfarecenter", "/cfi/btinfo");
		return "index";
	}
	
	@RequestMapping("/jeinfo") // (제주전기자동차서비스)충전 요금 안내 페이지
	public String jeinfo(Model m) {
		List<StationFareVO> list = null;

		try {
			list = sfbiz.jeget();
			m.addAttribute("jelist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center", "/cfi/chargingfareinfo");
		m.addAttribute("chargingfarecenter", "/cfi/jeinfo");
		return "index";
	}
	
	@RequestMapping("/kpinfo") // (한국전력)충전 요금 안내 페이지
	public String kpinfo(Model m) {
		List<StationFareVO> list = null;

		try {
			list = sfbiz.kpget();
			m.addAttribute("kplist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center", "/cfi/chargingfareinfo");
		m.addAttribute("chargingfarecenter", "/cfi/kpinfo");
		return "index";
	}
	
	@RequestMapping("/tdinfo") // (타디스테크놀로지)충전 요금 안내 페이지
	public String tdinfo(Model m) {
		List<StationFareVO> list = null;

		try {
			list = sfbiz.tdget();
			m.addAttribute("tdlist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center", "/cfi/chargingfareinfo");
		m.addAttribute("chargingfarecenter", "/cfi/tdinfo");
		return "index";
	}
	
	@RequestMapping("/obinfo") // (현대오일뱅크)충전 요금 안내 페이지
	public String obinfo(Model m) {
		List<StationFareVO> list = null;

		try {
			list = sfbiz.obget();
			m.addAttribute("oblist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center", "/cfi/chargingfareinfo");
		m.addAttribute("chargingfarecenter", "/cfi/obinfo");
		return "index";
	}
	
	@RequestMapping("/klinfo") // (클린일렉스)충전 요금 안내 페이지
	public String klinfo(Model m) {
		List<StationFareVO> list = null;

		try {
			list = sfbiz.klget();
			m.addAttribute("kllist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center", "/cfi/chargingfareinfo");
		m.addAttribute("chargingfarecenter", "/cfi/klinfo");
		return "index";
	}
	
	@RequestMapping("/lhinfo") // (LG헬로비전)충전 요금 안내 페이지
	public String lhinfo(Model m) {
		List<StationFareVO> list = null;

		try {
			list = sfbiz.lhget();
			m.addAttribute("lhlist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center", "/cfi/chargingfareinfo");
		m.addAttribute("chargingfarecenter", "/cfi/lhinfo");
		return "index";
	}
	
	@RequestMapping("/hminfo") // (휴맥스이브이)충전 요금 안내 페이지
	public String hminfo(Model m) {
		List<StationFareVO> list = null;

		try {
			list = sfbiz.hmget();
			m.addAttribute("hmlist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center", "/cfi/chargingfareinfo");
		m.addAttribute("chargingfarecenter", "/cfi/hminfo");
		return "index";
	}
	
	@RequestMapping("/ssinfo") // (삼성EVC)충전 요금 안내 페이지
	public String ssinfo(Model m) {
		List<StationFareVO> list = null;

		try {
			list = sfbiz.ssget();
			m.addAttribute("sslist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center", "/cfi/chargingfareinfo");
		m.addAttribute("chargingfarecenter", "/cfi/ssinfo");
		return "index";
	}
	
	@RequestMapping("/cuinfo") // (씨어스)충전 요금 안내 페이지
	public String cuinfo(Model m) {
		List<StationFareVO> list = null;

		try {
			list = sfbiz.cuget();
			m.addAttribute("culist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center", "/cfi/chargingfareinfo");
		m.addAttribute("chargingfarecenter", "/cfi/cuinfo");
		return "index";
	}
	
	@RequestMapping("/plinfo") // (플러그링크)충전 요금 안내 페이지
	public String plinfo(Model m) {
		List<StationFareVO> list = null;

		try {
			list = sfbiz.plget();
			m.addAttribute("pllist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center", "/cfi/chargingfareinfo");
		m.addAttribute("chargingfarecenter", "/cfi/plinfo");
		return "index";
	}
	
	@RequestMapping("/ekinfo") // (이노케이텍)충전 요금 안내 페이지
	public String ekinfo(Model m) {
		List<StationFareVO> list = null;

		try {
			list = sfbiz.ekget();
			m.addAttribute("eklist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center", "/cfi/chargingfareinfo");
		m.addAttribute("chargingfarecenter", "/cfi/ekinfo");
		return "index";
	}
	
	@RequestMapping("/ecinfo") // (이지차저)충전 요금 안내 페이지
	public String ecinfo(Model m) {
		List<StationFareVO> list = null;
		try {
			list = sfbiz.ecget();
			m.addAttribute("eclist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center", "/cfi/chargingfareinfo");
		m.addAttribute("chargingfarecenter", "/cfi/ecinfo");
		return "index";
	}
	
}
