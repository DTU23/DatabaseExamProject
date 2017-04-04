package dk.dtu_23.model.data.interfaces;

import dk.dtu_23.model.ProduceBatchDTO;

import java.util.List;

public interface ProduceBatchDAO {
	ProduceBatchDTO getRaavareBatch(int rbId) throws DALException;
	List<ProduceBatchDTO> getRaavareBatchList() throws DALException;
	List<ProduceBatchDTO> getRaavareBatchList(int raavareId) throws DALException;
	void createRaavareBatch(ProduceBatchDTO raavarebatch) throws DALException;
	void updateRaavareBatch(ProduceBatchDTO raavarebatch) throws DALException;
}

