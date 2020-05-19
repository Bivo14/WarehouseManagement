package model;

/**
 * 
 * @author Bivolaru Alexandru - 30225
 *
 *<p> Clasa care modeleaza o entitate de tip Link (Tabel de legatura intre client si comanda) </p>
 *
 */

public class Link 
{
	private int idComanda;
	private int idClient;
	
	public Link(int idComanda, int idClient)
	{
		this.idComanda = idComanda;
		this.idClient = idClient;
	}

	/**
	 * 
	 * @return - id-ul comenzii dorite
	 */
	
	public int getIdComanda() {
		return idComanda;
	}

	/**
	 * 
	 * @param idComanda - setam id-ul comenzii dorite
	 */
	
	public void setIdComanda(int idComanda) {
		this.idComanda = idComanda;
	}
	
	/**
	 * 
	 * @return id-ul clientului din comanda
	 */
	
	public int getIdClient() {
		return idClient;
	}

	/**
	 * 
	 * @param idProdus - setam id-ul clientului din comanda
	 */
	
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
}
