import org.junit.Test;

import main.app.ApplicationView;
import main.app.InputHandler;

public class TestInputHandler {
	@Test
	public void testInputHandler1() {
		ApplicationView applicationView = new ApplicationView();
		InputHandler inputHandler = new InputHandler(applicationView);
	}
}
