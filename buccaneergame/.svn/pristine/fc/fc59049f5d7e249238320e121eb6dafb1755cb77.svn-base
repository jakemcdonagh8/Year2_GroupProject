/*
 * @(#) Pack.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */
package uk.ac.aber.cs211.group07.system.resources;

import java.util.ArrayList;

import uk.ac.aber.cs211.group07.system.exceptions.EmptyPackException;

/**
 * Imitates a real-life 'Pack' behaviour of either CrewCard or ChanceCard objects
 * Top of the deck - first item in the ArrayList
 * Bottom of the deck - last item in the ArrayList
 * @author Kamil Cupial
 * @version 0.1 - 18/02/17 - initial creation /kac12
 * @version 0.2 - 21/02/17 - initialisation of ArrayList at creation /rob27
 * @version 1.0 - 05/05/17 - Ready for initial release /haa14
 */
public class Pack<T extends Card> {

	private ArrayList<T> cards; //Card objects stored in the ArrayList
	
	/**
	 * Default constructor
	 */
	public Pack(){	
		cards = new ArrayList<T>();
	}
	
	/**
	 * Removes a card from the top of the Pack and returns it
	 * @return a CrewCard/ChanceCard from the top of the pack
	 * @throws EmptyPackException thrown if the Pack is empty
	 */
	public T removeCard() throws EmptyPackException{
		if (cards.isEmpty()) throw new EmptyPackException();
		return cards.remove(0);
	}
	
	/**
	 * Removes the card from the Pack
	 * @param card
	 */
	public void removeCard(T card){
		cards.remove(card);
	}
	
	/**
	 * Returns the card to the bottom of the pack/deck
	 * @param card	cart to insert/add
	 */
	public void add(T card){
		cards.add(card);
	}
	
	/**
	 * Shuffles the Pack randomly, randomizing the order of the cards
	 */
	public void shuffle(){
		ArrayList<T> randomized = new ArrayList<T>();
		while (!cards.isEmpty()){
			int rand = (int)(Math.random() * (cards.size() - 1));
			randomized.add(cards.remove(rand));
		}
		cards = randomized;
	}
	
	/**
	 * 
	 * @return current size of the Pack
	 */
	public int size(){
		return cards.size();
	}
	
	/**
	 * 
	 * @return whether the Pack is empty
	 */
	public boolean isEmpty(){
		return cards.isEmpty();
	}
	
	/**
	 * 
	 * @return ArrayList containing all the cards in the pack
	 */
	public ArrayList<T> getCards(){
		return cards;
	}
}
