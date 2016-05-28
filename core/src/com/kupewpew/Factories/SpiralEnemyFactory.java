package com.kupewpew.Factories;

import com.kupewpew.Enemies.Enemy;
import com.kupewpew.Enemies.SpiralEnemy;
import com.kupewpew.Strategies.SpiralEnemyStrategy;
import com.kupewpew.Strategies.Strategy;

/**
 * Created by Pipatpol on 2559-05-27.
 */
public class SpiralEnemyFactory implements EnemyFactory {
    Strategy spiralEnemyStrategy = new SpiralEnemyStrategy();
    @Override
    public Enemy createEnemy() {
        Enemy spiralEnemy = new SpiralEnemy();
        spiralEnemy.setStrategy(spiralEnemyStrategy);
        return spiralEnemy;
    }
}
