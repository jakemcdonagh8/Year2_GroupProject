/*
 * @(#) TreasureHolder.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs211.group07.system.resources;

import java.util.ArrayList;

import uk.ac.aber.cs211.group07.system.exceptions.TooMuchTreasureException;

/**
 * Interface for every class able to hold Treasure objects.
 * @author Kamil Cupial
 * @version 0.1 - 18/02/17 - initial creation /kac12
 * @version 1.0 - 05/05/17 - Ready for initial release /haa14
 */
public interface TreasureHolder {

	/**
	 * Sets a maximum number of Treasure objects a class can hold
	 * @param count	the limit of objects
	 */
	public void setMaxTreasureCount(int count);
	
	/**
	 * 
	 * @return maximum number of treasure objects a class can store
	 */
	public int getMaxTreasureCount();
	
	/**
	 * Adds treasure to the container class
	 * @param treasure	Treasure object to add
	 */
	public void addTreasure(Treasure treasure) throws TooMuchTreasureException;
	
	/**
	 * Removes a Treasure object from the container
	 * @param treasure	Treasure item to remove
	 */
	public void removeTreasure(Treasure treasure);
	
	/**
	 * Removes first treasure from the container (combine with sortTreasureInc/Dec)
	 * @return a Treasure object
	 */
	public Treasure removeFirstTreasure();
	
	/**
	 * Removes last treasure from the container (combine with sortTreasureInc/Dec)
	 * @return a Treasure object
	 */
	public Treasure removeLastTreasure();
	
	/**
	 * Gets current number of Treasure objects stored
	 * @return number of treasure stored
	 */
	public int getTreasureNum();
	
	/**
	 * Returns all the Treasure objects stored
	 * @return ArrayList of Treasure stored
	 */
	public ArrayList<Treasure> getTreasureList();
	
	/**
	 * Sorts treasure pieces according to their value, increasingly
	 */
	public void sortTreasureInc();
	
	/**
	 * Sorts treasure pieces according to their value, decreasingly
	 */
	public void sortTreasureDec();
}
