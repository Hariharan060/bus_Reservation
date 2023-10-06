/* 
 Title:Bus Reservation System
Created by:Hariharan.R
Created On:12-10-2022
Last Modified on:03-11-2022
Reviewed By:Anushya Narayanan
Reviewed On:27-10-2002
*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args)  {
     access();  
    }
    //Main page 
    public static void access() {
    	 System.out.println("----------My Ticket Space.com----------\n");
         System.out.println("Enter Your Choice \n");
         System.out.println("1.Registration 2.Login \n");

         Scanner scanner = new Scanner(System.in);
         int page = scanner.nextInt();

         if (page == 1) {
             SignUp signUp = new SignUp();
             signUp.getDetail();
             Login login = new Login();
             login.getDetail();
         } else {
             Login login = new Login();
             login.getDetail();

         }

         scanner.close();	
    }
}