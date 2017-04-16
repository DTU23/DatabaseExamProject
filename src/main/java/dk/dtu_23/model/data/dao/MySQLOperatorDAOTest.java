package dk.dtu_23.model.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dk.dtu_23.model.OperatorDTO;
import dk.dtu_23.model.data.connector.Connector;
import dk.dtu_23.model.data.interfaces.DALException;

public class MySQLOperatorDAOTest {

	MySQLOperatorDAO opr;

	@Before
	public void setUp() throws Exception {
		//TODO mangler noget reset database
		try { new Connector(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
		opr = new MySQLOperatorDAO();
	}

	@After
	public void tearDown() throws Exception {
		//TODO mangler noget reset database
		opr = null;
	}

	/**
	 * Positive get operator test
	 */
	@Test
	public void testGetOperatorByID() {
		OperatorDTO opr3 = null;
		try {
			opr3 = opr.getOperator(3);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		assertEquals(opr3, new OperatorDTO(3, "Luigi C", "LC", "090990-9009", "jEfm5aQ", false, "Operator"));
	}

	/**
	 * Negative get operator test
	 */
	@Test
	public void testGetOperatorByIDThatDoesntExist() {
		String errorMsg = null;
		try {
			opr.getOperator(7);
		} catch (DALException e) {errorMsg = e.getMessage(); }
		assertEquals(errorMsg, "Operator with id 7 does not exist");
	}

	/**
	 * Positive create operator test
	 */
	@Test
	public void testCreateOperator() {
		OperatorDTO newOpr = new OperatorDTO(5,"Don Juan","DJ","000000-0000","iloveyou", false, null);
		OperatorDTO OprCheck = null;
		try {
			opr.createOperator(newOpr);
			OprCheck = opr.getOperator(5);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		assertEquals(newOpr, OprCheck);
	}

	/**
	 * Negative create operator test
	 */
	@Test
	public void testCreateOperatorInvalidInput() {
		//TODO
		OperatorDTO newOpr = new OperatorDTO(6,"Don Juan","DJ","000000-0000","iloveyou", false, null);
		OperatorDTO OprCheck = null;
		try {
			opr.createOperator(newOpr);
			OprCheck = opr.getOperator(5);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		assertEquals(newOpr, OprCheck);
	}
	
	/**
	 * Positive update operator test
	 */
	@Test
	public void testUpdateOperator() {
		OperatorDTO editedInitials = new OperatorDTO(5,"Don Juan","DoJu","000000-0000","iloveyou", false, null);
		OperatorDTO OprCheck = null;
		try {
			opr.updateOperator(editedInitials);
			opr.getOperator(5);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		assertEquals(editedInitials, OprCheck);
	}

	/**
	 * Negative update operator test
	 */
	@Test
	public void testUpdateOperatorInvalidInput() {
		//TODO
		OperatorDTO editedInitials = new OperatorDTO(5,"Don Juan","DoJu","000000-0000","iloveyou", false, null);
		OperatorDTO OprCheck = null;
		try {
			opr.updateOperator(editedInitials);
			opr.getOperator(5);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		assertEquals(editedInitials, OprCheck);
	}

	/**
	 * Positive get operator list test
	 */
	@Test
	public void testGetOperatorList() {
		List<OperatorDTO> list = null;
		try {
			list = opr.getOperatorList();
		} catch (DALException e) { System.out.println(e.getMessage()); }
		assertTrue(list != null);
	}

}
