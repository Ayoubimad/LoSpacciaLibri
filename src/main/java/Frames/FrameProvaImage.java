package Frames;

import Utils.DBManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class FrameProvaImage extends JFrame {
    Blob image ;
    FrameProvaImage() throws IOException, SQLException {
       /*inserisco l'immagine inserendo i byte*/
        /*** nel db ho una tabella così
         create table books(
         isbn varchar(25) PRIMARY KEY,
         nome varchar(25),
         picData longblob
         );
         ***/
        try {
            DBManager.setConnection();
            PreparedStatement st = DBManager.getConnection().prepareStatement("insert into books values(?,?,?)");
            InputStream input = new FileInputStream("Immagini/dance.jpg");
            st.setString(1, "1234567");
            st.setString(2, "dance.png");
            st.setBlob(3, input);
            st.execute();
            st.close();
        }catch (SQLException e){
            System.out.println("il libro c'è già...bisognerebbe incrementare la quantià di libri");
        }
      /*leggo i byte dell'immagine*/
        try {
            DBManager.setConnection();
            Statement statement = DBManager.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("select * from books");
            while(rs.next()){
                image = rs.getBlob("picData");
            }
            statement.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        /*converto in immagine*/
        InputStream output = image.getBinaryStream(1, image.length());
        JPanel panel = new JPanel();
        BufferedImage myPicture = ImageIO.read(output);

        /*attacco l'immagine alla label poi al pannello*/
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        panel.add(picLabel);


        setContentPane(panel);
        setTitle("LoSpacciaLibri");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }


}
