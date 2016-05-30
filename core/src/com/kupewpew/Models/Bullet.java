package com.kupewpew.Models;

/**
 * Created by Pipatpol on 2559-05-27.
 */
public class Bullet {

    //    private Vector2 pos, direction;
    private float posX, posY;
    private int attack;

    public Bullet(float bulletSpeed) {
//        pos = new Vector2( 0, 0 );
//        direction = new Vector2( 0, bulletSpeed );

        this.posX = 0;
        this.posY = 0;
        this.attack = 5;
    }

//    public Vector2 getPos() { return pos; }

//    public Vector2 getDirection() { return direction; }

    public float getPosX() {
//        return pos.x;
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

//    public boolean isOutOfScreen() {
//        if( posX < 0 || posX > Gdx.graphics.getWidth()) return true;
//        if( posY < 0 || posY > Gdx.graphics.getHeight()) return true;
//        return false;
//    }

    public boolean isHit(){
        //TODO Need to implement.
        return false;
    }

//    public void setPos(Vector2 newPosition) {
//        pos = newPosition;
//    }

//    public void update() {
//        pos.add(direction);
//        pos.scl( Gdx.graphics.getDeltaTime() );
//    }

}
