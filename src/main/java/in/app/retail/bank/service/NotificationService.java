package in.app.retail.bank.service;

import java.util.Map;

import in.app.retail.bank.account.Account;
import in.app.retail.bank.util.NotificationListener;

public class NotificationService {

	public static void generateLoginNotification(Account acc) {

		NotificationListener.add(String.format("Hello, %s!", acc.getName()));

		Map<String, Long> owingFrom = acc.getOweFrom();
		displayOwingInfo(owingFrom, "from");

		Map<String, Long> owingTo = acc.getOweFrom();
		displayOwingInfo(owingTo, "to");
		displayBalanceInfo(acc);

	}

	private static void displayBalanceInfo(Account acc) {

		NotificationListener.add(String.format("Your balance is %d.", acc.getBalance()));
	}

	private static void displayOwingInfo(Map<String, Long> mapData, String direction) {
		for (String name : mapData.keySet()) {
			NotificationListener.add(String.format("Owing %d %s %s.", mapData.get(name), direction, name));
		}

	}

	public static void generatePayNotification(Account acc, Map<String, Long> paymentData) {

		for (String name : paymentData.keySet()) {
			generateTransferredNotification(paymentData.get(name), name);
		}

		displayOwingInfo(acc.getOweFrom(), "from");
		displayOwingInfo(acc.getOweTo(), "to");
		displayBalanceInfo(acc);

	}

	private static void generateTransferredNotification(Long amtToPay, String name) {
		NotificationListener.add(String.format("Transferred %d to %s.", amtToPay, name));

	}

	public static void generateTopUpNotification(Account acc, Map<String, Long> paymentData) {

		for (String name : paymentData.keySet()) {
			generateTransferredNotification(paymentData.get(name), name);
		}
		displayOwingInfo(acc.getOweFrom(), "from");
		displayOwingInfo(acc.getOweTo(), "to");
		displayBalanceInfo(acc);

	}

}
