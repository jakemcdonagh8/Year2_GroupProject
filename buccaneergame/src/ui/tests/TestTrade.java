package ui.tests;

import static org.junit.Assert.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ui.game.W_Credits;
import ui.game.W_MainMenu;
import ui.game.W_MyCards;
import ui.game.W_TakeTreasure;
import ui.game.W_Trade;
import uk.ac.aber.cs211.group07.system.Board;
import uk.ac.aber.cs211.group07.system.GameEngine;
import uk.ac.aber.cs211.group07.system.resources.ChanceCard;
import uk.ac.aber.cs211.group07.system.resources.Port;
import uk.ac.aber.cs211.group07.system.resources.Treasure;
import uk.ac.aber.cs211.group07.system.resources.TreasureHolder;

public class TestTrade extends javafx.application.Application{
	@Test
	public void test() {
		this.launch();
	}


	/**
	 * D
	 * @param stage
	 * @throws Exception
	 */
	@Override
	public void start(Stage stage) throws Exception {
		GameEngine engine;
		String names[] = new String[4];
		names[0] = "name1";
		names[1] = "name2";
		names[2] = "name3";
		names[3] = "name4";
		engine = new GameEngine();
		engine.initGame(names);
		stage = new Stage();
		Treasure diamond = new Treasure(uk.ac.aber.cs211.group07.system.resources.TreasureType.DIAMOND, 5, engine.getCurrentPlayer().getShip());
		Treasure gold = new Treasure(uk.ac.aber.cs211.group07.system.resources.TreasureType.GOLD, 4, engine.getCurrentPlayer().getShip());

		try {
			FXMLLoader loader = new FXMLLoader(TestTrade.class.getClassLoader().getResource("ui/W_Trade.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			Board b = engine.getBoard();
			engine.getPlayer(0).getShip().setPos(1, 14);
			Port port = (Port) engine.getPlayerDestinationObject(engine.getCurrentPlayer());
			Treasure diamond1 = new Treasure(uk.ac.aber.cs211.group07.system.resources.TreasureType.DIAMOND, 5, port);
			Treasure gold1 = new Treasure(uk.ac.aber.cs211.group07.system.resources.TreasureType.GOLD, 4, port);
			Treasure ruby = new Treasure(uk.ac.aber.cs211.group07.system.resources.TreasureType.RUBY, 5, port);
			Treasure pearl = new Treasure(uk.ac.aber.cs211.group07.system.resources.TreasureType.PEARL, 3, port);
			Treasure rum = new Treasure(uk.ac.aber.cs211.group07.system.resources.TreasureType.RUM, 2, port);


        	Stage newStage = new Stage();
			/* Set up the controller */        	
        	Scene scene = new Scene(page);
            newStage.setScene(scene);
            newStage.setTitle("Trade");
            newStage.setResizable(false);
        	((W_Trade) (loader.getController())).setUpController(newStage, stage, null, engine, engine.getPlayer(0)); 
            /* Show the window */
            newStage.show();
		} catch (Exception ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		
	}
}
