package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable(){
        String sql = "CREATE TABLE IF NOT EXISTS Users(" +
                "id INT PRIMARY KEY," +
                "name VARCHAR(50) NOT NULL," +
                "lastName VARCHAR(50) NOT NULL," +
                "age INT)";
        try (Connection connect = Util.connect();
             Statement statement = connect.createStatement()){
            statement.executeUpdate(sql);
            System.out.println("Table successfully created");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        String DROP_TABLE = "DROP TABLE Users";
        try(
                Connection connect = Util.connect();
                Statement statement = connect.createStatement()){

            statement.executeUpdate(DROP_TABLE);
            System.out.println("Dropping table");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }




    public void saveUser(String name, String lastName, byte age){
        String SQL = "INSERT INTO Users(name, lastName, age) VALUES (?, ?, ?)";
        try (
                Connection connect = Util.connect();
                PreparedStatement statement  = connect.prepareStatement(SQL)){
            statement.setString(1,name);
            statement.setString(2,lastName);
            statement.setInt(3,age);
            statement.executeUpdate();
            System.out.println("Information has been added successfully " + name + "!");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String REMOVE_USER_SQL = "DELETE FROM Users WHERE id = ?";
        try(
                Connection connect = Util.connect();
                PreparedStatement statement = connect.prepareStatement(REMOVE_USER_SQL)){
            statement.setInt(1, (int) id);
            statement.executeUpdate();
            System.out.println("Delete by id");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        String SQL = "SELECT * FROM Users";
        List<User> users = new ArrayList<>();
        try (
                Connection connect = Util.connect();
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL)) {
            while (resultSet.next()) {
                User user = new User();
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                int age = resultSet.getInt("age");
                System.out.println(id + " " + name + " " + lastName + " " + age);
                user.setId((long) id);
                user.setName(name);
                user.setLastName(lastName);
                user.setAge((byte)age);
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    public void cleanUsersTable() {

        String CLEAN_SQL = "TRUNCATE TABLE Users";
        try(
                Connection connect = Util.connect();
                Statement statement = connect.createStatement()){
            statement.executeUpdate(CLEAN_SQL);
            System.out.println("Clean table done!");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}