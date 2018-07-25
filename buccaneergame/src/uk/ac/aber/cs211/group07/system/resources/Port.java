  /*
     * @(#) Port.java 1.1 2017/03/09
     *
     * Copyright (c) 2002 University of Wales, Aberystwyth.
     * All rights reserved.
     *
     */

package uk.ac.aber.cs211.group07.system.resources;

import java.util.ArrayList;

import uk.ac.aber.cs211.group07.system.exceptions.EmptyPackException;
import uk.ac.aber.cs211.group07.system.exceptions.TooMuchTreasureException;

/**
 * Port class contains variables/methods common for every type of port
 * @author Julia Wojciechowska
 * @version 0.1 - 09/03/17 - initial creation /juw30
 * @version 0.2 - 18/03/17 - added initializers to objects in the constructor, maxTreasureCount = 20 (no limit) /kac12
 * @version 0.3 - 21/04/17 - changed treasure arraylist to protected (so it gets inherited),
 * @version 1.0 - 05/05/17 - Ready for initial release /haa14							
 */


public class Port 
implements TreasureHolder, MapObject, CardHolder<Card>{

	protected String name;
	private ArrayList<Card> cards;
	protected ArrayList<Treasure> treasure;
	private int maxTreasureCount;
	protected Position position;
	protected Position size;
	
	/**
	 * Port constructor
	 * @param name name of the Port
	 * @param position position of the Port
	 */
	public Port(String name, Position position) {
		this.name = name;
		this.position = position;
		maxTreasureCount = 20;
		cards = new ArrayList<Card>();
		treasure = new ArrayList<Treasure>();
		size = new Position(1,1);
	}
	
	/**
	 * Returns name of the Port
	 * @return name of the Port
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Returns cards
	 * @return cards
	 */
	public ArrayList<Card> getCards(){
		return cards;
	}
	
	/**
	 * Adds cards
	 * @param card card to be added
	 */
	public void addCard(Card card){
		cards.add(card);
	}
	
	/**
	 * Removes cards
	 * @param card card to be removed
	 */
	public void removeCard(Card card){
		cards.remove(card);
	}
	
	/**
	 * Sets the position of the Port
	 */
	public void setPos(Position pos) {
		this.position = pos;
	}

	/**
	 * Sets the position of the Port
	 */
	public void setPos(int x, int y) {
		this.position.x = x;
		this.position.y = y;
	}

	/**
	 * Returns the position of the Port
	 */
	public Position getPos() {
		return position;
	}

	/**
	 * Return the size of the Port
	 */
	public Position getSize() {
		return size;
	}
	
	/**
	 * Sets the size of the Port
	 */
	public void setSize(Position size) {
		this.size = size;
	}
	
	/** 
	 * Sets the size of the Port
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
		this.maxTreasureCount = count;
	}
	
	/**
	 * 
	 * @return maximum number of treasure objects a class can store
	 */
	public int getMaxTreasureCount(){
		return maxTreasureCount;
	}
	
	/**
	 * Adds treasure to the container class
	 * @param treasure	Treasure object to add
	 */
	public void addTreasure(Treasure treasure) {
		this.treasure.add(treasure);
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
	
	/**
	 * Gets current number of Treasure objects stored
	 * @return number of treasure stored
	 */
	public int getTreasureNum(){
		return this.treasure.size();
	}
	
	/**
	 * Returns all the Treasure objects stored
	 * @return ArrayList of Treasure stored
	 */
	public ArrayList<Treasure> getTreasureList(){
		return this.treasure;
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
	 * Returns card from the top
	 */
	public Card peek() {
		return cards.get(0);
	}
	
	
	/**
	 * Returns number of cards
	 */
	public int size() {
		return cards.size();
	}

	/**
	 * Removes card from the top
	 * @throws EmptyPackException 
	 */
	public Card draw() throws EmptyPackException {
			if (cards.size() != 0) {
				return cards.remove(0);
			} else {
				throw new EmptyPackException();
			}		
	}

	public Pack<Card> getPack() {
		return null;
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
			if (this.cards.get(index) instanceof CrewCard && this.cards.get(index+1) instanceof CrewCard) {
			CrewCard c1 = (CrewCard) this.cards.get(index);
			CrewCard c2 = (CrewCard) this.cards.get(index+1);
			if (c1.getValue() < c2.getValue()){		
			} else {
				this.cards.set(index, c2);
				this.cards.set(index+1, c1);
				swapped = true;
			}
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
				if (this.cards.get(index) instanceof CrewCard && this.cards.get(index+1) instanceof CrewCard) {
				CrewCard c1 = (CrewCard) this.cards.get(index);
				CrewCard c2 = (CrewCard) this.cards.get(index+1);
				if (c1.getValue() > c2.getValue()){
				} else {
					this.cards.set(index, c2);
					this.cards.set(index+1, c1);
					swapped = true;
				}
				}
			}
		} while (swapped);
	}
}
