package model;

/**
 * 
 * @author Bivolaru Alexandru - 30225
 *
 *<p> Clasa care modeleaza un produs (conform atributelor din tabela Product din mysql)</p>
 *
 */

public class Product 
{
	private int idProdus;
	private String nume;
	private int quantity;
	private double pret;
	
	public Product(String nume, int quantity, double d, int idProdus)
	{
		this.idProdus = idProdus;
		this.nume = nume;
		this.quantity = quantity;
		this.pret = d;
	}

	/**
	 * 
	 * @return - cantitatea produsului dorit
	 */
	
	public int getQuantity() {
		return quantity;
	}

	/**
	 * 
	 * @param quantity - cantitatea setata a produsului
	 */
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * 
	 * @return id-ul produsului dorit
	 */
	
	public int getIdProdus() 
	{
		return idProdus;
	}

	/**
	 * 
	 * @param idProdus - id-ul pe care dorim sa il setam produsului
	 */
	
	public void setIdProdus(int idProdus) 
	{
		this.idProdus = idProdus;
	}

	/**
	 * 
	 * @return - numele produsului dorit
	 */
	
	public String getNume() 
	{
		return nume;
	}

	/**
	 * 
	 * @param nume - numele pe care dorim sa il setam produsului dorit
	 */
	
	public void setNume(String nume) 
	{
		this.nume = nume;
	}

	/**
	 * 
	 * @return - pretul produsului dorit
	 */
	
	public double getPret() 
	{
		return pret;
	}

	/**
	 * 
	 * @param pret - pretul pe care dorim sa il setam produsului dorit
	 */
	
	public void setPret(float pret) 
	{
		this.pret = pret;
	}
}
