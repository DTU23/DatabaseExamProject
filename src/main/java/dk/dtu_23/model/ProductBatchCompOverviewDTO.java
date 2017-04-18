package dk.dtu_23.model;

public class ProductBatchCompOverviewDTO
{
	private int pbId;
	private String recipeName;
	private int status;
	private String produceName;
	double netto;
	int OprId;


	public ProductBatchCompOverviewDTO(int pbId, String recipeName, int status, String produceName, double netto, int operatorId){
		this.pbId = pbId;
		this.recipeName = recipeName;
		this.status = status;
		this.produceName = produceName;
		this.netto = netto;
		this.OprId = operatorId;
	}
	
	public int getPbId() { return this.pbId; }
	public void setPbId(int pbId) { this.pbId = pbId; }
	public String getRecipeName(){ return this.recipeName; }
	public void setRecipeName(String recipeName){ this.recipeName = recipeName;}
	public int getStatus(){ return this.status; }
	public void setStatus(int status){ this.status = status; }
	public String getProduceName(){ return this.produceName; }
	public void setProduceName(String produceName){ this.produceName = produceName; }

	public double getNetto() { return netto; }
	public void setNetto(double netto) { this.netto = netto; }
	public int getOprId() { return this.OprId; }
	public void setOprId(int oprId) { this.OprId = oprId; }
	public String toString() { 
		return pbId + "\t" + recipeName +"\t" + status +"\t" + produceName + "\t" + netto + "\t" + OprId; 
	}
}
