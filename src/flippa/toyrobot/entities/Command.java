package flippa.toyrobot.entities;

import flippa.toyrobot.enums.Action;

/**
 * A class that models the details about a command that will be interpreted
 * by the ToyRobot.
 * 
 * @author JP
 *
 */
public class Command {
	private Action action;
	private Location location;
	
	/**
	 * Constructor that creates an action for the command.
	 * 
	 * @param action
	 */
	public Command(Action action) {
		this.action = action;
	}
	
	/**
	 * Constructor that creates an action and a location for a command.
	 * 
	 * @param action
	 * @param location
	 */
	public Command(Action action, Location location) {
		this.action = action;
		this.location = location;
	}

	// Getter and setter methods should be self explanatory.
	public Action getAction() {
		return action;
	}

	public Location getLocation() {
		return location;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	

	/**
	 * Determines if the command generated is a PLACE action.
	 * @return
	 */
	public boolean isPlaceCommand() {
		return this.getAction() == Action.PLACE;
	}
	
	/**
	 * Displays a formatted version of the Command (e.g. PLACE 1,1,EAST)
	 */
	@Override
	public String toString() {
		switch (this.getAction()) {
			case PLACE : 
				return String.format("%s %d,%d,%s", this.getAction().toString(), this.getLocation().getX(), this.getLocation().getY(), this.getLocation().getDirection().toString());
			default: 
				return String.format("%s", this.getAction().toString());
		}
	}
}
