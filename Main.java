import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
/*
Name: Chandan Chandel
Purpose:  calculate the change that has to be returned to the customer with the least number of coins/notes.
Date: 2021-02-24
Notes: Used recursion to optimize change dispense, and keep code clean.
*/


public class Main {
    // The double values of British currency
   public static double[] notes_coins = new double [] {50.0, 20.0, 10.0, 5.0, 2.0, 1.0, 0.5, 0.2, 0.1, 0.05, 0.02, 0.01};
   // The String values of Britis currency
   public static String[] string_notes_coins = new String [] {"Fifty Pounds", "Twenty Pounds", "Ten Pounds", "Five Pounds", "Two Pounds", "One Pound", "Fifty Pence", "Twenty Pence", "Ten Pence", "Five Pence", "Two Pence","One Pence" };
  //storage variable for our output.
   public static String output = "";
  
   /**
   * This is the main method which makes use of to read in our input, then call the appropriate methods to calculate, finally print.
   * @param args Unused.
   * @throws NumberFormatException, not handled here but make an alternative version of this code that can handle that.
   * @return Nothing.
   */
  public static void main(String[] args) throws NumberFormatException {
    System.out.println("Please type in the price of a product and then hit enter, then type the amount of cash you will provide and hit enter again.");
    InputStreamReader reader = new InputStreamReader(System.in);
    BufferedReader in = new BufferedReader(reader);
    double difference = 0.0;
    try {
        double purchasePrice = Double.parseDouble(in.readLine());
        double cash = Double.parseDouble(in.readLine());
        difference = Main.calculateChange(purchasePrice, cash);
    } catch (Exception e) {
        System.out.println(e);
    }
    if (difference == -1) {System.out.println("I'm sorry what you entered is impossible :( ");}
    else if (difference == 0) { System.out.println("Your change is: ZERO");}
    else {System.out.print("Your change is: " + Main.optimizeChange(output, difference, 0));}
    
  }
  
  
  /**
  * This method is used to calculate the change, while handling all kinds of impossible situations.
  * @param purchasePrice, this is the cost of the good.
  * @param cash, this is the total amount handed over
  * @return double, This returns either the difference in the two
  *  values (if valid values entered), -1 if impossible, 0 if no change is given.
  */
  public static double calculateChange(double purchasePrice, double cash) {
	//following 6 lines used to calculate decimal places (can't be less than one pence)
	String text1 = Double.toString(cash);
	int integerPlaces1 = text1.indexOf('.');
	int decimalPlaces1 = text1.length() - integerPlaces1 - 1;
	String text2 = Double.toString(cash);
	int integerPlaces2 = text2.indexOf('.');
	int decimalPlaces2 = text2.length() - integerPlaces2 - 1;
    //Put catch here for invalid numbers (less digits than one pence,urchasePrice or cash is negative), returns error string
	 if (cash < purchasePrice ||decimalPlaces1 >2 || decimalPlaces2 >2 ||purchasePrice<0||cash <0) { return -1.0; }
     else if (cash == purchasePrice) { return 0.0; }
     else {return cash - purchasePrice;}
  }
  /**
   * This recursive method is used to find the most optimal way to return the change
   * @param output, this String adds the next biggest chunk of change at each call.
   * @param difference, this is the total amount left to be returned in an optimal way at each call.
   * @return String, This returns the final output of the most optimal change delivery.
   */
  public static String optimizeChange (String output, double difference, int position){
    // optimize the difference to return change with the least number of coins/notes
    if (!(difference==0)){//if there is more change iterate through loop again, else return output
      double temp1 = difference/notes_coins[position];
      for (int i = 1; i<=(int)temp1; i++)//add to string output
        {output = output +string_notes_coins[position]+ ", ";}
   return optimizeChange(output, Math.round((difference % notes_coins[position])*100.0)/100.0, position +=1);// recursive call
    }
    else return output.substring(0, output.length()-2); 
    
  }

}