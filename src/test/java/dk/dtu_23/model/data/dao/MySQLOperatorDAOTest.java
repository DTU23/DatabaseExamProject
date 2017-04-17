package dk.dtu_23.model.data.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dk.dtu_23.model.data.connector.Connector;
import dk.dtu_23.model.OperatorDTO;
import dk.dtu_23.model.data.interfaces.DALException;

public class MySQLOperatorDAOTest {

	MySQLOperatorDAO opr;

	@Before
	public void setUp() throws Exception {
		try { new Connector(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
		//TODO mangler noget reset database
		opr = new MySQLOperatorDAO();
	}

	@After
	public void tearDown() throws Exception {
		//TODO mangler noget reset database
		opr = null;
	}

	/**
	 * Positive get operator test.
	 */
	@Test
	public void testGetOperatorByID() {
		OperatorDTO opr3 = null;
		OperatorDTO oprCheck = new OperatorDTO(3, "Luigi C", "LC", "090990-9009", "jEfm5aQ", false, "Operator");
		try {
			opr3 = opr.getOperator(3);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		assertThat(opr3, equalTo(oprCheck));
	}

	/**
	 * Negative get operator test - non existing ID.
	 */
	@Test
	public void testGetOperatorByIDThatDoesntExist() {
		String errorMsg = null;
		try {
			opr.getOperator(6);
		} catch (DALException e) { errorMsg = e.getMessage(); }
		assertThat(errorMsg, is(equalTo("Operator with id 6 does not exist")));
	}

	/**
	 * Positive create operator test.
	 */
	@Test
	public void testCreateOperator() {
		OperatorDTO newOpr = new OperatorDTO(5,"Don Juan","DJ","000000-0000","iloveyou", false, "Operator");
		OperatorDTO OprCheck = null;
		try {
			opr.createOperator(newOpr);
			OprCheck = opr.getOperator(5);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		assertThat(newOpr, is(equalTo(OprCheck)));
	}

	/**
	 * Negative create operator test - ID already used.
	 */
	@Test
	public void testCreateOperatorInvalidID() {
		String errorMsg = null;
		OperatorDTO newOpr = new OperatorDTO(3,"Don Juan","DJ","000000-0000","iloveyou", false, null);
		OperatorDTO OprCheck = null;
		try {
			opr.createOperator(newOpr);
		} catch (DALException e) { errorMsg = e.getMessage(); }
		try {
			OprCheck = opr.getOperator(3);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		assertThat(newOpr, is(not(equalTo(OprCheck))));
		assertThat(errorMsg, is(not(equalTo(null))));
	}

	/**
	 * Negative create operator test - invalid role.
	 */
	@Test
	public void testCreateOperatorInvalidRole() {
		String errorMsg = null;
		OperatorDTO newOpr = new OperatorDTO(5,"Don Juan","DJ","000000-0000","iloveyou", false, "Admin");
		OperatorDTO OprCheck = null;
		try {
			opr.createOperator(newOpr);
		} catch (DALException e) { errorMsg = e.getMessage(); }
		try {
			OprCheck = opr.getOperator(5);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		assertThat(newOpr, is(not(equalTo(OprCheck))));
		OprCheck.setRole("None");
		assertThat(newOpr, is(not(equalTo(OprCheck))));
		assertThat(errorMsg, is(not(equalTo(null))));
	}

	/**
	 * Positive update operator test.
	 */
	@Test
	public void testUpdateOperator() {
		OperatorDTO newOpr = new OperatorDTO(5,"Don Juan","DJ","000000-0000","iloveyou", false, null);
		OperatorDTO editedInitials = new OperatorDTO(5,"Don Juan","DoJu","000000-0000","iloveyou", false, null);
		OperatorDTO OprCheckBeforeEdit = null;
		OperatorDTO OprCheckAfterEdit = null;
		try {
			opr.createOperator(newOpr);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		try {
			OprCheckBeforeEdit = opr.getOperator(5);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		assertThat(newOpr, is(equalTo(OprCheckBeforeEdit)));
		try {
			opr.updateOperator(editedInitials);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		try {
			OprCheckAfterEdit = opr.getOperator(5);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		assertThat(editedInitials, is(equalTo(OprCheckAfterEdit)));
		assertThat(OprCheckBeforeEdit, is(not(equalTo(OprCheckAfterEdit))));
	}

	/**
	 * Negative update operator test - trying to change ID.
	 */
	@Test
	public void testUpdateOperatorCantChangeID() {
		String errorMsg = null;
		OperatorDTO newOpr = new OperatorDTO(5,"Don Juan","DJ","000000-0000","iloveyou", false, null);
		OperatorDTO editedID = new OperatorDTO(6,"Don Juan","DJ","000000-0000","iloveyou", false, null);
		OperatorDTO OprCheckBeforeEdit = null;
		OperatorDTO Opr5AfterEdit = null;
		OperatorDTO Opr6AfterEdit = null;
		try {
			opr.createOperator(newOpr);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		try {
			OprCheckBeforeEdit = opr.getOperator(5);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		assertThat(newOpr, is(equalTo(OprCheckBeforeEdit)));
		try {
			opr.updateOperator(editedID);
		} catch (DALException e) { errorMsg = e.getMessage(); }
		try {
			Opr5AfterEdit = opr.getOperator(5);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		try {
			Opr6AfterEdit = opr.getOperator(6);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		assertThat(editedID, is(not(equalTo(Opr6AfterEdit))));
		assertThat(Opr5AfterEdit, is(equalTo(OprCheckBeforeEdit)));
		assertThat(errorMsg, is(not(equalTo(null))));
	}

	/**
	 * Negative update operator test - invalid role.
	 */
	@Test
	public void testUpdateOperatorInvalidRole() {
		String errorMsg = null;
		OperatorDTO newOpr = new OperatorDTO(5,"Don Juan","DJ","000000-0000","iloveyou", false, null);
		OperatorDTO editedRole = new OperatorDTO(5,"Don Juan","DJ","000000-0000","iloveyou", false, "CEO");
		OperatorDTO OprCheckBeforeEdit = null;
		OperatorDTO OprCheckAfterEdit = null;
		try {
			opr.createOperator(newOpr);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		try {
			OprCheckBeforeEdit = opr.getOperator(5);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		assertThat(newOpr, is(equalTo(OprCheckBeforeEdit)));
		try {
			opr.updateOperator(editedRole);
		} catch (DALException e) { errorMsg = e.getMessage(); }
		try {
			OprCheckAfterEdit = opr.getOperator(5);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		assertThat(editedRole, is(not(equalTo(OprCheckAfterEdit))));
		assertThat(OprCheckAfterEdit, is(equalTo(OprCheckBeforeEdit)));
		assertThat(errorMsg, is(not(equalTo(null))));
	}

	/**
	 * Positive get operator list test.
	 */
	@Test
	public void testGetOperatorList() {
		List<OperatorDTO> list = null;
		try {
			list = opr.getOperatorList();
		} catch (DALException e) { System.out.println(e.getMessage()); }
		assertThat(list, notNullValue());
	}
}
