package com.maven.latestBank.DAO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.maven.latestBank.beans.Register;

class TransactionDAOImplTest {

	static TransactionDAOImpl t;

	@BeforeAll
	public static void init() {
		t = new TransactionDAOImpl();

	}

	@Test
	void testWithdraw() throws Exception {
		assertEquals(1, t.withdraw(1, 100));
	}

	@Test
	void testDeposit() throws Exception {
		int bal = t.balance(1);
		bal = bal + 100;
		assertEquals(bal, t.deposit(1, 100));
	}

	@Test
	void testBalance() throws Exception {
		assertEquals(6500, t.balance(1));
	}

}
