package peaksoft.dao;

import peaksoft.model.User;

import java.util.List;

public interface UserDao {

    public static void createUsersTable(){
        String sql = "CREATE TABLE IF NOT EXISTS Users(" +
                "id LONG PRIMARY KEY," +
                "name VARCHAR(50) NOT NULL," +
                "lastName VARCHAR(50) NOT NULL," +
                "age BYTE)"
        try (Connection connect = Util.connect();;
        Statement statement = connect.createStatement()){
            statement.executeUpdate(sql);
            System.out.println("Table successfully created" + Users);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    void dropUsersTable();

    void saveUser(String name, String lastName, byte age);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
}
