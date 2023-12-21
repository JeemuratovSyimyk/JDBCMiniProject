package org.example;

import org.example.Entity.Employee;
import org.example.Service.EmployeeService;
import org.example.Service.ServiceImpl.EmployeeServiceImpl;

import java.util.Scanner;

public class App {
    public static void main( String[] args ) {

        Scanner scannerNum=new Scanner(System.in);
        EmployeeService employeeService = new EmployeeServiceImpl();

        int num;
        while(true){
            System.out.println(""" 
                     ______________________________________________________________
                   ||_______________________  Employee ____________________________|| 
                   ||                                                              ||   
                   || 1-create table employees      ||    5-update employee        ||
                   || 2-save employee               ||    6-get all employees      ||
                   || 3-drop table employees        ||    7-find employee by email ||  
                   || 4-clean table employees       ||                             ||
                   ||______________________________________________________________||    
                   ||______________________________________________________________||  
                    """);
            switch (num=scannerNum.nextInt()) {

                case 1-> employeeService.createEmployee();
                case 2-> employeeService.addEmployee(new Employee("Syimyk","Ulukbek Uulu",21,"ulukbekuulusyimyk50@gmail.com"));
                case 3-> employeeService.dropTable();
                case 4-> employeeService.cleanTable();
                case 5-> employeeService.updateEmployee(2L,new Employee("Aizat","Asanova",20,"Asanova@gmail.com"));
                case 6-> System.out.println(employeeService.getAllEmployees());
                case 7-> System.out.println(employeeService.findByEmail("ulukbekuulusyimyk50@gmail.com"));

            }
            }
    }
}
