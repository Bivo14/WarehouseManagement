package start;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.itextpdf.text.DocumentException;

import model.Client;
import model.Warehouse;
import presentation.PDFCreator;
import presentation.ParserClass;

public class MainClass 
{	
	protected static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());
	public static int y;
	public static int c1;
	public static int c2;
	public static int c3;
	
	public static void main(String[] args) throws SQLException, IOException, DocumentException
	{	
		y = 0;
		c1 = 1;
		c2 = 1;
		c3 = 1;
		PDFCreator pdf = new PDFCreator();	
		ParserClass ps = new ParserClass(args);
		System.out.println(ps.parse(y, c1, c2, c3));
	}
}
