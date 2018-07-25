/*
 * @(#) Player.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs211.group07.system.resources;

import java.util.ArrayList;

import uk.ac.aber.cs211.group07.system.exceptions.EmptyPackException;
import uk.ac.aber.cs211.group07.system.exceptions.InvalidDirectionException;
import uk.ac.aber.cs211.group07.system.exceptions.InvalidMoveException;
import uk.ac.aber.cs211.group07.system.exceptions.TooMuchTreasureException;

/**
 * This class contains details of the player
 * @author Julia Wojciechowska
 * @version 0.1 - 09/03/17 - initial creation /juw30
 * @version 0.2 - 12/03/17 - setHomePort sets home for the ship, owner for that port to this,
 * changed Port instances to PlayerPort /kac12
 * @version 0.3 - 27/04/17 - move() no longer throws an exception if in homePort,
 * 							added breaks; in move() (Julia!) /kac12
 * @version 1.0 - 05/05/17 - Ready for initial release /haa14
 */

public class Player implements CardHolder<Card> {
	
	private String name;
	private Ship ship;
	private PlayerPort homePort;
	private ArrayList<CrewCard> hand;
	private ArrayList<ChanceCard> chanceCards;
	private Pack<Card> cards;
	
	/**
	 * Constructor for Player class
	 */
	public Player(){
		name = null;
		ship = null;
		homePort = new PlayerPort(null, null);
		hand = new ArrayList<CrewCard>();
		chanceCards = new ArrayList<ChanceCard>();
		cards = new Pack<Card>();
	}
	
	/**
	 * Returns the name of the Player
	 * @return name of the Player
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Sets the name of the Player
	 * @param name - name of the Player
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * Returns the ship
	 * @return ship
	 */
	public Ship getShip(){
		return ship;
	}
	
	/**
	 * Sets new ship
	 * @param newShip - new ship
	 */
	public void setShip(Ship newShip) {
		this.ship = newShip;		
	}
	
	/**
	 * Returns the Home Port
	 * @return Home Port
	 */
	public PlayerPort getHomePort(){
		return homePort;
	}
	
	/**
	 * Sets the Home Port
	 * @param port - Home Port
	 */
	public void setHomePort(PlayerPort port){
		this.homePort = port;
		this.ship.setHome(port.getPos());
		port.setOwner(this);
	}
	
	/**
	 * Returns the number of the crew cards in player's hand
	 * @return number of crew card in player's hand
	 */
	public int getCrewCardNum(){
		return hand.size();
	}
	
	/**
	 * Returns the number of the Chance Card in player's hand
	 * @return number of the Chance Cards in player's hand
	 */
	public int getChanceCardNum(){
		return chanceCards.size();
		
	}
	
	/**
	 * Returns the Crew Cards in player's hand
	 * @return crew cards in player's hand
	 */
	public ArrayList<CrewCard> getHand(){
		return hand;
	}
	
	/**
	 * Returns the Chance Cards in player's hand
	 * @return Chance Cards in player's hand
	 */
	public ArrayList<ChanceCard> getChanceCards(){
		return chanceCards;
	}
	
	/**
	 * Returns the moving strength
	 * @return moving strength
	 */
	public int getMovingStrength(){
		int strengthValue = 0;
		for (CrewCard card : hand){
			strengthValue += card.getValue();
		}
		if (strengthValue == 0){ //no cards scenario 
			strengthValue = 1;
		}
		return strengthValue;
	}
	
	/**
	 * Returns the combat value
	 * @return combat value
	 */
	public int getFightingStrength(){
		int black = 0;
		int red = 0;
		for (CrewCard card : hand) {
			if (card.isBlack()) {
				black += card.getValue();
			} else if (card.isRed()) {
				red += card.getValue();
			}
		}		
		return Math.abs(red-black);
	}
	
	/**
	 * Returns Pack of Cards
	 * @return pack of cards
	 */
	public Pack<Card> getPack() {
		return cards;
	}

	/**
	 * Returns pack of cards
	 */
	public ArrayList<Card> getCards() {
		return cards.getCards();
	}

	/**
	 * Returns number of cards
	 */
	public int size() {
		return cards.size();
	}
	
	/**
	 * Sorts Crew Cards according to their value, increasingly
	 */
	public void sortCardsInc(){
		int size = this.hand.size();
		boolean swapped = true;
		do {
			swapped = false;
		for (int index = 0; index < size-1; index++){
			CrewCard c1 = this.hand.get(index);
			CrewCard c2 = this.hand.get(index+1);
			if (c1.getValue() <= c2.getValue()){		
			} else {
				this.hand.set(index, c2);
				this.hand.set(index+1, c1);
				swapped = true;
			}
		}
		} while (swapped);
	}
	
	/**
	 * Sorts Crew Cards according to their value, decreasingly
	 */
	public void sortCardsDec() {
		int size = this.hand.size();
		boolean swapped = true;
		do {
			swapped = false;
			for (int index = 0; index < size-1; index++){
				CrewCard c1 = this.hand.get(index);
				CrewCard c2 = this.hand.get(index+1);
				if (c1.getValue() >= c2.getValue()){
				} else {
					this.hand.set(index, c2);
					this.hand.set(index+1, c1);
					swapped = true;
				}
				
			}
		} while (swapped);
	}
	
	/**
	 * Returns and removes card from the top
	 */
	public CrewCard draw() 
	throws EmptyPackException {
		return hand.remove(0);
	}
	
	/**
	 * Returns card from the top
	 */
	public CrewCard peek() {
		return hand.get(0);
	}

	/**
	 * Adds cards to the hand if it is a crew card or adds it to 
	 * pack of Chance Cards if it is a chance card
	 * @param card card to be added
	 */
	public void addCard(Card card){
		cards.add(card);
		if (card instanceof CrewCard){
			hand.add((CrewCard) card);
		} else {
			chanceCards.add((ChanceCard) card);
		}
	}
	
	/**
	 * Removes cards depending on its type
	 * @param card card to be removed
	 */
	public void removeCard(Card card){
		if (card instanceof CrewCard){
			hand.remove(card);
		} else {
			chanceCards.remove(card);
		}
	}
	
	/**
	 * Sets the new direction
	 * @param newDir - new direction
	 */
	public void turn(Direction newDir)
	throws InvalidDirectionException {
		ship.setOrientation(newDir);		
	}
	
	/**
	 * Moves the player by the specified number of squares
	 * @param squares number of squares 
	 */
	public void move(int squares) 
	throws InvalidMoveException{
		int posX = ship.getPos().x;
		int posY = ship.getPos().y;
		Direction direction = ship.getOrientation();
		switch(direction){
		case NE:
			ship.setPos(posX + squares, posY + squares);
			break;
		case N:
			ship.setPos(posX, posY + squares);
			break;
		case SE:
			ship.setPos(posX + squares, posY - squares);
			break;
		case E:
			ship.setPos(posX + squares, posY);
			break;
		case SW:
			ship.setPos(posX - squares, posY - squares);
			break;
		case S:
			ship.setPos(posX, posY - squares);
			break;
		case NW:
			ship.setPos(posX - squares, posY + squares);
			break;
		case W:
			ship.setPos(posX - squares, posY);
			break;
		}
	}
	
	
}
