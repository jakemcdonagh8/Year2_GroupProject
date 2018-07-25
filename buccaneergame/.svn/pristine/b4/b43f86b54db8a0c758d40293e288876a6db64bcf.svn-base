package uk.ac.aber.cs211.group07.system.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.ac.aber.cs211.group07.system.exceptions.EmptyPackException;
import uk.ac.aber.cs211.group07.system.exceptions.InvalidDirectionException;
import uk.ac.aber.cs211.group07.system.exceptions.InvalidMoveException;
import uk.ac.aber.cs211.group07.system.resources.ChanceCard;
import uk.ac.aber.cs211.group07.system.resources.CrewCard;
import uk.ac.aber.cs211.group07.system.resources.Direction;
import uk.ac.aber.cs211.group07.system.resources.Player;
import uk.ac.aber.cs211.group07.system.resources.PlayerPort;
import uk.ac.aber.cs211.group07.system.resources.Position;
import uk.ac.aber.cs211.group07.system.resources.Ship;

public class testPlayer {
	
	@Test
	public void testNameAndShip(){
		Player player = new Player();
		Ship ship = new Ship(player);
		String name = "testPlayer";
		player.setName(name);
		player.setShip(ship);
		assertEquals("Player name should be testPlayer", name, player.getName());
		assertEquals("Player should have a ship assigned", ship, player.getShip());
	}
	
	@Test
	public void testMoving() 
	throws InvalidDirectionException, InvalidMoveException{
		Player player = new Player();
		Ship ship = new Ship(player);
		player.setShip(ship);
		System.out.println(ship.getOrientation());
		
		player.turn(Direction.NE);
		assertEquals("Direction should be set to NE", Direction.NE, ship.getOrientation());
		Position pos = new Position(1,1);
		ship.setPos(pos);
		
		player.move(3);
		assertEquals("X should be 4", 4, ship.getPos().x);
		assertEquals("Y should be 4", 4, ship.getPos().y);		
	}
	
	@Test
	public void testPort(){
		Player player = new Player();
		Position pos = new Position(1,1);
		Ship ship = new Ship(player);
		player.setShip(ship);
		PlayerPort port = new PlayerPort("Test", pos);
		player.setHomePort(port);
		
		assertEquals("Player should have a home port", port, player.getHomePort());
	}
	
	@Test
	public void testCards() 
	throws EmptyPackException{
		Player player = new Player();
		ChanceCard card1 = new ChanceCard(1, "");
		ChanceCard card2 = new ChanceCard(15, "");
		CrewCard cardVal3 = new CrewCard(3,true);
		CrewCard cardBlack = new CrewCard(3, false);
		CrewCard cardRed = new CrewCard(2, true);
		player.addCard(card1);
		player.addCard(card2);
		player.addCard(cardVal3);
		player.addCard(cardBlack);
		
		assertEquals("Total number of cards should be 4", 4, player.getPack().size());
		assertEquals("Total number of cards should be 4", 4, player.getCards().size());
		assertEquals("Total number of cards should be 4", 4, player.size());
		assertEquals("Total number of Crew cards should be 2", 2, player.getCrewCardNum());
		assertEquals("Total number of Chance cards should be 2", 2, player.getChanceCardNum());
		
		player.removeCard(card1);
		player.removeCard(cardBlack);
		assertEquals("Total number of Crew cards should be 1", 1, player.getCrewCardNum());
		assertEquals("Total number of Chance cards should be 1", 1, player.getChanceCardNum());
		
		player.addCard(cardRed);
		assertEquals("Moving strenght should be 5", 5, player.getMovingStrength());
		player.addCard(cardBlack);
		assertEquals("Fighting strength should be 2", 2, player.getFightingStrength());
		

		player.sortCardsDec();
		assertEquals("1st card should be of value 3", 3, player.getHand().get(0).getValue());
		assertEquals("2nd card should be of value 3", 3, player.getHand().get(1).getValue());
		assertEquals("3rd card should be of value 2", 2, player.getHand().get(2).getValue());
		
		player.sortCardsInc();
		assertEquals("1st card should be of value 2", 2, player.getHand().get(0).getValue());
		assertEquals("2nd card should be of value 3", 3, player.getHand().get(1).getValue());
		assertEquals("3rd card should be of value 3", 3, player.getHand().get(2).getValue());
		
		assertEquals("Red card", cardRed, player.peek());
		assertEquals("Red card", cardRed, player.draw());
		assertEquals("Total number of Crew cards should be 2", 2, player.getCrewCardNum());
		
	}
	
	
}
