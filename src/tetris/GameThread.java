/**

 * File: GameThread.java

 * Author: Aleksandar Ivanov

 * Date: 20.04.2023

 */

package tetris;



public class GameThread extends Thread{
    
    private GameArea ga;
    private GameForm gf;
    private int score;
    private int level = 1;
    private int scorePerLevel = 3;
    
    private int pause = 700;
    private int speedupPerLevel = 100;
     
    public GameThread(GameArea ga, GameForm gf){
        this.ga = ga;
        this.gf = gf;
        
        gf.updateScore(score);
        gf.updateLevel(level);
    }
    
    @Override
    public void run(){
        boolean executedClearLeft = false;
        Tetris.playThemeSong();
        gf.updateScore(0);
        gf.updateLevel(0);
        while(true){        //While the game is playing we call functions that are responsible for: 
            ga.spawnBlock();
            while(ga.moveBlockDown() == true){      //Moving the blocks down
                try {
                    Thread.sleep(1000);        

                } catch (InterruptedException ex) {
                    return;                
                }
            }
            if(ga.isBlockOutOfBounds()){        //Checking if the block is inboubds
                Tetris.gameOver(score);
                break;
            }
            ga.moveBlockToBackground();         //Moves the blocks that have fallen to the background
            score += ga.clearLines();           //Clearing full lines
            gf.updateScore(score);              //Updating the score

            int lvl = score / scorePerLevel + 1;
            if(lvl > level){                  //Speeding up the game
                level = lvl;
                gf.updateLevel(level);      
                pause -= speedupPerLevel;
                Tetris.playNextLevel();
                ga.shiftUp();                   //Duplicating the last row
            }
            if(score % 5 == 0 && !executedClearLeft && score != 0){     //Clearing the left column
                ga.clearLeft();
                executedClearLeft = true;
            }
            else if(score % 5 != 0){
                executedClearLeft = false;
            }
        }
    }
}

