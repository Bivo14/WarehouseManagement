package model;

/**
 * 
 * @author Bivolaru Alexandru - 30225
 *
 *<p> Clasa care modeleaza un client (conform atributelor din tabela client din mysql)</p>
 *
 */

public class Client 
{
	private int idClient;
	private String nume;
	private String adresa;
	
	public Client(String nume, String adresa, int idClient)
	{
		this.idClient = idClient;
		this.nume = nume;
		this.adresa = adresa;
	}

	/**
	 * 
	 * @return id-ul clientului dorit
	 */
	
	public int getIdClient() 
	{
		return idClient;
	}

	/**
	 * 
	 * @param idClient - id-ul pe care dorim sa il setam clientului
	 */
	
	public void setIdClient(int idClient) 
	{
		this.idClient = idClient;
	}

	/**
	 * 
	 * @return - numele clientului dorit
	 */
	
	public String getNume() 
	{
		return nume;
	}

	/**
	 * 
	 * @param nume - numele pe care dorim sa il setam clientului dorit
	 */
	
	public void setNume(String nume) 
	{
		this.nume = nume;
	}

	/**
	 * 
	 * @return - adresa clientului dorit
	 */
	
	public String getAdresa() 
	{
		return adresa;
	}

	/**
	 * 
	 * @param adresa - adresa pe care dorim sa o setam clientului dorit
	 */
	
	public void setAdresa(String adresa) 
	{
		this.adresa = adresa;
	}
}
