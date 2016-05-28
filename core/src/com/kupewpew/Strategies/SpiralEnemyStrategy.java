package com.kupewpew.Strategies;

import com.kupewpew.Enemies.Enemy;

/**
 * Created by Pipatpol on 2559-05-27.
 */
public class SpiralEnemyStrategy implements Strategy {

    private float fixpY;
    int i = 0;
    private boolean check = false;

    @Override
    public void move(Enemy enemy) {
        if(!check)
        {
            fixpY = enemy.getpY();
            check = true;
        }
        enemy.setpX(enemy.getpX() + enemy.getSPEED());
        double a =(Math.sin(Math.toRadians(i++)))* 200;
        enemy.setpY(fixpY + (float)a);
        if(i>=360) i =0;
    }
}
