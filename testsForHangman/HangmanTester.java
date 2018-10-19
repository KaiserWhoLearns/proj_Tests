/*
author: Kaiser Sun;
date: 10/19/2018
*/

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//A class for CSE143 assign4 tests
public class HangmanTester {

	private static Set<String> dict1;
	private static Set<String> dict2;

	public static void main(String[] args) {
		/*try {
		printExpected("user_contact.txt");
		}
		catch (IOException x) {
			System.out.println("You met an IOException, please check your files or try to recompile the program.");
		}*/
		readData("dictionary2.txt");
		testConstructor();
		testRecord();
	}

	public static void testConstructor() {
		System.out.println("Testing the constructor as well as words() and guessesLeft()...");
		//Data1
		System.out.println("\nUsing data1...");
		HangmanManager h1 = new HangmanManager(dict1, 4, 7);
		System.out.println("Testing words()...");
		if(h1.words().containsAll(dict1) && dict1.containsAll(h1.words())) {
			System.out.println("constructor and word() passed.");
		} else {
			System.out.println("Error:");
			System.out.print("Expected wordSet:");
			System.out.println(dict1);
			System.out.println("Your wordSet:");
			System.out.print(h1.words());
		}
		System.out.println("Testing guessesLeft()...");
		if(h1.guessesLeft() == 7) {
			System.out.println("Constructor and guessesLeft() passed.");
		} else {
			System.out.println("Error:");
			System.out.println("Expected guessLeft: 7");
			System.out.println("Your guessLeft:" + h1.guessesLeft());
		}
		System.out.println("Testing guesses()...");
		if(h1.guesses().isEmpty()) {
			System.out.println("Constructor and guesses() passed.");
		} else {
			System.out.println("Error:");
			System.out.println("Expected guesses: []");
			System.out.println("Your guesses: ");
			System.out.print("h1.guesses");
		}

		//Data2
		System.out.println("Testing Exceptions...");
		System.out.println("There should be two pass message. If not, check your throw exception.");
		try {
			HangmanManager h2 = new HangmanManager(dict1, 0, 6);
		}
		catch(IllegalArgumentException x) {
			System.out.println("length < 1, Pass!");
		}

		try {
			HangmanManager h3 = new HangmanManager(dict1, 4, -1);
		}
		catch(IllegalArgumentException ex) {
			System.out.println("max < 0, pass!");
		}
	}

	public static void testRecord() {
		System.out.println("\nTesting record()...");
		//test1
		HangmanManager h4 = new HangmanManager(dict1, 4, 7);
		h4.record('e');
		h4.record('o');
		if(h4.pattern().equals("- o o -")) {
			System.out.println("Data1, pass!");
		} else {
			System.out.println("Expected: - o o -");
			System.out.println("Your output: " + h4.pattern());
			System.out.println("Check either your pattern() or Record()");
		}
		//test2
		HangmanManager h5 = new HangmanManager(dict1, 4, 7);
		h5.record('o');
		h5.record('e');
		if(h5.pattern().equals("- - e -")) {
			System.out.println("Data2, pass!");
		} else {
			System.out.println("Expected: - - e -");
			System.out.println("Your output: " + h5.pattern());
			System.out.println("Check either your pattern() or Record()");
		}

	}

	private static void readData(String fileName) {
		try{
			dict1 = new HashSet<String>();
        	Scanner input = new Scanner(new File(fileName));
        	while (input.hasNextLine()) {
            	String word = input.nextLine().trim();
            	dict1.add(word);
			}
			}
		catch (FileNotFoundException ex) {
			System.out.println("Did not found your " + fileName + " file, did you download it?");
		}
	}
}