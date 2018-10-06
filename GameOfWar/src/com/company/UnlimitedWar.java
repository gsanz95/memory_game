package com.company;

class UnlimitedWar {

    static int MAX_TIED_PLAYERS = 2;
    private static int numberOfPlayers = 2;
    private static GameLogger logger = new GameLogger();
    private static GameController gameControl = new GameController();
    private static PrepareGame gamePreparer = new PrepareGame();
    private int roundLimit;

    /**
     * Constructor
     *
     * @param maxRounds The number of max rounds
     */
    public UnlimitedWar(int maxRounds){
        this.roundLimit = maxRounds;
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

        int roundWinner;
        for(int i = 0; i < this.roundLimit; i++){
            roundWinner = playRound(playerDecks);
            logger.printRoundWinner(roundWinner - 1);
        }

        return playerDecks;
    }

    /**
     * Plays a round by getting a card from each player and finiding the winner.
     * If a war is started, the MAX_TIED_PLAYERS are determined.
     * Once tiedPlayers have been located they get more cards taken out of their
     * decks and compared until a winner is found.
     *
     * @param playerDecks
     * @return
     */
    private int playRound(Deck[] playerDecks) {
        Card[] cardsPlayed = gameControl.popPlayerCards(playerDecks, numberOfPlayers);

        int[] playerPositions = getPlayerPositions(cardsPlayed);

        logger.printCardsPlayed(playerPositions, cardsPlayed);

        int roundWinner = gameControl.determineRoundWinner(cardsPlayed);

        // Has a War been started?
        while (roundWinner == -1) {
            logger.printWar();
            int[] tiedPlayers = gameControl.determineTiedPlayers(cardsPlayed, MAX_TIED_PLAYERS);
            Deck[] tiedDecks = new Deck[MAX_TIED_PLAYERS];

            for (int i = 0; i < MAX_TIED_PLAYERS; i++)
                tiedDecks[i] = playerDecks[tiedPlayers[i]];

            cardsPlayed = gameControl.popPlayerCards(tiedDecks, MAX_TIED_PLAYERS);
            logger.printCardsPlayed(tiedPlayers, cardsPlayed);
            roundWinner = gameControl.determineRoundWinner(cardsPlayed);
        }

        addCardsToWinner(playerDecks, cardsPlayed, roundWinner);

        return roundWinner;
    }

    /**
     * Groups the position of each player and the players card.
     *
     * @param cardsPlayed Cards played this round
     * @return Position of a player's card in the cardsPlayed group.
     */
     private int[] getPlayerPositions(Card[] cardsPlayed){
        int[] playerPositions = new int[cardsPlayed.length];
        for(int i = 0; i < playerPositions.length; i++)
            playerPositions[i] = i;

        return playerPositions;
    }

    /**
     * Takes all the cards in the cards played pile and adds the to the winner's deck
     *
     * @param playerDecks Grouped decks containing the winner deck
     * @param cardsPlayed All cards to be put into the winning deck
     * @param roundWinner The location of the winner's deck inside playerDecks
     */
     private void addCardsToWinner(Deck[] playerDecks, Card[] cardsPlayed, int roundWinner){
        for(Card singleCard : cardsPlayed){
            playerDecks[roundWinner - 1].addCard(singleCard);
        }
    }
}
