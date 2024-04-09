
// User file by Manny

import java.util.List;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isDoctor;

    // Constructor
    public User(int id, String firstName, String lastName, String email, String password, boolean isDoctor) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isDoctor = isDoctor;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDoctor() {
        return isDoctor;
    }

    public void setDoctor(boolean doctor) {
        isDoctor = doctor;
    }
    // Show user information
    public void displayUserInfo() {
        System.out.println("User ID: " + id);
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        System.out.println("Is Doctor: " + (isDoctor ? "Yes" : "No"));
        System.out.println();
    }
 
    // Testing
    public static void main(String[] args) {
    // Instantiate UserDaoExample to interact with the database
    UserDaoExample userDaoExample = new UserDaoExample();

    // Get all users from the database
    List<User> users = userDaoExample.getAllUsers();

    // Display information for all users
    for (User user : users) {
        System.out.println("User Information:");
        user.displayUserInfo();
    }
}
}