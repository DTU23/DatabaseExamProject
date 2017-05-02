package dk.dtu_23.model.data.interfaces;

import dk.dtu_23.model.ProductBatchCompDTO;
import dk.dtu_23.model.ProductBatchCompOverviewDTO;
import dk.dtu_23.model.ProductBatchCompSupplierDetailsDTO;

import java.util.List;

public interface ProductBatchCompDAO {
	ProductBatchCompDTO getProductBatchComp(int pbId, int rbId) throws DALException;
	List<ProductBatchCompDTO> getProductBatchCompList() throws DALException;
	void createProductBatchComp(ProductBatchCompDTO productbatchcomponent) throws DALException;
	List<ProductBatchCompOverviewDTO> getProductBatchCompOverview() throws DALException;
	List<ProductBatchCompSupplierDetailsDTO> getSupplierDetailById(int pbId) throws DALException;
}
