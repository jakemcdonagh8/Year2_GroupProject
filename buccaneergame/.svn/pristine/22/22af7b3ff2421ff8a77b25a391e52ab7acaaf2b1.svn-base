/*
 * @(#) TestGameSetup.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs211.group07.system.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import uk.ac.aber.cs211.group07.system.Board;
import uk.ac.aber.cs211.group07.system.GameSetup;
import uk.ac.aber.cs211.group07.system.exceptions.InvalidPositionException;
import uk.ac.aber.cs211.group07.system.resources.AnchorBay;
import uk.ac.aber.cs211.group07.system.resources.Card;
import uk.ac.aber.cs211.group07.system.resources.ChanceCard;
import uk.ac.aber.cs211.group07.system.resources.CrewCard;
import uk.ac.aber.cs211.group07.system.resources.FlatIsland;
import uk.ac.aber.cs211.group07.system.resources.Island;
import uk.ac.aber.cs211.group07.system.resources.PirateIsland;
import uk.ac.aber.cs211.group07.system.resources.Player;
import uk.ac.aber.cs211.group07.system.resources.PlayerPort;
import uk.ac.aber.cs211.group07.system.resources.Port;
import uk.ac.aber.cs211.group07.system.resources.Treasure;
import uk.ac.aber.cs211.group07.system.resources.TreasureIsland;
import uk.ac.aber.cs211.group07.system.resources.TreasureType;
/**
 * Tests addressing system.resources.GameSetup class
 * @author Kamil Cupial
 * @version 0.1 - 02/04/17 - initial creation (last method tested: initChancePack()) /kac12
 * @version 0.2 - 15/04/17 - added remaining tests /kac12
 * @version 0.3 - 21/04/17 - added TestIslandPositions /kac12
 * @version 0.1
 */
public class TestGameSetup {

	@Test(expected = NullPointerException.class)
	public void testConstructorNumber() throws NullPointerException{
		Player[] array = new Player[5];
		array[0] = new Player();
		array[0].setName("test1");
		array[1] = new Player();
		array[1].setName("test2");
		array[2] = new Player();	
		array[2].setName("test3");
	}
	
	@Test(expected = NullPointerException.class)
	public void testConstructorNames() throws NullPointerException{
		Player[] array = new Player[5];
		array[0] = new Player();
		array[1] = new Player();
		array[2] = new Player();
		array[3] = new Player();
	}
	
	@Test
	public void testConstructorValid(){
		Player[] array = new Player[5];
		array[0] = new Player();
		array[1] = new Player();
		array[2] = new Player();
		array[3] = new Player();
		array[0].setName("test1");
		array[1].setName("test2");
		array[2].setName("test3");
		array[3].setName("test4");
	}
	
	/**
	 * Tests the board construction and all the assignments
	 * (without checking the logic and functional requirements yet)
	 */
	@Test
	public void testGameSetup(){
		/* Prepare player names */
		Player[] array = new Player[5];
		array[0] = new Player();
		array[1] = new Player();
		array[2] = new Player();
		array[3] = new Player();
		array[0].setName("testPlayer1");
		array[1].setName("testPlayer2");
		array[2].setName("testPlayer3");
		array[3].setName("testPlayer4");
	}
	
	/**
	 * Tests both positions and names
	 */
	@Test 
	public void testPortPositions(){
		Board board = getBoard();
		for (int i = 0; i < 7; i++){
			Port port = board.getPort(i);
			int x = port.getPos().x;
			int y = port.getPos().y;
			switch (port.getName()){
			case "Port of London":
				System.out.println("testPortPositions: Port of London recognised.");
				assertEquals("X of the port should be 1.", 1, x);
				assertEquals("Y of the port should be 14.", 14, y);
				break;
			case "Port of Genoa":
				System.out.println("testPortPositions: Port of Genoa recognised.");
				assertEquals("X of the port should be 7.", 7, x);
				assertEquals("Y of the port should be 1.", 1, y);
				break;
			case "Port of Marseilles":
				System.out.println("testPortPositions: Port of Marseilles recognised.");
				assertEquals("X of the port should be 20.", 20, x);
				assertEquals("Y of the port should be 7.", 7, y);
				break;
			case "Port of Cadiz":
				System.out.println("testPortPositions: Port of Cadiz recognised.");
				assertEquals("X of the port should be 14.", 14, x);
				assertEquals("Y of the port should be 20.", 20, y);
				break;
			case "Port of Venice":
				System.out.println("testPortPositions: Port of Venice recognised.");
				assertEquals("X of the port should be 14.", 1, x);
				assertEquals("Y of the port should be 20.", 7, y);
				break;
			case "Port of Amsterdam":
				System.out.println("testPortPositions: Port of Amsterdam recognised.");
				assertEquals("X of the port should be 14.", 20, x);
				assertEquals("Y of the port should be 20.", 14, y);
				break;
			case "Anchor Bay":
				System.out.println("testPortPositions: Anchor Bay recognised.");
				assertEquals("X of the port should be 14.", 20, x);
				assertEquals("Y of the port should be 20.", 1, y);
				break;
			}
		}
		System.out.println();
	}
	
	/**
	 * Tests random port assignment (highly unlikely that fails, retest if it
	 * does to make sure it's not a coincidence)
	 */
	@Test
	public void testPortAssignment(){
		Board board1 = getBoard();
		Board board2 = getBoard();
		Board board3 = getBoard();
		Board board4 = getBoard();
		String p1 = board1.getPlayer(0).getName();
		String p2 = board2.getPlayer(0).getName();
		String p3 = board3.getPlayer(0).getName();
		String p4 = board4.getPlayer(0).getName();
		System.out.println("testPortAssignment: First player on board1: " + p1);
		System.out.println("testPortAssignment: First player on board2: " + p2);
		System.out.println("testPortAssignment: First player on board3: " + p3);
		System.out.println("testPortAssignment: First player on board4: " + p4);
		assertFalse("First player should be different on at least one board.", 
				p1.equals(p2) && p2.equals(p3) && p3.equals(p4));
		System.out.println();
	}
	
	/**
	 * Tests if players are ordered properly (in terms of turns)
	 */
	@Test
	public void testPlayerPortOrder(){
		Board board = getBoard();
		String p1 = board.getPlayer(0).getHomePort().getName();
		String p2 = board.getPlayer(1).getHomePort().getName();
		String p3 = board.getPlayer(2).getHomePort().getName();
		String p4 = board.getPlayer(3).getHomePort().getName();
		assertEquals("First player should have Port of London.", "Port of London", p1 );
		assertEquals("Second player should have Port of Genoa.", "Port of Genoa", p2);
		assertEquals("Third player should have Port of Marseilles.", "Port of Marseilles", p3 );
		assertEquals("Fourth player should have Port of Cadiz.", "Port of Cadiz", p4);
	}
	
	@Test
	public void testCrewCards(){
		int red1 = 0;
		int red2 = 0;
		int red3 = 0;
		int black1 = 0;
		int black2 = 0;
		int black3 = 0;
		Board board = getBoard();
		/* Get Cards from Pirate Island */
		ArrayList<CrewCard> cards = board.getPirateIsland().getCards();
		/* Get cards scattered around Ports */
		for (int i = 0; i < 7; i++){
			for (Card c : board.getPort(i).getCards()){
				if (c instanceof CrewCard){
					cards.add((CrewCard)c);
				}
			}	
		}
		/* Get cards from players */
		for (int i = 0; i < 4; i++){
			cards.addAll(board.getPlayer(i).getHand());
		}
		for (CrewCard c : cards){
			if (c.isBlack()){
				switch (c.getValue()){
				case 1:
					black1++;
					break;
				case 2:
					black2++;
					break;
				case 3:
					black3++;
					break;
				}
			} else if (c.isRed()){ //never too many checks in tests
				switch (c.getValue()){
				case 1:
					red1++;
					break;
				case 2:
					red2++;
					break;
				case 3:
					red3++;
					break;
				}
			}
		}
		/* Finally check if the numbers check out */
		assertEquals("There should be 36 CrewCards scattered around the Board.", 36, cards.size());
		assertEquals("There should be 6 black cards of value 1.", 6, black1);
		assertEquals("There should be 6 black cards of value 2.", 6, black2);
		assertEquals("There should be 6 black cards of value 3.", 6, black3);
		assertEquals("There should be 6 red cards of value 1.", 6, red1);
		assertEquals("There should be 6 red cards of value 2.", 6, red2);
		assertEquals("There should be 6 red cards of value 3.", 6, red3);
	}
	
	@Test
	public void testChanceCardNum(){
		Board board = getBoard();
		ArrayList<ChanceCard> chanceCards = board.getTreasureIsland().getCards();
		assertEquals("There should be 28 chanceCards.", 28, chanceCards.size());
		for (int i = 1; i <= 28; i++){
			boolean found = false;
			for (ChanceCard c : chanceCards){
				if (c.getNumber() == i){
					found = true;
					System.out.println("testChanceCardNum: ChanceCard #" + i + " text: " + c.getText());
				}
			}
			assertTrue("ChanceCards should be numbered 1-28.", found);
		}
		System.out.println();
	}
	
	/**
	 * Tests number of cards objects have in the beginning of the game
	 */
	@Test
	public void testCrewCardNum(){
		Board board = getBoard();
		for (int i=0; i < 4; i++){
			assertEquals("Player " + i + " should have 5 crew cards.", 5, board.getPlayer(i).getCrewCardNum());
		}
		for (int i = 4; i < 6; i++){
			String name = board.getPort(i).getName();
			/* Ports can have onl CrewCards in the beginning of the game, so no need to check for the type of card */
			assertEquals(name + " should have 2 crew cards.", 2, board.getPort(i).getCards().size() );
		}
		assertEquals("TreasureIsland should contain 28 chance cards.", 28, board.getTreasureIsland().getCards().size());
	}
	
	/**
	 * Checks if islands were created and added to board properly
	 */
	@Test
	public void testInitIslands(){
		Board board = getBoard();
		boolean flatIsland = false;
		boolean treasureIsland = false;
		boolean pirateIsland = false;
		for (int i=0; i < 3; i++){
			Island island = board.getIsland(i);
			if (island instanceof TreasureIsland && island != null
					&& board.getTreasureIsland() == island)
				treasureIsland = true;
			else if (island instanceof FlatIsland && island != null
					&& board.getFlatIsland() == island)
				flatIsland = true;
			else if (island instanceof PirateIsland && island != null
					&& board.getPirateIsland() == island)
				pirateIsland = true;
		}
		assertTrue("TreasureIsland should exist in the array, not be null"
				+ "and match the 'getTreasureIsland()' method in board.", treasureIsland);
		assertTrue("FlatIsland should exist in the array, not be null"
				+ "and match the 'getFlatIsland()' method in board.", flatIsland);
		assertTrue("PirateIsland should exist in the array, not be null"
				+ "and match the 'getPirateIsland()' method in board.", pirateIsland);
		
	}
	
	/**
	 * Tests 20 pieces of treasure available on board
	 */
	@Test
	public void testInitTreasure(){
		Board board = getBoard();
		int d = 0;
		int r = 0;
		int g = 0;
		int p = 0;
		int ru = 0;
		for (int i= 0; i < 20; i++){
			Treasure t = board.getTreasure(i);
			if (t.getType() == TreasureType.DIAMOND && t.getValue() == 5)
				d++;
			else if (t.getType() == TreasureType.RUBY && t.getValue() == 5)
				r++;
			else if (t.getType() == TreasureType.GOLD && t.getValue() == 4)
				g++;
			else if (t.getType() == TreasureType.PEARL && t.getValue() == 3)
				p++;
			else if (t.getType() == TreasureType.RUM && t.getValue() == 2)
				ru++;
		}
		assertEquals("There should be 4 diamonds worth 5 on the board.", 4, d);
		assertEquals("There should be 4 rubies worth 5 on the board.", 4, r);
		assertEquals("There should be 4 gold pieces worth 4 on the board.", 4, g);
		assertEquals("There should be 4 pearls worth 3 on the board.", 4, p);
		assertEquals("There should be 4 rums worth 2 on the board.", 4, ru);
	}
	
	/**
	 * Tests if the treasure was assigned to ports correctly
	 */
	@Test
	public void testTreasureAssignment(){
		Board board = getBoard();
		/* Get the lowest-value treasure that was left on treasure island */
		board.getTreasureIsland().sortTreasureInc();
		int lowest = board.getTreasureIsland().removeFirstTreasure().getValue();
		/* Go over non-player, non-anchor-bay ports */
		for (int i =0; i < 7; i++){
			Port p = board.getPort(i);
			if (!(p instanceof PlayerPort) && !(p instanceof AnchorBay)){
				ArrayList<Treasure> treasures = p.getTreasureList();
				ArrayList<Card> cards = p.getCards();
				int value = 0;
				for (Treasure t : treasures)
					value += t.getValue();
				for (Card c : cards)
					value += ((CrewCard) c).getValue();
				System.out.println("testTreasureAssignment: lowest: " + lowest + " | Port value: " + value);
				assertTrue(p.getName() + " can have value closer to 8 than it has, without going over it.", 8-value < lowest);
			}
			
		}
		System.out.println();
	}
	
	/**
	 * Tests the positions of the Islands
	 */
	@Test
	public void testIslandPositions(){
		Board board = getBoard();
		try {
			for (int x = 2; x <= 4; x++){
				for (int y = 16; y <= 19; y++){
					assertTrue("Position ("+x+","+y+") should be occupied by" +
							" flat island", board.getObjectByPos(x, y).get(0) instanceof FlatIsland);
				}
			}
			for (int x = 9; x <= 12; x++){
				for (int y = 9; y <= 12; y++){
					assertTrue("Position ("+x+","+y+") should be occupied by" +
							" treasure island", board.getObjectByPos(x, y).get(0) instanceof TreasureIsland);
				}
			}
			for (int x = 17; x <= 19; x++){
				for (int y = 2; y <= 5; y++){
					assertTrue("Position ("+x+","+y+") should be occupied by" +
							" pirate island", board.getObjectByPos(x, y).get(0) instanceof PirateIsland);
				}
			}
		} catch (InvalidPositionException ex){
			ex.printStackTrace();
		}
	}
	
	
	/**
	 * Gets a prepared Board object
	 * (same as in TestBoard)
	 */
	private Board getBoard(){
		/* Prepare player names */
		Player[] array = new Player[5];
		array[0] = new Player();
		array[1] = new Player();
		array[2] = new Player();
		array[3] = new Player();
		array[0].setName("testPlayer1");
		array[1].setName("testPlayer2");
		array[2].setName("testPlayer3");
		array[3].setName("testPlayer4");
		/* Create GameSetup object and set the game up */
		GameSetup setup = new GameSetup(array);
		Board board = setup.setupGame();
		return board;
	}

}
