package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class GameController {

    static ArrayList<Card> popPlayerCards(Deck[] playerDecks, int numberOfPlayers) {
        ArrayList<Card> cardsPlayed = new ArrayList<>();

        // Pop a card for each player
        for(int i = 0; i < numberOfPlayers; i++){
            cardsPlayed.add(playerDecks[i].popCard());
        }

        return cardsPlayed;
    }

    Deck determineRoundWinner(Deck[] decks, ArrayList<Card> cardsPlayed) {
        int highestValue = cardsPlayed.get(0).getRank();
        int numberOfPlayers = cardsPlayed.size();
        Deck roundWinner = decks[0];

        for(int i = 1; i < numberOfPlayers; i++){
            int rankToCompare = cardsPlayed.get(i).getRank();

            if(highestValue < rankToCompare){
                highestValue = rankToCompare;
                roundWinner = decks[i];
            }else if(highestValue == rankToCompare){
                return null;
            }
        }
        return roundWinner;
    }

    int[] determineTiedPlayers(ArrayList<Card> cardsPlayed, int maxPlayersAllowed){
        int[] tiedPlayers = new int[maxPlayersAllowed];

        HashMap<Card, Integer> cardLocation = new HashMap<Card, Integer>();

        for(int i = 0; i < cardsPlayed.size(); i++) {
            if(cardLocation.containsKey(cardsPlayed.get(i))){
                tiedPlayers[0] = cardLocation.get(cardsPlayed.get(i));
                tiedPlayers[1] = i;
                break;
            }

            cardLocation.put(cardsPlayed.get(i), i);
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
