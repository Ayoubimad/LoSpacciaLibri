import com.mysql.cj.protocol.FullReadInputStream;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;

public class Main {
    public static void main(String[] args) {
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

           new MainFrame();

           /*provo a inserire e leggere  un'immagine nel DB da finire e capire AHAHAHAHAHH
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
