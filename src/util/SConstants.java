package util;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.border.Border;


public class SConstants
{

	
	public static final Date 			TODAYS_DATE 			= 		new Date();
	
	public static final String[] 		VEHICLE_TYPES 			= 		{"Indica","Tavera"};
	public static final String[] 		CUSSTOMER_LIST 			= 		{"TAL","Mahindra"};
	public static final String[] 		HOME_BUTTONS_NAMES		= 		{"Generate Bill","Admin","History"};
	public static final String[] 		ADMIN_BUTTONS_NAMES		= 		{"Add Customer","Edit Customer","Add Vehicle","edit vehicle","Add Duty Type","Edit Duty Type"};
	public static final String 			MY_COMPANY_NAME 		= 		"Unity Infotech Group (I) Pvt Ltd";
	public static final String 			PRPJECT_HEADING 		= 		"SAIKRUPA TRANSPORT";
	
	public static final Font 			FONT_COURRIER_BOLD_18 	= 		new Font("Courier", Font.BOLD,18);
	public static final Font 			FONT_COURRIER_BOLD_10 	= 		new Font("Courier", Font.BOLD,10);
	public static final Font 			FONT_COURRIER_BOLD_13 	= 		new Font("Courier", Font.BOLD,13);
	
	
	public static final int 			MAIN_WINDOW_WIDTH 		= 		950;
	public static final int 			MAIN_WINDOW_HEIGHT 		= 		702;
	
	public static final Border 			BORDER_BLUE_1 			= 		BorderFactory.createLineBorder(Color.BLUE, 1);
	
	public static final Registry 		reg 					= 		new Registry(new File("resource/TelcoBillUI.config"));

	public static  final String 		BILL_GEN_WIN_NAME 		= 		"Generate Bill";
	public static  final String 		ADMIN_WIN_NAME 			= 		"Admin";
	public static  final String 		ADD_CUST_WIN_NAME 		= 		"Add Customer";
	public static  final String 		EDIT_CUST_WIN_NAME 		= 		"Edit Customer";
	public static  final String 		ADD_VEHICLE_WIN_NAME 	= 		"Add Vehicle";
	public static  final String 		EDIT_VEHICLE_WIN_NAME 	= 		"edit vehicle";
	public static  final String 		SAVE_BUTTON 			= 		"Save";
	public static  final String 		CUST_EDIT_BUTTON 		= 		"Save Customer";
	
	public static  final String 		ADD_DUTY_TYPE 			= 		"Add Duty Type";
	public static  final String 		EDIT_DUTY_TYPE 			= 		"Edit Duty Type";
}
