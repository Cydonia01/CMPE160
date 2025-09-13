# 🎮 Bubble Struggle Game

A Java implementation of the classic Bubble Struggle game using the StdDraw graphics library. Players control a character that shoots arrows to pop bouncing balls while avoiding collision.

## 📋 Project Overview

This project recreates the popular Bubble Struggle arcade game where players must eliminate all bouncing balls within a time limit while avoiding being hit by them.

**Author**: Mehmet Ali Özdemir  
**Date**: April 16, 2023  
**Course**: CMPE160 - Introduction to Object Oriented Programming

## 🎯 Game Features

- **Player Movement**: Arrow key controls for left/right movement
- **Shooting Mechanism**: Space bar to shoot arrows upward
- **Ball Physics**: Realistic bouncing ball behavior with gravity
- **Collision Detection**: Between player, arrows, balls, and boundaries
- **Time Management**: 40-second time limit per game
- **Win/Lose Conditions**: Clear all balls to win, get hit by ball to lose
- **Game Restart**: Y/N options to restart or quit after game over

## 🏗️ Project Structure

### Core Classes

1. **`BubbleStruggle.java`** - Main game controller and entry point
2. **`Environment.java`** - Game environment setup and main game loop
3. **`Player.java`** - Player character implementation and controls
4. **`Ball.java`** - Ball physics and behavior
5. **`Arrow.java`** - Arrow projectile mechanics
6. **`Bar.java`** - Ground bar implementation

### Assets

- `player_back.png` - Player character sprite
- `ball.png` - Ball texture
- `arrow.png` - Arrow projectile sprite
- `bar.png` - Ground bar texture
- `background.png` - Game background
- `game_screen.png` - Game interface elements

## 🎮 How to Play

1. **Movement**: Use ← → arrow keys to move the player left and right
2. **Shooting**: Press SPACEBAR to shoot arrows upward
3. **Objective**: Eliminate all balls by hitting them with arrows
4. **Avoid**: Don't let the balls touch your player character
5. **Time Limit**: Complete the level within 40 seconds

### Game Controls

- `←` / `→` : Move player left/right
- `SPACEBAR` : Shoot arrow
- `Y` : Restart game (after game over)
- `N` : Quit game (after game over)

## 🚀 Running the Game

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- StdDraw library

### Compilation and Execution

```bash
# Navigate to the project directory
cd "Bubble Struggle"

# Compile all Java files
javac -d bin src/*.java

# Run the game
java -cp bin BubbleStruggle
```

### Alternative (if already compiled)

```bash
# Run directly from bin directory
java -cp bin BubbleStruggle
```

## 🎥 Demo

A demo video (`demo.mp4`) is included showing gameplay footage and game mechanics.

## 📊 Game Constants

- **Canvas Size**: 800x500 pixels
- **Game Duration**: 40 seconds
- **Ball Period**: 15 seconds per ball cycle
- **Gravity**: 0.000003 units
- **Pause Duration**: 15ms between frames

## 🧩 Technical Implementation

### Key Features

- **Object-Oriented Design**: Separate classes for each game entity
- **Real-time Animation**: Using StdDraw.enableDoubleBuffering()
- **Physics Simulation**: Gravity and collision detection
- **Event Handling**: Keyboard input processing
- **Game State Management**: Win/lose condition checking

### Development Highlights

- Modular class structure for easy maintenance
- Efficient collision detection algorithms
- Smooth animation with proper frame timing
- Clean separation of game logic and rendering

## 📈 Learning Outcomes

This project demonstrates:

- Object-oriented programming principles
- Game loop implementation
- 2D graphics and animation
- Physics simulation in games
- Event-driven programming
- File I/O for game assets

## 📄 Additional Files

- `report.pdf` - Detailed project report and analysis
- Compiled `.class` files in `/bin/` directory

---

_This project was developed as part of the CMPE160 course curriculum at Boğaziçi University._
