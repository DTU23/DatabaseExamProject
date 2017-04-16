package dk.dtu_23.model.data.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import dk.dtu_23.model.data.connector.Connector;
import dk.dtu_23.model.data.dao.MySQLRecipeDAO;
import dk.dtu_23.model.data.interfaces.DALException;
import dk.dtu_23.model.data.interfaces.ProductBatchCompDAO;
import dk.dtu_23.model.RecipeCompDTO;
import dk.dtu_23.model.RecipeDTO;

public class MySQLRecipeDAOTest {

	MySQLRecipeDAO recipe;

	@Before
	public void setUp() throws Exception {
		//TODO mangler noget reset database
		try { new Connector(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
		recipe = new MySQLRecipeDAO();
	}

	@After
	public void tearDown() throws Exception {
		//TODO mangler noget reset database
		recipe = null;
	}

	/**
	 * Positive get Recipe test
	 */

	@Test
	public void positiveGetRecipe() {
		RecipeDTO actual = null;
		RecipeDTO expected = new RecipeDTO(3, "capricciosa");
		try {
			actual = recipe.getRecipe(3);	
		} catch (DALException e) {	System.out.println(e.getMessage());  }
		assertEquals(expected, actual);
	}

	/**
	 * Negative get Recipe test
	 */

	@Test
	public void getRecipeByIDThatDoesntExist() {
		String errorMsg = null;
		try {
			recipe.getRecipe(5);
		} catch (DALException e) {	errorMsg = e.getMessage();	}
		assertEquals(errorMsg, "Recipe with id 5 does not exist");

	}

	/**
	 * Positive get recipe list test
	 */

	@Test
	public void positiveGetListRecipe() {
		List<RecipeDTO> actual = null;
		try {
			actual = recipe.getRecipeList();
		} catch (DALException e) {	System.out.println(e.getMessage());}
		assertTrue(actual != null);

	}

	/**
	 * Positive create recipe test
	 */
	@Test
	public void positiveCreateRecipe() {
		RecipeDTO expected = new RecipeDTO(4, "salami");
		RecipeDTO actual = null;
		try {
			recipe.createRecipe(expected);
			actual = recipe.getRecipe(4);
		} catch (DALException e) {	System.out.println(e.getMessage());}
		assertEquals(expected, actual);
	}

	/**
	 * negative test for create recipe. 
	 * Auto-generates an ID so cant create on existing.  
	 */
	@Test
	public void createRecipeOnExistingID() {
		RecipeDTO expected = new RecipeDTO(1, "parmaskinke");
		RecipeDTO actual = null;
		try {
			recipe.createRecipe(expected);
			actual = recipe.getRecipe(1);
		}
		catch (DALException e) {	System.out.println(e.getMessage());}
		assertEquals(expected, actual);
	}
	
	/**
	 * get recipe with invalid input
	 */
	
	@Test
	public void getRecipeCompWithInvalidID() {
		String errorMsg = null;
		try {
			recipe.getRecipe(0);
		} catch (DALException e) { errorMsg = e.getMessage(); }
		assertEquals(errorMsg, "Recipe with id 0 does not exist");
	}
}