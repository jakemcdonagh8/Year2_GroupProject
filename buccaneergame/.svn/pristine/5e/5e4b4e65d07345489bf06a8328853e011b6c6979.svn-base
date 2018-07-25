/*
 * @(#) W_TakeTreasure.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */
package ui.game;

import java.util.ArrayList;
import java.util.LinkedList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import uk.ac.aber.cs211.group07.system.GameEngine;
import uk.ac.aber.cs211.group07.system.exceptions.TooMuchTreasureException;
import uk.ac.aber.cs211.group07.system.exceptions.UnevenTradeException;
import uk.ac.aber.cs211.group07.system.helpers.PortInteraction;
import uk.ac.aber.cs211.group07.system.resources.Card;
import uk.ac.aber.cs211.group07.system.resources.CardHolder;
import uk.ac.aber.cs211.group07.system.resources.ChanceCard;
import uk.ac.aber.cs211.group07.system.resources.CrewCard;
import uk.ac.aber.cs211.group07.system.resources.Player;
import uk.ac.aber.cs211.group07.system.resources.PlayerPort;
import uk.ac.aber.cs211.group07.system.resources.Port;
import uk.ac.aber.cs211.group07.system.resources.Treasure;
import uk.ac.aber.cs211.group07.system.resources.TreasureType;

/**
 * Controller for the trading window
 * @author Kamil Cupial
 * @version 0.1 - 05/05/17 - Initial creation (confirm() needs implementing when additional classes are done)
 * 
 */
public class W_Trade {
	//fxml variables
	@FXML
	Label DiamondC_L;
	
	@FXML
	ImageView Diamond_L;
	
	@FXML
	Label DiamondC_R;
	
	@FXML
	ImageView Diamond_R;
	
	@FXML
	Label RubyC_L;
	
	@FXML
	ImageView Ruby_L;
	
	@FXML
	Label RubyC_R;
	
	@FXML
	ImageView Ruby_R;
	
	@FXML
	Label GoldC_L;
	
	@FXML
	ImageView Gold_L;
	
	@FXML
	Label GoldC_R;
	
	@FXML
	ImageView Gold_R;
	
	@FXML
	Label PearlC_L;
	
	@FXML
	ImageView Pearl_L;
	
	@FXML
	Label PearlC_R;
	
	@FXML
	ImageView Pearl_R;
	
	@FXML
	Label RumC_L;
	
	@FXML
	ImageView Rum_L;
	
	@FXML
	Label RumC_R;
	
	@FXML
	ImageView Rum_R;
	
	@FXML
	HBox cardsL;
	
	@FXML
	HBox cardsR;
	
	@FXML
	GridPane toTradeL;
	
	@FXML
	GridPane toTradeR;
	
	@FXML
	Button confirmBtn;
	
	@FXML
	Label valL;
	
	@FXML
	Label valR;
	
	@FXML
	Label rName;
	
	
	//instance variables
	Stage self;
	Stage caller;
	GameEngine engine;
	W_Game gameController;
	Player player;
	Port port;
	int valueL;
	int valueR;
	ArrayList<Treasure> treasureL;
	ArrayList<Treasure> treasureR;
	ArrayList<Card> cardL;
	ArrayList<Card> cardR;
	ArrayList<Object> tradeL;
	ArrayList<Object> tradeR;

	//controller setup
	public void setUpController(Stage self, Stage caller, W_Game gameController, GameEngine engine, Player player){
		this.engine = engine;
		this.gameController = gameController;
		this.player = player;
		this.self = self;
		this.caller = caller;
		treasureL = new ArrayList<Treasure>();
		treasureR = new ArrayList<Treasure>();
		cardL = new ArrayList<Card>();
		cardR = new ArrayList<Card>();
		tradeL = new ArrayList<Object>();
		tradeR = new ArrayList<Object>();
		port = getPort();
		rName.setText(port.getName());
		
		confirmBtn.setOnAction(event -> confirm());
		
		DiamondC_L.setOnMouseClicked(action -> addTreasureL(TreasureType.DIAMOND));
		Diamond_L.setOnMouseClicked(action -> addTreasureL(TreasureType.DIAMOND));
		RubyC_L.setOnMouseClicked(action -> addTreasureL(TreasureType.RUBY));
		Ruby_L.setOnMouseClicked(action -> addTreasureL(TreasureType.RUBY));
		GoldC_L.setOnMouseClicked(action -> addTreasureL(TreasureType.GOLD));
		Gold_L.setOnMouseClicked(action -> addTreasureL(TreasureType.GOLD));
		PearlC_L.setOnMouseClicked(action -> addTreasureL(TreasureType.PEARL));
		Pearl_L.setOnMouseClicked(action -> addTreasureL(TreasureType.PEARL));
		RumC_L.setOnMouseClicked(action -> addTreasureL(TreasureType.RUM));
		Rum_L.setOnMouseClicked(action -> addTreasureL(TreasureType.RUM));
		
		DiamondC_R.setOnMouseClicked(action -> addTreasureR(TreasureType.DIAMOND));
		Diamond_R.setOnMouseClicked(action -> addTreasureR(TreasureType.DIAMOND));
		RubyC_R.setOnMouseClicked(action -> addTreasureR(TreasureType.RUBY));
		Ruby_R.setOnMouseClicked(action -> addTreasureR(TreasureType.RUBY));
		GoldC_R.setOnMouseClicked(action -> addTreasureR(TreasureType.GOLD));
		Gold_R.setOnMouseClicked(action -> addTreasureR(TreasureType.GOLD));
		PearlC_R.setOnMouseClicked(action -> addTreasureR(TreasureType.PEARL));
		Pearl_R.setOnMouseClicked(action -> addTreasureR(TreasureType.PEARL));
		RumC_R.setOnMouseClicked(action -> addTreasureR(TreasureType.RUM));
		Rum_R.setOnMouseClicked(action -> addTreasureR(TreasureType.RUM));

		getListValues();
		toTradeR.setPadding(new Insets(5,5,5,5));
		
		updateView();
		
	}
	
	private void addTreasureL(TreasureType type){
		Treasure found = null;
		for (Treasure t : treasureL){
			if (t.getType() == type){
				found = t;
			}
		}
		if (found != null){
			treasureL.remove(found);
			tradeL.add(found);
		}
		updateView();
	}
	
	private void addTreasureR(TreasureType type){
		Treasure found = null;
		for (Treasure t : treasureR){
			if (t.getType() == type){
				found = t;
			}
		}
		if (found != null){
			treasureR.remove(found);
			tradeR.add(found);
		}
		updateView();
	}
	
	
	private void addCardR(Card c){
		cardR.remove(c);
		tradeR.add(c);
	}
	
	private void addCardL(Card c){
		cardL.remove(c);
		tradeL.add(c);
	}
	
	//fxml functions
	private void confirm(){
		ArrayList<Treasure> toTradeTreasureL = new ArrayList<Treasure>();
		ArrayList<Treasure> toTradeTreasureR = new ArrayList<Treasure>();
		ArrayList<Card> toTradeCardsL = new ArrayList<Card>();
		ArrayList<Card> toTradeCardsR = new ArrayList<Card>();
		ArrayList<ChanceCard> chanceCards = new ArrayList<ChanceCard>();
		for (Object obj : tradeL){
			if (obj instanceof Card){
				toTradeCardsL.add((Card) obj);
				if (obj instanceof ChanceCard){
					chanceCards.add((ChanceCard) obj);
				}
			} else {
				toTradeTreasureL.add((Treasure) obj);
			}
		}
		for (Object obj : tradeR){
			if (obj instanceof Card){
				toTradeCardsR.add((Card) obj);
			} else {
				toTradeTreasureR.add((Treasure) obj);
			}
		}
		if (PortInteraction.isValidTrade(toTradeCardsL, toTradeTreasureL, toTradeCardsR, toTradeTreasureR)){
			try {
				PortInteraction.performTrade(player, port, toTradeCardsL, toTradeTreasureL, toTradeCardsR, toTradeTreasureR);
			} catch (UnevenTradeException | TooMuchTreasureException e) {
				e.printStackTrace();
			}
		}
		//silently take all the chance cards back and return them to treasureisland
		engine.getBoard().getTreasureIsland().getCards().addAll(chanceCards);
		if (port instanceof PlayerPort){
			for (ChanceCard c : chanceCards){
				((PlayerPort) port).getOwner().removeCard(c);
			}
		} else {
			for (ChanceCard c : chanceCards){
				port.removeCard(c);
			}
		}
		self.close();
	}
	
	
	private void updateView(){
		updateLeftView();
		updateRightView();
		updateLeftToTrade();
		updateRightToTrade();
		considerLongJohn();
		updateBalances();
		if (valueL >= valueR){
			confirmBtn.setDisable(false);
		} else {
			confirmBtn.setDisable(true);
		}
	}
	
	
	private void updateBalances(){
		valueL = 0;
		valueR = 0;
		for (Object o : tradeL){
			valueL += getItemValue(o);
		}
		for (Object o : tradeR){
			valueR += getItemValue(o);
		}
		valL.setText(String.valueOf(valueL));
		valR.setText(String.valueOf(valueR));
	}
	
	private int getItemValue(Object o){
		if (o instanceof Treasure){
			return ((Treasure) o).getValue();
		} else if (o instanceof ChanceCard){
			ChanceCard c = (ChanceCard) o;
			switch (c.getNumber()){
			case 21:
				return 5;
			case 23:
				return 5;
			case 24:
				return 4;
			case 25:
			case 26:
				return 7;
			}
		} else {
			return ((CrewCard)o).getValue();
		}
		return 0;
	}
	
	private void considerLongJohn(){
		boolean johnSilver = false;
		//check for johnSilver
		for (Object o : tradeL){
			if (o instanceof ChanceCard){
				ChanceCard c = (ChanceCard) o;
				if (c.getNumber() == 21){
					johnSilver = true;
				}
			}
		}
		for (Object o : tradeR){
			if (o instanceof ChanceCard){
				ChanceCard c = (ChanceCard) o;
				if (c.getNumber() == 21){
					johnSilver = true;
				}
			}
		}
		//remove all treasure if was
		if (johnSilver){
			for (Object o: tradeL){
				if (o instanceof Treasure){
					returnObjectL(o);
				}
			}
			for (Object o : tradeR){
				if (o instanceof Treasure){
					returnObjectR(o);
				}
			}
		}
		
	}
	
	private void returnObjectL(Object obj){
		if (obj instanceof Treasure){
			treasureL.add((Treasure)obj);
		} else {
			cardL.add((Card) obj);
		}
	}
	
	private void returnObjectR(Object obj){
		if (obj instanceof Treasure){
			treasureR.add((Treasure)obj);
		} else {
			cardR.add((Card) obj);
		}
	}
	
	private void updateLeftToTrade(){
		int colI = 0;
		int rowI = 1;
		toTradeL.getChildren().clear();
		LinkedList<Object> objs = new LinkedList<Object>();
		objs.addAll(tradeL);
		LinkedList<Image> imgs = InteractionController.getImages(objs);
		for (Image img : imgs){
			Object toPass = objs.removeFirst();
			ImageView iv = new ImageView(img);
			iv.setFitWidth(32);
			iv.setFitHeight(32);
			iv.setOnMouseClicked(event -> {
				returnObjectL(toPass);
				tradeL.remove(toPass);
				updateView();
			});
			toTradeL.add(iv, rowI++, colI);
			toTradeL.setMargin(iv, new Insets(5,5,5,5));
			if (rowI >= 10){
				rowI = 0;
				colI++;
			}
		}
	}
	
	private void updateRightToTrade(){
		int colI = 0;
		int rowI = 1;
		toTradeR.getChildren().clear();
		LinkedList<Object> objs = new LinkedList<Object>();
		objs.addAll(tradeR);
		LinkedList<Image> imgs;
		if (port instanceof PlayerPort){
			imgs = gameController.getIncognitoCardImages(objs);
		}else{
			imgs = InteractionController.getImages(objs);
		}
		for (Image img : imgs){
			Object toPass = objs.removeFirst();
			ImageView iv = new ImageView(img);
			iv.setFitWidth(32);
			iv.setFitHeight(32);
			iv.setOnMouseClicked(event -> {
				returnObjectR(toPass);
				tradeR.remove(toPass);
				updateView();
			});
			toTradeR.add(iv, rowI++, colI);
			toTradeR.setMargin(iv, new Insets(5,5,5,5));
			if (rowI >= 10){
				rowI = 0;
				colI++;
			}
		}
	}
	
	private void updateLeftView(){
		//update as usual
		int diamond = 0;
		int ruby = 0;
		int gold = 0;
		int pearl = 0;
		int rum = 0;
		for (Treasure t : treasureL){
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
		DiamondC_L.setText(String.valueOf(diamond));
		RubyC_L.setText(String.valueOf(ruby));
		GoldC_L.setText(String.valueOf(gold));
		PearlC_L.setText(String.valueOf(pearl));
		RumC_L.setText(String.valueOf(rum));
		cardsL.getChildren().clear();
		LinkedList<Object> cardList = new LinkedList<Object>();
		cardList.addAll(cardL);
		LinkedList<Image> images = InteractionController.getImages(cardList);
		for (Image img : images){
			ImageView iv = new ImageView(img);
			iv.setFitWidth(100);
			iv.setFitHeight(86);
			Card c = (Card) cardList.remove();
			iv.setOnMouseClicked(event ->{
				addCardL(c);
				updateView();
			});
			cardsL.getChildren().add(iv);
		}
	}
	
	private void updateRightView(){
		int diamond = 0;
		int ruby = 0;
		int gold = 0;
		int pearl = 0;
		int rum = 0;
		for (Treasure t : treasureR){
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
		DiamondC_R.setText(String.valueOf(diamond));
		RubyC_R.setText(String.valueOf(ruby));
		GoldC_R.setText(String.valueOf(gold));
		PearlC_R.setText(String.valueOf(pearl));
		RumC_R.setText(String.valueOf(rum));
		cardsR.getChildren().clear();
		LinkedList<Object> cardList = new LinkedList<Object>();
		cardList.addAll(cardR);
		LinkedList<Image> images;
		if (port instanceof PlayerPort){
			images = gameController.getIncognitoCardImages(cardList);
		} else {
			images = InteractionController.getImages(cardList);
		}
		for (Image img : images){
			ImageView iv = new ImageView(img);
			iv.setFitWidth(100);
			iv.setFitHeight(86);
			Card c = (Card) cardList.remove();
			iv.setOnMouseClicked(event ->{
				addCardR(c);
				updateView();
			});
			cardsR.getChildren().add(iv);
		}
	}
	
	//regular functions
	private Port getPort(){
		return (Port) engine.getPlayerDestinationObject(player);
	}
	
	private void getListValues(){
		treasureL.addAll(player.getShip().getTreasureList());
		cardL.addAll(getTradableCards(player));
		treasureR.addAll(port.getTreasureList());
		if (port instanceof PlayerPort){
			cardR.addAll(getTradableCards(((PlayerPort)port).getOwner()));
		} else {
			cardR.addAll(getTradableCards(port));
		}
	}
	
	private LinkedList<Card> getTradableCards(CardHolder<Card> holder){
		LinkedList<Card> cards = new LinkedList<Card>();
		for (Card c : holder.getCards()){
			if (c instanceof CrewCard){
				cards.add(c);
			} else {
				int num = ((ChanceCard) c).getNumber();
				if (num == 21 || num == 23 || num == 24){
					cards.add(c);
				}
			}
		}
		return cards;
	}
}
