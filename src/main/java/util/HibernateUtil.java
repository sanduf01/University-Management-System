package util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import model.Student;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings (Java-based configuration)
                Properties settings = new Properties();

                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL,
                        "jdbc:mysql://localhost:3306/ousl_management_system?useSSL=false&serverTimezone=UTC");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "");

                settings.put(Environment.DIALECT,
                        "org.hibernate.dialect.MySQL8Dialect");

                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.FORMAT_SQL, "true");

                // Safer for MariaDB / MySQL
                settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                configuration.setProperties(settings);

                // Register entity class
                configuration.addAnnotatedClass(Student.class);

                ServiceRegistry serviceRegistry =
                        new StandardServiceRegistryBuilder()
                                .applySettings(configuration.getProperties())
                                .build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
