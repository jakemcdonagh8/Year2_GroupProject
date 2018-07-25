/*
 * @(#) PirateIsland.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs211.group07.system.resources;

import java.util.ArrayList;

import uk.ac.aber.cs211.group07.system.exceptions.EmptyPackException;

/**
 * Provides functionality for Pirate Island
 * @author Julia Wojciechowska
 * @version 0.1 - 06/03/17 - initial creation /juw30
 * @version 0.11 - 05/05/17 - modified comments to comply with QA9
 * @version 1.0 - 05/05/17 - Ready for initial release /haa14
 */

public class PirateIsland extends Island 
implements MapObject, CardHolder<CrewCard> {
	
	private Pack<CrewCard> cards;
	
	/**
	 * PirateIsland constructor
	 * @param position position of Pirate Island
	 * @param size size of Pirate Island
	 */
	public PirateIsland(Position position, Position size){
		super();
		setPos(position);
		setSize(size);
		cards = new Pack<CrewCard>();
	}
	
	/**
	 * Returns Pack of Crew Cards
	 * @return pack of crew cards
	 */
	public Pack<CrewCard> getPack(){
		return cards;
	}
		
	/**
	 * Sets the position of Pirate Island
	 */
	public void setPos(Position pos) {
		this.position = pos;

	}
	
	/**
	 * Sets the position of Pirate Island
	 */
	public void setPos(int x, int y) {
		this.position.x = x;
		this.position.y = y;

	}

	/**
	 * Returns the position of Pirate Island
	 */
	public Position getPos() {
		return position;
	}

	/**
	 * Returns the size of Pirate Island
	 */
	public Position getSize() {
		return size;
	}

	/**
	 * Sets the size of Pirate Island
	 */
	public void setSize(Position size) {
		this.size = size;
	}

	/**
	 * Sets the size of Pirate Island
	 */
	public void setSize(int x, int y) {
		this.size.x = x;
		this.size.y = y;
	}
	
	/**
	 * Returns pack of cards
	 */
	public ArrayList<CrewCard> getCards() {
		return cards.getCards();
	}
		
	/**
	 * Adds card to the pack
	 */
	public void addCard(CrewCard card) {
		cards.add(card);	
	}

	/**
	 * Returns and removes card from the top
	 */
	public CrewCard draw() 
	throws EmptyPackException {
		return cards.removeCard();
	}
	
	/**
	 * Returns card from the top
	 */
	public CrewCard peek() {
		return cards.getCards().get(0);
	}
	
	/**
	 * Removes a specific card
	 */
	public void removeCard(CrewCard card) {
		cards.getCards().remove(card);
		
	}
	
	/**
	 * Returns number of cards
	 */
	public int size() {
		return cards.size();
	}
	
	/**
	 * Sorts Crew Cards according to their value, increasingly
	 */
	public void sortCardsInc(){
		int size = this.cards.size();
		boolean swapped = true;
		do {
			swapped = false;
		for (int index = 0; index < size-1; index++){
			CrewCard c1 = this.cards.getCards().get(index);
			CrewCard c2 = this.cards.getCards().get(index+1);
			if (c1.getValue() < c2.getValue()){		
			} else {
				this.cards.getCards().set(index, c2);
				this.cards.getCards().set(index+1, c1);
				swapped = true;
			}
		}
		} while (swapped);
	}
	
	/**
	 * Sorts Crew Cards according to their value, decreasingly
	 */
	public void sortCardsDec() {
		int size = this.cards.size();
		boolean swapped = true;
		do {
			swapped = false;
			for (int index = 0; index < size-1; index++){
				CrewCard c1 = this.cards.getCards().get(index);
				CrewCard c2 = this.cards.getCards().get(index+1);
				if (c1.getValue() > c2.getValue()){
				} else {
					this.cards.getCards().set(index, c2);
					this.cards.getCards().set(index+1, c1);
					swapped = true;
				}
				
			}
		} while (swapped);
	}


}
