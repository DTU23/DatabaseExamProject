package dk.dtu_23.model.data.interfaces;

import dk.dtu_23.model.ProductBatchCompDTO;

import java.util.List;

public interface ProductBatchCompDAO {
	ProductBatchCompDTO getProductBatchComp(int pbId, int rbId) throws DALException;
	List<ProductBatchCompDTO> getProductBatchCompList(int pbId) throws DALException;
	List<ProductBatchCompDTO> getProductBatchCompList() throws DALException;
	void createProductBatchComp(ProductBatchCompDTO productbatchcomponent) throws DALException;
	void updateProductBatchComp(ProductBatchCompDTO productbatchcomponent) throws DALException;
}

