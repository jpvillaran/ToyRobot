package flippa.toyrobot.enums;

import java.util.Arrays;

public enum Action {
	PLACE,
	MOVE,
	LEFT,
	RIGHT,
	REPORT;
	
	public static boolean hasValue(String value) {
		return Arrays.asList(Action.values()).stream().filter(v -> v.toString().equals(value.toUpperCase())).count() > 0;
	}
}
