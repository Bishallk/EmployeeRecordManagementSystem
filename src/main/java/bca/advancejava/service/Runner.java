package bca.advancejava.service;

import bca.advancejava.view.MainFrame;


public class Runner extends EmployeeService{
    public static void main(String[] args) {
        try {
             new MainFrame();
            //System.out.println(getEmployeeList());
             //new EmployeeController();
            // BrowseBtn.addActionListener(e ->chooseImageFile() );
           // ArrayList<Employee> myList=new ArrayList<>();
//            for (Employee e: getEmployeeList()
//                 ) {
//                System.out.println(e.getName());
//            }



        }catch (Exception e){
            System.out.println(e);
        }

    }
}
