package uit.billgen.util;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class ExcelUtils
{

	private HSSFWorkbook workbook = null;
	public static final int RIGHT_ALIGNMENT = 1;
	public static final int LEFT_ALIGNMENT = 2;
	public static final int CENTER_ALIGNMENT = 3;
	public static final int TOP_ALIGN = 0;
	
	public ExcelUtils(HSSFWorkbook workbook)
	{
		this.workbook = workbook;
	}
	public HSSFFont getExcelCellFont_Arial_Blue_Bold( short i)
	{
		HSSFFont font = workbook.createFont();
	    font.setFontName(HSSFFont.FONT_ARIAL);
	    font.setFontHeightInPoints(i);
	    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	    font.setColor(HSSFColor.BLUE.index);
	    return font;
	}
	public HSSFCellStyle getExcelCellTextAlinmentStyle(int alignment)
	{
		HSSFCellStyle contactNoRowStyle = workbook.createCellStyle();
		switch (alignment)
		{
			case RIGHT_ALIGNMENT:
				contactNoRowStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			break;
			case LEFT_ALIGNMENT:
				contactNoRowStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			break;
			case CENTER_ALIGNMENT:
				contactNoRowStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			break;
			/*case TOP_ALIGN:
				contactNoRowStyle.setAlignment(HSSFCellStyle.ALIGN_FILL);*/

		default:
			break;
		}
        
		return contactNoRowStyle;
	}
	public HSSFCellStyle getExcelHeaderRowStyle()
	{
		HSSFCellStyle headerRowStyle = workbook.createCellStyle();
        headerRowStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
        headerRowStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);            
        headerRowStyle.setFont(getExcelCellFont_Arial_Blue_Bold((short)20));
        
        return headerRowStyle;
	}
	public HSSFCellStyle getExcelRowCenterTextBoldFontStyle(short value)
	{
		HSSFCellStyle headerRowStyle = workbook.createCellStyle();
        //headerRowStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
        headerRowStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);            
        headerRowStyle.setFont(getExcelCellFont_Arial_Blue_Bold(value));
        
        return headerRowStyle;
	}
}
