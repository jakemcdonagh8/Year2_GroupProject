package ui.game;

import java.util.ArrayList;
import java.util.LinkedList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ui.menus.Popup_Customised;
import uk.ac.aber.cs211.group07.system.GameEngine;
import uk.ac.aber.cs211.group07.system.State;
import uk.ac.aber.cs211.group07.system.exceptions.EmptyPackException;
import uk.ac.aber.cs211.group07.system.exceptions.InvalidPositionException;
import uk.ac.aber.cs211.group07.system.exceptions.TooMuchTreasureException;
import uk.ac.aber.cs211.group07.system.helpers.PlayerInteraction;
import uk.ac.aber.cs211.group07.system.resources.CrewCard;
import uk.ac.aber.cs211.group07.system.resources.MapObject;
import uk.ac.aber.cs211.group07.system.resources.Player;
import uk.ac.aber.cs211.group07.system.resources.Position;
import uk.ac.aber.cs211.group07.system.resources.Ship;
import uk.ac.aber.cs211.group07.system.resources.Treasure;

/**
 * Controller for the fight confirmation popup
 * @author Kamil Cupial
 * @version 0.1 - 01/05/17 - Initial creation (confirm() needs implementing when additional classes are done)
 * @version 0.2 - 03/05/17 - implemented confirm() /juw30
 * @version 0.3 - 04/05/17 - added getStage() /kac12
 * @version 0.4 - 04/05/17 - now accepts game controller as parameter /kac12
 */
public class W_FightConfirm {
	private W_Game gameController;
	private Stage caller;
	private Stage self;
	private Position movingDestination;
	private GameEngine engine;
	private Player attacker;
	private Player defender;
	
	@FXML
	private Text txtFightStr;
	@FXML
	private Text txtPName;
	@FXML
	private Button confirmBtn;
	@FXML
	private Button abortBtn;
	@FXML
	private HBox hboxYour;
	@FXML
	private HBox hboxEnemy;
	
	
	/**
	 * Prepares all the display & sets up the controller
	 * @param self	self-Stage
	 * @param caller	caller-Stage
	 * @param engine	GameEngine object
	 * @param destination	destination of the player performing the move
	 */
	public void setUpController(Stage self, Stage caller, W_Game gameController, GameEngine engine, Position destination){
		/* FXML assignments */
		confirmBtn.setOnAction(event -> {
			confirm();
			self.close();
		});
		abortBtn.setOnAction(event -> abort());
		
		/* The rest of setup */
		this.self = self;
		this.caller = caller;
		this.engine = engine;
		this.movingDestination = destination;
		this.gameController = gameController;
		/* Get player performing the move (being attacked) */
		defender = engine.getCurrentPlayer();
		txtPName.setText(defender.getName());
		/* Get the player deciding whether to attack */
		try {
			LinkedList<MapObject> tile = engine.getBoard().getObjectByPos(engine.getCurrentPlayer().getShip().getPos()); //get all objects player's ship is at
			for (MapObject s : tile){
				if (s instanceof Ship && ((Ship)s).getOwner() != engine.getCurrentPlayer()){ //if the ship is not of the current player's
					attacker = ((Ship)s).getOwner();
				}
			}
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}
		/* Fighting strength */
		txtFightStr.setText(Integer.toString(attacker.getFightingStrength()));
		
		/* Sort out ship contents for both Ships */
		showShipContentImages(attacker.getShip(), hboxYour);
		showShipContentImages(defender.getShip(), hboxEnemy);
	}
	
	/**
	 * Returns the controller's window
	 * @return	Stage object of the controller
	 */
	public Stage getStage(){
		return self;
	}
	
	/**
	 * Performs a fight
	 * moved to a separate function to be used outside the window
	 */
	public void conductFight(){
		Player winner = PlayerInteraction.getFightWinner(attacker, defender);
		LinkedList<Object> treasureAwarded = new LinkedList<Object>();
		LinkedList<Object> treasureLost = new LinkedList<Object>();
		try {
			treasureAwarded = PlayerInteraction.getTreasureAwarded(attacker, defender);
			treasureLost = PlayerInteraction.getTreasureLost(attacker, defender);
			PlayerInteraction.performFight(attacker, defender, engine.getBoard().getTreasureIsland());
		} catch (TooMuchTreasureException | EmptyPackException e) {
			e.printStackTrace();
		}
		
		Stage customised = new Stage();
		
		LinkedList<Image> imgs = new LinkedList<Image>();
		imgs = InteractionController.getImages(treasureAwarded);


		
		Button btn = new Button("OK");
		btn.setOnAction(event -> customised.close());
		LinkedList<Button> btns = new LinkedList<Button>();
		btns.add(btn);
		
		String text_Winner = "" + winner.getName() + " has won. Treasure/cards stolen: " + treasureAwarded.size();
		Popup_Customised popup = new Popup_Customised(self, customised, "Confirmation", imgs, text_Winner, btns);
		popup.getStage().showAndWait();
		
		if (!treasureLost.isEmpty()) {
			imgs.clear();
			imgs = InteractionController.getImages(treasureLost);
			String text_Loser = "Treasure/cards lost to the sea: " + treasureLost.size();
			Popup_Customised popup2 = new Popup_Customised(self, customised, "Confirmation", imgs, text_Loser, btns);
			popup.getStage().showAndWait();
		}
		
		Player loser;
		if (winner == attacker){
			loser = defender;
		} else {
			loser = attacker;
		}
		
		State value = null;
		switch (engine.getPlayerIndex(loser)){
		case 0:
			value = State.T01;
		break;
		case 1:
			value = State.T02;
		break;
		case 2:
			value = State.T03;
		break;
		case 3:
			value = State.T04;
		break;
		}
		
		if (loser != attacker){
			engine.setState("action", State.PREPARE);
			gameController.showPrepareDialog(loser);
		}
		engine.setState("action", State.ATTMOVE);
		engine.setState("attloser", value);
	}
	
	/**
	 * Used for outside code to be able to perform fights on both
	 * (used by W_Game when player ends turn on a ship)
	 */
	public void swapFightingSides(){
		Player t = defender;
		defender = attacker;
		attacker = t;
	}
	
	/**
	 * Player attacking doesn't decide to attack - the move continues
	 */
	private void abort(){
		try {
			engine.getBoard().moveMapObject(engine.getCurrentPlayer().getShip(), movingDestination);
			engine.setState("action", State.PREPARE);
			gameController.showPrepareDialog(defender);
			engine.setState("action", State.TURN);
			self.close();
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * Player attacking decides to attack - the sequence begins
	 * @throws EmptyPackException 
	 * @throws TooMuchTreasureException 
	 */
	private void confirm() {
		conductFight();
		self.close();
		caller.show();
	}
	
	
	/**
	 * Adds images of the ship contents to a given HBox (or EMPTY text if no treasure on ship)
	 * @param ship	Ship object of which contents to show
	 * @param hbox	HBox object to which to add images
	 */
	private void showShipContentImages(Ship ship, HBox hbox){
		ArrayList<Image> images = new ArrayList<Image>();
		if (ship.getTreasureNum() > 0){
			for (Treasure t : ship.getTreasureList()){
				Image img;
				switch (t.getType()){
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
				ImageView imgview = new ImageView(img);
				hbox.getChildren().add(imgview);
			}
		} else {
			Text empty = new Text();
			empty.setText("EMPTY");
			empty.setFont(new Font(21));
			hbox.getChildren().add(empty);
		}
		
	}
	
}
