import model.Sectors;
import model.Users;
import repository.SectorsDAO;

import javax.swing.*;

import static java.lang.Integer.parseInt;

public class AppMain {

    public static void main(String[] args) {
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
}