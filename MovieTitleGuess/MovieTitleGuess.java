import java.util.Scanner;
import java.io.File;
import java.util.Arrays;

public class MovieTitleGuess {

    public static void main(String[] args) throws Exception {
        // Create a random number; choose movie title from array 
        // using the chosen random number as its index
        int randomNumber = (int) (Math.random() * 25) + 1;
        boolean win = false;
        int points = 10;

        System.out.println("Time to play: Guess That Movie Title!!");
        System.out.println("Here's your movie title: ");

        // Open movie title file
        File file = new File("movies.txt");
        Scanner scanner = new Scanner(file);

        // Create array, build it with movie titles from file
        String[] movieTitlesList = new String[25];
        for (int i = 0; i < movieTitlesList.length; i++) {
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                movieTitlesList[i] = line;
            }
        }
        // System.out.println(Arrays.toString(movieTitlesList));
        String title = movieTitlesList[randomNumber];
        System.out.println(title);
        String dashedTitle = title.replaceAll("[A-Za-z]", "_");
        System.out.println(dashedTitle);
        System.out.println("The length of your title is: " + title.length() + " characters long and may contain spaces.");
        System.out.println("Good luck!");
        
        Scanner scanner2 = new Scanner(System.in);

        // loop through random title length twice
        // respond to each guess: replace dashes with correct letters
        // deduct one point (from 10 total points) for each wrong guess
        System.out.println("You have 10 points, you lose one point for each incorrect guess.");
        System.out.println("Guess a lower-case letter: ");

        while (points > 0 || !dashedTitle.equals(title)) {
            
            if (dashedTitle.equals(title)) {
                win = true;
                break;
            } else {
                String guess = scanner2.next();
                int index = title.indexOf(guess);
                if (index != -1) {
                    while (index >= 0) {
                        char[] decodedTitle = dashedTitle.toCharArray();
                        
                        decodedTitle[index] = title.charAt(index);
                        dashedTitle = String.valueOf(decodedTitle);

                        index = title.indexOf(guess, index + 1);
                    }
                    System.out.println(dashedTitle);
                } else {
                    points--;
                    if (points == 0) {
                        break;
                    } else {
                        System.out.println("You lose one point, you now have " + points + " points left!");
                    }
                }
            }
        }
        if (win) {
            System.out.println("Congratulations! You Guessed the Movie Title, and earned " + points + " points.");
        } else {
            System.out.println("Sorry, you lost");
        }  
    }
}