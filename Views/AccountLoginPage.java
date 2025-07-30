package Views;

import Utilities.FixedValues;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Represents the login page of the Calendar App.
 * 
 * This class extends JFrame and provides a graphical interface
 * for users to enter their username and password, and either log in or create an account.
 */
public class AccountLoginPage extends JFrame {

    // Panels to organize the layout
    private JPanel headerPanel = new JPanel();
    private JPanel buttonsPanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    // Labels for UI text
    private JLabel welcomeTitle = new JLabel("Welcome to the Calendar App");
    private JLabel usernameLabel = new JLabel("Username:");
    private JLabel passwordLabel = new JLabel("Password:");

    // Buttons for user actions
    private JButton createAccountButton = new JButton("Create Account");
    private JButton loginButton = new JButton("Login");

    // Input fields for login credentials
    private JTextField usernameField = new JTextField();
    private JTextField passwordField = new JTextField();

    /**
     * Constructs the AccountLoginPage GUI.
     * Initializes the frame layout and adds all components including labels,
     * text fields, buttons, and custom fonts.
     */
    public AccountLoginPage() {
        this.setTitle("Login Page");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500, 400);
        this.setResizable(false);
        this.setLayout(null);

        // Set bounds for each panel in the frame
        headerPanel.setBounds(0, 0, 500, 100);
        contentPanel.setBounds(0, 100, 500, 100);
        buttonsPanel.setBounds(0, 200, 500, 200);

        // Header panel setup
        headerPanel.setLayout(null);
        welcomeTitle.setFont(FixedValues.TITLE_FONT);
        welcomeTitle.setBounds(75, 20, 400, 50);
        headerPanel.add(welcomeTitle);

        // Content panel setup (Username and Password)
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

        // Buttons panel setup
        buttonsPanel.setLayout(null);
        createAccountButton.setFont(FixedValues.TITLE_FONT);
        createAccountButton.setFocusable(false);
        createAccountButton.setBounds(125, 30, 250, 50);

        loginButton.setFont(FixedValues.TITLE_FONT);
        loginButton.setFocusable(false);
        loginButton.setBounds(150, 100, 200, 50);

        buttonsPanel.add(createAccountButton);
        buttonsPanel.add(loginButton);

        // Add panels to frame
        this.add(headerPanel);
        this.add(contentPanel);
        this.add(buttonsPanel);

        this.setVisible(true);
    }

    /**
     * Returns the login button component.
     * 
     * @return the login button
     */
    public JButton getLoginButton() {
        return loginButton;
    }

    /**
     * Returns the create account button component.
     * 
     * @return the create account button
     */
    public JButton getCreateAccountButton() {
        return createAccountButton;
    }

    /**
     * Registers an ActionListener for the login button.
     *
     * @param listener the ActionListener to add
     */
    public void addLoginButtonListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    /**
     * Registers an ActionListener for the create account button.
     *
     * @param listener the ActionListener to add
     */
    public void addCreateAccountButtonListener(ActionListener listener) {
        createAccountButton.addActionListener(listener);
    }

    /**
     * Returns the text entered in the username field.
     *
     * @return the username
     */
    public String getUsername() {
        return usernameField.getText();
    }

    /**
     * Returns the text entered in the password field.
     *
     * @return the password
     */
    public String getPassword() {
        return passwordField.getText();
    }
}