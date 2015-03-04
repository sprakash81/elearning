package com.comp.elearning;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class TestDBConnection {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Test
	@Transactional
	public void testSaveAndFind() throws Exception {
		
		
		Session session = sessionFactory.getCurrentSession();
		
		List results = session
				.createQuery( "select a from account a").list();
		assertTrue(results.size()>0);
	}
	
}
