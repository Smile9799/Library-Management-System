package com.eve_coding.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.eve_coding.interfaces.IUser;
import com.eve_coding.model.User;

public class UserDAO implements IUser{

	SessionFactory factory = new Configuration()
			.configure("hibernate.cfg.xml")
			.addAnnotatedClass(User.class)
			.buildSessionFactory();
}
