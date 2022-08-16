package com.user.mapper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.user.frame.Emitter;

@Repository
public class EmitterRepository implements Emitter {

	private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
    private final Map<String, Object> eventCache = new ConcurrentHashMap<>();

    @Override
    public SseEmitter save(String emitterId, SseEmitter sseEmitter) {
        emitters.put(emitterId, sseEmitter);
        return sseEmitter;
    }

    @Override
    public void saveEventCache(String eventCacheId, Object event) {
        eventCache.put(eventCacheId, event);
    }

    @Override
    public Map<String, SseEmitter> findAllEmitterStartWithByUid(String uid) {
        return emitters.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(uid))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public Map<String, Object> findAllEventCacheStartWithByUid(String uid) {
        return eventCache.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(uid))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public void deleteById(String id) {
        emitters.remove(id);
    }

    @Override
    public void deleteAllEmitterStartWithUid(String uid) {
        emitters.forEach(
                (key, emitter) -> {
                    if (key.startsWith(uid)) {
                        emitters.remove(key);
                    }
                }
        );
    }

    @Override
    public void deleteAllEventCacheStartWithUid(String uid) {
        eventCache.forEach(
                (key, emitter) -> {
                    if (key.startsWith(uid)) {
                        eventCache.remove(key);
                    }
                }
        );
    }

}
