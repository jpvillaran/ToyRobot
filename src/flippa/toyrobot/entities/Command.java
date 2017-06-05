package flippa.toyrobot.entities;

import flippa.toyrobot.enums.Action;

public class Command {
	private Action action;
	private Location location;
	
	public Command(Action action) {
		this.action = action;
	}
	
	public Command(Action action, Location location) {
		this.action = action;
		this.location = location;
	}

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
	
	public boolean isPlaceCommand() {
		return this.getAction() == Action.PLACE;
	}
	
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
