package dk.dtu_23.model.data.dao;

import dk.dtu_23.model.ProductBatchDTO;
import dk.dtu_23.model.RecipeDTO;
import dk.dtu_23.model.data.connector.Connector;
import dk.dtu_23.model.data.interfaces.DALException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
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
        try { new Connector(); }
        catch (InstantiationException e) { e.printStackTrace(); }
        catch (IllegalAccessException e) { e.printStackTrace(); }
        catch (ClassNotFoundException e) { e.printStackTrace(); }
        catch (SQLException e) { e.printStackTrace(); }
        pbdao = new MySQLProductBatchDAO();
    }

    @After
    public void tearDown() throws Exception {
        pbdao = null;
    }

    @Test
    public void getProductBatch() throws Exception {
        ProductBatchDTO pb1 = null;
        try {
            pb1 = pbdao.getProductBatch(1);
        } catch (DALException e) { System.out.println(e.getMessage()); }
        assertThat(pb1, is(equalTo(new ProductBatchDTO(1, 0, 1, "margherita"))));
    }

    @Test
    public void getProductBatchList() throws Exception {
        List<ProductBatchDTO> list = null;
        try {
            list = pbdao.getProductBatchList();
        } catch (DALException e) { System.out.println(e.getMessage()); }
        assertThat(list, is(not(equalTo(null))));
    }

    @Test
    public void createProductBatch() throws Exception {
        int batchCountBefore = pbdao.getProductBatchList().size();
        ProductBatchDTO newPb = new ProductBatchDTO(-1, 0, 1, "margharita");
        pbdao.createProductBatch(newPb);
        int batchCountAfter = pbdao.getProductBatchList().size();
        assertEquals(batchCountBefore, batchCountAfter-1);
    }

    @Test
    public void updateProductBatch() throws Exception {
        ProductBatchDTO newPb = new ProductBatchDTO(1, 0, 1, "margherita");
        ProductBatchDTO editedStatus = new ProductBatchDTO(1, 1, 1, "margherita");
        ProductBatchDTO PbCheckBeforeEdit = null;
        ProductBatchDTO PbCheckAfterEdit = null;
        try {
            pbdao.createProductBatch(newPb);
        } catch (DALException e) { System.out.println(e.getMessage()); }
        try {
            PbCheckBeforeEdit = pbdao.getProductBatch(1);
        } catch (DALException e) { System.out.println(e.getMessage()); }
        assertThat(newPb, is(equalTo(PbCheckBeforeEdit)));
        try {
            pbdao.updateProductBatch(editedStatus);
        } catch (DALException e) { System.out.println(e.getMessage()); }
        try {
            PbCheckAfterEdit = pbdao.getProductBatch(1);
        } catch (DALException e) { System.out.println(e.getMessage()); }
        assertThat(editedStatus, is(equalTo(PbCheckAfterEdit)));
        assertThat(PbCheckBeforeEdit, is(not(equalTo(PbCheckAfterEdit))));
    }

}