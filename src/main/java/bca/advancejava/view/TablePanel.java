package bca.advancejava.view;

import bca.advancejava.model.Employee;
import bca.advancejava.service.EmployeeService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class TablePanel extends JPanel {
  public  static DefaultTableModel tableModel = new DefaultTableModel();
  public  static JTable employeeTbl = new JTable(tableModel);

      public TablePanel() {

        tableModel.addColumn("SN");
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Gender");
        tableModel.addColumn("Address");
        tableModel.addColumn("Department");
        tableModel.addColumn("Salary (Rs.)");
       // tableModel.addColumn("Profile Picture");

        employeeTbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        employeeTbl.setFillsViewportHeight(true);
        employeeTbl.getColumnModel().getColumn(0).setPreferredWidth(30); // Sn
        employeeTbl.getColumnModel().getColumn(1).setPreferredWidth(40); // Id
        employeeTbl.getColumnModel().getColumn(2).setPreferredWidth(110); // Name
        employeeTbl.getColumnModel().getColumn(3).setPreferredWidth(50); // Gender
        employeeTbl.getColumnModel().getColumn(4).setPreferredWidth(150); // Address
        employeeTbl.getColumnModel().getColumn(5).setPreferredWidth(150); // Department
        employeeTbl.getColumnModel().getColumn(6).setPreferredWidth(70); // Salary
       // employeeTbl.getColumnModel().getColumn(7).setPreferredWidth(300); // images
        JScrollPane scrollPane = new JScrollPane(employeeTbl);
        scrollPane.setPreferredSize(new Dimension(620, 600));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);
        try {
            showDataToTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    //fetch data to table
    public static void showDataToTable() throws SQLException {
        ArrayList<Employee> list = EmployeeService.getEmployeeList();
        tableModel = (DefaultTableModel) employeeTbl.getModel();
        Object[] row = new Object[7];
        int count = 1;
        for (int i = 0; i < list.size(); i++) {
            row[0] = count;
            row[1] = list.get(i).getId();
            row[2] = list.get(i).getName();
            row[3] = list.get(i).getGender();
            row[4] = list.get(i).getAddress();
            row[5] = list.get(i).getDepartment();
            row[6] = list.get(i).getSalary();
            tableModel.addRow(row);
            count++;
        }
    }


}
