package ui.tests;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ui.menus.Popup_Customised;

/**
 * Tests Popup_Customised class
 * @author Kamil Cupial
 * @version 0.1 - 02/05/17 - Initial creation /kac12
 *
 */
public class TestPopupCustomised extends javafx.application.Application {
	
	@Test
	public void test(){
		this.launch(null);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		Stage customised = stage;
		
		//prepare buttons
		Button btn1 = new Button();
		btn1.setText("Change my text");
		btn1.setOnAction(event -> { 
			btn1.setText("Text changed"); });
		Button btn2 = new Button();
		btn2.setText("Exit");
		btn2.setOnAction(event -> customised.close());
		LinkedList<Button> btns = new LinkedList<Button>();
		btns.add(btn1);
		btns.add(btn2);
		btns.add(new Button("123"));
		btns.add(new Button("456"));
		
		//get some images
		LinkedList<Image> imgs = new LinkedList<Image>();
		imgs.add(new Image("ui/img/CrewCard_Black1.png", 148, 196, false, false));
		imgs.add(new Image("ui/img/CrewCard_Black2.png", 148, 196, false, false));
		imgs.add(new Image("ui/img/Rum.png", 48, 48, false, false));
		imgs.add(new Image("ui/img/Rum.png", 48, 48, false, false));
		imgs.add(new Image("ui/img/Rum.png", 48, 48, false, false));
		imgs.add(new Image("ui/img/Rum.png", 48, 48, false, false));
		imgs.add(new Image("ui/img/CrewCard_Black3.png", 48, 96, false, false));
		
		String text = "This is a test message. It should be clearly visible fully, with line breaks if necessary both"
				+ " within the given string AND automatically based on the width of the text. Linebreak: \n after linebreak";
		Popup_Customised popup = new Popup_Customised(null, customised, "Test popup", imgs, text, btns);
		popup.getStage().show();
	}

}
