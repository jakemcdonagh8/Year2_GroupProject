/*
 * @(#) CardHolder.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs211.group07.system.resources;

import java.util.ArrayList;

import uk.ac.aber.cs211.group07.system.exceptions.EmptyPackException;

/**
* Characterises any class that can hold objects of type Card/CrewCard/ChanceCard
* @author Julia Wojciechowska
* @version 0.1 - 13/03/17 - initial creation /juw30
* @version 1.0 - 05/05/17 - Ready for initial release /haa14
*/
public interface CardHolder<T extends Card> {
	
	/**
	 * Adds card to the pack
	 */
	public void addCard(T card);
	
	/**
	 * Returns and removes card from the top
	 */
	public T draw() 
	throws EmptyPackException;
	
	/**
	 * Returns card from the top
	 */
	public T peek();
	
	/**
	 * Removes a specific card
	 */
	public void removeCard(T card);
	
	/**
	 * Returns Pack of Cards
	 * @return pack of cards
	 */
	public Pack<T> getPack();
	
	/**
	 * Returns pack of cards
	 */
	public ArrayList<T> getCards();
	
	/**
	 * Returns number of cards
	 */
	public int size();
	
	/**
	 * Sorts CrewCards increasingly (doesn't do anything if ChanceCard holder)
	 */
	public void sortCardsInc();
	
	/**
	 * Sorts CrewCards decreasingly (doesn't do anything if ChanceCard holder)
	 */
	public void sortCardsDec();


}
