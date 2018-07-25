/*
 * @(#) TooMuchTreasureException.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs211.group07.system.exceptions;

/**
 * Exception thrown when trying to add more treasure than the holder can have
 * @author Kamil Cupial
 * @version 0.1 - 18/02/17 - initial creation /kac12
 * @version 1.0 - 05/05/17 - Ready for initial release /haa14
 */

@SuppressWarnings("serial")
public class TooMuchTreasureException extends Exception{

	/**
	 * The exception default constructor
	 */
	public TooMuchTreasureException(){
		super("Treasure can't be added - the container is full.");
	}
}
