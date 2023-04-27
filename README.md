# Tetris-Java

## Project Description

### What is the application

The Tetris Game is a classic puzzle game that challenges players to manipulate falling tetrominoes to form complete lines. The game is built using Java and the Swing framework, with a focus on object-oriented design and game mechanics.

### What technologies have been used and why
    
The project is written in Java with the help of Java Swing GUI toolkit which provides a set of lightweght components for building desktop applications. I chose Swing because it is ideal for building small games due to all the custom components, handle events and graphics rendering

### Challanges that I have faced and future features

The main challange was to visualise all the different blocks and connect every different class to work when it is needed. In the future there needs to be made a better design for all blocks and make the blocks spawn above the visible area and to start appearing while falling down slowly.

## How to install and Run the Project

The project is wrtten in Appache NetBeans17 and it does not require any additional software to be ran or installed. You can either open the project via Apache Netbeans or you can locate the Tetris.jar in: 

```bash
 cd /(path to project)/Tetris-Java/dist
```

And then you have to run the jar file with the following command: 

```bash
java -jar Tetris.jar
```
 
## How to use the project 

The project has an information tab when opened. That can be used to instrcuct the user how to use the project.

## Implemented classes

### The game initializes a lot of classes: 
AudioPlayer.java -> is responsible for playing and stopping songs

GameArea.java -> is responsible for the whole game logic(how the background, blocks work)

GameForm.java -> is responsible for displaying the game field

GameThread.java -> is responsible for moving the block downwards and updateing the score and level

Instructions.java -> is responsible for showing the player how to play the games

LeaderboardForm.java -> is responsible for showing the scores of the players
 
StartupForm.java -> is responsible for showing all menu options when the player opens the game

Tetris.java -> is responsible for setting the state of the game

TetrisBlock.java -> is responsible for getting, moving/rotating the block


tetrisblocks/NBlock.java -> are responsible for giving the shape and color of the block

## Java Documentation 

Java Documentation is located in:

```bash
Tetris-Java/dist/java-doc
```

## Credits
The project has been build by the help of the following sources:

"https://zetcode.com/javagames/tetris/"

"https://www.youtube.com/watch?v=QbBKsrvrIq8"

"https://www.youtube.com/watch?v=dgVh6S8X25k"
