
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

import flippa.toyrobot.entities.Command;
import flippa.toyrobot.entities.Table;
import flippa.toyrobot.entities.ToyRobot;
import flippa.toyrobot.exception.ToyRobotException;
import flippa.toyrobot.parser.CommandParser;

public class Program {
	
	private CommandParser parser = CommandParser.getInstance();
	private ToyRobot robot = new ToyRobot(this.createTable());
	private boolean isFirstCommandReady = false;

	public static void main(String[] args) {
		Program p = new Program();
		
		p.execute(args);
	}
	
	public void execute(String[] args) {
		System.out.println("Welcome to the Toy Robot program.\n");
		if (args.length == 0) {
			this.executeViaCommand();
		}
		else {
			this.executeViaFile(args[0]);
		}
		System.out.println("Exiting the program.");
	}
	
	private void executeViaCommand() {
		Scanner sc = new Scanner(System.in);
		String input = this.readLine(sc);
		
		while (!this.isQuiting(input)) {
			this.invokeRobotCommand(input);
			input = this.readLine(sc);
		}
		sc.close();
	}
	
	private String readLine(Scanner sc) {
		System.out.print("Enter a command (or Q<Enter> to exit): ");
		return sc.nextLine();
	}
	
	private void executeViaFile(String filename) {
		try (Stream<String> stream = Files.lines(Paths.get(filename))) {
	        stream.forEach(e -> { 
	        	if (e.trim().length() != 0) {
		        	invokeRobotCommand(e, false);
	        	}
	        });
		}
		catch (IOException e) {
			System.out.println("Unable to read the file: " + filename);
		}
	}
	
	private void invokeRobotCommand(String input, boolean willPrintNextLine) {
		try {
			Command command = parser.parseCommand(input);
			isFirstCommandReady = isFirstCommandReady || command.isPlaceCommand();
			
			if (isFirstCommandReady) {
				robot.performCommand(command);
			}
			else {
				System.out.println("A PLACE command must be executed first before commanding the Toy Robot.");
			}
		}
		catch (ToyRobotException e) {
			System.out.println(input + " >> " + e.getMessage());
		}
		if (willPrintNextLine) {
			System.out.println("\n");
		}
	}
	
	private void invokeRobotCommand(String input) {
		invokeRobotCommand(input, true);
	}
	
	private boolean isQuiting(String input) {
		return input.trim().toUpperCase().equals("Q");
	}
	
	private Table createTable() {
		return new Table(5,5);
	}
}
