package util;

import java.awt.Color;
import java.awt.Font;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.border.Border;


public class SConstants
{

	
	public static final Date 			TODAYS_DATE 			= 		new Date();
		
	public static final String 			MY_COMPANY_NAME 		= 		"Unity Infotech Group (I) Pvt Ltd";
	public static final String 			PRPJECT_HEADING 		= 		"SAIKRUPA TRANSPORT";
	
	public static final Font 			FONT_COURRIER_BOLD_18 	= 		new Font("Courier", Font.BOLD,18);
	public static final Font 			FONT_COURRIER_BOLD_10 	= 		new Font("Courier", Font.BOLD,10);
	public static final Font 			FONT_COURRIER_BOLD_13 	= 		new Font("Courier", Font.BOLD,13);
	
	
	public static final int 			MAIN_WINDOW_WIDTH 		= 		950;
	public static final int 			MAIN_WINDOW_HEIGHT 		= 		702;
	
	public static final Border 			BORDER_BLUE_1 			= 		BorderFactory.createLineBorder(Color.BLUE, 1);
	

	// Admin Button and its constants
	public static final String[] 		ADMIN_BUTTONS_NAMES		= 		{"Add Customer","Edit Customer","Add Vehicle","Edit Vehicle","Add Duty Type","Edit Duty Type","Update"};
	
	public static  final String 		ADD_VEHICLE_BTN_STRING 				= 		"Add Vehicle";
	public static  final String 		EDIT_VEHICLE_BTN_STRING 			= 		"Edit Vehicle";
	
	public static  final String 		ADD_CUSTOMER_BTN_STRING		 		= 		"Add Customer";
	public static  final String 		EDIT_CUSTOMER_BTN_STRING 			= 		"Edit Customer";
	
	public static  final String 		ADD_DUTY_TYPE_BTN_STRING 			= 		"Add Duty Type";
	public static  final String 		EDIT_DUTY_TYPE_BTN_STRING 			= 		"Edit Duty Type";
	
	public static  final String 		UPDATE_BTN_STRING					= 		"Update";
	
	
	// Home Page Button and its constants
	public static final String[] 	HOME_BUTTONS_NAMES			= 		{"TAL Bill","Monthly Bill","Admin","History"};
	public static final String 		TAL_BILL_BTN_STRING 		= 		"TAL Bill";
	public static final String 		ADMIN_BTN_STRING 			= 		"Admin";
	public static final String 		MONTHLY_BOM_BTN_STRING 		= 		"Monthly Bill";
	
	
	
	// Application Labels string
	
	public static final String 	L_BILL_NO				=	"Bill No";
	public static final String 	L_TEL_CONTACT_NO		=	"Contact No.";
	public static final String 	L_TEL_BILL_DTAE			=	"Bill Date";
	public static final String 	L_PAN_NO				=	"PAN NO";
	public static final String 	L_E_MAIl				=	"E-Mail";
	public static final String 	L_VEHICLE_TYPE				=	"Vehicle Type";
	public static final String 	L_TO					=	"TO";
	public static final String 	L_START_DATE			=	"Start Date";
	public static final String	L_RETRUN_DATE			=	"Return Date";
	public static final String	L_VEHICLE_NO			=	"Vehicle No.";
	public static final String	L_VENDOR_CODE			=	"Vendor Code";
	public static final String	L_EMP_NAME				=	"Employee Name";
	public static final String	L_START_KM				=	"Start Km";
	public static final String	L_END_KM				=	"End Km";
	public static final String	L_AC_noAC				=	"AC\non-AC";
	public static final String	L_START_TIME			=	"Start Time";
	public static final String	L_END_TIME				=	"End Time";
	public static final String	L_TOTAL_KM				=	"Total Km";
	public static final String	L_DUTY_TYPE				= 	"Duty Type";
	public static final String	L_TOTAL_PKG_KM			=	"Total Pkg Km";
	public static final String	L_Total_Distance		=	"Total  Km";
	public static final String	L_RATE					=	"Pkg. Rate";
	public static final String	L_Extra_Rate			=	"Extra Rate/Km";
	public static final String	L_PKG_Rate				=	"Pkg. Rate";
	public static final String	L_AMOUNT				=	"Pkg. Amount";
	public static final String	L_EXTRA_KM 				= 	"Extra Distance Travelled(Km)";
	public static final String	L_TOTAL_EXTRA_KM		=	"Total Ex. Km";
	public static final String	L_EXTRA_KM_RATE			=	"Ex. Rate/Km";
	public static final String	L_EXTRA_AMOUNT			=	"Ex. Amount";
	public static final String	L_TOLL_AMOUNT			= 	"Toll";
	public static final String	L_NIGHT_HALT_AMOUNT 	= 	"Night Halt";
	public static final String	L_AMOUNTS_IN_WORDS		= 	"Amount In Words";
	public static final String	L_GROSS_AMOUNT 			= 	"Total Amount To Pay";
	public static final String	L_GENERATE_EXCEL_CHECK	=	"Excel";
	
	
	public static final String	L_key					=	"Key";
	public static final String	L_value					=	"Value";
	public static final String	L_spinnermodel 			= 	"spinnermodel";
	public static final String	L_spinner 				= 	"spinner";
	public static final String	L_Picker				=	"Picker";
	
	
	// Label with values
	public static final String	V_CLIENT_ADDRESS		=	"S NO 74/7 RAJANIGANDHA HOUSING SOCIETY, WALHEKARWADI, CHINCHWAD, PUNE - 411033";
	public static final String	V_CLIENT_MOB1 			= 	"9623121570";
	public static final String	V_PAN_NO 				= 	"AKHPN9505G";
	public static final String	V_E_Mail				=	"nichit.a.sachin@gmail.com";
	
	// DUTY TYPE UI buttons string
	public static final String 	EDIT_BTN_STRING			=	"Edit";
	public static final String 	CANCEL_BTN_STRING 		= 	"Cancel";
	public static final String 	ADD_BTN_STRING 			= 	"Add";
	
	// TAL BILL buttons string
	public static final String	GET_TOTAL_BTN_STRING		=	"Get Total";
	
	// Admin UI buttons string
	//public static final String	ID_BTN_ADD_DUTY_TYPE	=	"Add Duty Type";
	
	
	// TAL Bill UI Combo IDs
	
	
	public static final String	ID_VEHICLE_TYPE_COMBO				=	"VTC";
	public static final String	ID_DUTY_TYPE_COMBO					=	"DTC";
	public static final String	ID_TO_CUSTOMER_COMBO 				= 	"TCC";
	public static final String	ID_SELECT_DUTY_TYPE_COMBO			= 	"SDTC";
	
	public static final String	ID_CUSTOMER_SELECT_DUTY_TYPE_COMBO	=	"DTCCS";
	public static final String	ID_CUSTOMER_SELECT_TELCO_BILL_UI	=	"CSTBU";
	public static final String	ID_DUTY_TYPE_COMBO_ON_TELCO_BILL 	= 	"DTCOTB";

	public static final String ID_VEHICLE_SELECT_DUTY_TYPE_COMBO 	= 	"";

	
	// Telco Bill Combo Ids
	public static final String COMBO_TAL_BILL_SELECT_VEHICLE_TYPE 	= "CTBSVT";
	public static final String COMBO_TAL_BILL_SELECT_CUSTOMER 		= "CTBSC";
	public static final String COMBO_TAL_BILL_SELECT_DUTY_TYPE		= "CTBSDT";

	
	// Edit Duty Type combo ids
	public static final String COMBO_EDIT_DUTY_TYPE_SELCT_DUTY_TYPE = "CEDTSDT";

	
	
	// EXCEPTIONS STRING
	public static final String	 	E_KM_EXCEPTION_STRING 				=  "Total KM Should Be Greater Than Zero(0), Start KM Should be less than End KM";
	public static final String		E_LOGIN_EXCEPTION_STRING 			= 	"InValid Credentials...please try again";

	
	// Mothly BOM UI Btns Strings
	public static final String 		GENEARTE_BILL_BTN_STRING 			= 	"Bill";

	public static final String 		REMOVE_BTN_STRING 					= 	"Remove";

	
}
