package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;

public class Program {

    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== TESTE 1: findByID ===");
        Seller sellerById = sellerDao.findById(3);
        System.out.println(sellerById);
        System.out.println("=== TESTE 2: findByDepartment ===");
        Department department = new Department(1, "Computers");
        List<Seller> sellerByDepartment = sellerDao.findByDepartment(department);

        sellerByDepartment.forEach(System.out::println);
    }
}
