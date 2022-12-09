package repository;

import model.Sector;
import model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        stmt.setString(7,  DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(user.getCreated()));
        stmt.setString(8, DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(user.getModified()));
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
            Sector sector = getSectorDAO().searchById(resultSet.getInt(6));
            user.setSector(sector);
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
            Sector sector = getSectorDAO().searchById(resultSet.getInt(6));
            user.setSector(sector);
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
            Sector sector = getSectorDAO().searchById(resultSet.getInt(6));
            user.setSector(sector);
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

        PreparedStatement stmt = connection.prepareStatement("update users SET name = ?,password = ?, type = ?, modified = ?, sectors_id = ? WHERE id = ?");
        ResultSet resultSet = stmt.executeQuery();

        stmt.setString(1, user.getName());
        stmt.setString(2, user.getPassword());
        stmt.setInt(3, user.getType());
        stmt.setString(4, user.getModified().toString());
        stmt.setInt(5, user.getSector().getId());
        stmt.setInt(6, user.getId());

        int i = stmt.executeUpdate();
        System.out.println(i + " linhas atualizadas");
        connection.close();
    }

    public void delete (User user) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM users WHERE id = ?");
        stmt.setInt(1, user.getId());
        stmt.executeUpdate();

        int i = stmt.executeUpdate();
        System.out.println(i + " linhas removidas");
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
            user.setActive(resultSet.getInt(6));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime created = LocalDateTime.parse(resultSet.getString(7), formatter);
            LocalDateTime modified = LocalDateTime.parse(resultSet.getString(8), formatter);
            user.setCreated(LocalDateTime.from(created));
            user.setModified(LocalDateTime.from(modified));
            Sector sector = getSectorDAO().searchById(resultSet.getInt(9));
            user.setSector(sector);
            users.add(user);
        }
        connection.close();
        return users;
    }

    public User searchByUsernamePassword(String username, String password) throws SQLException, ClassNotFoundException {
            User user = new User();
            Connection connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setUsername(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
                user.setType(resultSet.getInt(5));
                user.setActive(resultSet.getInt(6));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime created = LocalDateTime.parse(resultSet.getString(7), formatter);
                LocalDateTime modified = LocalDateTime.parse(resultSet.getString(8), formatter);
                user.setCreated(LocalDateTime.from(created));
                user.setModified(LocalDateTime.from(modified));
                Sector sector = getSectorDAO().searchById(resultSet.getInt(9));
                user.setSector(sector);
            }
            connection.close();
            return user;
    }

    public static SectorDAO getSectorDAO() {
        SectorDAO sectorDAO = new SectorDAO();
        return sectorDAO;
    }
}
