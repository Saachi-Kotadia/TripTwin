package Controller;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupPosts {
    private Connection connection;

    public void connect() {
        try {
            // Connect to your database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "root", "1234");
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    public void insertPost(String post,String username,String groupname) 
    {
        try 
        {
            // Insert the post into your database
            PreparedStatement statement = connection.prepareStatement("INSERT INTO final_project.GroupPosts (Username,group_name,post_text) VALUES (?,?,?)");
            statement.setString(1, username);
            statement.setString(2, groupname);
            statement.setString(3, post);
            statement.executeUpdate();
            } 
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public List<String> getPosts() 
    {
        List<String> posts = new ArrayList<>();
        try {
            // Retrieve posts from your database
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT post_text,Username FROM final_project.GroupPosts");
             while (resultSet.next()) 
            {
                String post = resultSet.getString("post_text");
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }
}

