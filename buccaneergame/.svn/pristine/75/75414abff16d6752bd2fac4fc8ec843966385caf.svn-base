/*
 * @(#) Treasure.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs211.group07.system.resources;

import uk.ac.aber.cs211.group07.system.exceptions.TooMuchTreasureException;

/**
 * Stores data equal to one piece of treasure.
 * @author Kamil Cupial
 * @version 0.1 - 18/02/17 - initial creation /kac12
 * @version 0.2 - ??/03/17 - TreasureType changes /haa14
 * @version 0.3 - 18/03/17 - TreasureType constructor fix /kac12
 * @version 0.4 - 25/03/17 - Treasure now adds itself to the owner when created /kac12
 * @version 0.41 - 05/05/17 - Modified order & comments to comply with QA9
 * @version 1.0 - 05/05/17 - Ready for initial release /haa14
 */
public class Treasure {
	
	private int value;	//Value of the treasure piece
	private TreasureType type;	//name of the treasure e.g. Diamond, Ruby etc. (purely cosmetic)
	private TreasureHolder owner;	//Current owner of the treasure

	/**
	 * Treasure constructor
	 * @param name	name of the treasure e.g. Diamond, Ruby etc. (purely cosmetic)
	 * @param value	value of the treasure piece
	 * @param owner	initial owner of the treasure
	 */
	public Treasure(TreasureType name, int value, TreasureHolder owner){
		this.value = value;
		this.type = name;
		this.owner = owner;
		try {
			owner.addTreasure(this);
		} catch (TooMuchTreasureException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets a new owner of the treasure
	 * @param owner	the new owner
	 */
	public void setOwner(TreasureHolder owner){
		this.owner.removeTreasure(this);
		this.owner = owner;
		try {
			owner.addTreasure(this);
		} catch (TooMuchTreasureException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets current owner
	 * @return current owner of the treasure
	 */
	public TreasureHolder getOwner(){
		return owner;
	}
	
	/**
	 * @return	treasure value
	 */
	public int getValue(){
		return value;
	}
	
	/**
	 * 
	 * @return	treasure type/name
	 */
	public TreasureType getType(){
		return type;
	}
}
