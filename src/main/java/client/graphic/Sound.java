package client.graphic;

import client.enums.ClientPath;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {

    private File audioFile;
    private AudioInputStream audioStream;
    private AudioFormat format;
    private DataLine.Info info;
    private Clip audioClip;
    private boolean check;

    public Sound(ClientPath pathEnum) {
        check = false;
        audioFile = new File(pathEnum.getPath());
        try {
            audioStream = AudioSystem.getAudioInputStream(audioFile);
            format = audioStream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    public void playLoop() {
        //audioClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void playOne() {
        if (check) {
            audioClip.loop(1);
        } else {
            audioClip.start();
            check = true;
        }

    }

    public void stopAudio() {
        audioClip.stop();
    }

}
