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
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    Connection connection = null;

    public DepartmentDaoJDBC (Connection connection){
        this.connection = connection;
    }

    @Override
    public void insert(Department department) {

    }

    @Override
    public void update(Department department) {

    }

    @Override
    public void deleteById(Integer id) {

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
                departmentList.add(new Department(resultSet.getInt("Id"), resultSet.getString("Name")));
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
