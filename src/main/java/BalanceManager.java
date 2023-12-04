/** @author Györk Pünkösti
 * @version 1.0
 * @since 10/20/2023
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Manages and displays balance information for player and dealer.
 * Allows adding funds to the player's balance.
 */
public class BalanceManager {
    private JFrame frame;
    private JLabel playerBalanceLabel;
    private JLabel dealerBalanceLabel;
    private JLabel addAmountLabel; // Label for "Add Amount"
    private JLabel addedAmountLabel; // Label for displaying added amount
    private JTextField addAmountField;
    private JButton addButton;
    private double playerBalance;
    private double dealerBalance;
    private double addedAmount;

    /**
     * Initializes the balance manager window with initial player and dealer balances.
     *
     * @param initialPlayerBalance Initial player's balance
     * @param initialDealerBalance Initial dealer's balance
     */
    public BalanceManager(double initialPlayerBalance, double initialDealerBalance) {
        // Initialization of balances and added amount
        this.playerBalance = initialPlayerBalance;
        this.dealerBalance = initialDealerBalance;
        this.addedAmount = 0;

        // Frame setup
        frame = new JFrame("Balance Manager");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 2)); // Increased the rows to accommodate the new label
        frame.setLocationRelativeTo(null);

        // Initializing labels, text field, and button
        playerBalanceLabel = new JLabel("Player Balance: $" + playerBalance);
        dealerBalanceLabel = new JLabel("Dealer Balance: $" + dealerBalance);
        addAmountLabel = new JLabel("Add Amount: "); // Moved "Add Amount" label here
        addedAmountLabel = new JLabel("Added Amount: $" + addedAmount);
        addAmountField = new JTextField();
        addButton = new JButton("Add Funds");

        // Adding components to the frame
        frame.add(playerBalanceLabel);
        frame.add(dealerBalanceLabel);
        frame.add(addAmountLabel); // Added "Add Amount" label before the text field
        frame.add(addAmountField);
        frame.add(addedAmountLabel); // Placed added amount label after "Add Amount" label
        frame.add(addButton);

        // Action listener for adding funds
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amountToPlay = Double.parseDouble(addAmountField.getText());
                    if (amountToPlay > 0 && amountToPlay <= playerBalance) {
                        addedAmount = amountToPlay;
                        playerBalanceLabel.setText("Player Balance: $" + playerBalance);
                        addedAmountLabel.setText("Added Amount: $" + addedAmount);
                        addAmountField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid amount or exceeds balance!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid amount!");
                }
            }
        });

        frame.setVisible(true);
    }

    /**
     * Retrieves the player's balance.
     *
     * @return The player's balance
     */
    public double getPlayerBalance() {
        return playerBalance;
    }

    /**
     * Retrieves the dealer's balance.
     *
     * @return The dealer's balance
     */
    public double getDealerBalance() {
        return dealerBalance;
    }

    /**
     * Retrieves the added amount.
     *
     * @return The added amount
     */
    public double getAddedAmount() {
        return addedAmount;
    }

    /**
     * Retrieves the frame of the balance manager window.
     *
     * @return The JFrame instance of the balance manager
     */
    public JFrame getFrame() { return frame; }

    /**
     * Updates the player's and dealer's balances with new values.
     *
     * @param updatedPlayerBalance New player's balance
     * @param updatedDealerBalance New dealer's balance
     */
    public void updateBalances(double updatedPlayerBalance, double updatedDealerBalance) {
        this.playerBalance = updatedPlayerBalance;
        this.dealerBalance = updatedDealerBalance;
        playerBalanceLabel.setText("Player Balance: $" + playerBalance);
        dealerBalanceLabel.setText("Dealer Balance: $" + dealerBalance);
    }
}
