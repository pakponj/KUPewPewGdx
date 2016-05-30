package com.kupewpew.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;
import com.kupewpew.Game;

/**
 * Created by Pipatpol on 2559-05-27.
 */
public class Bullet implements Pool.Poolable {

    //    private Vector2 pos, direction;
    private float posX, posY, speed;
    private boolean alive;
    private Sprite sprite;

    public Bullet(float bulletSpeed, Texture bulletTexture) {
        this.posX = 0;
        this.posY = 0;
        this.speed = bulletSpeed;
        this.alive = false;
        sprite = new Sprite(bulletTexture);
    }

    public void init(float posX, float posY) {
        this.posX = posX;
        this.posY = posY;
        sprite.setPosition(posX, posY);
        alive = true;
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

    @Override
    public void reset() {
        this.posX = 0;
        this.posY = 0;
        sprite.setPosition(0,0);
        sprite.setAlpha(0);
        alive = false;
    }

    public boolean isOutOfScreen() {
        Rectangle bulletRect = sprite.getBoundingRectangle();
        return !bulletRect.overlaps(Game.getScreenRect());
    }

    public Sprite getSprite() { return sprite; }

    public void update() {
        posY += speed;
        sprite.setPosition(posX, posY);
        sprite.setAlpha(1);
        if(isOutOfScreen()) alive = false;
    }

    public boolean isAlive() { return alive; }

}

