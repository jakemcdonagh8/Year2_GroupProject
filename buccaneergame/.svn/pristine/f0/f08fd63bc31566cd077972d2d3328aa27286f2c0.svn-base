/*
 * @(#) PlayerPort.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs211.group07.system.resources;

/**
 * This class contains details of the player's port
 * @author Julia Wojciechowska
 * @version 0.1 - ??/03/17 - initial creation /juw30
 * @version 0.2 - 12/03/17 - setOwner sets home for the owner as well /kac12
 * @version 1.0 - 05/05/17 - Ready for initial release /haa14
 */
public class PlayerPort extends Port{
	
	Player owner;
	
	/**
	 * PlayerPort constructor
	 * @param name name of the Port
	 * @param position position of the Port
	 */
	public PlayerPort(String name, Position position) {
		super(name, position);
		this.name = name;
		this.position = position;
		owner = null;
	}
	
	/**
	 * Returns the owner of the Port
	 * @return owner
	 */
	public Player getOwner(){
		return owner;
	}
	
	/**
	 * Sets owner of the Port
	 * @param player - owner
	 */
	public void setOwner(Player player){
		this.owner = player;
	}
	
	public Position getPos(){
		return this.position;
	}

}
