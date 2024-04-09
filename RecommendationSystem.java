
// RecommendationSystem.java - Manny
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RecommendationSystem {

    private Connection connection;

    public RecommendationSystem(Connection connection) {
        this.connection = connection;
    }


    public  Map<Integer, Set<String>> getAllRecommendations() {
        Map<Integer, Set<String>> allRecommendations = new HashMap<>();
        try {
            String query = "SELECT user_id, recommendation_text FROM recommendations";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String recommendation = resultSet.getString("recommendation_text");
                allRecommendations.computeIfAbsent(userId, k -> new HashSet<>()).add(recommendation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allRecommendations;
    }
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getCon()) {
            RecommendationSystem recommendationSystem = new RecommendationSystem(connection);
            Map<Integer, Set<String>> allRecommendations = recommendationSystem.getAllRecommendations();
            
            // Print out the recommendations
            for (Map.Entry<Integer, Set<String>> entry : allRecommendations.entrySet()) {
                int userId = entry.getKey();
                Set<String> userRecommendations = entry.getValue();
                System.out.println("Recommendations for User ID " + userId + ":");
                for (String recommendation : userRecommendations) {
                    System.out.println(recommendation);
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


//import java.util.ArrayList;
//import java.util.List;

//public class RecommendationSystem {
    //private static final int MIN_HEART_RATE = 60;
    //private static final int MIN_STEPS = 10000;

    //public List<String> generateRecommendations(HealthData healthData) {
        //List<String> recommendations = new ArrayList<>();

        // Analyze heart rate
        //int heartRate = healthData.getHeartRate();
        //if (heartRate < MIN_HEART_RATE) {
            //recommendations.add("Your heart rate is below avaetage. " +
                    //"I suggest you increase your physical activity to improve your heart rate.");
        //}

        // Analyze steps
        //int steps = healthData.getSteps();
        //if (steps < MIN_STEPS) {
            //recommendations.add("Daily count is important so pleae make sure you keep up with your steps. " +
                    //"Try to incorporate more walking or other physical activities into your daily routine.");
        //}

        // Add more health recommendations

        //return recommendations;
    //}
    
//}

