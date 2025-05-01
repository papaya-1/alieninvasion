import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class Soundtrack {
    public static void main(String[] args) {
        String filepath = "name of filepath here";
        //must be WAV file
        play(filepath);
        JOptionPane.showMessageDialog(null, "Click OK to stop playing");
    }

    public static void play(String fileLoc){
        try{
            File path = new File(fileLoc);
            if (path.exists()){
                AudioInputStream input = AudioSystem.getAudioInputStream(path);
                Clip clip = AudioSystem.getClip();
                clip.open(input);
                clip.start();
            }
            else{
                //System.out.println("Can't find song");
            }
        }
        catch(Exception e){
            //System.out.println("e");
        }
    }
}
