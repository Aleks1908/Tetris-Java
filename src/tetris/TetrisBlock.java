/**

 * File: TetrisBlock.java

 * Author: Aleksandar Ivanov

 * Date: 20.04.2023

 */

package tetris;

import java.awt.Color;
import java.util.Random;

public class TetrisBlock {      //This class is responsible for getting all tetris blocks and their colors, randomizing them and spawning them

    private int[][] shape;
    private Color color;
    private int x,y;
    private int[][][] shapes;
    private int currentRotation;
    
    
    
    
    public TetrisBlock(int[][] shape, Color color){     //This is the constructor that gets the shapes and their colors
        this.shape = shape;
        this.color = color;
        
        initShapes();

    }
    
    private void initShapes(){                          //This function initializes all shapes and randomizes them
        shapes = new int[4][][];
        
        for (int i = 0; i < 4; i++){
            int r = shape[0].length;  //making r to be equal to columns
            int c = shape.length;       //making c to be equal to rows
            
            shapes[i] = new int[r][c];
            
            for(int y = 0; y < r; y++){
                for(int x = 0; x < c; x++){
                    shapes[i][y][x] = shape[c - x - 1][y];
                }
            }
            shape = shapes[i];
        }
    }
    
    public void spawn(int gridWidth){
        Random r = new Random();
        
        currentRotation = r.nextInt(4);
        shape = shapes[currentRotation];
        this.color = color;
        
        y = -1;
        x = r.nextInt(gridWidth - getWidth()); //calculating the possibilities for random number because we have figures with different shapes
    }
    
    //These functions are responsible for setting block coordinates, shape, color and moving the block
    public int[][] getShape(){return shape;}
    public Color getColor(){return color;}
    
    public int getHeight() {return shape.length;}
    public int getWidth() {return shape[0].length;}
    
    public int getX(){
        return x;
    }
    public void setX(int newX){
        x = newX;
    }
    public int getY(){
        return y;
    }
    public void setY(int newY){
        y = newY;
    }
    
    public void moveDown(){
        y++;
    }
    public void moveLeft(){
        x--;
    }
    public void moveRight(){
        x++;
    }
    
    public void rotate(){
         currentRotation++;
         if(currentRotation > 3) currentRotation = 0;
         shape = shapes[currentRotation];
    }
    
    public int getBottomEdge() {return y + getHeight();}
    
    public int getLeftEdge() {return x;}  //we get the first column of the block
    public int getRightEdge() {return x + getWidth();}
    public void rotateBack() {
        currentRotation--;
        if (currentRotation < 0) {
            currentRotation = 3;
        }
        shape = shapes[currentRotation];
    }

    
}
