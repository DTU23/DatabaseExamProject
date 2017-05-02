package dk.dtu_23.model.data.interfaces;

import dk.dtu_23.model.ProduceBatchDTO;
import dk.dtu_23.model.ProduceDTO;

import java.util.List;

public interface ProduceBatchDAO {
	ProduceBatchDTO getProduceBatch(int rbId) throws DALException;
	List<ProduceBatchDTO> getProduceBatchList() throws DALException;
	void createProduceBatch(int produce_id, double amount) throws DALException;
	void updateProduceBatch(int produce_id, double amount) throws DALException;
}
