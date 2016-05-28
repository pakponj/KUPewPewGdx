package com.kupewpew.Factories;

import com.kupewpew.Enemies.ApproachEnemy;
import com.kupewpew.Enemies.Enemy;
import com.kupewpew.Strategies.ApproachEnemyStrategy;
import com.kupewpew.Strategies.Strategy;


/**
 * Created by Pipatpol on 2559-05-27.
 */
public class ApproachEnemyFactory implements EnemyFactory {
    Strategy approachingStrategy = new ApproachEnemyStrategy();
    @Override
    public Enemy createEnemy() {
        Enemy approachingEnemy = new ApproachEnemy();
        approachingEnemy.setStrategy(approachingStrategy);
        return approachingEnemy;
    }
}
