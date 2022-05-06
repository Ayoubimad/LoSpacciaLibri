import Frames.SchermataIniziale;
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
           /*inserisco
           * username=Ayoub
           * pw= password
           * per simulare un accesso
           */
           /*DBManager.openMySQLConnection();
           DBManager.statement.execute("insert into users values ('Ayoub','password')");
           DBManager.closeMySQLConnecion();
           */
           /*mainFrame è per prove ayoub*/
           //new MainFrame();

           new SchermataIniziale();

           /*provo a inserire e leggere  un'immagine nel DB da finire e capire AHAHAHAHAHH (cazzo ridi)
           DBManager.openMySQLConnection();
           PreparedStatement statement2 = DBManager.connection.prepareStatement("insert into immagini values(?,?,?)");
           InputStream input = new FileInputStream("C:\\Users\\imada\\OneDrive\\Desktop\\dance.jpg");
           statement2.setInt(1,1);
           statement2.setString(2,"dance.png");
           statement2.setBlob(3,input);
           statement2.execute();
           ResultSet rs = DBManager.statement.executeQuery("select * from immagini");
           Blob image = rs.getBlob("picData");
           InputStream output = image.getBinaryStream(0, image.length());
           BufferedImage imagebis =ImageIO.read(output);
           DBManager.closeMySQLConnecion();
            */

       }catch(Exception e){
           e.printStackTrace();
        }
    }
}
