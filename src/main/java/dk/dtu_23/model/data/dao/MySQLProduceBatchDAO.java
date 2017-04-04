package dk.dtu_23.model.data.dao;

import dk.dtu_23.model.ProduceBatchDTO;
import dk.dtu_23.model.data.interfaces.DALException;
import dk.dtu_23.model.data.interfaces.ProduceBatchDAO;

import java.util.List;

public class MySQLProduceBatchDAO implements ProduceBatchDAO {

	@Override
	public ProduceBatchDTO getRaavareBatch(int rbId) throws DALException {
		return null;
	}

	@Override
	public List<ProduceBatchDTO> getRaavareBatchList() throws DALException {
		return null;
	}

	@Override
	public List<ProduceBatchDTO> getRaavareBatchList(int raavareId) throws DALException {
		return null;
	}

	@Override
	public void createRaavareBatch(ProduceBatchDTO raavarebatch) throws DALException {

	}

	@Override
	public void updateRaavareBatch(ProduceBatchDTO raavarebatch) throws DALException {

	}
}
