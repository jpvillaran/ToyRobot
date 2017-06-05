package flippa.toyrobot.test;

import static org.junit.Assert.*;

import org.junit.Test;

import flippa.toyrobot.entities.Command;
import flippa.toyrobot.entities.Location;
import flippa.toyrobot.entities.Table;
import flippa.toyrobot.entities.ToyRobot;
import flippa.toyrobot.enums.Action;
import flippa.toyrobot.enums.Direction;
import flippa.toyrobot.exception.ToyRobotException;

/**
 * Unit Tests for the Toy Robot commands.
 * 
 * @author JP
 *
 */
public class ToyRobotTests {

	@Test
	public void getCurrentLocation_should_return_a_null_location() {
		ToyRobot robot = new ToyRobot(null, null);
		
		assertNull(robot.getCurrentLocation());
	}

	@Test
	public void getCurrentLocation_should_return_a_location_at_base_facing_north() {
		ToyRobot robot = new ToyRobot(new Table(5,5));
		
		assertEquals(robot.getCurrentLocation().getX(), 0);
		assertEquals(robot.getCurrentLocation().getY(), 0);
		assertEquals(robot.getCurrentLocation().getDirection(), Direction.NORTH);
	}

	@Test
	public void getCurrentLocation_should_return_a_valid_location() {
		Location location = new Location(2, 2, Direction.EAST);
		ToyRobot robot = new ToyRobot(location, new Table(5,5));
		
		assertEquals(robot.getCurrentLocation().getX(), location.getX());
		assertEquals(robot.getCurrentLocation().getY(), location.getY());
		assertEquals(robot.getCurrentLocation().getDirection(), location.getDirection());
	}
	
	@Test
	public void setNewLocation_should_succeed_if_a_valid_location_is_provided() throws ToyRobotException {
		Location location = new Location(3, 3, Direction.EAST);
		ToyRobot robot = new ToyRobot(new Table(5,5));
		robot.setNewLocation(location);		
		
		assertEquals(robot.getCurrentLocation().getX(), location.getX());
		assertEquals(robot.getCurrentLocation().getY(), location.getY());
		assertEquals(robot.getCurrentLocation().getDirection(), location.getDirection());
	}

	@Test(expected=ToyRobotException.class)
	public void setNewLocation_should_throw_an_exception_if_the_location_is_not_within_the_table_bounds() throws ToyRobotException {
		Location location = new Location(7, 7, Direction.EAST);
		ToyRobot robot = new ToyRobot(new Table(5,5));
		robot.setNewLocation(location);

		assertEquals(robot.getCurrentLocation().getX(), location.getX());
		assertEquals(robot.getCurrentLocation().getY(), location.getY());
		assertEquals(robot.getCurrentLocation().getDirection(), location.getDirection());
	}
	
	@Test
	public void move_should_be_successful_if_the_robot_is_not_going_to_fall_from_the_edge() throws ToyRobotException {
		Location location = new Location(3, 3, Direction.EAST);
		ToyRobot robot = new ToyRobot(location, new Table(5,5));
		
		robot.move();
		
		assertEquals(robot.getCurrentLocation().getX(), location.getX() + 1);
		assertEquals(robot.getCurrentLocation().getY(), location.getY());
		assertEquals(robot.getCurrentLocation().getDirection(), location.getDirection());
	}

	@Test(expected=ToyRobotException.class)
	public void move_should_throw_an_exception_if_the_robot_will_fall_from_the_north() throws ToyRobotException {
		Location location = new Location(4, 4, Direction.NORTH);
		ToyRobot robot = new ToyRobot(location, new Table(5,5));
		
		robot.move();
		
		assertEquals(robot.getCurrentLocation().getX(), location.getX());
		assertEquals(robot.getCurrentLocation().getY(), location.getY());
		assertEquals(robot.getCurrentLocation().getDirection(), location.getDirection());
	}

	@Test(expected=ToyRobotException.class)
	public void move_should_throw_an_exception_if_the_robot_will_fall_from_the_east() throws ToyRobotException {
		Location location = new Location(4, 4, Direction.EAST);
		ToyRobot robot = new ToyRobot(location, new Table(5,5));
		
		robot.move();
		
		assertEquals(robot.getCurrentLocation().getX(), location.getX());
		assertEquals(robot.getCurrentLocation().getY(), location.getY());
		assertEquals(robot.getCurrentLocation().getDirection(), location.getDirection());
	}

	@Test(expected=ToyRobotException.class)
	public void move_should_throw_an_exception_if_the_robot_will_fall_from_the_south() throws ToyRobotException {
		Location location = new Location(4, 0, Direction.SOUTH);
		ToyRobot robot = new ToyRobot(location, new Table(5,5));
		
		robot.move();

		assertEquals(robot.getCurrentLocation().getX(), location.getX());
		assertEquals(robot.getCurrentLocation().getY(), location.getY());
		assertEquals(robot.getCurrentLocation().getDirection(), location.getDirection());
	}

	@Test(expected=ToyRobotException.class)
	public void move_should_throw_an_exception_if_the_robot_will_fall_from_the_west() throws ToyRobotException {
		Location location = new Location(0, 4, Direction.WEST);
		ToyRobot robot = new ToyRobot(location, new Table(5,5));
		
		robot.move();

		assertEquals(robot.getCurrentLocation().getX(), location.getX());
		assertEquals(robot.getCurrentLocation().getY(), location.getY());
		assertEquals(robot.getCurrentLocation().getDirection(), location.getDirection());
	}

	@Test
	public void turnLeft_should_change_the_direction_of_the_robot() {
		Location location = new Location(0, 4, Direction.WEST);
		ToyRobot robot = new ToyRobot(location, new Table(5,5));
		
		robot.turnLeft();

		assertEquals(robot.getCurrentLocation().getX(), location.getX());
		assertEquals(robot.getCurrentLocation().getY(), location.getY());
		assertEquals(robot.getCurrentLocation().getDirection(), Direction.SOUTH);

		robot.turnLeft();

		assertEquals(robot.getCurrentLocation().getX(), location.getX());
		assertEquals(robot.getCurrentLocation().getY(), location.getY());
		assertEquals(robot.getCurrentLocation().getDirection(), Direction.EAST);

		robot.turnLeft();

		assertEquals(robot.getCurrentLocation().getX(), location.getX());
		assertEquals(robot.getCurrentLocation().getY(), location.getY());
		assertEquals(robot.getCurrentLocation().getDirection(), Direction.NORTH);

		robot.turnLeft();

		assertEquals(robot.getCurrentLocation().getX(), location.getX());
		assertEquals(robot.getCurrentLocation().getY(), location.getY());
		assertEquals(robot.getCurrentLocation().getDirection(), Direction.WEST);
	}

	@Test
	public void turnRight_should_change_the_direction_of_the_robot() {
		Location location = new Location(0, 4, Direction.WEST);
		ToyRobot robot = new ToyRobot(location, new Table(5,5));
		
		robot.turnRight();

		assertEquals(robot.getCurrentLocation().getX(), location.getX());
		assertEquals(robot.getCurrentLocation().getY(), location.getY());
		assertEquals(robot.getCurrentLocation().getDirection(), Direction.NORTH);

		robot.turnRight();

		assertEquals(robot.getCurrentLocation().getX(), location.getX());
		assertEquals(robot.getCurrentLocation().getY(), location.getY());
		assertEquals(robot.getCurrentLocation().getDirection(), Direction.EAST);

		robot.turnRight();

		assertEquals(robot.getCurrentLocation().getX(), location.getX());
		assertEquals(robot.getCurrentLocation().getY(), location.getY());
		assertEquals(robot.getCurrentLocation().getDirection(), Direction.SOUTH);

		robot.turnRight();

		assertEquals(robot.getCurrentLocation().getX(), location.getX());
		assertEquals(robot.getCurrentLocation().getY(), location.getY());
		assertEquals(robot.getCurrentLocation().getDirection(), Direction.WEST);
	}

	@Test
	public void performCommand_should_succeed_on_a_valid_command() throws ToyRobotException {
		Location location = new Location(0, 0, Direction.EAST);
		ToyRobot robot = new ToyRobot(location, new Table(5,5));
		
		robot.performCommand(new Command(Action.MOVE));
		
		assertEquals(robot.getCurrentLocation().getX(), location.getX() + 1);
		assertEquals(robot.getCurrentLocation().getY(), location.getY());
		assertEquals(robot.getCurrentLocation().getDirection(), Direction.EAST);
		
		robot.performCommand(new Command(Action.LEFT));

		assertEquals(robot.getCurrentLocation().getX(), location.getX() + 1);
		assertEquals(robot.getCurrentLocation().getY(), location.getY());
		assertEquals(robot.getCurrentLocation().getDirection(), Direction.NORTH);
	}
	
	@Test(expected=ToyRobotException.class)
	public void performCommand_should_throw_an_exception_when_the_command_location_is_invalid() throws ToyRobotException {
		Location location = new Location(0, 0, Direction.EAST);
		ToyRobot robot = new ToyRobot(location, new Table(5,5));
		
		robot.performCommand(new Command(Action.PLACE, new Location(7, 7, Direction.NORTH)));

		assertEquals(robot.getCurrentLocation().getX(), location.getX());
		assertEquals(robot.getCurrentLocation().getY(), location.getY());
		assertEquals(robot.getCurrentLocation().getDirection(), location.getDirection());
	}
	
	@Test(expected=ToyRobotException.class)
	public void performCommand_should_throw_an_exception_when_a_null_command_location_is_given() throws ToyRobotException {
		Location location = new Location(0, 0, Direction.EAST);
		ToyRobot robot = new ToyRobot(location, new Table(5,5));
		
		robot.performCommand(null);

		assertEquals(robot.getCurrentLocation().getX(), location.getX());
		assertEquals(robot.getCurrentLocation().getY(), location.getY());
		assertEquals(robot.getCurrentLocation().getDirection(), location.getDirection());
	}
}
