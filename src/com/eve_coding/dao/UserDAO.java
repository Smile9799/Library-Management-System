package com.eve_coding.dao;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.eve_coding.interfaces.IUser;
import com.eve_coding.model.User;
import com.eve_coding.utils.PasswordSaltingAndHashing;

public class UserDAO implements IUser {

	Session session = null;
	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
			.buildSessionFactory();

	@Override
	public boolean registerUser(User user) {
		session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public User getUserLogin(String email, String password) {
		session = factory.getCurrentSession();
		System.out.println(password);
		User user = null;
		try {
			session.beginTransaction();
			user = (User) session.createQuery("from users u where u.email = :email").setParameter("email", email)
					.getSingleResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String securePassword = PasswordSaltingAndHashing.getSecurePassword(password, user.getPasswordSalt());
		if (user.getPassword().equals(securePassword)) {
			return user;
		}
		else {
			return null;
		}
	}
}
