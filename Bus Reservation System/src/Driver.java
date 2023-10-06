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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Driver extends DriverUpdate {

    static boolean validateEmail(String Email) {

        String emailValidation = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(emailValidation);
        Matcher matcher = pattern.matcher(Email);

        if (matcher.matches()) {
            return true;
        }

        return false;
    }


    static boolean validatePassword(String password) {

        String regex = "^(?=.*[0-9])" +
            "(?=.*[a-z])(?=.*[A-Z])" +
            "(?=.*[@#$%^&+=])" +
            "(?=\\S+$).{8,20}$";


        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    static String Name;
    static String Mobile_no;
    static String Email;
    static int BusNo;
    static String Aadhar_no;
    static String salary;
    static String Joining_Date;

    public static void addDetail() {
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
            System.out.println("1.Add new Driver\n2.Update Driver\n3.view Driver List");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
            //Add new driver to database
                case 1:
                    {
                        System.out.println("My Ticket Space.com - Driver Detail");


                        System.out.println("Name: ");

                        Name = scanner.nextLine();

                        while (!Name.matches("[a-z A-Z]+")) {
                            System.out.println("Enter valid Name");
                            Name = scanner.nextLine();
                        }

                        System.out.println(" Bus Number: ");

                        BusNo = scanner.nextInt();
                        System.out.println(" Email: ");
                        Email = scanner.next();

                        while (!validateEmail(Email)) {
                            System.out.println("Enter valid Email Address");
                            Email = scanner.next();
                        }



                        System.out.println(" Mobile Number: ");
                        Mobile_no = scanner.next();
                        while (Mobile_no.equals("^[0-9]")) {
                            System.out.println("Enter valid Mobile Number");
                            Mobile_no = scanner.next();
                        }
                        while (Mobile_no.length() != 10) {
                            System.out.println("Enter valid Mobile Number");
                            Mobile_no = scanner.next();
                        }
                        System.out.println(" Aadhar Number : ");
                        Aadhar_no = scanner.next();

                        while (Aadhar_no.equals("^[0-9]")) {
                            System.out.println("Enter valid Aadar Number");
                            Aadhar_no = scanner.next();
                        }
                        while (Aadhar_no.length() != 16) {
                            System.out.println("Enter valid Aadar Number");
                            Aadhar_no = scanner.next();
                        }


                        System.out.println("Salary ");
                        salary = scanner.next();
                        System.out.println(" Driver Joining Date: ");
                        Joining_Date = scanner.next();

                        PreparedStatement preparedstatement = connection.prepareStatement("insert into driver_detail(BusNo,Driver_Name,Email,Mobile_Number,Aadar_Number,Salary,Joining_Date)values (?,?,?,?,?,?,?)");
                        preparedstatement.setInt(1, BusNo); //1 specifies the first parameter in the query  
                        preparedstatement.setString(2, Name);
                        preparedstatement.setString(3, Email);
                        preparedstatement.setString(4, Mobile_no);
                        preparedstatement.setString(5, Aadhar_no);
                        preparedstatement.setString(6, salary);
                        preparedstatement.setString(7, Joining_Date);

                        preparedstatement.executeUpdate();
                        System.out.println("Driver Detail Added Successfully");
                        scanner.close();
                        break;
                    }


             
                case 2:
                    {
                        Driver.updation();
                        break;

                    }
                    //Get the driver details
                case 3:
                    {
                        String sql = "select * from bus_db.driver_detail";
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(sql);
                        System.out.println("Bus No  Driver_Name  Email  Mobile_Number  Aadhar_Number  Salary  Joining_Date");
                        while (resultSet.next()) {
                            System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + "  " + resultSet.getString(3) + " " + resultSet.getString(4) + " " +
                            		resultSet.getString(5) + " " + resultSet.getString(6) + " " + resultSet.getString(7));
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
        Home home = new Home();
        home.accessHome();

    }


}