package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.impl.DepartmentDaoJDBC;
import model.entities.Department;

import java.util.List;

public class Program2 {

    public static void main(String[] args) {

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("=== TESTE 1: findByID ===");
        Department department = departmentDao.findById(7);
        System.out.println(department);

        System.out.println("=== TESTE 1: findByID ===");
        List<Department> departmentList = departmentDao.findAll();
        departmentList.forEach(System.out::println);
    }
}
