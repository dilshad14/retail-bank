package in.app.retail.bank.delegation;

import in.app.retail.bank.service.TransactionService;
import in.app.retail.bank.util.Constants;

public class TransactionDelegator {

	private TransactionService tService = new TransactionService();

	public void delegate(String commandLineInput) {

		if (commandLineInput != null && !commandLineInput.isBlank()) {
			String[] fields = commandLineInput.trim().split(" ");
			String action = fields[0];
			switch (action.toLowerCase()) {
			case Constants.ACTION_LOGIN:
				this.login(fields);
				break;
				
			case Constants.ACTION_PAY:
				this.pay(fields);
				break;
			case Constants.ACTION_TOPUP:
				this.topup(fields);
				break;

			default:
				System.out.println(Constants.INVALID_SYNTAX);
				break;

			}

		}

	}

	private void login(String[] fields) {

		if (fields.length != 2) {
			System.out.println(Constants.INVALID_SYNTAX);

		} else {
			tService.login(fields[1]);
		}

	}
	
	private void pay(String[] fields) {

		if (fields.length != 3) {
			System.out.println(Constants.INVALID_SYNTAX);

		} else {
			tService.pay(fields[1],Long.valueOf(fields[2]));
		}

	}
	
	private void topup(String[] fields) {

		if (fields.length != 2) {
			System.out.println(Constants.INVALID_SYNTAX);

		} else {
			tService.topup(Long.valueOf(fields[1]));
		}

	}

}
