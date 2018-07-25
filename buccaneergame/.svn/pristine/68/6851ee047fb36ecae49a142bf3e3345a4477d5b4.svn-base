/*
 * @(#) GameSetup.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs211.group07.system;

//import java.lang.reflect.Array;
import java.util.ArrayList;

import uk.ac.aber.cs211.group07.system.exceptions.EmptyPackException;
import uk.ac.aber.cs211.group07.system.resources.AnchorBay;
import uk.ac.aber.cs211.group07.system.resources.Card;
import uk.ac.aber.cs211.group07.system.resources.ChanceCard;
import uk.ac.aber.cs211.group07.system.resources.CrewCard;
import uk.ac.aber.cs211.group07.system.resources.FlatIsland;
import uk.ac.aber.cs211.group07.system.resources.Island;
import uk.ac.aber.cs211.group07.system.resources.Pack;
import uk.ac.aber.cs211.group07.system.resources.PirateIsland;
import uk.ac.aber.cs211.group07.system.resources.Player;
import uk.ac.aber.cs211.group07.system.resources.PlayerPort;
import uk.ac.aber.cs211.group07.system.resources.Port;
import uk.ac.aber.cs211.group07.system.resources.Position;
import uk.ac.aber.cs211.group07.system.resources.Ship;
import uk.ac.aber.cs211.group07.system.resources.Treasure;
import uk.ac.aber.cs211.group07.system.resources.TreasureIsland;
import uk.ac.aber.cs211.group07.system.resources.TreasureType;

/**
 * Sets the game board up given a list of players (with their names in them)
 * @author Kamil Cupial
 * @version 0.1 - 12/03/17 - initial creation (up to initCrewPack) /kac12
 * @version 0.2 - 18/03/17 - rest of the methods added /kac12
 * @version 0.21 - 02/04/17 - removed exception handling with ports (no exception is thrown) /kac12
 * @version 0.3 - 02/04/17 - Fixed bugs: initializing Ship's positions, adding CrewCards to PirateIsland,
 * 								assigning ChanceCards to TreasureIsland
 * @version 0.31 - 15/04/17 - Fixed assigning treasure to ports (making up to 8 in value)
 * @version 0.4 - 21/04/17 - assignPorts() now modifies/copies over the proper object in memory (passed by GameEngine)		
 * @version 0.41 - 02/05/17 - Fixed comments to fit QA9 /haa14
 * @version 0.5 - 03/05/17 - Fixed port assignment (passing the copy of port's Position object to the ship now)
 * @version 1.0 - 05/05/17 - Ready for initial release /haa14
 */
public class GameSetup {
	
	private Player[] playerList;
	private Port[] portList;
	private Pack<CrewCard> crewPack;
	private Pack<ChanceCard> chancePack;
	private Island[] islandList;
	private Treasure[] treasureList;
	
	/**
	 * Regular constructor that checks if there are 4 players with
	 * names defined
	 * @param players an array with 4 Player objects that have names defined
	 */
	public GameSetup(Player[] players){
		playerList = players;
		for (int i = 0; i < 4; i++){
			if (playerList[i] == null || playerList[i].getName() == null){
				throw new NullPointerException("Not all players or playernames in the array provided.");
			}
		}
	}
	
	/**
	 * Sets up the Board object, initialises values and objects
	 * @return returns a ready-to-play Board object
	 */
	public Board setupGame(){
		initPlayers();
		initPorts();
		assignPorts();
		initCrewPack();
		initChancePack();
		initIslands();
		initTreasure();
		assignCards();
		assignTreasure();
		Board board = new Board(playerList, portList, crewPack, chancePack, islandList, treasureList);
		return board;
	}
	
	/**
	 * Initialises ships for players
	 */
	private void initPlayers(){
		for (int i = 0; i < 4; i++){
			Ship next = new Ship(playerList[i]);
			playerList[i].setShip(next);
		}
	}
	
	/**
	 * Initialises ports in the portList array
	 * indexes 0-3 are Player controlled ports
	 * IF ORDER CHANGED CHANGE IN METHODS BELOW TOO
	 */
	private void initPorts(){
		portList = new Port[7];
		//PlayerPorts ordered according to which makes the first turn
		portList[0] = new PlayerPort("Port of London", new Position(1, 14));
		portList[1] = new PlayerPort("Port of Genoa", new Position(7, 1));
		portList[2] = new PlayerPort("Port of Marseilles", new Position(20, 7));
		portList[3] = new PlayerPort("Port of Cadiz", new Position(14,20));
		
		portList[4] = new Port("Port of Venice", new Position(1, 7));
		portList[5] = new Port("Port of Amsterdam", new Position(20, 14));
		
		portList[6] = new AnchorBay("Anchor Bay", new Position(20, 1));
		
		
	}
	
	/**
	 * Assigns ports to players randomly (indexes 0-3)
	 */
	private void assignPorts(){
		//Shuffle players randomly
		Player[] copy = new Player[4];
		int randomNumber = 0;
		for (int i = 0; i < 4; i++){ //fill all 4 cells of copy with players
			while (copy[i] == null){ //keep randomising until a valid player found
				randomNumber = (int)(Math.random() * 4); //get a random number 0-4
				copy[i] = playerList[randomNumber]; //assign player of that random index to copy[i] (can be already used, thus the check for null above)
			}
			playerList[randomNumber] = null; //remove reference to the copied player from original list
		}
		
		for (int i = 0; i < 4; i++){
			
		}
		//Assign ports in order of turns
		for (int i = 0; i < 4; i++){
			playerList[i] = copy[i]; //swap an emptied list entry with a randomised copy
			playerList[i].setHomePort((PlayerPort) portList[i]);
			playerList[i].getShip().setPos(new Position(portList[i].getPos().x,portList[i].getPos().y) );
		}
	}
	
	/** 
	 * Create a pack of 36 CrewCards 
	 */
	private void initCrewPack(){
		crewPack = new Pack<CrewCard>();
		//Strength 1-3
		for (int strength = 1; strength <= 3; strength++){
			//6 of each strength of each colour
			for (int count = 1; count <= 6; count++){
				CrewCard black = new CrewCard(strength, false);
				CrewCard red = new CrewCard(strength, true);
				crewPack.add(black);
				crewPack.add(red);
			}
		}
		crewPack.shuffle();
	}
	
	/**
	 * Create a Pack of 28 ChanceCards
	 */
	private void initChancePack(){
		chancePack = new Pack<ChanceCard>();
		/* Quick guide:
		 * Treasure/cards removed from player - usually least valuable (unless stated otherwise)
		 * Treasure awarded to player from treasure island - always let player pick which one (unless treasureIslandTotalValue <= valuePlayerCanChoose)
		 * Cards awarder to player from Pirate Island - top of the pack
		 * Treasure/cards taken away from player to another player - always least valuable
		 * 
		 * More sophisticated chance cards are explained in comments below.
		 * 
		 */
		
		chancePack.add(new ChanceCard(1, "Your ship has been blown 5 squares away from the island! If your crew strength is less than 3, 4 crew cards will be taken from Pirate Island."));
		chancePack.add(new ChanceCard(2, "Choose a player to take 3 crew cards from."));
		chancePack.add(new ChanceCard(3, "Your ship has been blown to Mud Bay! If your crew strength is less than 3, 4 crew cards will be taken from Pirate Island"));
		chancePack.add(new ChanceCard(4, "Your ship has been blown to Cliff Creek! If your crew strength is less than 3, 4 crew cards will be taken from Pirate Island"));
		chancePack.add(new ChanceCard(5, "Your ship has been blown to its home port! If your crew strength is less than 3, 4 crew cards will be taken from Pirate Island"));
		
		//Nearest port in the direction the ship is facing
		chancePack.add(new ChanceCard(6, "Your ship has been blown to one of the ports! If your crew strength is less than 3, 4 crew cards will be taken from Pirate Island"));
		
		/* If 2 other ships in equivalent distance from the ship - "Your crew managed to get the treasure back! Nothing's been lost!"
		 * Otherwise:
		 * a) Get the nearest ship to the player's one
		 * b) Transfer 1 treasure from the player's ship to the nearest ship
		 * c) If above is impossible (no treasure/space) - transfer up to 2 crew cards (depending on availability) to the nearest ship
		 * d) Display result
		 */
		chancePack.add(new ChanceCard(7, "Some of your loot has been washed away to another ship!"));
		
		
		//Same as above but the treasure/cards land on Flat Island, not the nearest ship
		chancePack.add(new ChanceCard(8, "Some of your loot has been washed away to the Flat Island!"));
		
		/*
		 * If no treasure available - "The thief has been caught, but turned out to be the best crewman you had! He has been cast out to Flat Island."
		 * If treasure available - "Your <treasurename> has been stolen and sold on an auction on Flat Island."
		 */
		chancePack.add(new ChanceCard(9, "Someone tried to steal your most valuable treasure!"));
		
		chancePack.add(new ChanceCard(10, "Your best crewman has deserted to Pirate Island!"));
		
		/*
		 * Take up to 5 value from treasureisland.
		 * If no treasure available - "No treasure available. 2 pirates from Pirate Island join your crew instead."
		 */
		chancePack.add(new ChanceCard(11, "You've gained treasure!"));
		chancePack.add(new ChanceCard(12, "You've gained treasure!"));
		chancePack.add(new ChanceCard(13, "You've gained treasure!"));
		chancePack.add(new ChanceCard(14, "You've gained treasure!")); //this one up to 7 value or 3 cards
		
		chancePack.add(new ChanceCard(15, "2 pirates have joined your crew!"));
		chancePack.add(new ChanceCard(28, "2 pirates have joined your crew!")); //mind the number - according to the req spec
		
		/*
		 * Value - 7
		 * additionally if sailing strength > 10 remove lowest-value cards until <= 10
		 * 	and display "Due to high number of your crew and multiple misunderstandings, some of the members have deserted to Pirate Island"
		 * 	+ show how many/what cards have been taken
		 */
		chancePack.add(new ChanceCard(16, "You've gained treasure!"));
		
		/*
		 * Same as above - value = 6
		 * Sailing strength threshold = 11
		 */
		chancePack.add(new ChanceCard(17, "You've gained treasure!"));
		
		/*
		 * Value - 4
		 * This time it adds 2 crew cards if sailing strength <= 7
		 * "Two pirates have joined your crew!"
		 */
		chancePack.add(new ChanceCard(18, "You've gained treasure!"));
		
		
		//Return all cards player has to pirate island and draw same number of cards from the top
		chancePack.add(new ChanceCard(19, "Your captain got so drunk in one of the taverns that he and his drinking partner boarded each other's ships!"
				+ " Looks like both crews are stuck with new captains from now on. "
				+ "Your crew cards have been shuffled."));
		
		/*
		 * If 1+ other players at treasure island's coast - let player choose who to give the two random cards to
		 * and get two random cards from that player as well (an exchange) with a message "You've received two other crewmen in exchange."
		 * If no players available: "The trader turned out to be a pirate! You get nothing in return!"
		 */
		chancePack.add(new ChanceCard(20, "You've decided to trade away two of your most insubordinate crewmen."));
		
		chancePack.add(new ChanceCard(21, "You've captured Long John Silver! You can trade him for crew at one of the other ports!")); //5 in value, crew only. Any one treasure if getting him FROM a port
		
	
		//Remove lowest value cards until number of cards <= 7 (FOR ALL PLAYERS!)
		chancePack.add(new ChanceCard(22, "You've released Yellow Fever from an old trunk! All ships' crews have suffered casualties."));
		
		chancePack.add(new ChanceCard(23, "You've found doubloons! Value: 5"));
		chancePack.add(new ChanceCard(24, "You've found Pieces of Eight! Value: 4"));
		chancePack.add(new ChanceCard(25, "You've found Kidd's chart! Sail to Anchor Bay, near Pirate Island, to use it to find valuable treasure!"));
		chancePack.add(new ChanceCard(26, "You've found Kidd's chart! Sail to Anchor Bay, near Pirate Island, to use it to find valuable treasure!"));
		
		/*
		 * Take up to 5 value from treasureisland.
		 * If no treasure available - "No treasure available. 3 pirates from Pirate Island join your crew instead."
		 */
		chancePack.add(new ChanceCard(27, "You've gained treasure!"));
		//#28 is up above after 15 since it's the same
		chancePack.shuffle();
	}
	
	/**
	 * Create all the islands
	 * IF ORDER CHANGED CHANGE IN INITTREASURE() AND ASSIGNTREASURE()
	 * @see initTreasure
	 * @see assignTreasure
	 */
	private void initIslands(){
		islandList = new Island[3];
		islandList[0] = new FlatIsland(new Position(2,16), new Position(2,3)); 
		islandList[1] = new TreasureIsland(new Position(9,9), new Position(3,3));
		islandList[2] = new PirateIsland(new Position(17,2), new Position(2,3));
	}
	
	/**
	 * Create 20 pieces of treasure and assign it to TreasureIsland
	 */
	private void initTreasure(){
		int islandIndex = 1;
		treasureList = new Treasure[20];
		int index = 0;
		for (int i = 0; i < 4; i++){
			treasureList[index++] = new Treasure(TreasureType.DIAMOND, 5, (TreasureIsland)islandList[islandIndex]); //assign to TreasureIsland
			treasureList[index++] = new Treasure(TreasureType.RUBY, 5, (TreasureIsland)islandList[islandIndex]);
			treasureList[index++] = new Treasure(TreasureType.GOLD, 4, (TreasureIsland)islandList[islandIndex]);
			treasureList[index++] = new Treasure(TreasureType.PEARL, 3, (TreasureIsland)islandList[islandIndex]);
			treasureList[index++] = new Treasure(TreasureType.RUM, 2, (TreasureIsland)islandList[islandIndex]);
		}
	}
	
	/**
	 * Assigns initial cards to players and ports
	 */
	private void assignCards(){
		try {
			//Assign 5 cards to each player
			for (int i = 0; i < 5; i++){
				playerList[0].addCard(crewPack.removeCard());
				playerList[1].addCard(crewPack.removeCard());
				playerList[2].addCard(crewPack.removeCard());
				playerList[3].addCard(crewPack.removeCard());
			}
			
			//Add 2 cards  to ports
			for (int i = 4; i < 6; i++){
				portList[i].addCard(crewPack.removeCard());
				portList[i].addCard(crewPack.removeCard());
			}
			
			//Assign the rest to PirateIsland and make the crewPack point to the one in the PirateIsland
			while (crewPack.size() != 0){
				((PirateIsland) islandList[2]).addCard(crewPack.removeCard());
			}
			crewPack = ((PirateIsland) islandList[2]).getPack();
			
			
		} catch (EmptyPackException ex){
			System.out.print(ex.getMessage());
		}
		
		// Add all chance cards to treasure island and make the chancePack point to the one in the TreasureIsland
		while (chancePack.size() != 0){
			try {
				((TreasureIsland)islandList[1]).addCard(chancePack.removeCard());
			} catch (EmptyPackException e) {
				e.printStackTrace();
			}
		}
		chancePack = ((TreasureIsland)islandList[1]).getPack();
	}
	
	/**
	 * Assigns some of the treasure to non-player ports
	 */
	private void assignTreasure(){
		//Assign treasure based on port's value making up to 8
		for (int i = 4; i < 6; i++){
			Port port = portList[i];
			int value = 0;
			//Get port's value
			for (Treasure t : port.getTreasureList()){
				value += t.getValue();
			}
			for (Card c : port.getCards()){
				value += ((CrewCard) c).getValue();
			}
			//check how much is missing
			int missing = 8 - value;
			ArrayList<Treasure> treasures = ((TreasureIsland)islandList[1]).getTreasureList();
			int lowestAvailable = 5;
			for (int ii = 0; ii < treasures.size(); ii++){
				if (lowestAvailable > treasures.get(ii).getValue()){
					lowestAvailable = treasures.get(ii).getValue();
				}
			}
			while (missing >= lowestAvailable){ //lowest treasure we can draw is the limit
				
				//Search for a matching treasure
				for (int j = 0; j < treasures.size(); j++){
					if (treasures.get(j).getValue() <= missing){ 
						Treasure t = treasures.remove(j);
						missing -= t.getValue();
						port.addTreasure(t);
					}
				}
				//Update lowestAvailable
				for (int ii = 0; ii < treasures.size(); ii++){
					if (lowestAvailable > treasures.get(ii).getValue()){
						lowestAvailable = treasures.get(ii).getValue();
					}
				}
			}
		}
	}
}
