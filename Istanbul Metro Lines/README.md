# 🚇 Istanbul Metro Lines Pathfinder

A Java application that finds optimal routes between Istanbul Metro stations and visualizes the journey with animated graphics. The program uses graph algorithms to calculate the shortest path and displays it on an interactive metro map.

## 📋 Project Overview

This project implements a pathfinding system for Istanbul's metro network, featuring route calculation between any two stations and real-time visual animation of the journey on an accurate metro map.

**Author**: Mehmet Ali Özdemir  
**Date**: March 24, 2023  
**Course**: CMPE160 - Introduction to Object Oriented Programming

## 🎯 Features

- **Route Calculation**: Find optimal paths between any two metro stations
- **Visual Animation**: Real-time route highlighting on metro map
- **Multi-Line Support**: Handles transfers between different metro lines
- **Breakpoint Navigation**: Intelligent handling of transfer stations
- **Interactive Graphics**: High-quality metro map visualization
- **Station Validation**: Input verification for valid station names

## 🏗️ Project Structure

### Core Components

**`IstanbulMetroLines.java`** - Complete application implementation including:

- Path finding algorithms
- Metro data processing
- Graphics rendering and animation
- User input handling

### Data Files

- **`coordinates.txt`** - Metro station coordinates and line information
- **`background.jpg`** - High-resolution Istanbul metro map background

### Assets

- Metro map background image (1024×482 resolution)
- Station coordinate mappings
- Line color specifications
- Breakpoint definitions

## 🎮 How to Use

### Input Format

The program expects two station names as input:

```
[Starting Station] [Destination Station]
```

### Example Usage

```bash
java -cp bin IstanbulMetroLines
Taksim Kadikoy
```

### Output

1. **Route Display**: List of stations in travel order
2. **Visual Animation**: Animated route on metro map
3. **Error Handling**: Invalid station warnings

## 🚀 Running the Application

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- StdDraw graphics library

### Compilation and Execution

```bash
# Navigate to the project directory
cd "Istanbul Metro Lines"

# Compile the Java file
javac -d bin src/*.java

# Run the application
java -cp bin IstanbulMetroLines
```

### Input Examples

```bash
# Valid station pairs
Taksim Sisli
Kadikoy Besiktas
Levent Uskudar
```

## 🗺️ Metro System Coverage

### Supported Lines

The application includes all major Istanbul metro lines:

- **M1** (Yenikapı - Havalimanı/Kirazlı)
- **M2** (Veliefendi - Hacıosman)
- **M3** (Olimpiyat - Başakşehir)
- **M4** (Kadıköy - Tavşantepe)
- **M5** (Üsküdar - Çekmeköy)
- **M6** (Levent - Boğaziçi Üniversitesi)
- **M7** (Kabataş - Mecidiyeköy)
- **And additional lines...**

### Transfer Stations

The system intelligently handles transfers at major interchange stations:

- **Veliefendi** (M1-M2 transfer)
- **Şişli-Mecidiyeköy** (M2-M7 transfer)
- **And other breakpoints...**

## 🧩 Technical Implementation

### Path Finding Algorithm

```java
public static String pathFinder(String currentStation, String end, String path,
                               String before, String[][] allLines,
                               String[][] breakpoints, String[] lineNames)
```

**Features:**

- Recursive depth-first search
- Breakpoint-aware navigation
- Cycle prevention
- Optimal route selection

### Graphics System

```java
public static void CanvasAnimator(String[][] metroLines, String[][] allLines,
                                 String[][] lineColors, String[] pathList)
```

**Capabilities:**

- Real-time station highlighting
- Progressive route animation
- Multi-colored line rendering
- High-resolution map display

### Key Algorithms

#### Station Lookup

- Efficient station name validation
- Multi-line station handling
- Case-sensitive matching

#### Route Optimization

- Shortest path calculation
- Transfer minimization
- Breakpoint utilization

#### Animation System

- Progressive highlighting
- Timed station updates
- Smooth visual transitions

## 📊 Data Structure

### Metro Lines Format

```
Line_Name R,G,B_Values
Station1 x1,y1 Station2 x2,y2 Station3 x3,y3 ...
```

### Breakpoint Format

```
Station_Name Line1 Line2 Line3 ...
```

### Coordinate System

- **Canvas Size**: 1024×482 pixels
- **Coordinate Range**: (0,0) to (1024,482)
- **Station Precision**: Pixel-perfect positioning

## 🎨 Visual Features

### Animation Details

- **Frame Rate**: ~3 FPS (300ms per frame)
- **Highlight Color**: Princeton Orange
- **Station Markers**: White circles with colored outlines
- **Line Thickness**: 0.012 pen radius

### Map Elements

- **Background**: High-resolution Istanbul metro map
- **Station Names**: Selected major stations labeled
- **Line Colors**: Authentic Istanbul Metro color scheme
- **Route Progress**: Orange highlighting for active route

## 📈 Learning Outcomes

This project demonstrates:

- **Graph Algorithms**: Pathfinding and traversal
- **File I/O**: Complex data structure parsing
- **Graphics Programming**: 2D animation and visualization
- **Recursive Programming**: Depth-first search implementation
- **Data Structures**: Multi-dimensional arrays and string processing
- **Error Handling**: Input validation and edge cases

## 🐛 Error Handling

### Invalid Input Cases

- **Non-existent Stations**: "The station names provided are not present in this map."
- **Disconnected Routes**: "These two stations are not connected"
- **Malformed Input**: Graceful handling of invalid formats

### Validation Features

- Station name verification
- Route connectivity checking
- Input format validation

## 📄 Output Examples

### Successful Route

```
Taksim
Sisli
Mecidiyekoy
[Visual animation follows]
```

### Error Cases

```
The station names provided are not present in this map.
```

```
These two stations are not connected
```

## 🔧 Customization Options

### Adding New Stations

1. Update `coordinates.txt` with new station data
2. Add coordinates and line information
3. Update breakpoint definitions if needed

### Modifying Animation

- Adjust `StdDraw.pause(300)` for animation speed
- Modify pen radius for different line thickness
- Change colors in the color array definitions

---

_This project was developed as part of the CMPE160 course curriculum at Boğaziçi University, focusing on graph algorithms and interactive graphics programming._
