import model.Sector;
import model.User;
import repository.UserDAO;
import repository.SectorsDAO;

import javax.swing.*;

import static java.lang.Integer.parseInt;

import java.sql.*;
import java.time.LocalDateTime;

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

        initAll();
        callLogin();
    }


    private static void initAll() {
        SectorsDAO.initSectors();

    }

    private static void callLogin(){
        String login = JOptionPane.showInputDialog(null, "Informe o login do usuário ",
                "Login Sistema", 3);
        String senha = JOptionPane.showInputDialog(null, "Informe a senha do usuário " + login,
                "Login Sistema", 3);
        callMenuEntity();
    }

    private static void callMenuEntity(){

        String[] optionsMenuEntity = {"Sair", "Usuários", "Setores", "Exercícios", "Orçamentos", "Tipos Orçamentos"};
        int menuEntity = JOptionPane.showOptionDialog(null, "Selecione uma entidade para mais ações",
                "Menu entidades (ADMIN)",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionsMenuEntity, optionsMenuEntity[0]);
        switch (menuEntity) {
            case 0:
                callLogin();
                break;
            case 1:
                callMenuUsers();
                break;
            case 2:
                callMenuSectors();
                break;
            case 3:
                callMenuExercises();
                break;
            case 4:
                callMenuBudgets();
                break;
            case 5:
                callMenuTypesBudgets();
                break;
        }
    }

    private static void callMenuUsers(){

        // chamando select de nome de setores
        Object[] namesSectors = SectorsDAO.findSectorsInArrayWithId();
        Object nameSector = JOptionPane.showInputDialog(null, "Selecione o setor: ",
                "Menu Usuários", JOptionPane.QUESTION_MESSAGE, null, namesSectors, namesSectors[0]);
        String[] split = nameSector.toString().split(" - ");
        int sectorId = parseInt(split[0]);
        Sectors sectors = SectorsDAO.findSectorsById(sectorId);
        // chamando select de nome de setores

        String[] optionsMenuUsers = {"Voltar", "Relatório", "Excluir", "Editar", "Novo"};
        int menuUsers = JOptionPane.showOptionDialog(null, "Selecione uma ação para Usuários do setor (" + nameSector + ")",
                "Menu Usuários (ADMIN)",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionsMenuUsers, optionsMenuUsers[0]);

        switch (menuUsers) {
            case 0:
                callMenuEntity();
                //RETORNA/ABRE PARA MENUS DE ENTIDADES
                break;
            case 1:
                System.out.println(" CHAMAR RELATÓRIO USUÁRIOS");
                break;
            case 2:
                System.out.println(" CHAMAR EXCLUIR USUÁRIOS");
                break;
            case 3:
                System.out.println(" CHAMAR EDITAR USUÁRIOS");
                break;
            case 4:
                System.out.println(" CHAMAR NOVO USUÁRIOS");
                break;
        }

    }

    private static void callMenuSectors(){
        String[] optionsMenuSectors = {"Voltar", "Relatório", "Excluir", "Editar", "Novo"};
        int menuSectors = JOptionPane.showOptionDialog(null, "Selecione uma ação para Setores",
                "Menu Setores (ADMIN)",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionsMenuSectors, optionsMenuSectors[0]);

        switch (menuSectors) {
            case 0:
                callMenuEntity();
                //RETORNA/ABRE PARA MENUS DE ENTIDADES
                break;
            case 1:
                System.out.println(" CHAMAR RELATÓRIO SETORES");
                break;
            case 2:
                System.out.println(" CHAMAR EXCLUIR SETORES");
                break;
            case 3:
                System.out.println(" CHAMAR EDITAR SETORES");
                break;
            case 4:
                System.out.println(" CHAMAR NOVO SETORES");
                break;
        }

    }

    private static void callMenuExercises(){
        String[] optionsMenuExercises = {"Voltar", "Relatório", "Excluir", "Editar", "Novo"};
        int menuExercises = JOptionPane.showOptionDialog(null, "Selecione uma ação para Exercícios",
                "Menu Exercícios (ADMIN)",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionsMenuExercises, optionsMenuExercises[0]);

        switch (menuExercises) {
            case 0:
                callMenuEntity();
                //RETORNA/ABRE PARA MENUS DE ENTIDADES
                break;
            case 1:
                System.out.println(" CHAMAR RELATÓRIO EXERCÍCIOS");
                break;
            case 2:
                System.out.println(" CHAMAR EXCLUIR EXERCÍCIOS");
                break;
            case 3:
                System.out.println(" CHAMAR EDITAR EXERCÍCIOS");
                break;
            case 4:
                System.out.println(" CHAMAR NOVO EXERCÍCIOS");
                break;
        }

    }


    private static void callMenuTypesBudgets(){
        String[] optionsMenuTypesBudgets = {"Voltar", "Relatório", "Excluir", "Editar", "Novo"};
        int menuTypesBudgets = JOptionPane.showOptionDialog(null, "Selecione uma ação para Tipos Orçamentos",
                "Menu Tipos Orçamentos (ADMIN)",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionsMenuTypesBudgets, optionsMenuTypesBudgets[0]);

        switch (menuTypesBudgets) {
            case 0:
                callMenuEntity();import model.Sector;
import model.User;
import repository.UserDAO;
import repository.SectorsDAO;

import javax.swing.*;

import static java.lang.Integer.parseInt;

import java.sql.*;
import java.time.LocalDateTime;

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

        initAll();
        callLogin();
    }


    private static void initAll() {
        SectorsDAO.initSectors();

    }

    private static void callLogin(){
        String login = JOptionPane.showInputDialog(null, "Informe o login do usuário ",
                "Login Sistema", 3);
        String senha = JOptionPane.showInputDialog(null, "Informe a senha do usuário " + login,
                "Login Sistema", 3);
        callMenuEntity();
    }

    private static void callMenuEntity(){

        String[] optionsMenuEntity = {"Sair", "Usuários", "Setores", "Exercícios", "Orçamentos", "Tipos Orçamentos"};
        int menuEntity = JOptionPane.showOptionDialog(null, "Selecione uma entidade para mais ações",
                "Menu entidades (ADMIN)",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionsMenuEntity, optionsMenuEntity[0]);
        switch (menuEntity) {
            case 0:
                callLogin();
                break;
            case 1:
                callMenuUsers();
                break;
            case 2:
                callMenuSectors();
                break;
            case 3:
                callMenuExercises();
                break;
            case 4:
                callMenuBudgets();
                break;
            case 5:
                callMenuTypesBudgets();
                break;
        }
    }

    private static void callMenuUsers(){

        // chamando select de nome de setores
        Object[] namesSectors = SectorsDAO.findSectorsInArrayWithId();
        Object nameSector = JOptionPane.showInputDialog(null, "Selecione o setor: ",
                "Menu Usuários", JOptionPane.QUESTION_MESSAGE, null, namesSectors, namesSectors[0]);
        String[] split = nameSector.toString().split(" - ");
        int sectorId = parseInt(split[0]);
        Sectors sectors = SectorsDAO.findSectorsById(sectorId);
        // chamando select de nome de setores

        String[] optionsMenuUsers = {"Voltar", "Relatório", "Excluir", "Editar", "Novo"};
        int menuUsers = JOptionPane.showOptionDialog(null, "Selecione uma ação para Usuários do setor (" + nameSector + ")",
                "Menu Usuários (ADMIN)",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionsMenuUsers, optionsMenuUsers[0]);

        switch (menuUsers) {
            case 0:
                callMenuEntity();
                //RETORNA/ABRE PARA MENUS DE ENTIDADES
                break;
            case 1:
                System.out.println(" CHAMAR RELATÓRIO USUÁRIOS");
                break;
            case 2:
                System.out.println(" CHAMAR EXCLUIR USUÁRIOS");
                break;
            case 3:
                System.out.println(" CHAMAR EDITAR USUÁRIOS");
                break;
            case 4:
                System.out.println(" CHAMAR NOVO USUÁRIOS");
                break;
        }

    }

    private static void callMenuSectors(){
        String[] optionsMenuSectors = {"Voltar", "Relatório", "Excluir", "Editar", "Novo"};
        int menuSectors = JOptionPane.showOptionDialog(null, "Selecione uma ação para Setores",
                "Menu Setores (ADMIN)",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionsMenuSectors, optionsMenuSectors[0]);

        switch (menuSectors) {
            case 0:
                callMenuEntity();
                //RETORNA/ABRE PARA MENUS DE ENTIDADES
                break;
            case 1:
                System.out.println(" CHAMAR RELATÓRIO SETORES");
                break;
            case 2:
                System.out.println(" CHAMAR EXCLUIR SETORES");
                break;
            case 3:
                System.out.println(" CHAMAR EDITAR SETORES");
                break;
            case 4:
                System.out.println(" CHAMAR NOVO SETORES");
                break;
        }

    }

    private static void callMenuExercises(){
        String[] optionsMenuExercises = {"Voltar", "Relatório", "Excluir", "Editar", "Novo"};
        int menuExercises = JOptionPane.showOptionDialog(null, "Selecione uma ação para Exercícios",
                "Menu Exercícios (ADMIN)",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionsMenuExercises, optionsMenuExercises[0]);

        switch (menuExercises) {
            case 0:
                callMenuEntity();
                //RETORNA/ABRE PARA MENUS DE ENTIDADES
                break;
            case 1:
                System.out.println(" CHAMAR RELATÓRIO EXERCÍCIOS");
                break;
            case 2:
                System.out.println(" CHAMAR EXCLUIR EXERCÍCIOS");
                break;
            case 3:
                System.out.println(" CHAMAR EDITAR EXERCÍCIOS");
                break;
            case 4:
                System.out.println(" CHAMAR NOVO EXERCÍCIOS");
                break;
        }

    }


    private static void callMenuTypesBudgets(){
        String[] optionsMenuTypesBudgets = {"Voltar", "Relatório", "Excluir", "Editar", "Novo"};
        int menuTypesBudgets = JOptionPane.showOptionDialog(null, "Selecione uma ação para Tipos Orçamentos",
                "Menu Tipos Orçamentos (ADMIN)",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionsMenuTypesBudgets, optionsMenuTypesBudgets[0]);

        switch (menuTypesBudgets) {
            case 0:
                callMenuEntity();
                //RETORNA/ABRE PARA MENUS DE ENTIDADES
                break;
            case 1:
                System.out.println(" CHAMAR RELATÓRIO TIPOS ORÇAMENTOS");
                break;
            case 2:
                System.out.println(" CHAMAR EXCLUIR TIPOS ORÇAMENTOS");
                break;
            case 3:
                System.out.println(" CHAMAR EDITAR TIPOS ORÇAMENTOS");
                break;
            case 4:
                System.out.println(" CHAMAR NOVO TIPOS ORÇAMENTOS");
                break;
        }

    }

    private static void callMenuBudgets(){

        // chamando select de nome de setores
        Object[] namesSectors = SectorsDAO.findSectorsInArrayWithId();
        Object nameSector = JOptionPane.showInputDialog(null, "Selecione o setor: ",
                "Menu Orçamentos", JOptionPane.QUESTION_MESSAGE, null, namesSectors, namesSectors[0]);
        String[] split = nameSector.toString().split(" - ");
        int sectorId = parseInt(split[0]);
        Sectors sectors = SectorsDAO.findSectorsById(sectorId);
        // chamando select de nome de setores

        String[] optionsMenuBudgests = {"Voltar", "Relatório", "Excluir", "Editar", "Novo"};
        int menuBudgests = JOptionPane.showOptionDialog(null, "Selecione uma ação para Orçamentos do setor (" + nameSector + ")",
                "Menu Orçamentos (Qualquer usuário)",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionsMenuBudgests, optionsMenuBudgests[0]);

        switch (menuBudgests) {
            case 0:
                callMenuEntity();
                //RETORNA/ABRE PARA MENUS DE ENTIDADES
                break;
            case 1:
                System.out.println(" CHAMAR RELATÓRIO ORÇAMENTOS");
                break;
            case 2:
                System.out.println(" CHAMAR EXCLUIR ORÇAMENTOS");
                break;
            case 3:
                System.out.println(" CHAMAR EDITAR ORÇAMENTOS");
                break;
            case 4:
                System.out.println(" CHAMAR NOVO ORÇAMENTOS");
                break;
        }

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
                //RETORNA/ABRE PARA MENUS DE ENTIDADES
                break;
            case 1:
                System.out.println(" CHAMAR RELATÓRIO TIPOS ORÇAMENTOS");
                break;
            case 2:
                System.out.println(" CHAMAR EXCLUIR TIPOS ORÇAMENTOS");
                break;
            case 3:
                System.out.println(" CHAMAR EDITAR TIPOS ORÇAMENTOS");
                break;
            case 4:
                System.out.println(" CHAMAR NOVO TIPOS ORÇAMENTOS");
                break;
        }

    }

    private static void callMenuBudgets(){

        // chamando select de nome de setores
        Object[] namesSectors = SectorsDAO.findSectorsInArrayWithId();
        Object nameSector = JOptionPane.showInputDialog(null, "Selecione o setor: ",
                "Menu Orçamentos", JOptionPane.QUESTION_MESSAGE, null, namesSectors, namesSectors[0]);
        String[] split = nameSector.toString().split(" - ");
        int sectorId = parseInt(split[0]);
        Sectors sectors = SectorsDAO.findSectorsById(sectorId);
        // chamando select de nome de setores

        String[] optionsMenuBudgests = {"Voltar", "Relatório", "Excluir", "Editar", "Novo"};
        int menuBudgests = JOptionPane.showOptionDialog(null, "Selecione uma ação para Orçamentos do setor (" + nameSector + ")",
                "Menu Orçamentos (Qualquer usuário)",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionsMenuBudgests, optionsMenuBudgests[0]);

        switch (menuBudgests) {
            case 0:
                callMenuEntity();
                //RETORNA/ABRE PARA MENUS DE ENTIDADES
                break;
            case 1:
                System.out.println(" CHAMAR RELATÓRIO ORÇAMENTOS");
                break;
            case 2:
                System.out.println(" CHAMAR EXCLUIR ORÇAMENTOS");
                break;
            case 3:
                System.out.println(" CHAMAR EDITAR ORÇAMENTOS");
                break;
            case 4:
                System.out.println(" CHAMAR NOVO ORÇAMENTOS");
                break;
        }

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