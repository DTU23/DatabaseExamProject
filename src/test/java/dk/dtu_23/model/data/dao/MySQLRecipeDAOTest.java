package dk.dtu_23.model.data.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import dk.dtu_23.model.data.dao.MySQLRecipeDAO;
import dk.dtu_23.model.data.interfaces.DALException;
import dk.dtu_23.model.data.interfaces.ProductBatchCompDAO;
import dk.dtu_23.model.RecipeDTO;

public class MySQLRecipeDAOTest {

	MySQLRecipeDAO recipe = new MySQLRecipeDAO();

	@Test
	public void positiveGetRecipe() {
		try {
			RecipeDTO expected = new RecipeDTO(3, "capricciosa");
			RecipeDTO actual = recipe.getRecipe(3);
			assertEquals(expected, actual);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Test
	public void getRecipeOutOfBounds() {
		try {
			RecipeDTO expected = new RecipeDTO(5, null); // Rettes til fejlmeddelse så testen bliver positiv?
			RecipeDTO actual = recipe.getRecipe(5);
			assertEquals(expected, actual);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Test
	public void getRecipeIntPlusOne() {
		try {
			RecipeDTO expected = new RecipeDTO(Integer.MAX_VALUE+1, null);
			RecipeDTO actual = recipe.getRecipe(Integer.MAX_VALUE+1);
			assertEquals(expected, actual);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void positiveGetListRecipe() {
		try {
			List<RecipeDTO> expected = new ArrayList<RecipeDTO>();
			expected.add(new RecipeDTO(1, "margherita"));
			expected.add(new RecipeDTO(2, "prosciutto"));
			expected.add(new RecipeDTO(3, "capricciosa"));
			List<RecipeDTO> actual = recipe.getRecipeList();
			assertEquals(expected, actual);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void positiveCreateRecipe() {
		try {
			RecipeDTO recipe1 = new RecipeDTO(4, "salami");
			recipe.createRecipe(recipe1);
			RecipeDTO expected = new RecipeDTO(4, "salami");
			RecipeDTO actual = recipe.getRecipe(4);
			assertEquals(expected, actual);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//CreateRecipe() autogenerer ID, så man vil ikke kunne overskrive en anden.  
	@Test
	public void CreateRecipeOnExistingID() {
		try {
			RecipeDTO recipe1 = new RecipeDTO(1, "parmaskinke");
			recipe.createRecipe(recipe1);
			RecipeDTO expected = new RecipeDTO(1, "parmaskinke");
			RecipeDTO actual = recipe.getRecipe(1);
			assertEquals(expected, actual);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}