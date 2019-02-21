/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.sse.example.snapshot;

/**
 *
 * @author pafer
 */
public class Snapshot {
    
    private int x;
    private int y;

    public static class Builder {

        private int x;
        private int y;

        private Builder() {
        }

        public Builder x(final int value) {
            this.x = value;
            return this;
        }

        public Builder y(final int value) {
            this.y = value;
            return this;
        }

        public Snapshot build() {
            return new io.github.kieckegard.sse.example.snapshot.Snapshot(x, y);
        }
    }

    public static Snapshot.Builder builder() {
        return new Snapshot.Builder();
    }

    private Snapshot(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Snapshot{" + "x=" + x + ", y=" + y + '}';
    }
}
