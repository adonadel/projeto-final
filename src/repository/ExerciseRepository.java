package repository;

import model.Exercise;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ExerciseRepository {
        public Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/controle_orcamento";
        Connection connection = DriverManager.getConnection(url, "root", "");

        return connection;
    }

    public void insert(Exercise exercise) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("insert into exercises values(?, ?, ?, ?, ?, ?, ?)");
        stmt.setNull(1, 1);
        stmt.setInt(2, exercise.getYear());
        stmt.setInt(3, exercise.getStatus());
        stmt.setInt(4, exercise.getActive());
        stmt.setString(5,  DateTimeFormatter.ISO_LOCAL_DATE.format(exercise.getCreated()));
        stmt.setString(6, DateTimeFormatter.ISO_LOCAL_DATE.format(exercise.getModified()));
        stmt.setInt(7, exercise.getBudget().getId());


        int i = stmt.executeUpdate();
        System.out.println(i + " linhas inseridas");
        connection.close();
    }

    public List<Exercise> search() throws SQLException, ClassNotFoundException {
        List<Exercise> exercises = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from exercises");
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()){
            Exercise exercise = new Exercise();
            exercise.setId(resultSet.getInt(1));
            exercise.setYear(resultSet.getInt(2));
            exercise.setActive(resultSet.getInt(3));
            exercise.setCreated(resultSet.getDate(4).toLocalDate());
            exercise.setModified(resultSet.getDate(5).toLocalDate());
            exercises.add(exercise);
        }
        connection.close();
        return exercises;
    }

        public List<Exercise> searchByName (String name) throws SQLException, ClassNotFoundException {
        List<Exercise> exercises = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from exercises WHERE name = ?");
        stmt.setString(1, name);
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()){
            Exercise exercise = new Exercise();
            exercise.setId(resultSet.getInt(1));
            exercise.setYear(resultSet.getInt(2));
            exercise.setActive(resultSet.getInt(3));
            exercise.setCreated(resultSet.getDate(4).toLocalDate());
            exercise.setModified(resultSet.getDate(5).toLocalDate());
            exercises.add(exercise);
        }
        connection.close();
        return exercises;
    }

    public List<Exercise> searchById (int id) throws SQLException, ClassNotFoundException {
        List<Exercise> exercises = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from exercises WHERE id = ?");
        stmt.setInt(1, id);
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()){
            Exercise exercise = new Exercise();
            exercise.setId(resultSet.getInt(1));
            exercise.setYear(resultSet.getInt(2));
            exercise.setActive(resultSet.getInt(3));
            exercise.setCreated(resultSet.getDate(4).toLocalDate());
            exercise.setModified(resultSet.getDate(5).toLocalDate());
            exercises.add(exercise);
        }
        connection.close();
        return exercises;
    }

    public Integer nextId () throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("select max(id) from exercises");
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            return resultSet.getInt(1) + 1;
        }
        return 1;
    }

    public void update (Exercise exercise) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("update exercises SET year = ? WHERE id = ?");
        stmt.setInt(1, exercise.getYear());
        stmt.setInt(2, exercise.getId());

        int i = stmt.executeUpdate();
        System.out.println(i + " linhas atualizadas");
        connection.close();
    }

    public void delete (Exercise exercise) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM exercises WHERE id = ?");
        stmt.setInt(1, exercise.getId());
        stmt.executeUpdate();
        connection.close();
    }
}
