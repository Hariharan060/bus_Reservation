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
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class DriverUpdate {
	//Update Driver Detail
    public static void updation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Driver Name to be Updated: ");
        String Driver_Name = scanner.nextLine();
        System.out.println("Press 1 to Update Bus Number\nPress 2 to Update Email\nPress 3 to Mobile Number\nPress 4 to Aadar Numer\n" +
            "Press 5 to Update salary\nPress 6 to Delete a Bus Detail");

        int choice = scanner.nextInt();


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
            switch (choice) {
                case 1:
                    {

                        System.out.println("Bus Number:");
                        int BusNo = scanner.nextInt();
                        String sql = "UPDATE driver_detail SET BusNo=" + "'" + BusNo + "'" + " WHERE Driver_Name=" + "'" + Driver_Name + "'";
                        Statement statement = connection.createStatement();
                        statement.executeUpdate(sql);
                        System.out.println("Updation Success");
                        break;
                    }
                case 2:
                    {
                        System.out.println("Email: ");

                        String Email = scanner.next();


                        String sql = "UPDATE driver_detail SET Email=" + "'" + Email + "'" + " WHERE Driver_Name=" + "'" + Driver_Name + "'";

                        Statement statement = connection.createStatement();
                        statement.executeUpdate(sql);
                        System.out.println("Updation Success");
                        break;
                    }
                case 3:
                    {

                        System.out.println("Mobile Number: ");
                        String Mobile_no = scanner.next();

                        String sql = "UPDATE driver_detail SET Mobile_Number=" + "'" + Mobile_no + "'" + " WHERE Driver_Name=" + "'" + Driver_Name + "'";

                        Statement statement = connection.createStatement();
                        statement.executeUpdate(sql);
                        System.out.println("Updation Success");
                        break;
                    }
                case 4:
                    {
                        System.out.println("Aadhar Number");
                        String Aadhar_Number = scanner.next();

                        String sql = "UPDATE driver_detail SET Aadar_Number=" + "'" + Aadhar_Number + "'" + " WHERE Driver_Name=" + "'" + Driver_Name + "'";
                        Statement statement = connection.createStatement();
                        statement.executeUpdate(sql);
                        System.out.println("Updation Success");
                        break;
                    }
                case 5:
                    {
                        System.out.println("Salary: ");

                        int Salary = scanner.nextInt();

                        String sql = "UPDATE driver_detail SET Salary=" + "'" + Salary + "'" + " WHERE Driver_Name=" + "'" + Driver_Name + "'";
                        Statement statement = connection.createStatement();
                        statement.executeUpdate(sql);
                        System.out.println("Updation Success");
                        break;
                    }
                case 6:
                    {
                        System.out.println("1.confirm 2.cancel ");

                        int choice1 = scanner.nextInt();
                        switch (choice1) {
                            case 1:
                                {
                                    String sql = "DELETE FROM driver_detail WHERE BusNo=" + "'" + Driver_Name + "'";
                                    Statement statement = connection.createStatement();
                                    statement.executeUpdate("SET FOREIGN_KEY_CHECKS=0");
                                    statement.executeUpdate(sql);
                                    System.out.println("Details of Driver: " + Driver_Name + " Deleted Successfully");
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
                        Home home = new Home();
                        home.accessHome();
                    }
            }


        } catch (Exception e) {
            System.out.println(e);
        }

    }

}