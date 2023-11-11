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
import main.constant.Currency;
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
	public void shouldTakeUserInput11() {
		ApplicationView applicationView = new ApplicationView();

		String input = "1234";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		InputHandler inputHandler = new InputHandler(applicationView, new Scanner(System.in));
		assertDoesNotThrow(() -> assertEquals(input, inputHandler.promptAmount("withdraw")));
	}
	
	@Test
	//test promptAmount()
	public void shouldTakeUserInput12() {
		ApplicationView applicationView = new ApplicationView();

		String input = "12))34";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		InputHandler inputHandler = new InputHandler(applicationView, new Scanner(System.in));
		assertThrows(IOFunctionException.class, () -> inputHandler.promptAmount("withdraw"));
	}
	
	@Test
	//test promptAccountOption()
	public void shouldTakeUserInput13() {
		ApplicationView applicationView = new ApplicationView();

		String input = "1";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		InputHandler inputHandler = new InputHandler(applicationView, new Scanner(System.in));
		assertDoesNotThrow(() -> assertEquals(1, inputHandler.promptAccountOption()));
	}
	
	@Test
	//test promptAccountOption()
	public void shouldTakeUserInput14() {
		ApplicationView applicationView = new ApplicationView();

		String input = "5";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		InputHandler inputHandler = new InputHandler(applicationView, new Scanner(System.in));
		assertThrows(IOFunctionException.class, () -> inputHandler.promptAccountOption());
	}
	
	@Test
	//test promptAccountOption()
	public void shouldTakeUserInput15() {
		ApplicationView applicationView = new ApplicationView();

		String input = "0";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		InputHandler inputHandler = new InputHandler(applicationView, new Scanner(System.in));
		assertThrows(IOFunctionException.class, () -> inputHandler.promptAccountOption());
	}
	
	@Test
	//test promptMenuOption()
	public void shouldTakeUserInput16() {
		ApplicationView applicationView = new ApplicationView();

		String input = "1";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		InputHandler inputHandler = new InputHandler(applicationView, new Scanner(System.in));
		assertDoesNotThrow(() -> assertEquals(1, inputHandler.promptMenuOption()));
	}
	
	@Test
	//test promptMenuOption()
	public void shouldTakeUserInput17() {
		ApplicationView applicationView = new ApplicationView();

		String input = "aacc";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		InputHandler inputHandler = new InputHandler(applicationView, new Scanner(System.in));
		assertThrows(IOFunctionException.class, () -> inputHandler.promptMenuOption());
	}
	
	@Test
	//test promptMenuOption()
	public void shouldTakeUserInput18() {
		ApplicationView applicationView = new ApplicationView();

		String input = "9";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		InputHandler inputHandler = new InputHandler(applicationView, new Scanner(System.in));
		assertThrows(IOFunctionException.class, () -> inputHandler.promptMenuOption());
	}
	
	@Test
	//test promptMenuOption()
	public void shouldTakeUserInput19() {
		ApplicationView applicationView = new ApplicationView();

		String input = "0";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		InputHandler inputHandler = new InputHandler(applicationView, new Scanner(System.in));
		assertThrows(IOFunctionException.class, () -> inputHandler.promptMenuOption());
	}
	
	@Test
	//test promptLoanID()
	public void shouldTakeUserInput20() {
		ApplicationView applicationView = new ApplicationView();
		String input = "0001";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		InputHandler inputHandler = new InputHandler(applicationView, new Scanner(System.in));
		assertDoesNotThrow(() -> assertEquals(input, inputHandler.promptLoanID()));
	}
	
	@Test
	//test promptLoanID()
	public void shouldTakeUserInput21() {
		ApplicationView applicationView = new ApplicationView();

		String input = "00-1";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		InputHandler inputHandler = new InputHandler(applicationView, new Scanner(System.in));
		assertThrows(IOFunctionException.class, () -> inputHandler.promptLoanID());
	}
	
	@Test
	//test promptCurrencyType()()
	public void shouldTakeUserInput22() {
		ApplicationView applicationView = new ApplicationView();
		String input = "2";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		InputHandler inputHandler = new InputHandler(applicationView, new Scanner(System.in));
		assertDoesNotThrow(() -> assertEquals(Currency.USD, inputHandler.promptCurrencyType()));
	}
	
	@Test
	//test promptCurrencyType()()
	public void shouldTakeUserInput23() {
		ApplicationView applicationView = new ApplicationView();
		String input = "%%%";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		InputHandler inputHandler = new InputHandler(applicationView, new Scanner(System.in));
		assertThrows(IOFunctionException.class, () -> inputHandler.promptCurrencyType());
	}
	
	@Test
	//test promptCurrencyType()()
	public void shouldTakeUserInput24() {
		ApplicationView applicationView = new ApplicationView();
		String input = "0";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		InputHandler inputHandler = new InputHandler(applicationView, new Scanner(System.in));
		assertThrows(IOFunctionException.class, () -> inputHandler.promptCurrencyType());
	}
	
	@Test
	//test promptCurrencyType()()
	public void shouldTakeUserInput25() {
		ApplicationView applicationView = new ApplicationView();
		String input = "100";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		InputHandler inputHandler = new InputHandler(applicationView, new Scanner(System.in));
		assertThrows(IOFunctionException.class, () -> inputHandler.promptCurrencyType());
	}
	
}
