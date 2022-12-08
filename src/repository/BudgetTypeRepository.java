package repository;

import model.Budget_type;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BudgetTypeRepository {
        public Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/controle_orcamento";
        Connection connection = DriverManager.getConnection(url, "root", "");

        return connection;
    }

    public void insert(Budget_type budget) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("insert into budgets_type values(?, ?, ?, ?, ?)");
        stmt.setNull(1, 1);
        stmt.setString(2, budget.getName());
        stmt.setInt(3, budget.getActive());
        stmt.setString(4,  DateTimeFormatter.ISO_LOCAL_DATE.format(budget.getCreated()));
        stmt.setString(5, DateTimeFormatter.ISO_LOCAL_DATE.format(budget.getModified()));


        int i = stmt.executeUpdate();
        System.out.println(i + " linhas inseridas");
        connection.close();
    }

    public List<Budget_type> search() throws SQLException, ClassNotFoundException {
        List<Budget_type> budgets = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from budgets_type");
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()){
            Budget_type budget = new Budget_type();
            budget.setId(resultSet.getInt(1));
            budget.setName(resultSet.getString(2));
            budget.setActive(resultSet.getInt(3));
            budget.setCreated(resultSet.getDate(4).toLocalDate());
            budget.setModified(resultSet.getDate(5).toLocalDate());
            budgets.add(budget);
        }
        connection.close();
        return budgets;
    }

    public List<Budget_type> searchByName (String name) throws SQLException, ClassNotFoundException {
        List<Budget_type> budgets = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from budgets_type WHERE name = ?");
        stmt.setString(1, name);
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()){
            Budget_type budget = new Budget_type();
            budget.setId(resultSet.getInt(1));
            budget.setName(resultSet.getString(2));
            budget.setActive(resultSet.getInt(3));
            budget.setCreated(resultSet.getDate(4).toLocalDate());
            budget.setModified(resultSet.getDate(5).toLocalDate());
            budgets.add(budget);
        }
        connection.close();
        return budgets;
    }

    public List<Budget_type> searchById (int id) throws SQLException, ClassNotFoundException {
        List<Budget_type> budgets = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from budgets_type WHERE id = ?");
        stmt.setInt(1, id);
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()){
            Budget_type budget = new Budget_type();
            budget.setId(resultSet.getInt(1));
            budget.setName(resultSet.getString(2));
            budget.setActive(resultSet.getInt(3));
            budget.setCreated(resultSet.getDate(4).toLocalDate());
            budget.setModified(resultSet.getDate(5).toLocalDate());
            budgets.add(budget);
        }
        connection.close();
        return budgets;
    }

    public Integer nextId () throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("select max(id) from budgets_type");
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            return resultSet.getInt(1) + 1;
        }
        return 1;
    }

    public void update (Budget_type budget) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("update budgets_type SET name = ?, password = ?, modified = ? WHERE id = ?");
        stmt.setString(1, budget.getName());
        stmt.setInt(2, budget.getActive());
        stmt.setString(3, LocalDateTime.now().toString());

        int i = stmt.executeUpdate();
        System.out.println(i + " linhas atualizadas");

        connection.close();
    }

    public void delete (Budget_type budget) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM budgets WHERE id = ?");
        stmt.setInt(1, budget.getId());
        stmt.executeUpdate();
        connection.close();
    }
}
