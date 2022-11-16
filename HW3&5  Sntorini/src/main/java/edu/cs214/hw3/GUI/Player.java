package edu.cs214.hw3.GUI;

import edu.cs214.hw3.GameLogic.Decorator.GameLogic;

public class Player {
    private String name;
    private String godName;
    GameLogic gameLogic;

    public Player(String name, String godName, GameLogic gameLogic){
        this.name = name;
        this.godName = godName;
        this.gameLogic = gameLogic;
    }

    public String getPlayerName(){
        return this.name;
    }

    public String getGodName(){
        return this.godName;
    }

    public void setGodName(String name){
        this.name = name;
    }

}


