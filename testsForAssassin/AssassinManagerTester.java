/*author: Kaiser
date: 10/13/2018*/
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class AssassinManagerTester {

	private static Set<String> names;
	private static List<String> names2;

	public static void main(String[] args) {
		try {
		printExpected("contact.txt");
		}
		catch (IOException x) {
			System.out.println("You are unlucky. I don't know how to solve it. Maybe you can't use this test.");
		}
		if(inputHelper()) {
			System.out.println("\nDo you want to test the constructor? Y/n :");
			if(inputHelper()) {
				testConstructor();
			}
			System.out.println("\nDo you want to test the KillRing()? Y/n :");
			if(inputHelper()) {
				testKillRing();
			}
			System.out.println("\nDo you want to test the killRingContains()? Y/n :");
			if(inputHelper()) {
				testKillRingContains();
			}
			System.out.println("\nDo you want to test the kill()? Y/n :");
			if(inputHelper()){
				testKill();
			}
			System.out.println("\nAll tests finished. Have a nice debugging time:)");
		}
		else {
			System.out.println("\nI am sorry. Looking forward for next time :)");
		}
	}

	public static void testConstructor() {
		System.out.println("Testing the constructor...");
		System.out.println("This constructor will only test on Exception.");
		System.out.println("Testing the constructor with list = []");
		try{
		AssassinManager manager = new AssassinManager(null);
		}
		catch (IllegalArgumentException x) {
			System.out.println("Pass!");
			System.out.println("Expected output: IllegalArgumentException");
			System.out.println("Your output: IllegalArgumentException");
		}
	}

	public static void testKillRing() {
		System.out.println("\nTesting the KillRing()...");
		System.out.println("You can use output comparison tool if you want.\n");
		//Data1
		System.out.println("Testing with names1.txt...");
		readData("names1.txt");
		System.out.println("Expected output: Robinson Crusoe is stalking Robinson Crusoe");
		System.out.print("Your output: ");
		AssassinManager manager = new AssassinManager(names2);
		manager.printKillRing();
		//Data2
		System.out.println("\nTesting with names2.txt...");
		readData("names2.txt");
		System.out.println("Expected output: ");
		System.out.println("    Robinson Crusoe is stalking Friday \n    Friday is stalking Robinson Crusoe");
		System.out.println("Your output: ");
		AssassinManager manager1 = new AssassinManager(names2);
		manager1.printKillRing();
		//Data3
		System.out.println("\nTesting with names.txt...");
		readData("names.txt");
		System.out.println("Expected output: ");
		try {
		printExpected("expected.txt");
		}
		catch (IOException x) {
			System.out.println("You are unlucky. I don't know how to solve it.");
		}
		System.out.println("Your output: ");
		AssassinManager manager2 = new AssassinManager(names2);
		manager2.printKillRing();
	}

	public static void testKillRingContains() {
		System.out.println("\nTesting the KillRingContains()...");
		//Data1
		System.out.println("\nTesting with names1.txt...");
		readData("names1.txt");
		System.out.println("Testing name: Robinson Crusoe");
		AssassinManager manager = new AssassinManager(names2);
		if(manager.killRingContains("Robinson Crusoe")) {
			System.out.println("Pass!");
		} else {
			System.out.println("Expected output: true");
			System.out.println("Your output: " + String.valueOf(manager.killRingContains("Robinson Crusoe")));
		}
		System.out.println("Testing name: robinson crusoe");
		if(manager.killRingContains("robinson crusoe")) {
			System.out.println("Pass!");
		} else {
			System.out.println("Expected output: true");
			System.out.println("Your output: " + String.valueOf(manager.killRingContains("Robinson Crusoe")));
			System.out.println("You should ignore the case!");
		}
		//Data2
		System.out.println("\nTesting with names2.txt...");
		readData("names2.txt");
		System.out.println("Testing name: Tuesday");
		AssassinManager manager1 = new AssassinManager(names2);
		if(! manager1.killRingContains("Tuesday")) {
			System.out.println("Pass!");
		} else {
			System.out.println("Expected output: false");
			System.out.println("Your output: " + manager1.killRingContains("Tuesday"));
		}
		System.out.println("Testing name: friday");
		if(manager1.killRingContains("friday")) {
			System.out.println("Pass!");
		} else {
			System.out.println("Expected output: true");
			System.out.println("Your output: " + manager1.killRingContains("friday"));
			System.out.println("You should ignore the case!");
		}
	}

	public static void testKill() {
		System.out.println("\nTesting kill()...");
		System.out.println("\nTesting with names.txt...");
		readData("names.txt");
		AssassinManager manager = new AssassinManager(names2);
		//Data1, test1
		manager.kill("Linus Torvalds");
		manager.kill("Grace Hopper");
		System.out.println("Expected output: ");
		try {
		printExpected("expected2.txt");
		}
		catch (IOException x) {
			System.out.println("You are unlucky. I don't know how to solve it.");
		}
		System.out.println("Your output: ");
		manager.printKillRing();
		manager.printGraveyard();
		manager.kill("ada lovelace");
		//test2
		manager.kill("alonzo church");
		manager.kill("Tim Berners-Lee");
		System.out.println("\nExpected output: ");
		try {
		printExpected("expected3.txt");
		}
		catch (IOException x) {
			System.out.println("You are unlucky. I don't know how to solve it.");
		}
		System.out.println("Your output: ");
		manager.printKillRing();
		manager.printGraveyard();
		//test3
		try {
			System.out.println("\nTesting IllegalArgumentException...");
			manager.kill("john");
		}
		catch (IllegalArgumentException | IllegalStateException z) {
			System.out.println("Pass!");
		}
		//Data2, test1
		System.out.println("\nTesting with names1.txt...");
		readData("names1.txt");
		AssassinManager manager1 = new AssassinManager(names2);
		try {
			System.out.println("\nTesting IllegalStateException...");
			manager.kill("Robinson Crusoe");
		}
		catch (IllegalStateException | IllegalArgumentException z) {
			System.out.println("Pass!");
		}

	}

	private static void readData(String fileName) {
		try{
			// read names into a list, using a Set to avoid duplicates
        	Scanner input = new Scanner(new File(fileName));
        	names = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        	names2 = new ArrayList<String>();
        	while (input.hasNextLine()) {
            	String name = input.nextLine().trim();
            	if (name.length() > 0 && !names.contains(name)) {
                	names.add(name);
                	names2.add(name);
				}
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