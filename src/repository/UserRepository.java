package repository;

import model.Sector;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
        public Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/controle_orcamento";
        Connection connection = DriverManager.getConnection(url, "root", "");

        return connection;
    }

    public void insert(User user) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("insert into users values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
        stmt.setNull(1, 1);
        stmt.setString(2, user.getName());
        stmt.setString(3, user.getUsername());
        stmt.setString(4, user.getPassword());
        stmt.setInt(5, user.getType());
        stmt.setInt(6, 1);
        stmt.setString(7, user.getCreated().toString());
        stmt.setString(8, user.getModified().toString());
        stmt.setInt(9, user.getSector().getId());


        int i = stmt.executeUpdate();
        System.out.println(i + " linhas inseridas");
        connection.close();
    }

    public List<User> search() throws SQLException, ClassNotFoundException {
        List<User> users = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from users");
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()){
            User user = new User();
            user.setId(resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setUsername(resultSet.getString(3));
            user.setPassword(resultSet.getString(4));
            user.setType(resultSet.getInt(5));
            user.setSector((Sector) resultSet.getObject(6));
            users.add(user);
        }
        connection.close();
        return users;
    }

    public List<User> searchByName (String name) throws SQLException, ClassNotFoundException {
        List<User> users = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from users WHERE name = ?");
        stmt.setString(1, name);
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()){
            User user = new User();
            user.setId(resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setUsername(resultSet.getString(3));
            user.setPassword(resultSet.getString(4));
            user.setType(resultSet.getInt(5));
            user.setSector((Sector) resultSet.getObject(6));
            users.add(user);
        }
        connection.close();
        return users;
    }

    public List<User> searchById (int id) throws SQLException, ClassNotFoundException {
        List<User> users = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from users WHERE id = ?");
        stmt.setInt(1, id);
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()){
            User user = new User();
            user.setId(resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setUsername(resultSet.getString(3));
            user.setPassword(resultSet.getString(4));
            user.setType(resultSet.getInt(5));
            user.setSector((Sector) resultSet.getObject(6));
            users.add(user);
        }
        connection.close();
        return users;
    }

    public Integer nextId () throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("select max(id) from users");
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            return resultSet.getInt(1) + 1;
        }
        return 1;
    }

    public void update (User user) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("update users SET password = ? WHERE id = ?");
        stmt.setString(1, user.getPassword());
        stmt.setInt(2, user.getId());

        int i = stmt.executeUpdate();
        System.out.println(i + " linhas atualizadas");
        connection.close();
    }

    public void delete (User user) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM users WHERE id = ?");
        stmt.setInt(1, user.getId());
        stmt.executeUpdate();
        connection.close();
    }

    public List<User> searchBySectorId(Integer id) throws SQLException, ClassNotFoundException {
        List<User> users = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE sectors_id=?");

        stmt.setInt(1, id);
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()){
            User user = new User();
            user.setId(resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setUsername(resultSet.getString(3));
            user.setPassword(resultSet.getString(4));
            user.setType(resultSet.getInt(5));
            user.setSector((Sector) resultSet.getObject(6));
            users.add(user);
        }
        connection.close();
        return users;
    }
}
