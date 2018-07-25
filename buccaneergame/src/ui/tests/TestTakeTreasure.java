package ui.tests;

import static org.junit.Assert.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ui.game.W_Credits;
import ui.game.W_MainMenu;
import ui.game.W_TakeTreasure;
import uk.ac.aber.cs211.group07.system.GameEngine;

/**
 * Tests addressing W_TakeTreasure and TakeTreasure.fxml
 * @author Kamil Cupial
 * @version 0.1 - 01/05/17 - initial creation /kac12
 */
public class TestTakeTreasure extends javafx.application.Application {

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
		try {
			
        	FXMLLoader loader = new FXMLLoader(TestTakeTreasure.class.getClassLoader().getResource("ui/TakeTreasure.fxml"));
        	GridPane page = (GridPane) loader.load();
        	
        	/* remove some treasures - feel free to modify */
        	for (int i = 0; i < 8; i++)
        		engine.getBoard().getTreasureIsland().removeFirstTreasure();
        	/* Set up the controller + limit - feel free to modify */
        	((W_TakeTreasure) (loader.getController())).setUpController(stage, null, engine, engine.getCurrentPlayer(), 8);
            
        	
        	Scene scene = new Scene(page, 460, 377);
            stage.setScene(scene);
            stage.setTitle("Select Treasure");
            stage.setResizable(false);
            /* Show the window */
            stage.show();
        } catch (Exception ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}

}
