package com.kupewpew.GameUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kupewpew.Models.Player;


/**
 * Created by Administrator on 30/5/2559.
 */
public class GameStartScreen implements Screen {

    SpriteBatch sb;

    private Stage stage;
    private Texture bg;
    private Image img;


    public GameStartScreen()
    {

        stage = new Stage();

        Texture startgame = new Texture(Gdx.files.internal("startgame.jpg"));
        bg = new Texture(Gdx.files.internal("MainMenu.jpg"));
        Image back = new Image(bg);

        img = new Image(startgame);
        img.setX(Gdx.graphics.getWidth() / 2 - startgame.getWidth() / 4);
        img.setY(Gdx.graphics.getHeight() / 3 - startgame.getHeight() / 2);
        img.setWidth(400);
        img.setHeight(200);

        back.setPosition(Gdx.graphics.getHeight() / 2 - bg.getWidth() + 200, Gdx.graphics.getWidth() - bg.getHeight() / 2);

        Gdx.input.setInputProcessor(stage);

        //text goes here


        stage.addActor(back);
        stage.addActor(img);



        img.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                Gdx.app.log("Clicked", "Clicked");
                ScreenManager.setScreen(Game.getInstance());
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
        this.sb = sb;
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
