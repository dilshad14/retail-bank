package in.app.retail.bank;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import in.app.retail.bank.delegation.TransactionDelegator;
import in.app.retail.bank.repository.AccountRepository;
import in.app.retail.bank.util.NotificationListener;

public class RetailBankAppTest {
	TransactionDelegator td = new TransactionDelegator();
	
	@BeforeAll
	public static void BeforeAllSetup() {
		NotificationListener.setConsoleOutput(Boolean.FALSE);
	}
	

	@BeforeEach
	public void BeforeEachSetup() {
		AccountRepository.allAccounts.clear();
	}
	
	

	@Test
	public  void loginTest() {
		
		String commandLineInput = "login Alice";
		td.delegate(commandLineInput);
		assertTrue(NotificationListener.pollFirstFromNotificationQueue().equals("Hello, Alice!")
				&& NotificationListener.pollFirstFromNotificationQueue().equals("Your balance is 0."));

	}

	@Test
	public void topupTest() {

		String commandLineInput1 = "login Alice";
		td.delegate(commandLineInput1);
		NotificationListener.pollFirstFromNotificationQueue();// "Hello, Alice!"
		NotificationListener.pollFirstFromNotificationQueue();// "Your balance is 0."
		
		String commandLineInput2 = "topup 100";
		td.delegate(commandLineInput2);
		assertTrue(NotificationListener.pollFirstFromNotificationQueue().equals("Your balance is 100."));

	}
	
	@Test
	public void payTest() {

		String commandLineInput1 = "login Alice";
		td.delegate(commandLineInput1);
		NotificationListener.pollFirstFromNotificationQueue();// "Hello, Alice!"
		NotificationListener.pollFirstFromNotificationQueue();// "Your balance is 0."
		
		String commandLineInput2 = "topup 100";
		td.delegate(commandLineInput2);
		NotificationListener.pollFirstFromNotificationQueue();//"Your balance is 100."
		
		String commandLineInput3 = "pay Bob 20";
		td.delegate(commandLineInput3);
		
		assertTrue(NotificationListener.pollFirstFromNotificationQueue().equals("Transferred 20 to Bob.")
				&& NotificationListener.pollFirstFromNotificationQueue().equals("Your balance is 80."));
	}
	
	@Test
	public void payWithOweTest() {

		String commandLineInput1 = "login Alice";
		td.delegate(commandLineInput1);
		NotificationListener.pollFirstFromNotificationQueue();// "Hello, Alice!"
		NotificationListener.pollFirstFromNotificationQueue();// "Your balance is 0."
		
		String commandLineInput2 = "topup 100";
		td.delegate(commandLineInput2);
		NotificationListener.pollFirstFromNotificationQueue();//"Your balance is 100."
		
		String commandLineInput3 = "pay Alex 130";
		td.delegate(commandLineInput3);
		
		assertTrue( NotificationListener.pollFirstFromNotificationQueue().equals("Transferred 100 to Alex.")
				&& NotificationListener.pollFirstFromNotificationQueue().equals("Owing 30 to Alex.")
				&& NotificationListener.pollFirstFromNotificationQueue().equals("Your balance is 0."));
		
	}
}
