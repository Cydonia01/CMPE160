/**
 * This class contains attributes and methods of player.
 * 
 * @author Mehmet Ali Özdemir
 * @since 16.04.2023
 */
import java.awt.event.KeyEvent;

public class Player {
	Arrow arrow = new Arrow(); // creating the arrow object
	
	private double playerX = Environment.getScaleX() / 2;
	private static double playerScaleY = (Environment.getScaleY() - 1) / 8;
	
	private double playerY = playerScaleY / 2;
	private double playerScaleX = 27.0/37.0;
	
	private boolean active = false; // used the check that if arrow is active or not
	private double playerVx = Environment.getScaleX() / 417; // the x component of the player's velocity.
	
	public double getPlayerX() {
		return playerX;
	}
	
	public void setPlayerX(double x) {
		playerX = x;
	}
	
	public double getPlayerY() {
		return playerY;
	}
	
	public void setPlayerY() {
		playerY = playerScaleY / 2;
	}
	
	public double getPlayerScaleX() {
		return playerScaleX;
	}
	
	public static double getPlayerScaleY() {
		return playerScaleY;
	}
	
	public boolean getActive() {
		return active;
	}
	
	public void setActive(boolean confirm) {
		active = confirm;
	}
	
	
	/**
	 * this method makes move according to the keyboard input. It also doesn't allow player
	 * to go out of the canvas' boundaries.
	 * 
	 * @param playerStartTime System time after this method is called.
	 * @param playerEndTime System time before this method is called.
	 */
	public void makeMove(long playerStartTime, long playerEndTime) {
		if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
			
			if (playerX - playerScaleX/2 >= 0 && playerX + playerScaleX/2 <= 16) 
				playerX -= playerVx * (playerEndTime - playerStartTime) / Environment.PAUSE_DURATION;
		}
		
		if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
			
			if (playerX - playerScaleX/2 >= 0 && playerX + playerScaleX/2 <= 16) 
				playerX += playerVx * (playerEndTime - playerStartTime) / Environment.PAUSE_DURATION;
		}
		
		if (playerX - playerScaleX/2 < 0) 
			playerX = playerScaleX/2;
			
		if (playerX + playerScaleX/2 > 16) 
			playerX = Environment.getScaleX() - playerScaleX/2;
	}
	
	
	/**
	 * this method draws the player on the canvas and calls the makeMove method
	 * to update the position of the player.
	 * 
	 * @param playerStartTime System time after this method is called.
	 * @param playerEndTime System time before this method is called.
	 */
	public void drawPlayer(long playerStartTime, long playerEndTime) {
		makeMove(playerStartTime, playerEndTime);
		StdDraw.picture(playerX, playerY, "player_back.png", playerScaleX, playerScaleY);
	}
	
	
	/**
	 * this method shoots and draws the arrow on the canvas when the arrow not active.
	 * 
	 * @param player Player object
	 */
	public void shootArrow(Player player) {
		if (active)
			arrow.drawArrow(player);
		
		else if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
			arrow.setArrowStartTime(System.currentTimeMillis());
			arrow.setArrowX(playerX);
			arrow.setArrowY();
			arrow.setArrowScaleY();
			active = true;
		}
	}
}
