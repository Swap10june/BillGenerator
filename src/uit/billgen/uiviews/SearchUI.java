package uit.billgen.uiviews;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uit.billgen.datamodel.CustomerDataModel;
import uit.billgen.util.AutoSuggestor;
import uit.billgen.util.SConstants;
import uit.billgen.util.Utils;

public class SearchUI extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<String, Object> searchUIComponent;
	private UITemplates templates = null;
	public SearchUI(JDialog owner, String searchString)
	{
		super(owner);
		this.templates  = new UITemplates();
		setSearchUIComponent(new HashMap<String,Object>());
		Utils.getUtilityInstance().applyBasicSettingsOnWindow(owner,searchString);
		initUI(owner);
		owner.setVisible(true);
	}

	private void initUI(JDialog owner)
	{
		JPanel searchCriteriaPanel = new JPanel();
		searchCriteriaPanel.setBounds(0, 32, owner.getWidth()-200, 100);
		searchCriteriaPanel.setBackground(Color.cyan);
		searchCriteriaPanel.setBorder(SConstants.BORDER_BLUE_1);
		owner.add(searchCriteriaPanel);
		
		JPanel selectBycustomerNamePanel = templates.getLabelWithComboWOListner("selectBycustomerNamePanel", "Enter Customer Name", new CustomerDataModel().getAllCustomerNames(), getSearchUIComponent(),SConstants.UI_LABEL_NAME_SIZE_20);
		@SuppressWarnings("unchecked")
		JComboBox<String> comboCName = (JComboBox<String>) selectBycustomerNamePanel.getComponent(2);	
		searchCriteriaPanel.add(selectBycustomerNamePanel);
		
		JButton btnSearch = new JButton(SConstants.SEARCH_STRING);
		btnSearch.setBounds(owner.getWidth()-150, 45, 100, 30);
		owner.add(btnSearch);
		/*@SuppressWarnings("unused")
		AutoSuggestor autoSuggestor = new AutoSuggestor(textCName, owner, null, Color.WHITE.brighter(), Color.BLUE, Color.RED, 0.75f)
		{
		    protected boolean wordTyped(String typedWord)
		    {

		        
		        setDictionary(new CustomerDataModel().getAllCustomersList());
		        //addToDictionary("bye");//adds a single word

		        return super.wordTyped(typedWord);//now call super to check for any matches against newest dictionary
		    }
		};*/
	}

	/**
	 * @return the searchUIComponent
	 */
	public HashMap<String, Object> getSearchUIComponent() {
		return searchUIComponent;
	}

	/**
	 * @param searchUIComponent the searchUIComponent to set
	 */
	public void setSearchUIComponent(HashMap<String, Object> searchUIComponent) {
		this.searchUIComponent = searchUIComponent;
	}

}
