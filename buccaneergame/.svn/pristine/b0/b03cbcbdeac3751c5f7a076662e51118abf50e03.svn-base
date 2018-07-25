/*
 * @(#) W_Rules.java 1.0 2017/05/05
 *
 * Copyright (c) 2017 Aberystwyth University.
 * All rights reserved.
 *
 */package ui.game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import uk.ac.aber.cs211.group07.system.GameEngine;

/*
 * Name of the controller class - in the UML diagram for
 * our game, it's all the classes that start with W_
 */
public class W_Rules{
	 String string;
	 Stage self;
	 Stage caller;
	 GameEngine engine;
	 /* This is the name of the button that is going to be referenced in the class
	  * For this to work the button has to have fx:id="btn1" in the fxml file (where btn1 is the name of the button
	  * [the name has to be unique in a single fxml file])
	  * (can be found on the right side under 'code' tab in the scenebuilder when the button is selected)
	  */
	 
	 @FXML
	 private Button prevBtn;
	 
	 @FXML
	 private Button nextBtn;
	 

	 @FXML
	 public ImageView image;

	 /* This is the method that's going to get injected into the button
	  * For this to work the button has to have onAction="#btnaction" in the fxml file (where btnaction is the name of the method
	  * [unique in a fxml file])
	  * (can be found on the right side under 'code' tab in the scenebuilder when the button is selected)
	  */
    
	  
    void returntoMenu(ActionEvent event) {
    	caller.show();
	    self.close();
    }

    public void setUpController(Stage stage, Stage selfinput){
    	caller=selfinput;
    	self=stage;
    	loadPageOne();
    	}
    
    private void loadPageOne(){
    	nextBtn.setOnAction(event -> loadPageTwo());
    	prevBtn.setOnAction(event -> returntoMenu(event));
    	Image page = new Image("ui/img/RuleScreen1.png");
    	image.setImage(page);
    }
    
    private void loadPageTwo(){
    	nextBtn.setOnAction(event -> loadPageThree());
    	prevBtn.setOnAction(event -> loadPageOne());
    	Image page = new Image("ui/img/RuleScreen2.png");
    	image.setImage(page);
    }
    
    private void loadPageThree(){
    	nextBtn.setOnAction(event -> returntoMenu(event));
    	prevBtn.setOnAction(event -> loadPageTwo());
    	Image page = new Image("ui/img/RuleScreen3.png");
    	image.setImage(page);
    }
    
    
	/* This is called to allow passing arguments before the window is displayed */
	public void setString(String s){
		string = s;
	}
}
