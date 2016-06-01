package com.kupewpew.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Pipatpol on 2559-05-26.
 */
public class Player {

    private int HP;
    private static Player player;
    private boolean isInvulnerable;
    private float pX,pY;
    private Sprite sprite;

    public static Player getInstance() {
        if(player == null) player = new Player();
        return player;
    }

    private Player() {
        this.HP = 3;
        this.pX = 0;
        this.pY = 0;
        this.isInvulnerable = false;
        Texture jetTexture = new Texture(Gdx.files.internal("jet64x64.png"));
        this.sprite = new Sprite(jetTexture);
    }


    public void reset() {
        this.HP = 3;
        this.pX = 0;
        this.pY = 0;
        this.isInvulnerable = false;
    }
    public boolean isInvulnerable() {
        return isInvulnerable;
    }

    public void setInvulnerable(boolean b) {
        isInvulnerable = b;
    }

    public void getHurt() {
        this.setHP(this.HP - 1);
    }

    public boolean isDead() {
        return this.getHP() <= 0;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public float getpX() {
        return pX;
    }

    public void setpX(float pX) {
        this.pX = pX;
    }

    public float getpY() {
        return pY;
    }

    public void setpY(float pY) {
        this.pY = pY;
    }

    public Sprite getSprite() { return sprite; }
}
