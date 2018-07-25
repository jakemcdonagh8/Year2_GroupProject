/*
 * @(#) TreasureIsland.java 1.0 2017/05/05
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
 * Provides functionality of Treasure Island (managing treasure,
 * accepting/rejecting treasure request)
 * 
 * @author Julia Wojciechowska
 * @version 0.1 - 06/03/17 - initial creation /juw30
 * @version 0.2 - 18/03/17 - added TreasureHolder interface
 * @version 0.3 - 15/04/17 - commented out setMaxTreasureCoun(1) (line 130)
 *          /kac12
 * @version 0.31 - 15/04/17 - fixed sortTreasure methods /kac12
 * @version 0.4 - 05/05/17 - Added sortTreasureDec and sortTreasureInc /haa14
 * @version 0.41 - 05/05/17 - Commented and edited to match QA9
 * @version 1.0 - 05/05/17 - Ready for initial release /haa14
 */

public class TreasureIsland extends Island implements CardHolder<ChanceCard>, TreasureHolder {

	private ArrayList<Treasure> treasure;
	private int maxTreasureCount;
	private Pack<ChanceCard> chanceCards;

	/**
	 * Constructor of Treasure Island
	 * 
	 * @param position
	 *            position of Treasure Island
	 * @param size
	 *            size of Treasure Island
	 */
	public TreasureIsland(Position position, Position size) {
		super();
		this.position = position;
		setSize(size);
		maxTreasureCount = 20;
		treasure = new ArrayList<Treasure>();
		chanceCards = new Pack<ChanceCard>();
	}

	/**
	 * Returns Pack of Chance Cards
	 * 
	 * @return
	 */
	public Pack<ChanceCard> getPack() {
		return chanceCards;
	}

	/**
	 * Sets the position of Treasure Island
	 */
	public void setPos(Position pos) {
		this.position = pos;
	}

	/**
	 * Sets the position of Treasure Island
	 */
	public void setPos(int x, int y) {
		this.position.x = x;
		this.position.y = y;
	}

	/**
	 * Returns the position of Treasure Island
	 */
	public Position getPos() {
		return position;
	}

	/**
	 * Return the size of Treasure Island
	 */
	public Position getSize() {
		return size;
	}

	/**
	 * Returns pack of cards
	 */
	public ArrayList<ChanceCard> getCards() {
		return chanceCards.getCards();
	}

	/**
	 * Sets the size of the Treasure Island
	 */
	public void setSize(Position size) {
		this.size = size;
	}

	/**
	 * Sets the size of Treasure Island
	 */
	public void setSize(int x, int y) {
		this.size.x = x;
		this.size.y = y;
	}

	/**
	 * Sets a maximum number of Treasure objects a class can hold
	 * 
	 * @param count
	 *            the limit of objects
	 */
	public void setMaxTreasureCount(int count) {
		this.maxTreasureCount = count;
	}

	/**
	 * 
	 * @return maximum number of treasure objects a class can store
	 */
	public int getMaxTreasureCount() {
		return maxTreasureCount;
	}

	/**
	 * Gets current number of Treasure objects stored
	 * 
	 * @return number of treasure stored
	 */
	public int getTreasureNum() {
		return this.treasure.size();
	}

	/**
	 * Returns all the Treasure objects stored
	 * 
	 * @return ArrayList of Treasure stored
	 */
	public ArrayList<Treasure> getTreasureList() {
		return this.treasure;
	}

	/**
	 * Sorts treasure pieces according to their value, increasingly
	 */
	public void sortTreasureInc() {
		int size = this.treasure.size();
		boolean swapped = true;
		do {
			swapped = false;
			for (int index = 0; index < size - 1; index++) {
				Treasure t1 = this.treasure.get(index);
				Treasure t2 = this.treasure.get(index + 1);
				if (t1.getValue() <= t2.getValue()) {
				} else {
					this.treasure.set(index, t2);
					this.treasure.set(index + 1, t1);
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
			for (int index = 0; index < size - 1; index++) {
				Treasure t1 = this.treasure.get(index);
				Treasure t2 = this.treasure.get(index + 1);
				if (t1.getValue() >= t2.getValue()) {
				} else {
					this.treasure.set(index, t2);
					this.treasure.set(index + 1, t1);
					swapped = true;
				}

			}
		} while (swapped);
	}

	/**
	 * Sorts Crew Cards according to their value, increasingly
	 */
	public void sortCardsInc() {
		int size = this.treasure.size();
		boolean swapped = true;
		do {
			swapped = false;
			for (int index = 0; index < size - 1; index++) {
				Treasure t1 = this.treasure.get(index);
				Treasure t2 = this.treasure.get(index + 1);
				if (t1.getValue() < t2.getValue()) {
				} else {
					this.treasure.set(index, t2);
					this.treasure.set(index + 1, t1);
					swapped = true;
				}
			}
		} while (swapped);
	}

	/**
	 * Sorts Crew Cards according to their value, decreasingly
	 */
	public void sortCardsDec() {
		int size = this.treasure.size();
		boolean swapped = true;
		do {
			swapped = false;
			for (int index = 0; index < size - 1; index++) {
				Treasure t1 = this.treasure.get(index);
				Treasure t2 = this.treasure.get(index + 1);
				if (t1.getValue() > t2.getValue()) {
				} else {
					this.treasure.set(index, t2);
					this.treasure.set(index + 1, t1);
					swapped = true;
				}

			}
		} while (swapped);
	}

	/**
	 * Returns and removes card from the top
	 * 
	 * @throws EmptyPackException
	 */
	public ChanceCard draw() 
		throws EmptyPackException {
		if (!chanceCards.isEmpty()) {
			return chanceCards.removeCard();
		} else {
			throw new EmptyPackException();
		}
	}

	/**
	 * Returns card from the top
	 */
	public ChanceCard peek() {
		return chanceCards.getCards().get(0);
	}

	/**
	 * Returns number of cards
	 */
	public int size() {
		return chanceCards.size();
	}

	/**
	 * Adds Chance Cards to the bottom of the Pack
	 * 
	 * @param card
	 *            - Chance Card to insert
	 */
	public void addCard(ChanceCard card) {
		chanceCards.add(card);

	}

	/**
	 * Removes card from the Pack of Chance Cards
	 * 
	 * @param card
	 *            - Chance Card to be removed
	 */
	public void removeCard(ChanceCard card) {
		chanceCards.removeCard(card);
	}

	/**
	 * Adds treasure to the container class
	 * 
	 * @param treasure
	 *            Treasure object to add
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
	 * 
	 * @param treasure
	 *            Treasure item to remove
	 */
	public void removeTreasure(Treasure treasure) {
		this.treasure.remove(treasure);
	}

	/**
	 * Removes first treasure from the container (combine with
	 * sortTreasureInc/Dec)
	 * 
	 * @return a Treasure object
	 */
	public Treasure removeFirstTreasure() {
		return this.treasure.remove(0);
	}

	/**
	 * Removes last treasure from the container (combine with
	 * sortTreasureInc/Dec)
	 * 
	 * @return a Treasure object
	 */
	public Treasure removeLastTreasure() {
		return this.treasure.remove(this.treasure.size() - 1);
	}

}
