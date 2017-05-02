package dk.dtu_23.model.data.interfaces;

import dk.dtu_23.model.ProductBatchDTO;

import java.util.List;

public interface ProductBatchDAO {
	ProductBatchDTO getProductBatch(int pbId) throws DALException;
	List<ProductBatchDTO> getProductBatchList() throws DALException;
	void createProductBatch(int recipe_id) throws DALException;
	void updateProductBatchStatus(ProductBatchDTO productbatch) throws DALException;
}