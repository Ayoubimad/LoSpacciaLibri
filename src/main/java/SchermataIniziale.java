import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.sql.SQLException;

public class SchermataIniziale extends JFrame {
    JButton login, registrazione;

    public SchermataIniziale(){
        login = new JButton("Login");
        registrazione = new JButton("Registrati");
        JLabel reg = new JLabel("Sei gia registrato?");
        JLabel non_reg = new JLabel("Non sei registrato?");

        JPanel start_panel = new JPanel();
        start_panel.setLayout(new GridBagLayout());

        Border b1 = BorderFactory.createTitledBorder("Benvenuto in LoSpacciaLibri!");
        Border b2 = BorderFactory.createEmptyBorder(20, 5, 5, 5);
        Border bordo = BorderFactory.createCompoundBorder(b1, b2);
        start_panel.setBorder(bordo);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(0, 0, 5, 0);

        start_panel.add(reg, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(0, 0, 15, 0);

        start_panel.add(login, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(0, 0, 5, 0);

        start_panel.add(non_reg, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;

        start_panel.add(registrazione, gbc);

        login.addActionListener(e -> {
            try {
                new LoginFrame();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        registrazione.addActionListener(e -> {
            new RegistrationFrame();
        });

        setContentPane(start_panel);
        setTitle("LoSpacciaLibri");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
