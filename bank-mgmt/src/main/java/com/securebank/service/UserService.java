package com.securebank.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.securebank.model.User;
//import com.securebank.util.HibernateUtil;

import jakarta.transaction.Transactional;

import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	public void saveUser(User user) {	
		
		//without spring
		//Open session
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		Transaction tx = null;
//		
//		try {
//			tx =  session.beginTransaction();
//			//save user
//			session.persist(user);
//			
//			tx.commit();
//			System.out.println("User saved successfully "+user);
//		}catch (Exception e) {
//            if (tx != null) tx.rollback(); // rollback if error
//            e.printStackTrace();
//        } finally {
//            session.close(); // close session
//        }
		
		//with spring
		getSession().persist(user);
        System.out.println("User saved successfully " + user);

		
	}
	
	public void deleteUser(int id) {
		
		//without spring
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		Transaction tx = null;
//		
//		try {
//			tx= session.beginTransaction();
//			
//			//get user by ID
//			User user = session.find(User.class, id);
//			if(user!=null) {
//				session.remove(user);
//				System.out.println("User deleted successfully: " + user);
//			}else {
//	            System.out.println("User with id " + id + " not found.");
//
//			}
//			
//			tx.commit();
//		}catch (Exception e) {
//	        if (tx != null) tx.rollback();
//	        e.printStackTrace();
//	    } finally {
//	        session.close();
//	    }
		
		 User user = getSession().find(User.class, id);
	        if (user != null) {
	            getSession().remove(user);
	            System.out.println("User deleted successfully: " + user);
	        } else {
	            System.out.println("User with id " + id + " not found.");
	        }
		
	}
	
	public void updateUser(int id, String newFName, String newLName, String newEmail) {
   
		//without spring
	//	Session session = HibernateUtil.getSessionFactory().openSession();
//	    Transaction tx = null;
//
//	    try {
//	        tx = session.beginTransaction();
//
//	        User user = session.find(User.class, id); // fetch user
//	        if (user != null) {
//	            user.setfName(newFName);
//	            user.setlName(newLName);
//	            user.setEmail(newEmail);
//	            session.merge(user); // save changes
//	            System.out.println("User updated successfully: " + user);
//	        } else {
//	            System.out.println("User with id " + id + " not found.");
//	        }
//
//	        tx.commit();
//	    } catch (Exception e) {
//	        if (tx != null) tx.rollback();
//	        e.printStackTrace();
//	    } finally {
//	        session.close();
//	    }
	    
		 User user = getSession().find(User.class, id);
	        if (user != null) {
	            user.setfName(newFName);
	            user.setlName(newLName);
	            user.setEmail(newEmail);
	            getSession().merge(user);
	            System.out.println("User updated successfully: " + user);
	        } else {
	            System.out.println("User with id " + id + " not found.");
	        }
		
	}
	
	
	public List<User> getAllUsers() {
		
		//without spring
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        List<User> users = null;
//
//        try {
//            users = session.createQuery("FROM User", User.class).list(); // HQL query
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//
//        return users;
		
        return getSession().createQuery("FROM User", User.class).list();

    }
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return getSession().find(User.class, id);
	}

	
}
