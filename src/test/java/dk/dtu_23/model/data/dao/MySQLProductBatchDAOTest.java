package dk.dtu_23.model.data.dao;

import dk.dtu_23.model.ProductBatchDTO;
import dk.dtu_23.model.data.connector.Connector;
import dk.dtu_23.model.data.interfaces.DALException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class MySQLProductBatchDAOTest {
    private MySQLProductBatchDAO pbdao;
    @Before
    public void setUp() throws Exception {
        new Connector();
        pbdao = new MySQLProductBatchDAO();
    }

    @After
    public void tearDown() throws Exception {
        pbdao = null;
    }

    /**
     * Positive test for getProductBatch
     * @throws Exception
     */
    @Test
    public void getProductBatch() throws Exception {
        ProductBatchDTO pb1;
        pb1 = pbdao.getProductBatch(1);
        assertThat(pb1, is(equalTo(new ProductBatchDTO(1, 0, 1, "margherita"))));
    }

    /**
     * Positive test for getProductBatchList
     * @throws Exception
     */
    @Test
    public void getProductBatchList() throws Exception {
        List<ProductBatchDTO> list;
        list = pbdao.getProductBatchList();
        assertThat(list, is(not(equalTo(null))));
    }

    /**
     * Positive test for createProductBatch
     * @throws Exception
     */
    @Test
    public void createProductBatch() throws Exception {
        int batchCountBefore = pbdao.getProductBatchList().size();
        ProductBatchDTO newPb = new ProductBatchDTO(-1, 0, 1, "margharita");
        pbdao.createProductBatch(newPb);
        int batchCountAfter = pbdao.getProductBatchList().size();
        assertEquals(batchCountBefore, batchCountAfter-1);
    }

    /**
     * Negative test for createProductBatch
     * @throws Exception
     */
    @Test(expected=DALException.class)
    public void createProductBatchWithInvalidStatus() throws Exception {
        int batchCountBefore = pbdao.getProductBatchList().size();
        ProductBatchDTO newPb = new ProductBatchDTO(1, -1, 1, "margharita");
        pbdao.createProductBatch(newPb);
        int batchCountAfter = pbdao.getProductBatchList().size();
        assertEquals(batchCountBefore, batchCountAfter);
    }

    /**
     * Negative test for createProductBatch
     * @throws Exception
     */
    @Test(expected=DALException.class)
    public void createProductBatchWithInvalidRecipeID() throws Exception {
        int batchCountBefore = pbdao.getProductBatchList().size();
        ProductBatchDTO newPb = new ProductBatchDTO(1, 0, -1, "margharita");
        pbdao.createProductBatch(newPb);
        int batchCountAfter = pbdao.getProductBatchList().size();
        assertEquals(batchCountBefore, batchCountAfter);
    }

    /**
     * Positive test for updateProductBatch
     * @throws Exception
     */
    @Test
    public void updateProductBatchStatus() throws Exception {
        ProductBatchDTO newPb = new ProductBatchDTO(1, 0, 1, "margherita");
        ProductBatchDTO editedStatus = new ProductBatchDTO(1, 1, 1, "margherita");

        ProductBatchDTO PbCheckBeforeEdit;
        ProductBatchDTO PbCheckAfterEdit;

        PbCheckBeforeEdit = pbdao.getProductBatch(1);
        assertThat(newPb, is(equalTo(PbCheckBeforeEdit)));

        pbdao.updateProductBatch(editedStatus);

        PbCheckAfterEdit = pbdao.getProductBatch(1);

        assertThat(editedStatus, is(equalTo(PbCheckAfterEdit)));
        assertThat(PbCheckBeforeEdit, is(not(equalTo(PbCheckAfterEdit))));
    }

    /**
     * Negative test for updateProductBatch
     * @throws Exception
     */
    @Test(expected = DALException.class)
    public void updateProductBatchWithInvalidStatus() throws Exception {
        ProductBatchDTO newPb = new ProductBatchDTO(1, 0, 1, "margherita");
        ProductBatchDTO editedInvalidStatus = new ProductBatchDTO(1, -1, 1, "margherita");

        ProductBatchDTO PbCheckBeforeEdit;
        ProductBatchDTO PbCheckAfterEdit;

        PbCheckBeforeEdit = pbdao.getProductBatch(1);
        assertThat(newPb, is(equalTo(PbCheckBeforeEdit)));

        pbdao.updateProductBatch(editedInvalidStatus);

        PbCheckAfterEdit = pbdao.getProductBatch(1);

        assertThat(editedInvalidStatus, is(not(equalTo(PbCheckAfterEdit))));
        assertThat(PbCheckBeforeEdit, is(equalTo(PbCheckAfterEdit)));
    }
}