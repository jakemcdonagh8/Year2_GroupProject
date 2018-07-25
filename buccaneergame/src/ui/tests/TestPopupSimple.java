package ui.tests;

import java.util.LinkedList;

import org.junit.Test;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ui.menus.Popup_Customised;
import ui.menus.Popup_Simple;

/**
 * Tests Popup_Simple class
 * @author Kamil Cupial
 * @version 0.1 - 02/05/17 - Initial creation /kac12
 *
 */
public class TestPopupSimple extends javafx.application.Application {
	
	@Test
	public void test(){
		this.launch(null);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		Stage simple = stage;
		
		//get an image
		Image img = new Image("ui/img/CrewCard_Black1.png", 148, 196, false, false);
		
		String text = "This is a test message. It should be clearly visible fully, with line breaks if necessary both"
				+ " within the given string AND automatically based on the width of the text. Linebreak: \n after linebreak";
		Popup_Simple popup = new Popup_Simple(null, simple, "Test popup", img, text);
		popup.getStage().show();
	}
}