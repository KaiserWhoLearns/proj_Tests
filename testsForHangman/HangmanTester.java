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
	private static boolean testTracker;

	public static void main(String[] args) {
		try {
		printExpected("user_contact.txt");
		}
		catch (IOException x) {
			System.out.println("You met an IOException, please check your files or try to recompile the program.");
		}
		if(inputHelper()) {
			testTracker = true;
			System.out.println("The constructor is tested jointly with words() and guessesLeft(), which are really simple methods. So pleas make sure your those two methods are working correctly.");
			System.out.println("\nDo you want to test the constructor? Y/n :");
			if(inputHelper()) {
				dict1 = new HashSet<String>();
				dict2 = new HashSet<String>();
				readData("dictionary2.txt", dict1);
				readData("dictionary.txt", dict2);
				testConstructor();
			}
			System.out.println("\nDo you want to test the pattern()? Y/n :");
			if (inputHelper()) {
				boolean throwed = false;
				try {
					new HangmanManager(new ArrayList<String>(), 10, 10).pattern();
				} catch (IllegalStateException ex) {
					throwed = true;
				}
				System.out.println(throwed ? "Test passed." : "pattern() should throw IllegalStateException when the words is empty.");
			}
			System.out.println("\nDo you want to test the record()? Y/n :");
			if(inputHelper()) {
				dict1 = new HashSet<String>();
				dict2 = new HashSet<String>();
				readData("dictionary2.txt", dict1);
				readData("dictionary.txt", dict2);
				testRecord();
			}
			if(testTracker) {
				System.out.println("All test(not included exception) Passed. Congratulatins:)");
			}
		} else {
			System.out.println("\nI am sorry. Looking forward for next time :)");
		}
		
	}

	public static void testConstructor() {
		System.out.println("Testing the constructor as well as words() and guessesLeft()...");
		//Data1
		System.out.println("Using data1...");
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
			testTracker = false;
		}
		System.out.println("Testing guessesLeft()...");
		if(h1.guessesLeft() == 7) {
			System.out.println("Constructor and guessesLeft() passed.");
		} else {
			System.out.println("Error:");
			System.out.println("Expected guessLeft: 7");
			System.out.println("Your guessLeft:" + h1.guessesLeft());
			testTracker = false;
		}
		System.out.println("Testing guesses()...");
		if(h1.guesses().isEmpty()) {
			System.out.println("Constructor and guesses() passed.");
		} else {
			System.out.println("Error:");
			System.out.println("Expected guesses: []");
			System.out.println("Your guesses: ");
			System.out.print("h1.guesses");
			testTracker = false;
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
		System.out.println();
	}

	public static void testRecord() {
		boolean result = true;
		System.out.println("Testing record()...");
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
			result = false;
			testTracker = false;
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
			result = false;
			testTracker = false;
		}
		//test3
		HangmanManager h6 = new HangmanManager(dict2, 8, 14);
		h6.record('a');
		h6.record('o');
		h6.record('i');
		h6.record('u');
		h6.record('e');
		if(h6.pattern().equals("- e - e - - e -")) {
			System.out.println("Data2, pass!");
		} else {
			System.out.println("Expected: - e - e - - e -");
			System.out.println("Your output: " + h6.pattern());
			System.out.println("Check either your pattern() or Record()");
			result = false;
			testTracker = false;
		}

		if(! result) {
			System.out.println("Everything passed! Your record() works well!");
		}
		System.out.println();
	}

	private static void readData(String fileName, Set<String> dict) {
		try{
        	Scanner input = new Scanner(new File(fileName));
        	while (input.hasNextLine()) {
            	String word = input.nextLine().trim();
            	dict.add(word);
			}
			}
		catch (FileNotFoundException ex) {
			System.out.println("Did not found your " + fileName + " file, did you download it?");
		}
	}

	private static void printExpected(String fileName) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
   			String line = null;
   			while ((line = br.readLine()) != null) {
       			System.out.println(line);
   			}
		}
	}

	private static boolean inputHelper() {
		Scanner ans = new Scanner(System.in);
		String ans1 = ans.next();
		if(ans1.equals("Y") || ans1.equals("y")) {
			return true;
		} else if(ans1.equals("N") || ans1.equals("n")) {
			return false;
		} else {
			System.out.println("Please input y or n : ");
			return inputHelper();
		}
	}
}
