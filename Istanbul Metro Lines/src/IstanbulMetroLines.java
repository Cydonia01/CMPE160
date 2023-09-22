/**
 * This program implements an application that finds the path between two 
 * metro stations and animates it using Standard Draw library.
 * 
 * @author Mehmet Ali Özdemir
 * @since Date: 24.03.2023
 */

import java.util.Scanner;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;

public class IstanbulMetroLines {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner input = new Scanner(System.in);
		String begin = input.next();
		String end = input.next();
		input.close();
		
		String[][] metroLines = new String[10][],
		breakpoints = new String[7][],
		allLines = new String[10][],
		lineColors = new String[10][];
		String[] lineNames = new String[10];
		String path = "";
		
		metroLinesReader(metroLines, allLines, lineColors, breakpoints, lineNames);
		path = pathFinder(begin, end, path, begin, allLines, breakpoints, lineNames).strip();
		
		if (!nameChecker(begin, end, allLines)) {
			System.out.println("The station names provided are not present in this map.");
			System.exit(0);
		}
		
		if (path == "") {
			System.out.println("These two stations are not connected");
			System.exit(0);
		}
		
		String[] pathList = path.split(" "); // Converts string that contains stations to an array.
		
		for (String e: pathList) { // prints path stations
			System.out.println(e);
		}
		
		Canvas(metroLines, allLines, lineColors, pathList);
	}
	
	
	/**
	 * This method reads the file and gets the necessary information.
	 * 
	 * @param metroLines Contains all metro stations with their coordinates
	 * @param allLines Contains only metro stations' names
	 * @param lineColors Contains RGB values of the line's colors
	 * @param breakpoints Contains breakpoints
	 * @param lineNames Contains the name of lines
	 * @throws FileNotFoundException
	 * 
	 */
	
	public static void metroLinesReader(String[][] metroLines, String[][] allLines, String[][] lineColors, String[][] breakpoints, String[] lineNames) throws FileNotFoundException {
		String fileName = "coordinates.txt";
		File file = new File(fileName); // creating a file object
		Scanner reader = new Scanner(file); 
		
		for (int i = 0; i < 10; i++) {
			String[] lineName = reader.nextLine().split(" ");
			String[] stations = reader.nextLine().split(" ");
			lineNames[i] = lineName[0];
			lineColors[i] = lineName[1].split(",");
			metroLines[i] = stations;
		}
		
		for (int i = 0; i < 7; i++) {
			String[] line = reader.nextLine().split(" ");
			breakpoints[i] = line;
		}
		
		reader.close();
		
		// filling allLines using metroLines
		for (int i = 0; i < 10; i++) {
			allLines[i] = new String[metroLines[i].length/2];
			
			for (int j = 0; j < metroLines[i].length/2; j++) {
				
				if (metroLines[i][2 * j].startsWith("*")) {
					String StarredStationName = metroLines[i][2 * j].substring(1, metroLines[i][2 * j].length());
					allLines[i][j] = StarredStationName;
				} else {
					String stationName = metroLines[i][2 * j];
					allLines[i][j] = stationName;
				}
			}
		}
	}
	
	
	/**
	 * This method is crucial part of the application. Firstly, it calls pathChecker 
	 * stations to find that if current stations is a breakpoint or not. Then, it recursively 
	 * calls itself to find the right path among the all possible ways. In the end, if path string is
	 * empty, it does nothing. If it's not empty, it means it found the correct route and returns it.
	 * 
	 * @param currentStation Current station
	 * @param end Target station
	 * @param path Contains the lines that are passed
	 * @param before The previous station
	 * @param allLines Contains only metro stations' names
	 * @param breakpoints Contains breakpoints
	 * @param lineNames Contains the name of lines
	 * @return Path between two stations
	 */
	
	public static String pathFinder(String currentStation, String end, String path, String before, String[][] allLines, String[][] breakpoints, String[] lineNames) {
		String[] pathArray = pathChecker(currentStation, allLines, breakpoints, lineNames);
		
		if (currentStation.equals(end)) { // checks whether it reached target or not
			return path + " " + currentStation;
		}
		
		for (int i = 0; i < pathArray.length; i++) {
			if (pathArray[i].equals(before)) {
				continue;
			}
			
			String temp = pathFinder(pathArray[i], end, path + " " + currentStation, currentStation, allLines, breakpoints, lineNames);
			
			if (temp != "") {
				return temp;	 
			}
		}
		return "";
	}
	
	
	/**
	 * This method checks the current station whether it is a breakpoint or not. If it's a breakpoint, using a loop,
	 * it sends current station and accessed metro lines' names' one by one to the accessedPaths method.
	 * If it's not a breakpoint it sends current station and the name of the line that contains the current station.
	 * 
	 * @param currentStation Current station
	 * @param allLines Contains only metro stations' names
	 * @param breakpoints Contains breakpoints
	 * @param lineNames Contains the name of lines
	 * @return AccessedPaths
	 */
	
	public static String[] pathChecker(String currentStation, String[][] allLines, String[][] breakpoints, String[] lineNames) {
		String path = "";
		
		// searching with a loops to find the breakpoint
		for (int i = 0; i < breakpoints.length; i++) {
			if (breakpoints[i][0].equals(currentStation)) {
				for (int j = 1; j < breakpoints[i].length; j++) {
					for (int k = 0; k < lineNames.length; k++) {
						if (lineNames[k].equals(breakpoints[i][j])) {
							path += accessedPaths(currentStation, allLines[k]);
						}
					}
				}
				return path.split(" ");
			}
		}
		return accessedPaths(currentStation, searchLine(currentStation, allLines)).split(" ");
	}
	
	
	/**
	 * This method finds the accessible stations from current station.
	 * It stores them in a string, then by using split method over the
	 * string, it returns an array.
	 * 
	 * @param currentStation Current station
	 * @param currentLine Current metro line
	 * @return Accessible paths' array.
	 */
	
	public static String accessedPaths(String currentStation, String[] currentLine) {
		String path = "";
		
		for (int i = 0; i < currentLine.length; i++) {
			if (currentLine[i].equals(currentStation)) {
				if (i > 0) {
					path += currentLine[i - 1] + " ";
				}
				if (i < currentLine.length - 1) {
					path += currentLine[i + 1] + " ";
				}
			return path;
			}
		}
		return path;
	}
	
	
	/**
	 * This method searchs for the line that contains the current station and returns that line array.
	 * 
	 * @param currentStation Current station
	 * @param allLines Contains only metro stations' names
	 * @return Line array
	 */
	
	public static String[] searchLine(String currentStation, String[][] allLines) {
		for (int i = 0; i < allLines.length; i++) {
			for (int j = 0; j < allLines[i].length; j++) {
				if (allLines[i][j].equals(currentStation)) {
					return allLines[i];
				}
			}
		}
		return allLines[0];
	}
	
	
	/**
	 * This method checks whether input values are valid or not. 
	 * 
	 * @param begin First station
	 * @param end Target station
	 * @param allLines Contains only metro stations' names
	 * @return A boolean value
	 */
	
	public static boolean nameChecker(String begin, String end, String[][] allLines) {
		boolean check1 = false;
		boolean check2 = false;
		
		//searching with loops
		for (int i = 0; i < allLines.length; i++) {
			for (int j = 0; j < allLines[i].length; j++) {
				if (begin.equals(allLines[i][j])) 
					check1 = true;
				if (end.equals(allLines[i][j])) 
					check2 = true;
			}
		}
		
		if (check1 && check2) 
			return true;
		else 
			return false;
	}
	
	
	/**
	 * This method sets up the canvas and calls CanvasAnimator method.
	 * 
	 * @param metroLines Contains all metro stations with their coordinates
	 * @param allLines Contains only metro stations' names
	 * @param lineColors Contains RGB values of the line's colors
	 * @param pathList Contains the stations between first station and target station.
	 */
	
	public static void Canvas(String[][] metroLines, String[][] allLines, String[][] lineColors, String[] pathList) {
		//set up the drawing area
		StdDraw.setCanvasSize(1024, 482);
		StdDraw.setXscale(0, 1024);
		StdDraw.setYscale(0, 482);
		StdDraw.setFont(new Font("Helvetica", Font.BOLD, 8));
		StdDraw.enableDoubleBuffering();
		
		CanvasAnimator(metroLines, allLines, lineColors, pathList);
	}
	
	
	/**
	 * This method firstly draws the background picture and gets the RGB values.
	 * Then, it calls other drawing methods.
	 * 
	 * @param metroLines Contains all metro stations with their coordinates
	 * @param allLines Contains only metro stations' names
	 * @param lineColors Contains RGB values of the line's colors
	 * @param pathList Contains the stations between first station and target station
	 */
	
	public static void CanvasAnimator(String[][] metroLines, String[][] allLines, String[][] lineColors, String[] pathList) {
		for (int i = 0; i < pathList.length; i++) {
			StdDraw.picture(512, 241, "background.jpg");
			
			for (int j = 0; j < 10; j++) {
				StdDraw.setPenRadius(0.012);
				//taking RGB values from lineColors
				int colorR = Integer.parseInt(lineColors[j][0]);
				int colorG = Integer.parseInt(lineColors[j][1]);
				int colorB = Integer.parseInt(lineColors[j][2]);
				
				StdDraw.setPenColor(colorR, colorG, colorB);
				
				lineDrawer(metroLines, j);
				stationDrawer(metroLines, allLines, j);
				pointDrawer(metroLines, pathList, i);
			}
			StdDraw.show();
			StdDraw.pause(300);
			StdDraw.clear();
		}
	}
	
	
	/**
	 * This method draws the metro lines.
	 * 
	 * @param metroLines Contains all metro stations with their coordinates
	 * @param i Index number comes from CanvasAnimator method
	 */
	
	public static void lineDrawer(String[][] metroLines, int i) {
		for (int j = 0; j < metroLines[i].length/2 - 1; j++) {
			String[] kords1 = metroLines[i][2 * j + 1].split(",");
			String[] kords2 = metroLines[i][2 * j + 3].split(",");
			// specifying the coordinates
			double xKord1 = Double.parseDouble(kords1[0]);
			double yKord1 = Double.parseDouble(kords1[1]);
			double xKord2 = Double.parseDouble(kords2[0]);
			double yKord2 = Double.parseDouble(kords2[1]);
			
			StdDraw.line(xKord1, yKord1, xKord2, yKord2);
		}
	}
	
	
	/**
	 * This method draws the stations and some of the stations' names.
	 * 
	 * @param metroLines Contains all metro stations with their coordinates
	 * @param allLines Contains only metro stations' names
	 * @param i Index number comes from CanvasAnimator method
	 */
	
	public static void stationDrawer(String[][] metroLines, String[][] allLines, int i) {
		StdDraw.setPenRadius(0.01);
		
		for (int j = 0; j < metroLines[i].length/2; j++) {
			String[] kords1 = metroLines[i][2 * j + 1].split(",");
			
			double xKord1 = Double.parseDouble(kords1[0]);
			double yKord1 = Double.parseDouble(kords1[1]);
			
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.point(xKord1, yKord1);
			
			if (metroLines[i][2 * j].startsWith("*")) {
				StdDraw.setPenColor(StdDraw.BLACK);
				String StarredStationName = metroLines[i][2 * j].substring(1, metroLines[i][2 * j].length());
				StdDraw.text(xKord1, yKord1 + 5, StarredStationName);
			}
		}
	}
	
	
	/**
	 * This method firstly finds the coordinates of passed metro stations and draw point on those stations
	 * Basically, it does animation.
	 * 
	 * @param metroLines Contains all metro stations with their coordinates
	 * @param pathList Contains the stations between first station and target station
	 * @param h Index number comes from CanvasAnimator method
	 */
	
	public static void pointDrawer(String[][] metroLines, String[] pathList, int h) {
		StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
		
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < metroLines.length; j++) {
				for (int k = 0; k < metroLines[j].length; k++) {
					
					if (metroLines[j][k].equals(pathList[h]) || metroLines[j][k].equals("*" + pathList[h])) {
						StdDraw.setPenRadius(0.02);
						
						double xKord = Double.parseDouble(metroLines[j][k + 1].split(",")[0]);
						double yKord = Double.parseDouble(metroLines[j][k + 1].split(",")[1]);
						
						StdDraw.point(xKord, yKord);
					}
					
					if (metroLines[j][k].equals(pathList[i]) || metroLines[j][k].equals(("*" + pathList[i]))) {
						StdDraw.setPenRadius(0.01);
						
						double xKord1 = Double.parseDouble((metroLines[j][k + 1]).split(",")[0]);
						double yKord1 = Double.parseDouble((metroLines[j][k + 1]).split(",")[1]);
						
						StdDraw.point(xKord1, yKord1);
					}
				}
			}
		}
	}
}	