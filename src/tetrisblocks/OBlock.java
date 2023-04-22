/**

 * File: OBlock.java

 * Author: Aleksandar Ivanov

 * Date: 20.04.2023

 */


package tetrisblocks;

import java.awt.Color;
import tetris.TetrisBlock;

public class OBlock extends TetrisBlock{        //This class is responsible for creating the figure and setting the colors

    public OBlock() {
        super(new int[][]{{1,1},{1,1}}, Color.yellow);

    }
    
}
