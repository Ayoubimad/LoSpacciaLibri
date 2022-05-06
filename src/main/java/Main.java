import Frames.StartFrame;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        /*cambiamo look,serve a settare modalità DARK*/
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Bomber sarai bianco");
        }
       try {

           new StartFrame();

       }catch(Exception e){
           e.printStackTrace();
        }
    }
}
