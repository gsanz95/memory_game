package com.company;

import java.util.ArrayList;

class GameLogger {

    private static String[] playerNames = {"Hans", "Gretel"};

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

    void printCardsPlayed(int[] playerPositions, Card[] cardsPlayed){
        for(int i = 0; i < cardsPlayed.length; i++){
            System.out.println(playerNames[playerPositions[i]] + " plays " + cardsPlayed[playerPositions[i]].toString() + " as up card");
        }
    }

    void printRoundWinner(int winnerPosition){
        System.out.println(playerNames[winnerPosition] + " wins the round.");
    }

    void printWar(){
        System.out.println("War!");
    }

    void printGameWinner(int winnerPosition){
        System.out.println(playerNames[winnerPosition] + " wins!");
    }

    void printGameTie() { System.out.println("Tied Game!");}

}
