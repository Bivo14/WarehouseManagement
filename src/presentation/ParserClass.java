package presentation;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.itextpdf.text.DocumentException;

import bll.ClientBLL;
import bll.Link2BLL;
import bll.LinkBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import model.Client;
import model.Link;
import model.Link2;
import model.Order;
import model.Product;
import model.Warehouse;
import utilities.FileReader;
import utilities.Operations;

public class ParserClass 
{
	public FileWriter fw;
	public PDFCreator pdf;
	public String[] split;
	public int lineCount;
	public Operations operator;
	public ArrayList<Client> persoane;
	public ArrayList<Link> linkObj;
	public ArrayList<Link2> link2Obj;
	public ArrayList<Order> orderObj;
	public Warehouse storage;
	public ClientBLL cbll;
	public OrderBLL obll;
	public ProductBLL pbll;
	public LinkBLL lbll;
	public Link2BLL lbll2;
	
	/**
	 * 
	 * @param args
	 * @throws IOException
	 * 
	 * <p> Constructorul clasei Parser. In aceasta clasa se salveaza continutul fisierului dat ca si argument din args[0] intr-o lista de Stringuri
	 * Totodata in aceasta clasa se initializeaza listele suplimentare pentru a realiza legatura intre tabele </p>
	 * 
	 */
	
	public ParserClass(String[] args) throws IOException
	{
		FileReader rf = new FileReader();
		List l = rf.readFileInList(args[0]);
		fw = new FileWriter(args[0],true);
		Iterator<String> it = l.iterator();
		split = new String[100];
		lineCount = 0;
		while(it.hasNext())
		{
			for(String s: it.next().split(": "))
			{
				split[lineCount++] = s;
			}
		}
		this.operator = new Operations();
		this.persoane = new ArrayList<Client>();
		this.link2Obj = new ArrayList<Link2>();
		this.linkObj = new ArrayList<Link>();
		this.orderObj = new ArrayList<Order>();
		this.storage = new Warehouse();
		this.cbll = new ClientBLL();
		this.obll = new OrderBLL();
		this.pbll = new ProductBLL();
		this.lbll = new LinkBLL();
		this.lbll2 = new Link2BLL();
	}
	
	/**
	 * 
	 * @param select - id-ul clientului/produsului/comenzii pentru care se genereaza raportul
	 * @param checkCommand - Stringul care verifica ce comanda sa apeleze
	 * @return - daca raportul a fost generat cu succes sau nu
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 * 
	 * </p> Aceasta metoda verifica ce comanda este selectata din fisier, genereaza raportul si modifica variabila generated din 0 in 1.
	 * La finalul metodei se returneaza "generated" pentru a fi semnalati de faptul ca rapoartele au fost generate </p>
	 * 
	 */
	
	public int generateReports(int select, String checkCommand) throws FileNotFoundException, DocumentException
	{
		int generated = 0;
		if(checkCommand.equals("Report client"))
		{
			ClientBLL cbll = new ClientBLL();
			cbll.selectClient(select);
			generated = 1;
		}
		if(checkCommand.equals("Report product"))
		{
			ProductBLL pbll = new ProductBLL();
			pbll.selectProduct(select);
			generated = 1;
		}
		if(checkCommand.equals("Report order"))
		{
			OrderBLL obll = new OrderBLL();
			obll.selectOrder(select);
			generated = 1;
		}
		return generated;
	}
	
	/**
	 * 
	 * @param errorCode - Codul de eroare verificat pentru a sti ce raport de eroare sa fie generat
	 * @param pdfNumber
	 * @return - daca raportul a fost generat cu succes sau nu
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 * 
	 * <p> Aceasta metoda verifica ce cod de eroare este primit prin parametrul errorCode si genereaza un raport de eroare corespunzator acestuia </p>
	 * 
	 */
	
	public int generateErrorReports(int errorCode, int pdfNumber) throws FileNotFoundException, DocumentException
	{
		int generated = 0;
		if(errorCode == 1)
		{
			this.pdf = new PDFCreator();
			this.pdf.generateErrorPDF("Nu exista produsul",pdfNumber);
			generated = 1;
		}
		if(errorCode == 2 || storage.getStorage().isEmpty() == true)
		{
			this.pdf = new PDFCreator();
			this.pdf.generateErrorPDF("Cantitate insuficienta",pdfNumber);
			generated = 1;
		}
		if(errorCode == 3 || persoane.isEmpty() == true)
		{
			this.pdf = new PDFCreator();
			this.pdf.generateErrorPDF("Persoana nu exista",pdfNumber);
			generated = 1;
		}
		return generated;
	}
	
	/**
	 * 
	 * @param checkCommand - String-ul care verifica ce comanda se foloseste
	 * @param checkCommand2 - String-ul care extrage parametrii comenzii respective
	 * @param id - id-ul clientului care va fi adaugat in tabele
	 * @return - daca, clientul a fost inserat cu succes sau nu
	 * 
	 * <p> Aceasta metoda verifica daca stringul de verificat reprezinta "Insert Client".
	 * Daca, comanda a fost selectata cu succes, se extrag parametri necesari apelarii ei,
	 * urmand ca aceasta sa se apeleze. Persoanele sunt adaugate atat in tabelul din baza de date,
	 * cat si in lista secundara din Parser, "persoane". </p>
	 * 
	 */
	
	public int insertClient(String checkCommand, String checkCommand2, int id)
	{
		int inserted = 0;
		if(checkCommand.equals("Insert client"))
		{
			String[] split2 = new String[5];
			int k = 0;
			for(String sp2: checkCommand2.split(":"))
			{
				for(String sp3: sp2.split(", "))
				{
					split2[k++] = sp3;
				}
			}
			Client c = new Client(split2[0],split2[1],id);
			cbll.insertClient(c);
			persoane.add(c);
			inserted = 1;
		}
		return inserted;
	}
	
	/**
	 * 
	 * @param checkCommand - String-ul care verifica ce comanda se foloseste
	 * @param checkCommand2 - String-ul care extrage parametrii comenzii respective
	 * 
	 * <p> Aceasta metoda extrage comanda si parametri prin intermediul checkCommand si checkCommand2,
	 * extrage id-ul pe care il stergem din tabela de legatura link, iar apoi il sterge si din tabela client din mysql</p>
	 * 
	 */
	
	public void deleteClient(String checkCommand, String checkCommand2)
	{
		if(checkCommand.equals("Delete client"))
		{
			String[] split2 = new String[5];
			int k = 0;
			for(String sp2: checkCommand2.split(":"))
			{
				for(String sp3: sp2.split(", "))
				{
					split2[k++] = sp3;
				}
			}
			int idToDelete = operator.getClientIdToDelete(persoane, split2[0]);
			operator.deleteFromPersoane(persoane, split2[0]);
			operator.deleteFromLinkTable(linkObj, idToDelete);
			lbll.deleteLink(idToDelete);
			cbll.deleteClient(split2[0],split2[1]);
		}
	}
	
	/**
	 * 
	 * @param checkCommand - String-ul care verifica ce comanda se foloseste
	 * @param checkCommand2 - String-ul care extrage parametrii comenzii respective
	 * @param id
	 * @return - daca produsul a fost inserat cu succes sau nu
	 * 
	 * <p> Aceasta metoda verifica daca stringul de verificat reprezinta "Insert product".
	 * Daca, comanda a fost selectata cu succes, se extrag parametri necesari apelarii ei,
	 * urmand ca aceasta sa se apeleze. Produsele sunt adaugate atat in tabelul din baza de date,
	 * cat si in lista secundara din Parser, "storage". </p>
	 * 
	 */
	
	public int insertProduct(String checkCommand, String checkCommand2, int id)
	{
		int inserted = 0;
		if(checkCommand.equals("Insert product"))
		{
			String[] split2 = new String[5];
			int k = 0;
			for(String sp2: checkCommand2.split(":"))
			{
				for(String sp3: sp2.split(", "))
				{
					split2[k++] = sp3;
				}
			}
			Product p = new Product(split2[0],Integer.parseInt(split2[1]),Double.parseDouble(split2[2]),id);
			Product pp = storage.addProduct(p);
			for(Product pw: storage.getStorage())
			{
				if(pw.getNume().equals(pp.getNume()) && pw.getQuantity() == pp.getQuantity())
				{
					pbll.insertProduct(pp);
					inserted = 1;
				}
				if(pw.getNume().equals(pp.getNume()) && pw.getQuantity() != pp.getQuantity())
				{
					pbll.updateProduct(pw.getNume(), pw.getQuantity());
				}
			}
		}
		return inserted;
	}
	
	/**
	 * 
	 * @param checkCommand - String-ul care verifica ce comanda se foloseste
	 * @param checkCommand2 - String-ul care extrage parametrii comenzii respective
	 * 
	 * <p> Aceasta metoda extrage comanda si parametri prin intermediul checkCommand si checkCommand2,
	 * extrage id-ul pe care il stergem din tabela de legatura link2, iar apoi il sterge si din tabela product din mysql</p>
	 * 
	 */
	
	public void deleteProduct(String checkCommand, String checkCommand2)
	{
		if(checkCommand.equals("Delete Product"))
		{
			String[] split2 = new String[5];
			int k = 0;
			for(String sp2: checkCommand2.split(":"))
			{
				for(String sp3: sp2.split(", "))
				{
					split2[k++] = sp3;
				}
			}
			int prodToDelete = operator.getProductIdToDelete(storage, split2[0]);
			operator.deleteFromStorage(storage, split2[0]);
			operator.deleteFromLink2Table(link2Obj, prodToDelete);
			lbll2.deleteLink2(prodToDelete);
			pbll.deleteProduct(split2[0]);
		}
	}
	
	/**
	 * 
	 * @param order - Comanda propriu ziusa
	 * @param c - Clientul care a efectuat comanda
	 * @param prod - Produsul din comanda
	 * @param orderId - Id-ul comenzii
	 * @param pdfNumber
	 * @return - faptul ca, comanda a fost setata (cantitatea scazuta din depozit, inserarea clientului / produsului in tabelele de legatura, generarea pdf-ului)
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 */
	
	public int setOrder(Order order, Client c, Product prod, int orderId, int pdfNumber) throws FileNotFoundException, DocumentException
	{
		order.setPrice(order.getQuantity() * prod.getPret());
		pbll.updateProduct(order.getNumeProdus(), prod.getQuantity() - order.getQuantity());
		prod.setQuantity(prod.getQuantity() - order.getQuantity());
		obll.insertOrder(order);
		orderObj.add(order);
		
		lbll.insertLink(orderId, c.getIdClient());
		Link linkAdd = new Link(orderId,c.getIdClient());
		linkObj.add(linkAdd);
		
		lbll2.insertLink2(orderId, prod.getIdProdus());
		Link2 link2Add = new Link2(orderId,prod.getIdProdus());
		link2Obj.add(link2Add);
		
		this.pdf = new PDFCreator();
		this.pdf.generateBillPDF(order,pdfNumber);

		return 1;
	}
	
	/**
	 * 
	 * @param y - Contorul pentru denumirea fisierului pdf
	 * @param c1 - Contorul pentru id-ul clientului
	 * @param c2 - Contorul pentru id-ul produsului
	 * @param c3 - Contorul pentru id-ul comenzii
	 * @return - Numarul de documente pdf generate (Pentru  a verifica corectitudinea programului)
	 * @throws DocumentException
	 * @throws IOException
	 * 
	 * <p> Metoda parseaza argumentele din fisier si apeleaza comenzile. Totodata, in aceasta metoda
	 * se efectueaza si cautarea operatiilor de adaugare a comenzilor, generarea codurilor de eroare
	 * si aplicarea acestora in generarea pdf-urilor. Se maresc contoarele pentru id-ul clientilor, produselor
	 * si comenzilor si contorul pentru denumirea fisierelor pdf </p>
	 * 
	 */
	
	public int parse(int y, int c1, int c2, int c3) throws DocumentException, IOException
	{	
		for(int j = 0; j < lineCount; j++)
		{
			int generated = this.generateReports(y,split[j]);
			if(generated == 1)
			{
				y++;
			}
			int inserted = this.insertClient(split[j], split[j+1],c1);
			if(inserted == 1)
			{
				c1++;
			}
			this.deleteClient(split[j], split[j+1]);
			int pInserted = this.insertProduct(split[j], split[j+1], c2);
			if(pInserted == 1)
			{
				c2++;
			}
			this.deleteProduct(split[j], split[j+1]);
			if(split[j].equals("Order"))
			{				
				String[] split2 = new String[5];
				int k = 0;
				for(String sp2: split[j+1].split(", "))
				{
					split2[k++] = sp2;
				}
				Order order = new Order(split2[0],split2[1],Integer.parseInt(split2[2]),0,c3);
				int errorCode = -1;
				for(Client c: persoane)
				{
					if(c.getNume().equals(split2[0]))
					{
						for(Product prod: storage.getStorage())
						{
							if(prod.getNume().equals(split2[1]))
							{
								if(prod.getQuantity() >= Integer.parseInt(split2[2]))
								{
									int orderSet = this.setOrder(order, c, prod, c3, y);
									if(orderSet == 1)
									{
										c3++;
										y++;
										errorCode = 0;
									}
									break;
								}
								errorCode = 2;
							}
							if(errorCode == -1)
							{
								errorCode = 1;
							}
						}
					}
					if(errorCode == 0)
					{
						break;
					}
					if(errorCode != 2 && errorCode != 1)
					{
						errorCode = 3;
					}
				}
				int errorsGenerated = this.generateErrorReports(errorCode, y);
				if(errorsGenerated == 1)
				{
					y++;
				}
			}
		}
		return y;
	}
}
