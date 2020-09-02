package in.app.retail.bank.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import in.app.retail.bank.account.Account;

public class AccountRepository {

	public static Account loggedInAccount = null;
	public static Map<String, Account> allAccounts = new ConcurrentHashMap<String, Account>();

	public static Account getAccountByName(String accName) {
		return allAccounts.get(accName);
	}

	public static void setLoggedInAccount(String accName) {
		loggedInAccount = getAccountByName(accName);
	}

	public static Account getLoggedInAccount(String accName) {
		return loggedInAccount;
	}

	public static Account getOrCreateAccountbyNameIfNotExist(String name) {

		Account acc = allAccounts.get(name);
		if (acc == null) {
			acc = new Account(name);
			allAccounts.put(name, acc);
		}

		return acc;

	}
}
