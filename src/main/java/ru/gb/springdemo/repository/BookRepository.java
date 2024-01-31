package ru.gb.springdemo.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class BookRepository {

  private final List<Book> books;

  public BookRepository() {
    this.books = new ArrayList<>();
    books.addAll(List.of(
            new Book("война и мир"),
            new Book("метрвые души"),
            new Book("чистый код")
    ));
  }

//  @PostConstruct
//  public void generateData() {
//    books.addAll(List.of(
//      new Book("война и мир"),
//      new Book("метрвые души"),
//      new Book("чистый код")
//    ));
//  }

  public Book getBookById(long id) {
    return books.stream().filter(it -> Objects.equals(it.getId(), id))
      .findFirst()
      .orElse(null);
  }

  public Book createBook(String name) {
    Book book = new Book(name);
    books.add(book);
    return book;
  }

  public Book deleteBook(long id) {
    Book resBook = null;
    for(Book book : books) {
      if (book.getId() == id) {
        resBook = book;
      }
    }
    books.remove(resBook);
    return resBook;
  }

  public List<Book> getAllBooks() {
    return books;
  }

    public List<Book> getReaderBooks(List<Long> listBooks) {
      List<Book> resBooks = new ArrayList<>();
      for (Book book : books) {
        if (listBooks.contains(book.getId())) {
          resBooks.add(book);
        }
      }
      return resBooks;
    }
}
