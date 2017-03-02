package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import exceptions.PopupDialogs;

public class Registry
{
	private Properties property = null;
	
	public Registry(File propertyFile)
	{
		try
		{
			property = new Properties();
			//String fileName = "app.config";
			InputStream is = new FileInputStream(propertyFile);

			property.load(is);
		} catch (IOException e)
		{
			new PopupDialogs("Prperty File Is Missing",PopupDialogs.ERROR_MESSAGE);
		}
	}
	public String getValueFor(String key)
	{
		return property.getProperty(key);	
	}
}
