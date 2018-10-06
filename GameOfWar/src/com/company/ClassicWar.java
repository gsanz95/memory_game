package com.company;

public class ClassicWar {

    private static int MAX_TIED_PLAYERS = 2;
    private int numberOfPlayers;
    private static GameLogger logger = new GameLogger();
    private static GameController gameControl = new GameController();
    private static PrepareGame gamePreparer = new PrepareGame();

    Deck[] startGame(Deck mainDeck, int numberOfPlayers){
        Deck[] playerDecks;

        // Try to split deck
        try {
            playerDecks = gamePreparer.splitDeck(mainDeck, numberOfPlayers);
        }catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }

        while(!isAnyDeckEmpty(playerDecks)){
            // TO-DO Game round

        }

        return playerDecks;
    }

    private boolean isAnyDeckEmpty(Deck[] decksToCheck){
        for(Deck singleDeck: decksToCheck){
            if(singleDeck.isEmpty()) return true;
        }

        return false;
    }
}
