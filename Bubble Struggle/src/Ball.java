/**
 * This class contains attributes and methods of the balls.
 * 
 * @author Mehmet Ali Özdemir
 * @since 16.04.2023
 */

public class Ball {
	private static final double HEIGHT_MULTIPLIER = 1.75;
	private static final double RADIUS_MULTIPLIER = 2.0;
	
	private double minPossibleHeight = Player.getPlayerScaleY() * 1.4;;
	private double minPossibleRadius = Environment.getScaleY() * 0.0175;
	private int level;
	
	private double Vx = Environment.getScaleX() / Environment.PERIOD_OF_BALL;
	private double Vy = -0.003 * ((level + 1) * 1.1);
	
	private double currentX;
	private double currentY;
	
	private double currentRadius;
	private double maxHeight;

	
	/**
	 * this is the constructor method of Ball class. It determines the position 
	 * of the ball, level of the ball, direction it goes, its radius, and the maximum
	 * height a ball can rise.
	 * 
	 * @param currentX X coordinate of the ball
	 * @param currentY Y coordinate of the ball
	 * @param level Level of the ball
	 * @param direction Direction of the ball
	 */
	
	Ball(double currentX, double currentY, int level, String direction) {
		this.currentX = currentX;
		this.currentY = currentY;
		this.level = level;
		
		if (direction.equals("left"))
			this.Vx = -Vx;
		
		setRadius();
		setHeight();
	}
	
	public double getVx() {
		return Vx;
	}
	
	public void setVx() {
		Vx = -Vx;
	}
	
	public double getVy() {
		return Vy;
	}
	
	
	public void setVy(long ballStartTime, long ballEndTime) {
		Vy += Environment.GRAVITY * (ballEndTime - ballStartTime);
	}
	
	public double getPositionX() {
		return currentX;
	}
	
	public double getPositionY() {
		return currentY;
	}
	
	public void setPositionX(long ballStartTime, long ballEndTime) {
		currentX += getVx() * (ballEndTime - ballStartTime);

	}
	
	public void setPositionY(long ballStartTime, long ballEndTime) {
		currentY -= getVy() * (ballEndTime - ballStartTime);
	}
	
	public int getLevel() {
		return level;
	}
	
	public double getRadius() {
		return currentRadius;
	}
	
	public void setRadius() {
		switch (level) {
			case 0:
				currentRadius = minPossibleRadius;
				break;
			case 1:
				currentRadius = minPossibleRadius * RADIUS_MULTIPLIER;
				break;
			case 2:
				currentRadius = minPossibleRadius * RADIUS_MULTIPLIER * RADIUS_MULTIPLIER;
				break;
		}
	}
	
	public void setHeight() {
		switch (level) {
			case 0:
				maxHeight = minPossibleHeight;
				break;
			case 1:
				maxHeight = minPossibleHeight * HEIGHT_MULTIPLIER;
				break;
			case 2:
				maxHeight = minPossibleHeight * HEIGHT_MULTIPLIER * HEIGHT_MULTIPLIER;
				break;
		}
	}
	
	
	/**
	 * this method sets the x component of the ball's veloicty when it hits walls.
	 */
	public void xBoundaries() {
		if (currentX - currentRadius <= 0 || currentX + currentRadius >= Environment.getScaleX())
			setVx();
	}
	
	
	/**
	 * this method sets the y component of the ball's velocity when it hits ground.
	 */
	public void yBoundaries() {
		if (currentY - currentRadius <= 0) 
			ballElasticCollision();
	}
	
	
	/**
	 * this method sets the y velocity of the ball according to its maximumHeight.
	 */
	public void ballElasticCollision() {
		Vy = -Math.sqrt(2 * Environment.GRAVITY * maxHeight);
	}
	
	
	/**
	 * this method checks if the player and the ball collided or not by checking
	 * the distance between the ball's center and the boundaries of the player.
	 * 
	 * @param ball Ball ojbect
	 * @param player Player object
	 * @return if the ball and player collided or not.
	 */
	public boolean playerBallCollision(Ball ball, Player player) {	
		double pointX = Math.max(player.getPlayerX() - player.getPlayerScaleX()/2, Math.min(ball.getPositionX(), player.getPlayerX() + player.getPlayerScaleX()/2));
		double pointY = Math.max(player.getPlayerY() - Player.getPlayerScaleY()/2, Math.min(ball.getPositionY(), player.getPlayerY() + Player.getPlayerScaleY()/2));
		
		double distX = pointX - ball.getPositionX();
		double distY = pointY - ball.getPositionY();
		
		return (distX * distX + distY * distY) < ball.getRadius() * ball.getRadius();
	}
	
	
	/**
	 * this method checks if the ball and arrow collided or not by checking
	 * the distance between the ball's center and the boundaries of the arrow when it is active.
	 * @param ball Ball ojbect
	 * @param player Player object
	 * @return if the ball and arrow collided or not.
	 */
	public boolean ballArrowCollision(Ball ball, Player player) {
		if (player.getActive()) {
			double pointX = Math.max(player.arrow.getArrowX() - player.arrow.getArrowScaleX()/2, Math.min(ball.getPositionX(), player.arrow.getArrowX() + player.arrow.getArrowScaleX()/2));
			double pointY = Math.max(player.arrow.getArrowY() - player.arrow.getArrowScaleY()/2, Math.min(ball.getPositionY(), player.arrow.getArrowY() + player.arrow.getArrowScaleY()/2));
			
			double distX = pointX - ball.getPositionX();
			double distY = pointY - ball.getPositionY();
			
			return (distX * distX + distY * distY) < ball.getRadius() * ball.getRadius();
		}
		return false;
	}
	
	
	/**
	 * this method draws the ball on canvas and after updating the position of the ball.
	 * 
	 * @param ballStartTime System time after this method is called.
	 * @param ballEndTime System time before this method is called.
	 */
	public void drawBall(long ballStartTime, long ballEndTime) {
		StdDraw.setPenColor(StdDraw.RED);
		
		xBoundaries();
		yBoundaries();
		
		setVy(ballStartTime, ballEndTime);
		setPositionX(ballStartTime, ballEndTime);
		setPositionY(ballStartTime, ballEndTime);
		
		double x = getPositionX();
		double y = getPositionY();
		double radius = getRadius();
		
		String picName = "ball.png";
		StdDraw.picture(x, y, picName, radius * 2, radius * 2);
	}
}
