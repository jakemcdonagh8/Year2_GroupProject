/*
 * @(#) W_TakeTreasure.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */
package ui.game;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import uk.ac.aber.cs211.group07.system.GameEngine;
import uk.ac.aber.cs211.group07.system.exceptions.TooMuchTreasureException;
import uk.ac.aber.cs211.group07.system.resources.Player;
import uk.ac.aber.cs211.group07.system.resources.Treasure;
import uk.ac.aber.cs211.group07.system.resources.TreasureType;

/**
 * Controller for the taking treasure popup
 * @author kac12
 * @version 0.1 - 01/05/17 - Initial creation
 * @version 1.0 - 05/05/17 - Ready for initial release /haa14
 */
public class W_TakeTreasure {
	// Private variables
	private ArrayList<Treasure> toTake = new ArrayList<Treasure>();
	private ArrayList<Treasure> onIsland = new ArrayList<Treasure>();
	private GameEngine engine;
	private int shipCount;
	private int valueLimit;
	private int currValue = 0;
	private Stage self;
	private Player player;
	private ArrayList<Button> addButtons = new ArrayList<Button>();
	private ArrayList<Button> remButtons = new ArrayList<Button>();
	
	// Add/remove + confirm buttons
	@FXML
	Button addDiamond;
	@FXML
	Button addRuby;
	@FXML
	Button addGold;
	@FXML
	Button addRum;
	@FXML
	Button addPearl;
	@FXML
	Button remDiamond;
	@FXML
	Button remPearl;
	@FXML
	Button remRuby;
	@FXML
	Button remGold;
	@FXML
	Button remRum;
	@FXML
	Button confirmBtn;
	// Treasure Island count displays
	@FXML
	Text txtTDiamond;
	@FXML
	Text txtTRuby;
	@FXML
	Text txtTGold;
	@FXML
	Text txtTPearl;
	@FXML
	Text txtTRum;
	
	// Ship count displays 
	@FXML
	Text txtSDiamond;
	@FXML
	Text txtSRuby;
	@FXML
	Text txtSGold;
	@FXML
	Text txtSRum;
	@FXML
	Text txtSPearl;
	
	// Misc text Value
	@FXML
	Text txtVal;
	
	
	/**
	 * Default constructor - nothing here
	 */
	public W_TakeTreasure(){
		
	}
	
	/**
	 * Setting up the controller
	 * @param self	 stage the window is going to be shown at
	 * @param caller	caller stage of the window
	 * @param engine	GameEngine object
	 * @param player	Player that takes the treasure
	 * @param valueLimit	Limit o the treasure taken
	 */
	public void setUpController(Stage self, Stage caller, GameEngine engine, Player player, int valueLimit){
		this.self = self;
		this.engine = engine;
		this.player = player;
		this.valueLimit = valueLimit;
		shipCount = player.getShip().getTreasureNum();
		onIsland = engine.getBoard().getTreasureIsland().getTreasureList();
		addButtons.add(addDiamond);
		addButtons.add(addPearl);
		addButtons.add(addRuby);
		addButtons.add(addGold);
		addButtons.add(addRum);
		remButtons.add(remDiamond);
		remButtons.add(remPearl);
		remButtons.add(remRuby);
		remButtons.add(remGold);
		remButtons.add(remRum);
		addDiamond.setOnAction(event -> addTreasure(TreasureType.DIAMOND));
		addRuby.setOnAction(event -> addTreasure(TreasureType.RUBY));
		addGold.setOnAction(event -> addTreasure(TreasureType.GOLD));
		addPearl.setOnAction(event -> addTreasure(TreasureType.PEARL));
		addRum.setOnAction(event -> addTreasure(TreasureType.RUM));
		
		remDiamond.setOnAction(event -> removeTreasure(TreasureType.DIAMOND));
		remRuby.setOnAction(event -> removeTreasure(TreasureType.RUBY));
		remGold.setOnAction(event -> removeTreasure(TreasureType.GOLD));
		remPearl.setOnAction(event -> removeTreasure(TreasureType.PEARL));
		remRum.setOnAction(event -> removeTreasure(TreasureType.RUM));
		
		confirmBtn.setOnAction(event -> confirm());
		
		updateView();
	}
	
	/**
	 * Adds a treasure of given type for the taking
	 * @param type	Type of the treasure
	 * @return 
	 */
	private void addTreasure(TreasureType type){
		ArrayList<Treasure> treasures = onIsland;
		for (Treasure t : treasures){
			if (t.getType().equals(type)){
				toTake.add(t);
				onIsland.remove(t);
				shipCount++;
				currValue += t.getValue();
				updateView();
				return;
			}
		}
	}

	/**
	 * Removes a treasure from the list for the taking
	 * @param type	Type of the treasure
	 */
	private void removeTreasure(TreasureType type){
		ArrayList<Treasure> treasures = toTake;
		for (Treasure t : treasures){
			if (t.getType().equals(type)){
				toTake.remove(t);
				onIsland.add(t);
				shipCount--;
				currValue -= t.getValue();
				updateView();
				return;
			}
		}
	}
	
	/**
	 * Confirm button action
	 */
	private void confirm(){
		if (currValue > valueLimit){
			//insert user-friendly message
			System.out.println("UI Error: Treasures over the limit(" + currValue + ")");
			return;
		}
		if (toTake.size() > 2){
			//insert user-friendly message
			System.out.println("UI Error: Ship container over the limit");
			return;
		}
		for (Treasure t : toTake){
			try {
				player.getShip().addTreasure(t);
			} catch (TooMuchTreasureException e) {
				e.printStackTrace();
			}
			engine.getBoard().getTreasureIsland().removeTreasure(t);
		}
		self.close();
	}
	
	/**
	 * Completely updates the window
	 */
	private void updateView(){
		ArrayList<Treasure> ttreasure = onIsland;
		ArrayList<Treasure> streasure = toTake;
		int diamond = 0;
		int ruby = 0;
		int gold = 0;
		int pearl = 0;
		int rum = 0;
		int sdiamond = 0;
		int sruby = 0;
		int sgold = 0;
		int spearl = 0;
		int srum = 0;
		// update values for both Treas. Island and the ship
		for (Treasure t : streasure){
			switch (t.getType()){
			case DIAMOND:
				sdiamond++;
				break;
			case RUBY:
				sruby++;
				break;
			case GOLD:
				sgold++;
				break;
			case PEARL:
				spearl++;
				break;
			case RUM:
				srum++;
				break;
			}
		}
		txtSDiamond.setText(Integer.toString(sdiamond));
		txtSRuby.setText(Integer.toString(sruby));
		txtSPearl.setText(Integer.toString(spearl));
		txtSGold.setText(Integer.toString(sgold));
		txtSRum.setText(Integer.toString(srum));
		// TreasureIsland vals 
		for (Treasure t : ttreasure){
			switch (t.getType()){
			case DIAMOND:
				diamond++;
				break;
			case RUBY:
				ruby++;
				break;
			case GOLD:
				gold++;
				break;
			case PEARL:
				pearl++;
				break;
			case RUM:
				rum++;
				break;
			}
		}
		txtTDiamond.setText(Integer.toString(diamond));
		txtTRuby.setText(Integer.toString(ruby));
		txtTPearl.setText(Integer.toString(pearl));
		txtTGold.setText(Integer.toString(gold));
		txtTRum.setText(Integer.toString(rum));
	
		// update add buttons
		// diamond 
		if (diamond <= 0 || shipCount >= 2 || currValue > (valueLimit - 5)){
			addDiamond.setDisable(true);
		} else {
			addDiamond.setDisable(false);
		}
		if (ruby <= 0 || shipCount >= 2 || currValue > (valueLimit - 5)){
			addRuby.setDisable(true);
		} else {
			addRuby.setDisable(false);
		}
		if (gold <= 0 || shipCount >= 2 || currValue > (valueLimit - 4)){
			addGold.setDisable(true);
		} else {
			addGold.setDisable(false);
		}
		if (pearl <= 0 || shipCount >= 2 || currValue > (valueLimit - 3)){
			addPearl.setDisable(true);
		} else {
			addPearl.setDisable(false);
		}
		if (rum <= 0 || shipCount >= 2 || currValue > (valueLimit - 2)){
			addRum.setDisable(true);
		} else {
			addRum.setDisable(false);
		}
		
		// Update remove buttons according to treasure to take 
		for (Button b : remButtons){
			b.setDisable(true); //clear them first
		}
		for (Treasure t : toTake){ //enable the ones that match
			switch (t.getType()){
			case DIAMOND:
				remDiamond.setDisable(false);
				break;
			case RUBY:
				remRuby.setDisable(false);
				break;
			case GOLD:
				remGold.setDisable(false);
				break;
			case PEARL:
				remPearl.setDisable(false);
				break;
			case RUM:
				remRum.setDisable(false);
				break;
			}
		}
		txtVal.setText(Integer.toString(currValue) + " / " + Integer.toString(valueLimit));
	}
}
