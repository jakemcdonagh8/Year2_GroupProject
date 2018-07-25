/*
 * @(#) W_Credits.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */

package ui.game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controller for the Credits screen
 * @author haa14
 * @version 0.1 - 01/05/17 - Initial creation /haa14
 * @version 0.11 - 02/05/17 - Refactoring of packages /kac12
 * @version 0.2 - 03/05/17 - Fixed issues /rob27
 * @version 0.3 - 04/05/17 - Commented to comply with QA9 /haa14
 * @version 0.31 - 04/05/17 - Replaced a removed function that I thought was unnecessary. Whoops /haa14
 */
public class W_Credits{
	 String string;
	 Stage caller;
	 Stage self;
	 
	 @FXML
	 private Button mainmenuBtn;
	  
	/**
	 * Return to main menu
	 * @param event
	 */
    @FXML
    void returntoMenu(ActionEvent event) { 
	    self.close();
    	caller.show();
    }
    
    /**
     * Set up the controller with stages
     * @param stage
     * @param selfinput
     */
    public void setUpController(Stage stage, Stage selfinput){
    	caller=selfinput;
    	self=stage;
    }
    
    /**
     * Close the pause screen and resume the game.
     */
    void resumeGame(){
    	self.close();
    }
	
    /**
     * Quit the game completely with System.exit()
     */
	@FXML
	void exitGame(){
		//Quit the game
		System.exit(0);
	}
	
	/**
	 *  This is called to allow passing arguments before the window is displayed
	 */
	public void setString(String s){
		string = s;
	}
}
