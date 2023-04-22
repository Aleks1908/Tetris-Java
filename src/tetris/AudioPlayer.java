/**

 * File: AudioPlayer.java

 * Author: Aleksandar Ivanov

 * Date: 20.04.2023

 */

package tetris;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {
    
    //Initializing all songs and their paths
    private String soundsFolder = "tetrissounds" + File.separator;
    private String clearLinePath = soundsFolder + "clearline.wav";
    private String gameoverPath = soundsFolder + "gameover.wav";
    private String nextLevelPath = soundsFolder + "nextlevel.wav";
    private String themeSongPath = soundsFolder + "tetris.wav";

    private Clip clearLineSound, gameoverSound, nextLevelSound, themeSong;
    
    public AudioPlayer(){       //Loading all songs and checking for exceptions
        try {
            clearLineSound = AudioSystem.getClip();
            gameoverSound = AudioSystem.getClip();
            nextLevelSound = AudioSystem.getClip();
            themeSong = AudioSystem.getClip();
            
            clearLineSound.open(AudioSystem.getAudioInputStream(new File(clearLinePath).getAbsoluteFile()));
            
            gameoverSound.open(AudioSystem.getAudioInputStream(new File(gameoverPath).getAbsoluteFile()));
            
            nextLevelSound.open(AudioSystem.getAudioInputStream(new File(nextLevelPath).getAbsoluteFile()));
            
            themeSong.open(AudioSystem.getAudioInputStream(new File(themeSongPath).getAbsoluteFile()));
            
            
            
        } catch (LineUnavailableException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    //These functions are used to play the songs
    public void playClearLine(){
        clearLineSound.setFramePosition(0);
        clearLineSound.start();
    }
    public void playGameover(){
        gameoverSound.setFramePosition(0);
        gameoverSound.start();
    }
    public void playNextLevel(){
        nextLevelSound.setFramePosition(0);
        nextLevelSound.start();
    }
    
    public void playThemeSong(){
        themeSong.setFramePosition(0);
        themeSong.start();
    }
    public void stopThemeSong(){
        themeSong.stop();
    }
    
}
