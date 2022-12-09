package relatorio;

import model.Active;
import model.Budget;
import model.BudgetStatus;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class TableBudget extends AbstractTableModel{
        private static final long serialVersionUID = 1L;
        public static final int INDEX_ID       = 0;
        public static final int INDEX_NAME     = 1;
        public static final int INDEX_ITEM     = 2;
        public static final int INDEX_QNT    = 3;
        public static final int INDEX_UNIT_VAL    = 4;
        public static final int INDEX_STATUS     = 5;
        public static final int INDEX_ACTIVE     = 6;
        public static final int INDEX_CREATED     = 7;
        public static final int INDEX_MODIFIED     = 8;
        public static final int INDEX_SECTOR     = 9;
        public static final int INDEX_BUDGET_TYPE     = 10;
        public static final int INDEX_ESCONDIDO = 11;

        protected String[] nomeColunas;
        protected Vector<Budget> vetorDados;

        public TableBudget(String[] columnNames, Vector<Budget> vetorDados) {
            this.nomeColunas = columnNames;
            this.vetorDados = vetorDados;
        }

        @Override
        public String getColumnName(int column) {
            return nomeColunas[column];
        }

        @Override
        public boolean isCellEditable(int linha, int coluna) {
            if (coluna == INDEX_ESCONDIDO) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        public Object getValueAt(int linha, int coluna) {
            Budget registroBudget = (Budget) vetorDados.get(linha);
            switch (coluna) {
                case INDEX_ID:
                    return registroBudget.getId();
                case INDEX_NAME:
                    return registroBudget.getName();
                case INDEX_ITEM:
                    return registroBudget.getItem();
                case INDEX_QNT:
                    return registroBudget.getQnt();
                case INDEX_UNIT_VAL:
                    return registroBudget.getUntVal();
                case INDEX_STATUS:
                    return BudgetStatus.getEnumByValue(registroBudget.getStatus());
                case INDEX_ACTIVE:
                    return Active.getEnumByValue(registroBudget.getActive());
                case INDEX_CREATED:
                    return registroBudget.getCreated();
                case INDEX_MODIFIED:
                    return registroBudget.getModified();
                case INDEX_SECTOR:
                    return registroBudget.getSector().getName();
                case INDEX_BUDGET_TYPE:
                    return registroBudget.getBudgetType().getName();
                default:
                    return new Object();
            }
        }

        @Override
        public int getRowCount() {
            return vetorDados.size();
        }

        @Override
        public int getColumnCount() {
            return nomeColunas.length;
        }
}
