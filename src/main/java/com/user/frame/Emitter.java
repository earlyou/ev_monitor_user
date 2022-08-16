package com.user.frame;

import java.util.Map;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface Emitter {
	public SseEmitter save(String emitterId, SseEmitter sseEmitter);
	public void saveEventCache(String emitterId, Object event);
	public Map<String, SseEmitter> findAllEmitterStartWithByUid(String uid);
	public Map<String, Object> findAllEventCacheStartWithByUid(String uid);
	public void deleteById(String id);
	public void deleteAllEmitterStartWithUid(String uid);
	public void deleteAllEventCacheStartWithUid(String uid);
}
