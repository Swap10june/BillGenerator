package util;
import java.awt.Desktop;
import  java.io.*;

import  org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import  org.apache.poi.hssf.usermodel.HSSFRow;
import  org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.util.CellRangeAddress;

import beans.BOM;

public class CreateExlFile
{
	private ExcelUtils utility = null;
	private Registry reg = SConstants.reg;
	
    public  void CreateBOMExcel(File filename,BOM bom)
    {
        try
        {
        	if(!filename.exists())
        		filename.createNewFile();
            HSSFWorkbook workbook = new HSSFWorkbook();
            utility  = new ExcelUtils(workbook);
            HSSFSheet sheet = workbook.createSheet("Bill"); 
            int width = sheet.getColumnWidth(8)+sheet.getColumnWidth(8)/2;
            sheet.setColumnWidth(8, width+100);
            // Header Row ---0
            int row = 0;
            HSSFRow headerRow = sheet.createRow((short)row);
            CellRangeAddress headerRowSpan = CellRangeAddress.valueOf("A1:I1");
            sheet.addMergedRegion(headerRowSpan);
            
            
            HSSFCell headerRow_cell0 = headerRow.createCell(0);
            headerRow_cell0.setCellValue("SIAKRUPA TRANSPORT");
            headerRow_cell0.setCellStyle(utility.getExcelHeaderRowStyle());
        
            // ADRESS ROW --1
            row =1;
            HSSFRow addressRow = sheet.createRow((short)row);
            CellRangeAddress addressSpan = CellRangeAddress.valueOf("A2:I2");
            sheet.addMergedRegion(addressSpan);
            
            
            HSSFCell addressRowSpan_cell0 = addressRow.createCell(0);
            String address = reg.getValueFor("V_CLIENT_ADDRESS");
            addressRowSpan_cell0.setCellValue(address);
            addressRowSpan_cell0.setCellStyle(utility.getExcelRowCenterTextBoldFontStyle((short)10));
            
          // Bill no and Contact No row ---2
            row =2;
            HSSFRow billNoContactNoRow = sheet.createRow((short)row);
            CellRangeAddress billNoRowSpan = CellRangeAddress.valueOf("A3:E3");
            sheet.addMergedRegion(billNoRowSpan);
            
            HSSFCell billNoRowSpan_cell0 = billNoContactNoRow.createCell(0);
            String BillNo = "Bill No:"+"   "+bom.getBillNumber();
            billNoRowSpan_cell0.setCellValue(BillNo);
            
            
            CellRangeAddress contactNoRowSpan = CellRangeAddress.valueOf("F3:I3");
            sheet.addMergedRegion(contactNoRowSpan);
            
            HSSFCell billNoRowSpan_cell1 = billNoContactNoRow.createCell(5);
            String contact = "Mobile No:"+"   "+bom.getContactNumber();
            billNoRowSpan_cell1.setCellValue(contact);
            billNoRowSpan_cell1.setCellStyle(utility.getExcelCellTextAlinmentStyle(ExcelUtils.RIGHT_ALIGNMENT));
            
            //PAN NO and EMAIL Row--3
            row = 3;
            HSSFRow PanRow = sheet.createRow((short)row);
            CellRangeAddress PanRowSpan = CellRangeAddress.valueOf("A4:E4");
            sheet.addMergedRegion(PanRowSpan);
            
            CellRangeAddress emailRowSpan = CellRangeAddress.valueOf("F4:I4");
            sheet.addMergedRegion(emailRowSpan);
            
            
            HSSFCell PanRowSpan_cell0 = PanRow.createCell(0);
            String pan = "PAN:"+"   "+reg.getValueFor("V_PAN_NO");
            PanRowSpan_cell0.setCellValue(pan);
            
            HSSFCell PanRowSpan_cell2 = PanRow.createCell(5);
            String email = "E-Mail:"+"   "+bom.getEmail();
            PanRowSpan_cell2.setCellValue(email);
            PanRowSpan_cell2.setCellStyle(utility.getExcelCellTextAlinmentStyle(ExcelUtils.RIGHT_ALIGNMENT));
            
            //  empty row -- 4
            //TO AND BILL DATE Row--5
            row = 5;
            HSSFRow CustomerRow = sheet.createRow((short)row);
            CellRangeAddress CustomerRowSpan = CellRangeAddress.valueOf("A6:D6");
            sheet.addMergedRegion(CustomerRowSpan);
            CellRangeAddress CustomerColSpan = CellRangeAddress.valueOf("A6:A12");
            sheet.addMergedRegion(CustomerColSpan);
            
            HSSFCell CustomerRow_cell0 = CustomerRow.createCell(0);
            String to = "TO:"+"   "+bom.getCustomerName();
            CustomerRow_cell0.setCellValue(to);
            CustomerRow_cell0.setCellStyle(utility.getExcelCellTextAlinmentStyle(ExcelUtils.CENTER_ALIGNMENT));
                        
            CellRangeAddress billDateRowSpan = CellRangeAddress.valueOf("E6:F6");
            sheet.addMergedRegion(billDateRowSpan);
            
            HSSFCell CustomerRow_cell1 = CustomerRow.createCell(4);
            String dats = "Bill Date:";
            CustomerRow_cell1.setCellValue(dats);
            
            CellRangeAddress billDateValueRowSpan = CellRangeAddress.valueOf("G6:I6");
            sheet.addMergedRegion(billDateValueRowSpan);
            HSSFCell CustomerRow_cell2 = CustomerRow.createCell(6);
            String billDateValue = bom.getBillDate();
            CustomerRow_cell2.setCellValue(billDateValue);
            
            //Row - 6
            CellRangeAddress startDateRowSpan = CellRangeAddress.valueOf("E7:F7");
            sheet.addMergedRegion(startDateRowSpan);
            HSSFRow DatesRow6 = sheet.createRow((short)6);
            HSSFCell DatesRow6_0 = DatesRow6.createCell(4);
            String startDate = "Date Of Travels:";
            DatesRow6_0.setCellValue(startDate);
            
            CellRangeAddress startDateValueRowSpan = CellRangeAddress.valueOf("G7:I7");
            sheet.addMergedRegion(startDateValueRowSpan);
            HSSFCell DatesRow6_cell1 = DatesRow6.createCell(6);
            String startDateValue = bom.getDateOfTravels();
            DatesRow6_cell1.setCellValue(startDateValue);
            
          //Row - 7
            CellRangeAddress dateReturnRowSpan = CellRangeAddress.valueOf("E8:F8");
            sheet.addMergedRegion(dateReturnRowSpan);
            HSSFRow DatesRow7 = sheet.createRow((short)7);
            HSSFCell DatesRow7_0 = DatesRow7.createCell(4);
            String dateReturn = "Date Of Return:";
            DatesRow7_0.setCellValue(dateReturn);
            
            CellRangeAddress dateReturnvalueRowSpan = CellRangeAddress.valueOf("G8:I8");
            sheet.addMergedRegion(dateReturnvalueRowSpan);
            HSSFCell DatesRow7_1 = DatesRow7.createCell(6);
            String dateReturnValue = bom.getDateOfReturn();
            DatesRow7_1.setCellValue(dateReturnValue);
            
          //Row - 8
            CellRangeAddress typeOfVehicleRowSpan = CellRangeAddress.valueOf("E9:F9");
            sheet.addMergedRegion(typeOfVehicleRowSpan);
            HSSFRow DatesRow8 = sheet.createRow((short)8);
            HSSFCell DatesRow8_0 = DatesRow8.createCell(4);
            String typeOfVehicle = "Vehicle Type:";
            DatesRow8_0.setCellValue(typeOfVehicle);
            
            CellRangeAddress typeOfVehicleValueRowSpan = CellRangeAddress.valueOf("G9:I9");
            sheet.addMergedRegion(typeOfVehicleValueRowSpan);
            HSSFCell DatesRow8_1 = DatesRow8.createCell(6);
            String typeOfVehicleValue = bom.getTypeOfVehicle();
            DatesRow8_1.setCellValue(typeOfVehicleValue);
            
          //Row - 9
            CellRangeAddress vehNumberRowSpan = CellRangeAddress.valueOf("E10:F10");
            sheet.addMergedRegion(vehNumberRowSpan);
            HSSFRow DatesRow9 = sheet.createRow((short)9);
            HSSFCell DatesRow9_0 = DatesRow9.createCell(4);
            String vehNumber = "Vehicle Number:";
            DatesRow9_0.setCellValue(vehNumber);
            
            CellRangeAddress vechicleNumberRowSpan  = CellRangeAddress.valueOf("G10:I10");
            sheet.addMergedRegion(vechicleNumberRowSpan);
            HSSFCell DatesRow9_1 = DatesRow9.createCell(6);
            String vechicleNumberValue = bom.getVehicleNumber();
            DatesRow9_1.setCellValue(vechicleNumberValue);
            
          //Row - 10
            CellRangeAddress vendorCodeRowSpan = CellRangeAddress.valueOf("E11:F11");
            sheet.addMergedRegion(vendorCodeRowSpan);
            HSSFRow DatesRow10 = sheet.createRow((short)10);
            HSSFCell DatesRow10_0 = DatesRow10.createCell(4);
            String vendorCode = "Vendor Code:";
            DatesRow10_0.setCellValue(vendorCode);
           
            CellRangeAddress vendorCodeValueRowSpan  = CellRangeAddress.valueOf("G11:I11");
            sheet.addMergedRegion(vendorCodeValueRowSpan);
            HSSFCell DatesRow10_1 = DatesRow10.createCell(6);
            String vendorCodeValue = bom.getVendorCode();
            DatesRow10_1.setCellValue(vendorCodeValue);
            
            // Empty Row 11
            // ROW-12
            CellRangeAddress employeeNameRowSpan = CellRangeAddress.valueOf("A13:D13");
            sheet.addMergedRegion(employeeNameRowSpan);
            CellRangeAddress employeeNameColSpan = CellRangeAddress.valueOf("A13:A15");
            sheet.addMergedRegion(employeeNameColSpan);
            
            HSSFRow DatesRow11 = sheet.createRow((short)12);
            HSSFCell DatesRow11_0 = DatesRow11.createCell(0);
            String employeeName = "Employee Name:    " +bom.getCustomerName();
            DatesRow11_0.setCellValue(employeeName);
           
            CellRangeAddress KMRowSpan = CellRangeAddress.valueOf("F13:G13");
            sheet.addMergedRegion(KMRowSpan);
            HSSFCell DatesRow11_2 = DatesRow11.createCell(5);
            DatesRow11_2.setCellValue("KM");
            DatesRow11_2.setCellStyle(utility.getExcelCellTextAlinmentStyle(ExcelUtils.CENTER_ALIGNMENT));
            
            CellRangeAddress timeRowSpan = CellRangeAddress.valueOf("H13:I13");
            sheet.addMergedRegion(timeRowSpan);
            HSSFCell DatesRow11_4 = DatesRow11.createCell(7);
            DatesRow11_4.setCellValue("TIME");
            DatesRow11_4.setCellStyle(utility.getExcelCellTextAlinmentStyle(ExcelUtils.CENTER_ALIGNMENT));
            
         // ROW-13
            
            HSSFRow DatesRow13 = sheet.createRow((short)13);
            
            HSSFCell DatesRow13_1 = DatesRow13.createCell(4);
            DatesRow13_1.setCellValue("Starting");
            
            CellRangeAddress KMValueRowSpan = CellRangeAddress.valueOf("F14:G14");
            sheet.addMergedRegion(KMValueRowSpan);
            HSSFCell DatesRow13_2 = DatesRow13.createCell(5);
            DatesRow13_2.setCellValue(bom.getStartKM());
            DatesRow13_2.setCellStyle(utility.getExcelCellTextAlinmentStyle(ExcelUtils.CENTER_ALIGNMENT));
            
            CellRangeAddress timeValueRowSpan = CellRangeAddress.valueOf("H14:I14");
            sheet.addMergedRegion(timeValueRowSpan);
            HSSFCell DatesRow13_4 = DatesRow13.createCell(7);
            DatesRow13_4.setCellValue(bom.getStartTime());
            DatesRow13_4.setCellStyle(utility.getExcelCellTextAlinmentStyle(ExcelUtils.CENTER_ALIGNMENT));
            
            // ROW-14
            
            HSSFRow DatesRow14 = sheet.createRow((short)14);
            
            HSSFCell DatesRow14_1 = DatesRow14.createCell(4);
            DatesRow14_1.setCellValue("Closing");
            
            CellRangeAddress closKMValueRowSpan = CellRangeAddress.valueOf("F15:G15");
            sheet.addMergedRegion(closKMValueRowSpan);
            HSSFCell DatesRow14_2 = DatesRow14.createCell(5);
            DatesRow14_2.setCellValue(bom.getEndKM());
            DatesRow14_2.setCellStyle(utility.getExcelCellTextAlinmentStyle(ExcelUtils.CENTER_ALIGNMENT));
            
            CellRangeAddress closetimeValueRowSpan = CellRangeAddress.valueOf("H15:I15");
            sheet.addMergedRegion(closetimeValueRowSpan);
            HSSFCell DatesRow14_4 = DatesRow14.createCell(7);
            DatesRow14_4.setCellValue(bom.getEndTime());
            DatesRow14_4.setCellStyle(utility.getExcelCellTextAlinmentStyle(ExcelUtils.CENTER_ALIGNMENT));
            
            //Row -- 15
            CellRangeAddress packageNameRowSpan = CellRangeAddress.valueOf("A16:D16");
            sheet.addMergedRegion(packageNameRowSpan);
            CellRangeAddress packageNameColSpan = CellRangeAddress.valueOf("A16:A17");
            sheet.addMergedRegion(packageNameColSpan);
            
            HSSFRow packageNameRow15 = sheet.createRow((short)15);
            HSSFCell packageNameRow15_0 = packageNameRow15.createCell(0);
            String packageName = "Package Name:    " +bom.getPackageType();
            packageNameRow15_0.setCellValue(packageName);
            
            HSSFCell packageNameRow15_1 = packageNameRow15.createCell(4);
            packageNameRow15_1.setCellValue("Total");
            
            CellRangeAddress packageNameValueRowSpan = CellRangeAddress.valueOf("F16:I16");
            sheet.addMergedRegion(packageNameValueRowSpan);
            
            HSSFCell packageNameRow15_2 = packageNameRow15.createCell(5);
            packageNameRow15_2.setCellValue(bom.getTotalKM());
            
            CellRangeAddress acRowSpan = CellRangeAddress.valueOf("E17:I17");
            sheet.addMergedRegion(acRowSpan);
            HSSFRow packageNameRow16 = sheet.createRow((short)16);
            HSSFCell packageNameRow16_1 = packageNameRow16.createCell(4);
            String acNonAc = "AC:";
            packageNameRow16_1.setCellValue(acNonAc);
            
            // empty row ---17
            
            // row 18 
            CellRangeAddress TotalRateAndAmountRowSpan = CellRangeAddress.valueOf("A19:D19");
            sheet.addMergedRegion(TotalRateAndAmountRowSpan);
            HSSFRow DatesRow18 = sheet.createRow((short)18);
            
            HSSFCell DatesRow18_1 = DatesRow18.createCell(4);
            DatesRow18_1.setCellValue("Total");
            DatesRow18_1.setCellStyle(utility.getExcelCellTextAlinmentStyle(ExcelUtils.CENTER_ALIGNMENT));
            
            CellRangeAddress RateValueRowSpan = CellRangeAddress.valueOf("F19:G19");
            sheet.addMergedRegion(RateValueRowSpan);
            HSSFCell DatesRow18_2 = DatesRow18.createCell(5);
            DatesRow18_2.setCellValue("Rate");
            DatesRow18_2.setCellStyle(utility.getExcelCellTextAlinmentStyle(ExcelUtils.CENTER_ALIGNMENT));
            
            CellRangeAddress AmountValueRowSpan = CellRangeAddress.valueOf("H19:I19");
            sheet.addMergedRegion(AmountValueRowSpan);
            HSSFCell DatesRow18_4 = DatesRow18.createCell(7);
            DatesRow18_4.setCellValue("Amount(RS.)");
            DatesRow18_4.setCellStyle(utility.getExcelCellTextAlinmentStyle(ExcelUtils.CENTER_ALIGNMENT));
            
            
            // Row 19
            CellRangeAddress dutyTypeRowSpan = CellRangeAddress.valueOf("A20:B20");
            sheet.addMergedRegion(dutyTypeRowSpan);
            HSSFRow DatesRow19 = sheet.createRow((short)19);
            
            HSSFCell DatesRow19_0 = DatesRow19.createCell(0);
            DatesRow19_0.setCellValue("Duty Type :");
            
            CellRangeAddress dutyTypeValueRowSpan = CellRangeAddress.valueOf("C20:D20");
            sheet.addMergedRegion(dutyTypeValueRowSpan);
            
            HSSFCell DatesRow19_1 = DatesRow19.createCell(2);
            DatesRow19_1.setCellValue(bom.getDutyType());
                        
            HSSFCell DatesRow19_2 = DatesRow19.createCell(4);
            DatesRow19_2.setCellValue(bom.getPackageKM());
            DatesRow19_2.setCellStyle(utility.getExcelCellTextAlinmentStyle(ExcelUtils.CENTER_ALIGNMENT));
            
            CellRangeAddress rateValueRowSpan = CellRangeAddress.valueOf("F20:G20");
            sheet.addMergedRegion(rateValueRowSpan);
            HSSFCell DatesRow19_3 = DatesRow19.createCell(5);
            DatesRow19_3.setCellValue(bom.getPackageRate());
            DatesRow19_3.setCellStyle(utility.getExcelCellTextAlinmentStyle(ExcelUtils.CENTER_ALIGNMENT));
            
            CellRangeAddress amountValueRowSpan = CellRangeAddress.valueOf("H20:I20");
            sheet.addMergedRegion(amountValueRowSpan);
            HSSFCell DatesRow19_4 = DatesRow19.createCell(7);
            DatesRow19_4.setCellValue(bom.getPackageRate());
            DatesRow19_4.setCellStyle(utility.getExcelCellTextAlinmentStyle(ExcelUtils.CENTER_ALIGNMENT));
            
         // Row 20
            CellRangeAddress extraKMRowSpan = CellRangeAddress.valueOf("A21:D21");
            sheet.addMergedRegion(extraKMRowSpan);
            HSSFRow DatesRow20 = sheet.createRow((short)20);
            
            HSSFCell DatesRow20_0 = DatesRow20.createCell(0);
            DatesRow20_0.setCellValue("Extra KM :");
            
            HSSFCell DatesRow20_2 = DatesRow20.createCell(4);
            DatesRow20_2.setCellValue(bom.getExtraKM());
            DatesRow20_2.setCellStyle(utility.getExcelCellTextAlinmentStyle(ExcelUtils.CENTER_ALIGNMENT));
            
            CellRangeAddress extrarateValueRowSpan = CellRangeAddress.valueOf("F21:G21");
            sheet.addMergedRegion(extrarateValueRowSpan);
            HSSFCell DatesRow20_3 = DatesRow20.createCell(5);
            DatesRow20_3.setCellValue(bom.getExtraRate());
            DatesRow20_3.setCellStyle(utility.getExcelCellTextAlinmentStyle(ExcelUtils.CENTER_ALIGNMENT));
            
            CellRangeAddress extraAmountValueRowSpan = CellRangeAddress.valueOf("H21:I21");
            sheet.addMergedRegion(extraAmountValueRowSpan);
            HSSFCell DatesRow20_4 = DatesRow20.createCell(7);
            DatesRow20_4.setCellValue(bom.getExtraTotalAmount());
            DatesRow20_4.setCellStyle(utility.getExcelCellTextAlinmentStyle(ExcelUtils.CENTER_ALIGNMENT));
            
            
         // Row 21
            CellRangeAddress extraTimePerHrsRowSpan = CellRangeAddress.valueOf("A22:D22");
            sheet.addMergedRegion(extraTimePerHrsRowSpan);
            HSSFRow DatesRow21 = sheet.createRow((short)21);
            
            HSSFCell DatesRow21_0 = DatesRow21.createCell(0);
            DatesRow21_0.setCellValue("Extra Time/Hrs :");
            
            HSSFCell DatesRow21_2 = DatesRow21.createCell(4);
            String extraTimePerHrs = (bom.getExtraTimeHours()==null)?"0":bom.getExtraTimeHours();
            DatesRow21_2.setCellValue(extraTimePerHrs);
            DatesRow21_2.setCellStyle(utility.getExcelCellTextAlinmentStyle(ExcelUtils.CENTER_ALIGNMENT));
            
            CellRangeAddress a = CellRangeAddress.valueOf("F22:G22");
            sheet.addMergedRegion(a);
            HSSFCell DatesRow21_3 = DatesRow21.createCell(5);
            DatesRow21_3.setCellValue("0");
            DatesRow21_3.setCellStyle(utility.getExcelCellTextAlinmentStyle(ExcelUtils.CENTER_ALIGNMENT));
            
            CellRangeAddress b = CellRangeAddress.valueOf("H22:I22");
            sheet.addMergedRegion(b);
            HSSFCell DatesRow21_4 = DatesRow21.createCell(7);
            DatesRow21_4.setCellValue("0");
            DatesRow21_4.setCellStyle(utility.getExcelCellTextAlinmentStyle(ExcelUtils.CENTER_ALIGNMENT));
            
         // Row 22
            CellRangeAddress c = CellRangeAddress.valueOf("A23:D23");
            sheet.addMergedRegion(c);
            HSSFRow DatesRow22 = sheet.createRow((short)22);
            
            HSSFCell DatesRow22_0 = DatesRow22.createCell(0);
            DatesRow22_0.setCellValue("Toll Charges :");
            
            CellRangeAddress d = CellRangeAddress.valueOf("H23:I23");
            sheet.addMergedRegion(d);
            HSSFCell DatesRow22_2 = DatesRow22.createCell(7);
            String toll = (bom.getTollCharges()==null)?"0":bom.getTollCharges();
            DatesRow22_2.setCellValue(toll);
            DatesRow22_2.setCellStyle(utility.getExcelCellTextAlinmentStyle(ExcelUtils.CENTER_ALIGNMENT));
            
            // Row 23 night halt
            CellRangeAddress e = CellRangeAddress.valueOf("A24:D24");
            sheet.addMergedRegion(e);
            HSSFRow DatesRow23 = sheet.createRow((short)23);
            
            HSSFCell DatesRow23_0 = DatesRow23.createCell(0);
            DatesRow23_0.setCellValue("Night Halt Charges :");
            
            CellRangeAddress f = CellRangeAddress.valueOf("H24:I24");
            sheet.addMergedRegion(f);
            HSSFCell DatesRow23_2 = DatesRow23.createCell(7);
            String nightHalt = (bom.getNightHaltRate()==null)?"0":bom.getNightHaltRate();
            DatesRow23_2.setCellValue(nightHalt);
            DatesRow23_2.setCellStyle(utility.getExcelCellTextAlinmentStyle(ExcelUtils.CENTER_ALIGNMENT));
            
            //empty row 24
            // row 25
            HSSFRow DatesRow25 = sheet.createRow((short)25);
            CellRangeAddress g = CellRangeAddress.valueOf("F26:G26");
            sheet.addMergedRegion(g);
            HSSFCell DatesRow25_2 = DatesRow25.createCell(5);
            DatesRow25_2.setCellValue("Total");
            DatesRow25_2.setCellStyle(utility.getExcelCellTextAlinmentStyle(ExcelUtils.CENTER_ALIGNMENT));
            
            CellRangeAddress h = CellRangeAddress.valueOf("H26:I26");
            sheet.addMergedRegion(h);
            HSSFCell DatesRow25_4 = DatesRow25.createCell(7);
            DatesRow25_4.setCellValue(bom.getTotalWithoutTax());
            DatesRow25_4.setCellStyle(utility.getExcelCellTextAlinmentStyle(ExcelUtils.CENTER_ALIGNMENT));
            
          //empty row 26
            // row 27
            HSSFRow DatesRow27 = sheet.createRow((short)27);
            CellRangeAddress i = CellRangeAddress.valueOf("F28:G28");
            sheet.addMergedRegion(i);
            HSSFCell DatesRow27_2 = DatesRow27.createCell(5);
            DatesRow27_2.setCellValue("Service Tax");
            DatesRow27_2.setCellStyle(utility.getExcelCellTextAlinmentStyle(ExcelUtils.CENTER_ALIGNMENT));
            
            CellRangeAddress j = CellRangeAddress.valueOf("H28:I28");
            sheet.addMergedRegion(j);
            HSSFCell DatesRow27_4 = DatesRow27.createCell(7);
            DatesRow27_4.setCellValue(bom.getServiceTaxCarges());
            DatesRow27_4.setCellStyle(utility.getExcelCellTextAlinmentStyle(ExcelUtils.CENTER_ALIGNMENT));
            
            //empty row 28
            // row 29
            HSSFRow DatesRow29 = sheet.createRow((short)29);
            CellRangeAddress k = CellRangeAddress.valueOf("F30:G30");
            sheet.addMergedRegion(k);
            HSSFCell DatesRow29_2 = DatesRow29.createCell(5);
            DatesRow29_2.setCellValue("Grand Total");
            DatesRow29_2.setCellStyle(utility.getExcelCellTextAlinmentStyle(ExcelUtils.CENTER_ALIGNMENT));
            DatesRow29_2.setCellStyle(utility.getExcelRowCenterTextBoldFontStyle((short) 10));
            
            CellRangeAddress l = CellRangeAddress.valueOf("H30:I30");
            sheet.addMergedRegion(l);
            HSSFCell DatesRow29_4 = DatesRow29.createCell(7);
            DatesRow29_4.setCellValue(bom.getGrandTotal());
            DatesRow29_4.setCellStyle(utility.getExcelCellTextAlinmentStyle(ExcelUtils.CENTER_ALIGNMENT));
            DatesRow29_4.setCellStyle(utility.getExcelRowCenterTextBoldFontStyle((short) 10));
            
            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            fileOut.close();
            //new ConvertExcelIntoPDF().convert(filename);
            System.out.println("Your excel file has been generated!");

            if(filename.exists())
            	Desktop.getDesktop().open(filename);
        } catch ( Exception ex )
        {
            System.out.println(ex);
        }
    }
}