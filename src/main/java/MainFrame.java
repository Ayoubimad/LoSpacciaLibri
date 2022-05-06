import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class MainFrame extends JFrame implements ActionListener {

    private JPanel mainPanel;
    private JButton loginButton;

    MainFrame() throws IOException {

        super("LoSpacciaLibri");

        setIconImage(ImageIO.read(new File("Immagini/Icona.png")));

        setContentPane(mainPanel);
        setVisible(true);
        setResizable(false);
        setSize(800,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loginButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*evento login buttun*/
        if(e.getSource()==loginButton){
            try {
                new LoginFrame();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
