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
import ui.menus.Popup_Simple;
import uk.ac.aber.cs211.group07.system.Board;
import uk.ac.aber.cs211.group07.system.GameEngine;
import uk.ac.aber.cs211.group07.system.State;
import uk.ac.aber.cs211.group07.system.resources.ChanceCard;
import uk.ac.aber.cs211.group07.system.resources.Player;
import uk.ac.aber.cs211.group07.system.resources.Position;

public class TestNextTurn extends javafx.application.Application{

	GameEngine engine;

	@Test
	public void test(){
		this.launch(null);
	}

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
		
		if(engine.getState("action") == State.PREPARE){
			Player currentPlayer = engine.getCurrentPlayer();
			Popup_Simple confirmWindow = new Popup_Simple(null, stage, currentPlayer.getName() + "'s turn", null, "IT'S " + currentPlayer.getName() + "'S TURN!");
			confirmWindow.getStage().showAndWait();
			engine.setState("action", State.MOVE);
		}

	}
}
