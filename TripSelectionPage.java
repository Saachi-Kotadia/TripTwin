package View;
import javax.swing.*;

import Controller.ViewAllTripDetails;
import Controller.ViewTrip;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.prefs.Preferences;

public class TripSelectionPage extends JFrame 
{
    private JComboBox<String> tripsDropdown;
    private JButton openTripButton;
    

    public TripSelectionPage() {
        setTitle("Select Trip");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Dropdown for trips
        tripsDropdown = new JComboBox<>();
        setTrips();
        add(tripsDropdown, BorderLayout.NORTH);

        // Button to open selected trip
        openTripButton = new JButton("Open Trip");
        openTripButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openSelectedTrip();
            }
        });
        add(openTripButton, BorderLayout.CENTER);

        setVisible(true);
    }

    // Method to populate the dropdown with user's trips
    private void setTrips() {
        String userID = retrieveUserID(); // Retrieve the user's ID
        ViewTrip view=new ViewTrip();
        List<String> userTrips = view.fetchTrips(userID);
        for (String trip : userTrips) {
            tripsDropdown.addItem(trip);
        }
    }

    // Method to retrieve the user's ID
    private String retrieveUserID() {
        // Retrieve the user ID from preferences (or any other storage mechanism)
        Preferences prefs = Preferences.userNodeForPackage(Login.class);
        return prefs.get("userID", ""); // Return the user ID
    }

   
    

    // Method to open the selected trip
    private void openSelectedTrip() 
    {
        String selectedTrip = (String) tripsDropdown.getSelectedItem();
        openTripPage(selectedTrip);
    }

    // Method to open a new page for the selected trip
    private void openTripPage(String tripName) 
    {
        try
        {
        ViewAllTripDetails viewall=new ViewAllTripDetails();
        ResultSet rs=viewall.fetchTrips(retrieveUserID(), tripName);
        while(rs.next())
        {
        String  destination=rs.getString("Destination");
        int TripID=rs.getInt("TripID");
        Date startDate=rs.getDate("StartDate");
        Date endDate=rs.getDate("EndDate");
        new TripEditorGUI(TripID, destination, startDate, endDate);
        }
        
    }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
