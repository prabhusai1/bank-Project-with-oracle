package com.maven.latestBank.beans;

public class Transactions {
	int fromAccountNumber,toAccountNumber,amountTransfer;

	public Transactions(int fromAccountNumber, int toAccountNumber, int amountTransfer) {
		this.fromAccountNumber = fromAccountNumber;
		this.toAccountNumber = toAccountNumber;
		this.amountTransfer = amountTransfer;
	}

	public void setFromAccountNumber(int fromAccountNumber) {
		this.fromAccountNumber = fromAccountNumber;
	}
	public int getFromAccountNumber() {
		return fromAccountNumber;
	}
	

	public void setToAccountNumber(int toAccountNumber) {
		this.toAccountNumber = toAccountNumber;
	}
	public int getToAccountNumber() {
		return toAccountNumber;
	}

	public void setAmountTransfer(int amountTransfer) {
		this.amountTransfer = amountTransfer;
	}
	public int getAmountTransfer() {
		return amountTransfer;
	}
}
