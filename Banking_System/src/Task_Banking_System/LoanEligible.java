package Task_Banking_System;

//Task 1: Conditional Statements
//In a bank, you have been given the task is to create a program that checks if a customer is eligible for
//a loan based on their credit score and income. The eligibility criteria are as follows:
//• Credit Score must be above 700.
//• Annual Income must be at least $50,000.
//Tasks:
//1. Write a program that takes the customer's credit score and annual income as input.
//2. Use conditional statements (if-else) to determine if the customer is eligible for a loan.
//3. Display an appropriate message based on eligibility.



import java.util.*;

public class LoanEligible {

 public static void main(String args[]){
     Scanner sc = new Scanner(System.in);
     System.out.println("Enter your credit score: ");
     int credit_score = sc.nextInt();

     System.out.println("ENTER YOUR ANNUAL INCOME:");
     double annual_income = sc.nextDouble();

     if(credit_score > 700 && annual_income >= 50000){
         System.out.println("Congratulations You're Eligible for the Loan");
     }
     else{
         System.out.println("Sorry You're not Eligible for the Loan");
     }
     sc.close();
 }

 
}
