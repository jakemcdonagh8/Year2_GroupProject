/*
 * @(#) Board.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs211.group07.system;

import java.util.LinkedList;

import uk.ac.aber.cs211.group07.system.exceptions.InvalidPositionException;
import uk.ac.aber.cs211.group07.system.resources.ChanceCard;
import uk.ac.aber.cs211.group07.system.resources.CrewCard;
import uk.ac.aber.cs211.group07.system.resources.FlatIsland;
import uk.ac.aber.cs211.group07.system.resources.Island;
import uk.ac.aber.cs211.group07.system.resources.MapObject;
import uk.ac.aber.cs211.group07.system.resources.Pack;
import uk.ac.aber.cs211.group07.system.resources.PirateIsland;
import uk.ac.aber.cs211.group07.system.resources.Player;
import uk.ac.aber.cs211.group07.system.resources.Port;
import uk.ac.aber.cs211.group07.system.resources.Position;
import uk.ac.aber.cs211.group07.system.resources.Ship;
import uk.ac.aber.cs211.group07.system.resources.Treasure;
import uk.ac.aber.cs211.group07.system.resources.TreasureIsland;

/**
 * Stores information about current state of the game and objects in it
 * @author Kamil Cupial
 * @author Julia Wojciechowska 
 * @version 0.1 - 12/03/17 - initial creation (framework) /kac12
 * @version 0.2 - 15/03/17 - implemented all of the methods /juw30
 * @version 0.3 - 19/03/17 - added accounting for position shift (all positions are regarded to as 1-20 index in the game, 
 * 								whereas indexes in the array are 0-19)
 * 							+ Added variable initializers + adding objects to the actual board on creation /kac12
 * @version 0.31 - 19/03/17 - fixed Exception throwing /kac12
 * @version 0.32 - 19/03/17 - added hasShip(), hasPort(), hasIsland() methods + fixed adding big objects to the board /kac12
 * @version 0.33 - 19/03/17 - added position boundary check for has..() methods /kac12
 * @version 0.4	- 02/04/17 - added MoveMapObject() method; Bug fixes: initializing LinkedList array properly /kac12
 * @version 0.41 - 21/04/17 - fixed: getMapObject()/moveMapObject() index tracking; overloaded functions now delegate to
 * 								already existing functions, passing modified parameters (functions using Position objects 
 * 								now just references Position.x and y in corresponding functions taking two ints /kac12
 * @version 0.42 - 02/05/17 - Fixed comments to fit QA9 /haa14
 * @version 1.0 - 05/05/17 - Ready for initial release /haa14
 */
public class Board {
	private Player[] playerList;
	private Ship[] shipList;
	private Port[] portList;
	//private Pack<CrewCard> crewPack;
	//private Pack<ChanceCard> chancePack;
	private Island[] islandList;
	private Treasure[] treasureList;
	private LinkedList<MapObject>[][] board;
	
	/**
	 * Board constructor
	 * @param players 	players in the game (4)
	 * @param ports 	ports in the game (7)
	 * @param crew 		CrewCard pack, all crewcards on pirate island
	 * @param chance 	ChanceCard pack, all ChanceCards on the treasure island
	 * @param islands	All islands on the board
	 * @param treasures	All treasure pieces on the board
	 */
	@SuppressWarnings("unchecked")
	public Board(Player[] players, Port[] ports, Pack<CrewCard> crew, Pack<ChanceCard> chance, Island[] islands, Treasure[] treasures){
		this.playerList = players;
		shipList = new Ship[4];
		shipList[0] = players[0].getShip();
		shipList[1] = players[1].getShip();
		shipList[2] = players[2].getShip();
		shipList[3] = players[3].getShip();
		this.portList = ports;
		//this.crewPack = crew;
		//this.chancePack = chance;
		this.islandList = islands;
		this.treasureList = treasures;
		board = (LinkedList<MapObject>[][]) new LinkedList<?>[20][20];
		for (int i = 0; i < 20; i++){
			for (int j = 0; j < 20; j++){
				board[i][j] = new LinkedList<MapObject>();
			}
		}
		addToBoard();
	}
	
	/**
	 * Returns the player by its index
	 * @param index - Player's position in the array of players
	 * @return player - Player object with their related information.
	 */
	public Player getPlayer(int index){
		return playerList[index];
	}
	
	/**
	 * Returns the player's position by the player's number
	 * @param playerNum player's number
	 * @return player's position
	 */
	public Position getPlayerPos(int playerNum){
		return playerList[playerNum].getShip().getPos();
		
	}
	
	/**
	 * Returns the port
	 * @param index
	 * @return port
	 */
	public Port getPort(int index){
		return portList[index];
	}
	
	/**
	 * Returns player's port
	 * @param index
	 * @return player's port
	 */
	public Port getPlayerPort(int index){
		return playerList[index].getHomePort();
	}
	
	/**
	 * Returns the Island
	 * @param index
	 * @return Island
	 */
	public Island getIsland(int index){
		return islandList[index];
		
	}
	
	/**
	 * Return the treasure
	 * @param index
	 * @return treasure
	 */
	public Treasure getTreasure(int index){
		return treasureList[index];
	}
	
	/**
	 * Returns the Treasure Island
	 * @return Treasure Island
	 */
	public TreasureIsland getTreasureIsland(){
		TreasureIsland treasureIsland = null;
		for (Island island : islandList){
			if (island instanceof TreasureIsland) {
				treasureIsland = (TreasureIsland) island;
			}
		}
		return treasureIsland;	
	}
	
	/**
	 * Returns the Flat Island
	 * @return Flat Island
	 */
	public FlatIsland getFlatIsland(){
		FlatIsland flatIsland = null;
		for (Island island : islandList){
			if (island instanceof FlatIsland) {
				flatIsland = (FlatIsland) island;
			}
		}
		return flatIsland;	
	}
	
	/**
	 * Returns the Pirate Island
	 * @return Pirate Island
	 */
	public PirateIsland getPirateIsland(){
		PirateIsland pirateIsland = null;
		for (Island island : islandList){
			if (island instanceof PirateIsland) {
				pirateIsland = (PirateIsland) island;
			}
		}
		return pirateIsland;	
	}
	
	/**
	 * Returns the Object by the position
	 * @param pos - position
	 * @return object
	 * @throws InvalidPositionException
	 */
	public LinkedList<MapObject> getObjectByPos(Position pos) 
	throws InvalidPositionException{
		return getObjectByPos(pos.x, pos.y);
	}
	
	/**
	 * Returns the object by the position
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @return object
	 * @throws InvalidPositionException
	 */
	public LinkedList<MapObject> getObjectByPos(int x, int y) 
	throws InvalidPositionException{
		x--; y--;
		if (x >= 0 && x <= 19 && y >= 0 && y <= 19)
			return board[x][y];
		else throw new InvalidPositionException();
	}
	
	/**
	 * Adds an object to the board
	 * @param object 
	 */
	public void addMapObject(MapObject object)
	throws InvalidPositionException{
		int coord_x = object.getPos().x-1;
		int coord_y = object.getPos().y-1;
		if (coord_x >= 0 && coord_x <= 19 && coord_y >= 0 && coord_y <= 19)
			board[coord_x][coord_y].add(object);
		else
			throw new InvalidPositionException();
	}
	
	/**
	 * Removes an object from the board
	 * @param object
	 */
	public void removeMapObject(MapObject object)
	throws InvalidPositionException{
		int coord_x = object.getPos().x-1;
		int coord_y = object.getPos().y-1;
		if (coord_x >= 0 && coord_x <= 19 && coord_y >= 0 && coord_y <= 19)
			board[coord_x][coord_y].remove(object);
		else
			throw new InvalidPositionException();
	}
	
	/**
	 * Checks if there's a Ship at a given tile
	 * @param pos	Position object to check
	 * @return hasShip - true if at least one ship present, false otherwise
	 */
	public boolean hasShip(Position pos){
		return hasShip(pos.x, pos.y);
	}
	
	/**
	 * Checks if there's a Ship at a given tile
	 * @param x	x index to check
	 * @param y y index to check
	 * @return	true if at least one ship present, false otherwise
	 */
	public boolean hasShip(int x, int y){
		x--;
		y--;
		if (x >= 0 && x <= 19 && y >= 0 && y <= 19){
			for (MapObject o : board[x][y]){
				if (o instanceof Ship){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks if there's a Port at a given tile
	 * @param pos	Position object to check
	 * @return	true if at least one port present, false otherwise
	 */
	public boolean hasPort(Position pos){
		return hasPort(pos.x, pos.y);
	}
	
	/**
	 * Checks if there's a Port at a given tile
	 * @param x	x index to check
	 * @param y y index to check
	 * @return true if at least one port present, false otherwise
	 */
	public boolean hasPort(int x, int y){
		x--;
		y--;
		if (x >= 0 && x <= 19 && y >= 0 && y <= 19){
			for (MapObject o : board[x][y]){
				if (o instanceof Port){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks if there's an Island at a given tile
	 * @param pos	Position object to check
	 * @return hasIsland - true if an Island tile present, false otherwise
	 */
	public boolean hasIsland(Position pos){
		return hasIsland(pos.x, pos.y);
	}
	
	/**
	 * Checks if there's an Island at a given tile
	 * @param x	x index to check
	 * @param y y index to check
	 * @return	true if an Island tile present, false otherwise
	 */
	public boolean hasIsland(int x, int y){
		x--;
		y--;
		if (x >= 0 && x <= 19 && y >= 0 && y <= 19){
			for (MapObject o : board[x][y]){
				if (o instanceof Island){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Moves a given MapObject to a new Position on the board
	 * Can be used with some chance cards (blowing the ship to a certain position)
	 * @param object	object to move (e.g. a Ship)
	 * @param newPos	new position of the object
	 * @throws InvalidPositionException	If the new position is invalid
	 */
	public void moveMapObject(MapObject object, Position newPos)
	throws InvalidPositionException{
		int x = newPos.x-1;
		int y = newPos.y-1;
		if (x >= 0 && x <= 19 && y >= 0 && y <= 19){
			this.removeMapObject(object);
			object.setPos(newPos.x, newPos.y);
			this.addMapObject(object);
		} else {
			throw new InvalidPositionException();
		}
	}
	
	/**
	 * Adds all the objects in the lists to the actual
	 * Array of LinkedList objects
	 */
	private void addToBoard(){
		try {
			for (Ship s : shipList){
				addMapObject(s);
			}
			for (Port p : portList){
				addMapObject(p);
			}
			for (Island i : islandList){
				for (int x = i.getPos().x; x <= i.getPos().x + i.getSize().x; x++){
					for (int y = i.getPos().y; y <= i.getPos().y + i.getSize().y; y++){
						board[x-1][y-1].add(i);
					}
				}
			}
		} catch(InvalidPositionException ex){
			ex.printStackTrace();
		}
	}
	
	
}
