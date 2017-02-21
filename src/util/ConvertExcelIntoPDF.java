package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ConvertExcelIntoPDF 
{
	public void convert(File Excel)
	{
        try {
			FileInputStream input_document = new FileInputStream(Excel);

			// Read workbook into HSSFWorkbook
			HSSFWorkbook my_xls_workbook = new HSSFWorkbook(input_document);

			// Read worksheet into HSSFSheet
			HSSFSheet my_worksheet = my_xls_workbook.getSheetAt(0);

			// To iterate over the rows
			Iterator<Row> rowIterator = my_worksheet.iterator();

			//We will create output PDF document objects at this point
			com.itextpdf.text.Document iText_xls_2_pdf = new com.itextpdf.text.Document();
			
			File pdfFile= new File("C:\\Bills\\test.pdf");

			if(!pdfFile.exists())
				pdfFile.createNewFile();
			PdfWriter.getInstance(iText_xls_2_pdf, new FileOutputStream(pdfFile));

			iText_xls_2_pdf.open();

			//we have 5 columns in the Excel sheet, so we create a PDF table with 5 columns; Note: There are ways to make this dynamic in nature, if you want to.
			PdfPTable my_table = new PdfPTable(9);

			//We will use the object below to dynamically add new data to the table
			PdfPCell table_cell;

			//Loop through rows.
			while(rowIterator.hasNext())
			       {
			        Row rowi = rowIterator.next();

			        Iterator<Cell> cellIterator = rowi.cellIterator();

			               while(cellIterator.hasNext())
			               {
			                       Cell celli = cellIterator.next(); //Fetch CELL

			                       switch(celli.getCellType())
			                       {
			                               //Identify CELL type you need to add more code here based on your requirement / transformations
			                        case Cell.CELL_TYPE_STRING:

			                               //Push the data from Excel to PDF Cell
			                               table_cell = new PdfPCell(new Phrase(celli.getStringCellValue()));

			                               //move the code below to suit to your needs
			                               my_table.addCell(table_cell);

			                               break;

			                               case Cell.CELL_TYPE_NUMERIC:

			                               //Push the data from Excel to PDF Cell
			                               table_cell = new PdfPCell(new Phrase("" + celli.getNumericCellValue()));

			                               //move the code below to suit to your needs
			                               my_table.addCell(table_cell);

			                               break;
			                       }
			                       //next line
			               }
			}

			//Finally add the table to PDF document
			iText_xls_2_pdf.add(my_table);
			iText_xls_2_pdf.close();

			//we created our pdf file..
			input_document.close(); //close xls  
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
