/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.sse.example.infrastructure.sse;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 *
 * @author pafer
 */
public interface SseEmitters {
    
    SseEmitter newEmitter();
    void release(SseEmitter emitter);
    void broadcast(Object payload);
}
