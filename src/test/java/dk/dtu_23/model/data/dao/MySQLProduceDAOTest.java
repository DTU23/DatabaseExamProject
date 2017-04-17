package dk.dtu_23.model.data.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dk.dtu_23.model.ProduceDTO;
import dk.dtu_23.model.ProduceOverviewDTO;
import dk.dtu_23.model.data.connector.Connector;
import dk.dtu_23.model.data.interfaces.DALException;

public class MySQLProduceDAOTest {
	
	MySQLProduceDAO produce;
	
    @Before
    public void setUp() throws Exception {
		try { new Connector(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
		produce = new MySQLProduceDAO();
    }

    @After
    public void tearDown() {
    	produce = null;
    }
    
    /**
     * Positive test. Get any produce by ID.
     */
    @Test
    public void testGetProduceByID() {
    	ProduceDTO actual = null;
		ProduceDTO expected = new ProduceDTO(2, "tomat", "Knoor");
		
		try {
			actual = produce.getProduce(2);
		} catch (DALException e) {
			System.out.println(e.getMessage());
		}
		
		assertThat(actual, is(equalTo(expected)));
    }

    /**
     * Negative test. Try to get produce with non-existing ID.
     */
    @Test
    public void testGetProduceByIDThatDoesntExist() {
    	String output = null;
    	ProduceDTO actual = null;
    	
		try {
			actual = produce.getProduce(10);
		} catch (DALException e) {
			output = e.getMessage();
			System.out.println(output);
		}
		
		assertThat(output, notNullValue());
		assertThat(output, is(equalTo("Produce with id 10 does not exist")));
		assertThat(actual, nullValue());
    }

    /**
     * Positive test. Get all produces from the produce list.
     */
    @Test
    public void testGetProduceList() {
    	List<ProduceDTO> produceList = null;
    	// Last row in our table, produce
    	ProduceDTO produceDTO = new ProduceDTO(7, "champignon", "Igloo Frostvarer");
    	
    	try {
    		produceList = produce.getProduceList();
    	} catch(DALException e) {
    		System.out.println(e.getMessage());
    	}
    	
    	// Print out the produce list to console
    	for(int i = 0; i < produceList.size(); i++)
    		System.out.println(produceList.get(i));
    	
    	assertThat(produceList, notNullValue());
    	assertThat(produceList.get(6), is(equalTo(produceDTO)));
    }
    
    /**
     * Positive test. Create a produce.
     */
    @Test
    public void testCreateProduce() {
        ProduceDTO expected = new ProduceDTO(8, "smør", "Kærgården");
        ProduceDTO actual = null;
        
        try {
        	produce.createProduce(expected);
        	actual = produce.getProduce(8);
        } catch (DALException e) {
        	System.out.println(e.getMessage());
        }
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    /**
     * Negative test. Try to overwrite already created produce
     */
    @Test
    public void testCreateProduceWithAlreadyUsedID() {
        ProduceDTO newProduce = new ProduceDTO(1, "vand", "Aqua");
        ProduceDTO actual = null;
        
        try {
        	produce.createProduce(newProduce);
        	actual = produce.getProduce(1);
        } catch (DALException e) {
        	System.out.println(e.getMessage());
        }
        
        assertThat(actual, is(not(equalTo(newProduce))));
    }

    /**
     * Positive test. Update produce
     */
    @Test
    public void testUpdateProduce() {
    	ProduceDTO expected = new ProduceDTO(4, "tomat", "Kims");
        ProduceDTO actual = null;
        
        try {
        	produce.updateProduce(expected);
        	actual = produce.getProduce(4);
        } catch (DALException e) {
        	System.out.println(e.getMessage());
        }
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    /**
     * Positive test. Get produce overview
     */
    @Test
    public void testGetProduceOverview() {
    	List<ProduceOverviewDTO> produceOverview = null;
    	// First row in our view, produce_overview
    	ProduceOverviewDTO produceOverviewDTO = new ProduceOverviewDTO(7, "champignon", 100);
    	
    	try {
    		produceOverview = produce.getProduceOverview();
    	} catch(DALException e) {
    		System.out.println(e.getMessage());
    	}
    	
    	// Print out the produce overview to console
    	for(int i = 0; i < produceOverview.size(); i++)
    		System.out.println(produceOverview.get(i));
    	
    	assertThat(produceOverview, notNullValue());
    	assertThat(produceOverview.get(0), is(equalTo(produceOverviewDTO)));
    }

}