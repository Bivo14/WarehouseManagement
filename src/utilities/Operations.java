package utilities;

import java.util.ArrayList;
import java.util.Iterator;

import model.Client;
import model.Link;
import model.Link2;
import model.Product;
import model.Warehouse;

/**
 * 
 * @author Bivolaru Alexandru - 30225
 * 
 * <p> Clasa care se ocupa de identificarea id-ului/numelor clientilor/produselor ce trebuie sterse </p>
 * <p> De asemenea, in aceasta clasa se gasesc metode pentru stergerea obiectelor din listele suplimentare de legatura </p>
 *
 */

public class Operations 
{
	/**
	 * 
	 * @param persoane - Lista in care se salveaza persoanele din tabelul Client
	 * @param nume - Numele clientului pe care dorim sa il stergem din lista.
	 * 
	 * <p> Aceasta metoda parcurge lista de persoane si cauta acea persoana care are numele "nume" si o sterge </p>
	 */
	
	public void deleteFromPersoane(ArrayList<Client> persoane, String nume)
	{
		Iterator<Client> it2 = persoane.iterator();
		while(it2.hasNext())
		{
			if(it2.next().getNume().equals(nume))
			{
				it2.remove();
			}
		}
	}
	
	/**
	 * 
	 * @param persoane - Lista in care se salveaza persoanele din tabelul Client
	 * @param nume - Numele clientului pe care dorim sa il stergem din lista.
	 * @return id-ul corespunzator persoanei cu numele "nume"
	 * 
	 * <p> Aceasta metoda parcurge lista de persoane si cauta id-ul persoanei cu numele "nume" </p>
	 */
	
	public int getClientIdToDelete(ArrayList<Client> persoane, String nume)
	{
		int idToDelete = 0;
		for(Client c : persoane)
		{
			if(c.getNume().equals(nume))
			{
				idToDelete = c.getIdClient();
			}
		}
		return idToDelete;
	}
	
	/**
	 * 
	 * @param storage - Lista in care sunt stocate produsele din tabelul Product
	 * @param nume - Numele produsului pe care dorim sa il stergem
	 * 
	 * <p> Aceasta metoda parcurge lista de produse si sterge produsul cu numele "nume" </p>
	 */
	
	public void deleteFromStorage(Warehouse storage, String nume)
	{
		Iterator<Product> it2 = storage.getStorage().iterator();
		while(it2.hasNext())
		{
			if(it2.next().getNume().equals(nume))
			{
				it2.remove();
			}
		}
	}
	
	/**
	 * 
	 * @param storage - Lista in care sunt stocate produsele din tabelul Product
	 * @param nume - Numele produsului pe care dorim sa il stergem din lista.
	 * @return id-ul corespunzator produsului cu numele "nume"
	 * 
	 * <p> Aceasta metoda parcurge lista de produse si cauta id-ul produsului cu numele "nume" </p>
	 * 
	 */
	
	public int getProductIdToDelete(Warehouse storage, String nume)
	{
		int prodToDelete = 0;
		for(Product p : storage.getStorage())
		{
			if(p.getNume().equals(nume))
			{
				prodToDelete = p.getIdProdus();
			}
		}
		return prodToDelete;
	}
	
	/**
	 * 
	 * @param linkObj - Lista care stocheaza clientii in tabelul de legatura dintre client si order
	 * @param idToDelete - Id-ul persoanei pe care dorim sa o stergem din tabela de legatura
	 * 
	 * <p> Aceasta metoda parcurge tabelul de legatura dintre client si order si cauta persoana cu id-ul idToDelete si o sterge </p>
	 */
	
	public void deleteFromLinkTable(ArrayList<Link> linkObj, int idToDelete)
	{
		Iterator<Link> it3 = linkObj.iterator();
		while(it3.hasNext())
		{
			if(it3.next().getIdClient() == idToDelete)
			{
				it3.remove();
			}
		}
	}
	
	/**
	 * 
	 * @param link2Obj - Lista care stocheaza produsele in tabelul de legatura dintre product si order
	 * @param prodToDelete - Id-ul produsului pe care dorim sa il stergem din tabela de legatura
	 * 
	 * <p> Aceasta metoda parcurge tabelul de legatura dintre product si order si cauta produsul cu id-ul idToDelete si il sterge </p>
	 * 
	 */
	
	public void deleteFromLink2Table(ArrayList<Link2> link2Obj, int prodToDelete)
	{
		Iterator<Link2> it3 = link2Obj.iterator();
		while(it3.hasNext())
		{
			if(it3.next().getIdProdus() == prodToDelete)
			{
				it3.remove();
			}
		}
	}
}
