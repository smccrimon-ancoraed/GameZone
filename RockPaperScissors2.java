/*
 * Author:  Efrem McCrimon
 * Example code
 * Game Zone, Exercise 2
 * Program:  RockPaperScissor2.java
 *
 * Enhanced version of the Rock Scissors and Paper Game.
 * 
 * This is the famous Rock, Scissors, and Paper Game using text mode, no graphics.
 * This version will accept a string input for the selection of a "Rock, Scissors, or Paper".
 *
 *
 * Methods:
 * ChkValidSelection() takes a String as input and returns the integer result selection as
 *  0 = Rock, 1 = Scissors, 2 = Paper, -1 = Invalid
 *
 * ChkWinner() takes both choices from the Computer and Player and determines a winner it
 *  returns a value use to track the number of ties and wins
 *
 * DisplayResults() - display the results after MAXRUNS attempts which we set to 10.
 *
 * AutoChoice() - this generates a random number for the computer selection of the Rock,
 *  Scissors, or Paper.  It allows picks a valid choice.  This is not the case for the Human
 *  input.
 *
 * XlatIntChoice() translates an integer into a string version of the selection for example,
 *  "Rock" , "Scissors", or "Paper"
 *
 * XlatStrChoice() translates a String into an integer version of the selection as,
 *  "Rock" is 0, "Scissors" is 1, and "Paper" is 2
 *
 * Variables Used:
 *     LOWNUM is the lowest number
 *     HIGHNUM is the highest number , used checking
 *     ROCKNUM is the integer value for Rock
 *     SCISNUM is te integer value for Scissors
 *     PAPRNUM is te integer value for Paper
 *     MAXRUNS = 10 is the maximum limit to use as attempts to play the game;
 *     validChoice is use to validate our selection match
 *     trkWins[] is an array of integers to track our wins as follows
 *       element 0 is the total number of Tie,
 *       element 1 is the total number of Computer
 *       element 2 is the total number of Human
 *
 *   Several counters and temporary placeholders:
 *     int choice, human1, computer1, counter;
 *     String strChoice, strSelect;
 *
 *   choice hold the selection after validation
 *   human1 is the human selection choice
 *   computer1 is the human selection choice
 *   counters is used to counting our attemtps
 *   StrChoice hold the "string" input from the user selection
 *   strSelect is a temporary holder for the user selection
 * 
 */

/**
 *
 * @author Efrem
 */

import java.util.Scanner;

public class RockPaperScissors2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
      final int LOWNUM = 0;
      final int HIGHNUM = 2;
      final int ROCKNUM = 0;
      final int SCISNUM = 1;
      final int PAPRNUM = 2;
      final int MAXRUNS = 10;
      boolean validChoice;      // Use to valid our selection match
      int[] trkWins = new int[3];

      int choice, human1, computer1, counter;
      String strChoice, strSelect;

      trkWins[0] = 0;   // Track number of TIEs
      trkWins[1] = 0;   // Track number Computer Wins
      trkWins[2] = 0;   // Track number Human Wins
      counter = 1;
      while (counter <= MAXRUNS) {
// Reset values to start over
//

      Scanner choiceitem1 = new Scanner(System.in);
      System.out.println("\nRock Scissor Paper Game\nNumber of attempts left: " +
              Integer.toString(MAXRUNS - (counter - 1)));
      System.out.print("Rock, ");
      System.out.print("Scissor, ");
      System.out.println("or Paper");
      System.out.print("Enter a selection (*hint: shortcut use the first two letters) > ");
      strSelect = choiceitem1.nextLine();     //Save original selection
      strChoice = strSelect.toUpperCase();    // Convert for checking
      computer1 = AutoChoice();               // Computer choice
      human1 = ChkValidSelection(strChoice);  // Human choice
      System.out.println();
      System.out.print("Computer picks " + XlatIntChoice(computer1) + "  ");
      System.out.println("Human picks " + XlatIntChoice(human1) + "\n");
      if (human1 < LOWNUM || human1 > HIGHNUM)
          System.out.println("Invalid selection!  Please try again");
      else {
         trkWins[ChkWinner(computer1, human1)] += 1;   // Track winners
         System.out.println();
         counter++;    // Advance another attempt until MAXRUNS valid runs
                       // Invalid input does not count
      }

      }   // while count again, invalid input counts as a try
      DisplayResults(trkWins);

    }

    public static int AutoChoice() {
        return (int) (Math.random() * 3); // Values from 0 to 2
        // (int) (Math.random() * 100) % HIGHNUM + LOWNUM
    }

    public static String XlatIntChoice (int value) {
        final int ROCKNUM = 0;
        final int SCISNUM = 1;
        final int PAPRNUM = 2;

        if (value == ROCKNUM) return "Rock";
        if (value == SCISNUM) return "Scissor";
        if (value == PAPRNUM) return "Paper";
        return "None or Invalid";
    }

    public static int XlatStrChoice (String strVal) {
        final int ROCKNUM = 0;
        final int SCISNUM = 1;
        final int PAPRNUM = 2;

        if (strVal.equals("Rock"))
            return ROCKNUM;
        if (strVal.equals("Scissors"))
            return SCISNUM;
        if (strVal.equals("Paper"))
            return PAPRNUM;
        return -1; // Fails


    }
    public static int ChkWinner (int Computer, int Human) {

        int winner;
        final int ROCKNUM = 0;
        final int SCISNUM = 1;
        final int PAPRNUM = 2;


        // We did not use our ROCKNUM, SCISNUM, or PAPRNUM because of spacing

        if (Computer == Human) {
            System.out.println("Tie game");
            winner = 0;         // Track a win for TIE

        } else {
            if (Computer == 0 && Human == 1 || Computer == 1 && Human == 2 ||
               Computer == 2 && Human == 0) {
                System.out.println("Computer wins");
                winner = 1;    // Track a win for the Computer
            } else {
                System.out.println("Human wins");
                winner = 2;    // Track a win for the Human
            }
        }
        return winner;
    }

    public static int ChkValidSelection(String strA) {

        String strB;
        int choice;

        choice = -1;   // Select an invalid number to flag user input
        if (strA.isEmpty() || strA.length() < 2) return choice;   // Bad input
        strB = strA.substring(0, 2);  // Capture the first two letter and check selection
        if (strB.equalsIgnoreCase("PA")) choice = 2;
        else if (strB.equalsIgnoreCase("RO")) choice = 0;
        else if (strB.equalsIgnoreCase("SC")) choice = 1;

        return choice;
    }

    public static void DisplayResults(int[] results) {

        int counter;

        System.out.println();
        System.out.println("Results after the matches\n\nTotal Wins are:\n");
        System.out.println("   Number of TIE - " + results[0]);
        System.out.println("   Number of Computer - " + results[1]);
        System.out.println("   Number of Human - " + results[2]);
    }

}
