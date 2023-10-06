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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class BusUpdate {
	//Update the bus Detail
    public static void updateBus() {
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
        System.out.println("Bus Number to be Updated: ");
        int BusNo = scanner.nextInt();
        PreparedStatement preparestatement = (PreparedStatement) connection.prepareStatement("Select BusNo from bus_details where BusNo=?"); 
        preparestatement.setInt(1, BusNo); 
        ResultSet resultSet = preparestatement.executeQuery(); 
           if (resultSet.next()) {
	 
 
        System.out.println("Press 1 to Update Bus Name\nPress 2 to Update Destination\nPress 3 to Update Timing\nPress 4 to Update Seat Count\n" +
            "Press 5 to Update Driver\nPress 6 to Update Bus Fare\nPress 7 to Update Bus Type\nPress 8 to Delete a Bus Detail");

        int option = scanner.nextInt();
        
            switch (option) {
                case 1:
                {
                	   scanner.nextLine();
                        System.out.println("Bus Name:");
                        String BusName = scanner.next();

                        String sql = "UPDATE bus_details SET Bus_Name=" + "'" + BusName + "'" + " WHERE BusNo=" + BusNo;
                        Statement statement = connection.createStatement();
                        statement.executeUpdate(sql);
                        System.out.println("Updation Success");
                        break;
                    }
                case 2:
                    {
                        System.out.println("From: ");
                        scanner.nextLine();
                        String From = scanner.next();
                        System.out.println("To: ");
                        String To = scanner.next();

                        String sql = "UPDATE bus_details SET Departure=" + "'" + From + "'" + " WHERE BusNo=" + BusNo;

                        Statement statement = connection.createStatement();
                        statement.executeUpdate(sql);
                        statement.executeUpdate("UPDATE bus_details SET Destination=" + "'" + To + "'" + " WHERE BusNo=" + BusNo);
                        System.out.println("Updation Success");
                        break;
                    }
                case 3:
                    {
                        System.out.println("Departure Time: ");
                        scanner.nextLine();
                        String DepartureTime = scanner.next();
                        System.out.println("Arrival Time: ");
                        String ArrivalTime = scanner.next();

                        String sql = "UPDATE bus_details SET DepartureTime=" + "'" + DepartureTime + "'" + " WHERE BusNo=" + BusNo;

                        Statement statement = connection.createStatement();
                        statement.executeUpdate(sql);
                        statement.executeUpdate("UPDATE bus_details SET ArrivalTime=" + "'" + ArrivalTime + "'" + " WHERE BusNo=" + BusNo);
                        System.out.println("Updation Success");
                        break;
                    }
                case 4:
                    {
                        System.out.println("Maximum Seat Number");
                        int Max_Seat = scanner.nextInt();

                        String sql = "UPDATE bus_details SET Max_Seat=" + "'" + Max_Seat + "'" + " WHERE BusNo=" + BusNo;
                        Statement statement = connection.createStatement();
                        statement.executeUpdate(sql);
                        System.out.println("Updation Success");
                        break;
                    }
                case 5:
                    {
                        System.out.println("Driver Name");
                        scanner.nextLine();
                        String DriverName = scanner.next();

                        String sql = "UPDATE bus_details SET DriverName=" + "'" + DriverName + "'" + " WHERE BusNo=" + BusNo;
                        Statement statement = connection.createStatement();
                        statement.executeUpdate(sql);
                        System.out.println("Updation Success");
                        break;
                    }
                case 6:
                    {
                        System.out.println("Bus Fare: ");
                        int Fare = scanner.nextInt();

                        String sql = "UPDATE bus_details SET Fare=" + "'" + Fare + "'" + " WHERE BusNo=" + BusNo;
                        Statement statement = connection.createStatement();
                        statement.executeUpdate(sql);
                        System.out.println("Updation Success");
                        break;
                    }
                case 7:
                    {
                        System.out.println("Bus Type: ");

                        String BusType = scanner.next();

                        String sql = "UPDATE bus_details SET Bus_Type=" + "'" + BusType + "'" + " WHERE BusNo=" + BusNo;
                        Statement statement = connection.createStatement();
                        statement.executeUpdate(sql);
                        System.out.println("Updation Success");
                        break;
                    }
                case 8:
                    {
                        System.out.println("1.confirm 2.cancel ");

                        int choice = scanner.nextInt();
                        switch (choice) {
                            case 1:
                                {
                                    String sql = "DELETE FROM bus_details WHERE BusNo=" + BusNo;
                                    Statement statement = connection.createStatement();
                                    statement.executeUpdate("SET FOREIGN_KEY_CHECKS=0");
                                    statement.executeUpdate(sql);
                                    System.out.println("Details of Bus Number: " + BusNo + " Deleted Successfully");
                                    break;
                                }
                            case 2:
                                {
                                    Home home = new Home();
                                    home.accessHome();
                                }
                        }
                    }
                default:
                    {
                        System.out.println("No Updation Done\n");
                        Home home = new Home();
                        home.accessHome();
                    }
            }
 }
 else {
	 System.out.println("BUS DOESNOT EXIST\n");
	 Home home = new Home();
     home.accessHome();
	 
 }


        } catch (Exception e) {
            System.out.println(e);
        }
        Home home = new Home();
        home.accessHome();
    }

    
}