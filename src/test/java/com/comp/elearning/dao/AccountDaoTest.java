package com.comp.elearning.dao;

import static junit.framework.Assert.*;

import java.util.List;

import javax.validation.ConstraintViolationException;

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
	public void testCount() {
		final long count = dao.count();
		assertEquals(dao.listAll().size(), count);
	}

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

	@Test
	public void testGetWhenInvalidId() {
		final Account account = dao.get(-1l);
		assertNull(account);
	}

	@Test
	public void testListAll() {
		final List<Account> accounts = dao.listAll();
		assertNotNull(accounts);
		assertTrue(accounts.size() > 0);
		for (final Account account : accounts) {
			assertFalse(account.getFirstName().isEmpty());
			assertFalse(account.getLastName().isEmpty());
			assertFalse(account.getEmailId().isEmpty());
		}
	}

	@Test
	public void testSave() {
		final Account account = new Account();
		account.setFirstName("TestFirstName");
		account.setLastName("testLastName");
		account.setEmailId("email@gmail.com");
		final long id = dao.saveOrUpdate(account);
		assertNotNull(id);
		final Account retrievedAccount = dao.get(id);
		assertNotNull(retrievedAccount);
		assertEquals(id, retrievedAccount.getId().longValue());
		assertEquals(account.getFirstName(), retrievedAccount.getFirstName());
		assertEquals(account.getLastName(), retrievedAccount.getLastName());
		assertEquals(account.getEmailId(), retrievedAccount.getEmailId());
		assertTrue(retrievedAccount.getActiveIndicator());
		assertEquals(0L, retrievedAccount.getVersion().longValue());
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSaveWithGreaterThanMaxLength() {
		final Account account = new Account();
		account.setFirstName("aaaaa aaaaa aaaaa aaaaa aaaaa aaaaa aaaaa aaaaa aaaaa aaaaa aaaaa aaaaa");
		account.setLastName("bbbbb bbbbb bbbbb bbbbb bbbbb bbbbb bbbbb bbbbb bbbbb bbbbb bbbbb bbbbb");
		account.setEmailId("ccccc ccccc ccccc ccccc ccccc ccccc ccccc ccccc ccccc ccccc ccccc ccccc");
		dao.saveOrUpdate(account);
		fail("The test should have failed with Data Exception for lengths being greater than max length");
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSaveWithoutMandatoryFields() {
		final Account account = new Account();
		dao.saveOrUpdate(account);
		fail("The test should have failed with constraint violation exception");
	}

	@Test
	public void testUpdate() {
		final Account account = dao.get(1l);
		assertNotNull(account);
		account.setFirstName("TestFirstName");
		account.setLastName("testLastName");
		account.setEmailId("email@gmail.com");
		final long id = dao.saveOrUpdate(account);
		assertNotNull(id);
		final Account retrievedAccount = dao.get(id);
		assertNotNull(retrievedAccount);
		assertEquals(id, retrievedAccount.getId().longValue());
		assertEquals(account.getFirstName(), retrievedAccount.getFirstName());
		assertEquals(account.getLastName(), retrievedAccount.getLastName());
		assertEquals(account.getEmailId(), retrievedAccount.getEmailId());
		assertTrue(retrievedAccount.getActiveIndicator());
		assertEquals(0L, retrievedAccount.getVersion().longValue());
	}

	// @Ignore("Hibernate save fails and saves more than the lenght of the column.  Alternate is to use @Size in entity.")
	@Test(expected = ConstraintViolationException.class)
	public void testUpdateWithGreaterThanMaxLength() {
		final Account account = dao.get(1l);
		account.setFirstName("aaaaa aaaaa aaaaa aaaaa aaaaa aaaaa aaaaa aaaaa aaaaa aaaaa aaaaa aaaaa");
		account.setLastName("bbbbb bbbbb bbbbb bbbbb bbbbb bbbbb bbbbb bbbbb bbbbb bbbbb bbbbb bbbbb");
		account.setEmailId("ccccc ccccc ccccc ccccc ccccc ccccc ccccc ccccc ccccc ccccc ccccc ccccc");
		final long id = dao.saveOrUpdate(account);
		final Account updatedAccount = dao.get(id);
		fail("The test should have failed with Data Exception for lengths being greater than max length");
	}

	// @Ignore("Hibernate save fails and saves with empty string for mandatory columns.  Alternate is to use @NotNull in entity.")
	@Test(expected = ConstraintViolationException.class)
	public void testUpdateWithoutMandatoryFields() {
		final Account account = dao.get(1l);
		assertNotNull(account);
		account.setFirstName("");
		account.setLastName("");
		account.setEmailId("");
		final long id = dao.saveOrUpdate(account);
		final Account updatedAccount = dao.get(id);
		fail("The test should have failed with constraint violation exception");
	}
}
