package dk.dtu_23.model.data.dao;

import dk.dtu_23.model.ProductBatchCompDTO;
import dk.dtu_23.model.ProductBatchCompOverviewDTO;
import dk.dtu_23.model.ProductBatchCompSupplierDetailsDTO;
import dk.dtu_23.model.data.connector.Connector;
import dk.dtu_23.model.data.interfaces.DALException;
import dk.dtu_23.model.data.interfaces.ProductBatchCompDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLProductBatchCompDAO implements ProductBatchCompDAO {
	@Override
	public ProductBatchCompDTO getProductBatchComp(int pbId, int rbId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * from productbatchcomponent WHERE pb_id="+pbId+" AND rb_id="+rbId+";");
		try {
			if (!rs.first()) throw new DALException("Product batch with id " + pbId + " not found!");
			return new ProductBatchCompDTO (
					rs.getInt("pb_id"),
					rs.getInt("rb_id"),
					rs.getDouble("tara"),
					rs.getDouble("netto"),
					rs.getInt("opr_id")
			);
		}
		catch (SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public void createProductBatchComp(ProductBatchCompDTO productbatchcomponent) throws DALException {
		MySQLProductBatchDAO pbdao = new MySQLProductBatchDAO();
		if(pbdao.exists(productbatchcomponent.getPbId())){
			Connector.doQuery("CALL create_product_batch_component(" + productbatchcomponent.getPbId() + "," + productbatchcomponent.getRbId() + "," + productbatchcomponent.getTara() + "," + productbatchcomponent.getNetto() + "," + productbatchcomponent.getOprId() + ");");
		}else{
			throw new DALException("Invalid ProductBatch ID");
		}
	}

	@Override
	public List<ProductBatchCompDTO> getProductBatchCompList() throws DALException {
		List<ProductBatchCompDTO> list = new ArrayList<ProductBatchCompDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM productbatchcomponent;");
		try
		{
			while (rs.next())
			{
				list.add(new ProductBatchCompDTO(
						rs.getInt("pb_id"),
						rs.getInt("rb_id"),
						rs.getDouble("tara"),
						rs.getDouble("netto"),
						rs.getInt("opr_id")
				));
			}
		}
		catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

	@Override
	public List<ProductBatchCompOverviewDTO> getProductBatchCompOverview() throws DALException {
		List<ProductBatchCompOverviewDTO> list = new ArrayList<ProductBatchCompOverviewDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM product_batch_component_overview;");
		try
		{
			while (rs.next())
			{
				list.add(new ProductBatchCompOverviewDTO(
						rs.getInt("pb_id"),
						rs.getString("recipe_name"),
						rs.getInt("status"),
						rs.getString("produce_name"),
						rs.getDouble("netto"),
						rs.getInt("opr_id")
				));
			}
		}
		catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

	@Override
	public List<ProductBatchCompSupplierDetailsDTO> getSupplierDetailById(int pbId) throws DALException{
		List<ProductBatchCompSupplierDetailsDTO> list = new ArrayList<ProductBatchCompSupplierDetailsDTO>();
		ResultSet rs = Connector.doQuery("CALL get_product_batch_component_supplier_details_by_pb_id("+pbId+");");
		try
		{
			if(rs.first()){
				while (rs.next())
				{
					list.add(new ProductBatchCompSupplierDetailsDTO(
							rs.getInt("rb_id"),
							rs.getString("produce_name"),
							rs.getString("supplier"),
							rs.getDouble("netto"),
							rs.getInt("opr_id")
					));
				}
			}
			else{
				throw new DALException("Invalid ID provided");
			}
		}
		catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}
}
