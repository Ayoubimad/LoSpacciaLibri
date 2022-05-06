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
           /*provo a inserire e leggere  un'immagine nel DB da finire e capire AHAHAHAHAHH (cazzo ridi)
           Utils.DBManager.openMySQLConnection();
           PreparedStatement statement2 = Utils.DBManager.connection.prepareStatement("insert into immagini values(?,?,?)");
           InputStream input = new FileInputStream("C:\\Users\\imada\\OneDrive\\Desktop\\dance.jpg");
           statement2.setInt(1,1);
           statement2.setString(2,"dance.png");
           statement2.setBlob(3,input);
           statement2.execute();
           ResultSet rs = Utils.DBManager.statement.executeQuery("select * from immagini");
           Blob image = rs.getBlob("picData");
           InputStream output = image.getBinaryStream(0, image.length());
           BufferedImage imagebis =ImageIO.read(output);
           Utils.DBManager.closeMySQLConnecion();
            */
       }catch(Exception e){
           e.printStackTrace();
        }
    }
}
