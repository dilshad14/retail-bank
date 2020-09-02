package in.app.retail.bank;

import java.util.Scanner;

import in.app.retail.bank.delegation.TransactionDelegator;
import in.app.retail.bank.util.NotificationListener;

public class RetailBankApp {

	public static void main(String[] args) {

		
		TransactionDelegator td = new TransactionDelegator();
		Scanner scn = new Scanner(System.in);
		while (true) {
			String commandLine = scn.nextLine();
			if (commandLine.trim().toLowerCase().startsWith("exit")) {
				break;
			}
			if (!commandLine.trim().isBlank()) {
				td.delegate(commandLine);
			}
		}
		
		NotificationListener.add("Program has ended!");
	}

}
