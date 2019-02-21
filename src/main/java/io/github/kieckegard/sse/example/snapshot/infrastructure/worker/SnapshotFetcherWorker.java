/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.sse.example.snapshot.infrastructure.worker;

import io.github.kieckegard.sse.example.snapshot.Snapshot;
import io.github.kieckegard.sse.example.snapshot.infrastructure.repository.SnapshotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author pafer
 */

@Component
public class SnapshotFetcherWorker {
    
    private final SnapshotRepository snapshotRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public SnapshotFetcherWorker(SnapshotRepository snapshotRepository, 
            ApplicationEventPublisher eventPublisher) {
        this.snapshotRepository = snapshotRepository;
        this.eventPublisher = eventPublisher;
    }
    
    @Scheduled(fixedRate = 2000, initialDelay = 2000)
    public void fetchSnapshotSchedule() {
        
        final Snapshot snapshot = this.snapshotRepository.get();
        final NewSnapshotEvent newSnapshotEvent = NewSnapshotEvent.builder()
                .source(this)
                .payload(snapshot)
                .build();
        
        this.eventPublisher.publishEvent(newSnapshotEvent);
    }
}
