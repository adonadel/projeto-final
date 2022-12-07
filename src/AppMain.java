import model.Sector;
import model.User;
import repository.UserDAO;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AppMain {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Connection connection = getConnection();

        Sector sector = new Sector();

        PreparedStatement stmt = connection.prepareStatement("insert into sectors values(?, ?, ?, ?, ?)");
        stmt.setNull(1, 1);
        stmt.setString(2, "João");
        stmt.setInt(3, 1);
        stmt.setString(4, "2022-10-10");
        stmt.setString(5, "2022-10-10");

        int i = stmt.executeUpdate();
        System.out.println(i + " linhas inseridas");
        connection.close();

        User user = chamaCadastroPessoa();
        cadastroUser();
        UserDAO.save(user);
    }

    private static User chamaCadastroPessoa() throws SQLException, ClassNotFoundException {
        User user;
        user = cadastroUser();
        return user;
        //da pra simplicar por return cadastroUser(); eu acho, ou ir direto pro outro se for só isso aqui
    }

    private static User cadastroUser() {
        String name = "Lucas";
        String username = "lucas";
        String password = "123";
        String type = "1";
        String created = String.valueOf(LocalDateTime.now());
        String modified = String.valueOf(LocalDateTime.now());
        Sector sector = new Sector();
        //---------------------

        Connection connection = null;
        try {
            connection = getConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("select * from sectors WHERE id = 1;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet resultSet = null;
        try {
            resultSet = stmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        while (true){
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                sector.setId(resultSet.getInt(1));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                sector.setName(resultSet.getString(2));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                sector.setActive(resultSet.getInt(3));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                String[] createdd;
                createdd = resultSet.getString(4).split("-", 0);
                String[] aux;
                aux = createdd[2].split(" ", 0);
                String[] times;
                times = aux[1].split(":", 0);
                sector.setCreated(LocalDateTime.of(Integer.parseInt(createdd[0]), Integer.parseInt(createdd[1]), Integer.parseInt(aux[0]), Integer.parseInt(times[0]), Integer.parseInt(times[1]), Integer.parseInt(times[2])));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                String[] modifedd;
                modifedd = resultSet.getString(5).split("-", 0);
                String[] auxModified;
                auxModified = modifedd[2].split(" ", 0);
                String[] timesModified;
                timesModified = auxModified[1].split(":", 0);
                sector.setCreated(LocalDateTime.of(Integer.parseInt(modifedd[0]), Integer.parseInt(modifedd[1]), Integer.parseInt(auxModified[0]), Integer.parseInt(timesModified[0]), Integer.parseInt(timesModified[1]), Integer.parseInt(timesModified[2])));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        user.setType(Integer.valueOf(type));
        user.setSector(sector);
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());

        return user;

    }
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/controle_orcamento";
        Connection connection = DriverManager.getConnection(url, "root", "");
        return connection;
    }

    public static UserDAO getUserDAO() {
        UserDAO userDAO = new UserDAO();
        return userDAO;
    }
}
