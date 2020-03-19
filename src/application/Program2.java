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

        System.out.println("=== TESTE 2: findALL ===");
        List<Department> departmentList = departmentDao.findAll();
        departmentList.forEach(System.out::println);

        System.out.println("=== TESTE 3: Insert ===");
        departmentDao.insert(new Department(null, "DVD"));
        System.out.println("insert feito com sucesso");

        System.out.println("=== TESTE 4: update ===");
        Department department1 = new Department(9, "Rola");
        departmentDao.update(department1);
        System.out.println("Update completo");
    }
}