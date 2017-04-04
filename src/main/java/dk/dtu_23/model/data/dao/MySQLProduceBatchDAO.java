package dk.dtu_23.model.data.dao;

import dk.dtu_23.model.ProduceDTO;
import dk.dtu_23.model.data.connector.Connector;
import dk.dtu_23.model.data.interfaces.DALException;
import dk.dtu_23.model.data.interfaces.ProduceBatchDAO;
import dk.dtu_23.model.ProduceBatchDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLProduceBatchDAO implements ProduceBatchDAO {

	@Override
	public ProduceBatchDTO getProduceBatch(int rbId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM produce_batch_list WHERE rb_id="+rbId+";");
		try{
			return new ProduceBatchDTO(
					rs.getInt("rb_id"),
					rs.getString("produce_name"),
					rs.getString("supplier"),
					rs.getDouble("amount")
			);
		}catch (SQLException e){ throw new DALException(e); }
	}

	@Override
	public List<ProduceBatchDTO> getProduceBatchList() throws DALException {
		List<ProduceBatchDTO> list = new ArrayList<ProduceBatchDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM produce_batch_list;");
		try
		{
			while (rs.next())
			{
				list.add(new ProduceBatchDTO(
						rs.getInt("rb_id"),
						rs.getString("produce_name"),
						rs.getString("supplier"),
						rs.getDouble("amount")
				));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createProduceBatch(ProduceDTO produce, double amount) throws DALException {
		Connector.doQuery("CALL create_produce_batch_from_produce_id("+produce.getProduceId()+", "+amount+");");
	}

	@Override
	public void updateProduceBatch(ProduceBatchDTO producebatch, double amount) throws DALException {
		Connector.doQuery("CALL update_produce_batch_by_id("+producebatch.getId()+", "+amount+");");
	}
}