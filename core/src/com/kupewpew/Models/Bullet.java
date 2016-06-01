package com.kupewpew.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;
import com.kupewpew.GameUI.Game;

/**
 * Created by Pipatpol on 2559-05-27.
 */
public class Bullet implements Pool.Poolable {

    private float posX, posY, speed;
    private boolean alive;
    private Sprite sprite;

    public Bullet(float bulletSpeed, Texture bulletTexture) {
        posX = 0;
        posY = 0;
        speed = bulletSpeed;
        alive = false;
        sprite = new Sprite(bulletTexture);
    }

    public void init(float posX, float posY) {
        this.posX = posX;
        this.posY = posY;
        sprite.setPosition(posX, posY);
        alive = true;
    }

    @Override
    public void reset() {
        posX = 0;
        posY = 0;
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

