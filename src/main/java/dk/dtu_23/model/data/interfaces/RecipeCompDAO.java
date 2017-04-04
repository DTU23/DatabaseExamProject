package dk.dtu_23.model.data.interfaces;

import dk.dtu_23.model.RecipeCompDTO;

import java.util.List;

public interface RecipeCompDAO {
	RecipeCompDTO getRecipeComp(int recipeId, int produceId) throws DALException;
	List<RecipeCompDTO> getRecipeCompList(int recipeId) throws DALException;
	List<RecipeCompDTO> getRecipeCompList() throws DALException;
	void createRecipeComp(RecipeCompDTO recipecomponent) throws DALException;
}
