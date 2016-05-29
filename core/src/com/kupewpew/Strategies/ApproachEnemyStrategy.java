package com.kupewpew.Strategies;

import com.kupewpew.Enemies.Enemy;

/**
 * Created by Pipatpol on 2559-05-27.
 */
public class ApproachEnemyStrategy implements Strategy {
    @Override
    public void move(Enemy enemy) {
        enemy.setpY(enemy.getpY() - enemy.getSPEED());
//        enemy.setpX(enemy.getpX() + enemy.getSPEED() * Gdx.graphics.getDeltaTime());
    }
}
