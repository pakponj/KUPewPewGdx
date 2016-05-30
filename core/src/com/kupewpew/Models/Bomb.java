package com.kupewpew.Models;

/**
 * Created by Pipatpol on 2559-05-27.
 */
public class Bomb {
    private float pX, py;
    private final float SPEED = 5f;

    public Bomb(float pX, float pY) {
        this.pX = pX;
        this.py =pY;
    }

    //TODO Need to implement.
    public boolean isCollidWithPlayer() {
        return false;
    }
}
