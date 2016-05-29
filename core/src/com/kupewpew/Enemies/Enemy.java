package com.kupewpew.Enemies;

import com.kupewpew.Strategies.Strategy;

/**
 * Created by Pipatpol on 2559-05-26.
 */
public abstract class Enemy {
    private float pX,pY;
    private Strategy strategy;
    private final float SPEED = 8;

    public void setpX(float pX) {
        this.pX = pX;
    }

    public void setpY(float pY) {
        this.pY = pY;
    }

    public Enemy() {

    }

    public void attack() {
        //TODO Need to implement.
    }
    public void move() {
        strategy.move(this);
    }

    public void setStrategy(Strategy strategy){
        this.strategy = strategy;
    }

    public int getHP() {
        return HP;
    }

    public float getpX() {
        return pX;
    }

    public float getSPEED() {
        return SPEED;
    }

    public float getpY() {
        return pY;

    }

}
