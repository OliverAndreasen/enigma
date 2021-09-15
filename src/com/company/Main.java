package com.company;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        // repeat always - until system.exit
        while (true) {
            System.out.println("Velkommen til Enigma-projektet!");
            System.out.println("Vælg mellem en form for kryptering:");
            System.out.println(" 1) Caesar cipher");
            System.out.println(" 0) afslut program");
            System.out.print("vælg (0-1): ");
            int type = scanner.nextInt();
            scanner.nextLine(); // FIX: Scanner Bug to ignore empty line
            if (type == 1) {
                System.out.println("Caesar cipher");
                System.out.println("-------------");
            } else if (type == 0) {
                System.out.println("Du har afsluttet programmet");
                System.exit(0);
            }

            System.out.println("Vælg (e)ncrypt eller (d)ecrypt");
            char mode = scanner.nextLine().charAt(0);

            if (type == 1 && mode == 'e') {
                encryptCaesarMenu();
            } else if (type == 1 && mode == 'd') {
                decryptCaesarMenu();
            }
        }

    }

    public static int letterToNumber(char letter) {
        String alfabet = " ABCDEFGHIJKLMNOPQRSTUVWXYZÆØÅ";
        int number = alfabet.indexOf(letter);
        return number;
    }

    public static int[] textToListOfNumbers(String text) {
        int[] numbers = new int[text.length()];
        char[] letters = text.toCharArray();

        for (int i = 0; i < text.length(); i++) {
            numbers[i] = letterToNumber(letters[i]);
        }
        return numbers;
    }

    public static char numberToLetter(int num) {
        String alfabet = " ABCDEFGHIJKLMNOPQRSTUVWXYZÆØÅ";
        char letter = alfabet.charAt(num);
        return letter;
    }

    public static String listOfNumbersToText(int[] numbers) {
        String text = "";

        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            char letter = numberToLetter(number);
            text = text + letter;
        }
        return text;
    }

    // Caesar
    public static void decryptCaesarMenu() {
        // beder brugeren om ciphertext
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Indtast krypteret tekst");
        String cipherText = sc.nextLine();  // Read user input
        // beder brugeren om shift-værdi
        System.out.println("Indtast shift-værdien");
        int shift = sc.nextInt();  // Read user input
        //Omskriver positiv til negativ tal
        shift = -shift;
        // kalder caesarDecrypt med ciphertext og shift-værdi
        String deCryptText = caesarDecrypt(cipherText, shift);
        // udskriver plaintext modtaget fra ovenstående
        System.out.println(deCryptText);
    }

    public static void encryptCaesarMenu() {
        // beder brugeren om plaintext
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Hej indtast din besked du vil kryptere");
        String text = sc.nextLine();  // Read user input
        // beder brugeren om shift-værdi
        System.out.println("Indtast shift-værdi");
        int shift = sc.nextInt();
        // kalder caesarEncrypt med ciphertext og shift-værdi
        String encryptedText = caesarEncrypt(text, shift);
        // udskriver ciphertext modtaget fra ovenstående
        System.out.println(encryptedText);
    }


    public static int[] shiftListOfNumbers(int[] numbers, int shift) {
        int[] shiftNumbers = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            shiftNumbers[i] = shiftNumber(numbers[i], shift);
        }
        return shiftNumbers;
    }

    public static int shiftNumber(int number, int shift) {
        int shiftNumber = number + shift;
        if (shiftNumber > 29) {
            shiftNumber = Math.abs(shiftNumber - 29);
        } else if (shiftNumber <= 0) {
            // omskriver negativt tal til et positvt tal
            int positiv = Math.abs(shiftNumber);
            shiftNumber = Math.abs(29 - positiv);
        }

        if (number == 0) {
            shiftNumber = 0;
        }
        return shiftNumber;
    }

    public static String caesarDecrypt(String ciphertext, int shift) {
        ciphertext = ciphertext.toUpperCase();
        int[] listOfNumbers = textToListOfNumbers(ciphertext);
        int[] shiftListOfNumbers = shiftListOfNumbers(listOfNumbers, shift);
        String result = listOfNumbersToText(shiftListOfNumbers);

        return result;
    }


    public static String caesarEncrypt(String plaintext, int shift) {
        plaintext = plaintext.toUpperCase();
        // textToListOfNumbers
        int[] textToListOfNumbers = textToListOfNumbers(plaintext);
        // shiftListOfNumbers
        int[] shiftListOfNumbers = shiftListOfNumbers(textToListOfNumbers, shift);
        // listOfNumbersToText
        String listOfNumbersToText = listOfNumbersToText(shiftListOfNumbers);

        return listOfNumbersToText;
    }
}
