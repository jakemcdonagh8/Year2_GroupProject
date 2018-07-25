package ui.tests;

import static org.junit.Assert.*;

import java.util.Random;
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
import ui.game.W_Scoreboard;
import ui.game.W_TakeTreasure;
import uk.ac.aber.cs211.group07.system.GameEngine;
import uk.ac.aber.cs211.group07.system.resources.Player;
import uk.ac.aber.cs211.group07.system.resources.Treasure;
import uk.ac.aber.cs211.group07.system.resources.TreasureType;

/**
 * Tests addressing and MyCards.fxml
 * @author Harry Adams
 * @version 0.1 - 01/05/17 - initial creation /haa14
 * @version 0.2 - 04/05/17 - changed to accommodate 0.31 change in W_Scoreboad /kac12
 */
public class TestScoreboard extends javafx.application.Application {

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
			
        	FXMLLoader loader = new FXMLLoader(TestScoreboard.class.getClassLoader().getResource("ui/ScoreBoard.fxml"));
        	AnchorPane page = (AnchorPane) loader.load();
        	
        	Random rand = new Random();
        	
        	Player playerOne = engine.getPlayer(0);
        	Player playerTwo = engine.getPlayer(1);
        	Player playerThree = engine.getPlayer(2);
        	Player playerFour = engine.getPlayer(3);
        	
        	for (int i = 0; i < 30; i++){
        		int player = rand.nextInt(3);
        		
        		switch(player){
        		case 0: addTreasure(rand.nextInt(5), playerOne); break;
        		case 1: addTreasure(rand.nextInt(5), playerTwo); break;
        		case 2: addTreasure(rand.nextInt(5), playerThree); break;
        		case 3: addTreasure(rand.nextInt(5), playerFour); break;
        		}
        		
        	}
        	
        	/* Set up the controller + limit - feel free to modify */

            
        	
        	Scene scene = new Scene(page, 1024, 660);
            stage.setScene(scene);
            stage.setTitle("Select Treasure");
            stage.setResizable(false);
            /* Show the window */
            
        	((W_Scoreboard) (loader.getController())).setUpController(stage, stage, engine);
            stage.show();
        	
        	
        } catch (Exception ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }	
		
	}	
	
	private void addTreasure(int type, Player player) {
		switch(type){
		case 0: Treasure t1 = new Treasure(TreasureType.DIAMOND, 5, player.getHomePort()); player.getHomePort().addTreasure(t1);break;
		case 1: Treasure t2 = new Treasure(TreasureType.RUBY, 5, player.getHomePort()); player.getHomePort().addTreasure(t2);break;
		case 2: Treasure t3 = new Treasure(TreasureType.GOLD, 4, player.getHomePort()); player.getHomePort().addTreasure(t3); break;
		case 3: Treasure t4 = new Treasure(TreasureType.PEARL, 3, player.getHomePort()); player.getHomePort().addTreasure(t4);break;
		case 4: Treasure t5 = new Treasure(TreasureType.RUM, 2, player.getHomePort()); player.getHomePort().addTreasure(t5);break;
		}
	}

}
