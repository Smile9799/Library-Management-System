package com.eve_coding.controller;

import java.awt.image.BufferedImage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.eve_coding.dao.BookDAO;
import com.eve_coding.model.Book;


@WebServlet("/Admin")
@MultipartConfig(fileSizeThreshold=1024*1024*10,  // 10 MB 
				 maxFileSize=1024*1024*50,       // 50 MB
				 maxRequestSize=1024*1024*100)    // 100 MB
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDAO bookDAO = null;

	public AdminController() {
		bookDAO = new BookDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("action");
		String ISBN = request.getParameter("ISBN");
		Book book = null;
		switch(action) {
		case "update":
			book = bookDAO.getBookDetails(ISBN);
			request.setAttribute("imgBase64Str", book.getImageBase64String());
			request.setAttribute("id", book.getBookId());
			request.setAttribute("ISBN", book.getISBN());
			request.setAttribute("price", book.getBookPrice());
			request.setAttribute("name", book.getBookName());
			request.setAttribute("description", book.getBookDescription());
			request.setAttribute("author", book.getBookAuthor());
			request.getRequestDispatcher("/views/update-book.jsp").forward(request, response);
			break;
		case "delete":
			book = bookDAO.getBookDetails(ISBN);
			request.setAttribute("imgBase64Str", book.getImageBase64String());
			request.setAttribute("id", book.getBookId());
			request.setAttribute("ISBN", book.getISBN());
			request.setAttribute("price", book.getBookPrice());
			request.setAttribute("name", book.getBookName());
			request.setAttribute("description", book.getBookDescription());
			request.setAttribute("author", book.getBookAuthor());
			request.getRequestDispatcher("/views/book-confirm-delete.jsp").forward(request, response);
			break;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String ISBN = request.getParameter("bookISBN");
		String bookName = request.getParameter("bookName");
		String authorName = request.getParameter("authorName");
		String textPrice = request.getParameter("bookPrice");
		String bookDescription = request.getParameter("bookDescription");
		String imgBase64Str = request.getParameter("hiddenBase64Str");
		String bookIdText = request.getParameter("id"); 
		Part part = request.getPart("bookImage");
		

		BufferedImage bufferimage = null;
		ByteArrayOutputStream output = null;
		byte[] imageArray = null;
		switch (action) {
		case "add":{
			if (part != null) {
				bufferimage = ImageIO.read(part.getInputStream());
				output = new ByteArrayOutputStream();

				ImageIO.write(bufferimage, "jpg", output);
				imageArray = output.toByteArray();
			}
			double bookPrice = Double.parseDouble(textPrice);
			Book book = new Book(ISBN, bookPrice, bookName, authorName, bookDescription, imageArray);
			addNewBook(book, request, response);
			break;
		}
		case "update":{
			if(part == null) {
				imageArray = Base64.getDecoder().decode(new String(imgBase64Str).getBytes("UTF-8"));
			}else {
				bufferimage = ImageIO.read(part.getInputStream());
				if(bufferimage !=null) {
					output = new ByteArrayOutputStream();
					
					ImageIO.write(bufferimage, "jpg", output);
					imageArray = output.toByteArray();
				}else {
					imageArray = Base64.getDecoder().decode(new String(imgBase64Str).getBytes("UTF-8"));
				}
			}
			int bookId = Integer.parseInt(bookIdText);
			double bookPrice = Double.parseDouble(textPrice);
			Book book = new Book(bookId,ISBN, bookPrice, bookName, authorName, bookDescription, imageArray);
			updateBook(book,request,response);
			break;
		}
		case "delete":
			int bookId = Integer.parseInt(bookIdText);
			deleteBook(bookId, request, response);
			break;
		}
	}

	private void updateBook(Book book, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(bookDAO.updateBook(book)) {
			response.sendRedirect(request.getContextPath()+"/Default?page=home");
		}
	}

	private void addNewBook(Book book,HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (bookDAO.addBook(book)) {
			response.sendRedirect(request.getContextPath()+"/Default?page=home");
		}
	}
	
	private void deleteBook(int bookId, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(bookDAO.deleteBook(bookId)) {
			response.sendRedirect(request.getContextPath()+"/Default?page=home");
		}
	}
}
