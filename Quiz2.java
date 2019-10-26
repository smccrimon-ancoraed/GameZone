/**
 * Example - Game Zone
 * Exercise 1
 * Program:  Quiz2.java
 * 
 * This is a quiz generator program.  The program will ask several questions and track
 * the responses to each question.  The program will calculate the percentage of correct
 * answers.
 *
 * 
 * Variable, methods, and classes:
 * Question Class created to hold a question, choices, answer, correct, and incorrect for
 *   each question.
 *   Have the ability to mark each question and track indivdual correct and incorrect answers.
 * MAXQUESTION is a constant holding the maxium questions in this quiz
 * 
 * Several counter and displacement variables:
 * j = counter for loop
 * correct = keep track of correct answers
 * incorrect keep track of incorrect answers
 * 
 * 
 * InitialQuestions method - initializes the data for the quiz
 * AskAQuestion - method - prompt the user for the current question in the quiz
 * 
 *
 *
 */

/**
 *
 * @author Efrem
 */

import java.util.Scanner;

public class Quiz2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        final int MAXQUESTION = 5;
        int j;
        int correct, incorrect;
        Question[] Questions = new Question[MAXQUESTION];


        correct = 0;
        incorrect = 0;
        j = 0;
        InitialQuestions(Questions);
        while (j < Questions.length) {


        if (AskAQuestion(Questions[j]) == 1) {
            Questions[j].correctNum = 1;
            System.out.println("Correct answer!\n");
            j++;

        }

        else {
            Questions[j].incorrNum = 0;
            System.out.println("Incorrect answer, the right answer is " +
                    Questions[j].answer + " Try again");
            System.out.println();
        }
        }
        

    }

    public static void InitialQuestions(Question[] Quest ) {

        String[] Q = new String[Quest.length] ;
        String[] QC = new String[Quest.length];
        int[] QA = new int[Quest.length];

        Q[0] = "Who was the second president of the United States of America?";
        Q[1] = "What the www stand for in relationship to the Internet?";
        Q[2] = "This is an  equation (x - h)2 + (y – k)2 = r2 of what?";
        Q[3] = "What sport consist of a Field goal, 10 yard markers, Helmet?";
        Q[4] = "Who is the manufacture of the Newton table computer?";

        QC[0] = "1) Geogre Washington  2)John Adams  3)Ben Franklin  4) James Kirk";
        QC[1] = "1) World Web Wide  2) World Wide Web  3) Wonderful Wide World  4) Willy Wonker Wally";
        QC[2] = "1) Triangle  2) Volume  3) Square  4) Circle ";
        QC[3] = "1) Golf  2) Basketball 3) Football  4) Wrestling";
        QC[4] = "1) IBM  2) Digital Computer Corporation  3) Xerox  4) Apple Computer Corporation";

        QA[0] = 2;
        QA[1] = 2;
        QA[2] = 4;
        QA[3] = 3;
        QA[4] = 4;

        Quest[0] = new Question(Q[0],QC[0],QA[0]);
        Quest[1] = new Question(Q[1],QC[1],QA[1]);
        Quest[2] = new Question(Q[2],QC[2],QA[2]);
        Quest[3] = new Question(Q[3],QC[3],QA[3]);
        Quest[4] = new Question(Q[4],QC[4],QA[4]);

    };

    public static int AskAQuestion (Question QuestRec) {

        int choice;

        Scanner choiceitem1 = new Scanner(System.in);
        System.out.println(QuestRec.questions);
        System.out.println(QuestRec.choices);
        System.out.print("Enter answer > ");
        choice = choiceitem1.nextInt();     //get entry answer
        if (QuestRec.answer == choice) return 1;
        return 0;
    }


}
