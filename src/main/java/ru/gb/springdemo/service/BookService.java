package ru.gb.springdemo.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final ReaderRepository readerRepository;
    @Autowired
    private final IssueRepository issueRepository;


    public Book getBook(long id) {
        return bookRepository.getBookById(id);
    }

    public Book createBook(String name) {
        return bookRepository.createBook(name);
    }

    public Book deleteBook(long id) {
        return bookRepository.deleteBook(id);
    }

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public List<Book> getReaderBooks(List<Long> listBooks) {
        return bookRepository.getReaderBooks(listBooks);
    }
}