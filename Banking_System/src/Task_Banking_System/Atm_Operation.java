package Task_Banking_System;

//Task 2: Nested Conditional Statements
//Create a program that simulates an ATM transaction. Display options such as "Check Balance,"
//"Withdraw," "Deposit,". Ask the user to enter their current balance and the amount they want to
//withdraw or deposit. Implement checks to ensure that the withdrawal amount is not greater than the
//available balance and that the withdrawal amount is in multiples of 100 or 500. Display appropriate
//messages for success or failure.


import java.util.*;

public class Atm_Operation {

 public static void main(String args[]){
     Scanner sc = new Scanner(System.in);

     System.out.println("ENTER YOUR CURRENT BALANCE :");
     double balance = sc.nextInt();

     while(true){
         System.out.println("ATM OPERATIONS");
         System.out.println("1--CHECK BALANCE");
         System.out.println("2--WITHDRAWL");
         System.out.println("3--DEPOSIT");
         System.out.println("4--EXIT");
         
         System.out.println("ENTER THE CHOICE :");
         int ops = sc.nextInt();

         if(ops == 1){
             System.out.println("Your Balance Is : " + balance);

         }
         else if (ops == 2){
             System.out.println("ENTER AMOUNT TO BE WITHDRAW");
             int withdraw_amt = sc.nextInt();
             
             if(withdraw_amt > balance){
                 System.out.println("Insufficient Balance");
             }
             else if (withdraw_amt % 100 == 0 || withdraw_amt%500 == 0){
                 balance -= withdraw_amt;
                 System.out.println("Success your current balance is "+ balance);
             }
             else{
                 System.out.println("Invalid amount! Please enter multiples of 100 or 500");
             }
         }
         else if (ops == 3){
             System.out.print("ENTER DEPOSIT AMOUNT: ");
             double depositAmount = sc.nextDouble();
             
             if (depositAmount > 0) {
                 balance += depositAmount;
                 System.out.println("Deposit successful! Your new balance is: " + balance);
             } else {
                 System.out.println("Invalid deposit amount!");
             }
         }
         else if (ops == 4){
             System.out.println("THANK YOU USING THE ATM. GOOD BYE!");
             break;
         }
         else{
             System.out.println("INVALID CHOICE ! ENTER VALID CHOICE");
         }
         
     }
     sc.close();
 }
 
}

