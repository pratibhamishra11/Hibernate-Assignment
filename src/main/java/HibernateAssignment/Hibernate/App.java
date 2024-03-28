package HibernateAssignment.Hibernate;

import entity.Category;
import entity.Customer;
import entity.CustomerDetail;
import entity.Manufacturer;
import entity.Product;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class App 
{
    public static void main( String[] args )
    {
    	CustomerDetail cd = new CustomerDetail();
   		cd.setAddress("Abc");
   		cd.setPincode(1010);
   		CustomerDetail cd1 = new CustomerDetail();
   		cd1.setAddress("Egf");
   		cd1.setPincode(9010);
   		
   		Product p = new Product();
        p.setProdName("Trends");
        Product p1 = new Product();
        p1.setProdName("Ab");
        
    	SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(cd);
        session.persist(cd1);
        
        // Customer and CustomerDetails have One To One mapping .
        
        Customer c = new Customer();
    	c.setName("Pratibha Mishra");
    	c.setCustomerdetail(cd);
    	Customer c1 = new Customer();
    	c1.setName("Shagun Pandey");
    	c1.setCustomerdetail(cd1);
    	
    	//delete example

      	session.remove(c1);
        
        // Category and Product have One To Many mapping .
        
        ArrayList<Product> l = new ArrayList<Product>();
        l.add(p);
        l.add(p1);
        
        c.setProduct(l);
        
        Category cg = new Category();
   		cg.setCatName("Clothes");
   		cg.setProduct(l);
   		session.persist(cg);
       
        // Product and Manufacturer have Many To One mapping .
        
        Manufacturer m = new Manufacturer();
        m.setManuName("Tata");
        m.setProduct(p);
        m.setProduct(p1);
        
        // Customer and Product have Many To Many mapping .
        
        ArrayList<Customer> cl = new ArrayList<Customer>();
    	cl.add(c);
    	cl.add(c1);
    	
    	c.setProduct(l);
    	p.setCustomer(cl);
    	
        session.persist(c);
        session.persist(c1);
        
        session.persist(p);
        session.persist(p1);
        
        session.persist(m);
        
        session.createQuery("from Customer", Customer.class).list().forEach(System.out::println);
        
        session.createQuery("from Category", Category.class).list().forEach(System.out::println);
        
        session.createQuery("from Manufacturer", Manufacturer.class).list().forEach(System.out::println);
        
        session.getTransaction().commit();
        
        //merge example
		 
      	c.setName("Pratibha");
      	session.beginTransaction();
   		session.merge(c);
   		System.out.println(c);
      	session.getTransaction().commit();
              
  		session.close();
    }
}
