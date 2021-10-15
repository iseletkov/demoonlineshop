package ru.studyit.testclass.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import ru.studyit.testclass.model.CGood;
import ru.studyit.testclass.model.COrder;
import ru.studyit.testclass.model.CUser;

import java.util.Properties;

public class CConfigHibernate {
    private static SessionFactory sessionFactory;

    private CConfigHibernate() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();//.configure();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "org.postgresql.Driver");
                settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/demoonlineshop");
                settings.put(Environment.USER, "demoonlineshop");
                settings.put(Environment.PASS, "123");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(CUser.class);
                configuration.addAnnotatedClass(COrder.class);
                configuration.addAnnotatedClass(CGood.class);

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }

}
