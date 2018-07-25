package ui.game;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ui.menus.Popup_Simple;
import uk.ac.aber.cs211.group07.system.GameEngine;
import uk.ac.aber.cs211.group07.system.exceptions.TooMuchTreasureException;
import uk.ac.aber.cs211.group07.system.exceptions.UnevenTradeException;
import uk.ac.aber.cs211.group07.system.resources.Card;
import uk.ac.aber.cs211.group07.system.resources.ChanceCard;
import uk.ac.aber.cs211.group07.system.resources.CrewCard;
import uk.ac.aber.cs211.group07.system.resources.Player;
import uk.ac.aber.cs211.group07.system.resources.Port;
import uk.ac.aber.cs211.group07.system.resources.Ship;
import uk.ac.aber.cs211.group07.system.resources.Treasure;
import uk.ac.aber.cs211.group07.system.resources.TreasureType;

public class W_Trade_old {
	private ArrayList<Treasure> onShip = new ArrayList<Treasure>();
	private ArrayList<Treasure> shipTrade = new ArrayList<Treasure>();
	private ArrayList<Treasure> inPort = new ArrayList<Treasure>();
	private ArrayList<Treasure> portTrade = new ArrayList<Treasure>();
	ArrayList<Card> cards = new ArrayList<Card>();//ports initial cards,as crew and chance aren't seperated

	private ArrayList<CrewCard> sCrCards = new ArrayList<CrewCard>(); //crew cards on ship
	private ArrayList<CrewCard> sCrCardsT = new ArrayList<CrewCard>(); //crew cards to trade
	private ArrayList<ChanceCard> sChCards = new ArrayList<ChanceCard>(); //chance cards on ship
	private ArrayList<ChanceCard> sChCardsT = new ArrayList<ChanceCard>(); //chance cards to trade
	
	private ArrayList<CrewCard> pCrCards = new ArrayList<CrewCard>(); //crew cards in port
	private ArrayList<CrewCard> pCrCardsT = new ArrayList<CrewCard>(); //crew cards to trade
	private ArrayList<ChanceCard> pChCards = new ArrayList<ChanceCard>(); //chance cards in port
	private ArrayList<ChanceCard> pChCardsT = new ArrayList<ChanceCard>(); //chance cards to trade
	private GameEngine engine;
	private W_Game gameController;
	private Ship ship;
	private Port port;
	private int shipCount;
	private int shipLimit=2;
	private Stage self;
	private Stage caller;
	private Player player;
	private int cardInt;
	//treasure on ship
	int diamondCountS = 0;
	int goldCountS = 0;
	int rubyCountS = 0;
	int pearlCountS = 0;
	int rumCountS = 0;
	//number of treasure for ship trade
	int diamondCountST = 0;
	int goldCountST = 0;
	int rubyCountST = 0;
	int pearlCountST = 0;
	int rumCountST = 0;
	//number of treasure for port
	int diamondCountP = 0;
	int goldCountP = 0;
	int rubyCountP = 0;
	int pearlCountP = 0;
	int rumCountP = 0;
	//number of treasure for ship trade
	int diamondCountPT = 0;
	int goldCountPT = 0;
	int rubyCountPT = 0;
	int pearlCountPT = 0;
	int rumCountPT = 0;
	
	//100 FXML declarations next
	@FXML
	private Label sDId;
	@FXML
	private Label sRId;
	@FXML
	private Label sPId;
	@FXML
	private Label sGId;
	@FXML
	private Label sRmId;
    @FXML
    private HBox shipCrewCards;
    @FXML
    private HBox shipChanceCards;
    @FXML
    private HBox shipCrewCardsTrading;
    @FXML
    private HBox shipChanceCardsTrading;
    @FXML
    private Label portName;
    @FXML
    private Label pDId;
    @FXML
    private Label pRId;
    @FXML
    private Label pPId;
    @FXML
    private Label pGId;
    @FXML
    private Label pRmId;
    @FXML
    private HBox portCrewCards;
    @FXML
    private HBox portChanceCards;
    @FXML
    private HBox portCrewCardsTrading;
    @FXML
    private HBox portChanceCardsTrading;
    @FXML
    private Label sDId1;
    @FXML
    private Label sGId1;
    @FXML
    private Label sRmId1;
    @FXML
    private Label sPId1;
    @FXML
    private Label sRId1;
    @FXML
    private Label pDId1;
    @FXML
    private Label pGId1;
    @FXML
    private Label pRmId1;
    @FXML
    private Label pPId1;
    @FXML
    private Label pRId1;
    @FXML
    private ImageView shipD;
    @FXML
    private ImageView shipG;
    @FXML
    private ImageView shipRM;
    @FXML
    private ImageView shipP;
    @FXML    
    private ImageView shipR;
    @FXML
    private ImageView portD;
    @FXML
    private ImageView portG;
    @FXML
    private ImageView portRM;
    @FXML
    private ImageView portP;
    @FXML
    private ImageView portR;
    @FXML
    private ImageView shipTD;
    @FXML
    private ImageView shipTG;
    @FXML
    private ImageView shipTRM;
    @FXML
    private ImageView shipTP;
    @FXML
    private ImageView shipTR;
    @FXML
    private ImageView portTD;
    @FXML
    private ImageView portTG;
    @FXML
    private ImageView portTRM;
    @FXML
    private ImageView portTP;
    @FXML
    private ImageView portTR;
    @FXML
    private Button confirmBtn;
    
    //fxml instance declarations over, next for fxml methods
    @FXML
    void confirmTrade(ActionEvent event) throws UnevenTradeException, TooMuchTreasureException {
		ArrayList<Card> portCards = new ArrayList<Card>();
		ArrayList<Card> shipCards = new ArrayList<Card>();
		portCards.addAll(0, pChCardsT);
		portCards.addAll(0, pCrCardsT);
		shipCards.addAll(0, sChCardsT);
		shipCards.addAll(0, sCrCardsT);
		
		if(uk.ac.aber.cs211.group07.system.helpers.PortInteraction.isValidTrade(portCards, portTrade, shipCards, shipTrade)==true){
			uk.ac.aber.cs211.group07.system.helpers.PortInteraction.performTrade(player, port, shipCards, shipTrade, portCards, portTrade);
			self.close();
			caller.show();
		}
		else{
			Stage newStage = new Stage();
			Popup_Simple popup = new Popup_Simple(newStage, this.self, "Uneven Trade", null, "This trade is uneven, please remedy this before trying again.");
			
		}
    }
	
	@FXML
    void clickPD(MouseEvent event) {
    	if(diamondCountP>=1){ // check if there are any diamonds
			updateTradeBuffer(uk.ac.aber.cs211.group07.system.resources.TreasureType.DIAMOND, inPort, portTrade);
    	}
    }

    @FXML
    void clickPG(MouseEvent event) {
    	if(goldCountP>=1){ // check if there are any diamonds
			updateTradeBuffer(uk.ac.aber.cs211.group07.system.resources.TreasureType.GOLD, inPort, portTrade);

    	}
    }

    @FXML
    void clickPP(MouseEvent event) {
		if(pearlCountP>=1){ // check if there are any pearls
			updateTradeBuffer(uk.ac.aber.cs211.group07.system.resources.TreasureType.PEARL, inPort, portTrade);
		}
    }

    @FXML
    void clickPR(MouseEvent event) {
		if(rubyCountP>=1){ // check if there are any rubies
			updateTradeBuffer(uk.ac.aber.cs211.group07.system.resources.TreasureType.RUBY, inPort, portTrade);
		}
    }

    @FXML
    void clickPRM(MouseEvent event) {
		if(rumCountP>=1){ // check if there are any diamonds
			updateTradeBuffer(uk.ac.aber.cs211.group07.system.resources.TreasureType.RUM, inPort, portTrade);
		}
    }

    @FXML
    void clickPTD(MouseEvent event) {
		if(diamondCountPT>=1){ // check if there are any diamonds
			updateTradeBuffer(uk.ac.aber.cs211.group07.system.resources.TreasureType.DIAMOND, portTrade, inPort);
		}
    }

    @FXML
    void clickPTG(MouseEvent event) {
		if(goldCountPT>=1){ // check if there are any diamonds
    		updateTradeBuffer(uk.ac.aber.cs211.group07.system.resources.TreasureType.GOLD, portTrade, inPort);
		}
    }

    @FXML
    void clickPTP(MouseEvent event) {
		if(pearlCountPT>=1){ // check if there are any diamonds
    		updateTradeBuffer(uk.ac.aber.cs211.group07.system.resources.TreasureType.PEARL, portTrade, inPort);
		}
    }

    @FXML
    void clickPTRM(MouseEvent event) {
		if(rumCountPT>=1){ // check if there are any diamonds
    		updateTradeBuffer(uk.ac.aber.cs211.group07.system.resources.TreasureType.RUM, portTrade, inPort);
		}
    }

    @FXML
    void clickSD(MouseEvent event) {//wtf?!?
    	if(diamondCountS>=1){ // check if there are any diamonds
    		updateTradeBuffer(uk.ac.aber.cs211.group07.system.resources.TreasureType.DIAMOND, onShip, shipTrade);
    	}
    }
    
    @FXML
    void clickSG(MouseEvent event) {//wtf?!?
		if(goldCountS>=1){ // check if there is gold
    		updateTradeBuffer(uk.ac.aber.cs211.group07.system.resources.TreasureType.GOLD, onShip, shipTrade);
		}
    }

    @FXML
    void clickSP(MouseEvent event) {
		if(pearlCountS>=1){ // check if there are any diamonds
    		updateTradeBuffer(uk.ac.aber.cs211.group07.system.resources.TreasureType.PEARL, onShip, shipTrade);
		}
    }

    @FXML
    void clickSR(MouseEvent event) {
		if(rubyCountS>=1){ // check if there are any diamonds
    		updateTradeBuffer(uk.ac.aber.cs211.group07.system.resources.TreasureType.RUBY, onShip, shipTrade);
		}
    }

    @FXML
    void clickSRM(MouseEvent event) {
		if(rumCountS>=1){ // check if there are any diamonds
    		updateTradeBuffer(uk.ac.aber.cs211.group07.system.resources.TreasureType.RUM, onShip, shipTrade);
		}
    }

    @FXML
    void clickSTD(MouseEvent event) {
		if(diamondCountST>=1){ // check if there are any diamonds
    		updateTradeBuffer(uk.ac.aber.cs211.group07.system.resources.TreasureType.DIAMOND, shipTrade,onShip);
		}
    }

    @FXML
    void clickSTG(MouseEvent event) {
		if(goldCountST>=1){ // check if there are any diamonds
    		updateTradeBuffer(uk.ac.aber.cs211.group07.system.resources.TreasureType.GOLD, shipTrade,onShip);
		}
    }

    @FXML
    void clickSTP(MouseEvent event) {
		if(pearlCountST>=1){ // check if there are any diamonds
    		updateTradeBuffer(uk.ac.aber.cs211.group07.system.resources.TreasureType.PEARL, shipTrade,onShip);
		}
    }

    @FXML
    void clickSTR(MouseEvent event) {
		if(rubyCountST>=1){ // check if there are any diamonds
    		updateTradeBuffer(uk.ac.aber.cs211.group07.system.resources.TreasureType.RUBY, shipTrade,onShip);
		}
    }
    
    @FXML
    void clickSTRM(MouseEvent event) {
		if(rumCountST>=1){ // check if there are any diamonds
    		updateTradeBuffer(uk.ac.aber.cs211.group07.system.resources.TreasureType.RUM, shipTrade,onShip);
		}
    }
//fxml method declarations done 

	public void setUpController(Stage self, Stage caller,W_Game gameController, GameEngine engine, Player player){
		this.self = self;
		this.gameController=gameController;
		this.caller = caller;
		this.engine = engine;
		this.player = player;
		ship = player.getShip();
		shipCount = player.getShip().getTreasureNum();
		port = (Port) engine.getPlayerDestinationObject(player);
		inPort = port.getTreasureList();
		onShip = ship.getTreasureList();
		cards = port.getCards();
		sCrCards = player.getHand();
		sChCards = player.getChanceCards();
		String portNameStr = port.getName();
		portName.setText("Trading with " + portNameStr);
		displayShipArray();
		displayPortArray();
		updateCards();
	}
	public void displayShipArray(){
		diamondCountS = 0;
		goldCountS = 0;
		rubyCountS = 0;
		pearlCountS = 0;
		rumCountS = 0;
		//number of treasure for ship trade
		diamondCountST = 0;
		goldCountST = 0;
		rubyCountST = 0;
		pearlCountST = 0;
		rumCountST = 0;
		for(Treasure t: onShip){
			TreasureType type = t.getType();
			switch(type){
			case DIAMOND:	diamondCountS++;
							break;
			case GOLD:		goldCountS++;
							break;
			case RUBY:		rubyCountS++;
							break;
			case PEARL:		pearlCountS++;
							break;
			case RUM:		rumCountS++;
							break;
			}
		}
		for(Treasure t: shipTrade){
			TreasureType type = t.getType();
			switch(type){
			case DIAMOND:	diamondCountST++;
							break;
			case GOLD:		goldCountST++;
							break;
			case RUBY:		rubyCountST++;
							break;
			case PEARL:		pearlCountST++;
							break;
			case RUM:		rumCountST++;
							break;
			}
		}
		sDId.setText(Integer.toString(diamondCountS));
		sGId.setText(Integer.toString(goldCountS));
		sRId.setText(Integer.toString(rubyCountS));
		sPId.setText(Integer.toString(pearlCountS));
		sRmId.setText(Integer.toString(rumCountS));
		sDId1.setText(Integer.toString(diamondCountST));
		sGId1.setText(Integer.toString(goldCountST));
		sRId1.setText(Integer.toString(rubyCountST));
		sPId1.setText(Integer.toString(pearlCountST));
		sRmId1.setText(Integer.toString(rumCountST));
	}
	public void displayPortArray(){
		//number of treasure for port
		 diamondCountP = 0;
		 goldCountP = 0;
		 rubyCountP = 0;
		 pearlCountP = 0;
		 rumCountP = 0;
		//number of treasure for ship trade
		 diamondCountPT = 0;
		 goldCountPT = 0;
		 rubyCountPT = 0;
		 pearlCountPT = 0;
		 rumCountPT = 0;
		for(Treasure t: inPort){
			TreasureType type = t.getType();
			switch(type){
			case DIAMOND:	diamondCountP++;
							break;
			case GOLD:		goldCountP++;
							break;
			case RUBY:		rubyCountP++;
							break;
			case PEARL:		pearlCountP++;
							break;
			case RUM:		rumCountP++;
							break;
			}
		}
		for(Treasure t: portTrade){
			TreasureType type = t.getType();
			switch(type){
			case DIAMOND:	diamondCountPT++;
							break;
			case GOLD:		goldCountPT++;
							break;
			case RUBY:		rubyCountPT++;
							break;
			case PEARL:		pearlCountPT++;
							break;
			case RUM:		rumCountPT++;
							break;
			}
		}
		pDId.setText(Integer.toString(diamondCountP));
		pGId.setText(Integer.toString(goldCountP));
		pRId.setText(Integer.toString(rubyCountP));
		pPId.setText(Integer.toString(pearlCountP));
		pRmId.setText(Integer.toString(rumCountP));
		pDId1.setText(Integer.toString(diamondCountPT));
		pGId1.setText(Integer.toString(goldCountPT));
		pRId1.setText(Integer.toString(rubyCountPT));
		pPId1.setText(Integer.toString(pearlCountPT));
		pRmId1.setText(Integer.toString(rumCountPT));
	}
	/*
	 * Display ship cards is currently very messy, i feel the code could be reduced, and you just call a main function
	 * need to update images for ship,shipTrade,port and portTrade, for both chance and crew cards (as they have separate hboxs
	 */
	public void crCardMove(ArrayList<CrewCard> cardSource,ArrayList<CrewCard> cardDest, Card card, String owner){
		cardSource.remove(card);
		cardDest.add((CrewCard) card);
	}
	public void chCardMove(ArrayList<ChanceCard> cardSource,ArrayList<ChanceCard> cardDest, Card card, String owner){
		cardSource.remove(card);
		cardDest.add((ChanceCard) card);
	}
	
	public void updateCards(){
		//update all cards
		displayCards("sCrCards");
		displayCards("sCrCardsT");
		displayCards("sChCards");
		displayCards("sChCardsT");
		displayCards("pCrCards");
		displayCards("pCrCardsT");
		displayCards("pChCards");
		displayCards("pChCardsT");
		
	}
	public void displayCards(String owner){//name sets owner
		switch(owner){
		case "sCrCards": 	shipCrewCards.getChildren().clear(); // empty Hbox before adding new cards
							for(Card i: sCrCards){
								addCardImage(i, "sCrCards");
							}
							break;
		case "sCrCardsT":	shipCrewCardsTrading.getChildren().clear(); // empty Hbox before adding new cards
							for(Card i: sCrCardsT){
								addCardImage(i, "sCrCardsT");
							}
							break;
		case "sChCards":	shipChanceCards.getChildren().clear(); // empty Hbox before adding new cards
							for(Card i: sChCards){
								addCardImage(i, "sChCards");
							}
							break;
		case "sChCardsT":	shipChanceCardsTrading.getChildren().clear(); // empty Hbox before adding new cards
							for(Card i: sChCardsT){
								addCardImage(i, "sChCardsT");
							}
							break;
		case "pCrCards":	portCrewCards.getChildren().clear(); // empty Hbox before adding new cards
							for(Card i: pCrCards){
								addCardImage(i, "pCrCards");
							}
							break;
		case "pCrCardsT":	portCrewCardsTrading.getChildren().clear(); // empty Hbox before adding new cards
							for(Card i: pCrCardsT){
								addCardImage(i, "pCrCardsT");
							}
							break;
		case "pChCards":	portChanceCards.getChildren().clear(); // empty Hbox before adding new cards
							for(Card i: pChCards){
								addCardImage(i, "pChCards");
							}
							break;
		case "pChCardsT":	portChanceCardsTrading.getChildren().clear(); // empty Hbox before adding new cards
							for(Card i: pChCardsT){
								addCardImage(i, "pChCardsT");
							}
							break;
		}
	}
	public void addCardImage(Card card, String owner){
		if(card instanceof CrewCard){
				CrewCard crCard = (CrewCard) card;
				int value = crCard.getValue();
				boolean isRed = crCard.isRed();
				if (isRed) {
					switch (value) {
					case 1:
						addCardToPane("ui/img/CrewCard_Red1.png", card, owner);
						break;
					case 2:
						addCardToPane("ui/img/CrewCard_Red2.png", card, owner);
						break;
					case 3:
						addCardToPane("ui/img/CrewCard_Red3.png", card, owner);
						break;
					}
				} else {
					switch (value) {
					case 1:
						addCardToPane("ui/img/CrewCard_Black1.png", card, owner);
						break;
					case 2:
						addCardToPane("ui/img/CrewCard_Black2.png", card, owner);
						break;
					case 3:
						addCardToPane("ui/img/CrewCard_Black3.png", card, owner);
						break;
					}
				}
			} else if(card instanceof ChanceCard) {
				for (int i = 0; i < sChCards.size(); i++) {
					ChanceCard currentCard = (ChanceCard) sChCards.get(i);
					int value = currentCard.getNumber();
					switch (value) {
					case 21:
						addCardToPane("ui/img/ChanceCard_LongJohnSilver.png", currentCard, owner);
						// addTooltip(true);
						//clear all treasure to be traded
						break; // Long John Silver
					case 23:
						addCardToPane("ui/img/ChanceCard_Doubloons.png", currentCard, owner);
						break; // Doubloons
					case 24:
						addCardToPane("ui/img/ChanceCard_PiecesOfEight.png", currentCard, owner);
						break; // Pieces of Eight
					case 25:
					case 26:
						addCardToPane("ui/img/ChanceCard_KiddsChart.png", currentCard, owner);
						break; // Kidd's chart
					case 99:
						addCardToPane("ui/img/ChanceCard_BlankCard.png", currentCard, owner);
						break;
					}
				}
			}		
	}
	
	private void addCardToPane(String url, Card currentCard, String owner) {
		Image cardImg = new Image(url, 100, 100, false, false);
		ImageView image = new ImageView();
		image.setImage(cardImg);
		switch(owner) {
		case "shipCrewCards":			shipCrewCards.getChildren().add(image);
										image.setOnMouseClicked(e -> crCardMove(sCrCards, sCrCardsT, currentCard,"shipCrewCards"));//the beginning of the rabbit hole
										break;
		case "shipCrewCardsTrading":	shipCrewCardsTrading.getChildren().add(image);
										image.setOnMouseClicked(e -> crCardMove(sCrCardsT, sCrCards, currentCard,"shipCrewCardsTrading"));//the beginning of the rabbit hole
										break;
		case "portCrewCards":			portCrewCards.getChildren().add(image);
										image.setOnMouseClicked(e -> crCardMove(pCrCards, pCrCardsT, currentCard,"portCrewCards"));//the beginning of the rabbit hole
										break;
		case "portCrewCardsTrading":	portCrewCardsTrading.getChildren().add(image);
										image.setOnMouseClicked(e -> crCardMove(pCrCardsT, pCrCards, currentCard,"portCrewCardsTrading"));//the beginning of the rabbit hole
										break;
		case "shipChanceCards":			shipChanceCards.getChildren().add(image);
										image.setOnMouseClicked(e -> chCardMove(sChCards, sChCardsT, currentCard,"shipChanceCards"));//the beginning of the rabbit hole
										break;
		case "shipChanceCardsTrading":	shipChanceCardsTrading.getChildren().add(image);
										image.setOnMouseClicked(e -> chCardMove(sChCardsT, sChCards, currentCard,"shipChanceCards"));//the beginning of the rabbit hole
										break;
		case "portChanceCards":			portChanceCards.getChildren().add(image);
										image.setOnMouseClicked(e -> chCardMove(pChCards, pChCardsT, currentCard,"shipChanceCards"));//the beginning of the rabbit hole
										break;
		case "portChanceCardsTrading":	portChanceCardsTrading.getChildren().add(image);
										image.setOnMouseClicked(e -> chCardMove(pChCardsT, pChCards, currentCard,"shipChanceCards"));//the beginning of the rabbit hole
										break;
		}
	}

	/*
	 * updates temporary buffers
	 */
	private void updateTradeBuffer(TreasureType type, ArrayList<Treasure> source, ArrayList<Treasure> dest){
    	Treasure treasure=null;
    	int x=0;
    	int loc=0;
		Boolean moved = false;
		for(Treasure i:source){
			if(i.getType() == type && moved==false){
				moved=true;
				//treasure =i;
				loc=x;
			}
			x++;
		}
		treasure = source.remove(loc);
		dest.add(treasure);
		displayShipArray();
		displayPortArray();
    }
}