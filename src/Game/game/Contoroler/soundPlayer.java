package Game.game.Contoroler;


import Game.login.setting;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class soundPlayer {

    private final static soundPlayer SoundPlayerInstance = new soundPlayer ();

    private static float soundVolume = 1;

    private static AudioInputStream audioInputStream;
    private static Clip clip;

    private static boolean muteSound = false;

    public static soundPlayer getInstance() {
        return SoundPlayerInstance;
    }
    public static void play(String path){
        play (path, setting.getVolumeSound ());
    }
    public static void play(String path, float value) {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(path));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            floatControl.setValue(value);
            if (muteSound) {
                BooleanControl booleanControl = (BooleanControl) clip.getControl(BooleanControl.Type.MUTE);
                booleanControl.setValue(true);
            } else {
                BooleanControl booleanControl = (BooleanControl) clip.getControl(BooleanControl.Type.MUTE);
                booleanControl.setValue(false);
            }
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void decreaseSound() {
        soundVolume -= 0.06;

        FloatControl floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        floatControl.setValue(20f * (float) Math.log10(soundVolume));
    }

    public static void increaseSound() {
        if (soundVolume < 1) {
            soundVolume += 0.06;

            FloatControl floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            floatControl.setValue(20f * (float) Math.log10(soundVolume));
        }
    }

}
