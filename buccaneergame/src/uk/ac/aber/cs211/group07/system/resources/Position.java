/*
 * @(#) Position.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */


package uk.ac.aber.cs211.group07.system.resources;

/**
 * A class to store two integers as coordinates (or size) refered to as X and Y at once.
 * @author Kamil Cupial
 * @version 0.1 - 17/02/17 - initial creation /kac12
 * @version 0.2 - 17/02/17 - modified default constructor /kac12
 * @version 0.3 - 18/02/17 - fixed comments /kac12
 * @version 0.4 - 02/04/17 - fixed equals method /kac12
 * @version 0.5 - 02/04/17 - Added methods for get/set x and y values /haa14
 * @version 0.51 - 05/04/17 - Commented to meet QA9 /haa14
 * @version 1.0 - 05/05/17 - Ready for initial release /haa14
 */
public class Position {
	public int x;
	public int y;
	
	/**
	 * Default constructor
	 */
	public Position(){
		x = 0;
		y = 0;
	}
	
	/**
	 * Initialised constructor
	 * @param x	initial value of x
	 * @param y	initial value of y
	 */
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Set new location with a given X and Y value
	 * @param x
	 * @param y
	 */
	public void setNewPos(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Get X value
	 * @return X
	 */
	public int getX() {
		return x;
	}

	/**
	 * Set new X value
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Get Y value
	 * @return Y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Set new Y value
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Equals method for the Position class
	 * @param pos2	second Position object to compare
	 * @return true if equal, false if not
	 */
	public boolean equals(Position pos2){
		return (x == pos2.x && y == pos2.y);
	}

}
