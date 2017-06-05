package flippa.toyrobot.entities;

import flippa.toyrobot.enums.Direction;

public class Location {
	private int x;
	private int y;
	private Direction direction;

	public Location() {
		
	}
	
	public Location(int x, int y, Direction facing) {
		this.x = x;
		this.y = y;
		this.direction = facing;
	}
	
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
