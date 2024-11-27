package View;
import javax.swing.*;

import Controller.TripEditor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
public class TripEditorGUI extends JFrame  {
private JTextField destinationField, startDateField, endDateField;
private JButton updateButton;
private TripEditor dbHandler;

    public TripEditorGUI(int tripId, String destination, Date startDate, Date endDate) 
    {
        super("Edit Trip");
        

        JPanel mainPanel = new JPanel(new GridLayout(4, 2));
        mainPanel.add(new JLabel("Destination:"));
        destinationField = new JTextField(destination);
        mainPanel.add(destinationField);

        mainPanel.add(new JLabel("Start Date (YYYY-MM-DD):"));
        startDateField = new JTextField(startDate.toString());
        mainPanel.add(startDateField);

        mainPanel.add(new JLabel("End Date (YYYY-MM-DD):"));
        endDateField = new JTextField(endDate.toString());
        mainPanel.add(endDateField);

        updateButton = new JButton("Update Trip");
        updateButton.addActionListener(new UpdateAction());
        mainPanel.add(updateButton);

        add(mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    
    private class UpdateAction implements ActionListener
    {
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            String newDestination = destinationField.getText();
            String newStartDate = startDateField.getText();
            String newEndDate = endDateField.getText();

            // Update trip in the database
            dbHandler=new TripEditor();
            dbHandler.updateTrip(newDestination, newStartDate, newEndDate);
            JOptionPane.showMessageDialog(null,"Trip Updated!");
        }
    }
}
}

