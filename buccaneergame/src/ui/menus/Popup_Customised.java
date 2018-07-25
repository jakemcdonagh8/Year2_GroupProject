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
 * Customised popup for general/custom use
 * @author Kamil Cupial
 * @version 0.1 - 02/05/17 - Initial creation /kac12
 *
 */
public class Popup_Customised {
	Stage self;
	
	/**
	 * Creates a customisable popup
	 * @param caller	caller stage
	 * @param title	title of the popup
	 * @param images	LinkedList of Images to display
	 * @param text	Text to display in the popup
	 * @param choices	LinkedList of Buttons available (can be left null)
	 */
	public Popup_Customised(Stage caller, Stage self, String title, LinkedList<Image> images, String text, LinkedList<Button> choices){
		this.self = self;
		self.setTitle(title);
		VBox mainBox = new VBox();
		Text displayText = new Text(text);
		HBox buttons = new HBox();
		HBox displayImages = new HBox();
		
		//setup
		mainBox.setPadding(new Insets(5,5,5,5));
		mainBox.setSpacing(10);
		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(5,5,5,5));
		displayImages.setAlignment(Pos.CENTER);
		displayImages.setPadding(new Insets(5,5,5,5));
		displayText.setWrappingWidth(680);
		displayText.setTextAlignment(TextAlignment.CENTER);
		
		//add buttons
		if (choices != null){
			for (Button b : choices){
				buttons.getChildren().add(b);
				buttons.setMargin(b, new Insets(5,5,5,5));
			}
		} else {
			Button confirm = new Button();
			confirm.setText("Confirm");
			confirm.setOnAction(event -> { caller.show(); self.close(); } );
			buttons.getChildren().add(confirm);
			buttons.setMargin(confirm, new Insets(5,5,5,5));
		}
		
		//add images
		for (Image i : images){
			ImageView displayer = new ImageView(i);
			displayImages.getChildren().add(displayer);
			displayImages.setMargin(displayer, new Insets(5,5,5,5));
		}
		
		//get everything together
		mainBox.getChildren().add(displayText);
		mainBox.getChildren().add(displayImages);
		mainBox.getChildren().add(buttons);
		
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
