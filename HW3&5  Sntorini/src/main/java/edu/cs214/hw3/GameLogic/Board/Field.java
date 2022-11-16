package edu.cs214.hw3.GameLogic.Board;

import edu.cs214.hw3.GUI.Player;

public class Field {
    private int height;
    private Player player;


    public Field(int height, Player player){
        this.height = height;
        this.player = player;
    }

    /**
     * Get the height of the field to see how many blocks and dome are in this field.
     * @return the height of the field
     */
    public int getHeight(){
        return this.height;
    }


    /**
     * Set the player in the field
     * @param Player
     */
    public void updatePlayer(Player player){
        this.player = player;
    }
    /**
     * Get the player in the field
     * @return Player
     */
    public Player getPlayer(){
        return this.player;
    }

    /**
     * Add a block to the field, if success, increment the height
     * @return boolean value indicate whether the add success or not
     */
    public boolean addBlock(){
        if(this.height < 3){
            this.height += 1;
            return true;
        }else{
            return false;
        }
    }
    /**
     * Add a dome to the field, if success, increment the height
     * @return boolean value indicate whether the add success or not
     */
    public boolean addDome(){
        if(this.height == 3){
            this.height += 1;
            return true;
        }
        return false;
    }



}
