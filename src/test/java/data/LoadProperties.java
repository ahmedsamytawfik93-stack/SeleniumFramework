package data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {
	// Load the properties from the folder
	public static Properties userData = 
			loadProperties(System.getProperty("user.dir") + "\\src\\main\\java\\properties\\userdata.properties");
	
	// Load SauceLabs Account Data
		public static Properties sauceLabsData = 
				loadProperties(System.getProperty("user.dir") + "\\src\\main\\java\\properties\\sauceLabsUser.properties");
		
		
	private static Properties loadProperties(String path) {
		Properties pro = new Properties();
		
		// Stream for reading file
		try {
			FileInputStream stream = new FileInputStream(path);
			pro.load(stream);
		} catch (IOException | NullPointerException e) {
			System.out.println("Error occurred: " + e.getMessage());
		}

		return pro;
	}
}
