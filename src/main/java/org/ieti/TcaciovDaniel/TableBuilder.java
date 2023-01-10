package org.ieti.TcaciovDaniel;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import javax.swing.border.*;


import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class TableBuilder {


    public void buildTable(String[][] content, String[] headers) {
        JFrame container = new JFrame("Table");
        container.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTable table = new JTable(content, headers);

        table.setCellSelectionEnabled(true);

        ListSelectionListenerImpl listSelectionListener = new ListSelectionListenerImpl(table);

        ListSelectionModel select = table.getSelectionModel();
        select.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        select.addListSelectionListener(listSelectionListener);

        JScrollPane scrollPane = new JScrollPane(table);

//        container.add(scrollPane);
//        container.setLayout(new BorderLayout());
//        container.add(table.getTableHeader(), BorderLayout.PAGE_START);
//        container.add(table, BorderLayout.CENTER);
        container.setVisible(true);
        container.setSize(500, 400);

         TableRowSorter<TableModel> rowSorter
                = new TableRowSorter<>(table.getModel());

         JTextField jtfFilter = new JTextField();

        table.setRowSorter(rowSorter);
        JPanel panel1 = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Specify a word to match:"),
                BorderLayout.WEST);
        panel.add(jtfFilter);

        panel.setLayout(new BorderLayout());
        panel1.add(panel, BorderLayout.SOUTH);
        panel1.add(new JScrollPane(table), BorderLayout.NORTH);
        container.pack();
        container.add(panel1);
        jtfFilter.getDocument().addDocumentListener(new DocumentListener(){


            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfFilter.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfFilter.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else { rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

        });
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
            {      final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row % 2 == 0 ? Color.GREEN : Color.orange);

                return c;
            }});
    }

    }

