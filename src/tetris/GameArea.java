/**

 * File: GameArea.java

 * Author: Aleksandar Ivanov

 * Date: 20.04.2023

 */

package tetris;
 
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import tetrisblocks.IBlock;
import tetrisblocks.JBlock;
import tetrisblocks.LBlock;
import tetrisblocks.OBlock;
import tetrisblocks.SBlock;
import tetrisblocks.TBlock;
import tetrisblocks.ZBlock;

public class GameArea extends JPanel {
    private int gridRows;
    private int gridColumns;
    private int gridCellSize;
    private TetrisBlock block;
    private TetrisBlock[] blocks;
    private Color[][] background;

    public GameArea(JPanel placeholder, int columns){       //This is the constructor of the class and it is used to set the columns and calculate the rows also it initializes all blocks
        this.setBounds(placeholder.getBounds());
        this.setBackground(placeholder.getBackground());
        this.setBorder(placeholder.getBorder());
        
        gridColumns = columns;
        gridCellSize = this.getBounds().width / gridColumns;
        gridRows = this.getBounds().height / gridCellSize;
        
        background = new Color[gridRows][gridColumns];
        
        blocks = new TetrisBlock[]{
            new IBlock(),
            new JBlock(),
            new LBlock(),
            new OBlock(),
            new SBlock(),
            new TBlock(),
            new ZBlock()
        };
    }
    
    public void initBackgroundArray(){              //This function is used to create the 
        background = new Color[gridRows][gridColumns];
    }
    
    //Functions that are used to make the visualisation of the background and tetris blocks
    private void drawGridSquare(Graphics g, Color color, int x, int y) {   
        g.setColor(color);
        g.fillRect(x, y, gridCellSize, gridCellSize);
        g.setColor(Color.black);
        g.drawRect(x, y, gridCellSize, gridCellSize);
    }
    
    private void drawBlock(Graphics g){             //Thks function gets the coordinates, shape and color of the block and calls drawGridSquare to draw the block
        
        Color color = block.getColor();
        
        for (int row = 0; row < block.getHeight(); row++){
            for(int col = 0; col < block.getWidth(); col++){
                if(block.getShape()[row][col]== 1){
                    
                    int x = (block.getX() + col) * gridCellSize;
                    int y = (block.getY() + row) * gridCellSize;
                    
                    drawGridSquare(g, color, x, y);

                }
            }
        }
    }

    private void drawBackground(Graphics g){   //This function checks the background for colored blocks and calls drawGridSquare to color it (we see difference after moveBlockToBackground has been executed)
        Color color;
        
        for (int r = 0; r < gridRows; r++){
            for (int c = 0; c < gridColumns; c++){
                color = background[r][c];
                
                if(color != null){
                    int x = c * gridCellSize;
                    int y = r * gridCellSize;
                    
                    drawGridSquare(g, color, x, y);
                }
            }
        }
    }
    
    public boolean isBlockOutOfBounds (){       //This function check if the block is out of bounds
        if(block.getY() < 0){
            block = null;
            return true;
        } 
        return false;
    }
    
    public void spawnBlock(){               //This function visualizes the Tetris block
        Random r = new Random();
        block = blocks[r.nextInt(blocks.length)];
        block.spawn(gridColumns);
    }
    
    private boolean checkBottom(){                   //checks if the block has reached the bottom if the block has reached it returns false
        if(block.getBottomEdge() == gridRows){
            return false;
        }
        
        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();
        
        for (int col = 0; col < w; col++){
            for(int row = h - 1; row >= 0; row--){          //making a loop that starts checking from the bottom if there is anything under the shape
                if(shape[row][col] != 0){                   //getting each square of the shape by x and y if the specific square is colored we check if on the same coords under it there is something in the background
                    int x = col + block.getX();
                    int y = row + block.getY() + 1;
                    if(y < 0) break; //avoiding the out of bounds spawn position 
                    if(background[y][x] != null) return false;
                      break;
                }
            }
        }
        
        return true;
    }
    
    private boolean checkLeft(){        //This function checks if when moving left the block will go out of bounds
        if(block.getLeftEdge() == 0) return false;
        
        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();
        
        for (int row = 0; row < h; row++){
            for(int col = 0; col < w; col++){
                if(shape[row][col] != 0){
                    int x = col + block.getX() -1;  // - 1 because we check the element that is to the left
                    int y = row + block.getY();
                    if(background[y][x] != null) return false;
                    break;    
                }
            }
        }
        
        return true;
    }
    
    private boolean checkRight(){       //This function checks if when moving right the block will go out of bounds
        if(block.getRightEdge() == gridColumns) return false;
        
        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();
        
        for (int row = 0; row < h; row++){
            for(int col = w - 1; col >= 0 ; col--){
                if(shape[row][col] != 0){
                    int x = col + block.getX() + 1;  // + 1 because we check the element that is to the right
                    int y = row + block.getY();
                    if(background[y][x] != null) return false;
                    break;    
                }
            }
        }
        
        
        return true; 
    }
    
    
    
    
    //Functions that react on keypresses
    
    public boolean moveBlockDown(){                 //calling this function allways while the game is running and while it returns true i use thread to slow down the falling of the block 
                                                    //checks the value of checkBottom and if it is false executes moveBlockToBackground 
        if(checkBottom() == false) { 
            return false;
        }
        block.moveDown();                           
        repaint();
        return true;
    }
    
    public void moveBlockRight(){           //This function moves the block to the right
        if(block == null) return;
        if(checkRight() == false) return;
        block.moveRight();
        repaint();
    }
    
    public void moveBlockLeft(){            //This function moves the block to the left
        if(block == null) return;
        if(checkLeft() == false) return;
        block.moveLeft();
        repaint();
    
    }
    
    public void dropBlock(){                //This function moves the block instantly to the bottom
        if(block == null) return;
        while (checkBottom() == true){          //checks the state of checkBottom if it is true it moved the block down
            block.moveDown();
        }
        repaint();
    
    }
    
    public void dropSlowBlock(){            //This function moves the block slowly to the bottom
        if(block == null) return;
        if(checkBottom() == false) return;
        
        block.moveDown();
        repaint();
    }
    
    public void rotateBlock(){               //This function rotates the block
        if(block == null) return;
         
        block.rotate();

        if(block.getLeftEdge() < 0) block.setX(0);
        if(block.getRightEdge() >= gridColumns) block.setX(gridColumns-block.getWidth());
        if(block.getBottomEdge() >= gridRows) block.setY(gridRows - block.getHeight());


        repaint();
    }
    
    
    
    
    

    
    public void moveBlockToBackground(){  //This function sets the background blocks that have to be colored it executes after the block has fallen
                                            
        int[][] shape = block.getShape();
        int h = block.getHeight();
        int w = block.getWidth();
        
        int xPos = block.getX();
        int yPos = block.getY();
        
        Color color = block.getColor();
        
        for(int r = 0; r < h; r ++){
            for(int c = 0; c < w; c++){
                if(shape[r][c] == 1){
                    background[r + yPos][c + xPos] = color;
                }
            }
        }
    }
   

    


    
    
    //These functions are used to clear a line that is full
    
    public int clearLines(){    //This function checks if a line is full with blocks
        
        boolean lineFilled;
        int linesCleared = 0;
        
        for (int r = gridRows - 1; r >= 0; r-- ){
            lineFilled = true;
            for (int c = 0; c < gridColumns; c++){
                if(background[r][c] == null){
                    lineFilled = false;
                    break;
                }
            }
            if(lineFilled){
                linesCleared++;
                clearLine(r);
                shiftDown(r);
                clearLine(0); 
                
                r++;
                
                repaint();  
            }
        }
        if(linesCleared > 0){
             Tetris.playClear();
        }
        return linesCleared;
    }
    
    private void clearLine(int r){          //This function is used to clear the background from the full row of blocks
        for(int i = 0; i < gridColumns; i++){
            background[r][i] = null;
        }
    }
        
    private void shiftDown(int r){          //This function is used to move all the blocks that are above the clearline
        for(int row = r; row > 0; row--){
            for (int col = 0; col < gridColumns; col++){
                background[row][col] = background[row-1][col];
            }
        }
    }
    
    
    //These functions are bonus features
    
     public void clearLeft(){           //This function clears out the left column if the score is divisible by 5
        for(int row = 0; row < gridRows; row++){
            background[row][0] = null;
        }
    }
     
    public void shiftUp(){          //This function duplicates the first row when the user gets to the next level
        for(int row = 0; row <= gridRows - 2; row++){
            for (int col = 0; col < gridColumns; col++){
                background[row][col] = background[row+1][col];
            }
        }
    }
    
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        drawBlock(g);
        drawBackground(g);  //background stays null while figure is falling because we dont call moveToBackGround func => background color does not change and background rerenders and it stays null and dispalyes the new figure at the new place
        
    }
}


