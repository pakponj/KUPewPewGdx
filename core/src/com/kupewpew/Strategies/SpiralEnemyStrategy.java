package com.kupewpew.Strategies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.kupewpew.Enemies.Enemy;

/**
 * Created by Pipatpol on 2559-05-27.
 */
public class SpiralEnemyStrategy implements Strategy {

    int i = 0;
    @Override
    public void move(Enemy enemy) {

        enemy.setpY(enemy.getpY() - enemy.getSpeed() / 3);
        double a = (Math.sin(Math.toRadians(i++)) * 15);
        enemy.setpX(enemy.getpX() + ((float) a));
        Sprite enemySprite = enemy.getSprite();
        enemySprite.setPosition(enemy.getpX(), enemy.getpY());
        enemySprite.setAlpha(1);
        if(enemy.isOutOfScreen()) enemy.alive = false;

        if (i >= 45) i *= -1;
    }
}
