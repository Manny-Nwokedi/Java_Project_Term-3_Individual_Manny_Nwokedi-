
import java.util.ArrayList;
import java.util.List;

public class RecommendationSystem {
    private static final int MIN_HEART_RATE = 60;
    private static final int MIN_STEPS = 10000;

    public List<String> generateRecommendations(HealthData healthData) {
        List<String> recommendations = new ArrayList<>();

        // Analyze heart rate
        int heartRate = healthData.getHeartRate();
        if (heartRate < MIN_HEART_RATE) {
            recommendations.add("Your heart rate is below avaetage. " +
                    "I suggest you increase your physical activity to improve your heart rate.");
        }

        // Analyze steps
        int steps = healthData.getSteps();
        if (steps < MIN_STEPS) {
            recommendations.add("Daily count is important so pleae make sure you keep up with your steps. " +
                    "Try to incorporate more walking or other physical activities into your daily routine.");
        }

        // Add more health recommendations

        return recommendations;
    }
    
}
