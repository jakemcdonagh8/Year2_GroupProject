/*
 * @(#) Ship.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs211.group07.system.resources;

import java.util.ArrayList;

import uk.ac.aber.cs211.group07.system.exceptions.TooMuchTreasureException;

/**
 * Provides functionality for ships
 * @author Julia Wojciechowska
 * @version 0.1 - 09/03/17 - initial creation /juw30
 * @version 0.2 - 12/03/17 - Added default orientation + setHome method /kac12
 * @version 0.3 - 25/03/17 - Added initializers in the constructor /kac12
 * @version 0.31 - 25/03/17 - Fixed last treasure removal (index - 1), added proper exception throwing when
 * 								adding treasure /kac12
 * @version 0.32 - 03/05/17 - Fixed isAtHomePort() /kac12
 * @version 1.0 - 05/05/17 - Ready for initial release /haa14
 */

public class Ship implements MapObject, TreasureHolder {
	
	Player owner;
	private Position position;
	private Position size;
	private Position home;
	private Direction orientation;
	private ArrayList<Treasure> treasure; 
	private int maxTreasureCount = 2;
	
	/**
	 * Constructor of Ship
	 * @param owner - owner of the Ship
	 */
    public Ship(Player owner) {
		this.owner = owner;
		orientation = Direction.N;
		treasure = new ArrayList<Treasure>();
		position = new Position(-1,-1);
		size = new Position(-1,-1);
		home = new Position(-1,-1);
	} 
    
    /**
     * Returns the orientation of the ship
     * @return
     */
	public Direction getOrientation(){
		return orientation;
	}
	
	/**
	 * Sets the new orientation of the ship
	 * @param newOrient
	 */
	public void setOrientation(Direction newOrient) {
		this.orientation = newOrient;
	}
	
	/**
	 * Returns details of the owner of the ship
	 * @return
	 */
	public Player getOwner(){
		return owner;
	}
	
	/**
	 * Sets the new direction of the ship
	 * @param direction
	 */
	public void turn(Direction direction){
		this.orientation = direction;
		
	}
	
	/**
	 * Returns if the ship is at its Home Port
	 * @return true if at Home Port if not the return false
	 */
	public boolean isAtHomePort(){
		return (position.equals(home));		
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
	public void addTreasure(Treasure treasure) 
	throws TooMuchTreasureException {
		if (this.treasure.size() >= maxTreasureCount){
			throw new TooMuchTreasureException();
		} else {
			this.treasure.add(treasure);
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
	 * Sets the position of the ship
	 */
	public void setPos(Position pos) {
		this.position = pos;
	}

	/**
	 * Sets the position of the ship
	 */
	public void setPos(int x, int y) {
		this.position.x = x;
		this.position.y = y;
	}

	/**
	 * Returns the position of 
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
	 * Sets the homePort position of the ship
	 * @param pos	Position of the home port
	 */
	public void setHome(Position pos){
		home = pos;
	}
}
