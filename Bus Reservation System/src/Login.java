/* 
 Title:Bus Reservation System
Created by:Hariharan.R
Created On:12-10-2022
Last Modified on:15-11-2022
Reviewed By:Anushya Narayanan
Reviewed On:27-10-2002
*/	
import java.sql.Statement;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Scanner;
	public class Login extends Admin {
	    static String name;
	    static String Pass;
	    static Connection connection = null;
	 
	    //log in to the site
	    public void getDetail() {

	        Scanner loginScanner = new Scanner(System.in);
	        System.out.println("USERNAME:");
	        name = loginScanner.next();
	        System.out.println(" PASSWORD:");
	        Pass = loginScanner.next();
	       
	        try {
	        	  Properties properties = new Properties();
	              FileInputStream input = new FileInputStream("D:/Eclipse/Bus Reservation System/src/resources/db.properties");
	              properties.load(input);

	              String url = properties.getProperty("db.url");
	              String username = properties.getProperty("db.username");
	              String password = properties.getProperty("db.password");
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            connection = DriverManager.getConnection(url, username, password);
	            Statement stmt = connection.createStatement();
	            ResultSet rs = stmt.executeQuery("select Username, Password from bus_db.admindb");
	            int flag = 0;
	            Map < String, String > map = new LinkedHashMap < > ();
	            while (rs.next()) {
	                map.put(rs.getString(1), rs.getString(2));
	            }
	            for (Entry < String, String > e: map.entrySet()) {
	                if ((name.equals(e.getKey())) && (Pass.equals(e.getValue()))) {
	                    flag = 1;
	                    System.out.println("Loggedin Successfully\n");
	                    System.out.println("WELCOME ADMIN " + name);

	                }
	            }
	            if (flag == 0) {
	                System.out.println("Invalid Username or Password");
	                getDetail();
	            }  

	        } catch (Exception e) {
	            System.out.println(e);
	        }

	        //after logged in, call home page
	        Home home = new Home();
	        home.accessHome();

	    }

	}