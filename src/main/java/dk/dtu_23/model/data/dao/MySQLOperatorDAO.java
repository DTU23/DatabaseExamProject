package dk.dtu_23.model.data.dao;

import dk.dtu_23.model.OperatorDTO;
import dk.dtu_23.model.OperatorNoPWDTO;
import dk.dtu_23.model.data.connector.Connector;
import dk.dtu_23.model.data.interfaces.DALException;
import dk.dtu_23.model.data.interfaces.OperatorDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLOperatorDAO implements OperatorDAO {

	@Override
	public OperatorDTO getOperator(int oprId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM operator WHERE opr_id = " + oprId + ";");
		try
		{
			if (!rs.first()) throw new DALException("Operator with id " + oprId + " does not exist");
			return new OperatorDTO (rs.getInt("opr_id"), rs.getString("opr_name"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password"), rs.getBoolean("admin"), rs.getString("role"));
		}
		catch (SQLException e) {throw new DALException(e); }
	}

	@Override
	public void createOperator(OperatorDTO opr) throws DALException {
		if(Connector.doUpdate("CALL create_operator(" + opr.getOprId() + ",'" + opr.getOprName() + "','" + opr.getIni() + "','" + 
				opr.getCpr() + "','" + opr.getPassword() + "'," + opr.getAdmin() + ",'" + opr.getRole() + "');") == 0)
		{
			throw new DALException("No rows affected");
		}
	}

	@Override
	public void updateOperator(OperatorDTO opr) throws DALException {
		if(Connector.doUpdate("CALL update_operator(" + opr.getOprId() + ",'" + opr.getOprName() + "','" + opr.getIni() + "','" + 
				opr.getCpr() + "','" + opr.getPassword() + "'," + opr.getAdmin() + ",'" + opr.getRole() + "');") == 0)
		{
			throw new DALException("No rows affected");
		}
	}

	@Override
	public List<OperatorNoPWDTO> getOperatorList() throws DALException {
		List<OperatorNoPWDTO> list = new ArrayList<OperatorNoPWDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM operator_list;");
		try
		{
			while (rs.next()) 
			{
				list.add(new OperatorNoPWDTO(rs.getInt("opr_id"), rs.getString("opr_name"), rs.getString("ini"), rs.getString("cpr"), rs.getBoolean("admin"), rs.getString("role")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}
}