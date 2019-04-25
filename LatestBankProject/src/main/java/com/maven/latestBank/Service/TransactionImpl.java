package com.maven.latestBank.Service;

import com.maven.latestBank.DAO.TransactionDAO;
import com.maven.latestBank.DAO.TransactionDAOImpl;

public class TransactionImpl implements TransactionService{

	TransactionDAO transDAO=new TransactionDAOImpl();
	public int withdraw(int accountNo,int amount) throws Exception {
		transDAO.withdraw(accountNo,amount);
		return 0;
	}

	public int deposit(int accountNo,int amount) throws Exception {
		transDAO.deposit(accountNo,amount);
		return 0;
	}

	public int transfer(int fromAccountNo,int toAccountNo,int amount) throws Exception {
		transDAO.transfer(fromAccountNo,toAccountNo,amount);
		return 0;
	}

	public int balance(int accountNo) throws Exception {
		transDAO.balance(accountNo);
		return 0;
	}
	
}