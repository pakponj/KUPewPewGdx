package com.kupewpew.GameUI;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

/**
 * Created by Administrator on 29/5/2559.
 */
public class MainGame extends ApplicationAdapter implements ApplicationListener {
    private SpriteBatch batch;

    public static int WIDTH = 480 ,HEIGHT = 800;

    public MainGame(){

    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        Button btn = new Button();
        ScreenManager.setScreen(new GameStartScreen());
    }

    @Override
    public void dispose() {
        if (ScreenManager.getCurrentScreen()!=null)
            ScreenManager.getCurrentScreen().dispose();
        batch.dispose();
    }
    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (ScreenManager.getCurrentScreen() != null)
            ScreenManager.getCurrentScreen().update();

        if (ScreenManager.getCurrentScreen() != null)
            ScreenManager.getCurrentScreen().render(batch);
    }

    @Override
    public void resize(int width, int height) {
        if (ScreenManager.getCurrentScreen() != null)
            ScreenManager.getCurrentScreen().resize(width, height);
    }

    @Override
    public void pause() {
        if (ScreenManager.getCurrentScreen() != null)
            ScreenManager.getCurrentScreen().pause();
    }

    @Override
    public void resume() {
        if (ScreenManager.getCurrentScreen() != null)
            ScreenManager.getCurrentScreen().resume();
    }
}
