import model.Exercise;
import relatorio.TableExercise;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class ReportExerciseForm extends JPanel{

        private static final long serialVersionUID = 1L;
        public static final String[] nomeColunas =
                {"ID", "Ano", "Status", "Ativo" , "Criação", "Modificação", "Orçamento" , ""};

        protected JTable table;
        protected JScrollPane scroller;
        protected TableExercise tabela;

        public ReportExerciseForm(Vector<Exercise> vetorDados) {
            iniciarComponentes(vetorDados);
        }

        public void iniciarComponentes(Vector<Exercise> vetorDados) {
            tabela = new TableExercise(nomeColunas, vetorDados);
            table = new JTable();
            table.setModel(tabela);
            table.setSurrendersFocusOnKeystroke(true);
            scroller = new JScrollPane(table);
            table.setPreferredScrollableViewportSize(new Dimension(500, 300));

            TableColumn colunaEscondida = table.getColumnModel().getColumn(TableExercise.INDEX_ESCONDIDO);
            colunaEscondida.setMinWidth(2);
            colunaEscondida.setPreferredWidth(2);
            colunaEscondida.setMaxWidth(2);
            setLayout(new BorderLayout());
            add(scroller, BorderLayout.CENTER);
        }

        public static void emitirRelatorio(List<Exercise> sector) {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                JFrame frame = new JFrame("Relatório - Exercício");

                frame.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent evt) {
                        frame.setVisible(false);

                        AppMain.callMenuReports();
                    }
                });

                Vector<Exercise> vetorDados = new Vector<Exercise>();
                /*for (Sector sector : sectors) {
                    vetorDados.add(sector);
                }*/ //Validar

                frame.getContentPane().add(new ReportExerciseForm(vetorDados));
                frame.pack();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
