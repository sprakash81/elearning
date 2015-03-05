package com.comp.elearning.dao;

import static junit.framework.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.comp.elearning.AbstractJpaTests;
import com.comp.elearning.entity.Account;

@RunWith(SpringJUnit4ClassRunner.class)
public class AccountDaoTest extends AbstractJpaTests {

	@Autowired
	private AccountDao dao;

	@Test
	public void testGet() {
		final Account account = dao.get(1l);
		assertNotNull(account);
		assertEquals(1l, account.getId().longValue());
		assertEquals("Shyam", account.getFirstName());
		assertEquals("Prakash", account.getLastName());
		assertEquals("prakash.shyam@gmail.com", account.getEmailId());
		assertTrue(account.getActiveIndicator());
	}

}
