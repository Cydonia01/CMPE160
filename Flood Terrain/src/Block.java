/**
 * This project implements a code that modifies a terrain then floods the terrain recursively.
 * 
 * @author Mehmet Ali Özdemir
 * @since Date: 10.05.2023
 */

public class Block {
	
	private int x;
	private int y;
	private int height;
	private int labelNum;
	private int neighborMinHeight;
	
	private boolean flooded;
	private boolean labeled;
	
	Block(int x, int y, int height) {
		this.height = height;
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getLabelNum() {
		return labelNum;
	}
	
	public void setLabelNum(int labelNum) {
		this.labelNum = labelNum;
	}
	
	public int getNeighborMinHeight() {
		return neighborMinHeight;
	}
	
	public void setNeighborMinHeight(int maxHeight) {
		this.neighborMinHeight = maxHeight;
	}
	
	public void setLabeled(boolean label) {
		this.labeled = label;
	}
	
	
	/**
	 * This method checks if block is labeled or not.
	 * @return labeled
	 */
	public boolean isLabeled() {
		return labeled;
	}
	
	public void setFlooded(boolean water) {
		this.flooded = water;
	}
	
	
	/** This method checks if block is flooded or not.
	 * 
	 * @return flooded
	 */
	public boolean isFlooded() {
		return flooded;
	}
	

	/**
	 * This method increments the height of block by 1 after modification.
	 */
	public void incrementHeight() {
		this.height += 1;
	}
}
