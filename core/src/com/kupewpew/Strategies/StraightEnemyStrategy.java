package com.kupewpew.Strategies;

import com.kupewpew.Enemies.Enemy;

/**
 * Created by Pipatpol on 2559-05-27.
 */
public class StraightEnemyStrategy implements Strategy {
    @Override
    public void move(Enemy enemy) {
        enemy.update();
    }
}
