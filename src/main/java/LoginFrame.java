import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginFrame extends JFrame{
    //creo attributi
    JTextField username;
    JPasswordField password;
    JButton ok;
    private ResultSet rs;
    private String name;
    private String pw;

    public LoginFrame() throws SQLException {
        //creo una lista di User per controllare
        ArrayList<User> users = new ArrayList<>();

        /**carico dal db gli utenti**/

            /*apro la connessione con il db,leggo gli utenti e li metto nell'array users*/
            DBManager.openMySQLConnection();
            rs = DBManager.statement.executeQuery("select * from users");
            while(rs.next()){

                name = String.format("%s",rs.getString("username"));
                pw = String.format("%s", rs.getString("pw"));

                users.add(new User(name,pw));

            }
            /*chiudo connessione con il db*/
            DBManager.closeMySQLConnecion();



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
            //se password e username corrette
            boolean trovato = false;
            for(User i : users){
                if((i.getUsername().equals(username.getText())) && (i.getPassword().equals(String.copyValueOf(password.getPassword())))){
                    JOptionPane.showMessageDialog(null,"Login effettuato", null, JOptionPane.INFORMATION_MESSAGE);
                    trovato = true;
                    JPanel main_panel = new JPanel();
                    main_panel.add(new JLabel("Benvenuto"));
                    setContentPane(main_panel);
                    setVisible(true);
                    setResizable(false);
                    break;
                }
            }
            if(!trovato) {
                JOptionPane.showMessageDialog(null, "Errore!", null, JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
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
