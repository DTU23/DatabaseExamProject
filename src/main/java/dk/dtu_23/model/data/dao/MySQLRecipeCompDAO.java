package dk.dtu_23.model.data.dao;

import dk.dtu_23.model.RecipeCompDTO;
import dk.dtu_23.model.data.connector.Connector;
import dk.dtu_23.model.data.interfaces.DALException;
import dk.dtu_23.model.data.interfaces.RecipeCompDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLRecipeCompDAO implements RecipeCompDAO {

	@Override
	public RecipeCompDTO getRecipeComp(int recipeId, int produceId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM recipecomponent WHERE recipe_id=" + recipeId + " AND produce_id=" + produceId + ";");
		try {
			if (!rs.first())
				throw new DALException("Recipecomponent with recipeid " + recipeId + " and produceid " + produceId + " does not exist");
			return new RecipeCompDTO(rs.getInt("recipe_id"), rs.getInt("produce_id"), rs.getDouble("nom_netto"), rs.getDouble("tolerance"));
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public List<RecipeCompDTO> getRecipeCompList(int recipeId) throws DALException {
		List<RecipeCompDTO> list = new ArrayList<RecipeCompDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM recipecomponent WHERE recipe_id=" + recipeId + ";");
		
		try {
			while (rs.next()) {
				list.add(new RecipeCompDTO(
						rs.getInt("recipe_id"),
						rs.getInt("produce_id"),
						rs.getDouble("nom_netto"),
						rs.getDouble("tolerance")));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		
		return list;
	}

	@Override
	public List<RecipeCompDTO> getRecipeCompList() throws DALException {
		List<RecipeCompDTO> list = new ArrayList<RecipeCompDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM recipecomponent;");
		
		try {
			while (rs.next()) {
				list.add(new RecipeCompDTO(
						rs.getInt("recipe_id"),
						rs.getInt("produce_id"),
						rs.getDouble("nom_netto"),
						rs.getDouble("tolerance")));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		
		return list;
	}

	@Override
	public void createRecipeComp(RecipeCompDTO recipecomponent) throws DALException {
		Connector.doQuery("CALL create_recipe_component(" + recipecomponent.getRecipeId() + ", " + recipecomponent.getProduceId() + ", " + recipecomponent.getNomNetto() + ", " + recipecomponent.getTolerance() + ");");
	}
}
