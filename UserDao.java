

// import org.mindrot.jbcrypt.BCrypt;
// import java.sql.*;
// 
// public class UserDao {
    // private final String url;
    // private final String username;
    // private final String password;
// 
    // public UserDao(String url, String username, String password) {
        // this.url = url;
        // this.username = username;
        // this.password = password;
    // }
// 
    // public boolean createUser(User user) {
        // String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
// 
        // try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // String query = "INSERT INTO users (first_name, last_name, email, password, is_doctor) VALUES (?, ?, ?, ?, ?)";
            // PreparedStatement statement = connection.prepareStatement(query);
            // statement.setString(1, user.getFirstName());
            // statement.setString(2, user.getLastName());
            // statement.setString(3, user.getEmail());
            // statement.setString(4, hashedPassword);
            // statement.setBoolean(5, user.isDoctor());
            // statement.executeUpdate();
            // return true;
        // } catch (SQLException e) {
            // e.printStackTrace();
            // return false;
        // }
    // }
// 
    // public User getUserById(int id) {
        // User user = null;
        // try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // String query = "SELECT * FROM users WHERE id = ?";
            // PreparedStatement statement = connection.prepareStatement(query);
            // statement.setInt(1, id);
            // ResultSet resultSet = statement.executeQuery();
            // if (resultSet.next()) {
                // user = extractUserFromResultSet(resultSet);
            // }
        // } catch (SQLException e) {
            // e.printStackTrace();
        // }
        // return user;
    // }
// 
    // public User getUserByEmail(String email) {
        // User user = null;
        // try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // String query = "SELECT * FROM users WHERE email = ?";
            // PreparedStatement statement = connection.prepareStatement(query);
            // statement.setString(1, email);
            // ResultSet resultSet = statement.executeQuery();
            // if (resultSet.next()) {
                // user = extractUserFromResultSet(resultSet);
            // }
        // } catch (SQLException e) {
            // e.printStackTrace();
        // }
        // return user;
    // }
// 
    // private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        // int id = resultSet.getInt("id");
        // String firstName = resultSet.getString("first_name");
        // String lastName = resultSet.getString("last_name");
        // String email = resultSet.getString("email");
        // String password = resultSet.getString("password");
        // boolean isDoctor = resultSet.getBoolean("is_doctor");
        // return new User(id, firstName, lastName, email, password, isDoctor);
    // }
// 
    // Additional methods for updateUser and deleteUser can be added here
// }
// 



// Health_Stack_Project_Term3_Final_sprint


// the UserDao class

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class UserDao {

    // public UserDao(String url, String username, String password) {
        // TODO Auto-generated constructor stub
    // }

    public static boolean createUser(User user) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Health_Stack_Project_Term3_Final_sprint", "postgres", "Zack2012")) {
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            String query = "INSERT INTO users (first_name, last_name, email, password, is_doctor) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, hashedPassword);
            preparedStatement.setBoolean(5, user.isDoctor());

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getUserById(int id) {
        User user = null;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Health_Stack_Project_Term3_Final_sprint", "postgres", "Zack2012")) {
            String query = "SELECT * FROM users WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getBoolean("is_doctor")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getUserByEmail(String email) {
        User user = null;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Health_Stack_Project_Term3_Final_sprint", "postgres", "Zack2012")) {
            String query = "SELECT * FROM users WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getBoolean("is_doctor")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean updateUser(User user) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Health_Stack_Project_Term3_Final_sprint", "postgres", "Zack2012")) {
            String query = "UPDATE users SET first_name = ?, last_name = ?, email = ?, password = ?, is_doctor = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setBoolean(5, user.isDoctor());
            preparedStatement.setInt(6, user.getId());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(int id) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Health_Stack_Project_Term3_Final_sprint", "postgres", "Zack2012")) {
            String query = "DELETE FROM users WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean verifyPassword(String email, String password) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Health_Stack_Project_Term3_Final_sprint", "postgres", "Zack2012")) {
            String query = "SELECT password FROM users WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String hashedPassword = resultSet.getString("password");
                return BCrypt.checkpw(password, hashedPassword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
}



