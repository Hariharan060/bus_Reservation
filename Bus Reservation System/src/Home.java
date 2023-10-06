/* 
 Title:Bus Reservation System
Created by:Hariharan.R
Created On:12-10-2022
Last Modified on:15-11-2022
Reviewed By:Anushya Narayanan
Reviewed On:27-10-2002
*/
import java.util.Scanner;

public class Home {
	//Accessing the Home Page
    public void accessHome() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWelcome to My Ticket Space.com\n");
        System.out.println("press 1 to Add  New Bus\npress 2 to view BusList \nPress 3 for Update BusDetail \nPress 4 for Passenger Detail\nPress 5 for Driver Detail \nPress 6 for Logout");
        int Access = scanner.nextInt();
        switch (Access) {
            case 1:
                {
                    System.out.println("Add  New Bus\n");

                    BusDetail.addBusDetail();
                    break;
                }
            case 2:
                {
                    Buses.getBuses();
                    break;
                }
            case 3:
                {
                    BusUpdate.updateBus();
                    break;
                }
            case 4:
                {
                    System.out.println("Passenger Details:");

                    PassengerDetail.getPassengerDetail();
                    break;
                }
            case 5:
                {

                    Driver.addDetail();
                    break;
                }
            case 6:
                {
                	System.out.println("Logged out Successfully");
                    System.exit(0);
                }
            default:
                {

                    accessHome();

                }


                scanner.close();
        }
    }
}