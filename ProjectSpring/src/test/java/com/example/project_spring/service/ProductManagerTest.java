package com.example.project_spring.service;

import static org.junit.Assert.assertEquals;

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
	
	private final List<Magazyn> currentPositions = new ArrayList<Magazyn>();
	
 	@Before
    public void storeCurrentDatabase() {

        List<Magazyn> positions = productManager.getAllPositions();
		for(Magazyn position : positions) {
			currentPositions.add(position);
		}
        
    }

    @After
    public void cleanUpDatabase() {

    	List<Magazyn> afterTestPositions = productManager.getAllPositions();

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
}
