package repository;

import model.BudgetType;

import java.sql.*;
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

    public void insert(BudgetType budget) throws SQLException, ClassNotFoundException {
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

    public List<BudgetType> search() throws SQLException, ClassNotFoundException {
        List<BudgetType> budgetsType = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from budgets_type");
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()){
            BudgetType budgetType = new BudgetType();
            budgetType.setId(resultSet.getInt(1));
            budgetType.setName(resultSet.getString(2));
            budgetType.setActive(resultSet.getInt(3));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime created = LocalDateTime.parse(resultSet.getString(4), formatter);
            LocalDateTime modified = LocalDateTime.parse(resultSet.getString(5), formatter);
            budgetType.setCreated(LocalDateTime.from(created));
            budgetType.setModified(LocalDateTime.from(modified));
            budgetsType.add(budgetType);
        }
        connection.close();
        return budgetsType;
    }

    public List<BudgetType> searchByName (String name) throws SQLException, ClassNotFoundException {
        List<BudgetType> budgets = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from budgets_type WHERE name = ?");
        stmt.setString(1, name);
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()){
            BudgetType budget = new BudgetType();
            budget.setId(resultSet.getInt(1));
            budget.setName(resultSet.getString(2));
            budget.setActive(resultSet.getInt(3));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime created = LocalDateTime.parse(resultSet.getString(4), formatter);
            LocalDateTime modified = LocalDateTime.parse(resultSet.getString(5), formatter);
            budget.setCreated(LocalDateTime.from(created));
            budget.setModified(LocalDateTime.from(modified));
            budgets.add(budget);
        }
        connection.close();
        return budgets;
    }

    public List<BudgetType> searchById (int id) throws SQLException, ClassNotFoundException {
        List<BudgetType> budgets = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from budgets_type WHERE id = ?");
        stmt.setInt(1, id);
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()){
            BudgetType budget = new BudgetType();
            budget.setId(resultSet.getInt(1));
            budget.setName(resultSet.getString(2));
            budget.setActive(resultSet.getInt(3));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime created = LocalDateTime.parse(resultSet.getString(4), formatter);
            LocalDateTime modified = LocalDateTime.parse(resultSet.getString(5), formatter);
            budget.setCreated(LocalDateTime.from(created));
            budget.setModified(LocalDateTime.from(modified));
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

    public void update (BudgetType budget) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("update budgets_type SET name = ?, active = ?, modified = ? WHERE id = ?");
        stmt.setString(1, budget.getName());
        stmt.setInt(2, budget.getActive());
        stmt.setString(3, budget.getModified().toString());
        stmt.setInt(4, budget.getId());

        int i = stmt.executeUpdate();
        System.out.println(i + " linhas atualizadas");

        connection.close();
    }

    public void delete (BudgetType budgetType) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM budgets_type WHERE id = ?");
        stmt.setInt(1, budgetType.getId());
        stmt.executeUpdate();

        int i = stmt.executeUpdate();
        System.out.println("1 linhas removidas");
        connection.close();
    }
}
