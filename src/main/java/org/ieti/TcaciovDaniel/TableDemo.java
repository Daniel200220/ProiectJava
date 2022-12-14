package org.ieti.TcaciovDaniel;

import javax.swing.*;
import java.awt.*;

public class TableDemo {

    private static final String[] COLUMN_NAMES = {"Nume",
            "Prenume",
            "Ani",
            "Postul",
            "Echipa"};

    private static final Object[][] DATA = {
            {"Lionel", "Messi", 35, "Atacant", "PSG"},
            {"Cristiano", "Ronaldo", 37, "Atacant", "Agent Liber"},
            {"Sergio", "Ramos", 36, "Aparator", "PSG"},
            {"Paulo", "Dybala", 29, "Mijlocas", "Roma"},
    };

    public static void main(String[] args) {

        JFrame container = new JFrame("Table");
        JTable table = new JTable(DATA, COLUMN_NAMES);
        table.setCellSelectionEnabled(true);

        ListSelectionListenerImpl listSelectionListener = new ListSelectionListenerImpl(table);

        ListSelectionModel select = table.getSelectionModel();
        select.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        select.addListSelectionListener(listSelectionListener);

        JScrollPane scrollPane = new JScrollPane(table);

        container.add(scrollPane);
        container.setLayout(new BorderLayout());
        container.add(table.getTableHeader(), BorderLayout.PAGE_START);
        container.add(table, BorderLayout.CENTER);
        container.setVisible(true);
        container.setSize(300, 400);
    }

}