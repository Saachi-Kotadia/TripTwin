package Controller;
import java.sql.*;
import java.util.ArrayList;

public class ViewAllTripDetails 
{
   private  ResultSet resultSet;
   

    public ResultSet getResultSet() 
    {
    return resultSet;
   }


public void setResultSet(ResultSet resultSet) {
    this.resultSet = resultSet;
}


    public  ResultSet fetchTrips(String username,String destination) 
    {
         String url = "jdbc:mysql://localhost:3306/final_project";
         String user = "root";
         String password = "1234"; // Replace with your database URL
         String query = "SELECT * FROM final_project.Trips where Username=?"; // 
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url,user,password);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
             ResultSet rs = stmt.executeQuery();
            setResultSet(rs);

        } 
        catch (SQLException|ClassNotFoundException e) {
       e.printStackTrace();
   }
   return resultSet;
}
}

