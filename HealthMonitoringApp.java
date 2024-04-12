// Health Monitoring App
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

public class HealthMonitoringApp {

    public static void main(String[] args) {
        // Establish database connection
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Health_Stack_Project_Term3_Final_sprint", "postgres", "Zack2012")) {
            // Fetch and display health data
            fetchAndDisplayHealthData(connection);

            // Fetch and display medicine reminders
            fetchAndDisplayMedicineReminders(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void fetchAndDisplayHealthData(Connection connection) throws SQLException {
        String query = "SELECT * FROM health_data";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                double weight = resultSet.getDouble("weight");
                double height = resultSet.getDouble("height");
                int steps = resultSet.getInt("steps");
                int heartRate = resultSet.getInt("heart_rate");
                java.sql.Date date = resultSet.getDate("date");
                System.out.println("Health Data - User ID: " + userId + ", Weight: " + weight + ", Height: " + height +
                        ", Steps: " + steps + ", Heart Rate: " + heartRate + ", Date: " + date);
            }
        }
    }

    private static void fetchAndDisplayMedicineReminders(Connection connection) throws SQLException {
        String query = "SELECT * FROM medicine_reminders";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String medicineName = resultSet.getString("medicine_name");
                String dosage = resultSet.getString("dosage");
                String schedule = resultSet.getString("schedule");
                java.sql.Date startDate = resultSet.getDate("start_date");
                java.sql.Date endDate = resultSet.getDate("end_date");
                System.out.println("Medicine Reminder - User ID: " + userId + ", Medicine Name: " + medicineName +
                        ", Dosage: " + dosage + ", Schedule: " + schedule + ", Start Date: " + startDate +
                        ", End Date: " + endDate);
            }
        }
    }
}
