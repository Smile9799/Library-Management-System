package com.eve_coding.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eve_coding.dao.BookDAO;
import com.eve_coding.dao.UserDAO;
import com.eve_coding.model.Book;
import com.eve_coding.model.User;
import com.eve_coding.utils.PasswordSaltingAndHashing;

@WebServlet("/Default")
public class DefaultController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BookDAO bookDAO = null;
	private UserDAO userDAO = null;
	private PasswordSaltingAndHashing passwordSaltingAndHashing = null;

	public DefaultController() {
		bookDAO = new BookDAO();
		passwordSaltingAndHashing = new PasswordSaltingAndHashing();
		userDAO = new UserDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter p = response.getWriter();
		String page = request.getParameter("page");
		String ISBN = request.getParameter("ISBN");

		switch (page) {
		case "home":
			List<Book> books = new BookDAO().getAllBooks();
			request.setAttribute("books", books);
			request.getRequestDispatcher("/views/home.jsp").forward(request, response);
			break;
		case "profile":
			request.getRequestDispatcher("/views/user-profile.jsp").forward(request, response);
			break;
		case "add":
			request.getRequestDispatcher("/views/add-book.jsp").forward(request, response);
			break;
		case "management":
			List<Book> books1 = new BookDAO().getAllBooks();
			request.setAttribute("books", books1);
			request.getRequestDispatcher("/views/manage-books.jsp").forward(request, response);
			break;
		case "login":
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			break;
		case "request":
			request.getRequestDispatcher("/views/see-book-request.jsp");
			break;
		case "registration":
			request.getRequestDispatcher("/views/registration.jsp").forward(request, response);
			break;
		case "item":
			Book book = bookDAO.getBookDetails(ISBN);
			request.setAttribute("imgBase64Str", book.getImageBase64String());
			request.setAttribute("id", book.getBookId());
			request.setAttribute("ISBN", book.getISBN());
			request.setAttribute("price", book.getBookPrice());
			request.setAttribute("name", book.getBookName());
			request.setAttribute("description", book.getBookDescription());
			request.setAttribute("author", book.getBookAuthor());
			request.getRequestDispatcher("/views/book-item.jsp").forward(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		byte[] salt = null;
		try {
			salt = PasswordSaltingAndHashing.getSalt();
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			e.printStackTrace();
		}
		String securePassword = PasswordSaltingAndHashing.getSecurePassword(password, salt);
		switch (action) {
		case "login":
			loginUser(email, password, request, response);
			break;
		case "register":
			User user = new User(name, securePassword, salt, email, "customer");
			registerNewUser(user, request, response);
			break;
		}
	}

	private void loginUser(String email, String password, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = userDAO.getUserLogin(email, password);
		if(user != null) {
			response.sendRedirect(request.getContextPath() + "/Default?page=home");
		}else {
			response.sendRedirect(request.getContextPath() + "/Default?page=login");
		}
	}

	private void registerNewUser(User user, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (userDAO.registerUser(user)) {
			response.sendRedirect(request.getContextPath() + "/Default?page=login");
		}
	}

}
