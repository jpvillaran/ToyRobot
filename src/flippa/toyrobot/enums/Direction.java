package flippa.toyrobot.enums;

import java.util.Arrays;

public enum Direction {
	NORTH(0),
	EAST(1),
	SOUTH(2),
	WEST(3);
	
	private int numberValue;
	
	Direction(int numberValue) {
		this.numberValue = numberValue;
	}
	
	public int getNumberValue() {
		return this.numberValue;
	}
	
	public static Direction withNumberValue(int numberValue) {
		return Arrays
			.asList(Direction.values())
			.stream()
			.filter((v) -> v.getNumberValue() == (numberValue % 4)).findFirst().get();
	}
	
	public static boolean hasValue(String value) {
		return Arrays.asList(Direction.values()).stream().filter(v -> v.toString().equals(value.toUpperCase())).count() > 0;
	}
}
