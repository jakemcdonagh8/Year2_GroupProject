/*
 * @(#) InteractionController.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */
package ui.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ui.menus.Popup_Customised;
import uk.ac.aber.cs211.group07.system.GameEngine;
import uk.ac.aber.cs211.group07.system.State;
import uk.ac.aber.cs211.group07.system.exceptions.EmptyPackException;
import uk.ac.aber.cs211.group07.system.exceptions.IllegalOperationException;
import uk.ac.aber.cs211.group07.system.exceptions.InvalidMoveException;
import uk.ac.aber.cs211.group07.system.exceptions.InvalidPositionException;
import uk.ac.aber.cs211.group07.system.exceptions.NotInHomePortException;
import uk.ac.aber.cs211.group07.system.exceptions.TooMuchTreasureException;
import uk.ac.aber.cs211.group07.system.helpers.IslandInteraction;
import uk.ac.aber.cs211.group07.system.helpers.PortInteraction;
import uk.ac.aber.cs211.group07.system.resources.AnchorBay;
import uk.ac.aber.cs211.group07.system.resources.Card;
import uk.ac.aber.cs211.group07.system.resources.ChanceCard;
import uk.ac.aber.cs211.group07.system.resources.CrewCard;
import uk.ac.aber.cs211.group07.system.resources.Direction;
import uk.ac.aber.cs211.group07.system.resources.FlatIsland;
import uk.ac.aber.cs211.group07.system.resources.Island;
import uk.ac.aber.cs211.group07.system.resources.MapObject;
import uk.ac.aber.cs211.group07.system.resources.Player;
import uk.ac.aber.cs211.group07.system.resources.PlayerPort;
import uk.ac.aber.cs211.group07.system.resources.Port;
import uk.ac.aber.cs211.group07.system.resources.Position;
import uk.ac.aber.cs211.group07.system.resources.Ship;
import uk.ac.aber.cs211.group07.system.resources.Treasure;
import uk.ac.aber.cs211.group07.system.resources.TreasureIsland;

/**
 * This class handles the whole interaction step by step. Then modifies data
 * appropriately (according to the game requirement specification)
 * 
 * @author Julia Wojciechowska
 * @version 0.1 - 03/05/17 - Initial creation /juw30
 * @version 0.11 - 03/05/17 - Moved relevant methods to InteractionController
 *          /haa14
 * @version 0.2 - 03/05/17 - Added and modified relevant treasure deposit
 *          methods. /haa14
 * @version 0.3 - 04/05/17 - Added methods for processing cards 3,4,5 /haa14
 * @version 0.4 - 04/05/17 - Added doChanceCardInteraction and Jake's and
 *          Robert's code
 * @version 0.5 - 05/05/17 - Added final cards to the switch statement /rob27
 * @version 0.51 - 05/05/17 - Refactored, commented and separated out code and
 *          repeated code /haa14
 */

public class InteractionController {

	private GameEngine engine;
	private Player player;
	private Ship ship;
	private MapObject interactive;
	private W_Game game;
	private Image img;

	public InteractionController(W_Game game, GameEngine engine, Ship playerShip, MapObject interactive) {
		this.engine = engine;
		this.player = playerShip.getOwner();
		this.ship = playerShip;
		this.interactive = interactive;
		this.game = game;

	}

	public void interact() {
		if (interactive instanceof Ship) {
			engine.setState("action", State.PREPARE);
		} else if (interactive instanceof Port) {
			doPortInteraction();
		} else if (interactive instanceof Island) {
			doIslandInteraction();
		}
		engine.setState("action", State.TURN);
	}

	private void doIslandInteraction() {
		if (interactive instanceof FlatIsland) {
			LinkedList<Object> treasureAwarded = new LinkedList<Object>();
			try {
				treasureAwarded.add(IslandInteraction.transferTreasure((FlatIsland) interactive, ship));

			} catch (TooMuchTreasureException e) {
				e.printStackTrace();
			}

			try {
				treasureAwarded.addAll(IslandInteraction.getCards((FlatIsland) interactive, player));
			} catch (EmptyPackException e) {
				e.printStackTrace();
			}

			String text;
			if (treasureAwarded.size() > 0) {
				text = "You are awarded followed treasure/cards: ";
			} else {
				text = "There is no treasure/cards.";
			}
			popUp(treasureAwarded, text);

		} else if (interactive instanceof TreasureIsland) {
			try {
				doChanceCardInteraction(((TreasureIsland) interactive).draw());
			} catch (EmptyPackException e) {
				e.printStackTrace();
			}
		}
	}

	private void popUp(LinkedList<Object> treasure, String text) {
		Stage customised = new Stage();

		LinkedList<Image> imgs;
		imgs = getImages(treasure);

		Button btn = new Button("OK");
		btn.setOnAction(event -> customised.close());
		LinkedList<Button> btns = new LinkedList<Button>();
		btns.add(btn);

		Popup_Customised popup = new Popup_Customised(null, customised, "Confirmation", imgs, text, btns);
		popup.getStage().showAndWait();

	}

	private void showTradeMenu() {
		try {
			FXMLLoader loader = new FXMLLoader(
					InteractionController.class.getClassLoader().getResource("ui/W_Trade.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage newStage = new Stage();
			/* Create a new scene with the root element in it. */
			Scene scene = new Scene(page);
			/* Assign the scene to the page */
			newStage.setScene(scene);
			newStage.setTitle("Trade Menu");
			newStage.setResizable(false);
			newStage.initModality(Modality.APPLICATION_MODAL);
			((W_Trade) (loader.getController())).setUpController(newStage, null, game, engine, ship.getOwner());
			// self.close(); //could change to showandwait, and get rid of this
			newStage.show();
		} catch (Exception ex) {
			Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void showTakeTreasureMenu(int value) {
		try {
			FXMLLoader loader = new FXMLLoader(
					InteractionController.class.getClassLoader().getResource("ui/TakeTreasure.fxml"));
			GridPane page = (GridPane) loader.load();
			Stage newStage = new Stage();
			/* Create a new scene with the root element in it. */
			Scene scene = new Scene(page);
			/* Assign the scene to the page */
			newStage.setScene(scene);
			newStage.setTitle("Take Treasure Menu");
			newStage.setResizable(false);
			newStage.initModality(Modality.APPLICATION_MODAL);
			((W_TakeTreasure) (loader.getController())).setUpController(newStage, null, engine, ship.getOwner(), value);
			// self.close(); //could change to showandwait, and get rid of this
			newStage.show();
		} catch (Exception ex) {
			Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void doPortInteraction() {
		LinkedList<Object> treasureDeposited = new LinkedList<Object>();
		if (interactive instanceof PlayerPort && ship.isAtHomePort()) {
			int index = 0;
			int number = ship.getTreasureNum();
			while (index < number) {
				treasureDeposited.add(ship.getTreasureList().get(index));
				index++;
			}
			try {
				PortInteraction.depositTreasure(ship, (PlayerPort) interactive);
			} catch (NotInHomePortException e) {
				e.printStackTrace();
			}

			String text;
			if (treasureDeposited.isEmpty()) {
				text = "You do not have any items to deposit.";
			} else {
				text = "You have deposited followed items: ";
			}
			System.out.println("POP UP");
			popUp(treasureDeposited, text);

		} else if (interactive instanceof AnchorBay) {
			if (PortInteraction.isAnchorBayEligible(ship.getOwner())) {
				for (Card c : player.getCards()) {
					if (c instanceof ChanceCard) {
						if (((ChanceCard) c).getNumber() == 25) {
							player.removeCard(c);
						} else if (((ChanceCard) c).getNumber() == 26) {
							player.removeCard(c);
						}
					}
				}
				showTakeTreasureMenu(7);
			}
		} else if (interactive instanceof Port) {
			showTradeMenu();
		}
	}

	public int countDistance(int coord_x, int coord_y, Direction dir, int num) {
		int index = 0;
		int smallestDistance = 0;
		int winner = index;
		int distanceCount = 0;
		switch (num) {
		case 6:
			while (index < 6) {
				int x = engine.getBoard().getPort(index).getPos().x;
				int y = engine.getBoard().getPort(index).getPos().y;
				if (engine.getDirectionTo(ship.getPos(), engine.getBoard().getPort(index).getPos()) == dir) {
					distanceCount = (int) Math.abs(Math.sqrt(Math.pow(coord_x - x, 2) + Math.pow(coord_y - y, 2)));
				}
				
				if (smallestDistance > distanceCount) {
					smallestDistance = distanceCount;
					winner = index;
				}
				index++;
			}
			break;
		case 7:
			int count = 0;
			for (int index1 = 0; index1 < 4; index1++) {
				int x = ship.getPos().x;
				int y = ship.getPos().y;
				distanceCount = (int) Math.abs(Math.sqrt(Math.pow(coord_x - x, 2) + Math.pow(coord_y - y, 2)));
				if (smallestDistance > distanceCount) {
					if (smallestDistance == distanceCount) {
						count++;
					}
					if (count == 2) {
						winner = -1; // there is no winner as 2 ships are
										// equally nearest
						break;
					}
					smallestDistance = distanceCount;
					winner = index1;
				}
			}
			break;
		}

		return winner;
	}

	/**
	 * Takes a chance card and calls the relevant function to process it
	 * 
	 * @param chanceCard
	 */
	public void doChanceCardInteraction(ChanceCard chanceCard) {
		int num = chanceCard.getNumber();
		boolean value = true;

		Stage customised = new Stage();

		LinkedList<Image> imgs = new LinkedList<Image>();

		Button btn = new Button("OK");
		btn.setOnAction(event -> customised.close());
		LinkedList<Button> btns = new LinkedList<Button>();
		btns.add(btn);

		String text = chanceCard.getText();
		Image image = getImage(chanceCard.getNumber());
		imgs.add(image);
		Popup_Customised popup = new Popup_Customised(null, customised, "" + chanceCard.getNumber() + "", imgs, text,
				btns);
		popup.getStage().showAndWait();

		try {
			/* Direction dir; */
			switch (num) {
			case 1: // Blown 5 squares off the coast of Treasure Island (nearest
					// side)
				performCard1();
				drawCardsFromPirateIsland(4);
				break;

			case 2: // Choose a player to give you 3 crew cards
				performCard2(chanceCard);
				break;
			case 3: // Blown to Mud Bay. If crew < 3, take 4 crew cards from
					// Pirate
					// Island
				performCard3to5(chanceCard, 4);
				break;
			case 4: // Blown to Cliff Creek. If crew < 3, take 4 crew cards from
					// Pirate Island
				performCard3to5(chanceCard, 4);
				break;
			case 5: // Blown to Home Port. If Crew < 3, take 4 crew cards from
					// Pirate Island
					// empty ship of any treasure
				performCard3to5(chanceCard, 4);
				break;
			case 6: // Blown to nearest port in direction you are heading.
				performCard6(chanceCard);
				break;
			case 7: // One treasure || 2 crew cards from hand are last and
					// washed
					// overboard to the nearest ship.
					// If 2 ships are equidistant then ignore this instruction
					// If other player has room, give treasure. Else, give cards
				performCard7(chanceCard);

				break;
			case 8: // As above, but to flat island
				performCard8(chanceCard.getNumber());
				break;
			case 9: // Most valuable treasure (Or if !, best crew card) washed
					// to
					// Flat Island
				performCard9and10(9);
				break;
			case 10: // Best crew card deserts for Pirate Island. Place there
						// immediately
				performCard9and10(10);
				break;
			case 11: // Take treasure up to value 5 in total value, or 2 crew
						// cards
						// from Pirate Island
				performCards11to15and27to28(chanceCard.getNumber());
				break;
			case 12: // As above to value 4
				performCards11to15and27to28(chanceCard.getNumber());
				break;
			case 13: // As above to value 5
				performCards11to15and27to28(chanceCard.getNumber());
				break;
			case 14: // As above to value 7 or 3 crew cards
				performCards11to15and27to28(chanceCard.getNumber());
				break;
			case 15: // Take 2 crew cards from Pirate Island
				performCards11to15and27to28(chanceCard.getNumber());
				break;
			case 16: // Take treasure up to 7 in value, and reduce ship crew to
						// 10
						// by taking crew cards and placing on pirate island.
						// Limit of 2 treasure applies
				performCard16to18and22(chanceCard.getNumber());
				break;
			case 17: // As above, but up to 6 in value and crew down to 11
				performCard16to18and22(chanceCard.getNumber());
				break;
			case 18: // Take treasure up to 4 in total value, and if crew total
						// is
						// <=7, take 2 crew cards
				performCard16to18and22(chanceCard.getNumber());
				break;
			case 19: // Exchange all crew cards in hand for same number from
						// Pirate
						// Island
				performCard19();
				break;
			case 20: // If another player is anchored at Treasure Island, game
						// chooses two random cards from each and exchanges
						// them.
						// If one has less than two, they will give the maximum
						// they
						// have available.
						// If no other player is anchored, return lowest two
						// valued
						// cards to pack
				performCard20();
				break;
			case 21: // Long John Silver (Keep)
						// When arriving at a port with crew, exchange LJS for
						// up to
						// 5 crew in value.
						// If arriving at a port with LJS, you can take him, in
						// exchange for one treasure to the port
				player.addCard(chanceCard);
				value = false;
				((TreasureIsland) interactive).removeCard(chanceCard);
				break;
			case 22: // Yellow Fever. All players with >7 crew must bury surplus
						// on
						// Pirate Island
				performCard16to18and22(chanceCard.getNumber());
				break;
			case 23: // Doubloons (Keep)
						// May be traded for treasure up to value 5
				performCard23and24(chanceCard, chanceCard.getNumber());
				value = false;
				break;
			case 24: // Pieces of Eight (Keep)
						// As above, up to 4
				performCard23and24(chanceCard, chanceCard.getNumber());
				value = false;
				break;
			case 25: // Kidd's Chart (Keep)
						// Sail to the far side of Pirate Island, square marked
						// with
						// anchor.
						// Take treasure up to value 7, and return card to the
						// pack.
				performCard25and26(chanceCard, chanceCard.getNumber());
				value = false;
				break;
			case 26:
				performCard25and26(chanceCard, chanceCard.getNumber());
				value = false;
				break;
			case 27: // Take treasure up to 5 value, or 3 crew cards from Pirate
						// Island
				performCards11to15and27to28(chanceCard.getNumber());
				break;
			case 28: // Take 2 crew cards from Pirate Island
				performCards11to15and27to28(chanceCard.getNumber());
				break;

			}
			if (value) {
				((TreasureIsland) interactive).getPack().add(chanceCard);
			} else {
				//nothing - card given to player
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Function for Chance Card 1 /haa14
	 */
	private void performCard1() {
		int distance = 4; //not 5 because additional check is made to not bump into other islands
		int x = ship.getPos().getX();
		int y = ship.getPos().getY();
		
		if(x == 8 && y >= 9 && y <= 12){ //If on left edge, move 5 West
			try {
				engine.movePlayer(player, distance, Direction.W);
			} catch (InvalidMoveException e) {
				e.printStackTrace();
			}
		} else if (x == 8 && y == 13){ //If top left corner, move 3 NW
			try {
				engine.movePlayer(player, 3, Direction.NW);
			} catch (InvalidMoveException e) {
				e.printStackTrace();
			}
		} else if (x == 8 && y == 8){ //If bottom left corner, move 5 SW
			try {
				engine.movePlayer(player, 3, Direction.SW);
			} catch (InvalidMoveException e) {
				e.printStackTrace();
			}
		} else if (x == 13 && y >= 9 && y <= 12){ //If right side, move 5 E
			try {
				engine.movePlayer(player, distance, Direction.E);
			} catch (InvalidMoveException e) {
				e.printStackTrace();
			}
		} else if (x == 8 && y == 13){ //If top right corner, move 5 NE
			try {
				engine.movePlayer(player, 3, Direction.NE);
			} catch (InvalidMoveException e) {
				e.printStackTrace();
			}
		} else if (x == 8 && y == 8){ //If bottom left corner, move 3 SE
			try {
				engine.movePlayer(player, 3, Direction.SE);
			} catch (InvalidMoveException e) {
				e.printStackTrace();
			}
		} else if (y == 8 && x >= 9 && x <= 12){ //If bottom edge, move 5 S
			try {
				engine.movePlayer(player, distance, Direction.S);
			} catch (InvalidMoveException e) {
				e.printStackTrace();
			}
		} else { //If top side, move 5 N
			try {
				engine.movePlayer(player, distance, Direction.N);
			} catch (InvalidMoveException e) {
				e.printStackTrace();
			}
		}
		//you can bump into an island diagonally - this prevents it
		try {
			if (engine.isValidMove(player, 1, ship.getOrientation())) {
				engine.setState("action", State.TURN);
				engine.movePlayer(player, 1, ship.getOrientation());
			}
		} catch (InvalidMoveException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Handles functions for card 2
	 * 
	 * @param chanceCard
	 */
	private void performCard2(ChanceCard chanceCard) {
		Stage customised = new Stage();
		LinkedList<Object> objects = new LinkedList<Object>();
		LinkedList<Image> imgs1 = new LinkedList<Image>();
		LinkedList<Button> btns = new LinkedList<Button>();
		Button btn1 = new Button();
		Button btn2 = new Button();
		Button btn3 = new Button();
		Button btn4 = new Button();
		player.sortCardsInc();
		if (engine.getPlayer(0) != player) {
			btn1 = new Button(engine.getPlayer(0).getName());
			btn1.setOnAction(event -> {
				for (int index2 = 0; index2 < 3; index2++) {
					try {
						objects.add(engine.getPlayer(0).peek());
						player.addCard(engine.getPlayer(0).draw());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				popUp(objects, "You've gained followed cards:");
				customised.close();
			});
			btns.add(btn1);
			imgs1.add(game.getPlayerIconImage(engine.getPlayer(0), true).getImage());
		} 
		if (engine.getPlayer(1) != player) {
			btn2 = new Button(engine.getPlayer(1).getName());
			btn2.setOnAction(event -> {
				for (int index2 = 0; index2 < 3; index2++) {
					try {
						objects.add(engine.getPlayer(1).peek());
						player.addCard(engine.getPlayer(1).draw());
					} catch (EmptyPackException e) {
						e.printStackTrace();
					}
				}
				popUp(objects, "You've gained followed cards:");
				customised.close();
			});
			btns.add(btn2);
			imgs1.add(game.getPlayerIconImage(engine.getPlayer(1), true).getImage());
		} 
		if (engine.getPlayer(2) != player) {
			btn3 = new Button(engine.getPlayer(2).getName());
			btn3.setOnAction(event -> {
				for (int index2 = 0; index2 < 3; index2++) {
					try {
						objects.add(engine.getPlayer(2).peek());
						player.addCard(engine.getPlayer(2).draw());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				popUp(objects, "You've gained followed cards:");
				customised.close();
			});
			btns.add(btn3);
			imgs1.add(game.getPlayerIconImage(engine.getPlayer(2), true).getImage());
		} 
		if (engine.getPlayer(3) != player) {
			btn4 = new Button(engine.getPlayer(3).getName());
			btn4.setOnAction(event -> {
				for (int index2 = 0; index2 < 3; index2++) {
					try {
						objects.add(engine.getPlayer(3).peek());
						player.addCard(engine.getPlayer(3).draw());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				popUp(objects, "You've gained followed cards:");
				customised.close();
			});
			btns.add(btn4);
			imgs1.add(game.getPlayerIconImage(engine.getPlayer(3), true).getImage());
		}
		System.out.print(btns.size());

		Popup_Customised popup = new Popup_Customised(null, customised, "" + chanceCard.getNumber() + "", imgs1,
				"Choose a player who must give you 3 crew cards", btns);
		popup.getStage().showAndWait();
	}

	/**
	 * Handles functions for card 3,4,5
	 * 
	 * @param chanceCard
	 * @param cardsToDraw
	 * @throws InvalidPositionException
	 * @throws EmptyPackException
	 */
	private void performCard3to5(ChanceCard chanceCard, int cardsToDraw) {
		try {
			moveShipToSquare(chanceCard.getNumber());
		} catch (InvalidPositionException | EmptyPackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		drawCardsFromPirateIsland(cardsToDraw);
	}

	/**
	 * Handles functions for Card 6
	 * 
	 * @param chanceCard
	 * @throws InvalidPositionException
	 */
	private void performCard6(ChanceCard chanceCard) {
		int distance = 21;
		Position est = ship.getPos();
		for (int i = 0; i < 5; i++){
			est.x = engine.addDirectionalX(est.x, ship.getOrientation());
			est.y = engine.addDirectionalY(est.y, ship.getOrientation());
		}
		Port closest = null;
		for (int i = 0; i < 4; i++){
			Port port = engine.getBoard().getPort(i);
			if (engine.getDistanceBetween(est, port.getPos()) < distance){
				closest = port;
				distance = engine.getDistanceBetween(est, port.getPos());
			}
		}
		try {
			engine.getBoard().moveMapObject(ship, closest.getPos());
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}
		drawCardsFromPirateIsland(4);
	}

	/**
	 * Function for Chance Card 7
	 * 
	 * @param chanceCard
	 */
	private void performCard7(ChanceCard chanceCard) {
		LinkedList<Object> objects = new LinkedList<Object>();
		int distance = 20;
		Ship nearestShip = null;
		for (int i = 0; i < 4; i++){
			Ship ship = engine.getPlayer(i).getShip();
			if (ship != player.getShip()){
				int tempDist = engine.getDistanceBetween(ship.getPos(), player.getShip().getPos());
				if (tempDist < distance){
					nearestShip = ship;
					distance = tempDist;
				} else if (tempDist == distance){
					popUp(objects, "You've managed to recover all treasure! Nothing's been lost.");
					return;
				}
			}
		}
		if (nearestShip.getTreasureList().size() < 2
				&& ship.getTreasureNum() > 0) {
			ship.sortTreasureInc();
			try {
				objects.add(ship.getTreasureList().get(0));
				nearestShip.addTreasure(ship.removeFirstTreasure());
			} catch (TooMuchTreasureException e) {
				e.printStackTrace();
			}
		} else {
			int count = 0;
			player.sortCardsInc();
			while (count < 2 && player.getHand().size() > 0){
				try {
					objects.add(player.peek());
					nearestShip.getOwner().addCard(player.draw());
				} catch (EmptyPackException e) {
					e.printStackTrace();
				}
			}
		}

		popUp(objects, "You've lost: ");
	}

	/**
	 * The following function carries out the interactions that happen upon
	 * drawing the chance cards 8
	 * 
	 * @param cardNum
	 * @throws EmptyPackException
	 */
	private void performCard8(int chanceNum) {
		LinkedList<Image> imgs = new LinkedList<Image>();
		System.out.println(
				"One treasure from your ship or 2 crew cardsfrom your hand are lost and washedoverboard to Flat Island.");
		LinkedList<Object> objects = new LinkedList<Object>();
		if (ship.getTreasureNum() != 0) {
			ship.sortTreasureInc();
			try {
				objects.add(ship.getTreasureList().get(0));
				engine.getBoard().getFlatIsland().addTreasure(ship.removeFirstTreasure());
			} catch (TooMuchTreasureException e) {
				e.printStackTrace();
			}
		} else {
			player.sortCardsInc();
			for (int i = 0; i < 2; i++) {
				try {
					 if (!player.getHand().isEmpty()) {
					objects.add(player.peek());
					engine.getBoard().getFlatIsland().addCard(player.draw());
					 }
				} catch (EmptyPackException e) {
					e.printStackTrace();
				}
			}
		}
		Image image = new Image("ui/img/ChanceCard_WashedOverboard.png");
		imgs.add(image);
		popUp(objects, "You've lost the following items: ");

	}

	/**
	 * Handles functions for card 9 and 10
	 * 
	 * @param chanceCard
	 */
	private void performCard9and10(int chanceCard) {
		Player player = engine.getCurrentPlayer();
		player.sortCardsDec();
		switch (chanceCard) {
		case 9:
			if (ship.getTreasureNum() > 0) {
				ship.sortTreasureDec();
				try {
					engine.getBoard().getFlatIsland().addTreasure(ship.removeFirstTreasure());
				} catch (TooMuchTreasureException e) {
					e.printStackTrace();
				}
			} else if (!player.getHand().isEmpty()) {
				player.sortCardsDec();
				try {
					engine.getBoard().getFlatIsland().addCard(player.draw());
				} catch (EmptyPackException e) {
					e.printStackTrace();
				}
			}
			break;
		case 10:
			player.sortCardsDec();
			try {
				IslandInteraction.returnCard(player, engine.getBoard().getPirateIsland(), player.draw());
			} catch (EmptyPackException e) {
				e.printStackTrace();
			}
			break;
		}
	}

	/**
	 * Handles functions for card 11,12,13,14,15,27,28
	 * 
	 * @param cardNum
	 */
	private void performCards11to15and27to28(int cardNum) {
		int value = 0;
		LinkedList<Object> list = new LinkedList<Object>();
		try {
			switch (cardNum) {
			case 11:
				popUpForCards11to15(2, 5);
				break;

			case 12:
				popUpForCards11to15(2, 4);
				break;

			case 13:
				popUpForCards11to15(2, 5);
				break;

			case 14:
				popUpForCards11to15(3, 7);
				break;

			case 15:
				System.out.println("Take 2 crew cards from Pirate Island.");
				list = arrayToLinked(
						IslandInteraction.getCards(engine.getBoard().getPirateIsland(), engine.getCurrentPlayer(), 2));
				popUp(list, "Here are your crew cards");
				break;

			case 27:
				popUpForCards11to15(3, 5);

				break;

			case 28:
				System.out.println("Take 2 crew cards from Pirate Island.");
				list = arrayToLinked(
						IslandInteraction.getCards(engine.getBoard().getPirateIsland(), engine.getCurrentPlayer(), 2));
				popUp(list, "Here are your crew cards");
				break;

			default:
				throw new IllegalOperationException();
			}
			if (value > 0)
				showTakeTreasureMenu(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void crewTotal(int greater){
		if (player.getMovingStrength() >= greater) {
			if (player.getMovingStrength() >= greater + 1) { //crew card value 1
				player.sortCardsInc();
				try {
					engine.getBoard().getPirateIsland().addCard(player.draw());
				} catch (EmptyPackException e1) {
					e1.printStackTrace();
				}
				if (player.getMovingStrength() >= greater + 2) { //crew card value 2
					player.sortCardsInc();
					for (CrewCard c : player.getHand()) {
						if (c.getValue() == 2) {
							IslandInteraction.returnCard(player, engine.getBoard().getPirateIsland(), c);
						}
					}
					if (player.getMovingStrength() >= greater + 3) { //crew card value 3
						player.sortCardsDec();
						try {
							engine.getBoard().getPirateIsland().addCard(player.draw());
						} catch (EmptyPackException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
	
	/**
	 * Handles functions for Card 16,17,18,22
	 * 
	 * @param chanceNum
	 */
	private void performCard16to18and22(int chanceNum) {
		player.sortCardsInc();
		switch (chanceNum) {
		case 16:
			System.out.println("Card 16");
			showTakeTreasureMenu(7);
			crewTotal(10);
			break;
		case 17:
			System.out.println("Card 17");
			showTakeTreasureMenu(6);
			crewTotal(11);
			break;
		case 18:
			System.out.println("Card 18");
			showTakeTreasureMenu(4);
			while (engine.getCurrentPlayer().getMovingStrength() < 7) {
				try {
					IslandInteraction.getCards(engine.getBoard().getPirateIsland(), player, 2);
				} catch (EmptyPackException e) {
					e.printStackTrace();
				}
			}
			break;
		case 22:
			System.out.println("Card 22");
			for (int i = 0; i < 4; i++) {
				Player currPlayer = engine.getPlayer(i);
				currPlayer.sortCardsInc();
				while (currPlayer.getCrewCardNum() > 7) {
					IslandInteraction.returnCard(currPlayer, engine.getBoard().getPirateIsland(),
							currPlayer.peek());
				}
			}
		}
	}

	/**
	 * Handles functions for Card 19
	 * 
	 * @throws EmptyPackException
	 */
	private void performCard19() throws EmptyPackException {
		LinkedList<Object> objects = new LinkedList<Object>();
		System.out.println("Card 19");
		player.sortCardsInc();
		while (player.getHand().size() > 0) {
			objects.add(player.peek());
			IslandInteraction.returnCard(player, engine.getBoard().getPirateIsland(), player.peek());
		}
		IslandInteraction.getCards(engine.getBoard().getPirateIsland(), player, objects.size());
		popUp(objects, "Your new cards: ");
	}

	/**
	 * Handles functions for Card 20
	 */
	private void performCard20() {
		LinkedList<Object> players = new LinkedList<Object>();
		for (int i = 0; i < 4; i++) {
			if (engine.getPlayerDestinationObject(engine.getPlayer(i)) instanceof TreasureIsland) {
				players.add(engine.getPlayer(i));
			}
		}
		
		if (players.size() > 1) {
			
		} else if (players.isEmpty()) { //places 2 of player's crew cards on Pirate Island
			player.sortCardsInc();
			IslandInteraction.returnCard(player, engine.getBoard().getPirateIsland(), player.peek());
			IslandInteraction.returnCard(player, engine.getBoard().getPirateIsland(), player.peek());
		}
	}

	/**
	 * Handles the functions for card 23 and 24
	 * 
	 * @param cardNo
	 * @param value
	 */
	private void performCard23and24(ChanceCard chanceCard, int cardNo) {
		switch(cardNo) {
		case 23: 
			player.addCard(chanceCard);
			((TreasureIsland) interactive).removeCard(chanceCard);
			break;
		case 24: 
			player.addCard(chanceCard);
			((TreasureIsland) interactive).removeCard(chanceCard);
			break;
		}
	}

	/**
	 * Handles the functions for card 25 and 26 (Kidd's Chart)
	 * 
	 * @param chanceCard
	 * @throws InvalidPositionException
	 * @throws EmptyPackException
	 */
	private void performCard25and26(ChanceCard chanceCard, int num){
		switch (num) {
		case 25:
			player.addCard(chanceCard);
			((TreasureIsland) interactive).removeCard(chanceCard);
			break;
		case 26:
			player.addCard(chanceCard);
			((TreasureIsland) interactive).removeCard(chanceCard);
			break;
		}
	}

	/**
	 * Deals with popups for cards 11 to 15
	 * 
	 * @param crew
	 * @param treasure
	 */
	private void popUpForCards11to15(int crew, int treasure) {
		Stage customised = new Stage();
		LinkedList<Button> btns = new LinkedList<Button>();
		LinkedList<Object> objects = new LinkedList<Object>();
		LinkedList<Image> imgs = new LinkedList<Image>();

		Button btn1 = new Button(crew + " Crew Cards");
		btn1.setOnAction(event -> {
			while (objects.size() < crew && engine.getBoard().getPirateIsland().getCards().size() > 0) {
				objects.add(engine.getBoard().getPirateIsland().peek());
				try {
					player.addCard(engine.getBoard().getPirateIsland().draw());
				} catch (EmptyPackException e) {
					e.printStackTrace();
				}

			}
			popUp(objects, "Cards you've got: ");
			customised.close();
		});
		btns.add(btn1);

		Button btn2 = new Button("Treasure up to " + treasure);
		btn2.setOnAction(event -> {
			if (ship.getTreasureNum() < 2) {
				showTakeTreasureMenu(treasure);
			}
			customised.close();
		});
		btns.add(btn2);

		Popup_Customised popup1 = new Popup_Customised(null, customised, "", imgs,
				"What would you like to take? Available: "
						+ engine.getBoard().getTreasureIsland().getTreasureList().size() + " treasure and "
						+ engine.getBoard().getPirateIsland().getCards().size() + " Crew Cards",
				btns);
		popup1.getStage().showAndWait();

	}

	/**
	 * Converts LinkedList of CrewCards to LinkedList of Objects
	 * 
	 * @param array
	 * @return
	 */
	public LinkedList<Object> arrayToLinked(LinkedList<CrewCard> array) {
		LinkedList<Object> objects = new LinkedList<Object>();
		for (CrewCard c : array) {
			objects.add(c);
		}
		return objects;
	}

	/**
	 * Returns images
	 * 
	 * @param treasure
	 * @return list of images
	 */
	public static LinkedList<Image> getImages(LinkedList<Object> treasure) {
		LinkedList<Image> imgs = new LinkedList<Image>();
		Image img = null;
		for (Object t : treasure) {
			if (t instanceof Treasure) {
				System.out.println("TREASURE");
				switch (((Treasure) t).getType()) {
				case DIAMOND:
					img = new Image("ui/img/Diamond.png", 48, 48, false, false);
					break;
				case RUBY:
					img = new Image("ui/img/Ruby.png", 48, 48, false, false);
					break;
				case GOLD:
					img = new Image("ui/img/GoldBar.png", 48, 48, false, false);
					break;
				case RUM:
					img = new Image("ui/img/Rum.png", 48, 48, false, false);
					break;
				case PEARL:
					img = new Image("ui/img/Pearl.png", 48, 48, false, false);
					break;
				default:
					img = new Image("ui/img/Rum.png", 48, 48, false, false);
				}
			} else if (t instanceof CrewCard) {
				System.out.println("CARD");
				if (((CrewCard) t).isRed()) {
					switch (((CrewCard) t).getValue()) {
					case 1:
						img = new Image("ui/img/CrewCard_Red1.png", 148, 196, false, false);
						break;
					case 2:
						img = new Image("ui/img/CrewCard_Red2.png", 148, 196, false, false);
						break;
					case 3:
						img = new Image("ui/img/CrewCard_Red3.png", 148, 196, false, false);
						break;
					}
				} else {
					switch (((CrewCard) t).getValue()) {
					case 1:
						img = new Image("ui/img/CrewCard_Black1.png", 148, 196, false, false);
						break;
					case 2:
						img = new Image("ui/img/CrewCard_Black2.png", 148, 196, false, false);
						break;
					case 3:
						img = new Image("ui/img/CrewCard_Black3.png", 148, 196, false, false);
						break;
					}
				}
			} else if (t instanceof ChanceCard) {
				switch (((ChanceCard) t).getNumber()) {
				case 21:
					img = new Image("ui/img/ChanceCard_LongJohnSilver.png", 148, 196, false, false);
					break;
				case 23:
					img = new Image("ui/img/ChanceCard_Doubloons.png", 148, 196, false, false);
					break;
				case 24:
					img = new Image("ui/img/ChanceCard_PiecesOfEight.png", 148, 196, false, false);
					break;
				case 25:// cascades down
				case 26:
					img = new Image("ui/img/ChanceCard_KiddsChart.png", 148, 196, false, false);
					break;
				}
			}
			imgs.add(img);
		}
		return imgs;
	}

	/**
	 * Moves a passed ship to a specified square
	 */
	private void moveShipToSquare(int cardNo) throws InvalidPositionException, EmptyPackException {
		Position newPos = new Position();
		// Checks card number for correct place to move the player
		switch (cardNo) {
		case 3:
			newPos = new Position(1, 1);
			break;
		case 4:
			newPos = new Position(20, 20);
			break;
		case 5:
			newPos = player.getHomePort().getPos();
			engine.getBoard().moveMapObject(ship, newPos);
			InteractionController interaction = new InteractionController(game, engine, ship, player.getHomePort());
			interaction.interact();
			return;
		}
		// Move ship to relevant position
		engine.getBoard().moveMapObject(ship, newPos);
	}

	/**
	 * Draws the required number of cards from pirate island, and returns to the
	 * current player's hand
	 * 
	 * @param cardsToDraw
	 */
	private void drawCardsFromPirateIsland(int cardsToDraw) {
		// Checks if player's crew cards is 3 or less
		String text = null;
		LinkedList<Object> drawnCards = new LinkedList<Object>();
		if (engine.getCurrentPlayer().getCrewCardNum() <= 3) {
			// Draws the required number of cards, if pack is not empty. If it
			// is, break the for loop
			for (int i = 0; i < cardsToDraw; i++) {
				if (!engine.getBoard().getPirateIsland().getCards().isEmpty()) {
					CrewCard cardToAdd = null;
					try {
						cardToAdd = engine.getBoard().getPirateIsland().draw();
					} catch (EmptyPackException e) {
						e.printStackTrace();
					}
					engine.getCurrentPlayer().getHand().add(cardToAdd);
					drawnCards.add(cardToAdd);

				} else {
					break;
				}
			}
			text = "As you had less than 4 crew cards, you drew " + drawnCards.size() + " cards from Pirate Island";
		}
		
		if (!drawnCards.isEmpty()) {
			popUp(drawnCards, text);
		}
	}

	/**
	 * Get appropriate image for the drawn card
	 * 
	 * @param cardNo
	 * @return
	 */
	private Image getImage(int cardNo) {
		Image image = null;
		switch (cardNo) {
		case 1:
			image = new Image("ui/img/ChanceCard_BlownAway.png");
			break;
		case 2:
			image = new Image("ui/img/ChanceCard_GiveCrewCards.png");
			break;
		case 3:
		case 4:
		case 5:
		case 6:
			image = new Image("ui/img/ChanceCard_BlownAway.png");
			break;
		case 7:
		case 8:
		case 9:
			image = new Image("ui/img/ChanceCard_WashedOverboard.png");
			break;
		case 10:
			image = new Image("ui/img/ChanceCard_Desertion.png");
			break;
		case 11:
			image = new Image("ui/img/ChanceCard_PickupTreasure_5.png");
			break;
		case 12:
			image = new Image("ui/img/ChanceCard_PickupTreasure_4.png");
			break;
		case 13:
			image = new Image("ui/img/ChanceCard_PickupTreasure_5.png");
			break;
		case 14:
			image = new Image("ui/img/ChanceCard_PickupTreasure_7.png");
			break;
		case 15:
			image = new Image("ui/img/ChanceCard_PickupCrew_2.png");
			break;
		case 16:
			image = new Image("ui/img/ChanceCard_PickupTreasure_7.png");
			break;
		case 17:
			image = new Image("ui/img/ChanceCard_PickupTreasure_6.png");
			break;
		case 18:
			image = new Image("ui/img/ChanceCard_PickupTreasure_4.png");
			break;
		case 19:
			image = new Image("ui/img/ChanceCard_GiveCrewCards.png");
			break;
		case 20:
			image = new Image("ui/img/ChanceCard_GiveCrewCards.png");
			break;
		case 21:
			image = new Image("ui/img/ChanceCard_LongJohnSilver.png");
			break;
		case 22:
			image = new Image("ui/img/ChanceCard_YellowFever.png");
			break;
		case 23:
			image = new Image("ui/img/ChanceCard_Doubloons.png");
			break;
		case 24:
			image = new Image("ui/img/ChanceCard_PiecesOfEight.png");
			break;
		case 25:
		case 26:
			image = new Image("ui/img/ChanceCard_KiddsChart.png");
			break;
		case 27:
			image = new Image("ui/img/ChanceCard_PickupTreasure_5.png");
			break;
		case 28:
			image = new Image("ui/img/ChanceCard_PickupCrew_2.png");
			break;
		}
		return image;
	}

}

