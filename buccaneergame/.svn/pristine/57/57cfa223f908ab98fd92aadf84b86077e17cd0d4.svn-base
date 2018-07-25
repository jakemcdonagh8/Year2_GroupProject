package ui.menus;

import java.util.LinkedList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * Simple popup for general/custom use
 * @author Kamil Cupial
 * @version 0.1 - 02/05/17 - Initial creation /kac12
 *
 */
public class Popup_Simple {
Stage self;
	
	/**
	 * Creates a customisable popup
	 * @param caller	caller stage
	 * @param self	self stage
	 * @param title	title of the popup
	 * @param image	 image to display
	 * @param text	Text to display in the popup
	 */
	public Popup_Simple(Stage caller, Stage self, String title, Image image, String text){
		this.self = self;
		self.setTitle(title);
		VBox mainBox = new VBox();
		Text displayText = new Text(text);
		
		//setup
		mainBox.setPadding(new Insets(5,5,5,5));
		mainBox.setSpacing(10);
		mainBox.setAlignment(Pos.CENTER);
		displayText.setWrappingWidth(680);
		displayText.setTextAlignment(TextAlignment.CENTER);
		
		//add button
		Button confirm = new Button();
		confirm.setText("Confirm");
		confirm.setOnAction(event -> { caller.show(); self.close(); } );
		
		
		//add image
		ImageView displayer = new ImageView(image);
		
		//get everything together
		mainBox.getChildren().add(displayText);
		mainBox.getChildren().add(displayer);
		mainBox.setMargin(displayer, new Insets(5,5,5,5));
		mainBox.getChildren().add(confirm);
		mainBox.setMargin(confirm, new Insets(5,5,5,5));
		
		//create a scene
		Scene scene = new Scene(mainBox);
		self.setScene(scene);
		self.setResizable(false);
	}
	
	/**
	 * Returns the prepared window
	 * @return	Stage object
	 */
	public Stage getStage(){
		return self;
	}
}
