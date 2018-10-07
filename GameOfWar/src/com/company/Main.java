package com.company;

/**
 *  Main class for the Game of War
 *
 * @author Giancarlo Sanz
 * @version 1.0
 */
public class Main {

    private static int MAX_ROUNDS = 50;
    private static PrepareGame gamePreparer = new PrepareGame();
    private static GameController gameControl = new GameController();
    private static GameLogger logger = new GameLogger();

    /**
     * Main method
     */
    public static void main(String[] args){

        Deck mainDeck = gamePreparer.makeDeck();

        //playUnlimitedWar(mainDeck);
        playThreePlayerWar(mainDeck);
        playClassicWar(mainDeck);
    }

    private static void playUnlimitedWar(Deck mainDeck){
        int numberOfPlayers = 2;
        logger.printGameStart("Unlimited War");

        UnlimitedWar warGame = new UnlimitedWar(MAX_ROUNDS, numberOfPlayers);
        Deck[] decksToCount = warGame.startGame(mainDeck);
        finalizeGame(decksToCount);
    }

    private static void finalizeGame(Deck[] decksToCount){
        int winningPlayerNumber = gameControl.determineGameWinner(decksToCount);

        if(winningPlayerNumber == -1){
            logger.printGameTie();
        }else {
            logger.printGameWinner(decksToCount[winningPlayerNumber - 1]);
        }
    }

    private static void playThreePlayerWar(Deck mainDeck) {
        int numberOfPlayers = 3;

        logger.printGameStart("Three Player War");
        UnlimitedWar warGame = new UnlimitedWar(MAX_ROUNDS,numberOfPlayers);
        Deck[] decksToCount = warGame.startGame(mainDeck);
        finalizeGame(decksToCount);
    }

    static void playClassicWar(Deck mainDeck){
        //ClassicWar warGame = new ClassicWar();
        //Deck[] decksToCount = ;
        //finalizeGame(decksToCount);
    }
}
