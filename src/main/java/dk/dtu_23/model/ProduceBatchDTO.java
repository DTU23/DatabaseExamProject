package dk.dtu_23.model;

public class ProduceBatchDTO
{
	int rbId;                     // i omraadet 1-99999999
	int amountId;             // i omraadet 1-99999999
	double amount;             // kan vaere negativ 

	public ProduceBatchDTO(int rbId, int amountId, double amount)
	{
		this.rbId = rbId;
		this.amountId = amountId;
		this.amount = amount;
	}
	
	public int getRbId() { return rbId; }
	public void setRbId(int rbId) { this.rbId = rbId; }
	public int getProduceId() { return amountId; }
	public void setProduceId(int amountId) { this.amountId = amountId; }
	public double getAmount() { return amount; }
	public void setAmount(double amount) { this.amount = amount; }
	public String toString() { 
		return rbId + "\t" + amountId +"\t" + amount; 
	}
}
