/*
 * @(#) CrewCard.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */
package uk.ac.aber.cs211.group07.system.resources;

/**
 * Represents crew cards in the game
 * @author Kamil Cupial
 * @version 0.1 - 18/02/17 - initial creation /kac12
 * @version 1.0 - 05/05/17 - Ready for initial release /haa14
 */
public class CrewCard extends Card {
	private boolean red; //true if red, false = black
	private int value; //strength of the card
	
	/**
	 * CrewCard constructor
	 * @param value	strength of the card
	 * @param isRed	whether the card is red or black
	 */
	public CrewCard(int value, boolean isRed){
		this.value = value;
		this.red = isRed;
	}
	
	/**
	 * Returns the strength of the card
	 * @return strength of the card
	 */
	public int getValue(){
		return value;
	}
	
	/**
	 * 
	 * @return whether the card is red
	 */
	public boolean isRed(){
		return red;
	}
	
	/**
	 * 
	 * @return whether the card is black
	 */
	public boolean isBlack(){
		return !red;
	}
	
}
