package repository;

import model.Budget;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BudgetDAO {

    public static Budget String;
    static List<Budget> budgets = new ArrayList<>();

    public void save(Budget budget) {
        BudgetRepository budgetRepository = new BudgetRepository();
        try {
            if (budget.getId() != null) {
                budgetRepository.update(budget);
            } else {
                budget.setId(budgetRepository.nextId());
                budgetRepository.insert(budget);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        budgets.add(budget);
    }

    public void remove(Budget budget) throws Exception {
        BudgetRepository budgetRepository = new BudgetRepository();
        budgetRepository.delete(budget);
    }

    public List<Budget> searchAll() throws SQLException, ClassNotFoundException {
        BudgetRepository budgetRepository = new BudgetRepository();
        try{
            budgets = budgetRepository.search();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return budgets;
    }

    public List<Budget> searchWithName(String name) {
        BudgetRepository budgetRepository = new BudgetRepository();
        List<Budget> filtredBudgets = new ArrayList<>();

        try{
            budgets = budgetRepository.searchByName(name);

            for (Budget budget : budgets){
                filtredBudgets.add(budget);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return filtredBudgets;
    }

    public Budget searchById(Integer id) {
        BudgetRepository budgetRepository = new BudgetRepository();
        Budget budget = new Budget();
        try{
            budgets = budgetRepository.searchById(id);

            for (Budget auxBudget : budgets){
                if (auxBudget.getId().equals(id)) {
                    budget = auxBudget;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return budget;
    }

    public Object[] searchAllOnlyWithName() throws SQLException, ClassNotFoundException {
        BudgetRepository budgetRepository = new BudgetRepository();
        ArrayList<String> names = new ArrayList<>();
        try{
            budgets = budgetRepository.search();

            for (Budget budget : budgets) {
                names.add(budget.getName());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return names.toArray();
    }

    public Object[] searchAllWithIdOnName() {
        BudgetRepository budgetRepository = new BudgetRepository();
        List<String> budgetsName = new ArrayList<>();

        try{
            budgets = budgetRepository.search();

            for (Budget budget : budgets) {
                budgetsName.add(budget.getId() + " - " + budget.getName());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return budgetsName.toArray();
    }
}
