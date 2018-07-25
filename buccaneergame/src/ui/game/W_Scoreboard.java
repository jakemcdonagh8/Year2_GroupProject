/*
 * @(#) W_Scoreboard.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */
package ui.game;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import uk.ac.aber.cs211.group07.system.GameEngine;
import uk.ac.aber.cs211.group07.system.helpers.PortInteraction;
import uk.ac.aber.cs211.group07.system.resources.Player;
import uk.ac.aber.cs211.group07.system.resources.Treasure;

/**
 * 
 * Controller for the Scoreboard window.
 * 
 * @author haa14
 * @version 0.1 - 01/05/2017 - Initial creation
 * @version 0.2 - 01/05/2017 - Added & Finished setUpController and
 *          getTreasureCount
 * @version 0.3 - 02/05/2017 - Fully commented
 * @version 0.31 - 04/05/2017 - Fixed setUpController() to match UML; fixed confirm button /kac12
 * @version 1.0 -05/05/17 - Ready for initial release /haa14 
 */
public class W_Scoreboard {
	Stage self;
	Stage caller;
	String string;
	public void setString(String s) {
		string = s;
	}
	// Declarations for all variables shown on Scoreboard

	@FXML
	Label playerOne;

	@FXML
	Label playerTwo;

	@FXML
	Label playerThree;

	@FXML
	Label playerFour;

	@FXML
	Label currentScoreOne;

	@FXML
	Label currentScoreTwo;

	@FXML
	Label currentScoreThree;

	@FXML
	Label currentScoreFour;

	@FXML
	Label portOne;

	@FXML
	Label portTwo;

	@FXML
	Label portThree;

	@FXML
	Label portFour;

	@FXML
	Label pOneD;

	@FXML
	Label pOneG;

	@FXML
	Label pOneR;

	@FXML
	Label pOneP;

	@FXML
	Label pOneRm;

	@FXML
	Label pTwoD;

	@FXML
	Label pTwoG;

	@FXML
	Label pTwoR;

	@FXML
	Label pTwoP;

	@FXML
	Label pTwoRm;

	@FXML
	Label pThreeD;

	@FXML
	Label pThreeG;

	@FXML
	Label pThreeR;

	@FXML
	Label pThreeP;

	@FXML
	Label pThreeRm;

	@FXML
	Label pFourD;

	@FXML
	Label pFourG;

	@FXML
	Label pFourR;

	@FXML
	Label pFourP;

	@FXML
	Label pFourRm;

	@FXML
	private Button menuBtn;

	/**
	 * Setting up the controller, provides external requirements to functions
	 * @param thisStage,
	 *            engine
	 * 
	 */
	public void setUpController(Stage thisStage, Stage caller, GameEngine engine) {
		self = thisStage;
		this.caller = caller;
		int[] treasureCount;
		//Button goes back to menu pls
		menuBtn.setOnAction(event -> confirm());
		
		// Get all the players from the game engine
		Player pOne = engine.getPlayer(0);
		Player pTwo = engine.getPlayer(1);
		Player pThree = engine.getPlayer(2);
		Player pFour = engine.getPlayer(3);

		// Retrieve player names
		playerOne.setText(pOne.getName());
		playerTwo.setText(pTwo.getName());
		playerThree.setText(pThree.getName());
		playerFour.setText(pFour.getName());

		// Retrieve player home ports
		portOne.setText(pOne.getHomePort().getName());
		portTwo.setText(pTwo.getHomePort().getName());
		portThree.setText(pThree.getHomePort().getName());
		portFour.setText(pFour.getHomePort().getName());

		// Retrieve port treasure counts for every player
		treasureCount = getTreasureCount(pOne, pOne.getHomePort().getTreasureList());
		pOneD.setText(Integer.toString(treasureCount[0]));
		pOneG.setText(Integer.toString(treasureCount[1]));
		pOneR.setText(Integer.toString(treasureCount[2]));
		pOneP.setText(Integer.toString(treasureCount[3]));
		pOneRm.setText(Integer.toString(treasureCount[4]));
		currentScoreOne.setText(String.valueOf(PortInteraction.calculateTreasure(pOne.getHomePort().getTreasureList()))); //Sums the treasure value

		treasureCount = getTreasureCount(pTwo, pTwo.getHomePort().getTreasureList());
		pTwoD.setText(Integer.toString(treasureCount[0]));
		pTwoG.setText(Integer.toString(treasureCount[1]));
		pTwoR.setText(Integer.toString(treasureCount[2]));
		pTwoP.setText(Integer.toString(treasureCount[3]));
		pTwoRm.setText(Integer.toString(treasureCount[4]));
		currentScoreTwo.setText(String.valueOf(PortInteraction.calculateTreasure(pTwo.getHomePort().getTreasureList())));

		treasureCount = getTreasureCount(pThree, pThree.getHomePort().getTreasureList());
		pThreeD.setText(Integer.toString(treasureCount[0]));
		pThreeG.setText(Integer.toString(treasureCount[1]));
		pThreeR.setText(Integer.toString(treasureCount[2]));
		pThreeP.setText(Integer.toString(treasureCount[3]));
		pThreeRm.setText(Integer.toString(treasureCount[4]));
		currentScoreThree.setText(String.valueOf(PortInteraction.calculateTreasure(pThree.getHomePort().getTreasureList())));

		treasureCount = getTreasureCount(pFour, pFour.getHomePort().getTreasureList());
		pFourD.setText(Integer.toString(treasureCount[0]));
		pFourG.setText(Integer.toString(treasureCount[1]));
		pFourR.setText(Integer.toString(treasureCount[2]));
		pFourP.setText(Integer.toString(treasureCount[3]));
		pFourRm.setText(Integer.toString(treasureCount[4]));
		currentScoreFour.setText(String.valueOf(PortInteraction.calculateTreasure(pFour.getHomePort().getTreasureList())));
		
	}

	/**
	 * A function that goes through a port's list of treasure and counts how
	 * many of a particular type it has.
	 * 
	 * @param type
	 *            - the type of treasure the function is searching for
	 * @param treasure
	 *            - an array list of treasure in a previously specified port
	 * @return treasureCount - how many treasures of the type parameter the
	 *         player's port is storing
	 */
	private int[] getTreasureCount(Player player, ArrayList<Treasure> treasure) {
		/*
		 * Array positions: 0 - Diamonds 1 - Gold 2 - Rubies 3 - Pearls 4 - Rum
		 */
		
		int[] treasureCount = new int[5];
		
		// Iterate over all treasure that a player owns
		if (treasure != null && treasure.size() > 0) {
			for (int i = 0; i < treasure.size(); i++) {
				Treasure current = treasure.get(i); // Get the next treasure
				String name = current.getType().toString(); // Get the name of
															// the treasure

				// Check the type of treasure
				switch (name) {
				case "DIAMOND":
					treasureCount[0]++;
					break;
				case "GOLD":
					treasureCount[1]++;
					break;
				case "RUBY":
					treasureCount[2]++;
					break;
				case "PEARL":
					treasureCount[3]++;
					break;
				case "RUM":
					treasureCount[4]++;
					break;
				}
				
			}
		}
		return treasureCount;
	}

	/**
	 * Closes the window if button is pressed
	 */
	public void confirm() {
		self.close();
		caller.show();
	}

}
