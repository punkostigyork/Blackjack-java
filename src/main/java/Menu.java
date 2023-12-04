/** @author Györk Pünkösti
 * @version 1.0
 * @since 10/20/2023
 */

/**
 * Represents a simple menu interface for a Blackjack game.
 */


import java.awt.*;
import javax.swing.*;

/**
 * Represents the graphical user interface for the Blackjack game's main menu.
 * Allows players to start a new game, view game history, and exit the application.
 */

public class Menu {
    /** The main window of the application. */
    JFrame window;
    /** The container to hold the components. */
    Container con;
    /** Panel for the title. */
    JPanel titleNamePanel;
    /** Label for the title. */
    JLabel titleNameLabel;
    /** Font for the title. */
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
    /** Font for buttons. */
    Font buttonFont = new Font("Times New Roman", Font.PLAIN, 30);
    /** Panel for the start button. */
    JPanel startButtonPanel;
    /** Button to start the game. */
    JButton startButton;
    /** Panel for the continue button. */
    JPanel continueButtonPanel;
    /** Button to continue the game. */
    JButton continueButton;
    /** Panel for the ranking table button. */
    JPanel historyTablePanel;
    /** Button to view the ranking table. */
    JButton historyTableButton;
    /** Panel for the exit button. */
    JPanel exitButtonPanel;
    /** Button to exit the game. */
    JButton exitButton;

    /**
     * Constructor for the Menu class.
     * Initializes the graphical user interface for the Blackjack menu.
     */
    public Menu() {
        // Initialization of UI components
        // window
        window = new JFrame("BLACKJACK");
        window.setSize(1000, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(new Color(40, 52, 44));
        window.setLayout(null);
        con = window.getContentPane();

        //title
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(200, 50, 600, 150);
        titleNamePanel.setBackground(new Color(40, 52, 44));
        titleNameLabel = new JLabel("BLACKJACK");
        titleNameLabel.setForeground(new Color(233, 174, 3));
        titleNameLabel.setFont(titleFont);

        //startButton
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(400, 200, 200, 60);
        startButtonPanel.setBackground(new Color(40, 52, 44));

        startButton = new JButton("    START    ");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.white);
        startButton.setFont(buttonFont);

        //Add the action listener to the startButton
        startButton.addActionListener(e -> {
            SwingUtilities.invokeLater(NewPlayer::new); // Create a new instance of NewPlayer
            window.dispose(); // Close the Menu window
        });


        // continueButton
        continueButtonPanel = new JPanel();
        continueButtonPanel.setBounds(400, 260, 200, 60);
        continueButtonPanel.setBackground(new Color(40, 52, 44));

        continueButton = new JButton("CONTINUE");
        continueButton.setBackground(Color.black);
        continueButton.setForeground(Color.white);
        continueButton.setFont(buttonFont);

        continueButton.addActionListener(e -> {
            SwingUtilities.invokeLater(Load::new);
            window.dispose();
        });


        //rankingTableButton
        historyTablePanel = new JPanel();
        historyTablePanel.setBounds(400, 320, 200, 60);
        historyTablePanel.setBackground(new Color(40, 52, 44));

        historyTableButton = new JButton(" HISTORY ");
        historyTableButton.setBackground(Color.black);
        historyTableButton.setForeground(Color.white);
        historyTableButton.setFont(buttonFont);

        //Add the action listener to the rankingTableButton
        historyTableButton.addActionListener(e -> {
            SwingUtilities.invokeLater(RankingTable::new); // Create a new instance of NewPlayer
            window.dispose(); // Close the Menu window
        });


        // exitButton
        exitButtonPanel = new JPanel();
        exitButtonPanel.setBounds(400, 380, 200, 60);
        exitButtonPanel.setBackground(new Color(40, 52, 44));

        exitButton = new JButton("     EXIT     ");
        exitButton.setBackground(Color.black);
        exitButton.setForeground(Color.white);
        exitButton.setFont(buttonFont);

        // Create an action listener for the exitButton
        exitButton.addActionListener(e -> {
            // When the EXIT button is clicked, close the application
            window.dispose(); // Close the window
            System.exit(0); // Terminate the program
        });


        // Adding components to the content pane
        titleNamePanel.add(titleNameLabel);
        startButtonPanel.add(startButton);
        continueButtonPanel.add(continueButton);
        historyTablePanel.add(historyTableButton);
        exitButtonPanel.add(exitButton);

        con.add(titleNamePanel);
        con.add(startButtonPanel);
        con.add(continueButtonPanel);
        con.add(historyTablePanel);
        con.add(exitButtonPanel);

        window.setVisible(true);    // Display the window
    }

}
