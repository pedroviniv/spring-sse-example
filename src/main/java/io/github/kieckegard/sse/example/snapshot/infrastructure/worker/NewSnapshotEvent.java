/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.sse.example.snapshot.infrastructure.worker;

import io.github.kieckegard.sse.example.snapshot.Snapshot;
import org.springframework.context.ApplicationEvent;

/**
 *
 * @author pafer
 */
public class NewSnapshotEvent extends ApplicationEvent {
    
    private final Snapshot payload;
    
    private NewSnapshotEvent(Object source, Snapshot payload) {
        super(source);
        this.payload = payload;
    }
    
    public static class Builder {
        
        private Object source;
        private Snapshot payload;
        
        public Builder() {}
        
        public Builder source(Object source) {
            this.source = source;
            return this;
        }
        
        public Builder payload(Snapshot payload) {
            this.payload = payload;
            return this;
        }
        
        public NewSnapshotEvent build() {
            return new NewSnapshotEvent(source, payload);
        }
    }
    
    public static Builder builder() {
        return new Builder();
    }

    public Snapshot getPayload() {
        return payload;
    }

    @Override
    public String toString() {
        return "NewSnapshotEvent{" + "payload=" + payload + '}';
    }
}
