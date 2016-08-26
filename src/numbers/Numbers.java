/*
 * Sarah Overlander
 */
package numbers;

import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;
/**
 * This program asks the user for a number of sets between 1 and 5, then generates
 * sets of random numbers: 
 * Subgroup A, 5 numbers between 1 and 69, inclusive, no duplicates
 * Subgroup B, 1 number between 1 and 26, inclusive, can be a duplicate of a number
 * in Subgroup A
 * After generating the requested number of sets, asks the user if they want to
 * run again, defaulting to yes.
 * Subgroup A is generated using a loop to create a random number, then iterates over
 * a secondary loop to check for duplicates against existing members, inserting
 * the number into an array if it is not a duplicate. Once all five
 * members are generated, the array is then sorted and both Subgroups A and B
 * are printed to the output.
 */
public class Numbers {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Boolean stop = false;
        Scanner userInput = new Scanner(System.in);
        while(!stop) { //runs program until user enters a "n" or "N" to the continuation question
            System.out.println("How many groups would you like to generate? (1-5)");
            int numGroups = askNumGroups(userInput);
            generateGroup(numGroups);
            System.out.print("Would you like to generate more sets? y/n ");
            String s = userInput.nextLine().trim();
            if (s.equals("n") || s.equals("N")) {
                stop = true;
                System.out.println("Goodbye!");
            }
         }   
        
    
    }
    /*
     * This function is for validating user input on how many groupings to generate.
     * If the user enters a non-number or a number that is not between 1 and 5,
     * it will try again.
     * Returns an integer between 1 and 5.
    */
   private static int askNumGroups(Scanner userInput) {
      int userGroups = -1; //Initialize variable to definitely unacceptable value
      try {
        userGroups = Integer.parseInt(userInput.nextLine().trim()); //First attempt at user input
        } catch(NumberFormatException e) {} //catches not-a-number
      while (userGroups <= 0 || userGroups > 5) { //if the first try block didn't work, this comes into play and retries until an acceptable value is entered
          System.out.println("Please enter a number between 1 and 5!");
          try {
                userGroups = Integer.parseInt(userInput.nextLine().trim());
            } catch(NumberFormatException e) {} //catches subsequent not-a-numbers
      }
      return userGroups;
   }
   /*
    * Generates sets of random numbers based on the number of groups desired by the user.
    * Subgroup A is 5 numbers in the range 1-69 without duplicates within the set, sorted ascending.
    * Subgroup B is 1 number in the range 1-26 and can be a duplicate of any number in Subgroup A.
    * Duplicates between sets are okay.
    * After a set is generated, it is output to the screen, each A number in the array separated by a space, with
    * the B number separated from the A numbers by a tab.
   */
   private static void generateGroup(int numGroups) {
       for (int i = numGroups; i > 0; i--) {
           int[] subgroupA = new int[5]; //Array to hold subgroup A numbers
           int subgroupB;
           
           Random generatorA = new Random();
           Random generatorB = new Random();
           subgroupB = generatorB.nextInt(26) + 1; // Generates between 0-25 then adds 1 for actual range 1-26
           
           for (int j = 0; j < 5;) {
               int temp = generatorA.nextInt(69) + 1; //Generates between 0-68 then adds 1 for actual range 0-69
               Boolean isDuplicate = false; //assume non duplicate until proven otherwise
               for (int k = 0; k < 5; k++) { //checks for duplicates in Subgroup A
                   if (temp == subgroupA[k]) {
                       isDuplicate = true;
                   }
               }
               if (isDuplicate == false) { //temp is not a duplicate, add to array
                   subgroupA[j] = temp;
                   j++;
               }
               
           }
           Arrays.sort(subgroupA);
           System.out.println(subgroupA[0] + " " +subgroupA[1] + " " +subgroupA[2] + " " +subgroupA[3] + " " +subgroupA[4] + "\t" + subgroupB);
       }
   } 
}
