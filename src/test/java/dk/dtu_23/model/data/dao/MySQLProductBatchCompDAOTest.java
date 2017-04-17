package dk.dtu_23.model.data.dao;

import dk.dtu_23.model.ProductBatchCompDTO;
import dk.dtu_23.model.ProductBatchCompOverviewDTO;
import dk.dtu_23.model.ProductBatchCompSupplierDetailsDTO;
import dk.dtu_23.model.data.connector.Connector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MySQLProductBatchCompDAOTest {
    MySQLProductBatchCompDAO pbcdao;
    @Before
    public void setUp() throws Exception {
        new Connector();
        pbcdao = new MySQLProductBatchCompDAO();
    }

    @After
    public void tearDown() throws Exception {
        pbcdao = null;
    }

    @Test
    public void getProductBatchComp() throws Exception {
        ProductBatchCompDTO pbc1 = null;
        pbc1 = pbcdao.getProductBatchComp(1);
        assertThat(pbc1, is(not(equalTo(null))));
    }

    @Test
    public void createProductBatchComp() throws Exception {
        int batchCountBefore = pbcdao.getProductBatchComp(1).size();
        pbcdao.createProductBatchComp(new ProductBatchCompDTO(1, 1, 10, 10, 1));
        int batchCountAfter = pbcdao.getProductBatchComp(1).size();
        assertEquals(batchCountAfter, batchCountAfter-1);
    }

    @Test
    public void getProductBatchCompList() throws Exception {
        List<ProductBatchCompDTO> pbcl;
        pbcl = pbcdao.getProductBatchCompList();
        assertThat(pbcl, is(not(equalTo(null))));
    }

    @Test
    public void getProductBatchCompOverview() throws Exception {
        List<ProductBatchCompOverviewDTO> pbco;
        pbco = pbcdao.getProductBatchCompOverview();
        assertThat(pbco, is(not(equalTo(null))));
    }

    @Test
    public void getSupplierDetailById() throws Exception {
        List<ProductBatchCompSupplierDetailsDTO> pbcsd;
        pbcsd = pbcdao.getSupplierDetailById(1);
        assertThat(pbcsd, is(not(equalTo(null))));
    }

}