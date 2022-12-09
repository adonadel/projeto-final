package repository;


import model.Budget;
import model.BudgetType;
import model.Sector;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BudgetRepository {
        public Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/controle_orcamento";
        Connection connection = DriverManager.getConnection(url, "root", "");

        return connection;
    }

    public void insert(Budget budget) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("insert into budgets values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        stmt.setNull(1, 1);
        stmt.setString(2, budget.getName());
        stmt.setString(3, budget.getItem());
        stmt.setInt(4, budget.getQnt());
        stmt.setDouble(5, budget.getUntVal());
        stmt.setInt(6, budget.getStatus());
        stmt.setInt(7,budget.getActive());
        stmt.setString(8, DateTimeFormatter.ISO_LOCAL_DATE.format(budget.getCreated()));
        stmt.setString(9, DateTimeFormatter.ISO_LOCAL_DATE.format(budget.getModified()));
        stmt.setInt(10, budget.getSector().getId());
        stmt.setInt(11, budget.getBudgetType().getId());


        int i = stmt.executeUpdate();
        System.out.println(i + " linhas inseridas");
        connection.close();
    }

    public List<Budget> search() throws SQLException, ClassNotFoundException {
        List<Budget> budgets = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from budgets");
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()){
            Budget budget = new Budget();
            budget.setId(resultSet.getInt(1));
            budget.setName(resultSet.getString(2));
            budget.setItem(resultSet.getString(3));
            budget.setQnt(resultSet.getInt(4));
            budget.setUntVal(resultSet.getDouble(5));
            budget.setStatus(resultSet.getInt(6));
            budget.setActive(resultSet.getInt(7));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime created = LocalDateTime.parse(resultSet.getString(8), formatter);
            LocalDateTime modified = LocalDateTime.parse(resultSet.getString(9), formatter);
            budget.setCreated(LocalDateTime.from(created));
            budget.setModified(LocalDateTime.from(modified));
            Sector sector = getSectorDAO().searchById(resultSet.getInt(10));
            budget.setSector(sector);
            BudgetType budgetType = getBudgetTypeDAO().searchById(resultSet.getInt(11));
            budget.setBudgetType(budgetType);
            budgets.add(budget);
        }
        connection.close();
        return budgets;
    }

        public List<Budget> searchByName (String name) throws SQLException, ClassNotFoundException {
        List<Budget> budgets = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from budgets WHERE name = ?");
        stmt.setString(1, name);
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()){
            Budget budget = new Budget();
            budget.setId(resultSet.getInt(1));
            budget.setName(resultSet.getString(2));
            budget.setItem(resultSet.getString(3));
            budget.setQnt(resultSet.getInt(4));
            budget.setUntVal(resultSet.getDouble(5));
            budget.setStatus(resultSet.getInt(6));
            budget.setActive(resultSet.getInt(7));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime created = LocalDateTime.parse(resultSet.getString(8), formatter);
            LocalDateTime modified = LocalDateTime.parse(resultSet.getString(9), formatter);
            budget.setCreated(LocalDateTime.from(created));
            budget.setModified(LocalDateTime.from(modified));
            Sector sector = getSectorDAO().searchById(resultSet.getInt(10));
            budget.setSector(sector);
            BudgetType budgetType = getBudgetTypeDAO().searchById(resultSet.getInt(11));
            budget.setBudgetType(budgetType);
            budgets.add(budget);
        }
        connection.close();
        return budgets;
    }

    public List<Budget> searchById (int id) throws SQLException, ClassNotFoundException {
        List<Budget> budgets = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from budgets WHERE id = ?");
        stmt.setInt(1, id);
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()){
            Budget budget = new Budget();
            budget.setId(resultSet.getInt(1));
            budget.setName(resultSet.getString(2));
            budget.setItem(resultSet.getString(3));
            budget.setQnt(resultSet.getInt(4));
            budget.setUntVal(resultSet.getDouble(5));
            budget.setStatus(resultSet.getInt(6));
            budget.setActive(resultSet.getInt(7));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime created = LocalDateTime.parse(resultSet.getString(8), formatter);
            LocalDateTime modified = LocalDateTime.parse(resultSet.getString(9), formatter);
            budget.setCreated(LocalDateTime.from(created));
            budget.setModified(LocalDateTime.from(modified));
            Sector sector = getSectorDAO().searchById(resultSet.getInt(10));
            budget.setSector(sector);
            BudgetType budgetType = getBudgetTypeDAO().searchById(resultSet.getInt(11));
            budget.setBudgetType(budgetType);
            budgets.add(budget);
        }
        connection.close();
        return budgets;
    }

    public Integer nextId () throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("select max(id) from budgets");
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            return resultSet.getInt(1) + 1;
        }
        return 1;
    }

    public void update (Budget budget) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("update budgets SET name = ?, item = ?, qnt = ?, unt_val = ?, status= ?, active = ?, modified = ? WHERE id = ?");
        stmt.setString(1, budget.getName());
        stmt.setString(2, budget.getItem());
        stmt.setInt(3, budget.getQnt());
        stmt.setDouble(4, budget.getUntVal());
        stmt.setInt(5, budget.getStatus());
        stmt.setInt(6, budget.getActive());
        stmt.setString(7, budget.getModified().toString());
        stmt.setInt(8, budget.getId());

        int i = stmt.executeUpdate();
        System.out.println(i + " linhas atualizadas");
        connection.close();
    }

    public void delete (Budget budget) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM budgets WHERE id = ?");
        stmt.setInt(1, budget.getId());
        stmt.executeUpdate();

        int i = stmt.executeUpdate();
        System.out.println("1 linha removida");
        connection.close();
    }

    public static SectorDAO getSectorDAO() {
        SectorDAO sectorDAO = new SectorDAO();
        return sectorDAO;
    }

    public static BudgetTypeDAO getBudgetTypeDAO() {
        BudgetTypeDAO budgetTypeDAO = new BudgetTypeDAO();
        return budgetTypeDAO;
    }
}
