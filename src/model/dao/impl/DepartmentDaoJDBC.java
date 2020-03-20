package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentDaoJDBC implements DepartmentDao {

    Connection connection = null;

    public DepartmentDaoJDBC (Connection connection){
        this.connection = connection;
    }

    @Override
    public void insert(Department department) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO department "
                    + "(Name) "
                    + "VALUES "
                    + "(?)");

            statement.setString(1,department.getName());
             int rowsAffected = statement.executeUpdate();
             if (rowsAffected == 0){
                 throw new DbException("No rows affected");
             }
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
        }

    }

    @Override
    public void update(Department department) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "UPDATE department "
                    + "SET Name=? "
                    + "WHERE Id=?");
            statement.setString(1, department.getName());
            statement.setInt(2, department.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0){
                throw new DbException("No rows affected");
            }
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
        }
    }

    @Override
    public void deleteById(Integer id) {

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM department WHERE Id = ?");
            statement.setInt(1,id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0){
                throw new DbException("No rows affected");
            }
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
          DB.closeStatement(statement);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(
                    "SELECT * "
                    + "FROM department "
                    + "WHERE Id = ?");
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                return new Department(resultSet.getInt("Id"),resultSet.getString("Name"));
            }
            return null;
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Department> departmentList = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT * FROM department");
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                Department dep = new Department(resultSet.getInt("id"), resultSet.getString("Name"));
                if (!departmentList.contains(dep)){
                    departmentList.add(dep);
                }
            }
            return departmentList;
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }
    }
}
