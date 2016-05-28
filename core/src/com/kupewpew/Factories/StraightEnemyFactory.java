package com.kupewpew.Factories;

import com.kupewpew.Enemies.Enemy;
import com.kupewpew.Enemies.StraightEnemy;
import com.kupewpew.Strategies.StraightEnemyStrategy;
import com.kupewpew.Strategies.Strategy;

/**
 * Created by Pipatpol on 2559-05-26.
 */
public class StraightEnemyFactory implements EnemyFactory{
    Strategy straightEnemyStrategy = new StraightEnemyStrategy();
    @Override
    public Enemy createEnemy() {
        Enemy straightEnemy = new StraightEnemy();
        straightEnemy.setStrategy(straightEnemyStrategy);
        return straightEnemy;
    }
}
