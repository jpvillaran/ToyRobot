package flippa.toyrobot.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import flippa.toyrobot.entities.Command;
import flippa.toyrobot.entities.Location;
import flippa.toyrobot.enums.Action;
import flippa.toyrobot.enums.Direction;
import flippa.toyrobot.exception.ToyRobotException;

public class CommandParser {
	private static String SPACE = " ";
	private static String COMMA = ",";
	private static String NUMBER_PATTERN = "[0-9]+";
	
	private static CommandParser instance;
	
	private CommandParser() {
		
	}
	
	public static CommandParser getInstance() {
		if (instance == null) {
			instance = new CommandParser();
		}
		return instance;
	}
	
	public Command parseCommand(String commandLine) throws ToyRobotException {
		
		StringTokenizer st = new StringTokenizer(commandLine, SPACE);
		
		if (!st.hasMoreTokens()) {
			throw new ToyRobotException("The command is invalid.");
		}
		
		String commandWord = st.nextToken().toUpperCase();
		
		if (!Action.hasValue(commandWord)) {
			throw new ToyRobotException("The command is invalid.");
		}
		
		Command command = new Command(Action.valueOf(commandWord));
		
		switch (command.getAction()) {
			case PLACE :
				if (!st.hasMoreTokens()) {
					throw new ToyRobotException("A PLACE command must be followed by the location.");
				}
				command.setLocation(this.parseLocation(st.nextToken()));
				break;
			default :
				break;
		}
		
		return command;
	}
	
	private Location parseLocation(String locationLine) throws ToyRobotException {
		StringTokenizer st = new StringTokenizer(locationLine, COMMA);
		List<String> data = new ArrayList<String>();
		
		while(st.hasMoreTokens()) {
			data.add(st.nextToken().trim().toUpperCase());
		}
		
		if (data.size() < 3 || 
			!data.get(0).matches(NUMBER_PATTERN) || 
			!data.get(1).matches(NUMBER_PATTERN) ||
			!Direction.hasValue(data.get(2))) {
			throw new ToyRobotException("The location value is invalid.");
		}
		
		return new Location(Integer.parseInt(data.get(0)), Integer.parseInt(data.get(1)), Direction.valueOf(data.get(2)));
	}
}
