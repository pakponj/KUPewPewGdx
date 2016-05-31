package com.kupewpew.Models;

import com.kupewpew.GameUI.Game;

import java.util.Observable;

/**
 * Created by Administrator on 31/5/2559.
 */
public class Score extends Observable {

    private int score;
    private boolean update;

    public Score()
    {
        update = false;
        this.score = 0;
        //this.addObserver(Game.getInstance());
    }
    public void ready()
    {
        update = true;
    }

    public void updateScore()
    {
        if(update == true){
            this.score +=1;
            update = false;
        }
        setChanged();
        notifyObservers();
    }
    public int getScore()
    {
        return this.score;
    }

}
