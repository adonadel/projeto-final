package relatorio;

import model.Sector;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class TableSector extends AbstractTableModel{
        private static final long serialVersionUID = 1L;
        public static final int INDEX_ID       = 0;
        public static final int INDEX_NAME     = 1;
        public static final int INDEX_ACTIVE   = 2;
        public static final int INDEX_CREATED  = 3;
        public static final int INDEX_MODIFIED = 4;
        public static final int INDEX_ESCONDIDO = 5;

        protected String[] nomeColunas;
        protected Vector<Sector> vetorDados;

        public TableSector(String[] columnNames, Vector<Sector> vetorDados) {
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
            Sector registroSector = (Sector) vetorDados.get(linha);
            switch (coluna) {
                case INDEX_ID:
                    return registroSector.getId();
                case INDEX_NAME:
                    return registroSector.getName();
                case INDEX_ACTIVE:
                    return registroSector.getActive();
                case INDEX_CREATED:
                    return registroSector.getCreated();
                case INDEX_MODIFIED:
                    return registroSector.getModified();
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
