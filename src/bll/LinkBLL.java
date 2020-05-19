package bll;

import dao.LinkDAO;

/**
 * 
 * @author Bivolaru Alexandru - 30225
 *
 *<p> Clasa contine toate operatiile efectuate de DAO-ul tabelei Link </p>
 *
 */

public class LinkBLL 
{
	/**
	 * 
	 * @param idComanda - id-ul comenzii pe care se efectueaza operatia
	 * @paral idClient - id-ul clientului din comanda pe care se efectueaza operatia
	 * 
	 * <p> Metoda care apeleaza metoda insertLink din LinkDAO </p>
	 * 
	 */
	
	public void insertLink(int idComanda, int idClient)
	{
		LinkDAO.insertLink(idComanda, idClient);
	}
	
	/**
	 * 
	 * @paral idClient - id-ul clientului folosit pentru stergerea comenzii din tabela Link
	 * 
	 * <p> Metoda care apeleaza metoda deleteLink din LinkDAO </p>
	 * 
	 */
	
	public void deleteLink(int idClient)
	{
		LinkDAO.deleteLink(idClient);
	}
}
