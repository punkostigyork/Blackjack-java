/** @author Györk Pünkösti
 * @version 1.0
 * @since 10/20/2023
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a window for loading saved game data and displaying it in a table.
 * Allows users to select a game from the displayed list by its Save ID and load it for continuation.
 */
public class Load {
    /**
     * Constructs a Load window to display saved game data in a table format.
     * Users can select a game from the displayed list using its Save ID and load it for continuation.
     */
    public Load() {
        JFrame frame = new JFrame("Load Game");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Save ID");
        model.addColumn("Date");
        model.addColumn("Time");

        List<String[]> saveData = readSaveData();
        for (String[] data : saveData) {
            model.addRow(data);
        }

        JTable table = new JTable(model);
        table.setDefaultEditor(Object.class, null);

        JScrollPane scrollPane = new JScrollPane(table);

        JTextField saveIDField = new JTextField(10); // New saveID input field

        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(Menu::new);
                frame.dispose();
            }
        });

        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputSaveID = saveIDField.getText(); // Get input saveID
                boolean isValidSave = saveData.stream().anyMatch(data -> data[0].equals(inputSaveID));

                if (isValidSave) {
                    System.out.println("Loading game with Save ID: " + inputSaveID);
                    BlackjackContinue blackjackContinue = new BlackjackContinue(inputSaveID);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Save ID. Please enter a valid Save ID.");
                }
            }
        });

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Panel for input field
        inputPanel.add(new JLabel("Enter Save ID: "));
        inputPanel.add(saveIDField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(backButton);
        buttonPanel.add(loadButton);

        JPanel controlPanel = new JPanel(new GridLayout(2, 1));
        controlPanel.add(inputPanel);
        controlPanel.add(buttonPanel);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Reads the saved game data from the "saved_game.txt" file and constructs a list of String arrays.
     * Each array represents a row of data containing Save ID, Date, and Time information.
     *
     * @return A list of String arrays representing the saved game data.
     */
    private List<String[]> readSaveData() {
        List<String[]> saveData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("saved_game.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(";");
                if (tokens.length >= 4) {
                    String saveID = tokens[0];
                    String date = tokens[1];
                    String time = tokens[2];
                    saveData.add(new String[]{saveID, date, time});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return saveData;
    }
}
