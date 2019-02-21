/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.sse.example.snapshot.infrastructure.repository;

import io.github.kieckegard.sse.example.snapshot.Snapshot;

/**
 *
 * @author pafer
 */
public interface SnapshotRepository {
    Snapshot get();
}
