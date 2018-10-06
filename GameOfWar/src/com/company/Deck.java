package com.company;

import java.util.ArrayList;

/**
 *  Deck class contains a group of card classes
 */
class Deck {
    String ownerName;
    ArrayList<Card> cards;

    /**
     * Constructor
     */
    public Deck(String ownerName) {
        this.cards = new ArrayList<Card>();
        this.ownerName = ownerName;
    }

    // Accessors

    public ArrayList<Card> getCards(){
        return this.cards;
    }

    public String getOwnerName() {
        return ownerName;
    }

    /**
     * This method adds the cardToAdd into the cards ArrayList
     *
     * @param cardToAdd Card class to be added to cards ArrayList
     */
    public void addCard(Card cardToAdd) {
        this.cards.add(cardToAdd);
    }

    /**
     * Checks if the ArrayList cards in this class is empty
     *
     * @return Whether or not the ArrayList cards is empty
     */
    boolean isEmpty(){
        return this.cards.isEmpty();
    }

    /**
     * Removes and returns a card from the ArrayList cards at index 1
     *
     * @return The card removed from cards
     */
    Card popCard(){
        return this.cards.remove(0);
    }

    /**
     * Gets the size of the ArrayList and returns it.
     *
     * @return Size of ArrayList
     */
    int getSize(){
        return cards.size();
    }

}
