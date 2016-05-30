package com.kupewpew.Models;

import java.util.Stack;

/**
 * Created by Pipatpol on 2559-05-26.
 */
public class Player {
    private int score;
    private int HP;
    private int live;
    private static Player player;
    private boolean isInvulnerable;
    private float pX,pY;
    private Stack<Integer> bomb;

    public static Player getInstance(String name) {
        if (player == null) {
            player = new Player(name);
        }
        return player;
    }

    public static Player getInstance() {
        if(player==null)
            player = new Player();
        return player;
    }

    private Player(String name) {
        this.live = 3;
        this.score = 0;
        this.HP = 3;
        this.bomb = new Stack<Integer>();
        this.isInvulnerable = false;
    }

    private Player() {
        this.live = 3;
        this.score = 0;
        this.HP = 3;
        this.bomb = new Stack<Integer>();
        this.pX = 0;
        this.pY = 0;
        this.isInvulnerable = false;
    }

    public void reset() {
        this.live = 3;
        this.score = 0;
        this.HP = 3;
        this.bomb = new Stack<Integer>();
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

    public int getScore() {
        return score;
    }

    public int getHP() {
        return HP;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getLive() {
        return live;
    }

    public void setLive(int live) {
        this.live = live;
    }

    public void activateBomb() {
        this.bomb.pop();
    }

    public void collectBomb() {
        this.bomb.push(1);
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
}
