package com.kupewpew.Factories;

import com.badlogic.gdx.graphics.Texture;
import com.kupewpew.Enemies.Enemy;

/**
 * Created by Pipatpol on 2559-05-26.
 */
public interface EnemyFactory {
    public Enemy createEnemy(Texture texture, float speed);
}
