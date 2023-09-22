/**
 * This project implements a code that modifies a terrain then floods the terrain recursively.
 * 
 * @author Mehmet Ali Özdemir
 * @since Date: 10.05.2023
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Terrain ter = new Terrain(); // creating the terrain object
		ter.drawTerrain();
		
		takeInput(ter);
		floodLoop(ter, ter.createTempTerrain());
		labelLoop(ter);
		
		ter.findMinHigherNeighbor();
		ter.drawFinal();
		ter.printScore();
	}
	
	
	/**
	 * this method takes input from the user.
	 * @param ter terrain object
	 */
	public static void takeInput(Terrain ter) {
		Scanner input = new Scanner(System.in);
		
		for (int i = 0; i < 10; i++) {
			System.out.println(String.format("Add stone %d / 10 to coordinate:", i + 1));
			
			String command = input.next();
			
			if (!checkInput(command, ter))  {
				System.out.println("Not a valid step!");
				i--;
				continue;
			}
				
			ter.modifyTerrain(command);
			ter.drawTerrain();
			
			System.out.println("---------------");
		}
		input.close();
	}
	
	
	/**
	 * This method invokes floodTerrain method within a loop.
	 * 
	 * @param ter terrain object
	 * @param lastTerrain previous terrain nested list
	 */
	public static void floodLoop(Terrain ter, boolean[][] lastTerrain) {
		for (int i = 1; i < ter.getRowNum(); i++) {
			for (int j = 1; j < ter.getColNum(); j++) {
				ter.floodTerrain(i, j, ter.getBlocks()[i][j]);
			}
		}
		
		if (!Arrays.deepEquals(ter.createTempTerrain(), lastTerrain)) {
			floodLoop(ter, ter.createTempTerrain());
		}
	}
	
	
	/**
	 * This method invokes setLabel method within a loop.
	 * 
	 * @param ter terrain object
	 */
	public static void labelLoop(Terrain ter) {
		int labelNum = 1;
	
		while (!ter.allLakesLabeled()) {
			ArrayList<Block> lake = new ArrayList<>();
			boolean found = false;
			
			for (int i = 1; i < ter.getRowNum(); i++) {
				for (int j = 1; j < ter.getColNum(); j++) {
					Block block = ter.getBlocks()[i][j];
					
					if (block.isFlooded() && !block.isLabeled()) {
						ter.setLabel(i, j, labelNum, block, lake);
						found = true;
						break;
					}
				}
				if (found) {
					break;
				}
			}
			labelNum++;
		}
	}
	
	
	/**
	 * This method check the input whether it is valid or not.
	 * 
	 * @param command Input
	 * @param ter Terrain object
	 * @return boolean
	 */
	public static boolean checkInput(String command, Terrain ter) {
		if (command.length() == 1) {
			return false;
		}
		int colNum;
		String[] cordArray = command.split("");
		
		String letter = "";
		String number = "";
		
		boolean check = false;
		
		for (String e: cordArray) {
			try {
				number += Integer.parseInt(e);
			}
			catch (Exception j) {
				letter += e;
			}
		}
		
		if (letter.length() == 2) {
			colNum = ((((int) letter.charAt(0)) % 97) * 26) + (((int) letter.charAt(1)) % 97);
			if (colNum >= ter.getColNum()) {
				return false;
			}
		}
		
		if (letter.length() > 2 || letter.length() < 1) {
			return false;
		}
		
		if (number.isEmpty()) {
			return false;
		}
		
		try {
			if (Integer.parseInt(number) >= ter.getRowNum()) {
				return false;
			}
		}
		catch (Exception e) {}
			
		for (int i = 0; i < ter.getRowNum(); i++) {
			if (command.startsWith(Integer.toString(i))) {
				check = false;
			}
			else if (command.endsWith(Integer.toString(i))) {
				check = true;
			}
		}
		
		if (check) {
			return true;
		}
		return false;
	}
	
}
