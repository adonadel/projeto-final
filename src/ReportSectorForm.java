import model.Sector;
import relatorio.TableSector;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class ReportSectorForm extends JPanel{
        private static final long serialVersionUID = 1L;

        public static final String[] nomeColunas =
                {"ID", "Nome", "Ativo", "Criação", "Modificação", ""};

        protected JTable table;
        protected JScrollPane scroller;
        protected TableSector tabela;

        public ReportSectorForm(Vector<Sector> vetorDados) {
            iniciarComponentes(vetorDados);
        }

        public void iniciarComponentes(Vector<Sector> vetorDados) {
            tabela = new TableSector(nomeColunas, vetorDados);
            table = new JTable();
            table.setModel(tabela);
            table.setSurrendersFocusOnKeystroke(true);
            scroller = new JScrollPane(table);
            table.setPreferredScrollableViewportSize(new Dimension(500, 300));

            TableColumn colunaEscondida = table.getColumnModel().getColumn(TableSector.INDEX_ESCONDIDO);
            colunaEscondida.setMinWidth(2);
            colunaEscondida.setPreferredWidth(2);
            colunaEscondida.setMaxWidth(2);
            setLayout(new BorderLayout());
            add(scroller, BorderLayout.CENTER);
        }

        public static void emitirRelatorio(List<Sector> sector) {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                JFrame frame = new JFrame("Relatório - Setor");

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

                Vector<Sector> vetorDados = new Vector<Sector>();
                /*for (Sector sector : sectors) {
                    vetorDados.add(sector);
                }*/ //Validar

                frame.getContentPane().add(new ReportSectorForm(vetorDados));
                frame.pack();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
