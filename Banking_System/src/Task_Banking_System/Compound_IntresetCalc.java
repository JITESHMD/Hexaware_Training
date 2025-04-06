package Task_Banking_System;

//Task 3: Loop Structures
//You are responsible for calculating compound interest on savings accounts for bank customers. You
//need to calculate the future balance for each customer's savings account after a certain number of years.
//Tasks:
//1. Create a program that calculates the future balance of a savings account.
//2. Use a loop structure (e.g., for loop) to calculate the balance for multiple customers.
//3. Prompt the user to enter the initial balance, annual interest rate, and the number of years.
//4. Calculate the future balance using the formula:
//future_balance = initial_balance * (1 + annual_interest_rate/100)^years.
//5. Display the future balance for each customer.




import java.util.*;

public class Compound_IntresetCalc {

public static void main(String args[]){
     Scanner sc = new Scanner(System.in);

     System.out.println("ENTER NO OF CUSTOMERS:");
     int customers = sc.nextInt();

     for(int i=0;i<customers;i++){
         System.out.println("CUSTOMER" + i + ":");

         System.out.println("ENTER YOUR BALANCE :");
         double balance = sc.nextDouble();

         System.out.println("ENTER THE RATE OF INTEREST :");
         double rate_of_interest = sc.nextDouble();

         System.out.println("ENTER NO OF YEARS :");
         int no_of_yrs = sc.nextInt();

         double future_balance = balance * Math.pow(1+rate_of_interest/100, no_of_yrs);

         System.out.printf("Future Balance after %d years: 1.2f\n", future_balance , no_of_yrs, balance);

     }
     sc.close();
 }
}
