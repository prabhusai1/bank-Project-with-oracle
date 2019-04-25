package com.maven.latestBank.DAO;

import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.maven.latestBank.beans.Register;

class EntryDAOImplTest {

	static Register r;
	static EntryDAOImpl entry;
	
	@BeforeAll
	public static void init() {
		r = new Register();
		entry=new EntryDAOImpl();
	}
	
	
	//@Test
	void testRegister() throws Exception{
		r.setAadhaarNo(705331994557L);
		r.setFirstName("koushik"); 
		r.setLastName("gandi");
		r.setAddress("hyd");
		r.setBalance(500);
		r.setEmailId("gandi@gmail.com");
		r.setMobileNo(7386585920L);
		r.setPancardNo("ABCDE1234F");
		r.setPassword("gandi@1234");
		
		assertEquals(656184657,entry.register(r) );
		
	}

	@Test
	void testLogin() throws Exception {
		assertEquals(true, entry.login(1, "kotha@1234"));
	}

}
