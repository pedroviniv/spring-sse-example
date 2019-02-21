/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.sse.example.infrastructure.sse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 *
 * @author pafer
 */

@Service
@Scope("singleton")
public class SseEmitterDefaultImpl implements SseEmitters {
    
    private CopyOnWriteArrayList<SseEmitter> emitters =
            new CopyOnWriteArrayList<>();
    private static final Logger LOG = Logger.getLogger(SseEmitterDefaultImpl.class.getName());

    @Override
    public SseEmitter newEmitter() {
        
        final SseEmitter emitter = new SseEmitter();
        emitter.onCompletion(() -> {
            this.release(emitter);
        });
        emitter.onTimeout(() -> {
            emitter.complete();
            this.release(emitter);
        });
        
        this.emitters.add(emitter);
        return emitter;
    }

    @Override
    public void release(SseEmitter emitter) {
        this.emitters.remove(emitter);
    }
    
    private void releaseAll(List<SseEmitter> emitters) {
        this.emitters.removeAll(emitters);
    }

    @Override
    public void broadcast(Object payload) {
        List<SseEmitter> deadEmitters = new ArrayList<>();
        this.emitters.forEach(emitter -> {
            try {
                emitter.send(payload);
            } catch (IOException ex) {
                LOG.log(Level.INFO, "An error occurred while sending the"
                        + " payload {0} to the client {1}", 
                        new Object[]{payload, emitter});
                deadEmitters.add(emitter);
            }
        });
        this.releaseAll(deadEmitters);
    }
    
    
}
