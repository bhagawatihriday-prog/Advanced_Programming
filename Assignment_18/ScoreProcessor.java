package Assignment_18;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScoreProcessor {

    public int processScoreFile(String filePath) throws FileNotFoundException {

        Scanner sc = null;

        try {

            File file = new File(filePath);

            sc = new Scanner(file);

            String data = sc.nextLine();

            int score = Integer.parseInt(data);

            return score * 10;

        } catch (FileNotFoundException e) {

            System.out.println("Error: File not found.");
            throw e;

        } catch (NumberFormatException e) {

            System.out.println("Error: Invalid number format.");
            throw e;

        } finally {

            if (sc != null) {
                sc.close();
            }

            System.out.println("File cleanup completed");
        }
    }
}