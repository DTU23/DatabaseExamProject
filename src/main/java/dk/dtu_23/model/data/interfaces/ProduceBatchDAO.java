package dk.dtu_23.model.data.interfaces;

import java.util.List;

import dk.dtu_23.model.ProduceBatchDTO;

public interface ProduceBatchDAO {
	ProduceBatchDTO getProduceBatch(int rbId) throws DALException;
	List<ProduceBatchDTO> getProduceBatchList() throws DALException;
	void createProduceBatch(int produce_id, double amount) throws DALException;
	void updateProduceBatch(int produce_id, double amount) throws DALException;
}
