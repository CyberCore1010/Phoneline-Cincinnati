package Backend;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SFXplayer {
    private static Map<Integer, String> songList = new HashMap<>(); //List of songs and track number

    private static String currentSong; //String for the current song to be played
    private static Clip music; //A music clip holding the current song. Used so the "PlayMusic" method can stop the music

    /**
     * This method is called from the ChangeMusic method below. It's used to populate the list of songs if they aren't
     * already populated. Because of this it's only executed once.
     */
    private static void populateList() {
        songList.put(-1, "Sun Araw - Horse Steppin'.wav"); //Menu music
        songList.put(1, "Miami Disco - Perturbator.wav"); //Game music
        songList.put(2, "Crush - El Huervo.wav"); //Clear music
        songList.put(3, "Miami - Jasper Byrne.wav"); //End music
    }

    /**This method is used to change the current music. It does this by setting the current song to the song set at
     * the index in the HashMap. This is only ever used in conjunction with the PlayMusic method to change the song and
     * play the next one.
     *
     * @param index - takes an int as the index of the song to play from the map
     */
    public static void changeMusic(int index) {
        if(songList.isEmpty()) {
            populateList();
        }
        currentSong = songList.get(index);
    }


    /**
     * This method simply stops whatever song is playing and plays the song at the file location of the currentSong
     * variable (Set from the changeMusic method). It uses Java's AudioInputStream to do this.
     */
    @SuppressWarnings("Duplicates")
    public static synchronized void playMusic() {
        if(music != null) {
            music.stop();
            music.close();
        }
        File URL = new File("res/Music/"+currentSong);
        try {
            AudioInputStream temp = AudioSystem.getAudioInputStream(URL.getAbsoluteFile());
            music = AudioSystem.getClip();
            music.open(temp);
            music.loop(Clip.LOOP_CONTINUOUSLY);
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    /**This method is simply used to play a sound. It takes a string and converts it to a File, sending it to the
     * "play" method below
     *
     * @param url takes the URL of the sound file to play
     */
    public static synchronized void playSound(final String url) {
        File URL = new File("res/SFX/"+url);
        play(URL);
    }

    /**This method simply takes the file object from play sound and plays it using Java's "AudioInputStream"
     *
     * @param URL takes the File object from "playSound"
     */
    @SuppressWarnings("Duplicates")
    private static synchronized void play(File URL) {
        try {
            Clip clip;
            AudioInputStream temp = AudioSystem.getAudioInputStream(URL.getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(temp);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
}
