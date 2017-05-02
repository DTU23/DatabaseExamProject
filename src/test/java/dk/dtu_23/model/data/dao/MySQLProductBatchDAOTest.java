package dk.dtu_23.model.data.dao;

import dk.dtu_23.model.ProductBatchDTO;
import dk.dtu_23.model.data.connector.Connector;
import dk.dtu_23.model.data.interfaces.DALException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class MySQLProductBatchDAOTest {
    private MySQLProductBatchDAO pbdao;
    @Before
    public void setUp() throws Exception {
        new Connector();
        Connector.resetData();
        pbdao = new MySQLProductBatchDAO();
    }

    @After
    public void tearDown() throws Exception {
        Connector.resetData();
        pbdao = null;
    }

    /**
     * Positive test for getProductBatch
     * @throws Exception
     */
    @Test
    public void getProductBatch() throws Exception {
        ProductBatchDTO pb1, expected;
        expected = new ProductBatchDTO(1, 2, 1);
        pb1 = pbdao.getProductBatch(1);
        assertThat(pb1.toString(), is(equalTo(expected.toString())));
    }

    /**
     * Positive test for getProductBatchList
     * @throws Exception
     */
    @Test
    public void getProductBatchList() throws Exception {
        List<ProductBatchDTO> list;
        list = pbdao.getProductBatchList();
        assertThat(list, notNullValue());
    }

    /**
     * Positive test for createProductBatch
     * @throws Exception
     */
    @Test
    public void createProductBatch() throws Exception {
        int batchCountBefore = pbdao.getProductBatchList().size();
        pbdao.createProductBatch(1);
        int batchCountAfter = pbdao.getProductBatchList().size();
        assertEquals(batchCountBefore, batchCountAfter-1);
    }

    /**
     * Negative test for createProductBatch
     * @throws Exception
     */
    @Test(expected=DALException.class)
    public void createProductBatchWithInvalidRecipeID() throws Exception {
        int batchCountBefore = pbdao.getProductBatchList().size();
        pbdao.createProductBatch(-1);
        int batchCountAfter = pbdao.getProductBatchList().size();
        assertEquals(batchCountBefore, batchCountAfter);
    }

    /**
     * Positive test for updateProductBatch
     * @throws Exception
     */
    @Test
    public void updateProductBatchStatus() throws Exception {
        ProductBatchDTO beforeEdit = pbdao.getProductBatch(1);
        ProductBatchDTO afterEdit = pbdao.getProductBatch(1);
        afterEdit.setStatus(0);

        pbdao.updateProductBatchStatus(afterEdit);

        assertThat(afterEdit.toString(), is(equalTo(pbdao.getProductBatch(1).toString())));
        assertThat(beforeEdit.toString(), is(not(equalTo(pbdao.getProductBatch(1).toString()))));
    }

    /**
     * Negative test for updateProductBatch
     * @throws Exception
     */
    @Test(expected = DALException.class)
    public void updateProductBatchWithInvalidStatus() throws Exception {
        ProductBatchDTO beforeEdit = pbdao.getProductBatch(1);
        ProductBatchDTO afterEdit = pbdao.getProductBatch(1);
        afterEdit.setStatus(-1);

        pbdao.updateProductBatchStatus(afterEdit);

        assertThat(afterEdit.toString(), is(not(equalTo(pbdao.getProductBatch(1).toString()))));
        assertThat(beforeEdit.toString(), is(equalTo(pbdao.getProductBatch(1).toString())));
    }
}