package dk.dtu_23.model;

public class ProductBatchDTO
{
	private int pbId;                     // i omraadet 1-99999999
	private int status;					// 0: ikke paabegyndt, 1: under produktion, 2: afsluttet
	private int recipeId;
	private String recipeName;

	public ProductBatchDTO(int pbId, int status, int recipeId, String recipeName)
	{
		this.pbId = pbId;
		this.status = status;
		this.recipeId = recipeId;
		this.recipeName = recipeName;
	}

	public int getPbId() { return pbId; }
	public void setPbId(int pbId) { this.pbId = pbId; }
	public int getStatus() { return status; }
	public void setStatus(int status) { this.status = status; }
	public int getRecipeId() { return recipeId; }
	public void setRecipeId(int recipeId) { this.recipeId = recipeId; }
	public String getRecipeName() { return this.recipeName; }
	public void setRecipeName(String recipeName) { this.recipeName = recipeName; }
	public String toString() { return pbId + "\t" + status + "\t" + recipeId + "\t" + recipeName; }
}

