package presentation;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.Order;

/**
 * 
 * @author Bivolaru Alexandru - 30225
 *	
 * <p> In cadrul acestei clase se definesc metode pentru generarea de fisiere tip .pdf </p>
 * 
 */

public class PDFCreator
{
	/**
	 * 
	 * @param o - Comanda
	 * @param y - Contorul pentru denumirea fisierelor
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 * 
	 * <p> Aceasta metoda genereaza factura unei comenzi daca aceasta a fost plasata cu succes
	 * Pentru a nu suprascrie anumite fisiere .pdf, la finalul numelui generat se concateneaza un "y" </p>
	 * 
	 */
	
	public void generateBillPDF(Order o, int y) throws FileNotFoundException, DocumentException
	{
		Document document = new Document();
		PdfWriter.getInstance(document,new FileOutputStream("Bill " + o.getNumeClient() + y + ".pdf"));
		document.open();
		com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		document.add(new Paragraph("Your transaction has been approved"));
		document.add(new Paragraph("Nume client: " + o.getNumeClient()));
		document.add(new Paragraph("Produs achizitionat: " + o.getNumeProdus()));
		document.add(new Paragraph("Cantitate achizitionata: " + o.getQuantity() + " buc"));
		document.add(new Paragraph("Pret comanda: " + o.getPrice() + " ron"));
		document.add(new Paragraph("VA MULTUMIM!"));
		document.close();
	}
	
	/**
	 * 
	 * @param errMessage - Mesajul de eroare afisat
	 * @param y - Contorul pentru denumirea fisierelor
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 * 
	 * <p> Aceasta metoda genereaza un fisier .pdf in care este scris mesajul de eroare conform codului de eroare din parser </p>
	 */
	
	public void generateErrorPDF(String errMessage, int y) throws FileNotFoundException, DocumentException
	{
		Document document = new Document();
		PdfWriter.getInstance(document,new FileOutputStream("Error" + y + ".pdf"));
		document.open();
		com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		document.add(new Paragraph("Comanda nu a putut fi plasata!"));
		document.add(new Paragraph(errMessage));
		document.close();
	}
	
	/**
	 * 
	 * @param columns - Numarul de coloane ale tabelului
	 * @param rst - Obiect de tip ResultSet in care sunt stocate datele din tabelul client
	 * @param y - Contorul pentru denumirea fisierelor
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 * @throws SQLException
	 * 
	 * <p> Aceasta metoda genereaza un tabel cu clienti, tabel populat cu ajutorul obiectului ResultSet, care extrage datele din tabelul client din MySql </p>
	 * 
	 */
	
	public void generateClientTablePDF(int columns,ResultSet rst, int y) throws FileNotFoundException, DocumentException, SQLException
	{
		Document document = new Document();
		PdfWriter.getInstance(document,new FileOutputStream("ReportClient" + y + ".pdf"));
		document.open();
		String[] str = new String[1024];
		
		int rows = 0;
		PdfPTable table = new PdfPTable(columns);
		table.setComplete(true);
		PdfPCell header = new PdfPCell(new Paragraph("Nume"));
		header.setBackgroundColor(BaseColor.CYAN);
		header.setBorderWidth(1);
		header.setHorizontalAlignment(1);
		PdfPCell header2 = new PdfPCell(new Paragraph("Adresa"));
		header2.setBackgroundColor(BaseColor.CYAN);
		header2.setBorderWidth(1);
		header2.setHorizontalAlignment(1);
		table.addCell(header);
		table.addCell(header2);
		while(rst.next())
		{
			rows++;
			for(int i = 1; i <= columns; i++)
			{
				Object obj = rst.getObject(i);
				PdfPCell c1 = new PdfPCell(new Paragraph(obj.toString()));
				c1.setBorderWidth(1);
				c1.setHorizontalAlignment(1);
				table.addCell(c1);
			}	
		}
		
		document.add(table);
		document.close();
	}
	
	/**
	 * 
	 * @param columns - Numarul de coloane ale tabelului
	 * @param rst - Obiect de tip ResultSet in care sunt stocate datele din tabelul product
	 * @param y - Contorul pentru denumirea fisierelor
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 * @throws SQLException
	 * 
	 * <p> Aceasta metoda genereaza un tabel cu produse, tabel populat cu ajutorul obiectului ResultSet, care extrage datele din tabelul product din MySql </p>
	 * 
	 */
	
	public void ProductTablePDF(int columns,ResultSet rst, int y) throws FileNotFoundException, DocumentException, SQLException
	{
		Document document = new Document();
		PdfWriter.getInstance(document,new FileOutputStream("ReportProduct" + y + ".pdf"));
		document.open();
		String[] str = new String[1024];
		
		int rows = 0;
		PdfPTable table = new PdfPTable(columns);
		table.setComplete(true);
		PdfPCell header = new PdfPCell(new Paragraph("Produs"));
		header.setBackgroundColor(BaseColor.YELLOW);
		header.setBorderWidth(1);
		header.setHorizontalAlignment(1);
		PdfPCell header2 = new PdfPCell(new Paragraph("Cantitate"));
		header2.setBackgroundColor(BaseColor.YELLOW);
		header2.setBorderWidth(1);
		header2.setHorizontalAlignment(1);
		PdfPCell header3 = new PdfPCell(new Paragraph("Pret buc."));
		header3.setBackgroundColor(BaseColor.YELLOW);
		header3.setBorderWidth(1);
		header3.setHorizontalAlignment(1);
		table.addCell(header);
		table.addCell(header2);
		table.addCell(header3);
		while(rst.next())
		{
			rows++;
			for(int i = 1; i <= columns; i++)
			{
				Object obj = rst.getObject(i);
				PdfPCell c1 = new PdfPCell(new Paragraph(obj.toString()));
				c1.setBorderWidth(1);
				c1.setHorizontalAlignment(1);
				table.addCell(c1);
			}	
		}
		
		document.add(table);
		document.close();
	}
	
	/**
	 * 
	 * @param columns - Numarul de coloane ale tabelului
	 * @param rst - Obiect de tip ResultSet in care sunt stocate datele din tabelul comanda
	 * @param y - Contorul pentru denumirea fisierelor
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 * @throws SQLException
	 * 
	 * <p> Aceasta metoda genereaza un tabel cu comenzi, tabel populat cu ajutorul obiectului ResultSet, care extrage datele din tabelul comanda din MySql </p>
	 * 
	 */
	
	public void OrderTablePDF(int columns,ResultSet rst, int y) throws FileNotFoundException, DocumentException, SQLException
	{
		Document document = new Document();
		PdfWriter.getInstance(document,new FileOutputStream("ReportOrder" + y + ".pdf"));
		document.open();
		String[] str = new String[1024];
		
		int rows = 0;
		PdfPTable table = new PdfPTable(columns);
		table.setComplete(true);
		PdfPCell header = new PdfPCell(new Paragraph("Nume Client"));
		header.setBackgroundColor(BaseColor.ORANGE);
		header.setBorderWidth(1);
		header.setHorizontalAlignment(1);
		PdfPCell header2 = new PdfPCell(new Paragraph("Produs"));
		header2.setBackgroundColor(BaseColor.ORANGE);
		header2.setBorderWidth(1);
		header2.setHorizontalAlignment(1);
		PdfPCell header3 = new PdfPCell(new Paragraph("Cantitate"));
		header3.setBackgroundColor(BaseColor.ORANGE);
		header3.setBorderWidth(1);
		header3.setHorizontalAlignment(1);
		PdfPCell header4 = new PdfPCell(new Paragraph("Pret total"));
		header4.setBackgroundColor(BaseColor.ORANGE);
		header4.setBorderWidth(1);
		header4.setHorizontalAlignment(1);
		table.addCell(header);
		table.addCell(header2);
		table.addCell(header3);
		table.addCell(header4);
		while(rst.next())
		{
			rows++;
			for(int i = 1; i <= columns; i++)
			{
				Object obj = rst.getObject(i);
				PdfPCell c1 = new PdfPCell(new Paragraph(obj.toString()));
				c1.setBorderWidth(1);
				c1.setHorizontalAlignment(1);
				table.addCell(c1);
			}	
		}
		
		document.add(table);
		document.close();
	}
}
