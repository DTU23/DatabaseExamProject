package dk.dtu_23.model.data.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dk.dtu_23.model.data.dao.MySQLRecipeCompDAO;
import dk.dtu_23.model.data.interfaces.DALException;
import dk.dtu_23.model.RecipeCompDTO;
import dk.dtu_23.model.RecipeDTO;

public class MySQLRecipeCompDaoTest {


	MySQLRecipeCompDAO recipeComp = new MySQLRecipeCompDAO();

	@Test
	public void positiveGetRecipeComp() {
		try {
			RecipeCompDTO expected = new RecipeCompDTO(1, 1, 10, 0.1);
			RecipeCompDTO actual = recipeComp.getRecipeComp(1, 1);
			assertEquals(expected, actual);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	@Test
	public void getRecipeOutOfBounds() {
		try {
			RecipeCompDTO expected = new RecipeCompDTO(4, 1, 0, 0.1); // Rettes til fejlmeddelse så den bliver positiv?
			RecipeCompDTO actual = recipeComp.getRecipeComp(4,1);
			assertEquals(expected, actual);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	@Test
	public void getRecipeIntPlusOne() {
		try {
			RecipeCompDTO expected = new RecipeCompDTO(Integer.MAX_VALUE+1, 1, 0, 0.1 );
			RecipeCompDTO actual = recipeComp.getRecipeComp(Integer.MAX_VALUE+1, 1);
			assertEquals(expected, actual);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		@Test
		public void positiveGetListRecipeWithID() {
			try {
				List<RecipeCompDTO> expected = new ArrayList<RecipeCompDTO>();
				expected.add(new RecipeCompDTO(1, 1, 10, 0.1));
				expected.add(new RecipeCompDTO(1, 2, 2, 0.1));
				expected.add(new RecipeCompDTO(1, 5, 2, 0.1));
				List<RecipeCompDTO> actual = recipeComp.getRecipeCompList(1);
				assertEquals(expected, actual);
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	
	@Test
		public void positiveGetListRecipe() {
			try {
				List<RecipeCompDTO> expected = new ArrayList<RecipeCompDTO>();
				expected.add(new RecipeCompDTO(1, 1, 10, 0.1));
				expected.add(new RecipeCompDTO(1, 2, 2, 0.1));
				expected.add(new RecipeCompDTO(1, 5, 2, 0.1));
				expected.add(new RecipeCompDTO(2, 1, 10, 0.1));
				expected.add(new RecipeCompDTO(2, 3, 2, 0.1));
				expected.add(new RecipeCompDTO(2, 5, 1.5, 0.1));
				expected.add(new RecipeCompDTO(2, 6, 1.5, 0.1));
				expected.add(new RecipeCompDTO(3, 1, 10, 0.1));
				expected.add(new RecipeCompDTO(3, 4, 1.5, 0.1));
				expected.add(new RecipeCompDTO(3, 5, 1.5, 0.1));
				expected.add(new RecipeCompDTO(3, 6, 1, 0.1));
				expected.add(new RecipeCompDTO(3, 7, 1, 0.1));
				List<RecipeCompDTO> actual = recipeComp.getRecipeCompList();
				assertEquals(expected, actual);
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	@Test
	public void positiveCreateRecipeComp() {
		try {
			RecipeCompDTO recipecomp1 = new RecipeCompDTO(4, 1, 1.5, 0.1);
			recipeComp.createRecipeComp(recipecomp1);
			RecipeCompDTO expected = new RecipeCompDTO(4, 1, 1.5, 0.1);
			RecipeCompDTO actual = recipeComp.getRecipeComp(4, 1);
			assertEquals(expected, actual);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}