/**

 * File: IBlock.java

 * Author: Aleksandar Ivanov

 * Date: 20.04.2023

 */



package tetrisblocks;

import java.awt.Color;
import tetris.TetrisBlock;

public class IBlock extends TetrisBlock{        //This class is responsible for creating the figure and setting the colors
    public IBlock() {
        super(new int[][]{{1,1,1,1}}, Color.cyan);

    }
    
    @Override
    public void rotate(){
        super.rotate();
        
        if(this.getWidth() == 1){
            this.setX (this.getX() + 1);
            this.setY (this.getY() - 1);
        } else {
            this.setX (this.getX() - 1);
            this.setY (this.getY() + 1);
        }
    }
}
