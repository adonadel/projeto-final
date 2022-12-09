package relatorio;

import model.Active;
import model.Budget;
import model.Exercise;
import model.ExerciseStatus;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class TableExercise extends AbstractTableModel{
        private static final long serialVersionUID = 1L;
        public static final int INDEX_ID       = 0;
        public static final int INDEX_YEAR     = 1;
        public static final int INDEX_STATUS   = 2;
        public static final int INDEX_ACTIVE   = 3;
        public static final int INDEX_CREATED  = 4;
        public static final int INDEX_MODIFIED = 5;
        public static final int INDEX_BUDGET   = 6;
        public static final int INDEX_ESCONDIDO = 7;

        protected String[] nomeColunas;
        protected Vector<Exercise> vetorDados;

        public TableExercise(String[] columnNames, Vector<Exercise> vetorDados) {
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
            Exercise registroExercise = (Exercise) vetorDados.get(linha);
            switch (coluna) {
                case INDEX_ID:
                    return registroExercise.getId();
                case INDEX_YEAR:
                    return registroExercise.getYear();
                case INDEX_STATUS:
                    return ExerciseStatus.getEnumByValue(registroExercise.getStatus());
                case INDEX_ACTIVE:
                    return Active.getEnumByValue(registroExercise.getActive());
                case INDEX_CREATED:
                    return registroExercise.getCreated();
                case INDEX_MODIFIED:
                    return registroExercise.getModified();
                case INDEX_BUDGET:
                    return registroExercise.getBudget().getName();
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
