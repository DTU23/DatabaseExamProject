package dk.dtu_23.model;

/**
 * Produce Data Objekt
 * 
 * @author mn/sh/tb
 * @version 1.2
 */

public class ProduceDTO
{
    /** i omraadet 1-99999999 vaelges af brugerne */
    int produceId;                     
    /** min. 2 max. 20 karakterer */
    String produceNavn;                
    /** min. 2 max. 20 karakterer */
    String leverandoer;         
	
	public ProduceDTO(int produceId, String produceNavn, String leverandoer)
	{
		this.produceId = produceId;
		this.produceNavn = produceNavn;
		this.leverandoer = leverandoer;
	}
	
    public int getProduceId() { return produceId; }
    public void setProduceId(int produceId) { this.produceId = produceId; }
    public String getProduceName() { return produceNavn; }
    public void setProduceName(String produceNavn) { this.produceNavn = produceNavn; }
    public String getSupplier() { return leverandoer; }
    public void setLeverandoer(String leverandoer) { this.leverandoer = leverandoer; }
    public String toString() { 
		return produceId + "\t" + produceNavn +"\t" + leverandoer; 
	}
}
