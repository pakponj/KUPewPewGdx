package com.kupewpew.Enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;
import com.kupewpew.Game;
import com.kupewpew.Strategies.Strategy;

/**
 * Created by Pipatpol on 2559-05-26.
 */
public abstract class Enemy implements Pool.Poolable {

    private float pX,pY, speed;
    private Strategy strategy;
    private int HP;
    private Sprite sprite;
    private boolean alive;

    public Enemy(Texture texture, float enemySpeed) {
        this.pX = 0;
        this.pY = 0;
        this.speed = enemySpeed;
        this.alive = false;
        this.sprite = new Sprite(texture);
    }

    public void setpX(float pX) {
        this.pX = pX;
    }

    public void setpY(float pY) {
        this.pY = pY;
    }

    public Enemy() {
        this.HP = 3;
    }

    public void getHurt() {
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

    public float getpY() {
        return pY;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void init(float posX, float posY) {
        this.pX =  posX;
        this.pY = posY;
        sprite.setPosition(posX, posY);
        alive = true;
    }

    public void reset() {
        this.pX = 0;
        this.pY = 0;
        alive = false;
    }

    public boolean isOutOfScreen() {
        Rectangle enemyRect = sprite.getBoundingRectangle();
        return !enemyRect.overlaps(Game.getScreenRect());
    }

    public float getSpeed() {
        return speed;
    }

    public void update() {
        pY -= speed;
        sprite.setPosition(pX, pY);
        if(isOutOfScreen()) alive = false;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public boolean isAlive() { return alive; }

}
