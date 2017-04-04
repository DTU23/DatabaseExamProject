package dk.dtu_23.model.data.connector;


// erstat konstanterne nedenfor

public abstract class Constant
{
	public static final String
		server					= "localhost",  // database-serveren
		database				= "lab_database",  //"jdbcdatabase", // navnet paa din database = dit studienummer
		username				= "lab", // dit brugernavn = dit studienummer
		password				= "lab"; // dit password som du har valgt til din database

	public static final int
		port					= 3306;
}
