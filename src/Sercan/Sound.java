package Sercan;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

// The Sound class is responsible for handling sound effects in the game.
// It allows loading sound files, playing them, looping them, and stopping them.
public class Sound {
    
    // Clip object to handle the playback of audio.
    Clip clip;
    
    // Array to store URLs for different sound files.
    URL soundURL[] = new URL[10];

    
//      Constructor for the Sound class.
//     It initializes the sound URLs with different sound files for the game.
     
    public Sound() {
        // Initialize the sound files in the soundURL array.
        soundURL[0] = getClass().getResource("/Sound/MainSound.wav"); // Main background sound
        soundURL[1] = getClass().getResource("/Sound/Victory.wav"); // Victory sound
        soundURL[2] = getClass().getResource("/Sound/Lose.wav"); // Lose sound
    }

    
//      This method sets the sound file that needs to be played.
//     The index 'i' corresponds to different sound files in the soundURL array.
//      i The index of the sound in the soundURL array.
     
    public void setFile(int i) {
        try {
            // Load the audio file as an AudioInputStream.
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            
            // Get a Clip (a type of sound clip that can be played).
            clip = AudioSystem.getClip();
            
            // Open the audio clip with the input stream (audio data).
            clip.open(ais);
        } catch (Exception e) {
            // If there's an error loading the sound, handle it here.
            e.printStackTrace();
        }
    }

    /**
     * This method plays the current sound once.
     */
    public void play() {
        // Start playing the sound clip.
        clip.start();
    }

    
//      This method loops the current sound continuously.
//      It will play the sound repeatedly until it is manually stopped.
     
    public void loop() {
        // Loop the sound continuously.
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    
//     This method stops the currently playing sound.
     
    public void stop() {
		clip.stop();
	}
}