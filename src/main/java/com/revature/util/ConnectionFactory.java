package com.revature.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	private static ConnectionFactory cf;
	private static boolean build = true;
	
	private ConnectionFactory()
	{
		build = false;
	}
	
	public static synchronized ConnectionFactory getInstance()
	{
		return(build) ? cf = new ConnectionFactory():cf;
	}
	
	public Connection getConnection()
	{
		Connection conn = null;
		Properties prop = new Properties();
		
		try
		{
			//System.out.println(getClass().getResource(""));
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
						
			URL fileLoc = getClass().getResource ("/app.properties");

						
			System.out.println(fileLoc);
				        
			prop.load(new FileReader(fileLoc.getFile()));
			/**/
						
			//prop.load(new FileReader("src/main/resources/app.properties"));
				
			conn = DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("user"), 
					prop.getProperty("pass")
					);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		catch(FileNotFoundException fnfe)
		{
			fnfe.printStackTrace();
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
		
		return conn;
	}
}
