package ui.game;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import uk.ac.aber.cs211.group07.system.GameEngine;

/*
 * Name of the controller class - in the UML diagram for
 * our game, it's all the classes that start with W_
 */

public class W_MainMenu{
	Stage self = new Stage(); //To call the primaryStage
	String string;
	GameEngine engine;
	 	
	 /* This is the name of the button that is going to be referenced in the class
	  * For this to work the button has to have fx:id="btn1" in the fxml file (where btn1 is the name of the button
	  * [the name has to be unique in a single fxml file])
	  * (can be found on the right side under 'code' tab in the scenebuilder when the button is selected)
	  */

	 /* This is the method that's going to get injected into the button
	  * For this to work the button has to have onAction="#btnaction" in the fxml file (where btnaction is the name of the method
	  * [unique in a fxml file])
	  * (can be found on the right side under 'code' tab in the scenebuilder when the button is selected)
	  */
   

	public void setUpController(Stage selfinput, GameEngine engineinput){
		engine=engineinput;
		self=selfinput;
	}
	public void initSetupWindow() {
        try {
        	FXMLLoader loader = new FXMLLoader(W_MainMenu.class.getClassLoader().getResource("ui/W_Setup.fxml"));
        	AnchorPane page = (AnchorPane) loader.load();
        	Stage newStage = new Stage();
        	/* Create a new scene with the root element in it. */
            Scene scene = new Scene(page);
            /* Assign the scene to the page */
            newStage.setScene(scene);
            newStage.setTitle("Start Game Page");
            newStage.setResizable(false);
        	((W_Setup) (loader.getController())).setUpController(newStage, engine, self);
        	self.close();
            newStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
	void initRulesWindow() {
        try {
        	/* Create a FXMLLoader - this is how you load a particular fxml file (the template of your window) */
        	FXMLLoader loader = new FXMLLoader(W_MainMenu.class.getClassLoader().getResource("ui/RulesWindow.fxml"));
        	/* Load the parent/root element in the file (in this case an HBox object) */
        	AnchorPane page = (AnchorPane) loader.load();
        	/* Get the controller assigned to this file and set its instance variable.
        	 * Make sure your file has fx:controller="name" specified for the class that's going to control your scene (can be found on the left bottom corner in the scene builder software under 'Controller' tab).
        	 * can call any public method of the Controller class to set up parameters (e.g. to set up scene's GameEngine or Caller object in our buccaneer game)
        	 */
        	Stage newStage = new Stage();
        	/* Create a new scene with the root element in it. */
            Scene scene = new Scene(page, 600, 460);
            /* Assign the scene to the page */
            newStage.setScene(scene);
            newStage.setTitle("Rules Page");
            newStage.setResizable(false);
        	((W_Rules) (loader.getController())).setUpController(newStage, self);

            /* Show the window */
        	self.close();
            newStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	public void initCreditsWindow(Stage primaryStage) {
        try {
        	/* Create a FXMLLoader - this is how you load a particular fxml file (the template of your window) */
        	FXMLLoader loader = new FXMLLoader(W_MainMenu.class.getClassLoader().getResource("ui/W_Credits.fxml"));
        	/* Load the parent/root element in the file (in this case an HBox object) */
        	AnchorPane page = (AnchorPane) loader.load();
        	/* Get the controller assigned to this file and set its instance variable.
        	 * Make sure your file has fx:controller="name" specified for the class that's going to control your scene (can be found on the left bottom corner in the scene builder software under 'Controller' tab).
        	 * can call any public method of the Controller class to set up parameters (e.g. to set up scene's GameEngine or Caller object in our buccaneer game)
        	 */
        	((W_Credits) (loader.getController())).setString("Data passed to the credits controller"); //the file we're using to make a scene uses class Controller as its source of methods and data so this is what we cast to
        	Stage newStage = new Stage();
        	/* Create a new scene with the root element in it. */
            Scene scene = new Scene(page);
            /* Assign the scene to the page */
            newStage.setScene(scene);
            newStage.setTitle("Credits Page");
            newStage.setResizable(false);
        	((W_Credits) (loader.getController())).setUpController(newStage, self);

            /* Show the window */
        	self.close();
        	newStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
    @FXML
    void startBtn(ActionEvent event) {
    	initSetupWindow();
    	System.out.println("Button pressed - Start");
    	System.out.println(string);
    }
    @FXML
    void rulesBtn(ActionEvent event) {
    	//By creating the primaryStage and rules object I managed to open the rules page, closing is more of an issue.
    	initRulesWindow();
    	System.out.println("Button pressed - Rules");
    	System.out.println(string);
    }
    @FXML
    void creditsBtn(ActionEvent event) {
    	initCreditsWindow(self);
    	System.out.println("Button pressed - Credits");
    	System.out.println(string);
    }
    @FXML
    void exitBtn(ActionEvent event) {
    	System.out.println("Button pressed - Exit");
    	System.out.println(string);
    	System.exit(1);
    }
    
	/* This is called to allow passing arguments before the window is displayed */
	public void setString(String s){
		string = s;
	}
}
