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

public class BusDetail {
    //Add new bus to the database
    public static void addBusDetail() {

    	  Bus bus = new Bus();
        Scanner scanner = new Scanner(System.in);
        System.out.println("BusId: ");
        bus.setBusId(scanner.nextInt());
        System.out.println("Bus Name: ");
        scanner.nextLine();
        bus.setBus_Name(scanner.nextLine());
        System.out.println("From: ");
        bus.setFrom(scanner.nextLine());
        System.out.println("To: ");
        bus.setTo(scanner.nextLine());
        System.out.println("DepartureTime: ");
        bus.setDepartureTime(scanner.nextLine());
        System.out.println("ArrivalTime: ");
        bus.setArrivalTime(scanner.nextLine());
        System.out.println("Max_Seat: ");
        bus.setMax_Seat(scanner.nextLine());
        System.out.println("DriverName: ");
        bus.setDriverName(scanner.nextLine());
        System.out.println("Bus Fare: ");
        bus.setFare(scanner.nextInt());
        System.out.println(" Bus Type: ");
        scanner.nextLine();
        bus.setBus_Type(scanner.nextLine());

      

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

            String sql = "insert into bus_details(BusNo,Bus_Name,Departure,Destination ,DepartureTime,ArrivalTime,Max_Seat,DriverName,Fare,Bus_Type)VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, bus.getBusId());
            preparedStatement.setString(2, bus.getBus_Name());
            preparedStatement.setString(3, bus.getFrom());
            preparedStatement.setString(4, bus.getTo());
            preparedStatement.setString(5, bus.getDepartureTime());
            preparedStatement.setString(6, bus.getArrivalTime());
            preparedStatement.setString(7, bus.getMax_Seat());
            preparedStatement.setString(8, bus.getDriverName());
            preparedStatement.setInt(9, bus.getFare());
            preparedStatement.setString(10, bus.getBus_Type());

            preparedStatement.executeUpdate();
            System.out.println("Bus Detail Added Successfully");
            select();
        } catch (Exception e) {
            System.out.println(e);
        }
        scanner.close();
    }
    //To add more bus to database
    public static void select() {
        System.out.println("1.Add More Bus  2.Exit\n");
        Scanner scanner1 = new Scanner(System.in);
        int Choose = scanner1.nextInt();
        if (Choose == 1) {
            BusDetail.addBusDetail();
        } else {
            Home home = new Home();
            home.accessHome();
        }
        scanner1.close();
    }

}

class Bus {
    private int BusId;
    private String Bus_Name;
    private String From;
    private String To;
    private String DepartureTime;
    private String ArrivalTime;
    private String Max_Seat;
    private String DriverName;
    private int Fare;
    private String Bus_Type;




    public String getBus_Type() {
        return Bus_Type;
    }
    public void setBus_Type(String bus_Type) {
        Bus_Type = bus_Type;
    }
    public int getBusId() {
        return BusId;
    }
    public void setBusId(int busId) {
        BusId = busId;
    }
    public int getFare() {
        return Fare;
    }
    public void setFare(int fare) {
        Fare = fare;
    }

    public String getBus_Name() {
        return Bus_Name;
    }
    public void setBus_Name(String bus_Name) {
        Bus_Name = bus_Name;
    }
    public String getFrom() {
        return From;
    }
    public void setFrom(String from) {
        From = from;
    }
    public String getTo() {
        return To;
    }
    public void setTo(String to) {
        To = to;
    }
    public String getDepartureTime() {
        return DepartureTime;
    }
    public void setDepartureTime(String departureTime) {
        DepartureTime = departureTime;
    }
    public String getArrivalTime() {
        return ArrivalTime;
    }
    public void setArrivalTime(String arrivalTime) {
        ArrivalTime = arrivalTime;
    }
    public String getMax_Seat() {
        return Max_Seat;
    }
    public void setMax_Seat(String max_Seat) {
        Max_Seat = max_Seat;
    }
    public String getDriverName() {
        return DriverName;
    }
    public void setDriverName(String driverName) {
        DriverName = driverName;
    }





}