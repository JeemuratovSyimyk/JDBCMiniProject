package org.example.Service.ServiceImpl;

import org.example.Entity.Employee;
import org.example.Service.EmployeeService;
import org.example.dao.EmployeeDao;
import org.example.dao.daoImpl.EmployeeDaoImpl;

import java.util.List;


public class EmployeeServiceImpl implements EmployeeService {
   EmployeeDao employeeDao = new EmployeeDaoImpl();
    @Override
    public void createEmployee() {
  employeeDao.createEmployee();
    }

    @Override
    public void addEmployee(Employee employee) {
 employeeDao.addEmployee(employee);
    }

    @Override
    public void dropTable() {
  employeeDao.dropTable();
    }

    @Override
    public void cleanTable() {
  employeeDao.cleanTable();
    }

    @Override
    public void updateEmployee(Long id, Employee employee) {
      employeeDao.updateEmployee(id,employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    @Override
    public Employee findByEmail(String email) {
        return employeeDao.findByEmail(email);
    }



    }





