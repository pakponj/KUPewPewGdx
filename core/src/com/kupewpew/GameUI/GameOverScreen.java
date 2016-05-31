package com.kupewpew.GameUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
public class GameOverScreen implements Screen {


    private final Image toMenu;
    SpriteBatch sb;
    StringBuilder scoreBuilder;
    private Stage stage;
    private Texture bg;
    private Image retry;


    public GameOverScreen()
    {
        scoreBuilder = new StringBuilder("Score : ");
        stage = new Stage();
        Texture retryText = new Texture(Gdx.files.internal("retry.jpg"));
        Texture toMenuText = new Texture((Gdx.files.internal("tomenu.jpg")));
        bg = new Texture(Gdx.files.internal("gameover.jpg"));
        Image back = new Image(bg);

        retry = new Image(retryText);
        retry.setX(Gdx.graphics.getWidth() / 2 - retryText.getWidth() / 4);
        retry.setY(Gdx.graphics.getHeight() / 10 - retryText.getHeight() / 2 + 200);
        retry.setWidth(400);
        retry.setHeight(200);

        back.setPosition(Gdx.graphics.getHeight() / 2 - bg.getWidth() + 200, Gdx.graphics.getWidth() - bg.getHeight() / 2);

        Gdx.input.setInputProcessor(stage);

        toMenu = new Image(toMenuText);
        toMenu.setX(Gdx.graphics.getWidth() / 2 - toMenuText.getWidth() / 4);
        toMenu.setY(Gdx.graphics.getHeight() / 10 - toMenuText.getHeight() / 2);
        toMenu.setWidth(400);
        toMenu.setHeight(200);

        Label text;
        Label.LabelStyle textStyle;
        BitmapFont font = new BitmapFont();
        textStyle = new Label.LabelStyle();
        textStyle.font = font;
        text = new Label("GameOver",textStyle);
        text.setFontScale(7);
        text.setColor(Color.WHITE);
        text.setText("Score\n"+Player.getInstance().getScore());
        text.setX(Gdx.graphics.getWidth() / 2 - 50);
        text.setY(Gdx.graphics.getHeight() / 2 - retryText.getHeight() / 2);

        Label score;
        score = new Label("score",textStyle);
        score.setFontScale(7);
        score.setColor(Color.WHITE);
        score.setText(""+Player.getInstance().getScore());
        score.setX((Gdx.graphics.getWidth() / 2 ));
        score.setY(Gdx.graphics.getHeight() / 2 - retryText.getHeight() / 2 - 150);


        stage.addActor(back);
        stage.addActor(retry);
        stage.addActor(toMenu);
        stage.addActor(text);

        retry.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                Gdx.app.log("Clicked", "Clicked");
                Game.startGame = true;
                Player.getInstance().reset();
                ScreenManager.setScreen(Game.getInstance());
            }
        });
        toMenu.addListener(new ClickListener(){
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                Gdx.app.log("Clicked", "Clicked");
                ScreenManager.setScreen(new GameStartScreen());
                Game.startGame = true;
                Player.getInstance().reset();
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
