package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.management.InvalidAttributeValueException;
import java.util.ArrayList;

public class GameTests {

    @Test
    void CardEqualsTest()
    {
        Card card1 = new Card(12, Suit.CLOVERS);
        Card card2 = new Card(12, Suit.HEARTS);
        Card card3 = new Card(11, Suit.DIAMONDS);

        Assertions.assertTrue(card1.equals(card2));
        Assertions.assertFalse(card1.equals(card3));
    }

    @Test
    void InvalidAttributeTest(){

        int numberOfPlayers = 1;
        PrepareGame gamePreparer = new PrepareGame();
        Deck mainDeck = new Deck("");

        try {
            Deck[] playerCards = gamePreparer.splitDeck(mainDeck, numberOfPlayers);
            Assertions.fail();
        } catch (InvalidAttributeValueException e){
            Assertions.assertEquals("Number of players cannot be less than 1: " + numberOfPlayers, e.getMessage());
        }

    }

    @Test
    void determineTiedPlayersTest(){
        int numberOfPlayers = 2;
        ArrayList<Card> sampleCards = new ArrayList<>();
        GameController gControl = new GameController();

        sampleCards.add(new Card(1, Suit.HEARTS));
        sampleCards.add(new Card(1, Suit.DIAMONDS));

        int[] realOutput = new int[]{0, 1};
        int[] output = gControl.determineTiedPlayers(sampleCards, numberOfPlayers);
        Assertions.assertArrayEquals(output, realOutput);
    }
}
