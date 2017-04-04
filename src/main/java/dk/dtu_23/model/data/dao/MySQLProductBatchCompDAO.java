package dk.dtu_23.model.data.dao;

import dk.dtu_23.model.data.connector.Connector;
import dk.dtu_23.model.data.interfaces.DALException;
import dk.dtu_23.model.data.interfaces.ProductBatchCompDAO;
import dk.dtu_23.model.ProductBatchCompDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLProductBatchCompDAO implements ProductBatchCompDAO {

	private List<ProductBatchCompDTO> list = new ArrayList<ProductBatchCompDTO>();
	@Override
	public ProductBatchCompDTO getProductBatchComp(int pbId, int rbId) throws DALException {
		return null;
	}

	@Override
	public List<ProductBatchCompDTO> getProductBatchCompList(int pbId) throws DALException {
		ResultSet rs = Connector.doQuery("CALL get_batch_details_from_id("+pbId+");");
		try
		{
			while (rs.next())
			{
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public List<ProductBatchCompDTO> getProductBatchCompList() throws DALException {
		return this.list;
	}

	@Override
	public void createProductBatchComp(ProductBatchCompDTO productbatchcomponent) throws DALException {
		list.add(productbatchcomponent);
	}

	@Override
	public void updateProductBatchComp(ProductBatchCompDTO productbatchcomponent) throws DALException {
		list.remove(productbatchcomponent);
		list.add(productbatchcomponent);
	}
}