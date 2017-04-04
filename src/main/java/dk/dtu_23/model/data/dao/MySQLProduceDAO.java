package dk.dtu_23.model.data.dao;

import dk.dtu_23.model.ProduceDTO;
import dk.dtu_23.model.data.connector.Connector;
import dk.dtu_23.model.data.interfaces.DALException;
import dk.dtu_23.model.data.interfaces.ProduceDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLProduceDAO implements ProduceDAO {

	@Override
	public ProduceDTO getProduce(int raavareId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM produce WHERE "+raavareId+"=produce_id;");
		try{
			return new ProduceDTO(rs.getInt("produce_id"), rs.getString("produce_name"), rs.getString("supplier"));
		}catch (SQLException e){ throw new DALException(e); }
	}

	@Override
	public List<ProduceDTO> getProduceList() throws DALException {
		List<ProduceDTO> list = new ArrayList<ProduceDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM produce;");
		try
		{
			while (rs.next())
			{
				list.add(new ProduceDTO(
						rs.getInt("produce_id"),
						rs.getString("produce_name"),
						rs.getString("supplier")
				));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createProduce(ProduceDTO produce) throws DALException {
		Connector.doQuery("INSERT INTO produce(produce_id, produce_name, supplier) VALUES("+produce.getProduceId()+", "+produce.getProduceName()+" ,"+produce.getSupplier()+");");
	}

	@Override
	public void updateProduce(ProduceDTO produce) throws DALException {
		Connector.doQuery("UPDATE produce SET produce_name="+produce.getProduceName()+", supplier="+produce.getSupplier()+"WHERE produce_id="+produce.getProduceId()+";");
	}
}
