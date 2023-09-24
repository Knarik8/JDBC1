package com.epam.jdbc.manager;

import com.epam.jdbc.config.ConnectionProvider;
import com.epam.jdbc.entity.Department;
import com.epam.jdbc.exception.ResourceNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentManager implements Manager<Department, String> {

    private Connection connection;


    @Override
    public void create(Department department) {
        connection = ConnectionProvider.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO departments(dept_no, dept_name) VALUES(?,?)");
            preparedStatement.setString(1, department.getDepartmentNumber());
            preparedStatement.setString(2, department.getDepartmentName());
            int execute = preparedStatement.executeUpdate();
            System.out.println(execute);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public Department getById(String id) {
        connection = ConnectionProvider.getInstance();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM departments where dept_no=?");
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Department department = new Department();
                department.setDepartmentNumber(resultSet.getString("dept_no"));
                department.setDepartmentName(resultSet.getString("dept_name"));

                return department;
            }
        } catch (SQLException e) {
            throw new ResourceNotFoundException(String.format("Given Department with ID: %s not found", id));
        }
        return null;
    }

    @Override
    public List<Department> getAll() {
        connection = ConnectionProvider.getInstance();
        String getAllQuery = "SELECT * FROM departments";
        List<Department> result = new ArrayList<>();

        try {
            ResultSet resultSet = connection.prepareStatement(getAllQuery).executeQuery();
            while (resultSet.next()) {
                Department department = new Department();
                department.setDepartmentNumber(resultSet.getString("dept_no"));
                department.setDepartmentName(resultSet.getString("dept_name"));
                result.add(department);
            }
        } catch (SQLException e) {
            throw new ResourceNotFoundException("Table departments is not found");

        }
        return result;
    }

    @Override
    public void update(Department department) {
        String updateQuery = "UPDATE departments SET dept_name=? where dept_no=?";
        connection = ConnectionProvider.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, department.getDepartmentName());
            preparedStatement.setString(2, department.getDepartmentNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ResourceNotFoundException("Given Department is not found");

        }
    }

    @Override
    public boolean delete(String id) {
        String deleteQuery = "DELETE FROM departments where dept_no = ?";
        connection = ConnectionProvider.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setString(1, id);
            int deletedCount = preparedStatement.executeUpdate();
            return deletedCount > 0;
        } catch (SQLException e) {
            throw new ResourceNotFoundException(String.format("Given Department with ID: %s not found", id));
        }
    }
}
