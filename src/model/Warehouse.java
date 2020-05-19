package model;

import java.util.ArrayList;

/**\
 * 
 * @author Bivolaru Alexandru - 30225
 * 
 * <p> In aceasta metoda este definit un depozit, in care sunt stocate produsele </p>
 *
 */

public class Warehouse 
{
	private ArrayList<Product> storage;
	/**
	 * In aceasta metoda se initializeaza lista in care sunt stocate produsele.
	 */
	public Warehouse()
	{
		storage = new ArrayList<Product>();
	}
	
	/**
	 * 
	 * @param p - produsul ce trebuie inserat in tabela
	 * @return produsul care urmeaza sa fie procesat in celelalte clase
	 * 
	 * <p> Metoda verifica daca produsul exista deja in depozit ca sa ii actualizeze cantitatea. 
	 * Daca nu exista, il insereaza. Daca exista, doar ii actualizeaza cantitatea </p>
	 * 
	 */
	
	public Product addProduct(Product p)
	{
		int valid = 0;
		for(Product pc : this.storage)
		{
			if(p.getNume().equals(pc.getNume()))
			{
				pc.setQuantity(pc.getQuantity() + p.getQuantity());
				valid = 1;
				break;
			}
		}
		
		if(valid == 0)
		{
			this.storage.add(p);
		}
		
		return p;
	}
	
	/**
	 * 
	 * @param p
	 * 
	 * <p> Metoda care elimina produsul din depozit </p>
	 * 
	 */
	
	public void removeProduct(Product p)
	{
		this.storage.remove(p);
	}
	
	/**
	 * 
	 * @return Lista de produse din depozit
	 */
	
	public ArrayList<Product> getStorage()
	{
		return this.storage;
	}
	
	/**
	 * Afiseaza informatiile despre starea actuala a depozitului
	 */
	
	public void warehouseInfo()
	{
		for(Product p: this.storage)
		{
			System.out.println(p.getNume() + " " + p.getQuantity() + " " + p.getPret());
		}
	}
}
