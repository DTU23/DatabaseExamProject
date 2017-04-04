package dk.dtu_23.model.data.interfaces;

import dk.dtu_23.model.ProduceBatchDTO;
import dk.dtu_23.model.ProduceDTO;

import java.util.List;

public interface ProduceBatchDAO {
	ProduceBatchDTO getProduceBatch(int rbId) throws DALException;
	List<ProduceBatchDTO> getProduceBatchList() throws DALException;
	void createProduceBatch(ProduceDTO produce, double amount) throws DALException;
	void updateProduceBatch(ProduceBatchDTO producebatch, double amount) throws DALException;
}
