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
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignUp extends Admin {

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
    static String Confirm_password;
    static String Username;
    static String Password;
    //signup to the site
    public void getDetail() {

        System.out.println("Welcome to My Ticket Space.com");

        Scanner signupScanner = new Scanner(System.in);

        System.out.println("Name: ");
        Name = signupScanner.nextLine();
        while (!Name.matches("[a-z A-Z]+")) {
            System.out.println("Enter valid Name");
            Name = signupScanner.nextLine();
        }

        System.out.println(" Username: ");
        Username = signupScanner.next();
        while (Username.length() < 4 || Username.length() > 10) {
            System.out.println("Enter valid UserName");
            Username = signupScanner.next();
        }
        System.out.println(" Email: ");
        Email = signupScanner.next();

        while (!validateEmail(Email)) {
            System.out.println("Enter valid Email Address");
            Email = signupScanner.next();
        }



        System.out.println(" Mobile Number: ");
        Mobile_no = signupScanner.next();
        while (Mobile_no.equals("^[0-9]")) {
            System.out.println("Enter valid Mobile Number");
            Mobile_no = signupScanner.next();
        }
        while (Mobile_no.length() != 10) {
            System.out.println("Enter valid Mobile Number");
            Mobile_no = signupScanner.next();
        }
        System.out.println(" Password: ");
        Password = signupScanner.next();

        while (!validatePassword(Password)) {
            System.out.println("Enter valid password");
            Password = signupScanner.next();
        }


        System.out.println("Confirm password: ");
        Confirm_password = signupScanner.next();
        while (!Password.equals(Confirm_password)) {
            System.out.println("Password and Confirm password does not match");
            Confirm_password = signupScanner.next();
        }
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
            PreparedStatement preparedstatement = connection.prepareStatement("insert into bus_db.admindb(Name,Email,Mobile_no,Password,Username)values (?,?,?,?,?)");
            preparedstatement.setString(1, Name); //1 specifies the first parameter in the query  
            preparedstatement.setString(2, Email);
            preparedstatement.setString(3, Mobile_no);
            preparedstatement.setString(4, Password);
            preparedstatement.setString(5, Username);

            preparedstatement.executeUpdate();
            System.out.println("Admin Registration Successfull");
         

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}