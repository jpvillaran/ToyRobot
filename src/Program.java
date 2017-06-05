
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

/**
 * Main program for executing the Toy Robot.
 * 
 * @author JP
 *
 */
public class Program {
	
	private CommandParser parser = CommandParser.getInstance();
	private ToyRobot robot = new ToyRobot(this.createTable());
	private boolean isFirstCommandReady = false;

	/**
	 * This is the entry point of the program.  The execution will depend on the arguments.  If it has
	 * zero arguments, it will assume that the user will enter commands.  If it has an argument, it will interpret
	 * the first argument as a filename.
	 * @param args
	 */
	public static void main(String[] args) {
		Program p = new Program();
		
		p.execute(args);
	}
	
	/**
	 * Executes the toy robot commands, either via command line or via a file.
	 * @param args
	 */
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
	
	/**
	 * Commands the toy robot via an input from the user.
	 */
	private void executeViaCommand() {
		Scanner sc = new Scanner(System.in);
		String input = this.readLine(sc);
		
		while (!this.isQuiting(input)) {
			this.invokeRobotCommand(input);
			input = this.readLine(sc);
		}
		sc.close();
	}
	
	/**
	 * Gives commands to the toy robot via command lines from the file.
	 * 
	 * @param filename
	 */
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
	
	/**
	 * Reads a line from the console input and returns the string.
	 * 
	 * @param sc
	 * @return
	 */
	private String readLine(Scanner sc) {
		System.out.print("Enter a command (or Q<Enter> to exit): ");
		return sc.nextLine();
	}
	
	/**
	 * Invokes the toy robot command by translating a string input into a command.
	 * 
	 * @param input
	 * @param willPrintNextLine
	 */
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
	
	/**
	 * Invokes the toy robot command by translating a string input into a command.
	 * 
	 * @param input
	 */
	private void invokeRobotCommand(String input) {
		invokeRobotCommand(input, true);
	}
	
	/**
	 * Determines if the user input is a quit command.
	 * 
	 * @param input
	 * @return
	 */
	private boolean isQuiting(String input) {
		return input.trim().toUpperCase().equals("Q");
	}
	
	/**
	 * Creates a 5x5 table for usage by the robot.
	 * 
	 * @return
	 */
	private Table createTable() {
		return new Table(5,5);
	}
}
