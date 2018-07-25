/*
 * @(#) TestCard.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs211.group07.system.tests;

import static org.junit.Assert.*;
import org.junit.Test;

import uk.ac.aber.cs211.group07.system.resources.ChanceCard;
import uk.ac.aber.cs211.group07.system.resources.CrewCard;

/**
 * Tests addressing system.resources.Card class
 * @author Robert Buchan Terrey
 * @version 0.1 - 21/02/17 - initial creation /rob27
 * @version 0.2 - 01/04/17 - added: testChanceNum, testChanceText, added tests for blacks and different
 * 							cards in testRed, changed testRed()'s name to testColor() /kac12
 * @version 1.0 - 05/05/17 - Ready for initial release
 */

public class TestCard {
	@Test
	public void testValue(){
		CrewCard cardVal3 = new CrewCard(3,true);
		assertTrue("Pack size should be equal to 3", 3==cardVal3.getValue());
	}
	
	@Test
	public void testColor(){
		CrewCard cardVal3 = new CrewCard(3,true);
		CrewCard cardBlack = new CrewCard(3, false);
		assertTrue("Card should be red", cardVal3.isRed());
		assertTrue("Card should not be black", !cardVal3.isBlack());
		assertTrue("Card should not be red", !cardBlack.isRed());
		assertTrue("Card should be black", cardBlack.isBlack());
	}
	
	@Test
	public void testChanceNum(){
		ChanceCard card1 = new ChanceCard(1, "");
		ChanceCard card2 = new ChanceCard(15, "");
		assertEquals("Cardnum should be 1", card1.getNumber(), 1);
		assertEquals("Cardnum should be 15", card2.getNumber(), 15);
	}
	
	@Test
	public void testChanceText(){
		ChanceCard card1 = new ChanceCard(1, "TeXt1.\nNewline");
		ChanceCard card2 = new ChanceCard(-1, "Some regular text.");
		assertEquals("Given texts don't match.", card1.getText(), "TeXt1.\nNewline");
		assertEquals("Given texts don't match.", card2.getText(), "Some regular text.");
	}
}
