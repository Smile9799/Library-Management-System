package com.eve_coding.dao;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Base64;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.eve_coding.interfaces.IBook;
import com.eve_coding.model.Book;

public class BookDAO implements IBook {

	private Session session = null;

	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Book.class)
			.buildSessionFactory();

	@Override
	public boolean addBook(Book book) {
		session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(book);
			session.getTransaction().commit();
		}catch(Exception e) {
			if(session.getTransaction() !=null) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getAllBooks() {
		session = factory.getCurrentSession();
		List<Book> books = null;
		try {
			session.beginTransaction();
			books = session.createQuery("from books").getResultList();
			for(Book book : books) {
				book.setImageBase64String(Base64.getEncoder().encodeToString(book.getBookImage()));
			}
			session.getTransaction().commit();
		}catch(Exception e) {
			if(session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		
		return books;
	}

	@Override
	public Book getBookDetails(String ISBN) {
		session = factory.getCurrentSession();
		Book book = null;
		try {
			session.beginTransaction();
			//book = session.get(Book.class, id);
			book = (Book)session.createQuery("from books b where b.ISBN = :ISBN").setParameter("ISBN", ISBN).uniqueResult();
			book.setImageBase64String(Base64.getEncoder().encodeToString(book.getBookImage()));
			session.getTransaction().commit();
		}catch(Exception e) {
			if(session.getTransaction() !=null) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		return book;
	}
	@Override
	public boolean updateBook(Book book) {
		System.out.print(book.toString());
		session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			session.update(book);
			session.getTransaction().commit();
		}catch(Exception e) {
			if(session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean deleteBook(int id) {
		session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			Book book = session.get(Book.class, id);
			if(book != null) {
				session.delete(book);
			}
			session.getTransaction().commit();
		}catch(Exception e) {
			if(session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		return true;
	}

}
