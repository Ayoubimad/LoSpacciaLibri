package Frames;


import Utils.DBManager;
import Utils.User;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class SignUpFrame extends JFrame implements KeyListener {
    JTextField reg_username;
    JPasswordField reg_password;
    JButton registrami;
    JFrame frame;
    ArrayList<User> users;
    public SignUpFrame(JFrame frame){

        users = new ArrayList<>();

        this.frame = frame;
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

        registrami.addActionListener(e -> signUp());

        setContentPane(reg_panel);
        setTitle("Registrazione");
        setSize(300, 200);
        setLocationRelativeTo(null); //fa apparire finestra al centro dello schermo
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);

    }


    private void signUp(){

          readUsers();
          if(check()){
              JOptionPane.showMessageDialog(null, "Utente già registrato!", null, JOptionPane.INFORMATION_MESSAGE);
          }
          else{
              insertUser();
              //frame.dispose();
              this.dispose();
          }

    }

    private boolean check(){
        User c = new User(reg_username.getText(),String.copyValueOf(reg_password.getPassword()));
        for(User i : users){
            if((i.getUsername().equals(c.getUsername()))){
                return true;
            }
        }
        return false;
    }



    private void readUsers(){
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
    }


    private void insertUser(){
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

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if(!reg_username.getText().equals("")){
                if(!String.copyValueOf(reg_password.getPassword()).equals(""))
                    signUp();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
