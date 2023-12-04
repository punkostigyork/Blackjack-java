/** @author Györk Pünkösti
 * @version 1.0
 * @since 10/20/2023
 */

/**
 * Represents a window displaying a ranking table with player information.
 */


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Represents a window displaying a ranking table with player information.
 * The class constructs a graphical user interface presenting player data retrieved from the "ranking.txt" file.
 * It populates a table with player names, balances, and results, allowing users to view this information.
 */

public class RankingTable {
    private JFrame frame;
    private JTable table;

    /**
     * Constructs a new RankingTable window with a table displaying player information.
     */
    public RankingTable() {
        frame = new JFrame("RANKING TABLE");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Create a table model with columns
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("PLAYER");
        model.addColumn("BALANCE");
        model.addColumn("RESULT");

        // Read data from the file and populate the table
        try (BufferedReader reader = new BufferedReader(new FileReader("ranking.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                model.addRow(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create table and set the model
        table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };
        table.setDefaultEditor(Object.class, null); // Set cell editor to null for all cells
        JScrollPane scrollPane = new JScrollPane(table);

        // Exit button
        JButton exitButton = new JButton("EXIT");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(Menu::new); // Create a new instance of NewPlayer
                frame.dispose(); // Close the RankingTable window
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(exitButton);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
