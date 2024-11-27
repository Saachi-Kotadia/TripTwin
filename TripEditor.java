package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TripEditor 
{
    private Connection connection;
    private int tripIdToUpdate;
    public void updateTrip(String newDestination, String newStartDate, String newEndDate) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "root", "1234");
            PreparedStatement statement = connection.prepareStatement("UPDATE final_project.Trips SET destination=?, start_date=?, end_date=? WHERE trip_id=?");
            statement.setString(1, newDestination);
            statement.setString(2, newStartDate);
            statement.setString(3, newEndDate);
            statement.setInt(4, tripIdToUpdate);
            statement.executeUpdate();
            // You can add more handling or feedback here if needed
        } catch (SQLException|ClassNotFoundException ex) {
            ex.printStackTrace();
            // Handle the exception as required
        }
    }
}

