/*
 * @(#) Application.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ui.game.W_MainMenu;
import uk.ac.aber.cs211.group07.system.GameEngine;


/**
 * Main class, starting point
 * @author Kamil Cupial
 * @version 0.1 - 12/03/17 - initial creation (framework) /kac12
 * @version 0.11 - 02/05/17 - Changed comment and layout style to fit QA9 /haa14
 * @version 1.0 - 05/05/17 - Ready for initial release /haa14
 */
public class Application
extends javafx.application.Application{
	
	GameEngine engine;
	Stage mainStage;
	
	public static void main(String[] args) {
		Application app = new Application();
		app.run(args);
	}
	
	public void run(String[] args){
    	launch(args);
	}
	
	/**
	 * Start the main game
	 */
	@Override
	public void start(Stage primaryStage) {
        try {
        	engine = new GameEngine();
        	//Create a FXMLLoader - this is how you load a particular fxml file (the template of your window)
        	FXMLLoader loader = new FXMLLoader(Application.class.getClassLoader().getResource("ui/MainMenuFXML.fxml"));
        	//Load the parent/root element in the file (in this case an HBox object)
        	BorderPane page = (BorderPane) loader.load();
        	
        	//Get the controller assigned to this file and set its instance variable.
        	//Make sure your file has fx:controller="name" specified for the class that's going to control your scene (can be found on the left bottom corner in the scene builder software under 'Controller' tab).
        	//can call any public method of the Controller class to set up parameters (e.g. to set up scene's GameEngine or Caller object in our buccaneer game)
        	
        	((W_MainMenu) (loader.getController())).setUpController(primaryStage, engine);
        	//Create a new scene with the root element in it.
            Scene scene = new Scene(page, 1024, 660);
            //Assign the scene to the page
            primaryStage.setScene(scene);
            primaryStage.setTitle("Main Menu");
            primaryStage.setResizable(false);
            //Show the window
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
		
}
