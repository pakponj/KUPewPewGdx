package com.kupewpew.Models;

import com.badlogic.gdx.Gdx;

/**
 * Created by Pipatpol on 2559-05-27.
 */
public class Bullet {

    private float posX, posY;
    private int attack;
    public Bullet() {
        this.posX = 0;
        this.posY = 0;
        this.attack = 5;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public boolean isOutOfScreen() {
        if( posX < 0 || posX > Gdx.graphics.getWidth()) return true;
        if( posY < 0 || posY > Gdx.graphics.getHeight()) return true;
        return false;
    }

    public boolean isHit(){
        //TODO Need to implement.
        return false;
    }
}
