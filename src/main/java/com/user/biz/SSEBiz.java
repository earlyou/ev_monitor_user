package com.user.biz;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.user.frame.Biz;
import com.user.listener.SSEEvent;
import com.user.mapper.SSEMapper;
import com.user.vo.SSEVO;

@Service
public class SSEBiz implements Biz<String, SSEVO> {

	@Autowired
	SSEMapper dao;
	
	private final ApplicationEventPublisher eventPublisher;

    public SSEBiz(final ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }
	
	// 이게 비동기로 돌아가아함
    @Async
	@Override
	public void register(SSEVO v) throws Exception {
		// 아이디로 비교하여 타이머 스레드 정지
		
		dao.insert(v);
		
		LocalDateTime nowDate = LocalDateTime.now();
		String alarmdate = v.getAlarmtime();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(alarmdate, formatter);
		
		long settime = ChronoUnit.SECONDS.between(nowDate, dateTime);
		
		// alarm time으로 타이머 돌리기
		// *1000 앞에 settime으로 수정 지금은 테스트 할것임
		try {
            Thread.sleep(20*1000);
           
            System.out.println("onalarm");
            
            SSEVO sse = new SSEVO(v.getId(), v.getUid(), v.getStatid(), v.getAlarmtime(), "충전이 완료되었습니다.", "achec");
            dao.insert(sse);
            eventPublisher.publishEvent(new SSEEvent(sse));
        } catch (InterruptedException e) {
        	Thread.currentThread().interrupt();
        	e.printStackTrace();
        }
		
	}

	@Override
	public void modify(SSEVO v) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(String k) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SSEVO get(String k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SSEVO> get() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
