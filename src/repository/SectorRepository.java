package repository;

import model.Sector;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class SectorRepository {
        public Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/controle_orcamento";
        Connection connection = DriverManager.getConnection(url, "root", "");

        return connection;
    }

    public void insert(Sector sector) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("insert into sectors values(?, ?, ?, ?, ?)");
        stmt.setNull(1, 1);
        stmt.setString(2, sector.getName());
        stmt.setInt(3, sector.getActive());
        stmt.setString(4, sector.getCreated().toString());
        stmt.setString(5, sector.getModified().toString());


        int i = stmt.executeUpdate();
        System.out.println(i + " linhas inseridas");
        connection.close();
    }

    public List<Sector> search() throws SQLException, ClassNotFoundException {
        List<Sector> sectors = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from sectors");
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()){
            Sector sector = new Sector();
            sector.setId(resultSet.getInt(1));
            sector.setName(resultSet.getString(2));
            sector.setActive(resultSet.getInt(3));
            sectors.add(sector);
        }
        connection.close();
        return sectors;
    }

    public List<Sector> buscaPorId (int id) throws SQLException, ClassNotFoundException {
        List<Sector> sectors = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from sectors WHERE id = ?");
        stmt.setInt(1, id);
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()){
            Sector sector = new Sector();
            sector.setId(resultSet.getInt(1));
            sector.setName(resultSet.getString(2));
            sector.setActive(resultSet.getInt(3));
            sectors.add(sector);
        }
        connection.close();
        return sectors;
    }

    public Integer nextId () throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("select max(id) from sectors");
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            return resultSet.getInt(1) + 1;
        }
        return 1;
    }

    public void update (Sector sector) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("update sectors SET password = ? WHERE id = ?");
        stmt.setString(1, sector.getName());
        stmt.setInt(2, sector.getId());

        int i = stmt.executeUpdate();
        System.out.println(i + " linhas atualizadas");
        connection.close();
    }

    public void delete (Sector sector) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM sectors WHERE id = ?");
        stmt.setInt(1, sector.getId());
        stmt.executeUpdate();
        connection.close();
    }
}
