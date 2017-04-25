package dk.dtu_23.model.data.dao;

import dk.dtu_23.model.ProductBatchDTO;
import dk.dtu_23.model.data.connector.Connector;
import dk.dtu_23.model.data.interfaces.DALException;
import dk.dtu_23.model.data.interfaces.ProductBatchDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLProductBatchDAO implements ProductBatchDAO {

	@Override
	public ProductBatchDTO getProductBatch(int pbId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM productbatch WHERE pb_id = " + pbId + ";");
		try {
			if (!rs.first()) throw new DALException("Product batch with id " + pbId + " does not exist");
			return new ProductBatchDTO(rs.getInt("pb_id"), rs.getInt("status"), rs.getInt("recipe_id"), rs.getString("recipe_id"));
		}
		catch (SQLException e) {throw new DALException(e); }
	}

	@Override
	public List<ProductBatchDTO> getProductBatchList() throws DALException {
		List<ProductBatchDTO> list = new ArrayList<ProductBatchDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM product_batch_list;");
		try
		{
			while (rs.next()) 
			{
				list.add(new ProductBatchDTO(rs.getInt("pb_id"), rs.getInt("status"), rs.getInt("recipe_id"), rs.getString("recipeName")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createProductBatch(ProductBatchDTO productbatch) throws DALException {
		Connector.doUpdate("CALL create_product_batch_from_recipe_id(" + productbatch.getRecipeId() + ");");
	}

	@Override
	public void updateProductBatch(ProductBatchDTO productbatch) throws DALException {
		Connector.doUpdate("CALL update_product_batch_status(" + productbatch.getStatus() + ");");
	}

	public boolean exists(int pbId) throws DALException{
		ResultSet rs = Connector.doQuery("SELECT * FROM productbatch WHERE pb_id = " + pbId + ";");
		try {
			return rs.first();
		}
		catch (SQLException e) {
			return false;
		}
	}
}
