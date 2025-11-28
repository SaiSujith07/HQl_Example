package com.hqlqueries;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hqlqueries.entity.Product;

public class App {
    public static void main(String[] args) {
        
    	SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    	
    	Session session = sessionFactory.openSession();
    	
    	Transaction transaction = session.beginTransaction();
    	
    	Product product1 = new Product();
    	product1.setName("Samsung Refridgerator");
    	product1.setPrice(35000);
    	
    	Product product2 = new Product();
    	product2.setName("RealMe 8 Pro");
    	product2.setPrice(21999);
    	
    	Product product3 = new Product();
    	product3.setName("Samsung Galaxy S25 Ultra");
    	product3.setPrice(175000);
    	
    	session.persist(product1);
    	session.persist(product2);
    	session.persist(product3);
    	
    	transaction.commit();
    	
    	Scanner scanner = new Scanner(System.in);
    	
    	
    	while (true) {
    		System.out.println("\n=====HQL OPERATIONS MENU=====");
        	System.out.println("1. Fetch all the products");
        	System.out.println("2. Fetch the products with price > input value");
        	System.out.println("3. Fetch all products ordered by price DESC");
        	System.out.println("4. Update Product price by ID");
        	System.out.println("5. Delete Product by ID");
        	System.out.println("6. Count total number of products");
        	System.out.println("7. Search the products by using LIKE operator");
        	System.out.println("========================================================");
        	System.out.println("Choose an option: ");
        	
        	int choice = scanner.nextInt();
        	scanner.nextLine();
    		
    		switch (choice) {
    		
    		case 1:
    			session.clear();
    			Query<Product> query = session.createQuery("from Product", Product.class);
    			List<Product> productList = query.getResultList();
    			
    			productList.forEach(System.out::println);
    			break;
    		
    		case 2:
    			System.out.print("Enter the minimum price: ");
    			double min = scanner.nextDouble();
    			session.clear();
    			
    			Query<Product> query2 = session.createQuery("from Product where price > :minPrice", Product.class);
    			query2.setParameter("minPrice", min);
    			
    			List<Product> productList2 = query2.getResultList();
    			productList2.forEach(System.out::println);
    			break;
    			
    		case 3:
    			session.clear();
    			Query<Product> query3 = session.createQuery("from Product order by price DESC", Product.class);
    			List<Product> productList3 = query3.getResultList();
    			productList3.forEach(System.out::println);
    			break;
    		
    		case 4:
    			System.out.print("Enter the Product ID to update price: ");
    			int id = scanner.nextInt();
    			System.out.print("Enter the Product Price: ");
    			double price1= scanner.nextDouble();
    			
    			Transaction t4 = session.beginTransaction();
    			
    			@SuppressWarnings("deprecation") 
    			Query<?> query4 = session.createQuery("update Product set price = :newPrice where id = :id");
    			query4.setParameter("newPrice", price1);
    			query4.setParameter("id", id);
    			
    			int rows = query4.executeUpdate();
    			t4.commit();
    			session.clear();
    			System.out.println(rows + " record(s) updated.");
                break;
                
    		case 5:
    			System.out.print("Enter the product ID to delete: ");
    			int delId = scanner.nextInt();
    			
    			Transaction t5 = session.beginTransaction();
//    			Query<Product> query5 = session.createQuery("delete from Product where id = :id", Product.class);
    			@SuppressWarnings("deprecation") 
    			Query<?> query5 = session.createQuery("delete from Product where id = :id");

    			query5.setParameter("id", delId);
    			
    			int deleted = query5.executeUpdate();
    			t5.commit();
    			session.clear();
    			System.out.println(deleted + " record from Product with ID: " + delId);
    			
    			break;
    			
    		case 6:
    			Query<Long> query6 = session.createQuery("select count(*) from Product", Long.class);
    			Long countLong = query6.uniqueResult();
    			
    			System.out.println("Total products: " + countLong);
    			break;
    			
    		case 7:
    			System.out.print("Enter the pattern to search: ");
    			String txt = scanner.next();
    			session.clear();
    			
    			Query<Product> query7 = session.createQuery("from Product where name like :txt", Product.class);
    			query7.setParameter("txt", txt + "%");
    			
    			List<Product> productList4 =  query7.getResultList();
    			productList4.forEach(System.out::println);
    			break;
    		case 0:
                System.out.println("Exiting...");
                //transaction.commit();
                session.close();
                sessionFactory.close();
                scanner.close();
                return;
    			
    		default:
    			System.out.println("Invalid Option! Please Enter valid option");
    		}
    	
    	}
    }
}
