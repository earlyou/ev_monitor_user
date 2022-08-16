package com.user.biz;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.user.mapper.EmitterRepository;
import com.user.vo.SSEVO;

@Service
public class EmitterBiz {

	@Autowired
	EmitterRepository emitterRepository;
	
	
	private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;


    public SseEmitter subscribe(String uid, String lastEventId) {
        // 1 언제 발행된 emitter인지 구분을 위해 uid와 시간을 합쳐 id 생성
        String id = uid + "_" + System.currentTimeMillis();
        
        // 2 id와 발행된 내용을 저장
        SseEmitter emitter = emitterRepository.save(id, new SseEmitter(DEFAULT_TIMEOUT));
        
        emitter.onCompletion(() -> emitterRepository.deleteById(id));
        emitter.onTimeout(() -> emitterRepository.deleteById(id));

	// 3
        // 503 에러를 방지하기 위한 더미 이벤트 전송
        sendToClient(emitter, id, "EventStream Created. [userId=" + uid + "]");

	// 4
        // 클라이언트가 미수신한 Event 목록이 존재할 경우 전송하여 Event 유실을 예방
        if (!lastEventId.isEmpty()) {
            Map<String, Object> events = emitterRepository.findAllEventCacheStartWithByUid(uid);
            events.entrySet().stream()
                  .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
                  .forEach(entry -> sendToClient(emitter, entry.getKey(), entry.getValue()));
        }

        return emitter;
    }
	
    
    public void send(SSEVO sse) {
    	
        // 로그인 한 유저의 SseEmitter 모두 가져오기
        Map<String, SseEmitter> sseEmitters = emitterRepository.findAllEmitterStartWithByUid(sse.getUid());
        sseEmitters.forEach(
                (key, emitter) -> {
                    // 데이터 캐시 저장(유실된 데이터 처리하기 위함)
                    emitterRepository.saveEventCache(key, sse);
                    // 데이터 전송
                    sendToClient(emitter, key, sse);
                }
        );
    }
    
    
    private void sendToClient(SseEmitter emitter, String id, Object data) {
        try {
            emitter.send(SseEmitter.event()
                                   .id(id)
                                   .name("sse")
                                   .data(data));
           
        } catch (IOException exception) {
            emitterRepository.deleteById(id);
            throw new RuntimeException("연결 오류!");
        }
    }
	
	
//	public SseEmitter subscribe(String uid, String lastEventId) {
//	    String emitterId = makeTimeIncludeId(uid);
//	    SseEmitter emitter = emitterRepository.save(emitterId, new SseEmitter(timeout));
//	    emitter.onCompletion(() -> emitterRepository.deleteById(emitterId));
//	    emitter.onTimeout(() -> emitterRepository.deleteById(emitterId));
//
//	    // 503 에러를 방지하기 위한 더미 이벤트 전송
//	    String eventId = makeTimeIncludeId(uid);
//	    sendNotification(emitter, eventId, emitterId, "EventStream Created. [userId=" + uid + "]");
//
//	    // 클라이언트가 미수신한 Event 목록이 존재할 경우 전송하여 Event 유실을 예방
//	    if (hasLostData(lastEventId)) {
//	        sendLostData(lastEventId, uid, emitterId, emitter);
//	    }
//
//	    return emitter;
//	}
//
//	private String makeTimeIncludeId(String uid) {
//	    return uid + "_" + System.currentTimeMillis();
//	}
//
//	private void sendNotification(SseEmitter emitter, String eventId, String emitterId, Object data) {
//	    try {
//	        emitter.send(SseEmitter.event()
//	                     .id(eventId)
//	                     .data(data));
//	    } catch (IOException exception) {
//	        emitterRepository.deleteById(emitterId);
//	    }
//	}
//
//	private boolean hasLostData(String lastEventId) {
//	    return !lastEventId.isEmpty();
//	}
//
//	private void sendLostData(String lastEventId, String uid, String emitterId, SseEmitter emitter) {
//	    Map<String, Object> eventCaches = emitterRepository.findAllEventCacheStartWithByUid(String.valueOf(uid));
//	    eventCaches.entrySet().stream()
//	        .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
//	        .forEach(entry -> sendNotification(emitter, entry.getKey(), emitterId, entry.getValue()));
//	}
//	
//	public void send(String uid, String notificationType, String content, String url) {
//	    String eventId = uid + "_" + System.currentTimeMillis();
//	    Map<String, SseEmitter> emitters = emitterRepository.findAllEmitterStartWithByUid(uid);
//	    emitters.forEach(
//	        (key, emitter) -> {
//	            emitterRepository.saveEventCache(key, notification);
//	            sendNotification(emitter, eventId, key, NotificationResponseDto.create(notification));
//	        }
//	    );
//	}
//
//	private Notification createNotification(Member receiver, NotificationType notificationType, String content, String url) {
//	    return Notification.builder()
//	        .receiver(receiver)
//	        .notificationType(notificationType)
//	        .content(content)
//	        .url(url)
//	        .isRead(false)
//	        .build();
//	}
}
