/*
 * @(#) TestMyCards.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */

package ui.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ui.game.W_FightConfirm;
import ui.game.W_MyCards;
import uk.ac.aber.cs211.group07.system.Board;
import uk.ac.aber.cs211.group07.system.GameEngine;
import uk.ac.aber.cs211.group07.system.resources.ChanceCard;
import uk.ac.aber.cs211.group07.system.resources.Player;
import uk.ac.aber.cs211.group07.system.resources.Position;

/**
 * Tests addressing W_FightConfirm and FightConfirm.fxml
 * @author Jake McDonagh
 * @author Harry Adams
 * @version 0.1 - 01/05/17 - initial creation of test /jam93
 * @version 0.2 - 02/05/17 - Created the current test /jam93
 * @version 0.21 - 02/05/17 - Added comments relater to current errors /jam93
 * @version 0.22 - 02/05/17 - Refactored to account for lack of UI Application class /rob27
 * @version 0.3 - 02/05/17 - Completed tests but not completely functional /jam93
 * @version 0.4 - 02/05/17 - Test working as it should be. /haa14
 * @version 0.5 - 03/05/17 - MyCards screen now accounts for treasures in ship as well as the home port /haa14
 * @version 0.6 - 03/05/17 - Screen size edited to suit better, cards show properly and have text tooltips /haa14
 */

public class TestMyCards extends javafx.application.Application {
	
	@Test
	public void test() {
		this.launch(null);
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
		try {
			
			FXMLLoader loader = new FXMLLoader(TestTakeTreasure.class.getClassLoader().getResource("ui/W_MyCards.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			Board b = engine.getBoard();
			
			for (int i = 0; i < 3; i++) {
				engine.getCurrentPlayer().addCard(b.getPirateIsland().draw());
			}			

			ChanceCard testCard = new ChanceCard(99, "This is just a test card! If you see this appear in the game, please email the developers.");
			
			engine.getCurrentPlayer().addCard(b.getTreasureIsland().draw());
			engine.getCurrentPlayer().addCard(testCard);
			
			/* Set up the controller */        	
        	Scene scene = new Scene(page, 640, 610);
            stage.setScene(scene);
            stage.setTitle("Select Treasure");
            stage.setResizable(false);
        	((W_MyCards) (loader.getController())).setUpController(stage, engine, null); 
        	

            /* Show the window */
            stage.show();
			
			
		} catch (Exception ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		
	}
}
