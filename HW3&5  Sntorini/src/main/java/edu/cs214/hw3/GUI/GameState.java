
package edu.cs214.hw3.GUI;

import edu.cs214.hw3.GameLogic.Board.Board;

import java.util.Arrays;

public class GameState {

    private final String instructions;
    private final Cell[] cells;

    private GameState(String instructions, Cell[] cells) {
        this.instructions = instructions;
        this.cells = cells;
    }

    /**update game state*/
    public static GameState forGame(Game game) {
        Cell[] cells = getCells(game);
        String instructions = getInstructions(game);
        return new GameState(instructions, cells);
    }

    public String getInstructions() {
        return this.instructions;
    }

    public Cell[] getCells() {
        return this.cells;
    }

    @Override
    public String toString() {
        return "GameState[" +
                "instructions=" + this.instructions + ", " +
                "cells=" + Arrays.toString(this.cells) + ']';
    }

    /**get instructions from the game including current player, next player, winner, valid move or build*/
    private static String getInstructions(Game game) {
        String instructions = null;
        if (game.getWinner() != null) {
            instructions = "Player " + (game.getWinner().getPlayerName()) + " has won!";
        } else {
            String currentPlayerName = "None";
            String nextPlayerName = "None";
            Player currentPlayer = game.getCurrentPlayer();
            Player nextPlayer = game.getNextPlayer();
            String nextAction = game.getNextAction();
            if(currentPlayer != null) {
                currentPlayerName = currentPlayer.getPlayerName();
            }
            if(nextPlayer != null){
                nextPlayerName = nextPlayer.getPlayerName();
            }
            String message = game.getMessage();
            instructions = "Current turn:" + currentPlayerName + ";"+ "Next turn:" + nextPlayerName + ";" +"Valid/Invalid:" + message + ";"+ "Next Action:" + nextAction;
        }
        return instructions;
    }

    /**get information of all cells*/
    private static Cell[] getCells(Game game) {
        Cell []cells = new Cell[25];
        Board board = game.getBoard();
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                String text = "";
                String link = "";
                String clazz = "";
                int height= game.getBoard().getHeight(x, y);

                Player player = board.getPlayer(x, y);
                if(player != null){
                    String playerName = player.getPlayerName();
                    text = playerName;
                }

                link = "/play?x=" + y + "&y=" + x;
                cells[5 * x + y] = new Cell(text, clazz, link, height);
            }
        }
        return cells;
    }
}

class Cell {
    private final String text;
    private final String clazz;
    private final String link;
    private final int height;

    Cell(String text, String clazz, String link, int height) {
        this.text = text;
        this.clazz = clazz;
        this.link = link;
        this.height = height;
    }

    public String getText() {
        return this.text;
    }

    public String getClazz() {
        return this.clazz;
    }

    public String getLink() {
        return this.link;
    }

    public int getHeight(){
        return this.height;
    }

    @Override
    public String toString() {
        return "Cell[" +
                "text=" + this.text + ", " +
                "clazz=" + this.clazz + ", " +
                "link=" + this.link +
                "height=" + this.height +
                ']';
    }
}

