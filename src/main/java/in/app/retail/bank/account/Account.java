package in.app.retail.bank.account;

import java.util.HashMap;
import java.util.Map;

public class Account {

	private String name;
	private Long balance = 0L;
	private Map<String, Long> oweTo = new HashMap();
	private Map<String, Long> oweFrom = new HashMap();

	public Account(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public Map<String, Long> getOweTo() {
		return oweTo;
	}

	public void setOweTo(Map<String, Long> oweTo) {
		this.oweTo = oweTo;
	}

	public Map<String, Long> getOweFrom() {
		return oweFrom;
	}

	public void setOweFrom(Map<String, Long> oweFrom) {
		this.oweFrom = oweFrom;
	}
	
	// new methods
	
	public void setOweToInfo(String name, Long newAmt) {
		Long oldAmt = oweTo.get(name);
		if (oldAmt == null) {
			oweTo.put(name, newAmt);
		} else {
			oweTo.put(name, oldAmt+newAmt);
		}
	}
	
	public void addBalance(Long amount) {
		this.balance = this.balance + amount;
	}

	
}
