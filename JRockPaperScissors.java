/*
 * @author Efrem McCrimon
 * Example GUI
 * 
 * Final Game Zone
 *
 * Enhanced version of the Rock Scissors and Paper Game.
 *
 * This is the famous Rock, Scissors, and Paper Game using text mode, no graphics.
 * This version will accept a string input for the selection of a
 * "Rock, Scissors, or Paper".
 *
 *
 * Methods:
 * ChkWinner() takes both choices from the Computer and Player and determines a winner it
 *  returns a value use to track the number of ties and wins
 *
 * DisplayResults() - display the results after game
 *
 * AutoChoice() - this generates a random number for the computer selection of the Rock,
 *  Scissors, or Paper.  It allows a valid choice.  
 * The Human choice selection is form the buttons.
 *
 * XlatIntChoice() translates an integer into a string version of the selection for example,
 *  "Rock" , "Scissors", or "Paper"
 *
 * XlatStrChoice() translates a String into an integer version of the selection as,
 *  "Rock" is 0, "Scissors" is 1, and "Paper" is 2
 *
 * setupActions() - turns on the action event listeners for the buttons
 * init() - main execution start, setup the GUI environment
 * start() - set up environment for running
 * stop() - example for tesing the exit routine
 *
 * Variables Used:
 *     LOWNUM is the lowest number
 *     HIGHNUM is the highest number , used checking
 *     ROCKNUM is the integer value for Rock
 *     SCISNUM is te integer value for Scissors
 *     PAPRNUM is te integer value for Paper
 *     MAXRUNS = 10 is the maximum limit to use as attempts to play the game;
 *     trkWins[] is an array of integers to track our wins as follows
 *       element 0 is the total number of Tie,
 *       element 1 is the total number of Computer
 *       element 2 is the total number of Human
 *
 *   Several counters and temporary placeholders:
 *     int choice, human1, computer1, counter;
 *
 *   choice hold the selection after validation
 *   human1 is the human selection choice
 *   computer1 is the human selection choice
 *   counters is used to counting our games attemtps
 *   strPicks - message for what gets picks for both users
 *   strWinner is a temporary holder for the game results
 *   strTotals - shows a running total of wins and ties
 * Labels
 *  lblResults1, lblResults2, lblResults3 - message for display
 *  lblResults1 - used for picks
 *  lblResults2 - used for winner results
 *  lblResults3 - used for showing the total wins
 *
 */

import javax.swing.JApplet;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.JOptionPane;

public class JRockPaperScissors extends JApplet implements ActionListener {

    /**
     * Initialization method that will be called after the applet is loaded
     * into the browser.
     */
    Container con = getContentPane();
    JLabel greetings = new JLabel ("Rock, Paper, Scissors");
    JLabel mainMsg = new JLabel("Choose one button ");
    JLabel resultsMsg = new JLabel ("------ Results ------");
    JLabel lblResults1 = new JLabel();
    JLabel lblResults2 = new JLabel();
    JLabel lblResults3 = new JLabel();
    String goodbyeMsg = "Good bye, thanks for playing.";
    JLabel[] labels = {lblResults1, lblResults2, lblResults3 };

    Font titleFont = new Font("Arial", Font.BOLD, 36);
    Font msgFont = new Font("Arial", Font.BOLD, 20);
    Font resultFont = new Font("Arial", Font.BOLD,16);
    String youPickMsg = "You picked ";
    String computerPickMsg = "Computer picked ";
            
    ButtonGroup userChoice1 = new ButtonGroup();
    JButton btnRock = new JButton("Rock");
    JButton btnScissor = new JButton("Scissors");
    JButton btnPaper = new JButton("Paper");

    final int LOWNUM = 0;
    final int HIGHNUM = 2;
    final int ROCKNUM = 0;
    final int SCISNUM = 1;
    final int PAPRNUM = 2;
    final int MAXRUNS = 10;
    boolean validChoice;      // Use to valid our selection match
    int[] trkWins = new int[3];
    int choice, human1, computer1;
    int counter;
    String strPicks, strWinner, strTotals;


    public void init() {
        // TODO start asynchronous download of heavy resources
        userChoice1.add(btnRock);
        userChoice1.add(btnScissor);
        userChoice1.add(btnPaper);
        con.setLayout(new FlowLayout());
        con.add(greetings).setFont(titleFont);
        con.add(mainMsg).setFont(msgFont);
        con.add(btnRock);
        con.add(btnPaper);
        con.add(btnScissor);
        con.add(resultsMsg);
        trkWins[0] = 0;   // Track number of TIEs
        trkWins[1] = 0;   // Track number Computer Wins
        trkWins[2] = 0;   // Track number Human Wins
        counter = 1;
        setupActions();
        lblResults1.setText("");
        lblResults2.setText("");
        lblResults3.setText("");
        con.add(lblResults1).setFont(resultFont);
        con.add(lblResults2).setFont(resultFont);
        con.add(lblResults3).setFont(resultFont);
    }


    public void setupActions() {

        btnRock.addActionListener(this);
        btnScissor.addActionListener(this);
        btnPaper.addActionListener(this);
     
    }

    public void start() {

        lblResults2.setVisible(true);
        lblResults3.setVisible(true);

    }
    // TODO overwrite start(), stop() and destroy() methods

    public void stop() {

        //default title and icon
        // Just testing the stop() method
        int k;
        JFrame frame = new JFrame();
        k = JOptionPane.showConfirmDialog(frame, goodbyeMsg, "Ready to quit",
                JOptionPane.YES_NO_OPTION);  // .showMessageDialog(frame,

        if (k == 1)
           System.exit(0);

    }

    public void actionPerformed (ActionEvent e) {

        Object source = e.getSource();
        if (source == btnRock) {
            human1 = 0;
        } else if (source == btnScissor) {
            human1 = 1;
        } else if (source == btnPaper) {
            human1 = 2;
        }

        // Computer choice
        computer1 = AutoChoice();               

        // Human picks from the button choice

        //  youPickMsg + XlatIntChoice(human1)
        //  computerPickMsg + XlatIntChoice(human1)
        strPicks = "Human picks " + XlatIntChoice(human1) + "    ";
        strPicks += "Computer picks " + XlatIntChoice(computer1);
        lblResults1.setText(strPicks);
        trkWins[ChkWinner(computer1, human1)] += 1;   // Track winners
        counter++; // Advance another attempt until MAXRUNS or MAXWINS for a player   
        DisplayResults(trkWins);  

        validate();

    }

    public static int AutoChoice() {
        return (int) (Math.random() * 3); // Values from 0 to 2
        // (int) (Math.random() * 100) % HIGHNUM + LOWNUM
    }

    public void DisplayResults (int[] results) {

        // Display using JLabels
        strTotals = "  TIE: " + results[0] + " ";
        strTotals += "  Computer: " + results[1] + " ";
        strTotals += "  Human: " + results[2];
        lblResults3.setText(strTotals);
    }

    // Keep playing until one player reaches 10 wins

    public boolean numOfWins(int[] results) {

        final int MAXWINS = 10;

        return ((results[1] == MAXWINS) || (results[2] == MAXWINS));

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


    public int ChkWinner (int Computer, int Human) {

        int winner;
        final int ROCKNUM = 0;
        final int SCISNUM = 1;
        final int PAPRNUM = 2;

        // We did not use our ROCKNUM, SCISNUM, or PAPRNUM because of spacing

        if (Computer == Human) {
            lblResults2.setText("Tie game");
            winner = 0;         // Track a win for TIE

        } else {
            if (Computer == 0 && Human == 1 || Computer == 1 && Human == 2 ||
               Computer == 2 && Human == 0) {
                lblResults2.setText("Computer wins");
                winner = 1;    // Track a win for the Computer
            } else {
                lblResults2.setText("Human wins");
                winner = 2;    // Track a win for the Human
            }
        }
        
        return winner;
    }


}
