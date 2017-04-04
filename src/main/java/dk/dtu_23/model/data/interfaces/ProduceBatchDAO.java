package dk.dtu_23.model.data.interfaces;

import dk.dtu_23.model.ProduceBatchDTO;

import java.util.List;

public interface ProduceBatchDAO {
	ProduceBatchDTO getProduceBatch(int rbId) throws DALException;
	List<ProduceBatchDTO> getProduceBatchList() throws DALException;
	List<ProduceBatchDTO> getProduceBatchList(int produceId) throws DALException;
	void createProduceBatch(ProduceBatchDTO producebatch) throws DALException;
	void updateProduceBatch(ProduceBatchDTO producebatch) throws DALException;
}
