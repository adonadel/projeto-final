package relatorio;

import model.User;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class TableUser extends AbstractTableModel {
        private static final long serialVersionUID = 1L;
        public static final int INDEX_ID       = 0;
        public static final int INDEX_NAME     = 1;
        public static final int INDEX_USERNAME = 2;
        public static final int INDEX_PASSWORD = 3;
        public static final int INDEX_TYPE     = 4;
        public static final int INDEX_CREATED  = 5;
        public static final int INDEX_MODIFIED = 6;
        public static final int INDEX_SECTOR   = 7;
        public static final int INDEX_ESCONDIDO = 8;

        protected String[] nomeColunas;
        protected Vector<User> vetorDados;

        public TableUser(String[] columnNames, Vector<User> vetorDados) {
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
            User registroUser = (User) vetorDados.get(linha);
            switch (coluna) {
                case INDEX_ID:
                    return registroUser.getId();
                case INDEX_NAME:
                    return registroUser.getName();
                case INDEX_USERNAME:
                    return registroUser.getUsername();
                case INDEX_PASSWORD:
                    return registroUser.getPassword();
                case INDEX_TYPE:
                    return registroUser.getType();
                case INDEX_CREATED:
                    return registroUser.getCreated();
                case INDEX_MODIFIED:
                    return registroUser.getModified();
                case INDEX_SECTOR:
                    return registroUser.getSector().getName();
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
