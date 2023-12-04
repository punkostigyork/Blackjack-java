/** @author Györk Pünkösti
 * @version 1.0
 * @since 10/20/2023
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents an end-of-game window displaying a message and a button to return to the main menu.
 */
public class EndOfGame {
    /**
     * Constructs an EndOfGame window with a message and a button to return to the main menu.
     *
     * @param message The message to be displayed in the window
     */
    public EndOfGame(String message) {
        JFrame frame = new JFrame("EndOfGame");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        JLabel messageLabel = new JLabel(message);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));

        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(Menu::new); // Create a new instance of Menu
                frame.dispose(); // Close the EndOfGame window
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(messageLabel, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
