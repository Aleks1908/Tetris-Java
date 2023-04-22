/**

 * File: SBlock.java

 * Author: Aleksandar Ivanov

 * Date: 20.04.2023

 */


package tetrisblocks;

import java.awt.Color;
import tetris.TetrisBlock;

public class SBlock extends TetrisBlock{        //This class is responsible for creating the figure and setting the colors

    public SBlock() {
        super(new int[][]{{0,1,1},{1,1,0}}, Color.green);

    }
    
}
