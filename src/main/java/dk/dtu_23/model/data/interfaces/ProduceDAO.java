package dk.dtu_23.model.data.interfaces;

import dk.dtu_23.model.ProduceDTO;

import java.util.List;

public interface ProduceDAO {
	ProduceDTO getProduce(int raavareId) throws DALException;
	List<ProduceDTO> getProduceList() throws DALException;
	void createProduce(ProduceDTO raavare) throws DALException;
	void updateProduce(ProduceDTO raavare) throws DALException;
}
