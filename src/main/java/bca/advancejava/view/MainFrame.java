package bca.advancejava.view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
public class MainFrame extends JFrame {
    JPanel FormSection, TableSection;
    JLabel Title;

    public MainFrame() {
        setTitle("Employee Record Management System");
        setBounds(250,100,1050,670);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setLayout(new FlowLayout(FlowLayout.LEADING));

        Container container=getContentPane();

        FormSection =new JPanel();
        TableSection =new JPanel();

        Title=new JLabel("Employee Record Management System");
        FormSection.setPreferredSize(new Dimension(400,620));
        TableSection.setPreferredSize(new Dimension(620,620));
        container.add(FormSection);
        container.add(TableSection);
        JPanel registrationForm=new RegistrationFormPanel();

        registrationForm.setBounds(10,10,380,600);
        FormSection.add(registrationForm);
        FormSection.setLayout(null);
        JPanel employeeTable=new TablePanel();
        employeeTable.setBounds(10,10,600,620);
        TableSection.add(employeeTable);
       // TableSection.setLayout(null);

        setVisible(true);
    }
}

/*      RegistrationFormPanel myForm=new RegistrationFormPanel();

         JTable myTable=TablePanel.employeeTbl;
        DefaultTableModel defaultTableModel=TablePanel.tableModel;//
//        myTable.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                int selectedRow = myTable.getSelectedRow();
//                TableModel model = myTable.getModel();
//                int id = Integer.parseInt(model.getValueAt(selectedRow, 1).toString());
//                String name = model.getValueAt(selectedRow, 2).toString();
//                String gender = model.getValueAt(selectedRow, 3).toString();
//                String address = model.getValueAt(selectedRow, 4).toString();
//                String department = model.getValueAt(selectedRow, 5).toString();
//                int salary = Integer.parseInt(model.getValueAt(selectedRow, 6).toString());
//                byte[] image = model.getValueAt(selectedRow, 7).toString().getBytes();
//                Employee employee=new Employee(id, name, gender, department, salary, address, image);
//                System.out.println(employee.getName());
//
//
//
////                  public Employee(int id, String name, String gender, String department, int salary, String address, byte[] profilePic)
//                //RegistrationFormPanel.printEmployee(new Employee(id, name, gender, department, salary, address, image));
//            }
//        });*/
