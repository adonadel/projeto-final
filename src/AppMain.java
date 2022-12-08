import jdk.nashorn.internal.scripts.JO;
import model.*;
import repository.*;

import javax.swing.*;

import static java.lang.Integer.parseInt;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class AppMain {
    public static void main(String[] args) throws Exception {
        callLogin();
    }

    private static void callLogin() throws Exception {
        String username = JOptionPane.showInputDialog(null, "Informe o login do usuário ",
                "Login Sistema", 3);
        String password = JOptionPane.showInputDialog(null, "Informe a senha do usuário " + username,
                "Login Sistema", 3);
        if (username != null && password != null)
            checkUser(username, password);
    }

    private static void checkUser(String username, String password) throws Exception {
        User user = getUserDAO().checkUserAuth(username, password);
        if (user.getId() != null) {
            callMenuOptions();
        }else{
            JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos!", "Login Sistema", JOptionPane.INFORMATION_MESSAGE);
            callLogin();
        }
    }

    private static void callMenuOptions() throws Exception {

        String[] optionsMenu = {"Entidades", "Relatórios", "Sair"};
        int menuOptions = JOptionPane.showOptionDialog(null, "Selecione uma opção :",
                "Menu Opções (ADMIN)",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionsMenu, optionsMenu[0]);
        switch (menuOptions) {
            case 0:
                callMenuEntities();
                break;
            case 1:

                break;
            case 2:
                callLogin();
                break;
        }
    }


    private static void callMenuEntities() throws Exception {

        String[] optionsMenuEntity = {"Usuários", "Setores", "Exercícios", "Orçamentos", "Tipos Orçamentos", "Voltar"};
        int menuEntity = JOptionPane.showOptionDialog(null, "Selecione uma entidade para mais ações:",
                "Menu entidades (ADMIN)",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionsMenuEntity, optionsMenuEntity[0]);
        switch (menuEntity) {
            case 0:
                callMenuUsers();
                callMenuEntities();
                break;
            case 1:
                callMenuSectors();
                callMenuEntities();
                break;
            case 2:
                callMenuExercises();
                callMenuEntities();
                break;
            case 3:
                callMenuBudgets();
                callMenuEntities();
                break;
            case 4:
                callMenuTypesBudgets();
                callMenuEntities();
                break;
            case 5:
                callMenuOptions();
                break;
        }
    }

    private static void callMenuUsers() throws Exception {
        String[] optionsMenuUsers = {"Novo", "Editar", "Excluir", "Voltar"};
        int menuUsers = JOptionPane.showOptionDialog(null, "Selecione uma ação: ",
                "Menu Usuários",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionsMenuUsers, optionsMenuUsers[0]);
        User user = null;
        switch (menuUsers) {
            case 0:
                //novo usuário
                user = callCreateUser();
                if (user != null)
                    getUserDAO().save(user);
                break;
            case 1:
                user = selectUser();
                user = callUpdateSector(user);

                if (user != null)
                    getUserDAO().save(user);

                callMenuUsers();
                break;
            case 2:
                user = selectUser();
                getUserDAO().remove(user);

                callMenuSectors();
                break;
            case 3:
                callMenuEntities();
                break;
        }
    }

    private static User callCreateUser() throws SQLException, ClassNotFoundException{
        User user = new User();
        String name = JOptionPane.showInputDialog(null, "Informe o nome do usuário",
                "Cadastro Usuário", 3);

        String userName = JOptionPane.showInputDialog(null, "Informe o login que o usuário irá utilizar",
                "Cadastro Usuário", 3);

        String password = JOptionPane.showInputDialog(null, "Informe a senha do usuário",
                "Cadastro Usuário", 3);

        Object[] arrayType = UserType.getEnumArray();
        Object auxType = JOptionPane.showInputDialog(null, "Informe o tipo do usuário", "Cadastro Usuário", JOptionPane.QUESTION_MESSAGE, null, arrayType, arrayType[1]);
        Integer type = UserType.getEnumIntValue(auxType);

        Object[] arraySectors = getSectorDAO().searchAllReturnArray();
        Object auxSector = JOptionPane.showInputDialog(null, "Informe o setor do usuário", "Cadastro Usuário", JOptionPane.QUESTION_MESSAGE, null, arraySectors, arraySectors[0]);
        List<Sector> sectors = getSectorDAO().searchByName((String) auxSector);
        Sector sector = sectors.get(0);

        user.setName(name);
        user.setUsername(userName);
        user.setSector(sector);
        user.setType(type);
        user.setPassword(password);
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());

        return user;
    }

    private static User callUpdateSector(User user) throws SQLException, ClassNotFoundException{
        String name = JOptionPane.showInputDialog(null, "Informe o novo nome do usuário", user.getName());
        String password = JOptionPane.showInputDialog(null, "Informe a nova senha", user.getPassword());

        Object[] arrayType = UserType.getEnumArray();
        Object auxType = JOptionPane.showInputDialog(null, "Informe o novo tipo do usuário", "Cadastro Usuário", JOptionPane.QUESTION_MESSAGE, null, arrayType, arrayType[1]);
        Integer type = UserType.getEnumIntValue(auxType);

        Object[] arraySectors = getSectorDAO().searchAllReturnArray();
        Object auxSector = JOptionPane.showInputDialog(null, "Informe o setor do usuário", "Cadastro Usuário", JOptionPane.QUESTION_MESSAGE, null, arraySectors, arraySectors[0]);
        List<Sector> sectors = getSectorDAO().searchByName((String) auxSector);
        Sector sector = sectors.get(0);

        user.setName(name);
        user.setPassword(password);
        user.setType(type);
        user.setSector(sector);
        user.setModified(LocalDateTime.now());

        return user;
    }

    private static User selectUser() throws SQLException, ClassNotFoundException {
        Object[] selectionValues = getUserDAO().searchAllReturnArray();
        String initialSelection = (String) selectionValues[0];
        Object selection = JOptionPane.showInputDialog(null, "Selecione um usuário",
                null, JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
        List<User> users = getUserDAO().searchByName((String) selection);
        return users.get(0);
    }

    private static void callMenuSectors() throws Exception {
        String[] optionsMenuSectors = {"Novo", "Editar", "Excluir", "Voltar"};
        int menuSectors = JOptionPane.showOptionDialog(null, "Selecione uma ação para Setores",
                "Menu Setores (ADMIN)",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionsMenuSectors, optionsMenuSectors[0]);
        Sector sector = null;
        switch (menuSectors) {
            case 0: //novo
                sector = callCreateSector();

                if (sector != null)
                    getSectorDAO().save(sector);

                callMenuSectors();
                break;
            case 1: //editar
                sector = selectSector();
                sector = callUpdateSector(sector);

                if (sector != null)
                    getSectorDAO().save(sector);

                callMenuSectors();
                break;
            case 2: //excluir
                sector = selectSector();
                getSectorDAO().remove(sector);

                callMenuSectors();
                break;
            case 3: //voltar
                callMenuEntities();
                break;
        }
    }

    private static Sector callCreateSector(){
        Sector sector = new Sector();

        String name = JOptionPane.showInputDialog(null, "Informe o nome do Setor",
                "Cadastro Setor", JOptionPane.QUESTION_MESSAGE);

        sector.setName(name);
        sector.setActive(1);
        sector.setCreated(LocalDateTime.now());
        sector.setModified(LocalDateTime.now());

        return sector;
    }

    private static Sector callUpdateSector(Sector sector){

        String name = JOptionPane.showInputDialog(null, "Informe o nome do Setor",
                sector.getName());
        Object[] options = {0, 1};
        Integer active = (Integer) JOptionPane.showInputDialog(null, "Informe o status do Setor", null, JOptionPane.QUESTION_MESSAGE, null, options, options[sector.getActive()]);

        sector.setName(name);
        sector.setActive(active);
        sector.setModified(LocalDateTime.now());

        return sector;
    }

    private static Sector selectSector() throws SQLException, ClassNotFoundException {
        Object[] selectionValues = getSectorDAO().searchAllReturnArray();
        String initialSelection = (String) selectionValues[0];
        Object selection = JOptionPane.showInputDialog(null, "Selecione um setor",
                null, JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
        List<Sector> sectors = getSectorDAO().searchByName((String) selection);
        return sectors.get(0);
    }

    private static void callMenuExercises() throws Exception {
        String[] optionsMenuExercises = {"Novo", "Editar", "Excluir", "Voltar"};
        int menuExercises = JOptionPane.showOptionDialog(null, "Selecione uma ação para Exercícios",
                "Menu Exercícios (ADMIN)",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionsMenuExercises, optionsMenuExercises[0]);
        Exercise exercise;
        switch (menuExercises) {
            case 0: //novo
                exercise = callCreateExercise();
                getExerciseDAO().save(exercise);
                callMenuExercises();
                break;
            case 1: //editar
                exercise = selectExercise();
                callUpdateExercise(exercise);
                callMenuExercises();
                break;
            case 2: //excluir
                exercise = selectExercise();
                getExerciseDAO().remove(exercise);

                callMenuExercises();
                break;
            case 3:
                callMenuEntities();
                break;
        }

    }

    private static Exercise callCreateExercise(){
        Exercise exercise = new Exercise();

        Integer year = Integer.valueOf(JOptionPane.showInputDialog(null, "Informe o ano do Exercício",
                "Cadastro Exercício", JOptionPane.QUESTION_MESSAGE));

        Object[] arrayStatus = ExerciseStatus.getEnumArray();

        Object auxStatus = JOptionPane.showInputDialog(null, "Informe o status do Setor", null, JOptionPane.QUESTION_MESSAGE, null, arrayStatus, arrayStatus[2]);
        Integer status = ExerciseStatus.getEnumIntValue(auxStatus);

        Object[] optionsActive = {0, 1};
        Integer active = (Integer) JOptionPane.showInputDialog(null, "Informe o status do Exercício", null, JOptionPane.QUESTION_MESSAGE, null, optionsActive, optionsActive[1]);

        exercise.setYear(year);
        exercise.setStatus(status);
        exercise.setActive(active);
        exercise.setCreated(LocalDateTime.now());
        exercise.setModified(LocalDateTime.now());

        return exercise;
    }

    private static Exercise callUpdateExercise(Exercise exercise){

        Integer year = Integer.valueOf(JOptionPane.showInputDialog(null, "Informe o ano do Exercício: ",
                exercise.getYear()));

        Object[] arrayStatus = ExerciseStatus.getEnumArray();

        Object auxStatus = JOptionPane.showInputDialog(null, "Informe o status do Setor: ", null, JOptionPane.QUESTION_MESSAGE, null, arrayStatus, arrayStatus[exercise.getStatus()]);
        Integer status = ExerciseStatus.getEnumIntValue(auxStatus);

        Object[] optionsActive = {0, 1};
        Integer active = (Integer) JOptionPane.showInputDialog(null, "Informe o status do Exercício: ", null, JOptionPane.QUESTION_MESSAGE, null, optionsActive, optionsActive[exercise.getActive()]);

        exercise.setYear(year);
        exercise.setStatus(status);
        exercise.setActive(active);
        exercise.setModified(LocalDateTime.now());

        return exercise;
    }

    private static Exercise selectExercise() throws SQLException, ClassNotFoundException {
        Object[] selectionValues = getExerciseDAO().searchAllReturnArray();
        String initialSelection = (String) selectionValues[0];
        Object selection = JOptionPane.showInputDialog(null, "Selecione um Exercício: ",
                null, JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
        List<Exercise> exercises = getExerciseDAO().searchByYear((Integer) selection);
        return exercises.get(0);
    }

            private static void callMenuBudgets() throws Exception {
                String[] optionsMenuBudgets = {"Novo", "Editar", "Excluir", "Voltar"};
                int menuBudgets = JOptionPane.showOptionDialog(null, "Selecione uma ação para Orçamentos",
                        "Menu Orçamentos",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionsMenuBudgets, optionsMenuBudgets[0]);
                Budget budget;
                switch (menuBudgets) {
                    case 0: //novo
                        budget = callCreateBudget();
                        getBudgetDAO().save(budget);
                        callMenuBudgets();
                        break;
                    case 1: //editar
                        budget = selectBudget();
                        callUpdateBudget(budget);
                        callMenuBudgets();
                        break;
                    case 2: //excluir
                        budget = selectBudget();
                        getBudgetDAO().remove(budget);

                        callMenuBudgets();
                        break;
                    case 3:
                        callMenuEntities();
                        break;
                }

            }

//            private static Budget callCreateBudget(){
//                Budget budget = new Budget();
//
//                String name = JOptionPane.showInputDialog(null, "Informe o nome do orçamento: ", "Cadastro orçamento");
//                String item = JOptionPane.showInputDialog(null, "Informe o item do orçamento: ", "Cadastro orçamento");
//                Integer qnt = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a quantidade de itens: ", "Cadastro orçamento"));
//                Double untVal = Double.parseDouble(JOptionPane.showInputDialog(null, "Informe o valor unitário do item: ", "Cadastro orçamento"));
//
//                return budget;
//            }
//
//            private static Budget callUpdateBudget(Budget budget){
//
//                Integer year = Integer.valueOf(JOptionPane.showInputDialog(null, "Informe o ano do Exercício: ",
//                        budget.getYear()));
//
//                Object[] arrayStatus = BudgetStatus.getEnumArray();
//
//                Object auxStatus = JOptionPane.showInputDialog(null, "Informe o status do Setor: ", null, JOptionPane.QUESTION_MESSAGE, null, arrayStatus, arrayStatus[budget.getStatus()]);
//                Integer status = BudgetStatus.getEnumIntValue(auxStatus);
//
//                Object[] optionsActive = {0, 1};
//                Integer active = (Integer) JOptionPane.showInputDialog(null, "Informe o status do Exercício: ", null, JOptionPane.QUESTION_MESSAGE, null, optionsActive, optionsActive[budget.getActive()]);
//
//                budget.setYear(year);
//                budget.setStatus(status);
//                budget.setActive(active);
//                budget.setModified(LocalDateTime.now());
//
//                return budget;
//            }

            private static Budget selectBudget() throws SQLException, ClassNotFoundException {
                Object[] selectionValues = getBudgetDAO().searchAllReturnArray();
                String initialSelection = (String) selectionValues[0];
                Object selection = JOptionPane.showInputDialog(null, "Selecione um Exercício: ",
                        null, JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
                List<Budget> budgets = getBudgetDAO().searchByName(selection.toString());
                return budgets.get(0);
            }

    private static void callMenuTypesBudgets() throws Exception {
        String[] optionsMenuTypesBudgets = {"Novo", "Editar", "Excluir", "Voltar"};
        int menuTypesBudgets = JOptionPane.showOptionDialog(null, "Selecione uma ação para Tipos Orçamentos: ",
                "Menu Tipos Orçamentos",
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

    private static BudgetType callCreateBudgeType(){
        BudgetType budgetType = new BudgetType();

        String name = JOptionPane.showInputDialog(null, "Informe o nome do orçamento",
                "Cadastro orçamento", JOptionPane.QUESTION_MESSAGE);

        budgetType.setName(name);
        budgetType.setActive(1);
        budgetType.setCreated(LocalDateTime.now());
        budgetType.setModified(LocalDateTime.now());

        return budgetType;
    }

    private static BudgetType callUpdateBudgetType(BudgetType budgetType){

        String name = JOptionPane.showInputDialog(null, "Informe o nome do tipo de orçamento",
                budgetType.getName());
        Object[] options = {0, 1};
        Integer active = (Integer) JOptionPane.showInputDialog(null, "Informe o status do tipo do orçamento", null, JOptionPane.QUESTION_MESSAGE, null, options, options[budgetType.getActive()]);

        budgetType.setName(name);
        budgetType.setActive(active);
        budgetType.setModified(LocalDateTime.now());

        return budgetType;
    }

    private static Sector selectBudgetType() throws SQLException, ClassNotFoundException {
        Object[] selectionValues = getBudgetTypeDAO().searchAllReturnArray();
        String initialSelection = (String) selectionValues[0];
        Object selection = JOptionPane.showInputDialog(null, "Selecione um tipo de orçamento",
                null, JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
        List<BudgetType> budgetTypes = getSectorDAO().searchByName((String) selection);
        return sectors.get(0);
    }

    public static UserDAO getUserDAO() {
        UserDAO userDAO = new UserDAO();
        return userDAO;
    }

    public static SectorDAO getSectorDAO() {
        SectorDAO sectorDAO = new SectorDAO();
        return sectorDAO;
    }

    public static ExerciseDAO getExerciseDAO() {
        ExerciseDAO exerciseDAO = new ExerciseDAO();
        return exerciseDAO;
    }

    public static BudgetDAO getBudgetDAO() {
        BudgetDAO budgetDAO = new BudgetDAO();
        return budgetDAO;
    }

    public static BudgetTypeDAO getBudgetTypeDAO() {
        BudgetTypeDAO budgetTypeDAO = new BudgetTypeDAO();
        return budgetTypeDAO;
    }

    public static void callMenuReports() {
    }
}