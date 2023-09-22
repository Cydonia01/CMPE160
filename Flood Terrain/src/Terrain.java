/**
 * This project implements a code that modifies a terrain then floods the terrain recursively.
 * 
 * @author Mehmet Ali Özdemir
 * @since Date: 10.05.2023
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Terrain {
	private int colNum;
	private int rowNum;
	
	private String[][] terrain;
	private Block[][] blocks;
	private static ArrayList<ArrayList<Block>> lakes = new ArrayList<>();
	
	private static double score = 0;
	
	Terrain() {
		readFile();
		this.blocks = new Block[rowNum][colNum];
		createBlocks();
	}
	
	public int getRowNum() {
		return rowNum;
	}

	public int getColNum() {
		return colNum;
	}
	
	public Block[][] getBlocks() {
		return blocks;
	}
	
	public ArrayList<ArrayList<Block>> getLakes() {
		return lakes;
	}
	
	
	/**
	 * This method add lake to lakes ArrayList
	 * 
	 * @param lake lake ArrayList
	 */
	
	public void printScore() {
		System.out.print(String.format("Final score: %.2f", this.score));
	}
	
	
	/**
	 * This method creates the block objects.
	 */
	private void createBlocks() {
		for (int i = 0; i < terrain.length; i++) {
			for (int j = 0; j < terrain[i].length; j++) {
				blocks[i][j] = new Block(i, j, Integer.parseInt(this.terrain[i][j]));
			}
		}
	}
	
	
	/**
	 * This method creates temporary terrain to compare this terrain with the previous terrain in Main class.
	 * 
	 * @return floodArray
	 */
	public boolean[][] createTempTerrain() {
		boolean[][] tempArray = new boolean[rowNum][colNum];
		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				tempArray[i][j] = blocks[i][j].isFlooded();
			}
		}
		return tempArray;
	}
	
	
	/**
	 * This method reads the input file.
	 */
	private void readFile() {
		try {
			FileInputStream fis = new FileInputStream("input.txt");
			Scanner reader = new Scanner(fis);
			
			this.colNum = reader.nextInt();
			this.rowNum = reader.nextInt();
			
			reader.nextLine();
			
			this.terrain = new String[rowNum][colNum];
			
			for (int i = 0; i < rowNum; i++) {
				terrain[i] = reader.nextLine().split(" ");
			}
			
			reader.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This method takes coordinates from main class and converts them into indexes that will be used in other methods.
	 * 
	 * @param coordinates input coordinates
	 * @return cordList
	 */
	private int[] findCoordinates(String coordinates) {
		String[] cordArray = coordinates.split("");
		int[] cordList = new int[2];
		
		String col = "";
		String row = "";
		
		for (String e: cordArray) {
			try {
				row += Integer.parseInt(e);
			}
			catch (Exception j) {
				col += e;
			}
		}
		
		int rowNum = Integer.parseInt(row);
		int colNum = 0;
		
		if (col.length() == 1) {
			colNum = (((int) col.charAt(0)) % 97);
		}
		
		if (col.length() == 2) {
			colNum = ((((int) col.charAt(0) + 1) % 97) * 26) + (((int) col.charAt(1)) % 97);
		}
		
		cordList[0] = rowNum;
		cordList[1] = colNum;
		
		return cordList;
	}
	
	
	/**
	 * This method modifies the terrain taking commands from the main class.
	 * 
	 * @param command input coming from main class.
	 */
	public void modifyTerrain(String command) {
		int[] cordList = findCoordinates(command);
		
		int row = cordList[0];
		int col = cordList[1];
		
		int value = Integer.parseInt(terrain[row][col]);
		
		this.terrain[row][col] = Integer.toString(value + 1);
		this.blocks[row][col].incrementHeight();
	}
	
	
	/**
	 * This method does the main recursive operation which is flooding. Firstly, it checks neighbours to check if there is
	 * a block that has lower height, then does recursive operation. If there is no lower neighbour block, it floods current block.
	 * 
	 * @param x X coordinate of matrix 
	 * @param y Y coordinate of matrix
	 * @param lastBlock The previous block
	 */
	public void floodTerrain(int x, int y, Block lastBlock) {
		Block block = blocks[x][y];
		
		if (x == 0 || y == 0 || x == terrain.length - 1 || y == terrain[0].length - 1 || blocks[x][y].isFlooded()) {
			return;
		}
	
		boolean lowBlock = false;
		
		for (Block neighbor: findNeighbors(block)) {
			if (block.getHeight() >= neighbor.getHeight() && !neighbor.isFlooded() && neighbor != lastBlock) {
				lowBlock = true;
				floodTerrain(neighbor.getX(), neighbor.getY(), block);
				break;
			}
		}
		
		if(!lowBlock) {
			block.setFlooded(true);
		}
	}
	
	
	/**
	 * This method finds the neighbors of a block and adds them into an array list. Then returns that array list.
	 * 
	 * @param block current block
	 * @return neighbors array list
	 */
	private ArrayList<Block> findNeighbors(Block block){
		ArrayList<Block> neighbors = new ArrayList<>();
		
		int[][] neighList = {{-1, 0, 1}, {-1, 0, 1}};
		for (Integer i: neighList[0]) {
			for (Integer j: neighList[1]) {
				
				if (i == 0 && j == 0) {
					continue;
				}
				
				int x = block.getX() + i;
				int y = block.getY() + j;
				
				if (x < 0 || x > rowNum - 1 || y < 0 || y > colNum - 1) {
					continue;
				}
				
				neighbors.add(blocks[block.getX() + i][block.getY() + j]);
			}
		}
		return neighbors;
	}
	
	
	/**
	 * This method finds the label letter of a block according to its label number.
	 * 
	 * @param labelNum label number
	 * @return label letter(s)
	 */
	private String findLabel(int labelNum) {
		String[] letterList = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
		String ch1;
		String ch2;
		String label;
		
		if (labelNum > 26) {
			ch1 = letterList[((labelNum - 1) / 26) - 1];
			ch2 = letterList[(labelNum - 1) % 26];
			label = ch1 + ch2;
			return label;
		}
		else {
			ch1 = letterList[labelNum - 1];
			return ch1;
		}
	}
	
	
	/**
	 * This method loops entire terrain to check whether all of the lakes labeled or not.
	 * 
	 * @return isAllLabeled
	 */
	public boolean allLakesLabeled() {
		boolean isAllLabeled = true;
		
		for (Block[] blockRow: blocks) {
			for (Block block: blockRow) {
				if (block.isFlooded() && !block.isLabeled()) {
					isAllLabeled = false;
					break;
				}
			}
			if (!isAllLabeled) {
				break;
			}
		}
		return isAllLabeled;
	}
	
	
	/**
	 * This method sets label to flooded lakes via recursive operation. Besides that it adds separate lakes into lakes
	 * array list.
	 * 
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param labelNum label number
	 * @param lastBlock previous block
	 * @param lake Lake array list
	 */
	public void setLabel(int x, int y, int labelNum, Block lastBlock, ArrayList<Block> lake) {
		
		if (x == 0 || y == 0 || x == terrain.length || y == terrain[0].length - 1) {
			return;
		}
		
		Block block = blocks[x][y];
		
		lake.add(block);
		
		for (Block neighbor: findNeighbors(block)) {
			if (neighbor.isFlooded() && neighbor != lastBlock) {
				setLabel(neighbor.getX(), neighbor.getY(), labelNum, block, lake);
			}
		}
		
		if (!lakes.contains(lake)) {
			lakes.add(lake);
		}
		
		block.setLabelNum(labelNum);
		block.setLabeled(true);
	}
	
	
	/**
	 * This method finds the higher neighbour block that has the minimum height.
	 */
	public void findMinHigherNeighbor() {
		for (ArrayList<Block> lake: lakes) {
			int minNeighbor = Integer.MAX_VALUE;
			
			for (Block block: lake) {
				for (Block neighbor: findNeighbors(block)) {
					if (!neighbor.isFlooded() && neighbor.getHeight() < minNeighbor) {
						minNeighbor = neighbor.getHeight();
					}
				}
			}
			computeScore(minNeighbor, lake);
		}
	}
	
	
	/**
	 * This method computes the score of a lake.
	 * 
	 * @param minNeighbor The higher neighbor that has the minimum height.
	 * @param lake Lake array list.
	 */
	private void computeScore(int minNeighbor, ArrayList<Block> lake) {
		double volume = 0;
		
		for (Block block: lake) {
			block.setNeighborMinHeight(minNeighbor);
			volume += minNeighbor - block.getHeight();
		}
		
		this.score += Math.sqrt(volume);
	}
	
	
	/**
	 * This method draws the terrain before flood.
	 */
	public void drawTerrain() {
		for (int i = 0; i < rowNum; i++) {
			System.out.print(String.format("%3d", i));
			
			for (String e: terrain[i]) {
				System.out.print(String.format("%3s", e));
			}
			
			System.out.println(" ");
		}
		drawBottomLetters();
	}
	
	
	/**
	 * This method draws the bottom letters.
	 */
	private void drawBottomLetters() {
		System.out.print("   ");
		
		int j = 0;
		
		for (int i = 0; i < colNum; i++) {
			if (i < 26) {
				System.out.print(String.format("%3s", (char) (i + 97)));
			}
			else {
				char ch1 = (char) (97 + j);
				char ch2 = (char) (97 + (i % 26));
				
				System.out.print(String.format("%2s%s", ch1, ch2));
				if ((i + 1) % 26 == 0) {
					j++;
				}
			}
		}
		System.out.println(" ");
	}
	
	
	/**
	 * This method draws the terrain after flood.
	 */
	public void drawFinal() {
		for (int i = 0; i < blocks.length; i++) {
			System.out.print(String.format("%3d", i));
			
			for (int j = 0; j < blocks[0].length; j++) {
				if (blocks[i][j].isFlooded()) {
					if (blocks[i][j].getNeighborMinHeight() - blocks[i][j].getHeight() == 0) {
						System.out.print(String.format("%3s", terrain[i][j]));
					}
					else {
						System.out.print(String.format("%3s", findLabel(blocks[i][j].getLabelNum())));
					}
				}
				else {
					System.out.print(String.format("%3s", terrain[i][j]));
				}
			}
			System.out.println(" ");
		}
		drawBottomLetters();
	}
}
