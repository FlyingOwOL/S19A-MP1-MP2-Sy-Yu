package Views;

import Utilities.FixedValues;

import javax.swing.JFrame;

import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;

public class AccountLoginPage extends JFrame{
    private JPanel headerPanel = new JPanel();
    private JPanel buttonsPanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    private JLabel welcomeTitle = new JLabel("Welcome to the Calendar App");
    private JLabel usernameLabel = new JLabel("Username:");
    private JLabel passwordLabel = new JLabel("Password:");

    private JButton createAccountButton = new JButton("Create Account");
    private JButton loginButton = new JButton("Login");
    
    private JTextField usernameField = new JTextField();
    private JTextField passwordField = new JTextField();
    
    public AccountLoginPage(){
        this.setTitle("Login Page");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500, 400);
        this.setResizable(false);
        this.setLayout(null);

        // Set bounds for each panel in the frame
        headerPanel.setBounds(0, 0, 500, 100);
        contentPanel.setBounds(0, 100, 500, 100);
        buttonsPanel.setBounds(0, 200, 500, 200);

        //components for headerPanel
        headerPanel.setLayout(null);        
        welcomeTitle.setFont(FixedValues.TITLE_FONT);
        welcomeTitle.setBounds(75, 20, 400, 50);

        headerPanel.add(welcomeTitle);

        //components for contentPanel
        contentPanel.setLayout(null);        
        usernameLabel.setFont(FixedValues.LABEL_FONT);
        usernameLabel.setBounds(20, 0, 100, 50);
        passwordLabel.setFont(FixedValues.LABEL_FONT);
        passwordLabel.setBounds(20, 50, 100, 50);

        usernameField.setFont(FixedValues.LABEL_FONT);
        usernameField.setBounds(100, 12, 300, 30);
        passwordField.setFont(FixedValues.LABEL_FONT);
        passwordField.setBounds(100, 62, 300, 30);

        contentPanel.add(usernameLabel);
        contentPanel.add(usernameField);
        contentPanel.add(passwordLabel);
        contentPanel.add(passwordField);

        //components for buttonsPanel
        buttonsPanel.setLayout(null);         
        createAccountButton.setFont(FixedValues.TITLE_FONT);
        createAccountButton.setFocusable(false);
        createAccountButton.setBounds(125, 30, 250, 50);

        loginButton.setFont(FixedValues.TITLE_FONT);
        loginButton.setFocusable(false);
        loginButton.setBounds(150, 100, 200, 50);

        buttonsPanel.add(createAccountButton);
        buttonsPanel.add(loginButton);
   

        // Add panels to the frame
        this.add(headerPanel);
        this.add(contentPanel);
        this.add(buttonsPanel);
        this.setVisible(true);
    }
    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getCreateAccountButton() {
        return createAccountButton;
    }

    public void addLoginButtonListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public void addCreateAccountButtonListener(ActionListener listener) {
        createAccountButton.addActionListener(listener);
    }

    public String getUsername() {
        return usernameField.getText();
    }
    public String getPassword() {
        return passwordField.getText();
    }
}
