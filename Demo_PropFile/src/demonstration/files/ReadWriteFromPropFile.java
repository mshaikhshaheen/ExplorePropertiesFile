package demonstration.files;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;

public class ReadWriteFromPropFile {

	InputStream inFileHandle = null;
	OutputStream outFileHandle = null;
	Properties data;

	/**
	 * 
	 * @param filename - filename of the properties file
	 * @return properties as key value pairs, read from the file
	 */
	public Properties readFromPropertiesFile(String filepath){
		data = new Properties();
		try {
			inFileHandle = ReadWriteFromPropFile.class.getClassLoader().getResourceAsStream(filepath);
			if(inFileHandle==null){
				System.out.println("Unable to find file:" + filepath);
				return data;
			}
			data.load(inFileHandle);
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			if (inFileHandle != null) {
				try {
					inFileHandle.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return data;
	}

	/**
	 * 
	 * @param filename - filename of the properties file
	 * @param data - data to be written to the properties file
	 * @return boolean value indicating successful write operation.
	 */
	public boolean writeToPropertiesFile(String filepath, Properties data){
		try {
			outFileHandle = new FileOutputStream(filepath,true);
			data.store(outFileHandle, null);
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}catch (IOException io) {
			io.printStackTrace();
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		finally {
			if (outFileHandle != null) {
				try {
					outFileHandle.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 
	 * @param filename - filename of the XML file
	 * @return properties as key value pairs, read from the file
	 */
	public Properties readFromXMLPropertiesFile(String filepath){
		data = new Properties();
		try {

			inFileHandle = new FileInputStream(filepath);
			if(inFileHandle==null){
				System.out.println("Unable to find xml file:" + filepath);
				return data;
			}
			data.loadFromXML(inFileHandle);
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			if (inFileHandle != null) {
				try {
					inFileHandle.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return data;	
	}

	/**
	 * 
	 * @param filename - filename of the XML properties file
	 * @param data - data to be written to the XML properties file
	 * @return boolean value indicating successful write operation.
	 */
	public boolean writeToXMLPropertiesFile(String filepath,Properties data){		
		try {
			outFileHandle = new FileOutputStream(filepath,true);
			data.storeToXML(outFileHandle, null);
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}catch (IOException io) {
			io.printStackTrace();
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		finally {
			if (outFileHandle != null) {
				try {
					outFileHandle.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	
	public Set<Object> getAllKeys(){
		Set<Object> keys = data.keySet();
		return keys;
	}

	
	public String getPropertyValue(String key){
		return this.data.getProperty(key);
	}

	
	public void printAllPropertyValues(){
		Enumeration<?> e = data.propertyNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			String value = data.getProperty(key);
			System.out.println("Key : " + key + ", Value : " + value);
		}
	}
	
	/**
	 * 
	 * @param args - arguments if any
	 */
	public static void main(String args[]) {

		ReadWriteFromPropFile obj = new ReadWriteFromPropFile();
		Properties prop;

		prop = obj.readFromPropertiesFile("resources\\readconfig.properties");
		System.out.println("Value from readconfig file : "+prop.getProperty("targetCities"));

		prop = new Properties();
		prop.setProperty("test1", "dummy1");
		obj.writeToPropertiesFile("src\\resources\\writeconfig.properties", prop);

		prop = obj.readFromXMLPropertiesFile("src\\resources\\readxmlconfig.xml");
		System.out.println("Value from readxmlconfig file : "+prop.getProperty("name"));

		prop = new Properties();
		prop.setProperty("test2", "dummy2");
		obj.writeToXMLPropertiesFile("src\\resources\\writexmlconfig.xml", prop);

//		Set<Object> keys = obj.getAllKeys();
//		for(Object k:keys){
//			String key = (String)k;
//		    System.out.println(key+": "+obj.getPropertyValue(key));
//		}
//		
//		obj.printAllPropertyValues();
	}
}

