package se.ju23.typespeeder;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Validator {
    public int validateInt(){
        Scanner sc = new Scanner (System.in);
        int input = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                input = sc.nextInt(); sc.nextLine();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.print("\nPlease enter a corresponding menu option: ");
                sc.nextLine();
            }
        }
        return input;
    }
}
