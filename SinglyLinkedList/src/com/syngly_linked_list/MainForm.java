package com.syngly_linked_list;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame{
    private JPanel panel1;
    private JTable table1;
    private JTextField toEndField;
    private JButton toEndButton;
    private JTextField byIndexField;
    private JButton byIndexButton;
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JButton deleteByIndex;
    private JButton deleteEnd;
    private JButton Sort;
    private JComboBox<String> comboBox1;
    private JTextField feField;
    private JButton forEachButton;

    public MainForm() {
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        table1.setVisible(true);
        SinglyLinkedList<Double> test = new SinglyLinkedList<>();
        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
        tableModel.addColumn("Elements");
        comboBox1.addItem("+");
        comboBox1.addItem("-");
        comboBox1.addItem("*");
        comboBox1.addItem("/");

        toEndButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double val;
                try {
                    val = Double.parseDouble(toEndField.getText());
                }
                catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Invalid format of value!");
                    return;
                }
                test.insertElement(val);
                tableModel.insertRow(tableModel.getRowCount(), new Object[] { val });
                toEndField.setText("");
            }
        });
        byIndexButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double val;
                int index;
                try {
                    val = Double.parseDouble(byIndexField.getText());
                }
                catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Invalid format of value!");
                    return;
                }
                try {
                    index = Integer.parseInt(spinner1.getValue().toString());
                }
                catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Invalid format of index!");
                    return;
                }
                if(index > test.count || index < 0) {
                    JOptionPane.showMessageDialog(null, "Index must be in interval from 0 to elements count!");
                    return;
                }
                test.insertElement(val);
                tableModel.insertRow(index, new Object[] { val });
                byIndexField.setText("");
                spinner1.setValue("0");
            }
        });
        deleteByIndex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index;
                try {
                    index = Integer.parseInt(spinner2.getValue().toString());
                }
                catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Invalid format of index!");
                    return;
                }
                if(index > test.count || index < 0) {
                    JOptionPane.showMessageDialog(null, "Index must be in interval from 0 to elements count!");
                    return;
                }
                tableModel.removeRow(index);
                test.deleteElement(index);
                spinner2.setValue("0");
            }
        });
        deleteEnd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.removeRow(tableModel.getRowCount() - 1);
                test.deleteElement();
            }
        });
        Sort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tableModel.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Empty list cannot be sorted!");
                    return;
                }
                test.sort();
                for (int i = 0; i < test.count; i++) {
                    tableModel.removeRow(0);
                }
                for (int i = 0; i < test.count; i++) {
                    tableModel.insertRow(tableModel.getRowCount(), new Object[] { test.getData(i) });
                }
            }
        });
        forEachButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double val;
                try {
                    val = Double.parseDouble(feField.getText());
                }
                catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Invalid format of value!");
                    return;
                }
                switch (comboBox1.getSelectedItem().toString()) {
                    case "+":
                        test.forEach(new IForEach<Double>() {
                            @Override
                            public Double toDo(Double data) {
                                return data + val;
                            }
                        });
                        break;
                    case "-":
                        test.forEach(new IForEach<Double>() {
                            @Override
                            public Double toDo(Double data) {
                                return data - val;
                            }
                        });
                        break;
                    case "*":
                        test.forEach(new IForEach<Double>() {
                            @Override
                            public Double toDo(Double data) {
                                return data * val;
                            }
                        });
                        break;
                    default:
                        test.forEach(new IForEach<Double>() {
                            @Override
                            public Double toDo(Double data) {
                                return data / val;
                            }
                        });
                        break;
                }
                for (int i = 0; i < test.count; i++) {
                    tableModel.removeRow(0);
                }
                for (int i = 0; i < test.count; i++) {
                    tableModel.insertRow(tableModel.getRowCount(), new Object[] { test.getData(i) });
                }
                feField.setText("");
            }
        });
    }
}
