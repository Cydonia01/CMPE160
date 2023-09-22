/**
 * This class creates the environment of the game and contains some constants
 * of the game. It also does most of the drawing and eventually runs the game.
 * 
 * @author Mehmet Ali Özdemir
 * @since 16.04.2023
 */

import java.awt.*;
import java.util.ArrayList;

public class Environment {
	private static int canvasWidth = 800;
	private static int canvasHeight = 500;
	private static double scaleX = 16;
	private static double scaleY = 9;
	
	public static final int TOTAL_GAME_DURATION = 40000;
	public static final int PAUSE_DURATION = 15;
	public static final int PERIOD_OF_BALL = 15000;
	public static final double GRAVITY = 0.000003;
	
	private static boolean check = true; // checking if the game is over or not. If it's true, the game is not over.
	private static boolean winCondition; // checking if player won the game or not.
	
	private static long startTime = System.currentTimeMillis();
	
	private Bar bar = new Bar(); // creating bar object
	private Player player = new Player(); // creating player object
	
	private ArrayList<Ball> balls = new ArrayList<Ball>(); // arraylist containing balls
	
	public static double getScaleX() {
		return scaleX;
	}
	
	public static double getScaleY() {
		return scaleY;
	}
	
	public static long getStartTime() {
		return startTime;
	}
	
	public static void setStartTime(long time) {
		startTime = time;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public void setCheck(boolean game) {
		check = game;
	}
	
	public boolean getWinCondition() {
		return winCondition;
	}
	
	public void setWinCondition(boolean confirm) {
		winCondition = confirm;
	}
	
	
	/**
	 * this method sets up the Canvas.
	 */
	public void setUpCanvas() {
		StdDraw.setCanvasSize(canvasWidth, canvasHeight);
		StdDraw.setXscale(0, scaleX);
		StdDraw.setYscale(-1, scaleY );
	}
	
	
	/**
	 * this method basically draws the background picture.
	 */
	public void displayBackground() {
		StdDraw.picture(scaleX/2, scaleY/2, "background.png", scaleX, scaleY);
	}
	
	
	/**
	 * this method creates the first three ball objects.
	 */
	public void createMainBalls() {
		balls.add(new Ball(scaleX/6, scaleY/5, 0, "right"));
		balls.add(new Ball(scaleX/3, scaleY/4, 1, "left"));
		balls.add(new Ball(scaleX/2, scaleY/3, 2, "right"));
	}
	
	
	/**
	 * this method creates two new balls objects and adds them into balls arraylist
	 * when a ball is popped by player.
	 * 
	 * @param index Index number coming from the for loop in runGame method.
	 */
	public void addBall(int index) {
		double x = balls.get(index).getPositionX();
		double y = balls.get(index).getPositionY();
		int level = balls.get(index).getLevel() - 1;
		
		balls.remove(index); // removing the popped ball from the balls arraylist
		
		if (level == 1) {
			balls.add(new Ball(x, y, level, "right"));
			balls.add(new Ball(x, y, level, "left"));
		}
		if (level == 0) {
			balls.add(new Ball(x, y, level, "right"));
			balls.add(new Ball(x, y, level, "left"));
		}
	}
	
	
	/** 
	 * this method removes all balls from the balls arraylist. It is used when the player
	 * restarts the game.
	 */
	public void clearBalls() {
		balls.clear();
	}
	
	
	/**
	 * this method draws gamescreen picture when the game is over using winCondition
	 * parameter. 
	 * @param winCondition win condition
	 */
	public void drawEndGame(boolean winCondition) {
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.picture(scaleX/2, scaleY/2.18, "game_screen.png", scaleX/3.8, scaleY/4);
		
		StdDraw.setFont(new Font("Helvetica", Font.BOLD, 20));
		
		if (winCondition) 
			StdDraw.text(scaleX/2, scaleY/2, "You Won!");
		else
			StdDraw.text(scaleX/2, scaleY/2, "Game Over!");
		
		StdDraw.setFont(new Font("Helvetica", Font.ITALIC, 15));
		StdDraw.text(scaleX/2, scaleY/2.3, "To Replay Click \"Y\"");
		StdDraw.text(scaleX/2, scaleY/2.6, "To Quit Click \"N\"");
	}
	
	
	/**
	 * this method runs the game. First it creates the balls and initilizes the starting times and then,
	 * it checks the conditions and draws the necessary things in a while loop.
	 * 
	 */
	public void runGame() {
		createMainBalls();
		
		long playerStartTime = System.currentTimeMillis();
		long ballStartTime = System.currentTimeMillis();
		
		while (check) {
			if (balls.size() == 0) {
				check = false;
				setWinCondition(true);
			}
			if (bar.timeOut()) {
				check = false;
				setWinCondition(false);
			}
			
			for (int i = 0; i < balls.size(); i++) {
				if (balls.get(i).playerBallCollision(balls.get(i), player)) {
					check = false;
					setWinCondition(false);
				}
				if (balls.get(i).ballArrowCollision(balls.get(i), player)) {
					addBall(i);
					player.setActive(false);
				}
			}
			
			displayBackground();
			bar.displayBar();
			bar.drawTimeBar();
			
			player.shootArrow(player);
			
			long playerEndTime = System.currentTimeMillis();
			
			player.drawPlayer(playerStartTime, playerEndTime);
			
			playerStartTime = System.currentTimeMillis();
			
			
			if (!check) {
				player.setPlayerX(getScaleX() / 2);
				player.setPlayerY();
				player.setActive(false);
				break;
			}
			
			StdDraw.show();
			
			long ballEndTime = System.currentTimeMillis();
			
			for (int i = 0; i < balls.size(); i++) {
				balls.get(i).drawBall(ballStartTime, ballEndTime);
			}
			
			ballStartTime = System.currentTimeMillis();
			
			StdDraw.show();
			StdDraw.pause(15);
			StdDraw.clear();
		}
	}
}
