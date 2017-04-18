package dk.dtu_23.model.data.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dk.dtu_23.model.ProduceBatchDTO;
import dk.dtu_23.model.ProduceDTO;
import dk.dtu_23.model.data.connector.Connector;
import dk.dtu_23.model.data.interfaces.DALException;

public class MySQLProduceBatchDAOTest {

	MySQLProduceBatchDAO produceBatch;

	@Before
	public void setUp() throws Exception {
		new Connector();		
		produceBatch = new MySQLProduceBatchDAO();
	}

	@After
	public void tearDown() throws Exception{
		produceBatch = null;
	}

	/**
	 * Positive test. Get any produce batch by ID.
	 */
	@Test
	public void testGetProduceBatchByID() throws Exception {
		ProduceBatchDTO expected = new ProduceBatchDTO(1, "dej", "Wawelka", 1000);
		ProduceBatchDTO actual = null;
		// Get produce batch from DB specified by ID
		actual = produceBatch.getProduceBatch(1);
		assertThat(actual.toString(), is(expected.toString()));
	}

	/**
	 * Negative test. Try to get produce batch by ID that doesn't exist.
	 */
	@Test
	public void testGetProduceBatchByIDThatDoesntExist() throws Exception{
		ProduceBatchDTO actual = null;
		String error = null;
		
		try {
			actual = produceBatch.getProduceBatch(17);
		} catch (DALException e) {
			e.printStackTrace();
			error = e.getMessage();
		}
		
		assertThat(actual, nullValue());
		assertThat(error, notNullValue());
	}

	/**
	 * Positive test. Get the produce batch list.
	 */
	@Test
	public void testGetProduceBatchList() throws Exception{
		List<ProduceBatchDTO> pbList = null;
		// Equal to the element in second row of the view produce_batch_list
		ProduceBatchDTO produceBatchDTO = new ProduceBatchDTO(2, "tomat", "Knoor", 300);

		pbList = produceBatch.getProduceBatchList();

		System.out.println("testGetProduceBatchList(): ");
		// Print out the produce batch list to console
		for(int i = 0; i < pbList.size(); i++)
			System.out.println(pbList.get(i));
		System.out.println();

		assertThat(pbList, notNullValue());
		assertThat(pbList.get(1).toString(), is(produceBatchDTO.toString()));
	}

	/**
	 * Positive test. Create a produce batch.
	 */
	@Test
	public void testCreateProduceBatch() throws Exception {
		// Get an already created produce
		MySQLProduceDAO produce = new MySQLProduceDAO();
		ProduceDTO produceDTO = produce.getProduce(4);
		// Define amount of produce
		double amount = 500;
		
		ProduceBatchDTO actual = null;
		ProduceBatchDTO expected = new ProduceBatchDTO(4, "ost", "Ost og Skinke A/S", amount);
		
		produceBatch.createProduceBatch(produceDTO, amount);
		actual = produceBatch.getProduceBatch(8);

		System.out.println(actual + "\n" + expected);
		
		assertThat(actual.toString(), is(expected.toString()));
	}

	/**
	 * Negative test. Create a produce batch with already existing ID.
	 */
	@Test
	public void testCreateProduceBatchWithAlreadyExistingID() throws Exception {
		double amount = 500;
		ProduceDTO produceDTO = new ProduceDTO(5, "chips", "Kims");
		ProduceBatchDTO expected = new ProduceBatchDTO(5, "chips", "Kims", amount);
		ProduceBatchDTO actual = null;

		produceBatch.createProduceBatch(produceDTO, amount);
		actual = produceBatch.getProduceBatch(5);

		assertThat(actual.toString(), is(not(expected.toString())));
	}

	/**
	 * Positive test. Update a produce batch.
	 */
	@Test
	public void testUpdateProduceBatch() throws Exception {
		ProduceBatchDTO pbDTO = new ProduceBatchDTO(3, "tomat", "Veaubais", 300);
		ProduceBatchDTO actual = null;
		double amount = 600;

		produceBatch.updateProduceBatch(pbDTO, amount);
		actual = produceBatch.getProduceBatch(3);

		assertThat(actual.toString(), is(not(pbDTO.toString())));
		assertThat(actual.getAmount(), is(amount));
	}

	/**
	 * Negative test. Update a produce batch that doesn't exist.
	 */
	@Test
	public void testUpdateProduceBatchThatDoesntExist() throws Exception{
		ProduceBatchDTO pbDTO = new ProduceBatchDTO(12, "ketchup", "Heinz", 200);
		ProduceBatchDTO actual = null;
		double amount = 400;

		produceBatch.updateProduceBatch(pbDTO, amount);
		actual = produceBatch.getProduceBatch(12);

		assertThat(actual.toString(), is(not(pbDTO.toString())));
	}

}