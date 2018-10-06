package com.company;

import java.util.ArrayList;

class GameLogger {

    /**
     * Prints all the cards inside of the deck passed.
     *
     * @param cardDeck Deck class containing the card classes
     */
    void printDeck(Deck cardDeck){
        ArrayList<Card> cards = cardDeck.getCards();
        int cardCount = 0;
        for (Card card : cards) {
            System.out.println(card.toString());
            cardCount += 1;
        }

        System.out.println("Total Cards: " + cardCount);
    }

    void printGameStart(String gameMode){
        System.out.println();
        System.out.println("Starting: " + gameMode);
    }

    void printCardsPlayed(Deck[] tiedDecks, ArrayList<Card> cardsPlayed){
        for(int i = 0; i < tiedDecks.length; i++){
            System.out.println(tiedDecks[i].getOwnerName() + " plays " + cardsPlayed.get(i).toString() + " as up card");
        }
    }

    void printRoundWinner(Deck winnerDeck){
        System.out.println(winnerDeck.getOwnerName() + " wins the round.");
    }

    void printWar(){
        System.out.println("War!");
    }

    void printGameWinner(Deck winnerDeck){
        System.out.println(winnerDeck.getOwnerName() + " wins!");
    }

    void printGameTie() { System.out.println("Tied Game!");}

}
