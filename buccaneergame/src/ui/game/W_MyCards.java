/*
 * @(#) W_MyCards.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */

package ui.game;

import java.util.ArrayList;
import java.util.stream.IntStream;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import uk.ac.aber.cs211.group07.system.GameEngine;
import uk.ac.aber.cs211.group07.system.helpers.PortInteraction;
import uk.ac.aber.cs211.group07.system.resources.Card;
import uk.ac.aber.cs211.group07.system.resources.ChanceCard;
import uk.ac.aber.cs211.group07.system.resources.CrewCard;
import uk.ac.aber.cs211.group07.system.resources.Player;
import uk.ac.aber.cs211.group07.system.resources.Treasure;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

/**
 * Controller for the "My Cards" screen
 * 
 * @author haa14
 * @version 0.1 - Initial creation /haa14
 * @version 0.2 - Completed basic functionality for MyCards /haa14
 * @version 0.3 - Tests completed and modifications made to comply with tests.
 * @version 0.31 - Merger /rob27
 * @version 0.4 - Fixed to allow card images to work correctly
 * @version 0.5 - Tooltips working for cards
 * @version 0.6 - FXML file modified to fix sizes
 * @version 0.7 - Modified to allow scoreboard to work appropriately with maingame
 * @version 0.71 - MyCards treasure counter updated
 * @version 0.72 - Modified to comply with QA9
 * @version 0.8 - Fixed the total score to actually represent the port score, not treasure count
 */
public class W_MyCards {
	private GameEngine engine;
	private Stage caller;
	private Stage self;
	private String string;
	private int movementSpeed;
	private int fightingStrength;

	public void setString(String s) {
		string = s;
	}

	//Declaration for labels in window
	@FXML
	HBox cardsInHand;

	@FXML
	private Button menuBtn;

	@FXML
	private Label playerId;

	@FXML
	private Label pRmId;

	@FXML
	private Label pPId;

	@FXML
	private Label pGId;

	@FXML
	private Label pRId;

	@FXML
	private Label pDId;

	@FXML
	private ScrollPane hand;

	@FXML
	private Label treasureOnShip;

	@FXML
	private Label portId;

	@FXML
	private Label currentScoreId;

	@FXML
	private Label pDS;

	@FXML
	private Label pGS;

	@FXML
	private Label pRS;

	@FXML
	private Label pPS;

	@FXML
	private Label pRmS;

	@FXML
	ImageView playerIcon;

	public void setUpController(Stage thisStage, GameEngine engineinput, Stage selfinput) {
		self = thisStage;
		caller = selfinput;
		engine = engineinput;
		int[] treasureCount;

		Player player = engine.getCurrentPlayer();

		playerId.setText(player.getName());
		portId.setText(player.getHomePort().getName());

		displayCards(player.getHand(), player.getChanceCards());

		// Retrieve port treasure counts for the current player
		treasureCount = getTreasureCount(player, player.getHomePort().getTreasureList());
		pDId.setText(Integer.toString(treasureCount[0]));
		pGId.setText(Integer.toString(treasureCount[1]));
		pRId.setText(Integer.toString(treasureCount[2]));
		pPId.setText(Integer.toString(treasureCount[3]));
		pRmId.setText(Integer.toString(treasureCount[4]));
		currentScoreId.setText(String.valueOf(PortInteraction.calculateTreasure(player.getHomePort().getTreasureList())));

		//Retrieve ship treasure counts for the current player
		treasureCount = getTreasureCount(player, player.getShip().getTreasureList());
		pDS.setText(Integer.toString(treasureCount[0]));
		pGS.setText(Integer.toString(treasureCount[1]));
		pRS.setText(Integer.toString(treasureCount[2]));
		pPS.setText(Integer.toString(treasureCount[3]));
		pRmS.setText(Integer.toString(treasureCount[4]));

		//Change the player icon to the relevant icon (based on their turn position)
		addPlayerIcon(engine);
		
	}

	public void displayCards(ArrayList<CrewCard> crewCards, ArrayList<ChanceCard> chanceCards) {
		if (crewCards != null && crewCards.size() > 0) {
			for (int i = 0; i < crewCards.size(); i++) {
				CrewCard currentCard = (CrewCard) crewCards.get(i);
				int value = currentCard.getValue();
				boolean isRed = currentCard.isRed();
				if (isRed) {
					switch (value) {
					case 1:
						addCrewCardToPane("ui/img/CrewCard_Red1.png", currentCard);
						break;
					case 2:
						addCrewCardToPane("ui/img/CrewCard_Red2.png", currentCard);
						break;
					case 3:
						addCrewCardToPane("ui/img/CrewCard_Red3.png", currentCard);
						break;
					}
				} else {
					switch (value) {
					case 1:
						addCrewCardToPane("ui/img/CrewCard_Black1.png", currentCard);
						break;
					case 2:
						addCrewCardToPane("ui/img/CrewCard_Black2.png", currentCard);
						break;
					case 3:
						addCrewCardToPane("ui/img/CrewCard_Black3.png", currentCard);
						break;
					}
				}
			}
			for (int i = 0; i < chanceCards.size(); i++) {
				ChanceCard currentCard = (ChanceCard) chanceCards.get(i);
				int value = currentCard.getNumber();
				switch (value) {
				case 21:
					addChanceCardToPane("ui/img/ChanceCard_LongJohnSilver.png", currentCard);
					// addTooltip(true);
					break; // Long John Silver
				case 23:
					addChanceCardToPane("ui/img/ChanceCard_Doubloons.png", currentCard);
					break; // Doubloons
				case 24:
					addChanceCardToPane("ui/img/ChanceCard_PiecesOfEight.png", currentCard);
					break; // Pieces of Eight
				case 25:
				case 26:
					addChanceCardToPane("ui/img/ChanceCard_KiddsChart.png", currentCard);
					break; // Kidd's chart
				case 99:
					addChanceCardToPane("ui/img/ChanceCard_BlankCard.png", currentCard);
					break;
				}
			}
		}
	}

	private void addPlayerIcon(GameEngine engine) {
		Image icon = null;
		Player player = engine.getCurrentPlayer();
		int indexPos = engine.getPlayerIndex(player);
		switch (indexPos) {
		case 0:
			icon = new Image("ui/img/PlayerIcon_Red.png");
			break;
		case 1:
			icon = new Image("ui/img/PlayerIcon_Blue.png");
			break;
		case 2:
			icon = new Image("ui/img/PlayerIcon_Yellow.png");
			break;
		case 3:
			icon = new Image("ui/img/PlayerIcon_Green.png");
			break;
		}
		playerIcon.setImage(icon);
	}

	// TODO: Add the tooltips for crew cards
	// TODO: Find a way to change the delay for tooltip appearance
	private void addCrewCardToPane(String url, CrewCard currentCard) {
		Image card = new Image(url, 100, 100, false, false);
		ImageView image = new ImageView();
		image.setImage(card);
		Tooltip tip = new Tooltip(
				"The total value of all your cards gives your MOVEMENT SPEED, and the difference between red and black card value gives your FIGHTING STRENGTH.\n This card has a value of "
						+ currentCard.getValue() + ".");
		Tooltip.install(image, tip);
		cardsInHand.getChildren().add(image);
	}

	private void addChanceCardToPane(String url, ChanceCard currentCard) {
		Image card = new Image(url, 100, 100, false, false);
		ImageView image = new ImageView();
		image.setImage(card);
		Tooltip tip = new Tooltip(currentCard.getText());
		Tooltip.install(image, tip);
		cardsInHand.getChildren().add(image);
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
		for (int i = 0; i < 5; i++) {
			if (treasureCount[i] == 0) {
				treasureCount[i] = 0;
			}
		}
		return treasureCount;
	}

	/**
	 * Closes the window if button is pressed
	 */
	public void confirm() {
		self.close();
	}
}
