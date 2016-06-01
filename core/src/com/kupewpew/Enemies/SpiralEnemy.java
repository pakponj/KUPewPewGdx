package com.kupewpew.Enemies;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Pipatpol on 2559-05-27.
 */
public class SpiralEnemy extends Enemy {

    public SpiralEnemy(Texture texture, float speed) {
        super(texture, speed);
        score = 10;
    }

    @Override
    public void move() {
        super.move();
    }

    @Override
    public void reset() {

    }
}
