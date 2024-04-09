
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HealthDataDao {
    private final String url;
    private final String username;
    private final String password;

    public HealthDataDao(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public boolean createHealthData(HealthData healthData) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO health_data (user_id, weight, height, steps, heart_rate, date) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, healthData.getUser().getId());
            statement.setDouble(2, healthData.getWeight());
            statement.setDouble(3, healthData.getHeight());
            statement.setInt(4, healthData.getSteps());
            statement.setInt(5, healthData.getHeartRate());
            statement.setDate(6, healthData.getDate());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<HealthData> getHealthDataForUser(int userId) {
        List<HealthData> healthDataList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM health_data WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                HealthData healthData = extractHealthDataFromResultSet(resultSet);
                healthDataList.add(healthData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return healthDataList;
    }

    private HealthData extractHealthDataFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int userId = resultSet.getInt("user_id");
        double weight = resultSet.getDouble("weight");
        double height = resultSet.getDouble("height");
        int steps = resultSet.getInt("steps");
        int heartRate = resultSet.getInt("heart_rate");
        java.sql.Date date = resultSet.getDate("date");
        // Assuming you have a UserDao to fetch the user object
        User user = new UserDao(url, username, password).getUserById(userId);
        return new HealthData(id, user, weight, height, steps, heartRate, date);
    }

    // Additional methods for update and delete can be added here
}