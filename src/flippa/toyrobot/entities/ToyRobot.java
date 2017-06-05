package flippa.toyrobot.entities;

import flippa.toyrobot.enums.Direction;
import flippa.toyrobot.exception.ToyRobotException;

/**
 * An entity representing the Toy Robot.  The robot knows 2 details:
 * the current location and the table it is currently using.  The 
 * information from those details are used to determine the actions
 * of the toy robot.
 * 
 * @author JP
 *
 */
public class ToyRobot {
	private static int WHOLE_ROTATION = 4;
	private static int CLOCKWISE = 1;
	private static int COUNTER_CLOCKWISE = -1;
	private static int LOWER_LIMIT = 0;
	
	/**
	 * The location of the robot on the table.
	 */
	private Location location;
	
	/**
	 * The table that the robot is currently on.
	 */
	private Table table;
	
	/**
	 * A constructor for creating an instance of a toy robot
	 * set at the 0,0 location, facing North.
	 * @param table
	 */
	public ToyRobot(Table table) {
		this(new Location(0, 0, Direction.NORTH), table);
	}
	
	/**
	 * A constructor for feeding the critical details
	 * to be used by the toy robot.
	 * @param location
	 * @param table
	 */
	public ToyRobot(Location location, Table table) {
		this.location = location;
		this.table = table;
	}
	
	/**
	 * Determines the current location of the toy robot 
	 * on the table.  
	 * @return
	 */
	public Location getCurrentLocation() {
		return this.location;
	}
	
	
	/**
	 * Sets a new location for the toy robot with respect to 
	 * the table.  A few checks are implemented to
	 * make sure that the new location will not go beyond the
	 * bounds of the table.
	 * 
	 * @param location
	 * @throws ToyRobotException
	 */
	public void setNewLocation(Location location) throws ToyRobotException {
		if (location == null) {
			throw new ToyRobotException("The location specified is invalid.");
		}
		else if (location.getX() > (this.table.getWidth() - 1) || 
			location.getY() > (this.table.getLength() - 1) ||
			location.getX() < LOWER_LIMIT ||
			location.getY() < LOWER_LIMIT) {
			throw new ToyRobotException("I wouldn't want to fall down the table.");
		}
		
		this.location = location;
	}
	
	/**
	 * Moves the toy robot 1 unit across the table using the
	 * direction as a means to determine how it will move.  If 
	 * the new location is invalid (bounds exceeded, etc.), the
	 * method will throw an exception.
	 * @throws ToyRobotException
	 */
	public void move() throws ToyRobotException {
		Location nextLocation = this.cloneLocation();
		
		switch(nextLocation.getDirection()) {
			case NORTH : 
				nextLocation.setY(nextLocation.getY() + 1);
				break;
			case EAST : 
				nextLocation.setX(nextLocation.getX() + 1);
				break;
			case SOUTH : 
				nextLocation.setY(nextLocation.getY() - 1);
				break;
			case WEST : 
				nextLocation.setX(nextLocation.getX() - 1);
				break;
		}
		
		this.setNewLocation(nextLocation);
	}
	
	/**
	 * Rotates the toy robot counter-clockwise.  This rotation
	 * will set the new location details for the robot, but
	 * mainly just the direction it is facing.
	 */
	public void turnLeft() {
		this.rotate(COUNTER_CLOCKWISE);
	}
	
	/**
	 * Rotates the toy robot clockwise.  This rotation
	 * will set the new location details for the robot, but
	 * mainly just the direction it is facing.
	 */
	public void turnRight() {
		this.rotate(CLOCKWISE);
	}
	
	/**
	 * Makes the Toy Robot perform an action using the command
	 * provided.  
	 * 
	 * @param command
	 * @throws ToyRobotException
	 */
	public void performCommand(Command command) throws ToyRobotException {
		if (command == null) {
			throw new ToyRobotException("A null command is invalid.");
		}
		switch (command.getAction()) {
			case PLACE : 
				this.setNewLocation(command.getLocation());
				break;
			case LEFT :
				this.turnLeft();
				break;
			case RIGHT :
				this.turnRight();
				break;
			case MOVE :
				this.move();
				break;
			case REPORT :
				System.out.println(this.toString());
				break;
		}
	}
	
	/**
	 * An override of the toString() method to display the current location
	 * of the toy robot on the table.
	 */
	@Override
	public String toString() {
		return String.format("The current location is (X = %d, Y = %d, Direction = %s)", 
				this.getCurrentLocation().getX(), 
				this.getCurrentLocation().getY(),
				this.getCurrentLocation().getDirection().toString());
	}

	/**
	 * Helper method to set the new direction that the toy robot will be facing.
	 * @param count
	 * @return
	 */
	private Location rotate(int count) {
		int currentDirection = this.location.getDirection().getNumberValue() + count;
		if (currentDirection < 0) {
			currentDirection += WHOLE_ROTATION;
		}
		this.getCurrentLocation().setDirection(Direction.withNumberValue(currentDirection));
		
		return this.getCurrentLocation();
	}
	
	/**
	 * Creates a new clone object for the location.
	 * 
	 * @return
	 */
	private Location cloneLocation() {
		return new Location(this.getCurrentLocation().getX(), this.getCurrentLocation().getY(), this.getCurrentLocation().getDirection());
	}
}
