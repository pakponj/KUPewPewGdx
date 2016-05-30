package com.kupewpew.Strategies;

import com.kupewpew.Enemies.Enemy;

/**
 * Created by Pipatpol on 2559-05-27.
 */
public class SpiralEnemyStrategy implements Strategy {

    //    private float fixpX;
    int i = 0;
//    private boolean check = false;

    @Override
    public void move(Enemy enemy) {
//        if(!check)
//        {
//            fixpX = enemy.getpX();
//            check = true;
//        }

        enemy.setpY(enemy.getpY() - enemy.getSpeed() / 3);
        double a = (Math.sin(Math.toRadians(i++)) * 15);
        enemy.setpX(enemy.getpX() + ((float) a));

//        enemy.setpX(enemy.getpX());
//        double a =(Math.sin(Math.toRadians(i++)))* 200;
//        enemy.setpY(fixpY - (float)a - enemy.getSPEED()/3 );
        if (i >= 45) i *= -1;
    }
}
