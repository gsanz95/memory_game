package com.company;

import java.util.EmptyStackException;
import java.util.HashMap;

public class GameController {

    static Card[] popPlayerCards(Deck[] playerDecks, int numberOfPlayers) throws EmptyStackException{
        Card[] cardsPlayed = new Card[numberOfPlayers];

        // Pop a card for each player
        for(int i = 0; i < numberOfPlayers; i++){
            cardsPlayed[i] = playerDecks[i].popCard();
        }

        return cardsPlayed;
    }

    int determineRoundWinner(Card[] cardsPlayed) {
        int roundWinner = -2;
        int numberOfPlayers = cardsPlayed.length;

        for(int i = 1; i < numberOfPlayers; i++){
            int compareResult = cardsPlayed[i-1].compareTo(cardsPlayed[i]);

            if(compareResult < 0){
                roundWinner = i + 1;
            }else if(compareResult > 0){
                roundWinner = i;
            }else{
                return -1;
            }
        }
        return roundWinner;
    }

    int[] determineTiedPlayers(Card[] cardsPlayed, int maxPlayersAllowed){
        int[] tiedPlayers = new int[maxPlayersAllowed];

        HashMap<Card, Integer> cardLocation = new HashMap<Card, Integer>();

        for(int i = 0; i < maxPlayersAllowed; i++) {
            if(cardLocation.containsKey(cardsPlayed[i])){
                tiedPlayers[0] = cardLocation.get(cardsPlayed[i]);
                tiedPlayers[1] = i;
            }

            cardLocation.put(cardsPlayed[i], i);
        }

        return tiedPlayers;
    }

    int determineGameWinner(Deck[] decksToCount){
        int gameWinner = -1;
        int numberOfPlayers = decksToCount.length;
        int compareResult;

        for(int i = 1; i < numberOfPlayers; i++){
            compareResult = Integer.compare(decksToCount[i-1].getSize(),decksToCount[i].getSize());

            if(compareResult < 0){
                gameWinner = i + 1;
            }else if(compareResult > 0){
                gameWinner = i;
            }else{
                return -1;
            }
        }

        return gameWinner;
    }
}
