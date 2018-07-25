/*
 * @(#) ChanceCard.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs211.group07.system.resources;


/**
 * Represents chance cards in the game
 * 
 * @author Kamil Cupial
 * @version 0.1 - 18/02/17 - initial creation /kac12
 * @version 1.0 - 05/05/17 - Ready for initial release /haa14
 */
public class ChanceCard extends Card{
	private int number; // number of the card (as specified in the requirements)
						// used to recognise cards according to their
						// functionality
	private String text; // text on the card (displaying purposes)
	private int value;

	/**
	 * ChanceCard constructor
	 * 
	 * @param number
	 *            number of the chance card (as in Req. Spec.)
	 * @param text
	 *            text on the card (for displaying to user)
	 */
	public ChanceCard(int number, String text) {
		this.number = number;
		this.text = text;
	}


	/**
	 * 
	 * @return text on the card
	 */
	public String getText() {
		return text;
	}

	/**
	 * 
	 * @return number of the chance card
	 */
	public int getNumber() {
		return number;
	}

	public int getValue() {
		return value;
	}

}
