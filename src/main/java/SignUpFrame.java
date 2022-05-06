import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class SignUpFrame extends JFrame {
    JTextField reg_username;
    JPasswordField reg_password;
    JButton registrami;

    public SignUpFrame(JFrame frame){

        reg_username = new JTextField(15);
        reg_password = new JPasswordField(15);
        reg_password.setEchoChar('*');
        registrami = new JButton("ok");

        JPanel reg_panel = new JPanel();
        reg_panel.setLayout(new GridBagLayout());

        Border b1 = BorderFactory.createTitledBorder("Crea un account SpacciaLibri");
        Border b2 = BorderFactory.createEmptyBorder(20, 5, 5, 5);
        Border bordo = BorderFactory.createCompoundBorder(b1, b2);
        reg_panel.setBorder(bordo);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.weighty = 0.05;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0, 0, 0, 5);

        reg_panel.add(new JLabel("Inserire username"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 0.05;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0, 0, 0, 5);

        reg_panel.add(new JLabel("Inserire password"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.weighty = 0.05;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 0, 0, 0);

        reg_panel.add(reg_username, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 0.05;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 0, 0, 0);

        reg_panel.add(reg_password, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.9;
        gbc.weighty = 0.9;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.PAGE_START;

        reg_panel.add(registrami, gbc);

        registrami.addActionListener(e -> {
            if(!Objects.equals(reg_username.getText(), "") && !Objects.equals(String.copyValueOf(reg_password.getPassword()), "")){
                try {
                    DBManager.setConnection();
                    Statement statement = DBManager.getConnection().createStatement();
                    String sql = String.format("insert into users values ('%s','%s')",reg_username.getText(),
                                                String.copyValueOf(reg_password.getPassword()));
                    statement.execute(sql);
                    statement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                this.dispose();
                frame.dispose();
                /***Qui si dovrebbe aprire l'app con l'utente appena registrato***/
                JOptionPane.showMessageDialog(null, "Grazie per aver scelto LoSpacciaLibri", null, JOptionPane.INFORMATION_MESSAGE);
                /*ma apre solo una foto caricata nel db poi letta!!!! DAYE CAAAZZZOOOO*/
                try {
                    new FrameProvaImage();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            else {
                this.dispose();
                JOptionPane.showMessageDialog(null, "Inserire username e password valide", null, JOptionPane.INFORMATION_MESSAGE);
            }
        });

        setContentPane(reg_panel);
        setTitle("Registrazione");
        setSize(300, 200);
        setLocationRelativeTo(null); //fa apparire finestra al centro dello schermo
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

    }

}
