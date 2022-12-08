import model.Budget;
import relatorio.TableBudget;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Vector;

public class ReportBudgetForm  extends JPanel{
        private static final long serialVersionUID = 1L;

        public static final String[] nomeColunas =
                {"ID", "Nome", "Nome", "Ativo", "Criação", "Modificação", ""};

        protected JTable table;
        protected JScrollPane scroller;
        protected TableBudget tabela;

        public ReportBudgetForm(Vector<Budget> vetorDados) {
            iniciarComponentes(vetorDados);
        }

        public void iniciarComponentes(Vector<Budget> vetorDados) {
            tabela = new TableBudget(nomeColunas, vetorDados);
            table = new JTable();
            table.setModel(tabela);
            table.setSurrendersFocusOnKeystroke(true);
            scroller = new JScrollPane(table);
            table.setPreferredScrollableViewportSize(new Dimension(500, 300));

            TableColumn colunaEscondida = table.getColumnModel().getColumn(TableBudget.INDEX_ESCONDIDO);
            colunaEscondida.setMinWidth(2);
            colunaEscondida.setPreferredWidth(2);
            colunaEscondida.setMaxWidth(2);
            setLayout(new BorderLayout());
            add(scroller, BorderLayout.CENTER);
        }

        public static void emitirRelatorio(List<Budget> sector) {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                JFrame frame = new JFrame("Relatório - Tipo de Orçamento");

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

                Vector<Budget> vetorDados = new Vector<Budget>();
                /*for (Sector sector : sectors) {
                    vetorDados.add(sector);
                }*/ //Validar

                frame.getContentPane().add(new ReportBudgetForm(vetorDados));
                frame.pack();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
