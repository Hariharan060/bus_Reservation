/* 
 Title:Bus Reservation System
Created by:Hariharan.R
Created On:12-10-2022
Last Modified on:15-11-2022
Reviewed By:Anushya Narayanan
Reviewed On:27-10-2002
*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;
import java.io.FileInputStream;
import java.sql.CallableStatement;

public class Buses {
    public static void getBuses() {
        Scanner scanner = new Scanner(System.in);
    	 try {
             Connection connection;
             Properties properties = new Properties();
             FileInputStream input = new FileInputStream("D:/Eclipse/Bus Reservation System/src/resources/db.properties");
             properties.load(input);

             String url = properties.getProperty("db.url");
             String username = properties.getProperty("db.username");
             String password = properties.getProperty("db.password");
             Class.forName("com.mysql.cj.jdbc.Driver");
             connection = DriverManager.getConnection(url, username, password);
    	System.out.println("1.Bus List 2.Searth bus for locations");
        int choice =scanner.nextInt();
        switch(choice) {
        //to get the bus detail
        case 1:{
        System.out.print("Bus List\n\n");
       
            String sql = "select * from bus_db.bus_details";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("BusNo  Bus_Name  Departure  Destination  DepartureTime  ArrivalTime  Max_Seat  DriverName  Fare   Bus_Type");
            while (resultSet.next()) {
                int BusNo = resultSet.getInt("BusNo");
                String Bus_Name = resultSet.getString("Bus_Name");
                String Departure = resultSet.getString("Departure");
                String Destination = resultSet.getString("Destination");
                String DepartureTime = resultSet.getString("DepartureTime");
                String ArrivalTime = resultSet.getString("ArrivalTime");
                String Max_Seat = resultSet.getString("Max_Seat");
                String DriverName = resultSet.getString("DriverName");
                int Fare = resultSet.getInt("Fare");
                String Bus_Type = resultSet.getString("Bus_Type");

                System.out.println(BusNo + "  " + Bus_Name + "  " + Departure + "  " + Destination + "  " + DepartureTime + "  " + ArrivalTime + "  " + Max_Seat + "  " + DriverName + "  " + Fare + "  " + Bus_Type);
            }
          break;
        }
        //get the bus according to destination
        case 2:{
        	System.out.println("From: ");
        	String from=scanner.next();
        	System.out.println("To: ");
        	String to=scanner.next();
            CallableStatement callableStatement=connection.prepareCall("{call getbus(?,?)}");
            callableStatement.setString(1,from);
            callableStatement.setString(2,to);
            ResultSet resultSet=callableStatement.executeQuery();
            while (resultSet.next()) {
            	
            	System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + "  " + resultSet.getString(3) + " " + resultSet.getString(4) + " " +
            			resultSet.getString(5) + " " + resultSet.getString(6) + " " + resultSet.getString(7)+ " " + resultSet.getString(8)+ " " + resultSet.getString(9)+ " " + resultSet.getString(10));
            	
        }
        }
        } 
    	 }catch (Exception e) {
            System.out.println(e);
        }
        Home home = new Home();
        home.accessHome();
    } 

}