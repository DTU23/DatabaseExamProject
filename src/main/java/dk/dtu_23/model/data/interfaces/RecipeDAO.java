package dk.dtu_23.model.data.interfaces;

import dk.dtu_23.model.RecipeDTO;

import java.util.List;

public interface RecipeDAO {
	RecipeDTO getRecipe(int recipeId) throws DALException;
	List<RecipeDTO> getRecipeList() throws DALException;
	void createRecipe(RecipeDTO recipe) throws DALException;
}
