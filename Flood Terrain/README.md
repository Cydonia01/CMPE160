# 🌊 Flood Terrain Simulation

A Java application that simulates terrain modification and flooding using recursive algorithms. Users can interactively place stones on a terrain, and the program simulates water flooding through the modified landscape.

## 📋 Project Overview

This project implements a terrain simulation where users can modify the landscape by adding stones, and then observe how water floods through the terrain using recursive flood-fill algorithms.

**Author**: Mehmet Ali Özdemir  
**Date**: May 10, 2023  
**Course**: CMPE160 - Introduction to Object Oriented Programming

## 🎯 Features

- **Interactive Terrain Modification**: Place stones at specific coordinates
- **Flood Simulation**: Recursive flooding algorithm
- **Lake Detection**: Automatic identification and labeling of water bodies
- **Pathfinding**: Find minimum height neighbors for water flow
- **Scoring System**: Calculate and display final terrain score
- **File I/O**: Read terrain data from input files

## 🏗️ Project Structure

### Core Classes

1. **`Main.java`** - Main controller and user interaction
2. **`Terrain.java`** - Terrain management and flood simulation
3. **`Block.java`** - Individual terrain block representation

### Input/Output Files

- `input.txt`, `input2.txt`, `input3.txt` - Terrain configuration files
- `output.txt`, `output2.txt`, `output3.txt` - Generated results
- `report.pdf` - Project documentation

## 🎮 How to Use

### Interactive Process

1. **Terrain Loading**: Program reads initial terrain from input file
2. **Stone Placement**: Add 10 stones to modify the terrain
3. **Coordinate Input**: Enter coordinates (e.g., "a1", "b5", "aa10")
4. **Flood Simulation**: Automatic flooding after stone placement
5. **Results Display**: View final terrain state and score

### Input Format

- **Coordinates**: Letter(s) + Number format
  - Single letter: `a1`, `b2`, `z26`
  - Double letter: `aa1`, `ab2`, `zz702`
- **Valid Range**: Depends on terrain dimensions loaded from file

## 🚀 Running the Application

### Prerequisites

- Java Development Kit (JDK) 8 or higher

### Compilation and Execution

```bash
# Navigate to the project directory
cd "Flood Terrain"

# Compile all Java files
javac -d bin src/*.java

# Run the application
java -cp bin Main
```

### Sample Execution

```
Add stone 1 / 10 to coordinate:
a5
[Terrain display updates]
---------------
Add stone 2 / 10 to coordinate:
b3
[Terrain display updates]
---------------
... (continue for 10 stones)
```

## 🧩 Technical Implementation

### Key Algorithms

#### Flood Algorithm

```java
// Recursive flooding implementation
public void floodTerrain(int row, int col, Block currentBlock)
```

- Checks neighboring blocks for flood conditions
- Recursively spreads water to adjacent lower areas
- Implements boundary checking and cycle prevention

#### Lake Labeling

```java
public void setLabel(int row, int col, int labelNum, Block block, ArrayList<Block> lake)
```

- Identifies connected water bodies
- Assigns unique labels to separate lakes
- Tracks lake composition for scoring

#### Pathfinding

```java
public void findMinHigherNeighbor()
```

- Finds optimal water flow paths
- Calculates minimum height neighbors
- Optimizes water distribution

### Data Structures

- **2D Block Array**: Represents terrain grid
- **ArrayList of Lakes**: Manages water body collections
- **Boolean Arrays**: Track flooding states and processing

## 📊 Terrain Coordinates System

### Coordinate Mapping

- **Single Letters**: a=0, b=1, ..., z=25
- **Double Letters**: aa=26, ab=27, ..., zz=701
- **Numbers**: Direct row mapping (0-based indexing)

### Example Coordinates

- `a1` → Column 0, Row 1
- `z5` → Column 25, Row 5
- `aa10` → Column 26, Row 10
- `ab15` → Column 27, Row 15

## 🎯 Scoring System

The application calculates scores based on:

- Lake formation efficiency
- Water distribution patterns
- Terrain modification effectiveness
- Final landscape stability

## 📈 Learning Outcomes

This project demonstrates:

- **Recursive Algorithms**: Flood-fill implementation
- **2D Array Manipulation**: Grid-based data structures
- **Graph Traversal**: Neighboring node exploration
- **File I/O Operations**: Reading terrain configurations
- **Interactive Programming**: User input validation
- **Algorithm Optimization**: Efficient pathfinding

## 🧪 Test Cases

### Included Test Files

1. **`input.txt`** - Basic terrain configuration
2. **`input2.txt`** - Complex landscape scenario
3. **`input3.txt`** - Advanced terrain challenge

Each test case includes corresponding output files showing expected results.

## 🐛 Input Validation

The program validates:

- Coordinate format correctness
- Boundary limit checking
- Duplicate placement prevention
- Invalid character handling

### Error Messages

- `"Not a valid step!"` - Invalid coordinate format
- Boundary checks prevent out-of-range placement

## 📄 Additional Features

- **Visual Terrain Display**: ASCII-based terrain representation
- **Progress Tracking**: Step-by-step modification display
- **Result Analysis**: Comprehensive final scoring
- **Multiple Test Scenarios**: Various terrain configurations

---

_This project was developed as part of the CMPE160 course curriculum at Boğaziçi University, focusing on recursive algorithms and 2D array manipulation._
