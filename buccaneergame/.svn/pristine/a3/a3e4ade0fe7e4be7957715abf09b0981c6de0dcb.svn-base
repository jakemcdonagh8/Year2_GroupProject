package ui.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ui.menus.Popup_MultipleOptions;
import ui.menus.Popup_Simple;
import uk.ac.aber.cs211.group07.system.Board;
import uk.ac.aber.cs211.group07.system.GameEngine;
import uk.ac.aber.cs211.group07.system.State;
import uk.ac.aber.cs211.group07.system.exceptions.InvalidDirectionException;
import uk.ac.aber.cs211.group07.system.exceptions.InvalidMoveException;
import uk.ac.aber.cs211.group07.system.exceptions.InvalidPositionException;
import uk.ac.aber.cs211.group07.system.helpers.PortInteraction;
import uk.ac.aber.cs211.group07.system.resources.Card;
import uk.ac.aber.cs211.group07.system.resources.CardHolder;
import uk.ac.aber.cs211.group07.system.resources.ChanceCard;
import uk.ac.aber.cs211.group07.system.resources.CrewCard;
import uk.ac.aber.cs211.group07.system.resources.Direction;
import uk.ac.aber.cs211.group07.system.resources.FlatIsland;
import uk.ac.aber.cs211.group07.system.resources.MapObject;
import uk.ac.aber.cs211.group07.system.resources.PirateIsland;
import uk.ac.aber.cs211.group07.system.resources.Player;
import uk.ac.aber.cs211.group07.system.resources.PlayerPort;
import uk.ac.aber.cs211.group07.system.resources.Port;
import uk.ac.aber.cs211.group07.system.resources.Position;
import uk.ac.aber.cs211.group07.system.resources.Ship;
import uk.ac.aber.cs211.group07.system.resources.Treasure;
import uk.ac.aber.cs211.group07.system.resources.TreasureHolder;
import uk.ac.aber.cs211.group07.system.resources.TreasureIsland;
import uk.ac.aber.cs211.group07.system.resources.TreasureType;
import javafx.scene.layout.*;

/**
 * Main Game window controller - where the action happens
 * @author Robert
 * @version 0.1 - xx/xx/xx - Whatever Robert's done before (displaying initial grid, window, buttons,
 * 								prototype of the move method and highlighting tiles)
 * @verison 0.2 - 02/05/17 - added initial updateView() method /kac12
 * @version 0.3 - 03/05/17 - added preloading images rather than re-loading them, finished updateView() method,
 * 								added move() method (with states) without any interactions (but with valid moves and
 * 								changing current player + game state) /kac12
 * @version 0.31 - 03/05/17 - Added showPrepareDialog() /haa14
 */
public class W_Game {
	//FXML variables
	@FXML
	Label pName;
	@FXML
	Label pScore;
	@FXML
	Label pFStrength;
	@FXML
	Label pMStrength;
	@FXML
	ImageView pTreasure1;
	@FXML
	ImageView pTreasure2;
	@FXML
	ImageView pIcon;
	@FXML
	VBox gridDetails;
	
	//filenames for the grid
	final String[] filenames_ports = {
			"ui/img/tiles/p1port.png",
			"ui/img/tiles/p2port.png",
			"ui/img/tiles/p3port.png",
			"ui/img/tiles/p4port.png",
			"ui/img/tiles/tile_NPCPort.png",
			"ui/img/tiles/tile_anchorbay.png",
	};
	final int PortsSize = 6;
	
	final String[] filenames_ships = {
			"ui/img/tiles/p1shipN.png", //i = 0 + direction
			"ui/img/tiles/p1shipNE.png",
			"ui/img/tiles/p1shipE.png",
			"ui/img/tiles/p1shipSE.png",
			"ui/img/tiles/p1shipS.png",
			"ui/img/tiles/p1shipSW.png",
			"ui/img/tiles/p1shipW.png",
			"ui/img/tiles/p1shipNW.png",
			"ui/img/tiles/p2shipN.png", //i = 8 + direction
			"ui/img/tiles/p2shipNE.png",
			"ui/img/tiles/p2shipE.png",
			"ui/img/tiles/p2shipSE.png",
			"ui/img/tiles/p2shipS.png",
			"ui/img/tiles/p2shipSW.png",
			"ui/img/tiles/p2shipW.png",
			"ui/img/tiles/p2shipNW.png",
			"ui/img/tiles/p3shipN.png", //i = 16 + dir
			"ui/img/tiles/p3shipNE.png",
			"ui/img/tiles/p3shipE.png",
			"ui/img/tiles/p3shipSE.png",
			"ui/img/tiles/p3shipS.png",
			"ui/img/tiles/p3shipSW.png",
			"ui/img/tiles/p3shipW.png",
			"ui/img/tiles/p3shipNW.png",
			"ui/img/tiles/p4shipN.png", //i = 24
			"ui/img/tiles/p4shipNE.png",
			"ui/img/tiles/p4shipE.png",
			"ui/img/tiles/p4shipSE.png",
			"ui/img/tiles/p4shipS.png",
			"ui/img/tiles/p4shipSW.png",
			"ui/img/tiles/p4shipW.png",
			"ui/img/tiles/p4shipNW.png",
	};
	final int ShipsSize = 32;
	
	final String[] filenames_other = {
			"ui/img/tiles/tile_darkblue.png",
			"ui/img/tiles/tile_lightblue.png",
			"ui/img/tiles/ArrowN.png", //i = 2 + direction
			"ui/img/tiles/ArrowNE.png",
			"ui/img/tiles/ArrowE.png",
			"ui/img/tiles/ArrowSE.png",
			"ui/img/tiles/ArrowS.png",
			"ui/img/tiles/ArrowSW.png",
			"ui/img/tiles/ArrowW.png",
			"ui/img/tiles/ArrowNW.png",
			"ui/img/tiles/MoveHighlighter.png", //i = 10
			"ui/img/Popup_Fight.png" //i = 11	
	};
	final int OthersSize = 12;
	
	final String[] filenames_playerIcons = {
			"ui/img/PlayerIcon_Red.png",
			"ui/img/PlayerIcon_Blue.png",
			"ui/img/PlayerIcon_Yellow.png",
			"ui/img/PlayerIcon_Green.png"
	};
	final int IconsSize = 4;
	
	final String[] filenames_treasure = {
			"ui/img/Diamond.png",
			"ui/img/Ruby.png",
			"ui/img/GoldBar.png",
			"ui/img/Pearl.png",
			"ui/img/Rum.png"
	};
	final int TreasureSize = 5;
	
	final String[] filenames_crewCards = {
			"ui/img/CrewCard1.png",
			"ui/img/CrewCard2.png",
			"ui/img/CrewCard3.png"
	};
	final int CrewCardsSize = 3;
	
	final String[] filenames_chanceCards = {
			"ui/img/ChanceCard_LongJohnSilver.png",
			"ui/img/ChanceCard_Doubloons.png",
			"ui/img/ChanceCard_PiecesOfEight.png",
	};
	final int ChanceCardsSize = 3;
	
	final String[] filenames_islands = {
			"ui/img/tiles/tile_flatisland.png",
			"ui/img/tiles/tile_treasureisland.png",
			"ui/img/tiles/tile_pirateisland.png"
	};
	final int islandSize = 3;
	
	final double gridBtnWidth = 32;
	final double gridBtnHeight = 32;
	
	//images - relative to paths above
	Image[] images_ships = new Image[ShipsSize];
	Image[] images_ports = new Image[PortsSize];
	Image[] images_other = new Image[OthersSize];
	Image[] images_icons = new Image[IconsSize * 2];
	Image[] images_treasure = new Image[TreasureSize];
	Image[] images_crewCards = new Image[CrewCardsSize];
	Image[] images_chanceCards = new Image[ChanceCardsSize];
	Image[] images_islands = new Image[islandSize];
	
	//other instance variables
	//Board board = engine.getBoard();
	ArrayList<Button> gridButtons;
	HashMap<String, Boolean> highlightedIds;
	String string;
	Button lastbutton = null;
	double width=0.0;
	double height=0.0;
	GameEngine engine;
	Stage caller;
	Stage self;
	Position lastHoverPos;
	
	//fxml variables
	@FXML
	private Button btn1;

	@FXML
	private Button btn2;

	@FXML
	private Button btn3;

	@FXML
	private Button myButton;

	//functions
	@FXML
	void showMyCards(ActionEvent event) {
		try {
        	FXMLLoader loader = new FXMLLoader(W_Game.class.getClassLoader().getResource("ui/W_MyCards.fxml"));
        	AnchorPane page = (AnchorPane) loader.load();
        	Stage newStage = new Stage();
        	newStage.initModality(Modality.APPLICATION_MODAL);
        	//Create a new scene with the root element in it.
            Scene scene = new Scene(page); //720, 660
            //Assign the scene to the page
            newStage.setScene(scene);
            newStage.setTitle("Pause");
            newStage.setResizable(false);
        	((W_MyCards) (loader.getController())).setUpController(newStage, engine, self);
            newStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

	@FXML
	void showPauseMenu(ActionEvent event) {
		try {
        	FXMLLoader loader = new FXMLLoader(W_Game.class.getClassLoader().getResource("ui/W_Pause.fxml"));
        	AnchorPane page = (AnchorPane) loader.load();
        	Stage newStage = new Stage();
        	/* Create a new scene with the root element in it. */
            Scene scene = new Scene(page);
            /* Assign the scene to the page */
            newStage.setScene(scene);
            newStage.setTitle("Pause");
            newStage.setResizable(false);
            newStage.initModality(Modality.APPLICATION_MODAL);
        	((W_Pause) (loader.getController())).setUpController(newStage, engine, self);
        	//self.close(); //could change to showandwait, and get rid of this
            newStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

	@FXML
	void showScoreboard(ActionEvent event) {
		try {
        	FXMLLoader loader = new FXMLLoader(W_Game.class.getClassLoader().getResource("ui/W_Scoreboard.fxml"));
        	AnchorPane page = (AnchorPane) loader.load();
        	Stage newStage = new Stage();
        	/* Create a new scene with the root element in it. */
            Scene scene = new Scene(page); //890, 400
            /* Assign the scene to the page */
            newStage.setScene(scene);
            newStage.setTitle("Scoreboard");
            newStage.setResizable(false);
            newStage.initModality(Modality.APPLICATION_MODAL);
        	((W_Scoreboard) (loader.getController())).setUpController(newStage, self, engine);
        	//self.close(); //could change to showandwait, and get rid of this
            newStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }	}
	
	/**
	 * Sets up the main game window
	 * @param stage	Window the content is going to be displayed on
	 * @param engineinput	GameEngine object
	 * @param selfinput		calling Stage
	 */
	public void setUpController(Stage stage, GameEngine engineinput, Stage selfinput){
		engine=engineinput;
		caller=selfinput;
		self=stage;
		gridButtons = new ArrayList<Button>();
		highlightedIds = new HashMap<String, Boolean>();
		lastHoverPos = new Position(-1,-1);
		
		preloadImages();
		GridPane grid = new GridPane();
		grid.setId("backround");
		((BorderPane)stage.getScene().getRoot()).getChildren().add(grid);
		((BorderPane)stage.getScene().getRoot()).setId("backround");
		gridDetails.setId("backround1");
        grid.setPadding(new Insets(0,0,0,30));
        int val = 0;
        int colourSwitch=0;
		drawBoard(engine, grid, colourSwitch, val);
		engine.setState("action", State.PREPARE);
		updateView();
	}
	
	/**
	 * Main move() method. Does a lot of magic. Handles all grid clicks.
	 * @param event	event passed in by the loader
	 * @param n	object calling (important!)
	 */
	@FXML
	public void move(ActionEvent event, Button n) {
		Player currentPlayer = engine.getCurrentPlayer();
		int x;
		int y;
		int distance;
		Direction dir;
		State currentState = engine.getState("action");
		try {
			switch (currentState){
			case ATTMOVE:
				currentPlayer = engine.getFightLoser();
			case MOVE:
				x = calcX((Integer.parseInt(n.getId())));
				y = calcY(Integer.parseInt(n.getId()));
				distance = engine.getDistanceBetween(new Position(x,y), currentPlayer.getShip().getPos());
				dir = engine.getDirectionTo(currentPlayer.getShip().getPos(), new Position(x,y));
				//check if we're clicking on a highlighted/turnable button
				if (dir != null && highlightedIds.get(n.getId()) != null){
					//check if it's a regular (valid) move
					if (currentState == State.MOVE && engine.isValidMove(currentPlayer, distance, dir)){
						//check if we cross any ships on our way first
						if (engine.crossesShipOnMove(currentPlayer, distance, dir)){
							performEncounter(currentPlayer, distance, dir); //handle the fighting in a separate function
						} else { //a perfectly valid move
							engine.movePlayer(currentPlayer, distance, dir); //move player first
							highlightedIds.clear();
							updateGrid();
							MapObject destinationObj = engine.getPlayerDestinationObject(currentPlayer); //get interactive (if any)
							if (destinationObj != null && destinationObj instanceof Ship 
									&& ((Ship) destinationObj).getOwner() != currentPlayer){ //fighting has to be handled in the W_Game due to its nature
								performFight(currentPlayer); //perform the regular fight
							} else if (destinationObj != null){ //some other interactive found
								InteractionController controller = new InteractionController(this, engine, currentPlayer.getShip(), destinationObj);
								controller.interact();
							} else {
								engine.setState("action", State.TURN);
							}
						}
					//if not a regular move (or not valid move), but a retreating move
					} else if (engine.isValidRetreatingMove(currentPlayer, distance, dir)){
						engine.movePlayer(currentPlayer, distance, dir);
						engine.setState("action", State.ATTURN);
					}
				}
				if (currentPlayer.getShip().isAtHomePort() && engine.getWinner() != null){
					performWinnerScreen();
				}
				break;
			case ATTURN:
				currentPlayer = engine.getFightLoser();
			case TURN:
				x = calcX((Integer.parseInt(n.getId())));
				y = calcY(Integer.parseInt(n.getId()));
				distance = engine.getDistanceBetween(new Position(x,y), currentPlayer.getShip().getPos());
				dir = engine.getDirectionTo(currentPlayer.getShip().getPos(), new Position(x,y));
				if (distance == 1 && highlightedIds.get(n.getId()) != null 
						&& engine.isValidTurn(currentPlayer, dir)){
					engine.turnPlayer(currentPlayer, dir);
					engine.setState("action", State.PREPARE);
					engine.incCurrentPlayer();
					showPrepareDialog(engine.getCurrentPlayer());
				}
				break;
			}
				
		} catch (InvalidMoveException e) {
			e.printStackTrace();
		} catch (InvalidDirectionException e) {
			e.printStackTrace();
		}
		//finally update the window view
		updateView();
	}

	/**
	 * Updates the whole window view according to current state of the game
	 */
	public void updateView(){
		if (engine.getState("action") != State.PREPARE)
			highlightValidMoves();
		updateDetails(engine.getCurrentPlayer());
		updateGrid();
	}
	
	/**
	 * Updates the details panel for the current player and state of the game
	 * @param player	Player object to update for (usually engine.getCurrentPlayer() )
	 */
	public void updateDetails(Player player){
		pName.setText(player.getName());
		switch (engine.getPlayerIndex(player)){
		case 0:
			pName.setTextFill(Paint.valueOf("#b90808"));
			break;
		case 1:
			pName.setTextFill(Paint.valueOf("#082eb9"));
			break;
		case 2:
			pName.setTextFill(Paint.valueOf("#b9b908"));
			break;
		case 3:
			pName.setTextFill(Paint.valueOf("#08b928"));
			break;
		}
		pIcon.setImage(images_icons[engine.getPlayerIndex(player)]);
		pIcon.setFitWidth(32);
		pIcon.setFitHeight(32);
		pScore.setText(String.valueOf(PortInteraction.calculateTreasure(player.getHomePort().getTreasureList())));
		pFStrength.setText("HIDDEN");
		pMStrength.setText("HIDDEN");
		//show player's sensitive details if safe
		if (engine.getState("action") != State.PREPARE){
			pFStrength.setText(String.valueOf(player.getFightingStrength()));
			pMStrength.setText(String.valueOf(player.getMovingStrength()));
		}
		//show ship treasure if available
		LinkedList<Object> treasures = new LinkedList<Object>();
		treasures.addAll(player.getShip().getTreasureList());
		LinkedList<Image> imgs = InteractionController.getImages(treasures);
		if (imgs.size() > 0){
			pTreasure1.setImage(imgs.get(0));
			pTreasure1.setFitWidth(32);
			pTreasure1.setFitHeight(32);
			pTreasure1.setVisible(true);
		} else {
			pTreasure1.setVisible(false);
		}
		if (imgs.size() > 1){
			pTreasure2.setImage(imgs.get(1));
			pTreasure2.setFitWidth(32);
			pTreasure2.setFitHeight(32);
			pTreasure2.setVisible(true);
		} else {
			pTreasure2.setVisible(false);
		}
	}
	
	/**
	 * Updates hovering box based on X and Y given
	 * @param x	X coordinate of the current position to inspect
	 * @param y	Y coordinate of the current position to inspect
	 */
	public void updateHover(int x, int y){
		if (lastHoverPos.x == x && lastHoverPos.y == y){ //don't update if mouse position hasn't changed (waste of resources)
			return;
		} else {
			lastHoverPos.x = x; lastHoverPos.y = y;
		}
		LinkedList<MapObject> objList = engine.getObjectsByPos(x, y);
		gridDetails.getChildren().clear();
		for (MapObject obj : objList){
			VBox mapObjectBox = new VBox();
			Label itemName = new Label();
			itemName.setId("cardheader");
			itemName.getStylesheets().add("ui/styles.css");
			mapObjectBox.getChildren().add(itemName);
			if (obj instanceof Port){
				if (obj instanceof PlayerPort){
					PlayerPort port = (PlayerPort) obj;
					itemName.setText(port.getName());
					mapObjectBox.getChildren().add(new Label("Owner: " + port.getOwner().getName()));
					if (port.getTreasureNum() > 0){
						mapObjectBox.getChildren().add(new Label("Treasure: "));
						mapObjectBox.getChildren().add(prepareTreasureHBox(port));
					}
					mapObjectBox.getChildren().add(new Label("Cards: "));
					mapObjectBox.getChildren().add(prepareCardsHBox(port.getOwner()));
				} else {
					Port port = (Port) obj;
					itemName.setText(port.getName());
					if (port.getTreasureNum() > 0){
						mapObjectBox.getChildren().add(new Label("Treasure: "));
						mapObjectBox.getChildren().add(prepareTreasureHBox(port));
					}
					mapObjectBox.getChildren().add(new Label("Cards: "));
					mapObjectBox.getChildren().add(prepareCardsHBox(port));
				}
			} else if (obj instanceof Ship){
				Ship ship = (Ship) obj;
				itemName.setText("Ship");
				mapObjectBox.getChildren().add(new Label("Owner: " + ship.getOwner().getName()));
				if (ship.getTreasureNum() > 0){
					mapObjectBox.getChildren().add(new Label("Treasure: "));
					mapObjectBox.getChildren().add(prepareTreasureHBox(ship));
				}
			} else if (obj instanceof TreasureIsland){
				TreasureIsland island = (TreasureIsland) obj;
				itemName.setText("Treasure Island");
				mapObjectBox.getChildren().add(new Label("Treasure: "));
				mapObjectBox.getChildren().add(prepareTreasureHBox(island));
			} else if (obj instanceof FlatIsland){
				FlatIsland island = (FlatIsland) obj;
				itemName.setText("Flat Island");
				mapObjectBox.getChildren().add(new Label("Treasure: "));
				mapObjectBox.getChildren().add(prepareTreasureHBox(island));
				mapObjectBox.getChildren().add(new Label("Cards: "));
				mapObjectBox.getChildren().add(prepareCardsHBox(island));
			} else if (obj instanceof PirateIsland){
				PirateIsland island = (PirateIsland) obj;
				itemName.setText("Pirate Island");
				mapObjectBox.getChildren().add(new Label("Cards: "));
				mapObjectBox.getChildren().add(prepareCardsHBox(island));
			}
			gridDetails.getChildren().add(mapObjectBox);
			gridDetails.setMargin(mapObjectBox, new Insets(5, 0, 5, 0));
		}
	}
	
	/**
	 * Returns an HBox item with treasure images based on
	 * the holder given
	 * @param holder TreasureHolder object
	 * @return	HBox with images
	 */
	public HBox prepareTreasureHBox(TreasureHolder holder){
		HBox result = new HBox();
		int diamonds = 0;
		int rubies = 0;
		int gold = 0;
		int rum = 0;
		int pearls = 0;
		for (Treasure t : holder.getTreasureList()){
			switch ( t.getType() ){
			case DIAMOND:
				diamonds++;
				break;
			case RUBY:
				rubies++;
				break;
			case GOLD:
				gold++;
				break;
			case RUM:
				rum++;
				break;
			case PEARL:
				pearls++;
				break;
			}	
		}
		LinkedList<Image> images = new LinkedList<Image>();
		LinkedList<Integer> counts = new LinkedList<Integer>();
		if (diamonds > 0){
			counts.add(diamonds);
			images.add(images_treasure[0]);
		}
		if (rubies > 0){
			counts.add(rubies);
			images.add(images_treasure[1]);
		}
		if (gold > 0){
			counts.add(gold);
			images.add(images_treasure[2]);
		}
		if (pearls > 0){
			counts.add(pearls);
			images.add(images_treasure[3]);
		}
		if (rum > 0){
			counts.add(rum);
			images.add(images_treasure[4]);
		}
		for (Image i : images){
			ImageView imgView = new ImageView(i);
			Label text = new Label(String.valueOf(counts.removeFirst()));
			imgView.setFitHeight(32);
			imgView.setFitWidth(32);
			result.getChildren().add(imgView);
			result.getChildren().add(text);
			result.setMargin(text, new Insets(0, 5, 0, 0));
		}
		result.setAlignment(Pos.CENTER_LEFT);
		return result;
	}
	
	/**
	 * Prepares an HBox filled with images based on the CardHolder given
	 * (distinguishes Player and covers the CrewCards!)
	 * Displays only ChanceCards 21, 23, 24
	 * @param holder	Holder of the cards
	 * @return	HBox item with images
	 */
	private HBox prepareCardsHBox(CardHolder holder){
		//cards are VERY unpredictable in terms of count - show first 5 and notify that there's more
		int count = 0;
		int index = 0;
		HBox result = new HBox();
		LinkedList<Object> cards = new LinkedList<Object>();
		while(count < 5 && index < holder.size()){
			Card c = (Card) holder.getCards().get(index);
			if ( c instanceof ChanceCard){
				ChanceCard cc = (ChanceCard)c;
				int num = cc.getNumber();
				if (num != 21 && num != 23 && num != 24){
					index++;
					continue;
				}
			}
			cards.add((Object)holder.getCards().get(index++));
			count++;
		}
		LinkedList<Image> images;
		if (holder instanceof Player || holder instanceof PirateIsland || holder instanceof FlatIsland){
			images = getIncognitoCardImages(cards);
		} else {
			images = InteractionController.getImages(cards);
		}
		for (Image i : images){
			ImageView imgView = new ImageView(i);
			imgView.setFitHeight(55);
			imgView.setFitWidth(42);
			result.getChildren().add(imgView);
		}
		if (count == 5 && holder.size() > index){
			result.getChildren().add(new Label("..."));
		}
		return result;
	}
	
	/**
	 * Returns a list of images of CrewCards/ChanceCards
	 * but with the colour of the CrewCards hidden
	 * @param list	Cards to display
	 * @return	List of images of the cards passed
	 */
	public LinkedList<Image> getIncognitoCardImages(LinkedList<Object> list){
		LinkedList<Image> result = new LinkedList<Image>();
		for (Object t : list){
			if (t instanceof CrewCard){
				int value = ((CrewCard)t).getValue();
				result.add(images_crewCards[value-1]);
			} else if (t instanceof ChanceCard){
				int number = ((ChanceCard)t).getNumber();
				switch (number){
				case 21:
					result.add(images_chanceCards[0]);
					break;
				case 23:
					result.add(images_chanceCards[1]);
					break;
				case 24:
					result.add(images_chanceCards[1]);
					break;
				}
			} else {
				Treasure treasure = (Treasure) t;
				switch (treasure.getType()){
				case DIAMOND:
					result.add(images_treasure[0]);
					break;
				case RUBY:
					result.add(images_treasure[1]);
					break;
				case GOLD:
					result.add(images_treasure[2]);
					break;
				case PEARL:
					result.add(images_treasure[3]);
					break;
				case RUM:
					result.add(images_treasure[4]);
					break;
				}
			}
		}
		return result;
	}

	/**
	 * Performs a regular fight (player ended turn at another player's ship)
	 */
	private void performFight(Player currentPlayer){
		Stage stage = new Stage();
		String enemy = ((Ship) engine.getPlayerDestinationObject(currentPlayer)).getOwner().getName();
		Popup_Simple confirmWindow = new Popup_Simple(self, stage, "A gruesome encounter!", images_other[11], "An intense fight has happened between the ships of "
				+ currentPlayer.getName() + " and " + enemy + "!");
		confirmWindow.getStage().showAndWait();
		engine.setState("action", State.PREPARE); //hiding details for the duration of the fight
		updateDetails(currentPlayer); //hiding details for the duration of the fight
		W_FightConfirm controller = getFightController(currentPlayer.getShip().getPos());
		controller.swapFightingSides();
		controller.conductFight();
		updateView();
	}
	
	/**
	 * Performs a whole encounter, where player tries to cross past another ship
	 * (another player can then attack or leave it)
	 */
	private void performEncounter(Player currentPlayer, int distance, Direction direction){
		Ship staticShip = engine.getBlockingShip(currentPlayer, direction);
		int firstDistance = engine.getDistanceBetween(currentPlayer.getShip().getPos(), staticShip.getPos());
		try {
			engine.movePlayer(currentPlayer, firstDistance, direction);
		} catch (InvalidMoveException e) {
			e.printStackTrace();
		}
		Position finalDestination = new Position(currentPlayer.getShip().getPos().x, currentPlayer.getShip().getPos().y);
		for (int i = 0; i < distance - firstDistance; i++){
			finalDestination.x = engine.addDirectionalX(finalDestination.x, direction);
			finalDestination.y = engine.addDirectionalY(finalDestination.y, direction);
		}
		Stage stage = new Stage();
		String enemy = ((Ship) engine.getPlayerDestinationObject(currentPlayer)).getOwner().getName();
		
		highlightedIds.clear();
		updateGrid();
		
		String text = "You've tried to sneak around an enemy ship, but your large booty been spotted!\n"
				+ "It's up to the other captain whether the fight is going to happen!";
		Popup_Simple confirmWindow = new Popup_Simple(self, stage, "Another ship encountered!", images_other[11], text);
		confirmWindow.getStage().showAndWait();
		engine.setState("action", State.PREPARE);
		showPrepareDialog(staticShip.getOwner());
		engine.setState("action", State.PREPARE); //hiding details for the duration of the fight
		updateDetails(staticShip.getOwner()); //hiding details for the duration of the fight
		getFightController(finalDestination).getStage().showAndWait();
	}
	
	/**
	 * Returns a W_FightConfirm controller based on the destination of the player
	 * without actually showing the window
	 * @param destination	destination of the move (of the current player)
	 * @return	W_FightConfirm object
	 */
	private W_FightConfirm getFightController(Position destination){
		try {
        	FXMLLoader loader = new FXMLLoader(W_Game.class.getClassLoader().getResource("ui/FightConfirm.fxml"));
        	VBox page = (VBox) loader.load();
        	Stage newStage = new Stage();
        	newStage.initModality(Modality.APPLICATION_MODAL);
        	((W_FightConfirm) (loader.getController())).setUpController(newStage, self, this, engine, destination);
        	Scene scene = new Scene(page, 460, 377);
            newStage.setScene(scene);
            newStage.setTitle("Set fight");
            newStage.setResizable(false);
            return (W_FightConfirm) loader.getController();
        } catch (Exception ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
		return null;
	}
	
	/**
	 * Highlights all the valid moves for the current state of the game
	 */
	private void highlightValidMoves(){
		highlightedIds.clear();
		if (engine.getState("action") == State.MOVE){
			Player player = engine.getCurrentPlayer();
			Ship ship = player.getShip();
			if (engine.getPlayerDestinationObject(player) instanceof Port){
				highlightDirectionalMoves(ship.getPos().x, ship.getPos().y, Direction.N, player, false);
				highlightDirectionalMoves(ship.getPos().x, ship.getPos().y, Direction.NE, player, false);
				highlightDirectionalMoves(ship.getPos().x, ship.getPos().y, Direction.E, player, false);
				highlightDirectionalMoves(ship.getPos().x, ship.getPos().y, Direction.SE, player, false);
				highlightDirectionalMoves(ship.getPos().x, ship.getPos().y, Direction.S, player, false);
				highlightDirectionalMoves(ship.getPos().x, ship.getPos().y, Direction.SW, player, false);
				highlightDirectionalMoves(ship.getPos().x, ship.getPos().y, Direction.W, player, false);
				highlightDirectionalMoves(ship.getPos().x, ship.getPos().y, Direction.NW, player, false);
			} else {
				highlightDirectionalMoves(ship.getPos().x, ship.getPos().y, ship.getOrientation(), player, false);
			}
			if (highlightedIds.isEmpty()){ //no valid moves, proceed to turning
				engine.setState("action", State.TURN);
				updateView();
				return;
			}
		} else if (engine.getState("action") == State.ATTMOVE){
			Player player = engine.getFightLoser();
			Ship ship = player.getShip();
				highlightDirectionalMoves(ship.getPos().x, ship.getPos().y, Direction.N, player, true);
				highlightDirectionalMoves(ship.getPos().x, ship.getPos().y, Direction.NE, player, true);
				highlightDirectionalMoves(ship.getPos().x, ship.getPos().y, Direction.E, player, true);
				highlightDirectionalMoves(ship.getPos().x, ship.getPos().y, Direction.SE, player, true);
				highlightDirectionalMoves(ship.getPos().x, ship.getPos().y, Direction.S, player, true);
				highlightDirectionalMoves(ship.getPos().x, ship.getPos().y, Direction.SW, player, true);
				highlightDirectionalMoves(ship.getPos().x, ship.getPos().y, Direction.W, player, true);
				highlightDirectionalMoves(ship.getPos().x, ship.getPos().y, Direction.NW, player, true);
		}
			
	}
	
	/**
	 * Highlights all buttons with valid moves
	 * @param x	starting X of the ship (to find the right button)
	 * @param y	starting Y of the ship (to find the right button)
	 * @param dir	Direction in which to highlight
	 * @param player	Player object (to pass to the engine)
	 * @param retreating	whether it's a retreating move or not
	 */
	private void highlightDirectionalMoves(int x, int y, Direction dir, Player player, boolean retreating){
		//cases where it's impossible to just stay in place
		if (!(
				retreating 
				|| engine.getPlayerDestinationObject(player) instanceof Port	//if in a port
				|| engine.getPlayerDestinationObject(player) instanceof TreasureIsland	//if next to TIsland
				|| engine.getPlayerDestinationObject(player) instanceof FlatIsland)){	//if next to FIsland
			highlightedIds.put(getButtonByPos(x,y).getId(), true);
		}
		x = engine.addDirectionalX(x, dir);
		y = engine.addDirectionalY(y, dir);
		int distance = 1;
		int movingStrength = player.getMovingStrength();
		if (!retreating){
			while(engine.isValidMove(player, distance, dir) && distance <= movingStrength){
				highlightedIds.put(getButtonByPos(x,y).getId(), true);
				x = engine.addDirectionalX(x, dir);
				y = engine.addDirectionalY(y, dir);
				distance++;
			}
		} else {
			while(engine.isValidRetreatingMove(player, distance, dir) && distance <= movingStrength){
				highlightedIds.put(getButtonByPos(x,y).getId(), true);
				x = engine.addDirectionalX(x, dir);
				y = engine.addDirectionalY(y, dir);
				distance++;
			}
		}
	}
	
	/**
	 * Updates the grid according to the current state of the game
	 */
	private void updateGrid(){
		boolean turning = false;
		boolean moving = false;
		if (engine.getState("action").equals(State.ATTURN)
				|| engine.getState("action").equals(State.TURN)){
			turning = true;
		} else if (engine.getState("action").equals(State.MOVE)
				|| engine.getState("action").equals(State.ATTMOVE)){
			moving = true;
		}
		//update the grid
		for (Button b : gridButtons){
			StackPane images = new StackPane();
			int x = calcX(Integer.parseInt(b.getId()));
			int y = calcY(Integer.parseInt(b.getId()));
			Position currentPos = new Position(x, y);
			//first image - background/sea
			if ( x%2 == 0 && y%2 == 0 || x%2 == 1 && y%2 == 1){
				images.getChildren().add(new ImageView(images_other[0]));
			} else {
				images.getChildren().add(new ImageView(images_other[1]));
			}
			//other images
			try {
				//go over the objects on given tile
				LinkedList<MapObject> currentMapObjects = engine.getBoard().getObjectByPos(currentPos);
				for (MapObject o : currentMapObjects){ //first iteration - ports
					if (o instanceof Port){ //detect a port + drag an image of it
						images.getChildren().add(getPortImage((Port) o));
					}
				}
				//ships nao
				for (MapObject o : currentMapObjects){ //second iteration - putting a potential ship on top
					if (o instanceof Ship){
						images.getChildren().add(getShipImage((Ship) o));
					}
				}
			} catch (InvalidPositionException e) {
				e.printStackTrace();
			}
			//arrows (if turning)
			if (turning){
				int currX, currY;
				Player currP;
				if (engine.getState("action").equals(State.TURN)){
					currP = engine.getCurrentPlayer();
				} else {
					currP = engine.getFightLoser();
				}
				currX = currP.getShip().getPos().x;
				currY = currP.getShip().getPos().y;
				highlightedIds.put(b.getId(), true); //put in the array for clicking detection
				//check if the button is the one around the current player's ship
				if (Math.abs(currX - x) <= 1 && Math.abs(currY - y) <= 1
						&& engine.isValidTurn(currP, engine.getDirectionTo(currX, currY, x, y))){
					images.getChildren().add(getTurnArrowImage(currX - x, currY - y));
				}
			}
			//highlighted squares (if moving)
			if (moving){
				if (highlightedIds.get(b.getId()) != null){
					images.getChildren().add(new ImageView(images_other[10]));
				}
			}
			//set sizes and display on buttons
			for (Node n : images.getChildren()){
				ImageView i = (ImageView) n;
				i.setFitHeight(gridBtnHeight);
				i.setFitWidth(gridBtnWidth);
			}
			images.setPrefWidth(gridBtnWidth);
			images.setPrefHeight(gridBtnHeight);
			b.setMaxWidth(gridBtnWidth);
			b.setMaxHeight(gridBtnHeight);
			b.setGraphic(images);
		}
	}
	
	/**
	 * Gets a correct image for a given port to display
	 * (also based on the state of the game)
	 * @param port
	 * @return
	 */
	private ImageView getPortImage(Port port){
		Image img = images_ports[4];
		if (port instanceof PlayerPort){
			PlayerPort pport = (PlayerPort) port;
			for (int i = 0; i < 4; i++){
				if (engine.getPlayer(i) == pport.getOwner()){
					img = images_ports[i];
				}
			}
		} else if (port.getName().equals("Anchor Bay")){
			img = images_ports[5];
		}
		ImageView result = new ImageView(img);
		return result;
	}
	
	/**
	 * Returns an image for a given Ship object (considering
	 * player owning the ship and the direction of it)
	 * @param ship	Ship object to get the image for
	 * @return	ImageView object for the given ship
	 */
	private ImageView getShipImage(Ship ship){
		Image img = images_ships[0];
		for (int i = 0; i < 4; i++){
			if (ship.getOwner() == engine.getPlayer(i)){
				img = images_ships[i * 8 + getDirectionIndex(ship.getOrientation())];
			}
		}
		return new ImageView(img);
	}
	
	/**
	 * Returns an image for a given Player
	 * @param player	Player to get the icon for
	 * @return	ImageView object with the icon (32x32 by default)
	 */
	public ImageView getPlayerIconImage(Player player, boolean large){
		int largeAdd = 0;
		if (large)
			largeAdd = 4;
		Image img = images_icons[0];
		for (int i = 0; i < 4; i++){
			if (player == engine.getPlayer(i)){
				img = images_icons[i + largeAdd];
			}
		}
		return new ImageView(img);
	}
	
	/**
	 * Returns an image for turning the ship, which
	 * direction is based on the difference of the tiles
	 * given
	 * @param diffX	difference between current player's X and the
	 * displaying tile's X
	 * @param diffY	difference between current player's Y and the
	 * displaying tile's Y
	 * @return
	 */
	private ImageView getTurnArrowImage(int diffX, int diffY){
		Image img = null;
		if (diffX == 1){
			if (diffY == 1){
				img = images_other[2 + getDirectionIndex(Direction.SW)];
			} else if (diffY == 0){
				img = images_other[2 + getDirectionIndex(Direction.W)];
			} else {
				img = images_other[2 + getDirectionIndex(Direction.NW)];
			}
		} else if (diffX == 0){
			if (diffY == 1){
				img = images_other[2 + getDirectionIndex(Direction.S)];
			} else if (diffY == 0){
				//empty - center
			} else {
				img = images_other[2 + getDirectionIndex(Direction.N)];
			}
		} else {
			if (diffY == -1){
				img = images_other[2 + getDirectionIndex(Direction.NE)];
			} else if (diffY == 0){
				img = images_other[2 + getDirectionIndex(Direction.E)];
			} else {
				img = images_other[2 + getDirectionIndex(Direction.SE)];
			}
		}
		return new ImageView(img);
	}
	
	/**
	 * Shows a confirmation dialog for the preparing player
	 */
	public void showPrepareDialog(Player player){
		Stage stage = new Stage();
		Image img = getPlayerIconImage(player, true).getImage();
		stage.initModality(Modality.APPLICATION_MODAL);
		if(engine.getState("action") == State.PREPARE){
			updateDetails(player);
			Popup_Simple confirmWindow = new Popup_Simple(self, stage, player.getName() + "'s time, ARRRRRR!", img, "It's " + player.getName() + "'s turn!");
			confirmWindow.getStage().showAndWait();
			engine.setState("action", State.MOVE);
			updateView();
		}
	}
	
	private void performWinnerScreen(){
        Player player = engine.getWinner();
        ImageView winnerIcon = getPlayerIconImage(player, true);
        Image img = winnerIcon.getImage();
        LinkedList<Button> buttons = new LinkedList<Button>();
       
        Button startAgain = new Button();
        startAgain.setOnAction(event -> returnToMenu());
        startAgain.setText("Return to Main Menu");
        buttons.add(startAgain);
        Button exit = new Button();
        exit.setOnAction(event -> System.exit(0));
        exit.setText("Exit Game");
        buttons.add(exit);
       
        String winnerText = "Congratulations, " + player.getName() + ", you have won Buccaneer!\n\n What would you like to do next?";
        Popup_MultipleOptions popup = new Popup_MultipleOptions(self, caller, "Winner!", img, winnerText, buttons);
        popup.getStage().show();
    }
	
	void returnToMenu() {
        try {
            engine = new GameEngine();
            //Create a FXMLLoader - this is how you load a particular fxml file (the template of your window)
            FXMLLoader loader = new FXMLLoader(W_Pause.class.getClassLoader().getResource("ui/MainMenuFXML.fxml"));
            //Load the parent/root element in the file (in this case an HBox object)
            BorderPane page = (BorderPane) loader.load();
           
            //Get the controller assigned to this file and set its instance variable.
            //Make sure your file has fx:controller="name" specified for the class that's going to control your scene (can be found on the left bottom corner in the scene builder software under 'Controller' tab).
            //can call any public method of the Controller class to set up parameters (e.g. to set up scene's GameEngine or Caller object in our buccaneer game)
            Stage primaryStage = new Stage();
            //Create a new scene with the root element in it.
            Scene scene = new Scene(page);
            //Assign the scene to the page
            primaryStage.setScene(scene);
            primaryStage.setTitle("Main Menu");
            primaryStage.setResizable(false);
            ((W_MainMenu) (loader.getController())).setUpController(primaryStage, engine);
            //Show the window
            caller.close();
            self.close();
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

	/**
	 * Preloads images so they don't have to be loaded every time screen gets refreshed
	 */
	private void preloadImages(){
		for (int i = 0; i < OthersSize; i++){
			images_other[i] = new Image(filenames_other[i], gridBtnWidth, gridBtnHeight, false, false);
		}
		for (int i = 0; i < PortsSize; i++){
			images_ports[i] = new Image(filenames_ports[i], gridBtnWidth, gridBtnHeight, false, false);
		}
		for (int i = 0; i < ShipsSize; i++){
			images_ships[i] = new Image(filenames_ships[i], gridBtnWidth, gridBtnHeight, false, false);
		}
		for (int i = 0; i < IconsSize; i++){
			images_icons[i] = new Image(filenames_playerIcons[i], gridBtnWidth, gridBtnHeight, false, false);
		}
		for (int i = 0; i < TreasureSize; i++){
			images_treasure[i] = new Image(filenames_treasure[i], gridBtnWidth, gridBtnHeight, false, false);
		}
		for (int i = 0; i < CrewCardsSize; i++){
			images_crewCards[i] = new Image(filenames_crewCards[i], 135, 178, false, false);
		}
		for (int i = 0; i < ChanceCardsSize; i++){
			images_chanceCards[i] = new Image(filenames_chanceCards[i], 135, 178, false, false);
		}
		//Load icons for a larger version of the image
		for (int i = IconsSize; i < IconsSize * 2; i++){
			images_icons[i] = new Image(filenames_playerIcons[i - IconsSize], gridBtnWidth * 8, gridBtnHeight * 8, false, false);
		}
		//special cases
		images_other[11] = new Image(filenames_other[11], 256, 256, false, false); //fighting image
		images_islands[0] = new Image(filenames_islands[0], 34 * 3, 34 * 4, false, false); //flat island
		images_islands[1] = new Image(filenames_islands[1], 34 * 4, 34 * 4, false, false); //treasure island
		images_islands[2] = new Image(filenames_islands[2], 34 * 3, 34 * 4, false, false); //pirate island
	}
	
	/**
	 * Calculates X coordinate (game-wise) based on button's ID (as integer)
	 * @param i	button's id
	 * @return	X coordinate
	 */
	int calcX(int i){
		int x = i%20;
		if(x==0){
			x=x+20;
		}
		return x;
	}
	
	/**
	 * Calculates Y coordinate (game-wise) based on button's ID (as integer)
	 * @param i	button's id
	 * @return	Y coordinate
	 */
	int calcY(int i){
		int y = (i/20)+1;
		if(calcX(i)==20){
			y--;
		}
		return y;
	}
	
	/**
	 * Returns a grid button based on the coordinates given
	 * @param x	x coordinate
	 * @param y	y coordinate
	 * @return	Button object
	 */
	private Button getButtonByPos(int x, int y){
		for (Button b : gridButtons){
			if (calcX(Integer.parseInt(b.getId())) == x){
				if (calcY(Integer.parseInt(b.getId())) == y){
					return b;
				}
			}
		}
		return null;
	}
	
	/**
	 * Creates all the buttons for the game
	 * @param game	GameEngine object
	 * @param grid	GridPane object to add the buttons to
	 * @param colourSwitch	used for switching between lightblue/darkblue tiles (deprecated?)
	 * @param val	used to keep track of increasing button IDs
	 */
	public void drawBoard(GameEngine game,GridPane grid, int colourSwitch, int val){
		BorderStroke bs = new BorderStroke(Color.DEEPSKYBLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT);
		Border border = new Border(bs);
		Background back = new Background(new BackgroundFill(Color.LIGHTBLUE,CornerRadii.EMPTY,Insets.EMPTY));
		Background back1 = new Background(new BackgroundFill(Color.LIGHTSKYBLUE,CornerRadii.EMPTY,Insets.EMPTY));
		String buttonVal = "";
		
		for(int row=20; row>0; row--){
	    	for(int col=1; col<21; col++){
	    		val++;
	    		if((col==2 || col==3 || col==4)  && (row==invertrow(16) || row==invertrow(17) || row==invertrow(18) || row==invertrow(19))){
	    			int cropX = (col-2) * 34;
	    			int cropY = (row-2) * 34;
	    			Image islandImg = images_islands[0];
	    			PixelReader reader = islandImg.getPixelReader();
	    			WritableImage cropped = new WritableImage(reader, cropX, cropY, 34, 34);
	    			ImageView islandPart = new ImageView(cropped);
	    			islandPart.setOnMouseMoved(event -> updateHover(2, 19));
	    			grid.add(islandPart, col, row);
	    		}else if((col==17 || col==18 || col==19)  && (row==invertrow(2) || row==invertrow(3) || row==invertrow(4) || row==invertrow(5))){
	    			int cropX = (col-17) * 34;
	    			int cropY = (row-16) * 34;
	    			Image islandImg = images_islands[2];
	    			PixelReader reader = islandImg.getPixelReader();
	    			WritableImage cropped = new WritableImage(reader, cropX, cropY, 34, 34);
	    			ImageView islandPart = new ImageView(cropped);
	    			islandPart.setOnMouseMoved(event -> updateHover(19, 2));
	    			grid.add(islandPart, col, row);
	    		}else if((col==9 || col==10 || col==11|| col==12)  && (row==invertrow(9) || row==invertrow(10) || row==invertrow(11) || row==invertrow(12))){
	    			int cropX = (col-9) * 34;
	    			int cropY = (row-9) * 34;
	    			Image islandImg = images_islands[1];
	    			PixelReader reader = islandImg.getPixelReader();
	    			WritableImage cropped = new WritableImage(reader, cropX, cropY, 34, 34);
	    			ImageView islandPart = new ImageView(cropped);
	    			islandPart.setOnMouseMoved(event -> updateHover(9, 9));
	    			grid.add(islandPart, col, row);	
	    		}else{
	    			colourSwitch++;
	    	    	Button myButton = new Button();
	    			myButton.setPadding(new Insets(0,0,0,0));
	    			buttonVal = String.valueOf(val);
	    			myButton.setId(buttonVal);
	    			myButton.setOnAction(e -> this.move(e,myButton)); 
	    			myButton.onMouseMovedProperty().set(event -> {
	    				updateHover(calcX(Integer.parseInt(myButton.getId())), calcY(Integer.parseInt(myButton.getId())));
	    			});
	    			grid.add(myButton,col,row);
	    			gridButtons.add(myButton);
	    			if (val-1%20==0 && (val<300)){
	    				colourSwitch++;
	    			}
	    			if (val==121 || val==141 || val==161 || val== 181 || val==201 ||  val==221 || val==241 || val==261 || val==281 || val==301 || val==305|| val==321 || val==325 || val==341 || val==345|| val==361 || val==365){
	    				colourSwitch++;
	    			}
	    			if(colourSwitch%2==0){
	    				myButton.backgroundProperty().set(back);
	    			}
	    			else if(colourSwitch%2==1){
	    				myButton.backgroundProperty().set(back1);
	    			}
	    			myButton.borderProperty().set(border);
	    			
	    		}
	    	}		
	    }
		for (int i = 1; i <= 20; i++){
			Label number = new Label(String.valueOf(i));
			number.setMaxWidth(32);
			number.setMaxHeight(32);
			number.setPadding(new Insets(0, 0, 0, 8));
			grid.add(number, i, 21);
		}
	}

	/**
	 * Returns an integer based on the direction given
	 * (for images/arrays)
	 * @param dir	Direction object
	 * @return	corresponding int value
	 */
	private int getDirectionIndex(Direction dir){
		switch (dir){
		case N:
			return 0;
		case NE:
			return 1;
		case E:
			return 2;
		case SE:
			return 3;
		case S:
			return 4;
		case SW:
			return 5;
		case W:
			return 6;
		case NW:
			return 7;
		default:
			return 0;	
		}
	}
	
	public int invertrow(int i){ 
		return (21-i);
	}
	
	

}
