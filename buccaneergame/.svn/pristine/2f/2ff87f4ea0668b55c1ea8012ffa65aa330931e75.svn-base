/*
 * @(#) GameEngine.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs211.group07.system;

import java.util.ArrayList;
import java.util.LinkedList;

import uk.ac.aber.cs211.group07.system.exceptions.InvalidDirectionException;
import uk.ac.aber.cs211.group07.system.exceptions.InvalidMoveException;
import uk.ac.aber.cs211.group07.system.exceptions.InvalidPositionException;
import uk.ac.aber.cs211.group07.system.exceptions.InvalidStateException;
import uk.ac.aber.cs211.group07.system.exceptions.NullStateException;
import uk.ac.aber.cs211.group07.system.resources.Direction;
import uk.ac.aber.cs211.group07.system.resources.FlatIsland;
import uk.ac.aber.cs211.group07.system.resources.MapObject;
import uk.ac.aber.cs211.group07.system.resources.Player;
import uk.ac.aber.cs211.group07.system.resources.PlayerPort;
import uk.ac.aber.cs211.group07.system.resources.Port;
import uk.ac.aber.cs211.group07.system.resources.Position;
import uk.ac.aber.cs211.group07.system.resources.Ship;
import uk.ac.aber.cs211.group07.system.resources.Treasure;
import uk.ac.aber.cs211.group07.system.resources.TreasureIsland;

/**
 * Provides all functionality required to create, store and manipulate data in a single Buccaneer game
 * @author Julia Wojciechowska 
 * @version 0.1 - 15/03/17 - initial creation (framework) + classes except GameEngine,
 * 																	 initGame, getState and setState /juw30
 * @version 0.2 - 19/03/17 - Added missing methods, comments, fixed errors, added crossesShipOnMove(), isValidTurn(), isValidMove() /kac12
 * @version 0.3 - 25/03/17 - Added getFightLoser(), isValidRetreatingMove() /kac12
 * @version 0.31 - 25/03/17 - Added incCurrentPlayer() /kac12
 * @version 0.32 - 30/03/17 - Changed order in getPlayerDestinationObject() to favour islands over ships (even though player shouldn't be let to land on a ship),
 * 								improved isValidMove() /kac12
 * @version 0.33 - 02/04/17 - Added Exception handling for Board.GetMapObject() method; isValid[Retreating]Move(),
 * 								crossesShipOnMove(), movePlayer() now accept direction as separate parameter /kac12
 * @version 0.4 - 21/04/17 - Added getBlockingShip() method /kac12
 * @version 0.41 - 21/04/17 - Added player proper initialization, state initialization;
 * 								isValidTurn() now uses isValidMove()
 * 								/kac12
 * @version 0.5 - 27/04/17 - movePlayer() now turns the player first
 * @version 0.6 - 01/05/17 - fixed isValidRetreatingMove() method to consider crossing a ship while retreating 
 * @version 0.61 - 02/05/17 - Updated comments to fix QA9 /haa14
 * @version 0.7 - 03/05/17 - added getDistanceBetween(), getDirectionTo(), getPlayerIndex(); fixed isValidMove() and isValidRetreatingMove() to consider borders
 * @version 0.71 - 04/05/17 - fixed isValidRetreatingMove() (doesn't let players near islands) and isValidMove()
 * 								(doesn't allow going through multiple ships) /kac12
 * @version 0.72 - 04/05/17 - isValidMove() now doesn't allow going onto island tiles after passing a ship /kac12
 * @version 0.73 - 05/05/17 - Comments updated to account for QA9
 * @version 1.0 - 05/05/17 - Ready for initial release /haa14
 */

public class GameEngine {
	
	private Board board;
	private Player[] playerList;
	private GameSetup gameSetup;
	private StateMachine gameStates;
	private Player currentPlayer;
	
	/**
	 * Initializes objects
	 */
	public GameEngine(){
		playerList = new Player[4];
		gameStates = new StateMachine();
	}
	
	/**
	 * Prepares a match with given player names
	 * @param names - names of players
	 * @see setState
	 */
	public void initGame(String names[]) {
		for (int i=0; i < 4; i++){
			playerList[i] = new Player();
			if (names[i] != null){
				playerList[i].setName(names[i]);
			} else {
				throw new NullPointerException("Not all names provided");
			}
		}
		gameSetup = new GameSetup(playerList);
		board = gameSetup.setupGame();
		currentPlayer = playerList[0];
		setState("turn", State.T01);
		setState("action", State.PREPARE);
	}
		
	/**
	 * Returns current state
	 * @param name	String for which to check the state for (e.g. "game")
	 * @return	enum State object
	 * @see gameStates.getState()
	 */
	public State getState(String name) {
		try {
			return gameStates.getState(name);
		} catch (NullStateException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Sets state for a current string
	 * @param name	String to set the state for
	 * @param value	State to set
	 */
	public void setState(String name, State value){
		try {
			gameStates.setState(name, value);
		} catch (InvalidStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @return	Player whose turn it is currently
	 */
	public Player getCurrentPlayer(){
		return currentPlayer;
	}
	
	/**
	 * Returns a player of given index (sorted by order of the turns)
	 * @param index	index/turn of the player
	 * @return	Player object
	 */
	public Player getPlayer(int index){
		return playerList[index];
	}
	
	/**
	 * Returns index of a given player
	 * @param player	Player object
	 * @return	index value in the array
	 */
	public int getPlayerIndex(Player player){
		for (int i = 0; i < 4; i++){
			if (player.equals(playerList[i]))
				return i;
		}
		return 0;
	}
	
	/**
	 * Returns player's current Position
	 * @param index	index of the player
	 * @return	Position object of Player's ship
	 */
	public Position getPlayerPos(int index){
		return playerList[index].getShip().getPos();
	}
	
	/**
	 * Returns the board object currently being used
	 * @return	Board object used by the GameEngine
	 */
	public Board getBoard(){
		return board;
	}
	
	/**
	 * Returns a LinkedList of MapObject objects at a given position
	 * @param pos	position to get the objects at
	 * @return	list of objects, null if position is invalid
	 */
	public LinkedList<MapObject> getObjectsByPos(Position pos){
		try {
			return board.getObjectByPos(pos);
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Returns a LinkedList of MapObject objects at a given position
	 * @param x	x coordinate
	 * @param y	y coordinate
	 * @return	list of objects, null if position is invalid
	 */
	public LinkedList<MapObject> getObjectsByPos(int x, int y){
		try {
			return board.getObjectByPos(x, y);
		} catch (InvalidPositionException e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Checks if a player can turn their ship into given direction
	 * (defined by whether or not it will face a blocking tile)
	 * @param player	player that wants to turn
	 * @param direction	new direction
	 * @return	true if a valid turn, false otherwise
	 * @see isValidMove()
	 */
	public boolean isValidTurn(Player player, Direction direction){
		int potX = addDirectionalX(player.getShip().getPos().x, direction);
		int potY = addDirectionalY(player.getShip().getPos().y, direction);
		if (potX < 1 || potX > 20 || potY < 1 || potY > 20)
			return false;
		else
			return isValidMove(player, 1, direction);
	}
	
	/**
	 * Turns player into given direction
	 * @param player	player to turn
	 * @param direction	new direction of player's ship
	 * @throws InvalidDirectionException	thrown if turning will make ship unable to move
	 * @see isValidTurn()
	 */
	public void turnPlayer(Player player, Direction direction)
	throws InvalidDirectionException {
		if (isValidTurn(player, direction)){
			player.getShip().setOrientation(direction);
		} else {
			throw new InvalidDirectionException();
		}
	}

	/**
	 * Checks whether a player can move specified distance (in terms of obstacles and board borders)
	 * @param player	owner of the ship
	 * @param distance	number of tiles to go past
	 * @param dir		direction of the move
	 * @return	true if move is valid (no obstacles), invalid otherwise
	 */
	public boolean isValidMove(Player player, int distance, Direction dir){
		int shipsEncountered = 0;
		int posX = player.getShip().getPos().x;
		int posY = player.getShip().getPos().y;
		for (int i = 0; i < distance; i++){
			posX = addDirectionalX(posX, dir);
			posY = addDirectionalY(posY, dir);
			//out of bounds scenario
			if (posX > 20 || posY > 20 || posX < 1 || posY < 1){
				return false;
			}
			//check if player tries to cross an island
			if (board.hasIsland(posX, posY)){
				return false;
			}
			//going anywhere after passing a ship
			if (shipsEncountered > 0){
				if (board.hasShip(posX, posY)) return false;
				if (board.hasPort(posX, posY)) return false;
				for (int x = posX - 1; x <= posX + 1; x++){
					for (int y = posY - 1; y <= posY + 1; y++){
						try {
							if (board.getObjectByPos(x, y).get(0) instanceof TreasureIsland
									|| board.getObjectByPos(x, y).get(0) instanceof FlatIsland)
								return false;
						} catch (InvalidPositionException ex) {
							ex.printStackTrace();
						}
					}
				}
			}
			//check if player tries to go through a ship next to an island/in a port
			if (board.hasShip(posX, posY)){
				try{
					for (MapObject m : board.getObjectByPos(posX, posY)){
						if (m instanceof Ship && ((Ship) m).getOwner() != player){
							MapObject destination = getPlayerDestinationObject(((Ship) m).getOwner());
							if (destination instanceof Port || destination instanceof TreasureIsland
									|| destination instanceof PlayerPort || destination instanceof FlatIsland){
								return false;
							}
						}
					}
				} catch (InvalidPositionException ex){
					ex.printStackTrace();
				}
			}
		}
		return true;
	}
	
	/**
	 * Same as isValidMove but cannot finish at a Port/Ship/Island's coast
	 * @param player	Player to check the move for
	 * @param distance	distance of the potential move
	 * @param dir		direction of the move
	 * @return	true if move is valid, false otherwise
	 */
	public boolean isValidRetreatingMove(Player player, int distance, Direction dir){
		int posX = player.getShip().getPos().x;
		int posY = player.getShip().getPos().y;
		for (int i = 0; i < distance; i++){
			posX = addDirectionalX(posX, dir);
			posY = addDirectionalY(posY, dir);
			//out of bounds scenario
			if (posX > 20 || posY > 20 || posX < 1 || posY < 1){
				return false;
			}
			if (board.hasIsland(posX, posY)){
				return false;
			}
		}
		if (board.hasPort(posX, posY) || board.hasShip(posX, posY))
			return false;
		for (int i = posX - 1; i <= posX + 1; i++){
			for (int j = posY - 1; j <= posY + 1; j++){
				if (board.hasIsland(i, j))
					return false;
			}
		}
		return !crossesShipOnMove(player, distance, dir); //move is finally valid only if doesn't cross a ship
	}
	
	/**
	 * Checks whether a move would take a player's ship through another player's ship
	 * @param player	player moving
	 * @param distance	distance of the move
	 * @return	true if another ship passed on the way (but not at the last tile), false otherwise
	 */
	public boolean crossesShipOnMove(Player player, int distance, Direction dir){
		int posX = player.getShip().getPos().x;
		int posY = player.getShip().getPos().y;
		for (int i = 0; i < distance-1; i++){ //distance-1 because we're not actually checking the last tile
			posX = addDirectionalX(posX, dir);
			posY = addDirectionalY(posY, dir);
			if (board.hasShip(posX, posY)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Gets a first occuring ship in a given direction
	 * @param player owner of the moving ship
	 * @param dir direction in which the player wishes to move
	 * @return first occuring ship (if any)
	 */
	public Ship getBlockingShip(Player player, Direction direction){
		int x = player.getShip().getPos().x;
		int y = player.getShip().getPos().y;
		while(true){
			x = addDirectionalX(x, direction);
			y = addDirectionalY(y, direction);
			try{
				LinkedList<MapObject> objs = board.getObjectByPos(x, y);
				for (MapObject o : objs){
					if (o instanceof Ship){
						return (Ship)o;
					}
				}
			} catch (InvalidPositionException ex){
				return null;
			}
		}
	}
	
	/**
	 * Moves the player by a given number of tiles
	 * @param player	player whose ship to move
	 * @param distance	number of tiles
	 * @throws InvalidMoveException	thrown if the move is invalid (can be checked with isValidMove() beforehand)
	 */
	public void movePlayer(Player player, int distance, Direction dir)
	throws InvalidMoveException {
		if (isValidMove(player, distance, dir)){
			try {
				player.turn(dir);
			} catch (InvalidDirectionException e) {
				e.printStackTrace();
			}
			try {
				board.removeMapObject(player.getShip());
				player.move(distance);
				board.addMapObject(player.getShip());
			} catch (InvalidPositionException ex) {
				ex.printStackTrace();
			}
		} else {
			throw new InvalidMoveException();
		}
	}
	
	
	/**
	 * Returns distance between two positions in squares
	 * @param x	first horizontal coordinate
	 * @param y	first vertical coordinate
	 * @param x2	second horizontal coordinate
	 * @param y2	second vertical coordinate
	 * @return	distance between coordinates in squares
	 */
	public int getDistanceBetween(int x, int y, int x2, int y2){
		return Math.max(Math.abs(x - x2), Math.abs(y - y2));
	}
	
	/**
	 * Returns distance between two position objects (in squares)
	 * @param p1	first position to compare
	 * @param p2	second position to compare
	 * @return	distance in squares
	 */
	public int getDistanceBetween(Position p1, Position p2){
		return getDistanceBetween(p1.x, p1.y, p2.x, p2.y);
	}
	
	/**
	 * Returns a Direction based on given coordinates
	 * @param from	set of coordinates (origin)
	 * @param to	set of coordinates (destination)
	 * @return	Direction object
	 */
	public Direction getDirectionTo(Position from, Position to){
		return getDirectionTo(from.x, from.y, to.x, to.y);
	}
	
	/**
	 * Returns a Direction based on given coordinates
	 * @param x
	 * @param y
	 * @param x2
	 * @param y2
	 * @return
	 */
	public Direction getDirectionTo(int x, int y, int x2, int y2){
		int xdiff = x2 - x;
		int ydiff = y2 - y;
		if (ydiff > 0 && xdiff == 0) return Direction.N;
		else if (ydiff > 0 && xdiff > 0 && Math.abs(ydiff) == Math.abs(xdiff)) return Direction.NE;
		else if (ydiff == 0 && xdiff > 0) return Direction.E;
		else if (ydiff < 0 && xdiff > 0 && Math.abs(ydiff) == Math.abs(xdiff)) return Direction.SE;
		else if (ydiff < 0 && xdiff == 0) return Direction.S;
		else if (ydiff < 0 && xdiff < 0 && Math.abs(ydiff) == Math.abs(xdiff)) return Direction.SW;
		else if (ydiff == 0 && xdiff < 0) return Direction.W;
		else if (ydiff > 0 && xdiff < 0 && Math.abs(ydiff) == Math.abs(xdiff)) return Direction.NW;
		else if (ydiff == 0 && xdiff == 0) return Direction.N; //special case where a person stays in place
		return null;
	}
	
	/**
	 * Increases X by one tile depending on the direction given
	 * @param x	X coordinate to increase/decrease
	 * @param dir	direction variable
	 * @return	new value of X
	 */
	public int addDirectionalX(int x, Direction dir){
		switch (dir){
		case NE:
			return x+1;
		case E:
			return x+1;
		case SE:
			return x+1;
		case SW:
			return x-1;
		case W:
			return x-1;
		case NW:
			return x-1;
		default:
			return x;
		}
	}
	
	/**
	 * Increases Y by one tile depending on the direction given
	 * @param y	Y coordinate to increase/decrease
	 * @param dir	direction variable
	 * @return	new value of Y
	 */
	public int addDirectionalY(int y, Direction dir){
		switch (dir){
		case NE:
			return y+1;
		case N:
			return y+1;
		case SE:
			return y-1;
		case SW:
			return y-1;
		case S:
			return y-1;
		case NW:
			return y+1;
		default:
			return y;
		}
	}

	
	/**
	 * Proceeds to next player's turn both in "turn" state and
	 * by changing currentPlayer instance variable
	 * @see gameStates.setState()
	 */
	@SuppressWarnings("incomplete-switch")
	public void incCurrentPlayer(){
		State st = null;
		try {
			st = gameStates.getState("turn");
			switch (st){
			case T01: 
				currentPlayer = playerList[1];
				gameStates.setState("turn", State.T02);
				break;
			case T02: 
				currentPlayer = playerList[2];
				gameStates.setState("turn", State.T03);
				break;
			case T03: 
				currentPlayer = playerList[3];
				gameStates.setState("turn", State.T04);
				break;
			case T04: 
				currentPlayer = playerList[0];
				gameStates.setState("turn", State.T01);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns winner of the game
	 * @return	Player object if a winner present, null if nobody owns
	 * 	20 or more points worth of treasure
	 */
	public Player getWinner(){
		int sum = 0;
		for (Player p : playerList){
			sum = 0;
			PlayerPort port = p.getHomePort();
			ArrayList<Treasure> treasure = port.getTreasureList();
			for (Treasure t : treasure){  //t - treasure
				sum += t.getValue();
			}
			if (sum >= 20) {
				return p;
			}
		
		}
		return null;
	}

	/**
	 * Gets the last fight's loser (if any) based on the state of "attloser"
	 * @return	Player object that is the loser
	 * @see gameStates.getState()
	 */
	public Player getFightLoser(){
		State state = null;
		try {
			state = gameStates.getState("attloser");
		} catch (NullStateException e) {
			e.printStackTrace();
		}
		switch (state){
		case T01: return playerList[0];
		case T02: return playerList[1];
		case T03: return playerList[2];
		case T04: return playerList[3];
		default: return null;
		}
	}

	/**
	 * Returns an object the player is currently at (a Port, Island tile next
	 * to it etc.)
	 * @param player	Player object to check
	 * @return	MapObject the player's at (Ports)/next to (Islands) or null if none
	 */
	public MapObject getPlayerDestinationObject(Player player){
		
		//Check ports first
		try {
			LinkedList<MapObject> objects = board.getObjectByPos(player.getShip().getPos());
			for (MapObject o : objects){
				if (o instanceof Port){
					return o;
				}
			}
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}
		
		
		//Check for nearby Islands
		try {
			int x = player.getShip().getPos().x;
			int y = player.getShip().getPos().y;
			LinkedList<MapObject> obj = null;
			//Iterate over a 3x3 square (including only valid positions + excluding ship's position)
			for (int i = x-1; i <= x+1; i++){
				for (int j = y-1; j <= y+1; j++){
					if (x >= 1 && x <= 20 && y >= 1 && y <= 20 && i != x && j != y){
						//Check if any non-ship, non-port objects there
						if (board.hasIsland(i, j)){
							obj = board.getObjectByPos(i, j);
							for (MapObject o : obj){
								if (o instanceof TreasureIsland || o instanceof FlatIsland){
									return o;
								}
							}
						}
					}
				}
			}
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}	
		
		//Check ships
		try {
			LinkedList<MapObject> objects = board.getObjectByPos(player.getShip().getPos());
			for (MapObject o : objects){
				if (o instanceof Ship && player.getShip() != (Ship) o){
					return o;
				}
			}
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}
		
		//No other objects
		return null;
	}
	
}
