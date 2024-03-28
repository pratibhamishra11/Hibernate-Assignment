package HibernateAssignment.Hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import entity.Category;
import entity.Customer;
import entity.CustomerDetail;
import entity.Manufacturer;
import entity.Product;

import java.util.Properties;

public class HibernateUtils {
	@SuppressWarnings("deprecation")
	public static SessionFactory getSessionFactory(){
        Configuration configuration = new Configuration();
        SessionFactory sessionFactory = null;

        Properties settings = new Properties();
        settings.put(Environment.DRIVER, "org.hsqldb.jdbc.JDBCDriver");
        settings.put(Environment.URL, "jdbc:hsqldb:mem:myDb");
        settings.put(Environment.USER, "sa");
        settings.put(Environment.PASS, "sa");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        settings.put(Environment.HBM2DDL_AUTO, "create-drop");

        configuration.setProperties(settings);
        configuration.addAnnotatedClass(Customer.class);
        configuration.addAnnotatedClass(CustomerDetail.class);
        
        configuration.addAnnotatedClass(Category.class);
        configuration.addAnnotatedClass(Product.class);
        
        configuration.addAnnotatedClass(Manufacturer.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        return sessionFactory;
    
    }

}
