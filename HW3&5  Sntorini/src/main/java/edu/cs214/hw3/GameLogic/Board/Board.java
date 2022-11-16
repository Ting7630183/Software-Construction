package edu.cs214.hw3.GameLogic.Board;

import edu.cs214.hw3.GUI.Player;

import java.util.ArrayList;
import java.util.List;

public class Board {
    Field[][] cells;

    public Board(){
        cells = new Field[5][5];
        for(int row = 0; row < 5; row++){
            for(int col = 0; col < 5; col++){
                cells[row][col] = new Field(0, null);
            }
        }
    }

    /**get the height of the cell*/
    public int getHeight(int row, int col){
        Field cell = cells[row][col];
        return cell.getHeight();
    }

    /**get the player of the cell*/
    public Player getPlayer(int row, int col){
        Field cell = cells[row][col];
        return cell.getPlayer();
    }

    /**update the player in the cell*/
    public void updatePlayer(int row, int col, Player player){
        Field cell = cells[row][col];
        cell.updatePlayer(player);
    }

    /**add a block in the cell*/
    public boolean addBlock(int row, int col){
        Field cell = cells[row][col];
        return cell.addBlock();

    }
    /**add a dome in the cell*/
    public boolean addDome(int row, int col){
        Field cell = cells[row][col];
        return cell.addDome();
    }

    /**get the player's workers' position in the board*/
    public List<List<Integer>> getPlayerPosition(Player player){
        if(player == null){
            return new ArrayList<>();
        }
        List<List<Integer>> playerPosition = new ArrayList<>();
        for(int row = 0; row < 5; row++){
            for(int col = 0; col < 5; col++){
                if(getPlayer(row, col) != null && getPlayer(row, col).equals(player)){
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(row);
                    list.add(col);
                    playerPosition.add(list);
                }
            }
        }
        return playerPosition;
    }

    /** get the number of workers in the board*/
    public int getPlayerNumber(){
        int res = 0;
        for(int row = 0; row < 5; row++){
            for(int col = 0; col < 5; col++){
                if(getPlayer(row, col) != null){
                    res++;
                }
            }
        }
        return res;
    }
}
