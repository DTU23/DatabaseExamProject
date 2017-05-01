package dk.dtu_23.model.data.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dk.dtu_23.model.data.connector.Connector;
import dk.dtu_23.model.OperatorDTO;
import dk.dtu_23.model.OperatorNoPWDTO;
import dk.dtu_23.model.data.interfaces.DALException;

public class MySQLOperatorDAOTest {

	MySQLOperatorDAO opr;

	@Before
	public void setUp() throws Exception {
		new Connector();
		Connector.resetData();
		opr = new MySQLOperatorDAO();
	}

	@After
	public void tearDown() throws Exception {
		Connector.resetData();
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
		assertThat(opr3.toString(), is(equalTo(oprCheck.toString())));
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
		assertThat(OprCheck.toString(), is(equalTo(newOpr.toString())));
	}

	/**
	 * Negative create operator test - ID already used.
	 */
	@Test
	public void testCreateOperatorInvalidID() {
		String errorMsg = null;
		OperatorDTO newOpr = new OperatorDTO(3,"Don Juan","DJ","000000-0000","iloveyou", false, "Operator");
		OperatorDTO OprCheck = null;
		try {
			opr.createOperator(newOpr);
		} catch (DALException e) { errorMsg = e.getMessage(); }
		try {
			OprCheck = opr.getOperator(3);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		assertThat(OprCheck, notNullValue());
		assertThat(newOpr.toString(), is(not(equalTo(OprCheck.toString()))));
		assertThat(errorMsg, notNullValue());
	}

	/**
	 * Negative create operator test - invalid role.
	 */
	@Test
	public void testCreateOperatorInvalidRole() {
		String errorMsg = null;
		String errorMsg2 = null;
		OperatorDTO newOpr = new OperatorDTO(5,"Don Juan","DJ","000000-0000","iloveyou", false, "Admin");
		OperatorDTO OprCheck = null;
		try {
			opr.createOperator(newOpr);
		} catch (DALException e) { errorMsg = e.getMessage(); }
		try {
			OprCheck = opr.getOperator(5);
		} catch (DALException e) { errorMsg2 = e.getMessage(); }
		assertThat(OprCheck, nullValue());
		assertThat(errorMsg, notNullValue());
		assertThat(errorMsg2, notNullValue());
	}

	/**
	 * Positive update operator test.
	 */
	@Test
	public void testUpdateOperator() {
		OperatorDTO newOpr = new OperatorDTO(5,"Don Juan","DJ","000000-0000","iloveyou",false,"Operator");
		OperatorDTO editObject = new OperatorDTO(5,"Don Juan","DoJu","000000-0000","iloveyou",false,"Operator");
		OperatorDTO OprCheckBeforeEdit = null;
		OperatorDTO OprCheckAfterEdit = null;
		try {
			opr.createOperator(newOpr);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		try {
			OprCheckBeforeEdit = opr.getOperator(5);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		assertThat(OprCheckBeforeEdit.toString(), is(equalTo(newOpr.toString())));
		try {
			opr.updateOperator(editObject);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		try {
			OprCheckAfterEdit = opr.getOperator(5);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		assertThat(OprCheckBeforeEdit, notNullValue());
		assertThat(OprCheckAfterEdit, notNullValue());
		assertThat(editObject.toString(), is(equalTo(OprCheckAfterEdit.toString())));
		assertThat(OprCheckBeforeEdit.toString(), is(not(equalTo(OprCheckAfterEdit.toString()))));
	}

	/**
	 * Negative update operator test - trying to change ID.
	 */
	@Test
	public void testUpdateOperatorCantChangeID() {
		String updateErrorMsg = null;
		String getOperatorErrorMsg = null;
		OperatorDTO newOpr = new OperatorDTO(5,"Don Juan","DJ","000000-0000","iloveyou", false, "Operator");
		OperatorDTO editedID = new OperatorDTO(6,"Don Juan","DJ","000000-0000","iloveyou", false, "Operator");
		OperatorDTO OprCheckBeforeEdit = null;
		OperatorDTO Opr5AfterEdit = null;
		OperatorDTO Opr6AfterEdit = null;
		try {
			opr.createOperator(newOpr);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		try {
			OprCheckBeforeEdit = opr.getOperator(5);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		assertThat(OprCheckBeforeEdit.toString(), is(equalTo(newOpr.toString())));
		try {
			opr.updateOperator(editedID);
		} catch (DALException e) { updateErrorMsg = e.getMessage(); }
		try {
			Opr5AfterEdit = opr.getOperator(5);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		try {
			Opr6AfterEdit = opr.getOperator(6);
		} catch (DALException e) { getOperatorErrorMsg = e.getMessage(); }
		assertThat(OprCheckBeforeEdit, notNullValue());
		assertThat(Opr5AfterEdit, notNullValue());
		assertThat(Opr6AfterEdit, nullValue());
		assertThat(Opr5AfterEdit.toString(), is(equalTo(OprCheckBeforeEdit.toString())));
		assertThat(updateErrorMsg, notNullValue());
		assertThat(getOperatorErrorMsg, notNullValue());
	}

	/**
	 * Negative update operator test - invalid role.
	 */
	@Test
	public void testUpdateOperatorInvalidRole() {
		String errorMsg = null;
		OperatorDTO newOpr = new OperatorDTO(5,"Don Juan","DJ","000000-0000","iloveyou", false, "Operator");
		OperatorDTO editedRole = new OperatorDTO(5,"Don Juan","DJ","000000-0000","iloveyou", false, "CEO");
		OperatorDTO OprCheckBeforeEdit = null;
		OperatorDTO OprCheckAfterEdit = null;
		try {
			opr.createOperator(newOpr);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		try {
			OprCheckBeforeEdit = opr.getOperator(5);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		assertThat(OprCheckBeforeEdit.toString(), is(equalTo(newOpr.toString())));
		try {
			opr.updateOperator(editedRole);
		} catch (DALException e) { errorMsg = e.getMessage(); }
		try {
			OprCheckAfterEdit = opr.getOperator(5);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		assertThat(OprCheckBeforeEdit, notNullValue());
		assertThat(OprCheckAfterEdit, notNullValue());
		assertThat(OprCheckAfterEdit.toString(), is(not(equalTo(editedRole.toString()))));
		assertThat(OprCheckAfterEdit.toString(), is(equalTo(OprCheckBeforeEdit.toString())));
		assertThat(errorMsg, notNullValue());
	}

	/**
	 * Positive get operator list test.
	 */
	@Test
	public void testGetOperatorList() {
		List<OperatorNoPWDTO> list = null;
		try {
			list = opr.getOperatorList();
		} catch (DALException e) { System.out.println(e.getMessage()); }
		assertThat(list, notNullValue());
		assertThat(list.get(0).getOprId(), notNullValue());
		assertThat(list.get(0).getOprName(), notNullValue());
		assertThat(list.get(0).getIni(), notNullValue());
		assertThat(list.get(0).getAdmin(), notNullValue());
		assertThat(list.get(0).getRole(), notNullValue());
	}
}
