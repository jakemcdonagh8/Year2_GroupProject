/*
 * @(#) PlayerInteraction.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs211.group07.system.helpers;

import java.util.LinkedList;

import uk.ac.aber.cs211.group07.system.exceptions.EmptyPackException;
import uk.ac.aber.cs211.group07.system.exceptions.TooMuchTreasureException;
import uk.ac.aber.cs211.group07.system.resources.Player;
import uk.ac.aber.cs211.group07.system.resources.Ship;
import uk.ac.aber.cs211.group07.system.resources.TreasureIsland;

/**
 * PlayerInteraction class manages interactions between Players
 * @author Julia Wojciechowska - juw30
 * @version 0.1 - 02/05/17 - initial creation /juw30
 * @version 0.11 - 02/05/17 - added comments
 * @version 0.2 - 03/05/17 - expanded and added confirm()
 * @version 1.0 - 05/05/17 - Ready for initial release /haa14
 */
public abstract class PlayerInteraction {
	
	/**
	 * Returns the fight winner.
	 * @param player1
	 * @param player2
	 * @return fight winner
	 */
	public static Player getFightWinner(Player player1, Player player2){
		if (player1.getFightingStrength() > player2.getFightingStrength()) {
			return player1;
		} else if (player1.getFightingStrength() == player2.getFightingStrength()) {
			return player2;
		} else {
			return player2;
		}
	}
	
	/**
	 * Performs fight between Players
	 * @param player1
	 * @param player2
	 * @param treasureIsland
	 * @throws TooMuchTreasureException
	 * @throws EmptyPackException
	 */
	public static void performFight(Player player1, Player player2, TreasureIsland treasureIsland) 
	throws TooMuchTreasureException, EmptyPackException{
		Player loser;
		Player winner;
		if (player1 == getFightWinner(player1, player2)){
			winner = player1;
			loser = player2;
		} else {
			winner = player2;
			loser = player1;
		}
		
		if (loser.getShip().getTreasureNum() > 0) {
			while (loser.getShip().getTreasureNum() != 0){
				if (winner.getShip().getTreasureNum() < 2) {
					winner.getShip().addTreasure(loser.getShip().removeFirstTreasure());
				} else {
					treasureIsland.addTreasure(loser.getShip().removeFirstTreasure());
				}
			}
		} else if (loser.getShip().getTreasureNum() == 0) {
			int index = 0;
			loser.sortCardsInc();
			while (!loser.getHand().isEmpty() && index < 2) {
				winner.addCard(loser.draw());
				index++;
			}
		}
		
	}
	
	/**
	 * Performs fight between Ships
	 * @param ship1
	 * @param ship2
	 * @param treasureIsland
	 * @throws TooMuchTreasureException
	 * @throws EmptyPackException
	 */
	public static void performFight(Ship ship1, Ship ship2, TreasureIsland treasureIsland) 
	throws TooMuchTreasureException, EmptyPackException {
		Player loser;
		Player winner;
		Player player1 = ship1.getOwner();
		Player player2 = ship2.getOwner();
		if (player1 == getFightWinner(player1, player2)){
			winner = player1;
			loser = player2;
		} else {
			winner = player2;
			loser = player1;
		}
		
		if (loser.getShip().getTreasureNum() > 0) {
			while (loser.getShip().getTreasureNum() != 0){
				if (winner.getShip().getTreasureNum() < 2) {
					winner.getShip().addTreasure(loser.getShip().removeFirstTreasure());
				} else {
					treasureIsland.addTreasure(loser.getShip().removeFirstTreasure());
				}
			}
		} else if (loser.getShip().getTreasureNum() == 0) {
			int index = 0;
			loser.sortCardsInc();
			while (!loser.getHand().isEmpty() && index < 2) {
				winner.addCard(loser.draw());
				index++;
			}
		}
	}
	
	/**
	 * Returns lost treasure or cards
	 * @param player1
	 * @param player2
	 * @return lost treasure or cards
	 * @throws EmptyPackException
	 */
	public static LinkedList<Object> getTreasureLost(Player player1, Player player2) 
	throws EmptyPackException{
		Player loser;
		LinkedList<Object> treasureOutcome = new LinkedList<Object>();
		LinkedList<Object> treasureAwarded = getTreasureAwarded(player1, player2);
		
		if (player1 == getFightWinner(player1, player2)){
			loser = player2;
		} else {
			loser = player1;
		}
		
		if (loser.getShip().getTreasureNum() > 0) {
			for (int index = 0; index < loser.getShip().getTreasureNum(); index++){
				if (loser.getShip().getTreasureNum() != 0) {
					treasureOutcome.add(loser.getShip().getTreasureList().get(index));
					
				}
			}
		} else if (loser.getShip().getTreasureNum() == 0) {
			loser.sortCardsInc();
			if (loser.getHand().size() > 2) {
			for (int index = 0; index < 2; index++){
				treasureOutcome.add(loser.getHand().get(index));
				}
			} else if (loser.getHand().size() == 1) {
				treasureOutcome.add(loser.getHand().get(0));
			} 
		}
		
		//Separates treasure (treasure stolen and treasure lost to the sea)
		for (Object a : treasureAwarded) {
			treasureOutcome.remove(a);
		}		
		return treasureOutcome;	
	}
	
	/**
	 * Returns awarded treasure or cards
	 * @param player1
	 * @param player2
	 * @return awarded treasure or cards
	 * @throws EmptyPackException
	 */
	public static LinkedList<Object> getTreasureAwarded(Player player1, Player player2) 
	throws EmptyPackException{
		Player loser;
		Player winner;
		LinkedList<Object> treasureOutcome = new LinkedList<Object>();
		
		if (player1 == getFightWinner(player1, player2)){
			winner = player1;
			loser = player2;
		} else {
			winner = player2;
			loser = player1;
		}
		
	
			if (loser.getShip().getTreasureNum() > 0
					&& winner.getShip().getTreasureNum() < 2) {
						for (int index = 0; index < loser.getShip().getTreasureNum() && treasureOutcome.size() < 2 - winner.getShip().getTreasureNum(); index++){
						treasureOutcome.add(loser.getShip().getTreasureList().get(index));
					}
			} else 	if (loser.getShip().getTreasureNum() == 0) {
				loser.sortCardsInc();
				if (loser.getHand().size() >= 2) {
					for (int index2 = 0; index2 < 2; index2++){
						treasureOutcome.add(loser.getHand().get(index2));
					}
				} else if (loser.getHand().size() == 1) {
					treasureOutcome.add(loser.getHand().get(0));
				} 
			}
		
		

	
		return treasureOutcome;	
	}
}

