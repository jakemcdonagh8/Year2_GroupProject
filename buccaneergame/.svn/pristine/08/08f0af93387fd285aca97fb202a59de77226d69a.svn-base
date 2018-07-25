/*
 * @(#) TestPack.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs211.group07.system.tests;

import static org.junit.Assert.*;
import org.junit.Test;

import uk.ac.aber.cs211.group07.system.exceptions.EmptyPackException;
import uk.ac.aber.cs211.group07.system.resources.Card;
import uk.ac.aber.cs211.group07.system.resources.ChanceCard;
import uk.ac.aber.cs211.group07.system.resources.CrewCard;
import uk.ac.aber.cs211.group07.system.resources.Pack;

/**
 * Tests addressing system.resources.Pack class
 * @author Robert Buchan Terrey
 * @version 0.1 - 21/02/17 - initial creation /rob27
 * @version 0.2 - 01/04/17 - added testEmptyExceptionCrew1, testEmptyExceptionCrew2, testEmptyExceptionChance1,
 * 							testEmptyExceptionChance2, testDefaultOrder, testFixedRemoval, testShuffle /kac12
 * @version 1.0 - 05/05/17 - Ready for initial creation
 */

public class TestPack {
	
	@Test
	public void testSize() throws EmptyPackException{
		Card card0 = new CrewCard(3,false);
		Card card1 = new CrewCard(2,false);
		Card card2 = new CrewCard(1,false);

		Pack<Card> testPack = new Pack<Card>();
		System.out.println(testPack); //pack exists
		testPack.add(card0);
		testPack.add(card1);
		testPack.add(card2);
		int packSize = testPack.size();
		assertTrue("Pack size should be equal to 3", 3==packSize);
		assertFalse("Pack size should not be equal to 2", 2==packSize);
		assertFalse("Pack size should not be equal to 4", 4==packSize);
	}
	
	@Test(expected = EmptyPackException.class)
	public void testEmptyExceptionCrew1() throws EmptyPackException{
		Pack<CrewCard> pack = new Pack<CrewCard>();
		pack.removeCard();
	}
	
	@Test(expected = EmptyPackException.class)
	public void testEmptyExceptionCrew2() throws EmptyPackException{
		Pack<CrewCard> pack = new Pack<CrewCard>();
		pack.add(new CrewCard(1, false));
		pack.removeCard();
		pack.removeCard();
	}
	
	@Test(expected = EmptyPackException.class)
	public void testEmptyExceptionChance1() throws EmptyPackException{
		Pack<ChanceCard> pack = new Pack<ChanceCard>();
		pack.removeCard();
	}
	
	@Test(expected = EmptyPackException.class)
	public void testEmptyExceptionChance2() throws EmptyPackException{
		Pack<ChanceCard> pack = new Pack<ChanceCard>();
		pack.add(new ChanceCard(1, " "));
		pack.removeCard();
		pack.removeCard();
	}
	
	@Test
	public void testDefaultOrder() throws EmptyPackException{
		Pack<CrewCard> pack = new Pack<CrewCard>();
		pack.add(new CrewCard(1, false));
		pack.add(new CrewCard(2, false));
		assertEquals("Top card drawn should have value 1.", pack.removeCard().getValue(), 1);
	}
	
	@Test
	public void testFixedRemoval(){
		Pack<CrewCard> pack = new Pack<CrewCard>();
		CrewCard card = new CrewCard(1, false);
		pack.add(card);
		pack.add(new CrewCard(1, false));
		pack.add(new CrewCard(1, false));
		pack.add(new CrewCard(1, false));
		pack.removeCard(card);
		assertEquals("Pack should be of size 3", pack.size(), 3);
		assertFalse("Pack should not contain the card", pack.getCards().contains(card));
	}
	
	@Test
	public void testShuffle(){
		Pack<CrewCard> pack = new Pack<CrewCard>();
		for (int i = 0; i < 100; i++){
			pack.add(new CrewCard(i, false));
		}
		for (int i = 0; i < 100; i++){
			pack.add(new CrewCard(i, true));
		}
		Card card50 = pack.getCards().get(50);
		int index50 = pack.getCards().indexOf(card50);
		Card card150 = pack.getCards().get(150);
		int index150 = pack.getCards().indexOf(card150);
		Card card160 = pack.getCards().get(160);
		int index160 = pack.getCards().indexOf(card160);
		System.out.println("TestShuffle: index50:" + pack.getCards().indexOf(card50));
		System.out.println("TestShuffle: index150:" + pack.getCards().indexOf(card150));
		System.out.println("TestShuffle: index160:" + pack.getCards().indexOf(card160));
		pack.shuffle();
		System.out.println("TestShuffle: shuffled50:" + pack.getCards().indexOf(card50));
		System.out.println("TestShuffle: shuffled150:" + pack.getCards().indexOf(card150));
		System.out.println("TestShuffle: shuffled160:" + pack.getCards().indexOf(card160));
		assertFalse("At least one card should have a different position", pack.getCards().indexOf(card50) == index50);
		assertFalse("At least one card should have a different position", pack.getCards().indexOf(card150) == index150);
		assertFalse("At least one card should have a different position", pack.getCards().indexOf(card160) == index160);
	}

}
