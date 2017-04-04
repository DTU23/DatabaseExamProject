package dk.dtu_23.model.data.interfaces;

import dk.dtu_23.model.OperatorDTO;

import java.util.List;

public interface OperatorDAO {
	OperatorDTO getOperator(int oprId) throws DALException;
	List<OperatorDTO> getOperatorList() throws DALException;
	void createOperator(OperatorDTO opr) throws DALException;
	void updateOperator(OperatorDTO opr) throws DALException;
	String getOperatorNameFromID(int id) throws DALException;
}
