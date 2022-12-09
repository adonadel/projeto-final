package repository;

import model.BudgetType;
import model.Sector;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BudgetTypeDAO {

    public static BudgetType String;
    static List<BudgetType> budgetTypes = new ArrayList<>();

    public void save(BudgetType budgetType) {
        BudgetTypeRepository budgetTypeTypeRepository = new BudgetTypeRepository();
        try {
            if (budgetType.getId() != null) {
                budgetTypeTypeRepository.update(budgetType);
            } else {
                budgetType.setId(budgetTypeTypeRepository.nextId());
                budgetTypeTypeRepository.insert(budgetType);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        budgetTypes.add(budgetType);
    }

    public void remove(BudgetType budgetType) throws Exception {
        BudgetTypeRepository budgetTypeTypeRepository = new BudgetTypeRepository();
        budgetTypeTypeRepository.delete(budgetType);
    }

    public List<BudgetType> searchAll() throws SQLException, ClassNotFoundException {
        BudgetTypeRepository budgetTypeTypeRepository = new BudgetTypeRepository();
        try{
            budgetTypes = budgetTypeTypeRepository.search();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return budgetTypes;
    }
    public List<BudgetType> searchWithName(String name) {
        BudgetTypeRepository budgetRepository = new BudgetTypeRepository();
        List<BudgetType> filtredBudgetTypes = new ArrayList<>();

        try{
            budgetTypes = budgetRepository.searchByName(name);

            for (BudgetType budgetType : budgetTypes){
                filtredBudgetTypes.add(budgetType);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return filtredBudgetTypes;
    }

    public BudgetType searchById(Integer id) {
        BudgetTypeRepository budgetTypeRepository = new BudgetTypeRepository();
        BudgetType budgetType = new BudgetType();
        try{
            budgetTypes = budgetTypeRepository.searchById(id);

            for (BudgetType auxBudgetType : budgetTypes){
                if (auxBudgetType.getId().equals(id)) {
                    budgetType = auxBudgetType;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return budgetType;
    }

        public List<BudgetType> searchByName(String name) {
        BudgetTypeRepository budgetTypeRepository = new BudgetTypeRepository();
        List<BudgetType> ListBudgetTypes = new ArrayList<>();

        try{
            budgetTypes = budgetTypeRepository.searchByName(name);

            for (BudgetType auxBudgetType : budgetTypes){
                if (auxBudgetType.getName().equals(name)) {
                    ListBudgetTypes.add(auxBudgetType);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ListBudgetTypes;
    }

    public Object[] searchAllOnlyWithName() throws SQLException, ClassNotFoundException {
        BudgetTypeRepository budgetTypeRepository = new BudgetTypeRepository();
        ArrayList<String> names = new ArrayList<>();
        try{
            budgetTypes = budgetTypeRepository.search();

            for (BudgetType budgetType : budgetTypes) {
                names.add(budgetType.getName());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return names.toArray();
    }

    public Object[] searchAllWithIdOnName() {
        BudgetTypeRepository budgetTypeRepository = new BudgetTypeRepository();
        List<String> budgetTypesName = new ArrayList<>();

        try{
            budgetTypes = budgetTypeRepository.search();

            for (BudgetType budgetType : budgetTypes) {
                budgetTypesName.add(budgetType.getId() + " - " + budgetType.getName());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return budgetTypesName.toArray();
    }

    public Object[] searchAllReturnArray() throws SQLException, ClassNotFoundException {
        List<BudgetType> budgetTypes = searchAll();
        List<String> budgetTypesNomes = new ArrayList<>();

        for (BudgetType budgetType : budgetTypes) {
            budgetTypesNomes.add(budgetType.getName());
        }

        return budgetTypesNomes.toArray();
    }
}
