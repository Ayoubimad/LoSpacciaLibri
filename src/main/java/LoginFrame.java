import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LoginFrame extends JFrame{

    //creo attributi
    JTextField username;
    JPasswordField password;
    JButton ok;
    ArrayList<User> users;
    public LoginFrame(JFrame frame) throws SQLException {

        users = new ArrayList<>();
        username = new JTextField(15);
        password = new JPasswordField(15);
        password.setEchoChar('*'); //per nascondere la password
        ok = new JButton("Ok");
        JPanel login_panel = new JPanel();
        login_panel.setLayout(new GridBagLayout());
        //login_panel.setBackground(Color.ORANGE);

        //bordi
        Border b1 = BorderFactory.createTitledBorder("Eseguire il login");
        Border b2 = BorderFactory.createEmptyBorder(20, 5, 5, 5);
        Border bordo = BorderFactory.createCompoundBorder(b1, b2);

        login_panel.setBorder(bordo);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.weighty = 0.05;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0, 0, 0, 5);

        login_panel.add(new JLabel("Username"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 0.05;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0, 0, 0, 5);

        login_panel.add(new JLabel("Password"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.weighty = 0.05;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 0, 0, 0);

        login_panel.add(username, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 0.05;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 0, 0, 0);

        login_panel.add(password, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.9;
        gbc.weighty = 0.9;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.PAGE_START;

        login_panel.add(ok, gbc);

        ok.addActionListener(e -> {
            /*leggo dal DB gli utenti e gli aggiunge alla lista*/
            try {
                DBManager.setConnection();
                Statement statement = DBManager.getConnection().createStatement();
                ResultSet rs = statement.executeQuery("select * from users");
                while(rs.next()){
                    String username = String.format("%s",rs.getString("username"));
                    String password = String.format("%s", rs.getString("pw"));
                    users.add(new User(username,password));
                }
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            //se password e username corrette
            boolean trovato = false;
            for(User i : users){
                if((i.getUsername().equals(username.getText())) && (i.getPassword().equals(String.copyValueOf(password.getPassword())))){
                    JOptionPane.showMessageDialog(null,"Login effettuato", null, JOptionPane.INFORMATION_MESSAGE);
                    trovato = true;
                    frame.dispose();
                    this.dispose();
                    break;
                }
            }
            if(!trovato) {
                JOptionPane.showMessageDialog(null, "Utente non registrato!", null, JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            }
        });

        setContentPane(login_panel);
        setTitle("Login");
        setSize(300, 200);
        setLocationRelativeTo(null); //fa apparire finestra al centro dello schermo
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }





}
