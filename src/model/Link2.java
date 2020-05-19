package model;

/**
 * 
 * @author Bivolaru Alexandru - 30225
 *
 *<p> Clasa care modeleaza o entitate de tip Link2 (Tabel de legatura intre produs si comanda) </p>
 *
 */

public class Link2 
{
	private int idComanda;
	private int idProdus;
	
	public Link2(int idComanda, int idProdus)
	{
		this.idComanda = idComanda;
		this.idProdus = idProdus;
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
	 * @return id-ul produsului din comanda
	 */
	
	public int getIdProdus() {
		return idProdus;
	}

	/**
	 * 
	 * @param idProdus - setam id-ul produsului din comanda
	 */
	
	public void setIdProdus(int idProdus) {
		this.idProdus = idProdus;
	}
}
