package ui.game;

import javafx.stage.Stage;
import uk.ac.aber.cs211.group07.system.GameEngine;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class W_Pause {
	String string;
	GameEngine engine;
	Stage caller;
	Stage self;
	public void setUpController(Stage stage, GameEngine engineinput, Stage selfinput){
		engine=engineinput;
		caller=selfinput;
		self=stage;
	}
	public void setString(String s) {
		string = s;
	}
	
    @FXML
    private Button returnMenu;

    @FXML
    private Button exitMenu;

    @FXML
    private Button quitGame;
    

    @FXML
    void exitGame(ActionEvent event) {
    	System.out.println("Button pressed - Exit");
		System.out.println(string);
    	System.exit(1);
    }

    @FXML
    void resumeGame(ActionEvent event) {
    	self.close();
    	//caller.show();
    	System.out.println("This would return you to the main menu.");
    }

    @FXML
    void returnToMenu() {
        try {
            engine = new GameEngine();
            //Create a FXMLLoader - this is how you load a particular fxml file (the template of your window)
            FXMLLoader loader = new FXMLLoader(W_Pause.class.getClassLoader().getResource("ui/MainMenuFXML.fxml"));
            //Load the parent/root element in the file (in this case an HBox object)
            BorderPane page = (BorderPane) loader.load();
           
            //Get the controller assigned to this file and set its instance variable.
            //Make sure your file has fx:controller="name" specified for the class that's going to control your scene (can be found on the left bottom corner in the scene builder software under 'Controller' tab).
            //can call any public method of the Controller class to set up parameters (e.g. to set up scene's GameEngine or Caller object in our buccaneer game)
            Stage primaryStage = new Stage();
            //Create a new scene with the root element in it.
            Scene scene = new Scene(page);
            //Assign the scene to the page
            primaryStage.setScene(scene);
            primaryStage.setTitle("Main Menu");
            primaryStage.setResizable(false);
            ((W_MainMenu) (loader.getController())).setUpController(primaryStage, engine);
            //Show the window
            caller.close();
            self.close();
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
