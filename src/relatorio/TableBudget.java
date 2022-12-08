package relatorio;

import model.Budget;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class TableBudget extends AbstractTableModel{
        private static final long serialVersionUID = 1L;
        public static final int INDEX_ID       = 0;
        public static final int INDEX_NAME     = 1;
        public static final int INDEX_ITEM     = 2;
        public static final int INDEX_ESCONDIDO = 3;

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