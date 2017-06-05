package flippa.toyrobot.entities;

import flippa.toyrobot.enums.Direction;
import flippa.toyrobot.exception.ToyRobotException;

public class ToyRobot {
	private static int WHOLE_ROTATION = 4;
	private static int CLOCKWISE = 1;
	private static int COUNTER_CLOCKWISE = -1;
	private static int LOWER_LIMIT = 0;
	
	private Location location;
	private Table table;
	
	public ToyRobot(Table table) {
		this(new Location(0, 0, Direction.NORTH), table);
	}
	
	public ToyRobot(Location location, Table table) {
		this.location = location;
		this.table = table;
	}
	
	// REPORT
	public Location getCurrentLocation() {
		return this.location;
	}
	
	
	// PLACE
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
	
	// MOVE
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
	
	// LEFT
	public void turnLeft() {
		this.rotate(COUNTER_CLOCKWISE);
	}
	
	// RIGHT
	public void turnRight() {
		this.rotate(CLOCKWISE);
	}
	
	public void performCommand(Command command) throws ToyRobotException {
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
	
	@Override
	public String toString() {
		return String.format("The current location is (X = %d, Y = %d, Direction = %s)", 
				this.getCurrentLocation().getX(), 
				this.getCurrentLocation().getY(),
				this.getCurrentLocation().getDirection().toString());
	}

	private Location rotate(int count) {
		int currentDirection = this.location.getDirection().getNumberValue() + count;
		if (currentDirection < 0) {
			currentDirection += WHOLE_ROTATION;
		}
		this.getCurrentLocation().setDirection(Direction.withNumberValue(currentDirection));
		
		return this.getCurrentLocation();
	}
	
	private Location cloneLocation() {
		return new Location(this.getCurrentLocation().getX(), this.getCurrentLocation().getY(), this.getCurrentLocation().getDirection());
	}
}
