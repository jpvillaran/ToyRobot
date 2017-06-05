package flippa.toyrobot.test;

import static org.junit.Assert.*;

import org.junit.Test;

import flippa.toyrobot.entities.Command;
import flippa.toyrobot.exception.ToyRobotException;
import flippa.toyrobot.parser.CommandParser;

/**
 * Unit Tests for the CommandParser.
 * 
 * @author JP
 *
 */
public class CommandParserTests {

	@Test
	public void parseCommand_should_return_a_command_if_the_command_string_is_correct() throws ToyRobotException {
		CommandParser p = CommandParser.getInstance();
		
		Command c1 = p.parseCommand("PLACE 1,1,NORTH");
		Command c2 = p.parseCommand("LEFT");
		Command c3 = p.parseCommand("RIGHT");
		Command c4 = p.parseCommand("MOVE");
		Command c5 = p.parseCommand("REPORT");
		
		assertNotNull(c1);
		assertNotNull(c2);
		assertNotNull(c3);
		assertNotNull(c4);
		assertNotNull(c5);
	}
	
	@Test(expected=ToyRobotException.class)
	public void parseCommand_should_fail_when_the_command_string_is_empty() throws ToyRobotException {
		CommandParser p = CommandParser.getInstance();

		Command c1 = p.parseCommand("");
		
		assertNull(c1);
	}

	@Test(expected=ToyRobotException.class)
	public void parseCommand_should_fail_when_the_command_string_is_not_valid() throws ToyRobotException {
		CommandParser p = CommandParser.getInstance();

		Command c1 = p.parseCommand("TEST");
		
		assertNull(c1);
	}

	@Test(expected=ToyRobotException.class)
	public void parseCommand_should_fail_when_the_place_command_has_no_location() throws ToyRobotException {
		CommandParser p = CommandParser.getInstance();

		Command c1 = p.parseCommand("PLACE");
		
		assertNull(c1);
	}

	@Test(expected=ToyRobotException.class)
	public void parseCommand_should_fail_when_the_place_command_has_incomplete_location_details() throws ToyRobotException {
		CommandParser p = CommandParser.getInstance();

		Command c1 = p.parseCommand("PLACE 1,1");
		
		assertNull(c1);
	}

	@Test(expected=ToyRobotException.class)
	public void parseCommand_should_fail_when_the_place_command_has_non_numeric_coordinates() throws ToyRobotException {
		CommandParser p = CommandParser.getInstance();

		Command c1 = p.parseCommand("PLACE a,1,EAST");
		Command c2 = p.parseCommand("PLACE 1,b,EAST");
		Command c3 = p.parseCommand("PLACE c,d,EAST");
		
		assertNull(c1);
		assertNull(c2);
		assertNull(c3);
	}

	@Test(expected=ToyRobotException.class)
	public void parseCommand_should_fail_when_the_place_command_has_an_invalid_direction() throws ToyRobotException {
		CommandParser p = CommandParser.getInstance();

		Command c1 = p.parseCommand("PLACE 1,1,TEST");
		
		assertNull(c1);
	}
}
