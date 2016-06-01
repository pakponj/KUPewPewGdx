package com.kupewpew.Factories;

import com.badlogic.gdx.graphics.Texture;
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
    public Enemy createEnemy(Texture texture, float speed) {
        Enemy spiralEnemy = new SpiralEnemy(texture, speed);
        spiralEnemy.setStrategy(spiralEnemyStrategy);
        return spiralEnemy;
    }
}
