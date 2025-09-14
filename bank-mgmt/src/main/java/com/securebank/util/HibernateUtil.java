//package com.securebank.util;
//
//import com.securebank.model.User;
//import org.hibernate.SessionFactory;
//import org.hibernate.boot.MetadataSources;
//import org.hibernate.boot.registry.StandardServiceRegistry;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//
//public class HibernateUtil {
//
//    private static SessionFactory sessionFactory;
//
//    public static SessionFactory getSessionFactory() {
//        if (sessionFactory == null) {
//            try {
//                // Build registry from hibernate.cfg.xml
//                StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//                        .configure("hibernate.cfg.xml") // config file in resources
//                        .build();
//
//                // Register entities and build SessionFactory
//                sessionFactory = new MetadataSources(registry)
//                        .addAnnotatedClass(User.class)
//                        .buildMetadata()
//                        .buildSessionFactory();
// 
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return sessionFactory;
//    }
//
//    // Close the SessionFactory
//    public static void shutdown() {
//        if (sessionFactory != null) {
//            sessionFactory.close();
//        }
//    }
//}
