package flippa.toyrobot.enums;

import java.util.Arrays;

/**
 * An enumeration of the different actions that the toy robot could perform.
 * 
 * @author JP
 *
 */
public enum Action {
	PLACE,
	MOVE,
	LEFT,
	RIGHT,
	REPORT;
	
	/**
	 * Determines if a given string is one of the actions.
	 * @param value
	 * @return
	 */
	public static boolean hasValue(String value) {
		return Arrays.asList(Action.values()).stream().filter(v -> v.toString().equals(value.toUpperCase())).count() > 0;
	}
}
