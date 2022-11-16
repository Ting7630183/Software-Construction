package edu.cs214.hw3.GUI;

import edu.cs214.hw3.GameLogic.Board.Board;
import edu.cs214.hw3.GameLogic.Decorator.GameLogic;

public class Game {
    private Board board;
    private Player nextPlayer;
    private Player currentPlayer;
    private GameLogic currentGameLogic;
    private GameLogic nextGameLogic;
    private String message;
    private String nextAction;
    private Player winner;

    /**Game constructor*/
    public Game(){
        this.board = new Board();
        this.currentPlayer = null;
        this.nextPlayer = null;
        this.currentGameLogic = null;
        this.nextGameLogic = null;
        this.message = null;
        this.nextAction = null;
        this.winner = null;
    }

    /**set the message indicating valid or invalid move or build*/
    public void setMessage(String message){
        this.message = message;
    }

    /**set the next available action*/
    public void setNextAction(String nextAction){
        this.nextAction = nextAction;
    }

    /** get the next available action*/
    public String getNextAction(){
        return this.nextAction;
    }

    /**get the message indicating valid or invalid move or build*/
    public String getMessage(){
        return this.message;
    }

    /** get the game's board*/
    public Board getBoard(){
        return this.board;
    }

    /**get current player*/
    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }

    /** set current player*/
    public void setCurrentPlayer(Player player){
        currentPlayer = player;
    }

    /**set next player in the game*/
    public void setNextPlayer(Player player){nextPlayer = player;}

    /**get next player in the game*/
    public Player getNextPlayer(){
        return this.nextPlayer;
    }

    /**set current game logic according to current player's god card*/
    public void setCurrentGameLogic(){
        currentGameLogic = currentPlayer.gameLogic;
    }

    /**set next game logic according to next player's god card*/
    public void setNextGameLogic(){
        nextGameLogic = nextPlayer.gameLogic;
    }

    /**put player's workers  in the board*/
    public void setWorkerPosition(int row, int col, Player player){
        this.board.updatePlayer(row, col, player);
    }

    /**switch turn in the game*/
    public void switchTurn(){
        Player temp = currentPlayer;
        currentPlayer = nextPlayer;
        nextPlayer = temp;
        GameLogic temp1 = currentGameLogic;
        currentGameLogic = nextGameLogic;
        nextGameLogic = temp1;
    }

    /**move the worker in the board*/
    public void moveInGame(int x, int y, int[] direction){
        checkAndMove(x, y, direction);
    }

    private void checkAndMove(int x, int y, int[] direction){
        if(!currentGameLogic.checkMoveValidity(x, y, direction, board, currentPlayer, nextPlayer)||(currentGameLogic.moveUp(x, y, direction, board) && nextGameLogic.opponentCannotMove())){
            setMessage("Invalid move!");
        }else{
            currentGameLogic.move(x, y, direction, board, currentPlayer, nextPlayer);
            setMessage("Valid move!");
        }
    }


    /**build a block or a dome in the board*/
    public void buildInGame(int x, int y, int[] direction, boolean block, int time){
        checkAndBuild(x, y, direction, block, time);
    }

    private void checkAndBuild( int x, int y, int[] direction, boolean block, int time){
        if(currentGameLogic.checkBuildValidity(x, y, direction, block, board, time)){
            currentGameLogic.build(x, y, direction, block, board);
            setMessage("Valid build!");
        }else{
            setMessage("Invalid build!");
        }
    }

    /**get the winner in the game*/
    public void checkWinnerInGame(int row, int col, int[] direction){
        if(currentGameLogic.checkWinner(row, col, direction, board)){
            this.winner = currentPlayer;
        }
    }

    public Player getWinner(){
        return this.winner;
    }







}
