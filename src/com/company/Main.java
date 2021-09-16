package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // repeat always - until system.exit
        while (true) {
            System.out.println("Velkommen til Enigma-projektet!");
            System.out.println("Vælg mellem en form for kryptering:");
            System.out.println(" 1) Number cipher");
            System.out.println(" 2) Caesar cipher");
            System.out.println(" 3) Vinigére cipher");
            System.out.println(" 0) afslut program");
            System.out.print("vælg (0-3): ");
            int type = scanner.nextInt();
            scanner.nextLine(); // FIX: Scanner Bug to ignore empty line
            if (type == 1) {
                System.out.println("Number cipher");
                System.out.println("-------------");
            } else if (type == 2) {
                System.out.println("Caesar cipher");
                System.out.println("-------------");
            } else if (type == 3) {
                System.out.println("vigniére cipher");
                System.out.println("-------------");
            } else if (type == 0) {
                System.out.println("Du har afsluttet programmet");
                System.exit(0);
            }

            System.out.println("Vælg (e)ncrypt eller (d)ecrypt");
            char mode = scanner.nextLine().charAt(0);

            if (type == 1 && mode == 'e') {
                encryptNumberMenu();
            } else if (type == 1 && mode == 'd') {
                decryptNumberMenu();
            } else if (type == 2 && mode == 'e') {
                encryptCaesarMenu();
            } else if (type == 2 && mode == 'd') {
                decryptCaesarMenu();
            } else if (type == 3 && mode == 'e') {
                encryptVigniereMenu();
            } else if (type == 3 && mode == 'd') {
                decryptVigniereMenu();
            }

        }
    }

    public static int letterToNumber(char letter) {
        String alfabet = " ABCDEFGHIJKLMNOPQRSTUVWXYZÆØÅ";
        return alfabet.indexOf(letter);
    }

    public static int[] textToListOfNumbers(String text) {
        int[] numbers = new int[text.length()];
        char[] letters = text.toUpperCase().toCharArray();

        for (int i = 0; i < text.length(); i++) {
            numbers[i] = letterToNumber(letters[i]);
        }
        return numbers;
    }

    public static char numberToLetter(int num) {
        String alfabet = " ABCDEFGHIJKLMNOPQRSTUVWXYZÆØÅ";
        return alfabet.charAt(num);
    }

    public static String listOfNumbersToText(int[] numbers) {
        StringBuilder text = new StringBuilder();
        int length = numbers.length;

        for (int i = 0; i < length; i++) {
            int number = numbers[i];
            char letter = numberToLetter(number);
            text.append(letter);
        }
        return text.toString();
    }

    public static void encryptNumberMenu() {
        // beder brugeren om plaintext
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Hej indtast din besked du vil kryptere");
        String text = sc.nextLine();  // Read user input
        // kalder textToListOfNumbers
        int[] encryptedText = textToListOfNumbers(text);
        // udskriver ciphertext modtaget fra ovenstående
        System.out.println(Arrays.toString(encryptedText));
    }

    public static void decryptNumberMenu() {
        // beder brugeren om plaintext
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Hej indtast din besked du vil dekryptere");
        String text = sc.nextLine();  // Read user input
        int[] encrypted = stringToNumberArray(text);
        // kalder textToListOfNumbers
        String encryptedText = listOfNumbersToText(encrypted);
        // udskriver ciphertext modtaget fra ovenstående
        System.out.println(encryptedText);
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
        String deCryptText = decryptCaesar(cipherText, shift);
        // udskriver plaintext modtaget fra ovenstående
        System.out.println(deCryptText);
    }

    public static void decryptVigniereMenu() {
        // beder brugeren om ciphertext
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Indtast krypteret tekst");
        String cipherText = sc.nextLine();  // Read user input
        int[] encrypted = stringToNumberArray(cipherText);
        System.out.println("Indtast keyword");
        String keyword = sc.nextLine();  // Read user input
        // kalder caesarDecrypt med ciphertext og shift-værdi
        String deCryptText = vigniereDecrypt(encrypted, keyword);
        // udskriver plaintext modtaget fra ovenstående
        System.out.println(deCryptText);
    }

    public static void encryptVigniereMenu() {

        // beder brugeren om plaintext
        Scanner sc = new Scanner(System.in);
        System.out.println("Hej indtast din besked du vil kryptere");
        String text = sc.nextLine();
        // beder brugeren om shift-værdi
        System.out.println("Indtast nøgle-ord");
        String keyword = sc.nextLine();
        // kalder caesarEncrypt med ciphertext og shift-værdi
        int[] encryptedText = vigniereEncrypt(text, keyword);
        // udskriver ciphertext modtaget fra ovenstående
        System.out.println(Arrays.toString(encryptedText));
    }


    public static int[] shiftListOfNumbers(int[] numbers, int shift) {
        int[] shiftNumbers = new int[numbers.length];
        int length = numbers.length;
        for (int i = 0; i < length; i++) {
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

    public static int shiftBackNumber(int number, int shift) {

        int shiftNumber = number - shift;
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


    public static String decryptCaesar(String ciphertext, int shift) {
        ciphertext = ciphertext.toUpperCase();
        int[] listOfNumbers = textToListOfNumbers(ciphertext);
        int[] shiftListOfNumbers = shiftListOfNumbers(listOfNumbers, shift);

        return listOfNumbersToText(shiftListOfNumbers);
    }

    public static String caesarEncrypt(String plaintext, int shift) {

        plaintext = plaintext.toUpperCase();
        // textToListOfNumbers
        int[] textToListOfNumbers = textToListOfNumbers(plaintext);
        // shiftListOfNumbers
        int[] shiftListOfNumbers = shiftListOfNumbers(textToListOfNumbers, shift);
        // listOfNumbersToText
        return listOfNumbersToText(shiftListOfNumbers);
    }

    public static int[] vigniereEncrypt(String message, String keyword) {
        int n = 0;

        int[] encryptedMessage = textToListOfNumbers(message);
        int length = encryptedMessage.length;

        int[] encryptedKeyWord = textToListOfNumbers(keyword);

        int[] encryptedResult = new int[length];

        for (int i = 0; i < length; i++) {

            int shift = encryptedKeyWord[n];
            encryptedResult[i] = shiftNumber(encryptedMessage[i], shift);
            if (n == encryptedKeyWord.length - 1) {
                n = 0;
            } else {
                n++;
            }
        }
        return encryptedResult;
    }

    public static String vigniereDecrypt(int[] encrypted, String keyword) {
        int length = encrypted.length;
        int[] encryptedKeyword = textToListOfNumbers(keyword);
        int[] decrypted = new int[encrypted.length];
        int n = 0;

        for (int i = 0; i < length; i++) {
            if (encrypted[i] != 0) {
                decrypted[i] = (shiftBackNumber(encrypted[i], encryptedKeyword[n]));
                n++;
            } else {
                decrypted[i] = 0;
                n++;
            }
            if (n == encryptedKeyword.length) {
                n = 0;
            }
        }
        System.out.println(Arrays.toString(decrypted));
        String result = listOfNumbersToText(decrypted);

        return result;

    }

    public static int[] stringToNumberArray(String encryptedMessage) {

        // Fundet på nettet
        int[] encrypted = Arrays.stream(encryptedMessage.substring(1, encryptedMessage.length() - 1).split(","))
                .map(String::trim).mapToInt(Integer::parseInt).toArray();
        return encrypted;
    }

}
