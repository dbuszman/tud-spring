package com.example.project_spring.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

//import com.example.project_spring.domain.Magazyn;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class ProductManagerTest {

	@Autowired
	ProductManager productManager;
	
	private final String NAME_1 = "Dell Vostro";
	private final int AMOUNT_1 = 100;
	private final int MARGIN_1 = 10;
	
	@Test
	public void test(){
		assertEquals(AMOUNT_1,100);
	}
}
