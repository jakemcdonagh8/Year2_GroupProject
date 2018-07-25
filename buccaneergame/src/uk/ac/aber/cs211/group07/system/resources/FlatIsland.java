/*
 * @(#) FlatIsland.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs211.group07.system.resources;

import java.util.ArrayList;

import uk.ac.aber.cs211.group07.system.exceptions.EmptyPackException;
import uk.ac.aber.cs211.group07.system.exceptions.TooMuchTreasureException;

/**
 * Provides functionality for Flat Island
 * @author Julia Wojciechowska
 * @version 0.1 - 06/03/17 - initial creation /juw30
 * @version 1.0 - 05/05/17 - Ready for initial release /haa14
 */

public class FlatIsland extends Island 
implements TreasureHolder, CardHolder<CrewCard>{
	
	private Pack<CrewCard> cards;
	private ArrayList<Treasure> treasure;
	private int maxTreasureCount;
	
	/**
	 * FlatIsland constructor
	 * @param position position of Flat Island
	 * @param size size of Flat Island
	 */
	public FlatIsland(Position position, Position size){
		super();
		setPos(position);
		setSize(size);
		maxTreasureCount = 20;
		cards = new Pack<CrewCard>();
		treasure = new ArrayList<Treasure>();
	}
	
	/**
	 * Sets the position of Flat Island
	 */
	public void setPos(Position pos) {
		this.position = pos;
	}

	/**
	 * Sets the position of Flat Island
	 */
	public void setPos(int x, int y) {
		this.position.x = x;
		this.position.y = y;
	}

	/**
	 * Returns the position of Flat Island
	 */
	public Position getPos() {
		return position;
	}

	/**
	 * Return the size of Flat Island
	 */
	public Position getSize() {
		return size;
	}
	
	/**
	 * Gets current number of Treasure objects stored
	 * @return number of treasure stored
	 */
	public int getTreasureNum(){
		return this.treasure.size();
	}

	/**
	 * Sets the size of the Flat Island
	 */
	public void setSize(Position size) {
		this.size = size;
	}
	
	/** 
	 * Sets the size of Flat Island
	 */
	public void setSize(int x, int y) {
		this.size.x = x;
		this.size.y = y;
	}
	
	/**
	 * Sets a maximum number of Treasure objects a class can hold
	 * @param count	the limit of objects
	 */
	public void setMaxTreasureCount(int count) {
		this.maxTreasureCount += count;
	}
	
	/**
	 * 
	 * @return maximum number of treasure objects a class can store
	 */
	public int getMaxTreasureCount(){
		return maxTreasureCount;
	}

	/**
	 * Returns all the Treasure objects stored
	 * @return ArrayList of Treasure stored
	 */
	public ArrayList<Treasure> getTreasureList(){
		return this.treasure;
	}
	
	/**
	 * Adds card to the pack
	 */
	public void addCard(CrewCard card) {
		cards.add(card);	
	}

	/**
	 * Returns Pack of Crew Cards
	 * @return pack of crew cards
	 */
	public Pack<CrewCard> getPack(){
		return cards;
	}
	
	/**
	 * Returns pack of cards
	 */
	public ArrayList<CrewCard> getCards() {
		return cards.getCards();
	}
	
	/**
	 * Returns number of cards
	 */
	public int size() {
		return cards.size();
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

	/**
	 * Sorts treasure pieces according to their value, increasingly
	 */
	public void sortTreasureInc(){
		int size = this.treasure.size();
		boolean swapped = true;
		do {
			swapped = false;
		for (int index = 0; index < size-1; index++){
			Treasure t1 = this.treasure.get(index);
			Treasure t2 = this.treasure.get(index+1);
			if (t1.getValue() < t2.getValue()){		
			} else {
				this.treasure.set(index, t2);
				this.treasure.set(index+1, t1);
				swapped = true;
			}
		}
		} while (swapped);
	}
	
	/**
	 * Sorts treasure pieces according to their value, decreasingly
	 */
	public void sortTreasureDec() {
		int size = this.treasure.size();
		boolean swapped = true;
		do {
			swapped = false;
			for (int index = 0; index < size-1; index++){
				Treasure t1 = this.treasure.get(index);
				Treasure t2 = this.treasure.get(index+1);
				if (t1.getValue() > t2.getValue()){
				} else {
					this.treasure.set(index, t2);
					this.treasure.set(index+1, t1);
					swapped = true;
				}
				
			}
		} while (swapped);
	}
	
	/**
	 * Adds treasure to the container class
	 * @param treasure	Treasure object to add
	 */
	public void addTreasure(Treasure treasure) 
	throws TooMuchTreasureException {
		if (this.treasure.size() < maxTreasureCount) {
		this.treasure.add(treasure);
		} else {
			throw new TooMuchTreasureException();
		}
	}
	
	/**
	 * Removes a Treasure object from the container
	 * @param treasure	Treasure item to remove
	 */
	public void removeTreasure(Treasure treasure){
		this.treasure.remove(treasure);
	}
	
	/**
	 * Removes first treasure from the container (combine with sortTreasureInc/Dec)
	 * @return a Treasure object
	 */
	public Treasure removeFirstTreasure(){
		return this.treasure.remove(0);
	}
	
	/**
	 * Removes last treasure from the container (combine with sortTreasureInc/Dec)
	 * @return a Treasure object
	 */
	public Treasure removeLastTreasure(){		
		return this.treasure.remove(this.treasure.size()-1);
	}
	

}