package com.kupewpew.Models;

import com.badlogic.gdx.Gdx;

import java.util.Observable;

/**
 * Created by Administrator on 31/5/2559.
 */
public class Score extends Observable {

    private int score;
    private boolean update;

    public Score() {
        update = false;
        this.score = 0;
    }
    public void ready()
    {
        update = true;
    }

    public void updateScore(int score) {
        if(update){
            this.score += score;
            Gdx.app.log("Score", ""+score);
            update = false;
        }
        setChanged();
        notifyObservers( this.score );
    }
    public int getScore()
    {
        return this.score;
    }

}
