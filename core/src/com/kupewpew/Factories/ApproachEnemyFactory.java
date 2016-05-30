package com.kupewpew.Factories;

import com.badlogic.gdx.graphics.Texture;
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
    public Enemy createEnemy(Texture texture, float speed) {
        Enemy approachingEnemy = new ApproachEnemy(texture, speed);
        approachingEnemy.setStrategy(approachingStrategy);
        return approachingEnemy;
    }
}
