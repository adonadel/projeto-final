import model.User;
import relatorio.TableUser;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class ReportUserForm extends JPanel{
        private static final long serialVersionUID = 1L;

        public static final String[] nomeColunas =
                {"ID", "Nome", "Nome de Usuário", "Senha" , "Tipo de Usuário" , "Criação", "Modificação", "Setor" , ""};
        protected JTable table;
        protected JScrollPane scroller;
        protected TableUser tabela;

        public ReportUserForm(Vector<User> vetorDados) {
            iniciarComponentes(vetorDados);
        }

        public void iniciarComponentes(Vector<User> vetorDados) {
            tabela = new TableUser(nomeColunas, vetorDados);
            table = new JTable();
            table.setModel(tabela);
            table.setSurrendersFocusOnKeystroke(true);
            scroller = new JScrollPane(table);
            table.setPreferredScrollableViewportSize(new Dimension(500, 300));

            TableColumn colunaEscondida = table.getColumnModel().getColumn(TableUser.INDEX_ESCONDIDO);
            colunaEscondida.setMinWidth(2);
            colunaEscondida.setPreferredWidth(2);
            colunaEscondida.setMaxWidth(2);
            setLayout(new BorderLayout());
            add(scroller, BorderLayout.CENTER);
        }

        public static void emitirRelatorio(List<User> users) {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                JFrame frame = new JFrame("Relatório - Usuários");

                frame.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent evt) {
                        frame.setVisible(false);
                        try {
                            AppMain.callMenuReports();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

                Vector<User> vetorDados = new Vector<User>();
                for (User user : users) {
                    vetorDados.add(user);
                }

                frame.getContentPane().add(new ReportUserForm(vetorDados));
                frame.pack();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
