package dk.dtu_23.model.data.dao;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dk.dtu_23.model.data.connector.Connector;
import dk.dtu_23.model.data.dao.MySQLRecipeCompDAO;
import dk.dtu_23.model.data.interfaces.DALException;
import dk.dtu_23.model.RecipeCompDTO;
import dk.dtu_23.model.RecipeDTO;

public class MySQLRecipeCompDaoTest {

	MySQLRecipeCompDAO recipeComp;

	@Before
	public void setUp() throws Exception {
		new Connector();
		Connector.resetData();
		recipeComp = new MySQLRecipeCompDAO();
	}

	@After
	public void tearDown() throws Exception {
		Connector.resetData();
		recipeComp = null;
	}

/**
 * positive get recipe comp test
 */
	
	@Test
	public void positiveGetRecipeComp() {
		RecipeCompDTO actual = null;
		RecipeCompDTO expected = new RecipeCompDTO(1, 1, 10, 0.1);
		try {
			actual = recipeComp.getRecipeComp(1, 1);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		assertThat(expected.toString(), is(equalTo(actual.toString())));
	}
	
	/**
	 * negative get recipe comp test
	 */
	
	@Test
	public void getRecipeCompByIDThatDoesntExist() {
		String errorMsg = null;
		try {
			recipeComp.getRecipeComp(4,1);
		} catch (DALException e) { errorMsg = e.getMessage(); }
		assertThat(errorMsg, is(equalTo("Recipecomponent with recipeid 4 and produceid 1 does not exist")));
	}

	/**
	 * positive get recipe comp list with ID 
	 */
	
	@Test
	public void positiveGetListRecipeCompWithID() {
		List<RecipeCompDTO> actual = null;
		try {
			actual = recipeComp.getRecipeCompList(1);
			
		} catch (DALException e) { System.out.println(e.getMessage()); }
		assertThat(actual.get(1).getRecipeId(), nullValue());
		assertThat(actual.get(1).getProduceId(), nullValue());
		assertThat(actual.get(1).getNomNetto(), nullValue());
		assertThat(actual.get(1).getTolerance(), nullValue());
	}

	/**
	 * positive get list recipe comp without ID test 
	 */
	
	@Test
	public void positiveGetListRecipeComp() {
		List<RecipeCompDTO> actual = null;
		try {
			actual = recipeComp.getRecipeCompList();
		} catch (DALException e) { System.out.println(e.getMessage()); }
		assertThat(actual.get(0).getRecipeId(), notNullValue());
		assertThat(actual.get(0).getProduceId(), notNullValue());
		assertThat(actual.get(0).getNomNetto(), notNullValue());
		assertThat(actual.get(0).getTolerance(), notNullValue());
	}

	/**
	 * Positive create recipe comp test
	 */
	
	@Test
	public void positiveCreateRecipeComp() {
		RecipeCompDTO expected = new RecipeCompDTO(4, 1, 1.5, 0.1);
		RecipeCompDTO actual = null;
		try {
			recipeComp.createRecipeComp(expected);
			actual = recipeComp.getRecipeComp(4, 1);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		assertThat(expected.toString(), is(equalTo(actual.toString())));
	}
	
	/**
	 * get recipe comp with invalid input
	 */
	
	@Test
	public void getRecipeCompWithInvalidID() {
		String errorMsg = null;
		try {
			recipeComp.getRecipeComp(0, 1);
		} catch (DALException e) { errorMsg = e.getMessage(); }
		assertEquals(errorMsg, is(equalTo("Recipecomponent with recipeid 0 and produceid 1 does not exist")));
	}
	
}
