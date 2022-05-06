import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class RegistrationFrame extends JFrame {
    JTextField reg_username, reg_password;
    JButton registrami;

    public RegistrationFrame(){
        reg_username = new JTextField(15);
        reg_password = new JTextField(15);
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
            JOptionPane.showMessageDialog(null, "Grazie per aver scelto LoSpacciaLibri", null, JOptionPane.INFORMATION_MESSAGE);
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
