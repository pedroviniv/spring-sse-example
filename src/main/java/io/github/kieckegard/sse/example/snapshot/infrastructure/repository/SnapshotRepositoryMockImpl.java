/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.sse.example.snapshot.infrastructure.repository;

import io.github.kieckegard.sse.example.snapshot.Snapshot;
import java.util.Random;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pafer
 */

@Repository
public class SnapshotRepositoryMockImpl implements SnapshotRepository {

    final Random random = new Random();
    
    @Override
    public Snapshot get() {
        
        final Integer x = this.random.nextInt();
        final Integer y = this.random.nextInt();
        
        return Snapshot.builder()
                .x(x)
                .y(y)
                .build();
    }
    
}
