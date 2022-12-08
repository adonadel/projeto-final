package relatorio;

import model.Budget_type;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class TableBudgetType extends AbstractTableModel{

        private static final long serialVersionUID = 1L;
        public static final int INDEX_ID       = 0;
        public static final int INDEX_NAME     = 1;
        public static final int INDEX_ACTIVE   = 2;
        public static final int INDEX_CREATED  = 3;
        public static final int INDEX_MODIFIED = 4;
        public static final int INDEX_ESCONDIDO = 5;

        protected String[] nomeColunas;
        protected Vector<BudgetType> vetorDados;

        public TableBudgetType(String[] columnNames, Vector<BudgetType> vetorDados) {
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
            BudgetType registroBudgetType = (BudgetType) vetorDados.get(linha);
            switch (coluna) {
                case INDEX_ID:
                    return registroBudgetType.getId();
                case INDEX_NAME:
                    return registroBudgetType.getName();
                case INDEX_ACTIVE:
                    return registroBudgetType.getActive();
                case INDEX_CREATED:
                    return registroBudgetType.getCreated();
                case INDEX_MODIFIED:
                    return registroBudgetType.getModified();
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
