package com.kupewpew.Enemies;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Pipatpol on 2559-05-26.
 */
public class StraightEnemy extends Enemy{

    public StraightEnemy(Texture texture, float speed) {
        super(texture, speed);
        score = 5;
    }

    @Override
    public void move() {
        super.move();
    }

    @Override
    public void reset() {

    }
}
