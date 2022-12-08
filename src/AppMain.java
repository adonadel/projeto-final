import model.Sector;
import model.User;
import repository.SectorDAO;
import repository.UserDAO;

import javax.swing.*;

import static java.lang.Integer.parseInt;

import java.sql.*;
import java.time.LocalDateTime;

public class AppMain {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        callLogin();
    }

    public static void mainTest(String[] args) throws SQLException, ClassNotFoundException {

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
        getUserDAO().save(user);

        callLogin();
    }

    private static void callLogin() throws SQLException, ClassNotFoundException {
        String login = JOptionPane.showInputDialog(null, "Informe o login do usuário ",
                "Login Sistema", 3);
        String senha = JOptionPane.showInputDialog(null, "Informe a senha do usuário " + login,
                "Login Sistema", 3);
        callMenuOptions();
    }

    private static void callMenuOptions() throws SQLException, ClassNotFoundException {

        String[] optionsMenu = {"Relatórios", "Entidades", "Sair"};
        int menuOptions = JOptionPane.showOptionDialog(null, "Selecione uma opção :",
                "Menu Opções (ADMIN)",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionsMenu, optionsMenu[0]);
        switch (menuOptions) {
            case 0:
//                chamar relatórios
                break;
            case 1:
                callMenuEntities();
                break;
            case 2:
                callLogin();
                break;
        }
    }


    private static void callMenuEntities() throws SQLException, ClassNotFoundException {

        String[] optionsMenuEntity = {"Usuários", "Setores", "Exercícios", "Orçamentos", "Tipos Orçamentos", "Voltar"};
        int menuEntity = JOptionPane.showOptionDialog(null, "Selecione uma entidade para mais ações:",
                "Menu entidades (ADMIN)",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionsMenuEntity, optionsMenuEntity[0]);
        switch (menuEntity) {
            case 0:
                callMenuUsers();
                break;
            case 1:
                callMenuSectors();
                break;
            case 2:
                callMenuExercises();
                break;
            case 3:
                callMenuBudgets();
                break;
            case 4:
                callMenuTypesBudgets();
                break;
            case 5:
                callMenuOptions();
                break;
        }
    }

    private static void callMenuUsers() throws SQLException, ClassNotFoundException {

        // chamando select de nome de setores
        Object[] namesSectors = getSectorDAO().searchAllWithIdOnName();
        Object nameSector = JOptionPane.showInputDialog(null, "Selecione o setor: ",
                "Menu Usuários", JOptionPane.QUESTION_MESSAGE, null, namesSectors, namesSectors[0]);
        String[] split = nameSector.toString().split(" - ");
        int sectorId = parseInt(split[0]);
        Sector sector = getSectorDAO().searchById(sectorId);
        // chamando select de nome de setores

        String[] optionsMenuUsers = {"Novo", "Editar", "Excluir", "Voltar"};
        int menuUsers = JOptionPane.showOptionDialog(null, "Selecione uma ação para Usuários do setor (" + nameSector + ")",
                "Menu Usuários (ADMIN)",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionsMenuUsers, optionsMenuUsers[0]);

        switch (menuUsers) {
            case 0:
                //novo usuário
                callCreateUser();
                break;
            case 1:
                //editar
                break;
            case 2:
                //excluir
                break;
            case 3:
                callMenuEntities();
                //RETORNA/ABRE PARA MENUS DE ENTIDADES
                break;
        }

    }

    //    NOVO USUÁRIO
    private static void callCreateUser(){

        String name = JOptionPane.showInputDialog(null, "Informe o nome do usuário",
                "Cadastro Usuário", 3);

        String userName = JOptionPane.showInputDialog(null, "Informe o login que o usuário irá utilizar",
                "Cadastro Usuário", 3);

        //transformar em list-box puxando os nomes do setores
        String sectorName = JOptionPane.showInputDialog(null, "Informe o setor que o usuário pertence",
                "Cadastro Usuário", 3);

        //transformar em list-box puxando os nomes dos tipos de usuários ()
        String type = JOptionPane.showInputDialog(null, "Informe o tipo do usuário",
                "Cadastro Usuário", 3);

        String password = JOptionPane.showInputDialog(null, "Informe a senha do usuário",
                "Cadastro Usuário", 3);

        // Mensagem sucesso
        JOptionPane.showMessageDialog(null, "Usuário Cadastrado com Sucesso!",
                "Mensagem do Sistema", 2);
        //Verificar se o tipod a mensagem está certo ou não

        // Mensagem Erro
        JOptionPane.showMessageDialog(null, "Ocorreu algum erro no cadastro deste usuário!",
                "Mensagem do Sistema", 1);
        //Verificar se o tipod a mensagem está certo ou não

    }
    //    NOVO USUÁRIO

    private static void callMenuSectors() throws SQLException, ClassNotFoundException {
        String[] optionsMenuSectors = {"Novo", "Editar", "Excluir", "Voltar"};
        int menuSectors = JOptionPane.showOptionDialog(null, "Selecione uma ação para Setores",
                "Menu Setores (ADMIN)",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionsMenuSectors, optionsMenuSectors[0]);

        switch (menuSectors) {
            case 0:
                //novo
                callCreateSector();
                break;
            case 1:
                //editar
                break;
            case 2:
                //excluir
                break;
            case 3:
                callMenuEntities();
                //RETORNA/ABRE PARA MENUS DE ENTIDADES
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + menuSectors);
        }

    }

    //    NOVO SETOR
    private static void callCreateSector(){

        String name = JOptionPane.showInputDialog(null, "Informe o nome do Setor",
                "Cadastro Setor", 3);


        // Mensagem sucesso
        JOptionPane.showMessageDialog(null, "Setor Cadastrado com Sucesso!",
                "Mensagem do Sistema", 2);
        //Verificar se o tipod a mensagem está certo ou não

        // Mensagem Erro
        JOptionPane.showMessageDialog(null, "Ocorreu algum erro no cadastro deste Setor!",
                "Mensagem do Sistema", 1);
        //Verificar se o tipo da mensagem está certo ou não

    }
    //    NOVO SETOR

    private static void callMenuExercises() throws SQLException, ClassNotFoundException {
        String[] optionsMenuExercises = {"Novo", "Editar", "Excluir", "Voltar"};
        int menuExercises = JOptionPane.showOptionDialog(null, "Selecione uma ação para Exercícios",
                "Menu Exercícios (ADMIN)",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionsMenuExercises, optionsMenuExercises[0]);

        switch (menuExercises) {
            case 0:
                //novo
                callCreateExercise();
                break;
            case 1:
                //editar
                break;
            case 2:
                //excluir
                break;
            case 3:
                callMenuEntities();
                //RETORNA/ABRE PARA MENUS DE ENTIDADES
                break;
        }

    }

    //    NOVO SETOR
    private static void callCreateExercise(){


        String year = JOptionPane.showInputDialog(null, "Informe o ano do Exercício",
                "Cadastro Exercício", 3);

        // Mensagem sucesso
        JOptionPane.showMessageDialog(null, "Exercício Cadastrado com Sucesso!",
                "Mensagem do Sistema", 2);
        //Verificar se o tipod a mensagem está certo ou não

        // Mensagem Erro
        JOptionPane.showMessageDialog(null, "Ocorreu algum erro no cadastro deste Exercício!",
                "Mensagem do Sistema", 1);
        //Verificar se o tipo da mensagem está certo ou não

    }
    //    NOVO SETOR


    private static void callMenuTypesBudgets() throws SQLException, ClassNotFoundException {
        String[] optionsMenuTypesBudgets = {"Novo", "Editar", "Excluir", "Voltar"};
        int menuTypesBudgets = JOptionPane.showOptionDialog(null, "Selecione uma ação para Tipos Orçamentos",
                "Menu Tipos Orçamentos (ADMIN)",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionsMenuTypesBudgets, optionsMenuTypesBudgets[0]);

        switch (menuTypesBudgets) {
            case 0:
                //novo
                break;
            case 1:
                //editar
                break;
            case 2:
                //excluir
                break;
            case 3:
                callMenuEntities();
                //RETORNA/ABRE PARA MENUS DE ENTIDADES
                break;
        }

    }

    private static void callMenuBudgets() throws SQLException, ClassNotFoundException {

        // chamando select de nome de setores
        Object[] namesSectors = getSectorDAO().searchAllOnlyWithName();
        Object nameSector = JOptionPane.showInputDialog(null, "Selecione o setor: ",
                "Menu Orçamentos", JOptionPane.QUESTION_MESSAGE, null, namesSectors, namesSectors[0]);
        String[] split = nameSector.toString().split(" - ");
        int sectorId = parseInt(split[0]);
        Sector sector = getSectorDAO().searchById(sectorId);

        // chamando select de nome de setores

        String[] optionsMenuBudgests = {"Novo", "Editar", "Excluir", "Voltar"};
        int menuBudgests = JOptionPane.showOptionDialog(null, "Selecione uma ação para Orçamentos do setor (" + nameSector + ")",
                "Menu Orçamentos (Qualquer usuário)",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionsMenuBudgests, optionsMenuBudgests[0]);

        switch (menuBudgests) {
            case 0:
                //novo
                break;
            case 1:
                //editar
                break;
            case 2:
                //excluir
                break;
            case 3:
                callMenuEntities();
                //RETORNA/ABRE PARA MENUS DE ENTIDADES
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

    public static SectorDAO getSectorDAO() {
        SectorDAO sectorDAO = new SectorDAO();
        return sectorDAO;
    }
}