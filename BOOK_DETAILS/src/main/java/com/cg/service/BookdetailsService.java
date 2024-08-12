package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cg.exceptions.BookdetailsAlreadyFoundException;
import com.cg.exceptions.BookdetailsNotFoundException;
import com.cg.model.Book;
import com.cg.repository.BookdetailsRepository;

@Service
@Component
public class BookdetailsService implements BookdetailsInterface {
	private BookdetailsRepository bookRepository;
	BookdetailsService bookservice;

	@Autowired
	public BookdetailsService(BookdetailsRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}

	@Override
	public Book addBook(Book book) {
		System.out.println("adding a book");
		// check if book is not present -> then add else throw custom exception
		if (bookRepository.findById(book.getId()) == null) {
			bookRepository.save(book);
			return book;
		}
		throw new BookdetailsAlreadyFoundException();
	}

	@Override
	public String deleteBook(int id) {
		if (bookRepository.findById(id) != null) {
			bookRepository.deleteById(id);
			return " id deleted";
		}
		throw new BookdetailsNotFoundException();
	}

	@Override
	public Book update(Book book) {
		Book book2 = bookRepository.findById(book.getId());
		if (book2 != null) {

			if (book.getBook_name() == null || book.getBook_name() == "") {
				book.setBook_name(book2.getBook_name());
			}

			if (book.getBook_author() == null || book.getBook_author() == "") {
				book.setBook_author(book2.getBook_author());
			}

			if (book.getBook_price() == null || book.getBook_price() == "") {
				book.setBook_price(book2.getBook_price());
			}

			return bookRepository.save(book);
		}
		throw new BookdetailsNotFoundException();
	}

	@Override
	public List<Book> getBook() {
		return (List<Book>) bookRepository.findAll();
	}

	@Override
	public Book getBookById(int id) {
		Book book = bookRepository.findById(id);
		if (book != null) {
			return book;
		}
		throw new BookdetailsNotFoundException();
	}
}
