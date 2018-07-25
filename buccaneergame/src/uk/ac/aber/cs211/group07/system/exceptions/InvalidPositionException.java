/*
 * @(#) InvalidPositionException.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs211.group07.system.exceptions;

/**
 * Thrown when a request to get an object from invalid position is made
 * @author Kamil Cupial
 * @version 0.1 - 12/03/17 - initial creation /kac12
 * @version 1.0 - 05/05/17 - Ready for initial release /haa14
 */
@SuppressWarnings("serial")
public class InvalidPositionException extends Exception{
	/**
	 * The exception default constructor
	 */
	public InvalidPositionException(){
		super("Position requested out of bounds.");
	}
}
