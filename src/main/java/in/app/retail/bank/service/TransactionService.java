package in.app.retail.bank.service;

import java.util.HashMap;
import java.util.Map;

import in.app.retail.bank.account.Account;
import in.app.retail.bank.repository.AccountRepository;

public class TransactionService {

	public void login(String name) {

		AccountRepository.getOrCreateAccountbyNameIfNotExist(name);
		AccountRepository.setLoggedInAccount(name);
		NotificationService.generateLoginNotification(AccountRepository.getLoggedInAccount(name));

	}

	public void pay(String name, Long amount) {

		Account loggedInAcc = AccountRepository.loggedInAccount;
		Account payToAcc = AccountRepository.getOrCreateAccountbyNameIfNotExist(name);
		Map<String, Long> paymentData = new HashMap<>(1);

		Long offset = loggedInAcc.getBalance() - amount;

		Long amtToPay = 0L;

		if (offset >= 0L) {
			loggedInAcc.setBalance(offset);
			amtToPay = amount;
		} else {
			amtToPay = loggedInAcc.getBalance();
			loggedInAcc.setBalance(0L);
			loggedInAcc.setOweToInfo(name, Math.abs(offset));
		}
		payToAcc.addBalance(amtToPay);
		paymentData.put(payToAcc.getName(), amtToPay);

		NotificationService.generatePayNotification(AccountRepository.getLoggedInAccount(name), paymentData);

	}

	public void topup(Long topUpAmount) {

		Account loggedInAcc = AccountRepository.loggedInAccount;

		Map<String, Long> oweTo = loggedInAcc.getOweTo();
		Map<String, Long> paymentData = new HashMap<>();

		if (oweTo.size() != 0) {
			for (String name : oweTo.keySet()) {

				Long oweAmt = oweTo.get(name);
				Long offset = topUpAmount - oweAmt;

				Long amtToPay = 0L;

				if (offset >= 0L) {
					topUpAmount = offset;
					amtToPay = oweAmt;

				} else {
					amtToPay = topUpAmount;
					topUpAmount = 0L;
				}

				loggedInAcc.addBalance(topUpAmount);
				AccountRepository.getAccountByName(name).addBalance(amtToPay);
				paymentData.put(name, amtToPay);

			}
		} else {

			loggedInAcc.addBalance(topUpAmount);
		}

		NotificationService.generateTopUpNotification(loggedInAcc, paymentData);

	}
}
