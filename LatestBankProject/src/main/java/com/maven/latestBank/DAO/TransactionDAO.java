package com.maven.latestBank.DAO;

public interface TransactionDAO {
	public int withdraw(int accountNo,int amount) throws Exception;
	public int deposit(int accountNo,int amount) throws Exception;
	public int transfer(int fromAccountNo,int toAccountNo,int amount) throws Exception;
	public int balance(int accountNo) throws Exception;
}