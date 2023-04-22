/**

 * File: LBlock.java

 * Author: Aleksandar Ivanov

 * Date: 20.04.2023

 */


package tetrisblocks;

import java.awt.Color;
import tetris.TetrisBlock;

public class LBlock extends TetrisBlock{        //This class is responsible for creating the figure and setting the colors

    public LBlock() {
        super(new int[][]{{1,0},{1,0},{1,1}}, Color.orange);

    }
    
}
