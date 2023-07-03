package bca.advancejava.view;

import bca.advancejava.model.Employee;
import bca.advancejava.service.EmployeeService;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;

public class RegistrationFormPanel extends JPanel {
    public JLabel NameLbl;
    public JLabel IdLbl;
    public JLabel DepartmentLbl;
    public JLabel AddressLbl;
    public JLabel GenderLbl;
    public JLabel SalaryLbl;
    public JLabel ProfilePicLbl;
    public JLabel SelectedPicLbl;
    public JLabel ImageLbl;
    public static JTextField NameField, IdField, AddressField, SalaryField;
    public static JComboBox<String> DepartmentCombo, GenderCombo;
    public JButton BrowseBtn,InsertBtn, UpdateBtn,DeleteBtn,SaveBtn,ClearBtn,ViewBtn;
    //image section variables
    public byte[] imageData = null;
    public String fileName;

    //height & width for label and text fields
    int height = 20;
    int labelWidth = 100;
    int fieldWidth = 260;
    static int y = 30;
    int labelX = 10;
    int fieldX = labelWidth + labelX;
    int nextY = height + 10;

    public RegistrationFormPanel() {
        setBackground(Color.WHITE);
        setLayout(null);

        Border border = BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),   // Border type
                "Registration Form",                           // Title text
                TitledBorder.LEFT,                    // Title position
                TitledBorder.DEFAULT_JUSTIFICATION,        // Border position
                new Font("Arial,Times New Roman", Font.BOLD, 15),     // Title font
                Color.BLACK
        );
        setBorder(border);


        IdLbl = new JLabel("Employee's Id");
        IdField = new JTextField();
        IdLbl.setBounds(labelX, y, labelWidth, height);
        IdField.setBounds(fieldX, y, fieldWidth, height);
        y += nextY;
        add(IdLbl);
        add(IdField);

        NameLbl = new JLabel("Name");
        NameField = new JTextField();
        NameLbl.setBounds(labelX, y, labelWidth, height);
        NameField.setBounds(fieldX, y, fieldWidth, height);
        y += nextY;
        add(NameLbl);
        add(NameField);

        GenderLbl = new JLabel("Select Gender");
        String[] genderList = {
                "Male",
                "Female",

        };
        GenderCombo = new JComboBox<>(genderList);
        GenderCombo.setBackground(Color.WHITE);
        add(GenderLbl);
        add(GenderCombo);
        GenderLbl.setBounds(labelX, y, labelWidth, height);
        GenderCombo.setBounds(fieldX, y, fieldWidth, height);
        y += nextY;

        AddressLbl = new JLabel("Address");
        AddressField = new JTextField(20);
        add(AddressLbl);
        add(AddressField);
        AddressLbl.setBounds(labelX, y, labelWidth, height);
        AddressField.setBounds(fieldX, y, fieldWidth, height);
        y += nextY;

        DepartmentLbl = new JLabel("Department");
        String[] departmentList = {
                "Sales",
                "Human Resources",
                "Information Technology",
                "Marketing",
                "Finance",
                "Operations",
                "Customer Service",
                "Research and Development"
        };
        DepartmentCombo = new JComboBox<>(departmentList);
        DepartmentCombo.setBackground(Color.WHITE);
        add(DepartmentLbl);
        add(DepartmentCombo);
        DepartmentLbl.setBounds(labelX, y, labelWidth, height);
        DepartmentCombo.setBounds(fieldX, y, fieldWidth, height);
        y += nextY;

        SalaryLbl = new JLabel("Salary");
        SalaryField = new JTextField();
        SalaryLbl.setBounds(labelX, y, labelWidth, height);
        SalaryField.setBounds(fieldX, y, fieldWidth, height);
        y += nextY;
        add(SalaryLbl);
        add(SalaryField);

        ProfilePicLbl = new JLabel("Profile Picture");
        BrowseBtn = new JButton("Browse");
        SelectedPicLbl = new JLabel("No file selected");
        ProfilePicLbl.setBounds(labelX, y, labelWidth, height);
        BrowseBtn.setBounds(fieldX, y, labelWidth, height);
        SelectedPicLbl.setBounds(fieldX + (fieldWidth / 2) + 5, y, labelWidth, height);
        y += nextY;
        add(ProfilePicLbl);
        add(BrowseBtn);
        add(SelectedPicLbl);
        ImageLbl = new JLabel();
        ImageLbl.setBounds(labelX+30, y, 300, 280);
        // ImageLbl.setPreferredSize(new Dimension(300,300));
        add(ImageLbl);
        InsertBtn = new JButton("Insert");
        UpdateBtn = new JButton("Update");
        DeleteBtn = new JButton("Delete");
        SaveBtn = new JButton("Save");
        ClearBtn = new JButton("Clear");
        ViewBtn=new JButton("View");
        InsertBtn.setBounds(labelX, y + 300, labelWidth, height);
        UpdateBtn.setBounds(labelX + 125, y + 300, labelWidth, height);
        DeleteBtn.setBounds(labelX + 250, y + 300, labelWidth, height);
        y += nextY;
        SaveBtn.setBounds(labelX, y + 300, labelWidth , height);
        SaveBtn.setEnabled(false);
        ClearBtn.setBounds(labelX + 125, y +300, labelWidth , height);
        ViewBtn.setBounds(labelX+250,y+300,labelWidth,height);
        add(InsertBtn);
        add(UpdateBtn);
        add(DeleteBtn);
        add(SaveBtn);
        add(ClearBtn);
        add(ViewBtn);
        setVisible(true);
        ClearBtn.addActionListener(e -> clearFields());
        BrowseBtn.addActionListener(e -> chooseImageFile());
        InsertBtn.addActionListener(e -> {
            boolean isValid = validateData();
            if (isValid) {
                try {
                    EmployeeService.insertEmployee(getEmployeeObj());
                    DefaultTableModel model = (DefaultTableModel) TablePanel.employeeTbl.getModel();
                    model.setRowCount(0);
                    TablePanel.showDataToTable();
                    clearFields();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }

            } else {

                JOptionPane.showMessageDialog(null, "All fields are Required!!");
            }
        });

        UpdateBtn.addActionListener(e -> {
            String uid = JOptionPane.showInputDialog(null, "Enter employee ID to update.");
            int updateId = 0;
            if (uid != null) {
                updateId = Integer.parseInt(uid);
            }

            try {
                Employee employee = EmployeeService.getEmployeeById(updateId);
                setValue(employee);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

        });
        ViewBtn.addActionListener(e -> {
            String uid = JOptionPane.showInputDialog(null, "Enter ID to View Info.");
            int updateId = 0;
            if (uid != null) {
                updateId = Integer.parseInt(uid);
            }

            try {
                Employee employee = EmployeeService.getEmployeeById(updateId);
                setValue(employee);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

        });
        SaveBtn.addActionListener(e -> {
            boolean isValid = validateData();
            if (isValid) {
                try {
                    EmployeeService.updateEmployee(getEmployeeObj());
                    DefaultTableModel model = (DefaultTableModel) TablePanel.employeeTbl.getModel();
                    model.setRowCount(0);
                    TablePanel.showDataToTable();
                    clearFields();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }

            } else {

                JOptionPane.showMessageDialog(null, "All fields are Required!!");
            }
        });


        DeleteBtn.addActionListener(e -> {
            String id = JOptionPane.showInputDialog(null, "Enter employee ID to delete.");
            if (id != null) {
                int deleteId = Integer.parseInt(id);
                try {
                    EmployeeService.deleteEmployee(deleteId);
                    DefaultTableModel model = (DefaultTableModel) TablePanel.employeeTbl.getModel();
                    model.setRowCount(0);
                    TablePanel.showDataToTable();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });
    }

    // setting value in input fields
    public void setValue(Employee employee) {
        IdField.setText(" "+employee.getId());
        IdField.setEditable(false);
        InsertBtn.setEnabled(false);
        SaveBtn.setEnabled(true);
        NameField.setText(employee.getName());
        String gender = employee.getGender();
        if (gender.equals("Male")) {
            GenderCombo.setSelectedIndex(0);
        } else {
            GenderCombo.setSelectedIndex(1);
        }


        AddressField.setText(employee.getAddress());

        String selectedDepartment = employee.getDepartment();
        switch (selectedDepartment) {
            case "Sales":
                DepartmentCombo.setSelectedIndex(0);
                break;
            case "Human Resources":
                DepartmentCombo.setSelectedIndex(1);
                break;
            case "Information Technology":
                DepartmentCombo.setSelectedIndex(2);
                break;
            case "Marketing":
                DepartmentCombo.setSelectedIndex(3);
                break;
            case "Finance":
                DepartmentCombo.setSelectedIndex(4);
                break;
            case "Operations":
                DepartmentCombo.setSelectedIndex(5);
                break;
            case "Customer Service":
                DepartmentCombo.setSelectedIndex(6);
                break;
            case "Research and Development":
                DepartmentCombo.setSelectedIndex(7);
                break;
        }
       //  String salary=(String)employee.getSalary();
        SalaryField.setText(" "+employee.getSalary());
        byte[] image= employee.getProfilePic();
        ImageIcon imageIcon = new ImageIcon(image);
        Image img = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        imageIcon.setImage(img);
        ImageLbl.setIcon(imageIcon);
        imageData=image;
        SelectedPicLbl.setText(null);
    }

    // choosing image file using JFileChooser
    public void chooseImageFile() {
        JFileChooser fileChooser = new JFileChooser();
        //setting filters
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "jpeg", "png");
        fileChooser.setFileFilter(filter);
        fileChooser.showOpenDialog(null);

        File selectedFile = fileChooser.getSelectedFile();
        fileName = selectedFile.getAbsolutePath();

        //to show selected pic in display
        SelectedPicLbl.setText(selectedFile.getName());
        ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
        Image img = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        imageIcon.setImage(img);
        ImageLbl.setIcon(imageIcon);

        //converting into bytes
        try {
            File image = new File(fileName);
            FileInputStream fis = new FileInputStream(image);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int readNum; (readNum = fis.read(buf)) != -1; ) {
                bos.write(buf, 0, readNum);
            }
            imageData = bos.toByteArray();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    //get obj of new employee
    public Employee getEmployeeObj() {

        String id = IdField.getText().trim();
        String salary = SalaryField.getText().trim();
        String name = NameField.getText().trim();
        int eId = Integer.parseInt(id);
        String gender = GenderCombo.getSelectedItem().toString().trim();
        String address = AddressField.getText();
        String department = DepartmentCombo.getSelectedItem().toString().trim();
        int eSalary = Integer.parseInt(salary);
        byte[] profilePic = imageData;
        return new Employee(eId, name, gender, department, eSalary, address, profilePic);

    }

    //validating empty fields
    public boolean validateData() {
        String name = NameField.getText().trim();
        String id = IdField.getText().trim();
        String gender = GenderCombo.getSelectedItem().toString();
        String address = AddressField.getText().trim();
        String department = DepartmentCombo.getSelectedItem().toString();
        String salary = SalaryField.getText().trim();
        byte[] profilePic = imageData;
        if (name.isEmpty() || id.isEmpty() || gender.isEmpty() || address.isEmpty() || department.isEmpty() || salary.isEmpty() || profilePic == null) {

            return false;
        } else {

            return true;
        }

    }

    //clear fields
    public void clearFields() {
        NameField.setText("");
        IdField.setEditable(true);
        IdField.setText("");
        InsertBtn.setEnabled(true);
        SaveBtn.setEnabled(false);
        GenderCombo.setSelectedIndex(0);
        AddressField.setText("");
        DepartmentCombo.setSelectedIndex(0);
        SalaryField.setText("");
        SelectedPicLbl.setText("");
        ImageLbl.setIcon(null);

    }


    //print employee details
    public static void printEmployee(Employee employee) {
        System.out.println(employee.getName());
        System.out.println(employee.getId());
        System.out.println(employee.getAddress());
        System.out.println(employee.getGender());
        System.out.println(employee.getSalary());
        System.out.println(employee.getDepartment());
        System.out.println(Arrays.toString(employee.getProfilePic()));
    }


}
