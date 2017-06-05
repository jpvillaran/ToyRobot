package flippa.toyrobot.enums;

import java.util.Arrays;

/**
 * An enumeration of the directions that the toy robot will face.
 * 
 * @author JP
 *
 */
public enum Direction {
	NORTH(0),
	EAST(1),
	SOUTH(2),
	WEST(3);
	
	/**
	 * A numeric representation of the direction.  This is used for rotating
	 * the toy robot.
	 */
	private int numberValue;
	
	/**
	 * Constructor for the Direction enumeration.
	 * @param numberValue
	 */
	Direction(int numberValue) {
		this.numberValue = numberValue;
	}
	
	/**
	 * Returns the numeric representation of a direction.
	 * @return
	 */
	public int getNumberValue() {
		return this.numberValue;
	}
	
	/**
	 * Provides a Direction enum using a numeric representation.  If the 
	 * value exceeds 3, a modulus value is used to determine the direction.
	 * @param numberValue
	 * @return
	 */
	public static Direction withNumberValue(int numberValue) {
		return Arrays
			.asList(Direction.values())
			.stream()
			.filter((v) -> v.getNumberValue() == (numberValue % 4)).findFirst().get();
	}
	
	/**
	 * Determines if given string is one of the directions in the enumeration.
	 * @param value
	 * @return
	 */
	public static boolean hasValue(String value) {
		return Arrays.asList(Direction.values()).stream().filter(v -> v.toString().equals(value.toUpperCase())).count() > 0;
	}
}
