
package edu.cs214.hw3;
import java.io.IOException;
import java.util.Map;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import edu.cs214.hw3.GUI.Game;
import edu.cs214.hw3.GUI.GameState;
import edu.cs214.hw3.GUI.Player;
import edu.cs214.hw3.GameLogic.Decorator.DefaultGameLogic;
import edu.cs214.hw3.GameLogic.GodCards.*;
import fi.iki.elonen.NanoHTTPD;

public class App extends NanoHTTPD {
    public static void main(String[] args) {
        try {
            new App();
        } catch (IOException ioe) {
            System.err.println("Couldn't start server:\n" + ioe);
        }
    }
    private int originalRow = 0;
    private int originalCol = 0;
    private int xx, yy;
    private int buildRow;
    private int buildCol;
    private String currentAction = "move";
    private Game game;
    private Template template;
    private int buildTime = 1;

    public App() throws IOException {
        super(8080);

        this.game = new Game();
        Handlebars handlebars = new Handlebars();
        this.template = handlebars.compile("main");

        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        System.out.println("\nRunning! Point your browsers to http://localhost:8080/ \n");
    }

    public String getNextAction(){
        if(this.currentAction.equals("move")){
            return "build";
        }else{
            return "move";
        }
    }

    public String getCurrentAction(){
        return this.currentAction;
    }

    @Override
    public Response serve(IHTTPSession session) {
        try {
            String uri = session.getUri();
            Map<String, String> params = session.getParms();


            if (uri.equals("/newgame")) {
                this.game = new Game();
                this.originalRow = 0;
                this.originalCol = 0;
                this.currentAction = "move";
                xx = 0;
                yy = 0;
                buildRow = 0;
                buildCol = 1;
                buildTime = 1;


                /**select godCard*/
            } else if (uri.equals("/godCardPan") || uri.equals("/godCardMinotaur") || uri.equals("/godCardAthena")||
                    uri.equals("/godCardDemeter") || uri.equals("/godCardApollo")) {
                Player player = null;
                if(uri.equals("/godCardPan")){
                    player = new Player("Pan", "Pan", new GameLogicPan(new DefaultGameLogic()));
                }else if(uri.equals("/godCardMinotaur")){
                    player = new Player("Minotaur", "Minotaur", new GameLogicMinotaur(new DefaultGameLogic()));
                }else if(uri.equals("/godCardAthena")){
                    player = new Player("Athena", "Athena", new GameLogicAthena(new DefaultGameLogic()));
                }else if(uri.equals("/godCardDemeter")){
                    player = new Player("Demeter", "Demeter", new GameLogicDemeter(new DefaultGameLogic()));
                }else{
                    player = new Player("Apollo", "Apollo", new GameLogicApollo(new DefaultGameLogic()));
                }
                if(game.getCurrentPlayer() == null){ /**set current player and set current game logic*/
                    game.setCurrentPlayer(player);
                    game.setCurrentGameLogic();

                }else{ /**set next player and set next game logic*/
                    game.setNextPlayer(player);
                    game.setNextGameLogic();
                }

            } else if (uri.equals("/play")) {
                int x = Integer.parseInt(params.get("x"));
                int y = Integer.parseInt(params.get("y"));

                int playerNumberOnTheBoard = game.getBoard().getPlayerNumber();


                if(playerNumberOnTheBoard < 2) { /**set worker place of the first player.*/
                    Player currentPlayer = game.getCurrentPlayer();
                    game.setWorkerPosition(y, x, currentPlayer);
                }else if(playerNumberOnTheBoard == 2 || playerNumberOnTheBoard == 3){/**set worker place of the second player.*/
                    Player nextPlayer = game.getNextPlayer();
                    game.setWorkerPosition(y, x, nextPlayer);

                }else if(playerNumberOnTheBoard == 4) {

                    Player player = game.getBoard().getPlayer(y, x);

                    if (player != null && player.equals(game.getCurrentPlayer())) {
                        originalRow = y;
                        originalCol = x;

                    } else {
                        if (currentAction.equals("move")) {/**move the worker in the game and set the next action*/
                            game.setNextAction("build");
                            game.moveInGame(originalRow, originalCol, new int[]{y, x});
                            currentAction = "build";
                            game.checkWinnerInGame(originalRow, originalCol, new int[]{y, x});
                        }else if(currentAction.equals("build")){
                            buildCol = x;
                            buildRow = y;

                        }
                    }
                }
            }else if(uri.equals("/block")){/**build a block the game and set the next action*/

                if(game.getCurrentPlayer().getPlayerName().equals("Demeter")){
                    if(buildTime == 1) {
                        game.buildInGame(originalRow, originalCol, new int[]{buildRow, buildCol}, true, 1);
                        game.checkWinnerInGame(originalRow, originalCol, new int[]{buildRow, buildCol});
                        buildTime = buildTime+1;

                    }else if(buildTime == 2){
                        game.buildInGame(originalRow, originalCol, new int[]{buildRow, buildCol}, true, 2);
                        game.checkWinnerInGame(originalRow, originalCol, new int[]{buildRow, buildCol});
                        buildTime = buildTime-1;
                        game.setNextAction("move");
                        currentAction = "move";
                        game.switchTurn();
                    }

                }else {
                    game.buildInGame(originalRow, originalCol, new int[]{buildRow, buildCol}, true, 1);
                    game.setNextAction("move");
                    currentAction = "move";
                    game.checkWinnerInGame(originalRow, originalCol, new int[]{buildRow, buildCol});
                    game.switchTurn();
                }

            }else if (uri.equals("/dome")){/**build a dome the game and set the next action*/
                if(game.getCurrentPlayer().getPlayerName().equals("Demeter")){
                    if(buildTime == 1) {
                        game.buildInGame(originalRow, originalCol, new int[]{buildRow, buildCol}, false, 1);
                        game.checkWinnerInGame(originalRow, originalCol, new int[]{buildRow, buildCol});
                        buildTime = buildTime+1;

                    }else if(buildTime == 2){
                        game.buildInGame(originalRow, originalCol, new int[]{buildRow, buildCol}, false, 2);
                        game.checkWinnerInGame(originalRow, originalCol, new int[]{buildRow, buildCol});
                        buildTime = buildTime-1;
                        game.setNextAction("move");
                        currentAction = "move";
                        game.switchTurn();
                    }

                }else {
                    game.buildInGame(originalRow, originalCol, new int[]{buildRow, buildCol}, false, 1);
                    game.setNextAction("move");
                    currentAction = "move";
                    game.checkWinnerInGame(originalRow, originalCol, new int[]{buildRow, buildCol});
                    game.switchTurn();
                }
            }
            /** Extract the view-specific data from the game and apply it to the template.*/
            GameState gameplay = GameState.forGame(this.game);
            String HTML = this.template.apply(gameplay);
            return newFixedLengthResponse(HTML);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}