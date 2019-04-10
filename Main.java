import java.util.Scanner;
import java.util.Random;

public class Main {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char shouldReplay = 'y';
        do {
            // The following variable determines the number of digits the user's guess must contain
            int length = 4;
            System.out.println("Enter a " + length + "-digit number to guess");
            String uniqueRandomNumber = generateUniqueRandomNumbers(length);
            boolean isGameOver = false;
            while(!isGameOver) {
                int input = scanner.nextInt();
                String inputString = String.valueOf(input);
                while (inputString.length() != length) {
                    System.out.println("The number to guess has to be exactly " + length + " digits");
                    input = scanner.nextInt();
                    inputString = String.valueOf(input);
                }
                while(!isUnique(inputString)) {
                    System.out.println("Only unique digits are allowed in your guess");
                    input = scanner.nextInt();
                    inputString = String.valueOf(input);
                }

                int dead = 0, wounded = 0;
                for(int i = 0; i < length; i++) {
                    char inputChar = inputString.charAt(i);
                    char randomChar = uniqueRandomNumber.charAt(i);
                    if(inputChar == randomChar) dead++;
                    else {
                        for(int j = 0; j < length; j++) {
                            if(uniqueRandomNumber.charAt(j) == inputChar) wounded++;
                        }
                    }
                }
                System.out.println(dead + " dead --- " + wounded + " wounded");
                if(dead == length) {
                    System.out.println("You won. The game is over");
                    isGameOver = true;
                }
            }
            System.out.println("Do you want to play again? (Enter y if yes or n if no)");
            shouldReplay = scanner.next().charAt(0);
        } while(shouldReplay == 'y');
    }
    
    static boolean isUnique(String input) {
        for (int i = 0; i < input.length(); i++) {
            char inputChar = input.charAt(i);
            for (int j = 0; j < input.length(); j++) {
              	if(i == j) continue;
                if(input.charAt(j) == inputChar) return false;
            }
        }
        return true;
    }

    static String generateUniqueRandomNumbers(int length) {
        String randString = "";
        for(int i = 0; i < length; i++) {
            randString += generateUniqueRandomNumber(randString);
        }
        return randString;
    }
  
  	static int generateUniqueRandomNumber(String generatedNumbers) {
        if(generatedNumbers.length() <= 0) return generateRandomNumber();
        int rand = generateRandomNumber();
        for(int i = 0; i < generatedNumbers.length(); i++) {
            char currentChar = generatedNumbers.charAt(i);
            char randomChar = (char) (48 + rand);
            if(currentChar == randomChar) return generateUniqueRandomNumber(generatedNumbers);
        }
        return rand;
    }

    static int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(10);
    }
}
