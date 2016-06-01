package com.kupewpew.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;
import com.kupewpew.GameUI.Game;

/**
 * Created by Pipatpol on 2559-05-27.
 */
public class Bomb implements Pool.Poolable {
    private float posX, posY, speed;
    private static Texture bombTexture;
    private static Texture explosionTexture;
    private boolean alive, exploding;
    private Sprite sprite;
    public static Sprite bombSprite, explosionSprite;

    public Bomb(float bombSpeed) {
        posX = 0;
        posY = 0;
        this.speed = bombSpeed;
        alive = false;
        if( bombTexture == null ) bombTexture = new Texture(Gdx.files.internal("bomb1_64x64.png"));
        if( explosionTexture == null ) explosionTexture = new Texture(Gdx.files.internal("explosion512x512.png"));
        bombSprite = new Sprite(bombTexture);
        explosionSprite = new Sprite(explosionTexture);
        sprite = bombSprite;
    }

    public void init(float posX, float posY) {
        this.posX = posX;
        this.posY = posY;
        sprite.setAlpha(1);
        sprite.setPosition(posX, posY);
        alive = true;
        exploding = false;
    }

    public void update() {
        if(!exploding) {
            posY -= speed;
            sprite.setPosition(posX, posY);
            sprite.setAlpha(1);
            if(isOutOfScreen()) alive = false;
        }
    }

    private boolean isOutOfScreen() {
        Rectangle bombRect = sprite.getBoundingRectangle();
        return !bombRect.overlaps(Game.getScreenRect());
    }

    public Sprite getSprite() { return sprite; }

    public void startExplode(/*Pool bombsPool, List<Bomb> explosionsList*/) {
        sprite = explosionSprite;
        sprite.setPosition(Player.getInstance().getpX() - sprite.getWidth()/2, Player.getInstance().getpY() - sprite.getHeight()/2);
        exploding = true;
    }

    public boolean isAlive() { return alive; }

    @Override
    public void reset() {
        sprite.setAlpha(1);
        posX = 0;
        posY = 0;
        sprite = bombSprite;
        sprite.setPosition(posX, posY);
        exploding = false;
        alive = false;
    }

    public boolean isExploding() { return exploding; }

}
