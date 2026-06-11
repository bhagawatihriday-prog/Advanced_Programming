package Assignment_18;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        ScoreProcessor sp = new ScoreProcessor();

        System.out.print("Enter file path: ");

        String path = input.nextLine();

        try {

            int result = sp.processScoreFile(path);

            System.out.println("Processed Score: " + result);

        } catch (FileNotFoundException e) {

            System.out.println("Program ended due to missing file.");

        } catch (NumberFormatException e) {

            System.out.println("Program ended due to invalid data.");

        }

        input.close();
    }
}