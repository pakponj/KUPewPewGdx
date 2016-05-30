package com.kupewpew.Strategies;

import com.kupewpew.Enemies.Enemy;
import com.kupewpew.Models.Player;

/**
 * Created by Pipatpol on 2559-05-27.
 */
public class ApproachEnemyStrategy implements Strategy {
    private final int firstQuadrant = 1,
                      secondQuadrant = 2,
                      thirdQuadrant = 3,
                      forthQuadrant = 4;
    private Player player;
    @Override
    public void move(Enemy enemy) {
        player = player.getInstance();
        //enemy.setpY(enemy.getpY() - enemy.getSPEED());
        followPlayer(enemy);
//        enemy.setpX(enemy.getpX() + enemy.getSPEED() * Gdx.graphics.getDeltaTime());
    }
    public int checkPlayerQuadrant(float distanceX, float distanceY) {
        if(distanceX >= 0){
            if(distanceY >= 0) {
                return firstQuadrant;
            } else return forthQuadrant;
        } else {
            if(distanceY >= 0) {
                return secondQuadrant;
            } else return thirdQuadrant;
        }
    }

    public float getDistanceXFromPlayer(Enemy enemy) {
        return enemy.getpX() - player.getpX();
    }

    public float getDistanceYFromPlayer(Enemy enemy) {
        return enemy.getpY() - player.getpY();
    }

    public void followPlayer(Enemy enemy) {
        float distanceFromX = getDistanceXFromPlayer(enemy);
        float distanceFromY = getDistanceYFromPlayer(enemy);
        int quadrant = checkPlayerQuadrant(distanceFromX,distanceFromY);
        double degree = Math.atan(distanceFromX/distanceFromY);
        if(quadrant == thirdQuadrant || quadrant == forthQuadrant)
            degree += Math.PI;
        moveAfterPlayer(enemy, degree);
    }

    public void moveAfterPlayer(Enemy enemy, double degree) {
        enemy.setpX(enemy.getpX() - (float)(enemy.getSPEED() * Math.sin(degree)));
        enemy.setpY(enemy.getpY() - (float)(enemy.getSPEED() * Math.cos(degree)));
    }
}
