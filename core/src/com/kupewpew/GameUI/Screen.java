package com.kupewpew.GameUI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Administrator on 30/5/2559.
 */
public interface Screen {

    public  void create();

    public  void update();

    public  void render(SpriteBatch sb);

    public  void resize(int width, int height);

    public  void dispose();

    public  void pause();

    public  void resume();

}
