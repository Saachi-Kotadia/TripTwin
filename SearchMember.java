package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchMember 
{ 
    private Connection connection;
    public SearchMember()
    {

    try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "root", "1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<String> searchMembers(String firstName) {
        List<String> searchResults = new ArrayList<>();
        try {
            String selectQuery = "SELECT First_Name, Last_Name,Username FROM final_project.users WHERE First_Name LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, "%" + firstName + "%"); 
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) 
            {
                String memberFirstName = resultSet.getString("First_Name");
                String memberLastName = resultSet.getString("Last_Name");
                String Username=resultSet.getString("Username");
                String fullName = memberFirstName + " " + memberLastName;
                searchResults.add(Username);
            }
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return searchResults;
    }
}

