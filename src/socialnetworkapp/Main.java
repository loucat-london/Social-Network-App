package socialnetworkapp;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Social Network App - please write command and press ENTER, or write 'quit' to end: ");
		
		Scanner reader = new Scanner(System.in);
		String input = reader.nextLine();
		Command cmd = new Command(input);
		
		while(!input.equals("quit")){
			cmd.setCommandline(input);
			cmd.interpret();
			input = reader.nextLine();
		}
		
		reader.close();

	}

}
