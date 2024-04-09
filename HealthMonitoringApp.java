
import java.util.List;

public class HealthMonitoringApp {

    public static void main(String[] args) {
        // Create a sample user
        User user1 = new User(5, "Ainee", "Malik", "qmalik@gmail.com", "gug", false);

        // Register the user
        UserDaoExample.createUser(user1);

        // Test login functionality
        testLoginUser();

        // Test doctor portal functionality
        testDoctorPortal();
    }

    
    
    public static boolean loginUser(String email, String password) {
        // Implement method to login user.
        User user = UserDaoExample.getUserByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            return true;
        }

        return false;
    }

    public static void testDoctorPortal() {
        int doctorId = 1;

        // Create an instance of DoctorPortalDao
        DoctorPortalDao doctorPortalDao = new DoctorPortalDao();

        // Fetch the doctor by ID
        Doctor doctor = doctorPortalDao.getDoctorById(doctorId);
        System.out.println("Doctor: " + doctor);

        // Fetch patients associated with the doctor
        List<User> patients = doctorPortalDao.getPatientsByDoctorId(doctorId);
        System.out.println("Patients associated with Doctor: " + patients);

        // Fetch health data for the patient
        int patientId = 1; // Assuming patient ID
        HealthData healthData = doctorPortalDao.getHealthDataByPatientId(patientId);
        System.out.println("Health Data for Patient: " + healthData);
    }

    public static void testLoginUser() {
        // Replace the email and password with valid credentials from your database
        String userEmail = "qmalik@gmail.com";
        String userPassword = "gug";

        boolean loginSuccess = loginUser(userEmail, userPassword);

        if (loginSuccess) {
            System.out.println("Login Successful");
        } else {
            System.out.println("Incorrect email or password. Please try again.");
        }
    }
}