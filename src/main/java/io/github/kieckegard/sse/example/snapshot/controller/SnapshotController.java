/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.sse.example.snapshot.controller;

import io.github.kieckegard.sse.example.snapshot.infrastructure.worker.NewSnapshotEvent;
import io.github.kieckegard.sse.example.snapshot.Snapshot;
import io.github.kieckegard.sse.example.infrastructure.sse.SseEmitters;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 *
 * @author pafer
 */

@RestController("/snapshots")
public class SnapshotController {
    
    private final SseEmitters sseEmitters;
    private static final Logger LOG = Logger.getLogger(SnapshotController.class.getName());

    @Autowired
    public SnapshotController(final SseEmitters sseEmitters) {
        this.sseEmitters = sseEmitters;
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping
    public SseEmitter connect() {
        final SseEmitter emitter = this.sseEmitters.newEmitter();
        return emitter;
    }
    
    @EventListener
    public void onNewSnapshotEvent(NewSnapshotEvent newSnapshotEvent) {
        
        final Snapshot snapshot = newSnapshotEvent.getPayload();
        LOG.log(Level.INFO, "Notificating all emitters of the"
                + " new Snapshot: [{0}]", snapshot);
        this.sseEmitters.broadcast(snapshot);
    }
}
