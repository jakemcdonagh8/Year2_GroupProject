package ui.menus;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.util.LinkedList;

public class Popup_MultipleOptions {
	Stage self;

	public Popup_MultipleOptions(Stage caller, Stage self, String title, Image image, String text,
			LinkedList<Button> choices) {
		this.self = self;
		self.setTitle(title);
		VBox mainBox = new VBox();
		ImageView imageBox = new ImageView(image);
		Text displayText = new Text(text);
		HBox buttons = new HBox();

		// setup
		mainBox.setPadding(new Insets(5, 5, 5, 5));
		mainBox.setSpacing(10);
		mainBox.setAlignment(Pos.CENTER);
		displayText.setWrappingWidth(680);
		displayText.setTextAlignment(TextAlignment.CENTER);

		// add buttons
		if (choices != null) {
			for (Button i : choices) {
				buttons.getChildren().add(i);
				HBox.setMargin(i, new Insets(5, 5, 5, 5));
			}
		} else {
			Button confirm = new Button();
			confirm.setText("Confirm");
			confirm.setOnAction(event -> {
				caller.show();
				self.close();
			});
			buttons.getChildren().add(confirm);
			buttons.setMargin(confirm, new Insets(5, 5, 5, 5));
		}

		// Bring all items together
		buttons.setAlignment(Pos.CENTER);
		mainBox.getChildren().add(displayText);
		mainBox.getChildren().add(imageBox);
		mainBox.getChildren().add(buttons);

		// create a scene
		Scene scene = new Scene(mainBox);
		self.setScene(scene);
		self.setResizable(false);
	}
	
	public Stage getStage(){
		return self;
	}

}
