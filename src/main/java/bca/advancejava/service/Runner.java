package bca.advancejava.service;

import bca.advancejava.view.MainFrame;


public class Runner extends EmployeeService{
    public static void main(String[] args) {
        try {
             new MainFrame();

        }catch (Exception exception){
            System.err.println(exception);
        }

    }
}
