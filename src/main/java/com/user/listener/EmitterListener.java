package com.user.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.user.biz.EmitterBiz;
import com.user.vo.SSEVO;

@Component
public class EmitterListener {
	
	@Autowired
	EmitterBiz ebiz;
	
	@EventListener
	public void setSSE(SSEEvent event) {
		System.out.println("이벤트감지");
		
		// 이벤트에서 sse 가져오기
		SSEVO sse = event.getSse();
		System.out.println(sse);
		
		// 이벤트 감지해서 전송하기?
		ebiz.send(sse);
		
//		// 이벤트감지해서 구독하기?
//		ebiz.subscribe(sse.getUid(), sse.getId());
	}
}
