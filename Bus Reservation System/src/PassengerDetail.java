/* 
 Title:Bus Reservation System
Created by:Hariharan.R
Created On:12-10-2022
Last Modified on:15-11-2022
Reviewed By:Anushya Narayanan
Reviewed On:27-10-2002
*/
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class PassengerDetail {
	//To get the detail of the Passenger
    static void getPassengerDetail() {
        System.out.println("Press 1 for full List\nPress 2 for Onboard List");
        Scanner scanner = new Scanner(System.in);
        int choose = scanner.nextInt();
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
            //passenger detail on the database
            if (choose == 1) {


                String sql = "select * from bus_db.passenger_detail";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                System.out.println("Name" + " " + "Email" + " " + "Mobile_Number" + " " + "Gender" + " " + "Age" + " " + "Bus_No" + " " + " " + "Pickup_Point" + " " + "Drop_Point" + " " + "Seat_No" + " " + "Date");
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + "  " + resultSet.getString(3) + " " + resultSet.getString(4) + " " +
                    		resultSet.getString(5) + " " + resultSet.getString(6) + " " + resultSet.getString(7) + " " + resultSet.getString(8) + " " + resultSet.getString(9) + " " + resultSet.getString(10));
                }
            } else {
            	//passenger detail on the specific bus
                System.out.println("BusId: ");
                Scanner scanner1 = new Scanner(System.in);
                int BusId = scanner1.nextInt();

                String query = "SELECT bus_details.BusNo,bus_details.Bus_Name,bus_details.Departure,bus_details.Destination,\r\n" +
                    "bus_details.DepartureTime,bus_details.ArrivalTime,bus_details.DriverName,bus_details.Fare\r\n" +
                    ",bus_details.Bus_Type,passenger_detail.Name,passenger_detail.Mobile_Number,passenger_detail.Pickup,\r\n" +
                    "passenger_detail.drop_point,passenger_detail.SeatNo,passenger_detail.Daate\r\n" +
                    "FROM bus_details,passenger_detail\r\n" +
                    "where bus_details.BusNo=passenger_detail.BusNo and bus_details.BusNo=" + BusId;

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1) +" " + resultSet.getString(2) +" " + resultSet.getString(3) +" " + resultSet.getString(4) +" " +
                    		resultSet.getString(5) +" " + resultSet.getString(6) +" " + resultSet.getString(7) +" " + resultSet.getString(8) +" " + resultSet.getString(9) +" " + resultSet.getString(10) +" " +
                        resultSet.getString(11) +" " + resultSet.getString(12) +" " + resultSet.getString(13) +" " + resultSet.getString(14) +" " + resultSet.getString(15));
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        Home home = new Home();
        home.accessHome();
    }
    
}