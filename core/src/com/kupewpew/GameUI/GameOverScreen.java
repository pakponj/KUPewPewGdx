package com.kupewpew.GameUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Administrator on 30/5/2559.
 */
public class GameOverScreen implements Screen {


    SpriteBatch sb;
    private Stage stage;
    private Texture bg;
    private Image retry;


    public GameOverScreen()
    {
        stage = new Stage();
        Texture retryText = new Texture(Gdx.files.internal("retry.jpg"));
        bg = new Texture(Gdx.files.internal("gameover.jpg"));
        Image back = new Image(bg);

        retry = new Image(retryText);
        retry.setX(Gdx.graphics.getWidth() / 2 - retryText.getWidth() / 4);
        retry.setY(Gdx.graphics.getHeight() / 3 - retryText.getHeight() / 2);
        retry.setWidth(400);
        retry.setHeight(200);

        back.setPosition(Gdx.graphics.getHeight() / 2 - bg.getWidth() + 200, Gdx.graphics.getWidth() - bg.getHeight() / 2);

        Gdx.input.setInputProcessor(stage);

        stage.addActor(back);
        stage.addActor(retry);

        retry.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                Gdx.app.log("Clicked", "Clicked");
                ScreenManager.setScreen(new GameStartScreen());
                Game.startGame = true;

                //stage.clear();
            }
        });
    }


    @Override
    public void create() {
    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch sb) {

        sb.begin();
        stage.act(Gdx.graphics.getDeltaTime());
        // sb.draw(bg, Gdx.graphics.getHeight() / 2 - bg.getWidth() + 200, Gdx.graphics.getWidth() - bg.getHeight() / 2);

        stage.draw();


        sb.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void dispose() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
