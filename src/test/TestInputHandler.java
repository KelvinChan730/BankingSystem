package test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.junit.Test;

import main.app.ApplicationView;
import main.app.InputHandler;
import main.exception.IOFunctionException;

public class TestInputHandler {
	@Test
	// test promptAccNo()
	public void shouldTakeUserInput01() {
		ApplicationView applicationView = new ApplicationView();

		String input = "0001";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		InputHandler inputHandler = new InputHandler(applicationView, new Scanner(System.in));
		assertDoesNotThrow(() -> assertEquals(input, inputHandler.promptAccNo()));
	}

	@Test
	// test promptAccNo()
	public void shouldTakeUserInput02() {
		ApplicationView applicationView = new ApplicationView();
		String input = "%%%";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		InputHandler inputHandler = new InputHandler(applicationView, new Scanner(System.in));
		assertThrows(IOFunctionException.class, () -> inputHandler.promptAccNo());
	}

	@Test
	// test promptTransferAccNo()
	public void shouldTakeUserInput03() {
		ApplicationView applicationView = new ApplicationView();

		String input = "%%%";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		InputHandler inputHandler = new InputHandler(applicationView, new Scanner(System.in));
		assertThrows(IOFunctionException.class, () -> inputHandler.promptTransferAccNo());
	}

	@Test
	//test promptTransferAccNo()
	public void shouldTakeUserInput04() {
		ApplicationView applicationView = new ApplicationView();

		String input = "0001";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		InputHandler inputHandler = new InputHandler(applicationView, new Scanner(System.in));
		assertDoesNotThrow(() -> assertEquals(input, inputHandler.promptTransferAccNo()));
	}
	
	@Test
	//test promptPassword()
	public void shouldTakeUserInput05() {
		ApplicationView applicationView = new ApplicationView();

		String input = "abc_123";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		InputHandler inputHandler = new InputHandler(applicationView, new Scanner(System.in));
		assertDoesNotThrow(() -> assertEquals(input, inputHandler.promptPassword()));
	}
	
	@Test
	//test promptPassword()
	public void shouldTakeUserInput06() {
		ApplicationView applicationView = new ApplicationView();

		String input = "abc_*123";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		InputHandler inputHandler = new InputHandler(applicationView, new Scanner(System.in));
		assertThrows(IOFunctionException.class, () -> inputHandler.promptPassword());
	}
	
	@Test
	//test promptName()
	public void shouldTakeUserInput07() {
		ApplicationView applicationView = new ApplicationView();

		String input = "abc123";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		InputHandler inputHandler = new InputHandler(applicationView, new Scanner(System.in));
		assertDoesNotThrow(() -> assertEquals(input, inputHandler.promptName()));
	}
	
	@Test
	//test promptName()
	public void shouldTakeUserInput08() {
		ApplicationView applicationView = new ApplicationView();

		String input = "abc_123";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		InputHandler inputHandler = new InputHandler(applicationView, new Scanner(System.in));
		assertThrows(IOFunctionException.class, () -> inputHandler.promptName());
	}
	@Test
	//test promptPhoneNo()
	public void shouldTakeUserInput09() {
		ApplicationView applicationView = new ApplicationView();

		String input = "12345678";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		InputHandler inputHandler = new InputHandler(applicationView, new Scanner(System.in));
		assertDoesNotThrow(() -> assertEquals(input, inputHandler.promptPhoneNo()));
	}
	
	@Test
	//test promptPhoneNo()
	public void shouldTakeUserInput10() {
		ApplicationView applicationView = new ApplicationView();

		String input = "1234567_8";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		InputHandler inputHandler = new InputHandler(applicationView, new Scanner(System.in));
		assertThrows(IOFunctionException.class, () -> inputHandler.promptPhoneNo());
	}
	
	@Test
	//test promptAmount()
	public void promptAmount11() {
		ApplicationView applicationView = new ApplicationView();

		String input = "1234";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		InputHandler inputHandler = new InputHandler(applicationView, new Scanner(System.in));
		assertDoesNotThrow(() -> assertEquals(input, inputHandler.promptAmount("withdraw")));
	}
	
	@Test
	//test promptAmount()
	public void promptAmount12() {
		ApplicationView applicationView = new ApplicationView();

		String input = "12))34";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		InputHandler inputHandler = new InputHandler(applicationView, new Scanner(System.in));
		assertThrows(IOFunctionException.class, () -> inputHandler.promptAmount("withdraw"));
	}
	
	@Test
	//test promptAccountOption()
	public void promptAccountOption13() {
		ApplicationView applicationView = new ApplicationView();

		String input = "1";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		InputHandler inputHandler = new InputHandler(applicationView, new Scanner(System.in));
		assertDoesNotThrow(() -> assertEquals(1, inputHandler.promptAccountOption()));
	}
	
	@Test
	//test promptAccountOption()
	public void promptAccountOption14() {
		ApplicationView applicationView = new ApplicationView();

		String input = "5";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		InputHandler inputHandler = new InputHandler(applicationView, new Scanner(System.in));
		assertThrows(IOFunctionException.class, () -> inputHandler.promptAccountOption());
	}
	
	@Test
	//test promptAccountOption()
	public void promptAccountOption15() {
		ApplicationView applicationView = new ApplicationView();

		String input = "0";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		InputHandler inputHandler = new InputHandler(applicationView, new Scanner(System.in));
		assertThrows(IOFunctionException.class, () -> inputHandler.promptAccountOption());
	}
	
	@Test
	//test promptMenuOption()
	public void promptMenuOption16() {
		ApplicationView applicationView = new ApplicationView();

		String input = "1";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		InputHandler inputHandler = new InputHandler(applicationView, new Scanner(System.in));
		assertDoesNotThrow(() -> assertEquals(1, inputHandler.promptMenuOption()));
	}
	
	@Test
	//test promptMenuOption()
	public void promptMenuOption17() {
		ApplicationView applicationView = new ApplicationView();

		String input = "aacc";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		InputHandler inputHandler = new InputHandler(applicationView, new Scanner(System.in));
		assertThrows(IOFunctionException.class, () -> inputHandler.promptMenuOption());
	}
	
	@Test
	//test promptMenuOption()
	public void promptMenuOption18() {
		ApplicationView applicationView = new ApplicationView();

		String input = "9";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		InputHandler inputHandler = new InputHandler(applicationView, new Scanner(System.in));
		assertThrows(IOFunctionException.class, () -> inputHandler.promptMenuOption());
	}
	
	
}
