package com.kupewpew.Strategies;

import com.kupewpew.Enemies.Enemy;

/**
 * Created by Pipatpol on 2559-05-27.
 */
public class ApproachEnemyStrategy implements Strategy {
    @Override
    public void move(Enemy enemy) {
        enemy.setpX(enemy.getpX() + enemy.getSPEED());
    }
}
