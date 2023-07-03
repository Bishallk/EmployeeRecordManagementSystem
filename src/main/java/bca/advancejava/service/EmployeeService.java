package bca.advancejava.service;

import bca.advancejava.database.DatabaseConnection;
import bca.advancejava.database.QueryUtils;
import bca.advancejava.model.Employee;
import bca.advancejava.view.RegistrationFormPanel;
import bca.advancejava.view.TablePanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeService extends RegistrationFormPanel {
    public static void insertEmployee(Employee employee) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
        ) {
            PreparedStatement statement = connection.prepareStatement(QueryUtils.insertQuery());
            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getName());
//            (id,name,address,gender,department,salary,profilePic)
            statement.setString(3, employee.getAddress());
            statement.setString(4, employee.getGender());
            statement.setString(5, employee.getDepartment());
            statement.setInt(6, employee.getSalary());
            statement.setBytes(7, employee.getProfilePic());

            int rows = statement.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "Inserted Successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed");
            }

        }
    }

    public static void updateEmployee(Employee employee) throws SQLException {

        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(QueryUtils.updateQuery(employee.getId()));
            statement.setString(1, employee.getName());
//            (id,name,address,gender,department,salary,profilePic)
            statement.setString(2, employee.getAddress());
            statement.setString(3, employee.getGender());
            statement.setString(4, employee.getDepartment());
            statement.setInt(5, employee.getSalary());
            statement.setBytes(6, employee.getProfilePic());
            int rows = statement.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "Updated Successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed");
            }
        }
    }

    public static ArrayList<Employee> getEmployeeList() throws SQLException {
        ArrayList<Employee> employeeList=new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QueryUtils.selectAllQuery());
            Employee employee;
            while (resultSet.next()) {
                employee= new Employee(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("gender"),resultSet.getString("department"),resultSet.getInt("salary"),resultSet.getString("address"),resultSet.getBytes("profilePic"));
                employeeList.add(employee);
            }
        }
        return employeeList;
    }

    public static void deleteEmployee(int id) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();) {
            int rows = statement.executeUpdate(QueryUtils.deleteQuery(id));
            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "Deleted Successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed!");
            }
        }
    }

    public static Employee getEmployeeById(int id) throws SQLException {
        Employee employee=null;
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(QueryUtils.getByIdQuery(id));) {
//             public Employee(int id, String name, String gender, String department, int salary, String address, byte[] profilePic)
            if (resultSet.next()) {
               employee= new Employee(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("gender"),resultSet.getString("department"),resultSet.getInt("salary"),resultSet.getString("address"),resultSet.getBytes("profilePic"));
            } else {
                JOptionPane.showMessageDialog(null, "Record not found for id: " + id);
            }
        }
        return employee;
    }


}
