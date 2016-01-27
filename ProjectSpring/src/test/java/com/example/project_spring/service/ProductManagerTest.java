package com.example.project_spring.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.project_spring.domain.Magazyn;
import com.example.project_spring.domain.ToOrder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class ProductManagerTest {

	@Autowired
	ProductManager productManager;
	
	private final String NAME_1 = "Dell Vostro";
	private final int AMOUNT_1 = 100;
	private final int MARGIN_1 = 10;
	
	private final String NAME_2 = "Dell Inspiron";
	private final int AMOUNT_2 = 10;
	private final int MARGIN_2 = 8;
	
	private final String PATTERN_1 = "Dell";
	
	private final List<Magazyn> currentPositions = new ArrayList<Magazyn>();
	private final List<ToOrder> currentOrders = new ArrayList<ToOrder>();
	
 	@Before
    public void storeCurrentDatabase() {

        List<Magazyn> positions = productManager.getAllPositions();
        List<ToOrder> orders = productManager.getAllOrders();
        
		for(Magazyn position : positions) {
			currentPositions.add(position);
		}
		
		for(ToOrder order : orders) {
			currentOrders.add(order);
		}
        
    }

    @After
    public void cleanUpDatabase() {

    	List<Magazyn> afterTestPositions = productManager.getAllPositions();
    	List<ToOrder> afterTestOrders = productManager.getAllOrders();

        for(Magazyn position : afterTestPositions) {
        	boolean flag = false;
        	for(Magazyn position2 : currentPositions){
        		if (position.getId() == position2.getId()){
        			flag = true;
        			break;
        		}
        	}
        	if (flag == false){
        		productManager.removePosition(position);
        	}
        }
        
        for(ToOrder order : afterTestOrders) {
        	boolean flag = false;
        	for(ToOrder order2 : currentOrders){
        		if (order.getId() == order2.getId()){
        			flag = true;
        			break;
        		}
        	}
        	if (flag == false){
        		productManager.removeOrder(order);
        	}
        }
    }
        	
	@Test
	public void addNewPositionCheck(){
		
		Magazyn magazyn = new Magazyn();
		magazyn.setName(NAME_1);
		magazyn.setAmount(AMOUNT_1);
		magazyn.setMargin(MARGIN_1);
		
		productManager.addNewPosition(magazyn);
		
		Magazyn addedPosition = productManager.findPositionByObject(magazyn);
		
		assertEquals(NAME_1, addedPosition.getName());
		assertEquals(AMOUNT_1, addedPosition.getAmount());
		assertEquals(MARGIN_1, addedPosition.getMargin());

	}
	
	@Test
	public void editPositionCheck() {

		Magazyn magazyn = new Magazyn();
		magazyn.setName(NAME_1);
		magazyn.setAmount(AMOUNT_1);
		magazyn.setMargin(MARGIN_1);
		
		productManager.addNewPosition(magazyn);
		
		Magazyn addedPosition = productManager.findPositionByObject(magazyn);
		
		addedPosition.setName(NAME_2);
		addedPosition.setAmount(AMOUNT_2);
		addedPosition.setMargin(MARGIN_2);
	
		productManager.editPosition(addedPosition);
		
		Magazyn editedPosition = productManager.findPositionByObject(addedPosition);
		
		assertEquals(NAME_2, editedPosition.getName());
		assertEquals(AMOUNT_2, editedPosition.getAmount());
		assertEquals(MARGIN_2, editedPosition.getMargin());
		
		productManager.removePosition(editedPosition);
		
		List<Magazyn> positionsAfterRemove = productManager.getAllPositions();
		
		assertEquals(currentPositions, positionsAfterRemove);
	}
	
	@Test
	public void isPositionWithIdCheck() {
		
		Magazyn magazyn = new Magazyn();
		magazyn.setName(NAME_1);
		magazyn.setAmount(AMOUNT_1);
		magazyn.setMargin(MARGIN_1);
		
		productManager.addNewPosition(magazyn);
		
		assertTrue(productManager.isPositionWithId(magazyn.getId()));
		
		productManager.removePosition(magazyn);
		
		assertFalse(productManager.isPositionWithId(magazyn.getId()));
		
	}
	
		
	@Test
	public void removePositionCheck() {
		
		Magazyn magazyn = new Magazyn();
		magazyn.setName(NAME_1);
		magazyn.setAmount(AMOUNT_1);
		magazyn.setMargin(MARGIN_1);
		
		productManager.addNewPosition(magazyn);
		
		Magazyn addedPosition = productManager.findPositionByObject(magazyn);
		
		productManager.removePosition(addedPosition);
		
		assertFalse(productManager.isPositionWithId(addedPosition.getId()));
		
		List<Magazyn> positionsAfterRemove = productManager.getAllPositions();
		
		assertEquals(currentPositions, positionsAfterRemove);
	}
	
	@Test
	public void findPositionByNameCheck() {

		List<Magazyn> matchingNames = productManager.findPositionByName(PATTERN_1);
		
		Magazyn magazyn = new Magazyn();
		magazyn.setName(PATTERN_1);
		magazyn.setAmount(AMOUNT_1);
		magazyn.setMargin(MARGIN_1);
		
		productManager.addNewPosition(magazyn);
		
		List<Magazyn> matchingNamesAfterAdd = productManager.findPositionByName(PATTERN_1);
		
		for(Magazyn matchingNameAfterAdd : matchingNamesAfterAdd) {
			assertEquals(PATTERN_1, matchingNameAfterAdd.getName());
		}
		
		assertEquals(matchingNames.size() + 1, matchingNamesAfterAdd.size());
	}
	

}
