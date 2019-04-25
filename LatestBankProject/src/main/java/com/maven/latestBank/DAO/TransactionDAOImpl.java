package com.maven.latestBank.DAO;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.maven.latestBank.Exception.InsufficientBalanceException;
import com.maven.latestBank.UI.MainUI;
import com.maven.latestBank.Utility.Utility;

public class TransactionDAOImpl implements TransactionDAO {
	Scanner in = new Scanner(System.in);
	MainUI mainui = new MainUI();
	Utility utility = new Utility();
	int amt;
	float bal;

	public int withdraw(int accountNo, int amount) throws Exception {
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "capgemini","Myoracle@123");

		String sql = "select * from customer_details1";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();

		while (rs.next()) {
			if (rs.getInt(1) == accountNo) {
				bal = rs.getFloat(10);
				break;
			}
		}

		if (bal > amount) {
			bal = bal - amount;
			String sql1 = "update customer_details1 set balance=? where account_no=?";
			pst = con.prepareStatement(sql1);
			pst.setInt(1, (int) bal);
			pst.setInt(2, accountNo);
			pst.executeUpdate();
		} else
			throw new InsufficientBalanceException("Balance is insuffiecinet to with draw");

		return mainui.printBalance(bal);
	}

	public int deposit(int accountNo, int amount) throws Exception {
		java.sql.Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "capgemini","Myoracle@123");

		String sql = "select * from customer_details1";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();

		while (rs.next()) {
			if (rs.getInt(1) == accountNo)
				bal = rs.getFloat(10);
		}

		bal = bal + amount;
		String sql1 = "update customer_details1 set balance=? where account_no=" + accountNo;
		PreparedStatement pst1 = con.prepareStatement(sql1);
		pst1.setInt(1, (int) bal);
		pst1.executeUpdate();
		mainui.printBalance(bal);
		return (int) bal;
	}

	public int transfer(int fromAccountNo, int toAccountNo, int amount) throws Exception {
		int c = 0, bal;

		java.sql.Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "capgemini","Myoracle@123");

		// Validate if toAccountNumber exists or not
		String sql = "select * from customer_details1 where account_no=" + fromAccountNo;
		PreparedStatement pst2 = con.prepareStatement(sql);
		ResultSet rs = pst2.executeQuery();

		while (rs.next()) {
			if (toAccountNo == rs.getInt(1))
				;
			c = 1;
		}
		bal = transferredBalance(fromAccountNo);

		if (c == 1 && bal > amount) {

			// Updating fromAccountNo balance
			String sql1 = "update customer_details1 set balance=? where account_no=" + fromAccountNo;
			PreparedStatement pst3 = con.prepareStatement(sql1);
			bal = bal - amount;
			pst3.setInt(1, bal);
			pst3.executeUpdate();

			mainui.updateStatus();
			
			// Updating toAccountNo balance
			String sql2 = "update customer_details1 set balance=? where account_no=" + toAccountNo;
			PreparedStatement pst4 = con.prepareStatement(sql2);
			bal = bal + amount;
			pst4.setInt(1, bal);
			pst4.executeUpdate();

			// inserting into transaction details table
			String sql3 = "insert into transaction_details1 values(account_seq.nextval,?,?,?)";
			PreparedStatement pst5 = con.prepareStatement(sql3);
			pst5.setInt(1, fromAccountNo);
			pst5.setInt(2, toAccountNo);
			pst5.setInt(3, amount);
			pst5.executeUpdate();

		}
		return 1;
	}

	public int balance(int accountNo) throws Exception {
		java.sql.Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "capgemini","Myoracle@123");

		String sql = "select * from customer_details1";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();

		int x = 0;

		while (rs.next()) {
			if (rs.getInt(1) == accountNo)
				bal = rs.getFloat(10);
			x = 1;
		}

		if (x == 1) {
			mainui.printBalance(bal);
			return (int) bal;
		} else
			return 0;
	}

	public int transferredBalance(int accountNo) throws Exception {
		java.sql.Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "capgemini","Myoracle@123");

		String sql = "select * from customer_details1";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();

		int x = 0;

		while (rs.next()) {
			if (rs.getInt(1) == accountNo)
				bal = rs.getFloat(10);
			x = 1;
		}

		if (x == 1) {
			// mainui.printBalance(bal);
			return (int) bal;
		} else
			return 0;
	}

}