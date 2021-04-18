package com.eve_coding.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eve_coding.dao.BookDAO;
import com.eve_coding.model.Book;


@WebServlet("/Default")
public class DefaultController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	BookDAO bookDAO = null;
    public DefaultController() {
       bookDAO = new BookDAO();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter p = response.getWriter();
		String page = request.getParameter("page");
		String ISBN = request.getParameter("ISBN");
		
		switch(page) {
		case "home":
			List<Book> books =new BookDAO().getAllBooks();
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
			List<Book> books1 =new BookDAO().getAllBooks();
			request.setAttribute("books", books1);
			request.getRequestDispatcher("/views/manage-books.jsp").forward(request, response);
			break;
		case "request":
			request.getRequestDispatcher("/views/see-book-request.jsp");
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
