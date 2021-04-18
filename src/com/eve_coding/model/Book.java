package com.eve_coding.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name = "books")
@Table(name = "books")
public class Book {

	@Id
	@Column(name = "book_id")
	private int bookId;
	@Column(name = "ISBN")
	private String ISBN;
	@Column(name = "book_price")
	private double bookPrice;
	@Column(name = "book_name")
	private String bookName;
	@Column(name = "author_name")
	private String bookAuthor;
	@Column(name = "book_description")
	private String bookDescription;
	@Column(name = "book_photho")
	private byte[] bookImage;
	@Transient
	private String imageBase64String;

	public Book() {
	}

	public Book(int bookId, String iSBN, double bookPrice, String bookName, String bookAuthor, String bookDescription,
			byte[] bookImage, String imageBase64String) {
		this.bookId = bookId;
		this.ISBN = iSBN;
		this.bookPrice = bookPrice;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.bookDescription = bookDescription;
		this.bookImage = bookImage;
		this.imageBase64String = imageBase64String;
	}
	

	public Book(String iSBN, double bookPrice, String bookName, String bookAuthor, String bookDescription,
			byte[] bookImage) {
		super();
		ISBN = iSBN;
		this.bookPrice = bookPrice;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.bookDescription = bookDescription;
		this.bookImage = bookImage;
	}

	public Book(int bookId, String iSBN, double bookPrice, String bookName, String bookAuthor, String bookDescription,
			byte[] bookImage) {
		this.bookId = bookId;
		this.ISBN = iSBN;
		this.bookPrice = bookPrice;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.bookDescription = bookDescription;
		this.bookImage = bookImage;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookDescription() {
		return bookDescription;
	}

	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}

	public byte[] getBookImage() {
		return bookImage;
	}

	public void setBookImage(byte[] bookImage) {
		this.bookImage = bookImage;
	}

	public String getImageBase64String() {
		return imageBase64String;
	}

	public void setImageBase64String(String imageBase64String) {
		this.imageBase64String = imageBase64String;
	}

	@Override
	public String toString() {
		return "Book [ISBN=" + ISBN + ", bookPrice=" + bookPrice + ", bookName=" + bookName + ", bookAuthor="
				+ bookAuthor + ", bookDescription=" + bookDescription + ", bookImage=" + Arrays.toString(bookImage)
				+ ", imageBase64String=" + imageBase64String + "]";
	}

}
