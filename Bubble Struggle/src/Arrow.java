/**
 * This class contains attributes and methods of arrow.
 * 
 * @author Mehmet Ali Özdemir
 * @since 16.04.2023
 */

public class Arrow {
	private final int PERIOD_OF_ARROW = 1500;
	
	private double arrowX;
	private double arrowY;
	private double arrowScaleY;
	
	private long arrowStartTime;
	
	public double getArrowX() {
		return arrowX;
	}
	
	public void setArrowX(double x) {
		arrowX = x;
	}
	
	public double getArrowY() {
		return arrowY;
	}
	
	public void setArrowY() {
		arrowY = 0.5;
	}
	
	public double getArrowScaleX() {
		return 0.15;
	}
	
	public double getArrowScaleY() {
		return arrowScaleY;
	}
	
	public void setArrowScaleY() {
		arrowScaleY = 1;
	}
	
	public void setArrowStartTime(long time) {
		arrowStartTime = time;
	}
	
	
	/**
	 * this method draws the arrow on canvas after updating the position of the arrow
	 * according to the time passed.
	 * 
	 * @param player Player object
	 */
	public void drawArrow(Player player) {
		long currentTime = System.currentTimeMillis();
		long timePassed = currentTime - arrowStartTime;
		
		arrowScaleY = 1 + 7 * (double) timePassed/PERIOD_OF_ARROW;
		arrowY = 0.5 +  3.5 * (double) timePassed/PERIOD_OF_ARROW;
		
		if (arrowScaleY <= 9) {
			StdDraw.picture(arrowX, arrowY, "arrow.png", 0.15, arrowScaleY);
		} else {
			currentTime = System.currentTimeMillis();
			player.setActive(false);
		}
	}
}
