/**
 * This class contains attributes and methods bar and time bar.
 * 
 * @author Mehmet Ali Özdemir
 * @since 16.04.2023
 */
public class Bar {
	private double barHeightScale = 1;
	private double barScaleX = Environment.getScaleX()/2;
	private double barScaleY = -1;
	private double barX = barScaleX;
	private double barY = barScaleY / 2;
	
	private double timeBarHeightScale = 0.5;
	private double timeBarWidth = Environment.getScaleX()/2;
	private double timeBarX;
	private double timeBarY = barScaleY/2;
	
	private int greenValue = 225;
	
	
	public void setTimeBarX(double x) {
		timeBarX = x;
	}
	
	public void setGreenValue(long timePassed) {
		greenValue = 225 - (int) timePassed/225;
	}
	
	public void setTimeBarWidth(double barScaleX, long timePassed) {
 		timeBarWidth = barScaleX - barScaleX * timePassed / Environment.TOTAL_GAME_DURATION;
	}
	
	
	/**
	 * this method draws the bar on the canvas.
	 */
	public void displayBar() {
		StdDraw.picture(barX, barY, "bar.png", barScaleX * 2, barHeightScale);
	}
	
	
	/**
	 * this method updates the position, color and the scales 
	 * of the time bar and draws it on the canvas.
	 */
	public void drawTimeBar() {
		long currentTime = System.currentTimeMillis();
		long timePassed =  currentTime - Environment.getStartTime();
		
		setGreenValue(timePassed);
 		StdDraw.setPenColor(225, greenValue, 0);
 		
 		setTimeBarWidth(barScaleX, timePassed);
 		setTimeBarX(timeBarWidth);
		
 		StdDraw.filledRectangle(timeBarX, timeBarY, Math.abs(timeBarWidth), timeBarHeightScale/2);
	}
	
	
	/**
	 * this method checks the whether the time is up or now.
	 * @return boolean if the time is out or now
	 */
	
	public boolean timeOut() {
		long currentTime = System.currentTimeMillis();
		long timePassed =  currentTime - Environment.getStartTime();
		
		if (timePassed >= Environment.TOTAL_GAME_DURATION) 
			return true;
		
		return false;
	}
}
