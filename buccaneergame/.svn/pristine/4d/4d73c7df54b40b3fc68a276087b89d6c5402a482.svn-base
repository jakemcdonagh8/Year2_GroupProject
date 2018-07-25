/*
 * @(#) W_Setup.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */
package ui.game;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uk.ac.aber.cs211.group07.system.GameEngine;

/**
 * Class that deals with the setup of the game
 * @author Kamil Cupail
 * @author Robert Buchan Terrey
 * 
 * @version 0.1 - 02/05/17 - Initial creation /kac12
 * @version 0.11 - 02/05/17 - Refactoring to account for lack of Application class /rob27
 * @version 0.2 - 02/05/17 - Number of methods included, and added updateView() /kac12
 * @version 0.3 - 02/05/17 - Fixed arrow issue on main game /rob27
 * @version 0.4 - 03/05/17 - Numbering on screen corrected /rob27
 * @version 0.41 - 04/05/17 - Name check modified to not allow non A-Z characters /haa14
 * @version 1.0 - 05/05/17 - Ready for initial release /haa14 
 */
public class W_Setup {
	String string;
	String[] PlayerNames = new String[4];
	String PlayerOne = "";
	String PlayerTwo = "";
	String PlayerThree = "";
	String PlayerFour = "";
	Stage self = new Stage();
	Stage caller;
	GameEngine engine;

	@FXML
	private TextField playerOne;

	@FXML
	private TextField playerTwo;

	@FXML
	private TextField playerThree;

	@FXML
	private TextField playerFour;
	
	@FXML
	private Button nextButton;

	@FXML
	void nextButton(ActionEvent event) {
		//Get names of all players
		PlayerNames[0] = playerOne.getText();
		PlayerNames[1] = playerTwo.getText();
		PlayerNames[2] = playerThree.getText();
		PlayerNames[3] = playerFour.getText();
		
		//Check if any field is blank
		if (PlayerNames[0].length()==0 || PlayerNames[1].length()==0 || PlayerNames[2].length()==0 || PlayerNames[3].length()==0) {
			String warning = "Please ensure that all name fields are filled.";
			Popup(warning); //Display a warning popup with the above string to the user
		//Check if any entry is too long
		}else if(PlayerNames[0].length()>=12 || PlayerNames[1].length()>=12 || PlayerNames[2].length()>=12 || PlayerNames[3].length()>=12){
			String warning = "Please provide a shorter name.";
			Popup(warning);
		//Check if any entry matches another
		}else if(PlayerNames[0].equals(PlayerNames[1]) || PlayerNames[0].equals(PlayerNames[2]) || PlayerNames[0].equals(PlayerNames[3]) || PlayerNames[1].equals(PlayerNames[2]) || PlayerNames[1].equals(PlayerNames[3]) || PlayerNames[2].equals(PlayerNames[3])){
			String warning = "Please ensure there are no duplicate fields.";
			Popup(warning);
		//Check for invalid characters
		}else if (containsInvalidCharacters(PlayerNames)==true){
			String warning = "Only A-Z, 0-9 or Space characters are allowed.";
			Popup(warning);	
		//Else, if no issues, set names
		}else{
			engine.initGame(PlayerNames);
			launchGame(engine);
		}
	}
	
	/**
	 * Function to check for invalid characters (non A-Z) in a string
	 * 
	 * 
	 * @param playerNames
	 * @return invalid - whether or not name has non A-Z character
	 */
	private boolean containsInvalidCharacters(String[] playerNames){
		boolean invalid = false;
		//For all players
		for(int i = 0; i < 4; i++){
			//For all characters in name
			for(int k = 0; k < playerNames[i].length(); k++){
				char current = playerNames[i].charAt(k);
				//Check if character lies outside A-Z ASCII range
				if(current < 32 || current > 122){
					invalid = true;
				} else if(current > 90 && current < 97){
					invalid = true;
				} else if(current > 32 && current < 48){
					invalid = true;
				} else if (current > 57 && current < 65){
					invalid = true;
				}
			}
		}
		return invalid;
	}
	
	//Function to create a popup for the user if name doesn't match requirements
	private void Popup(String s){
		Stage popupwindow=new Stage();			      
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("WARNING");				      
		Label label1= new Label(s);				      				     
		Button button1= new Button("Close");			     
		button1.setOnAction(e -> popupwindow.close());
		VBox layout= new VBox(10);			      
		layout.getChildren().addAll(label1, button1);				      
		layout.setAlignment(Pos.CENTER);				      
		Scene scene1= new Scene(layout, 300, 250);				      
		popupwindow.setScene(scene1);			      
		popupwindow.showAndWait();
	}
	@FXML
	private Button backButton;

	/**
	 * Return to main menu
	 * @param event
	 */
	@FXML
	void returnToMenu(ActionEvent event) {
		//returns player to Menu
		self.close();
		caller.show();
	}

	//Get player names
	public String getPlayerOneName(){
		return PlayerOne;
	}
	public String getPlayerTwoName(){
		return PlayerTwo;
	}
	public String getPlayerThreeName(){
		return PlayerThree;
	}
	public String getPlayerFourName(){
		return PlayerFour;
	}


/**
 * Setting up the controller, provides external requirements to functions
 * @param stage
 * @param engineinput
 * @param selfinput
 */
public void setUpController(Stage stage, GameEngine engineinput, Stage selfinput){
	engine=engineinput;
	caller=selfinput;
	self=stage;
	}

	/**
	 * This launches the main game screen 
	 */
public void launchGame(GameEngine game) {
    try {
    	/* Create a FXMLLoader - this is how you load a particular fxml file (the template of your window) */
    	FXMLLoader loader = new FXMLLoader(W_Setup.class.getClassLoader().getResource("ui/MainGameScreen.fxml"));
    	
    	/* Load the parent/root element in the file (in this case an HBox object) */
    	BorderPane page = (BorderPane) loader.load();
    	Stage newStage = new Stage();
    	/* Create a new scene with the root element in it. */
        Scene scene = new Scene(page, 1024, 700);
        /* Assign the scene to the page */
        newStage.setScene(scene);
        newStage.setResizable(false);
        newStage.setTitle("Buccaneer!");
    	((W_Game) (loader.getController())).setUpController(newStage, engine, self);
        self.close();
        newStage.show();
        ((W_Game) (loader.getController())).showPrepareDialog(engine.getCurrentPlayer());
    } catch (Exception ex) {
        Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
    }
}

}
