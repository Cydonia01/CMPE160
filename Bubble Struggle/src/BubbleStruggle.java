/**
 * This program is implemented to run an imitation of Bubble Struggle game.
 * This project consists of 6 java classes:
 * Environment, Ball, Arrow, Player, Bar and this class.
 * 
 * @author Mehmet Ali Özdemir
 * @since Date: 16.04.2023
 */

import java.awt.event.KeyEvent;

public class BubbleStruggle {
	/**
	 * This main method creates the environment object and sets up the environment.
	 * Then, runs the game in a while loop.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Environment env = new Environment(); // creating the environment object 
		env.setUpCanvas();
		
		StdDraw.enableDoubleBuffering(); // allows faster animations
		
		while (env.getCheck()) {
			env.runGame();
			restartGame(env);
		}
	}
	
	/**
	 * this method restarts the game or closes the game according to keyboard input when the game is over.
	 * @param env Environment object
	 */
	public static void restartGame(Environment env) {
		while (!env.getCheck()) {
			env.drawEndGame(env.getWinCondition());
			
			if (StdDraw.isKeyPressed(KeyEvent.VK_Y)) {
				env.setCheck(true);
				Environment.setStartTime(System.currentTimeMillis());
				env.clearBalls();
			}
			if (StdDraw.isKeyPressed(KeyEvent.VK_N))
				System.exit(0);
			
			StdDraw.show();
			StdDraw.pause(15);
		}
	}
}
