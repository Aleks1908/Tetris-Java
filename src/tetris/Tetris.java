/**

 * File: Tetris.java

 * Author: Aleksandar Ivanov

 * Date: 20.04.2023

 */

package tetris;

import javax.swing.JOptionPane;


public class Tetris {

    private static GameForm gf;
    private static LeaderboardForm lf;
    private static StartupForm sf; 
    private static Instructions ins;
    
    private static AudioPlayer audio = new AudioPlayer();
    
    
    //These functions are responsible for:
    
    public static void start(){ //Setting the game visibility to true and staring the game
        gf.setVisible(true);
        gf.startGame();
    }
    public static void showLeaderboard(){       //Showing the leaderboard
        lf.setVisible(true);
    }
    
    public static void showStartup(){           //Showing the main menu
        sf.setVisible(true);
    }
    public static void showInstructions(){           //Showing the instructions menu
        ins.setVisible(true);
    }

    
    public static void gameOver(int score){     //Showing the gameover screen
        playGameover();
        stopThemeSong();
        String playerName = JOptionPane.showInputDialog("GameOver\nPlease enter your name.");
        System.out.println(playerName);
        gf.setVisible(false);
        lf.addPlayer(playerName, score);
         
    }
    
    //Play the songs on call
    public static void playClear(){
        audio.playClearLine();
    }
    
    public static void playGameover(){
        audio.playGameover();
    }
    public static void playNextLevel(){
        audio.playNextLevel();
    }
    
    public static void playThemeSong(){
        audio.playThemeSong();
    }
    public static void stopThemeSong(){
        audio.stopThemeSong();
    }
        
    public static void main(String args[]){
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                gf = new GameForm();
                lf = new LeaderboardForm();
                sf = new StartupForm();
                ins = new Instructions();
                sf.setVisible(true);
            }
        });
    }
    
}
