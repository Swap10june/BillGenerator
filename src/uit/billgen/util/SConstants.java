package uit.billgen.util;

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
	public static final String[] 	HOME_BUTTONS_NAMES			= 		{"Extra Cab Bill","Monthly Bill","Admin","History","Update"};
	public static final String 		TAL_BILL_BTN_STRING 		= 		"Extra Cab Bill";
	public static final String 		ADMIN_BTN_STRING 			= 		"Admin";
	public static final String 		MONTHLY_BOM_BTN_STRING 		= 		"Monthly Bill";
	public static final String 		HISTORY_BTN_STRING 			= 		"History";
	//public static final String 		UPDATE_BTN_STRING			=		"Update";
	
	
	
	// Application Labels string
	
	public static final String 	L_BILL_NO				=	"Bill No";
	public static final String 	L_TEL_CONTACT_NO		=	"Contact No.";
	public static final String 	L_TEL_BILL_DTAE			=	"Bill Date";
	public static final String 	L_PAN_NO				=	"PAN NO";
	public static final String 	L_E_MAIl				=	"E-Mail";
	public static final String 	L_VEHICLE_TYPE			=	"Vehicle Type";
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
	public static final String 	L_EXTRA_HOUR 			= 	"Extra Time Travelled(Hour)";
	public static final String	L_TOTAL_EXTRA_KM		=	"Total Ex. Km";
	public static final String	L_EXTRA_KM_RATE			=	"Ex. Rate/Km";
	public static final String	L_EXTRA_AMOUNT			=	"Ex. Amount";
	
	public static final String	L_TOTAL_EXTRA_HOUR		=	"Total Ex. Hour";
	public static final String	L_EXTRA_HOUR_RATE		=	"Ex. Rs/Hour";
	
	public static final String	L_TOLL_AMOUNT			= 	"Toll";
	public static final String	L_NIGHT_HALT_AMOUNT 	= 	"Night Halt/Out Station";
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

	public static final String 	ID_VEHICLE_SELECT_DUTY_TYPE_COMBO 	= 	"VSDTC";
	public static final String 	ID_TYPE_SELECT_DUTY_TYPE_COMBO 		= 	"TSDTC";

	
	// Telco Bill Combo Ids
	public static final String 	COMBO_TAL_BILL_SELECT_VEHICLE_TYPE 	= "CTBSVT";
	public static final String 	COMBO_TAL_BILL_SELECT_CUSTOMER 		= "CTBSC";
	public static final String 	COMBO_TAL_BILL_SELECT_DUTY_TYPE		= "CTBSDT";

	
	// Edit Duty Type combo ids
	public static final String 	COMBO_EDIT_DUTY_TYPE_SELCT_DUTY_TYPE = "CEDTSDT";

	
	
	// EXCEPTIONS STRING
	public static final String	 	E_KM_EXCEPTION_STRING 				=  "Total KM Should Be Greater Than Zero(0), Start KM Should be less than End KM";
	public static final String		E_LOGIN_EXCEPTION_STRING 			= 	"InValid Credentials...please try again";

	
	// Mothly BOM UI Btns Strings
	public static final String 		GENEARTE_BILL_BTN_STRING 			= 	"Bill";

	public static final String 		REMOVE_BTN_STRING 					= 	"Remove";

	
	////
	public static final String 		ID_ATTR						 		= "Id";
	
	// customer data model constants
	public static final String 		CUSTOMER_DATA_MODEL_FILE_PATH 			= "/UIT-BillGen/src/uit/billgen/resources/CustomerModel.xml";
	public static final String 		CUSTOMER_DATA_MODEL_CUSTOMERS_TAG 		= "Customers";
	public static final String 		CUSTOMER_DATA_MODEL_CUSTOMER_TAG		= "Customer";
	public static final String 		CUSTOMER_DATA_MODEL_CUST_CODE_ATTR 		= "cCode";
	public static final String 		CUSTOMER_DATA_MODEL_CUST_DEPT_ATTR 		= "cDept";

	// duty type data model constants
	public static final String 		DUTY_TYPE_DATA_MODEL_FILE_PATH 			= "/UIT-BillGen/src/uit/billgen/resources/DutyTypeModel.xml";
	public static final String 		DUTY_TYPE_DATA_MODEL_DUTYTYPES_TAG 		= "DutyTypes";
	public static final String 		DUTY_TYPE_DATA_MODEL_DUTYTYPE_TAG		= "DutyType";
	public static final String 		UID_ATTR						 		= "UId";
	public static final String 		SUID_ATTR						 		= "sUId";
	public static final String 		CUSTOMER_NAME_ATTR				 		= "cName";
	public static final String 		CUSTOMER_ADD_ATTR 						= "cAdd";
	public static final String 		VEHICLE_NAME_ATTR 						= "vName";
	public static final String 		HOURS_ATTR						 		= "hours";
	public static final String 		KM_ATTR 								= "KM";
	public static final String 		DUTY_TYPE_STRING_ATTR					= "dutyTypeString";
	public static final String		RATE_ATTR								= "Rate";
	public static final String		EXTRA_KM_RATE							= "exKMRate";
	public static final String 		EXTRA_HOUR_RATE 						= "exHourRate";
	public static final String 		ACNONAC_ATTR 							= "ACType";

	//Monthly bill data model
	public static final String 		MBILL_DM_FILE_PATH 							= "/UIT-BillGen/src/uit/billgen/resources/MonthlyBillModel.xml";
	public static final String 		MBILL_DM_MBILLS_TAG 						= "MBills";
	public static final String 		BILL_TAG 									= "Bill";
	public static final String 		BILL_ROW_STRING 							= "BillRow";
	public static final String 		EXTRA_KM_AMOUNT_ATTR 						= "eKmAmount";
	public static final String 		BILL_NO_STRING_ATTR 						= "BillNo";
	public static final String 		FROM_DATE_STRING_ATTR						= "FromDate";
	public static final String 		TO_DATE_STRING_ATTR 						= "ToDate";
	public static final String 		VEHICLE_DESC_ATTR 							= "vDesc";
	public static final String 		PKG_KM_ATTR 								= "pKM";
	public static final String 		EXTRA_KM_ATTR 								= "exKM";
	public static final String 		PKG_RATE_ATTR 								= "pRate";
	public static final String 		PGK_AMOUNT_ATTR 							= "pAmount";
	public static final String 		TOTAL_AMOUNT_ATTR 							= "tAmount";
	public static final String 		TOTAL_FINAL_AMOUNT_ATTR 					= "fAmount";

	// telco bill
	public static final String 		TELCO_BILL_FILE_PATH 						= "/UIT-BillGen/src/uit/billgen/resources/TelcoBillModel.xml";
	public static final String 		TBILLS_TAG 									= "TBills";
	public static final String 		ADMIN_ATTR 									= "admin";
	public static final String 		BILL_DATE_ATTR 								= "BillDate";
	public static final String 		CLIENT_MOB_NO_ATTR 							= "Mob.No";
	public static final String 		CLIENT_EMAIL_ATTR 							= "E-Mail";
	public static final String 		TRAVEL_STRAT_DATE 							= "sDate";
	public static final String 		TRAVEL_CLOSE_DATE 							= "eDate";
	public static final String 		VEHICLE_NO_ATTR 							= "vNo";
	public static final String 		PKG_TYPE_ATTR 								= "pkgType";
	public static final String 		START_KM_ATTR 								= "sKM";
	public static final String 		END_KM_ATTR 								= "eKM";
	public static final String 		TOTAL_KM_ATTR 								= "tKM";
	public static final String 		START_TIME_ATTR 							= "sTime";
	public static final String 		END_TIME_ATTR 								= "eTime";
	public static final String 		DUTY_TYPE_ATTR 								= "DutyType";
	public static final String 		PKG_TOTAL_AMOUNT 							= "pkgTAmount";
	public static final String 		EXTRA_TIME_HOURS_ATTR 						= "exTimePH";
	public static final String 		TOLL_CHARG_ATTR 							= "TollCharge";
	public static final String 		MONTHLY_EXTRA_CHARGES_ATTR 					= "MExCharges";
	public static final String 		SERVICE_TAX_ATTR 							= "STCharge";
	public static final String 		NIGHT_HALT_ATTR 							= "NHaltRate";
	public static final String 		GRAND_TOTAL 								= "GTotal";

	// vehicle data model
	public static final String 		VEHICLE_DATA_MODEL_FILE_PATH 				= "/UIT-BillGen/src/uit/billgen/resources/VehicleModel.xml";
	public static final String 		VEHICLES_TAG 								= "Vehicles";
	public static final String 		VEHICLE_TAG 								= "Vehicle";
	public static final String 		MONTHLY_VEHICLE_RATE_ATTR 					= "MRate";

	
	// general
	public static final String 		MSG_UPDATED_SUCCESSFULLY 		= "Updated Successfully";

	public static final String 		MSG_ADDED_SUCCESSFULLY 			= "Added Successfully";

	public static final String 		MSG_PLZ_FILL_ALL_THE_FIELDS 	= "Please fill all the fields";

	public static final String 		MSG_ONLY_5_ROWS_ALLOWED 		= "Only 5 Rows are allowed...Sorry";

	public static final String 		LOGIN_BTN_STRING 				= "Login";

	public static final String 		DBCONNECTION_URL 				= "jdbc:oracle:thin:@localhost:1521:xe";

	public static final String 		DB_UN 							= "swap";

	public static final String 		DB_PW 							= "swap";

	public static final String 		DB_ORACLE_CLASS_FORNAME 		= "oracle.jdbc.driver.OracleDriver";
	
	public static final String 		ADMIN_XML_FILE_PATH 			= "/UIT-BillGen/src/uit/billgen/resources/admin.xml";
	public static final String 		USER_XML_FILE_PATH 				= "/UIT-BillGen/src/uit/billgen/resources/users.xml";

	public static final String 		ADMIN_WINDOW_TITLE = "Admin";

	public static final int 		ADD_CUSTOMER_TEXTFIELD_COL_SIZE 	= 15;
	public static final int 		ADD_CUSTOMER_LABLE_NAME_SIZE 		= 23;
	public static final int 		ADD_DUTY_TYPE_TEXTFILED_COL_SIZE 	= 15;
	public static final int 		ADD_DUTY_TYPE_LABEL_NAME_SIZE 		= 17;
	public static final int 		TEXT_COL_SIZE_15 					= 15;
	public static final int 		TEXT_COL_SIZE_10 					= 10;
	public static final int 		UI_LABEL_NAME_SIZE_20				= 20;
	public static final int 		UI_LABEL_NAME_SIZE_25				= 25;

	public static final String SEARCH_STRING = "Search";

	public static final String NA_STRING = "N/A";

	

	

	

	

	
	
}
