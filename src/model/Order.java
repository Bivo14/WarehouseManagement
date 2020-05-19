package model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Bivolaru Alexandru - 30225
 * 
 * <p> Clasa care modeleaza entitatea Comanda din tabelul Comanda din mysql </p>
 *
 */

public class Order 
{
	private int idOrder;
	private String numeClient;
	private String prenumeClient;
	private String numeProdus;
	private int quantity;
	private double price;

	public Order(String numeClient, String numeProdus, int quantity, double price, int idOrder)
	{
		this.idOrder = idOrder;
		this.numeClient = numeClient;
		this.numeProdus = numeProdus;
		this.quantity = quantity;
		this.price = 0;
	}
	
	/**
	 * 
	 * @return - Pretul total al comenzii dorite
	 */
	
	public double getPrice() 
	{
		return price;
	}

	/**
	 * 
	 * @param price - setarea pretului total al comenzii 
	 */
	
	public void setPrice(double price)
	{
		this.price = price;
	}

	/**
	 * 
	 * @return - id-ul comenzii dorite
	 */
	
	public int getIdOrder() 
	{
		return idOrder;
	}

	/**
	 * 
	 * @param idOrder - id-ul pe care dorim sa il setam comenzii dorite
	 */
	
	public void setIdOrder(int idOrder) 
	{
		this.idOrder = idOrder;
	}

	/**
	 * 
	 * @return - numele clientului care efectueaza comanda
	 */
	
	public String getNumeClient() 
	{
		return numeClient;
	}

	/**
	 * 
	 * @param numeClient - setam numele clientului care efectueaza comadna
	 */
	
	public void setNumeClient(String numeClient) 
	{
		this.numeClient = numeClient;
	}

	/**
	 * 
	 * @return - returnam numele produsului din comanda
	 */
	
	public String getNumeProdus() 
	{
		return numeProdus;
	}

	/**
	 * 
	 * @param numeProdus - setam numele produsului din comanda
	 */
	
	public void setNumeProdus(String numeProdus) 
	{
		this.numeProdus = numeProdus;
	}

	/**
	 * 
	 * @return - cantitatea totala a comenzii
	 */
	
	public int getQuantity() 
	{
		return quantity;
	}

	/**
	 * 
	 * @param quantity - setam cantitatea totala a comenzii
	 */
	
	public void setQuantity(int quantity) 
	{
		this.quantity = quantity;
	}
}
