package com.company;

import java.util.ArrayList;

class UnlimitedWar {

    private static int MAX_TIED_PLAYERS = 2;
    private int numberOfPlayers;
    private static GameLogger logger = new GameLogger();
    private static GameController gameControl = new GameController();
    private static PrepareGame gamePreparer = new PrepareGame();
    private int roundLimit;

    /**
     * Constructor
     *
     * @param maxRounds The number of max rounds
     */
    public UnlimitedWar(int maxRounds, int numberOfPlayers){
        this.roundLimit = maxRounds;
        this.numberOfPlayers = numberOfPlayers;
    }

    /**
     * Starts the game, splits the deck and plays game rounds
     * roundLimit times. It then returns decks to be counted for scoring.
     *
     * @param mainDeck A deck class containing all the cards in the game
     * @return A group of decks pertaining to each player
     */
    Deck[] startGame(Deck mainDeck){
        Deck[] playerDecks;

        // Try to split deck
        try {
            playerDecks = gamePreparer.splitDeck(mainDeck, numberOfPlayers);
        }catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }

        for(int i = 0; i < this.roundLimit; i++){
            tryPlayRound(playerDecks);

            if(isAnyDeckEmpty(playerDecks)) break;
        }

        return playerDecks;
    }

    private void tryPlayRound(Deck[] playerDecks)throws EmptyDeckException{
        try{
            playRound(playerDecks);
        }catch (EmptyDeckException e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * Plays a round by getting a card from each player and finding the winner.
     * If a war is started, playWarRound is called to determine the winner.
     *
     * @param playerDecks Decks for each player
     * @return Position of the winner
     */
    private void playRound(Deck[] playerDecks) throws EmptyDeckException {
        ArrayList<Card> cardsPlayed = gameControl.popPlayerCards(playerDecks, numberOfPlayers);

        logger.printCardsPlayed(playerDecks, cardsPlayed);

        Deck roundWinner = gameControl.determineRoundWinner(playerDecks, cardsPlayed);

        // Has a War been started?
        if(roundWinner == null){
            try{
                 roundWinner = playWarRound(playerDecks, cardsPlayed);
            }catch (EmptyDeckException e){
                throw new EmptyDeckException(e.getMessage());
            }
        }

        addCardsToWinner(roundWinner, cardsPlayed);
        logger.printRoundWinner(roundWinner);
    }


    /**
     * Plays extra rounds of war until a winner has been decided.
     *
     *
     * @param playerDecks Decks for all the players
     * @param cardsPlayed previously played cards in the round the function is called
     * @return The position of the actual winner of the round
     */
    private Deck playWarRound(Deck[] playerDecks, ArrayList<Card> cardsPlayed) throws EmptyDeckException{
        if(isAnyDeckEmpty(playerDecks)) {
            throw new EmptyDeckException("War stopped: Empty Deck");
        }

        Deck roundWinner = null;
        Deck[] tiedDecks = new Deck[MAX_TIED_PLAYERS];

        while (roundWinner == null) {
            logger.printWar();
            int[] tiedPlayers = gameControl.determineTiedPlayers(cardsPlayed, MAX_TIED_PLAYERS);

            for (int i = 0; i < MAX_TIED_PLAYERS; i++)
                tiedDecks[i] = playerDecks[tiedPlayers[i]];


            ArrayList<Card> warCards = gameControl.popPlayerCards(tiedDecks, MAX_TIED_PLAYERS);
            logger.printCardsPlayed(tiedDecks, warCards);

            roundWinner = gameControl.determineRoundWinner(tiedDecks, warCards);
            cardsPlayed.addAll(warCards);
        }
        return roundWinner;
    }

    /**
     * Takes all the cards in the cards played pile and adds the to the winner's deck
     *
     * @param winnerDeck Deck where the cards will be added into
     * @param cardsPlayed All cards to be put into the winning deck
     */
     private void addCardsToWinner(Deck winnerDeck, ArrayList<Card> cardsPlayed){
        for(Card singleCard : cardsPlayed){
            winnerDeck.addCard(singleCard);
        }
    }

    private boolean isAnyDeckEmpty(Deck[] decksToCheck){
         for(Deck singleDeck: decksToCheck){
             if(singleDeck.isEmpty()) return true;
         }

         return false;
    }
}
