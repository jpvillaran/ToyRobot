package flippa.toyrobot.entities;

import flippa.toyrobot.enums.Direction;

/**
 * A class that models the location of a ToyRobot in the table.
 * The location is represented by the X and Y coordinates, as well
 * as the direction that the Toy Robot is facing.
 * @author JP
 *
 */
public class Location {
	private int x;
	private int y;
	private Direction direction;

	public Location() {
		
	}
	
	/**
	 * Constructor for setting the initial values of a location.
	 * 
	 * @param x
	 * @param y
	 * @param direction
	 */
	public Location(int x, int y, Direction direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	// Getter and setter methods should be self explanatory.
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
}
