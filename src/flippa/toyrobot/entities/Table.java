package flippa.toyrobot.entities;

/**
 * A class that represents the table.  The details
 * that are to be used for a table will be the
 * length and the width.
 * 
 * @author JP
 *
 */
public class Table {
	private int length;
	private int width;
	
	public Table(int length, int width) {
		this.length = length;
		this.width = width;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	
}
