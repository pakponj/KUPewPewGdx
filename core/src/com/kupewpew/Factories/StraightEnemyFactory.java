package com.kupewpew.Factories;

import com.badlogic.gdx.graphics.Texture;
import com.kupewpew.Enemies.Enemy;
import com.kupewpew.Enemies.StraightEnemy;
import com.kupewpew.Strategies.StraightEnemyStrategy;
import com.kupewpew.Strategies.Strategy;

/**
 * Created by Pipatpol on 2559-05-26.
 */
public class StraightEnemyFactory implements EnemyFactory{
    Strategy straightEnemyStrategy = new StraightEnemyStrategy();

//    private static final Texture texture = new Texture(Gdx.files.internal("invader1_64x64.png"));

    public StraightEnemyFactory() {

    }

    @Override
    public Enemy createEnemy(Texture texture, float speed) {
        Enemy straightEnemy = new StraightEnemy(texture, speed);
        straightEnemy.setStrategy(straightEnemyStrategy);
        return straightEnemy;
    }
}
