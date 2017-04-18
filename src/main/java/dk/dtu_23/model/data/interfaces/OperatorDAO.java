package dk.dtu_23.model.data.interfaces;

import dk.dtu_23.model.OperatorDTO;
import dk.dtu_23.model.OperatorNoPWDTO;

import java.util.List;

public interface OperatorDAO {
	OperatorDTO getOperator(int oprId) throws DALException;
	List<OperatorNoPWDTO> getOperatorList() throws DALException;
	void createOperator(OperatorDTO opr) throws DALException;
	void updateOperator(OperatorDTO opr) throws DALException;
}
