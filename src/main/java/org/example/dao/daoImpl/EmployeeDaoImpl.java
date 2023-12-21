package org.example.dao.daoImpl;

import org.example.Config.Configuration;
import org.example.Entity.Employee;
import org.example.dao.EmployeeDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public void createEmployee() {
        String sql = "CREATE TABLE IF NOT EXISTS employees(" +
                "id SERIAL PRIMARY KEY," +
                "first_name VARCHAR(50)," +
                "last_name VARCHAR(50)," +
                "age INT," +
                "email VARCHAR UNIQUE)" ;
        try (Connection connection = Configuration.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Table successfully created");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
   }
    }
    @Override
    public void addEmployee(Employee employee) {
        String sql = "insert into employees(" +
                "first_name,last_name,age,email)" +
                "values(?,?,?,?)";

        try (
            Connection connection = Configuration.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setInt(3, employee.getAge());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.executeUpdate();
            System.out.printf("Employee with name=%s is successfully saved!", employee.getFirstName());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        }
    @Override
    public void dropTable() {
   String sql = "drop table employees";
   try (
       Connection connection = Configuration.getConnection();
       Statement statement = connection.createStatement()){
       statement.executeUpdate(sql);
       System.out.println("Table is successfully deleted!");
   }catch (SQLException e){
       System.out.println(e.getMessage());
   }
    }
    @Override
    public void cleanTable() {
    String sql = "DELETE FROM employees";
  try (
         Connection connection = Configuration.getConnection();
         Statement statement = connection.createStatement();){
         statement.executeUpdate(sql);
      System.out.println("Table successfully cleaned");
  }catch (SQLException e){
      System.out.println(e.getMessage());
  }
    }
    @Override
    public void updateEmployee(Long id, Employee employee) {
    String sql =  "" +
            "update employees set first_name=?,last_name=?,age=?,email=? WHERE id=?";
    try (
            Connection connection = Configuration.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

        preparedStatement.setString(1, employee.getFirstName());
        preparedStatement.setString(2, employee.getLastName());
        preparedStatement.setInt(3, employee.getAge());
        preparedStatement.setString(4, employee.getEmail());
        preparedStatement.setLong(5,employee.getId());
        preparedStatement.executeUpdate();
        System.out.printf("Employee with id=%d is successfully updated!", id);
    }catch (Exception e){
        System.out.println(e.getMessage());
    }
    }
    @Override
    public List<Employee> getAllEmployees() {
       List<Employee> employees = new ArrayList<>();
        String sql = "" +
                "select * from employees";
        try (
                Connection connection = Configuration.getConnection();
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                employees.add(new Employee(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"),
                        resultSet.getString("email")
                ));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            }
        return employees;
    }
    @Override
    public Employee findByEmail(String email) {
        Employee employee = new Employee();
        String sql = "" +
                "select * from employees where email like ?";
try (
        Connection connection = Configuration.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
    preparedStatement.setString(1, "%" + email + "%");
    ResultSet resultSet = preparedStatement.executeQuery();
    while (resultSet.next()) {
        employee = (new Employee(
                resultSet.getLong("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getInt("age"),
                resultSet.getString("email")
        ));
    }
}catch (Exception e){
    System.out.println(e.getMessage());
}
        return employee;

    }
}
