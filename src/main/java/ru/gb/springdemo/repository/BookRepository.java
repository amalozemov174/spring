package ru.gb.springdemo.repository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Entity;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.gb.springdemo.model.BookEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long>, PagingAndSortingRepository<BookEntity, Long>{

//  private final List<Book> books;
//
//  public BookRepository() {
//    this.books = new ArrayList<>();
//    books.addAll(List.of(
//            new Book("война и мир"),
//            new Book("метрвые души"),
//            new Book("чистый код")
//    ));
//  }

//  @PostConstruct
//  public void generateData() {
//    books.addAll(List.of(
//      new Book("война и мир"),
//      new Book("метрвые души"),
//      new Book("чистый код")
//    ));
//  }

  @Query("select b from BookEntity b where b.id = :id")
  public Book getBookById(long id);

  @Query(value = "insert into book (name) values (:name)", nativeQuery = true)
  public Book createBook(String name);

  @Query(value = "delete from book where id = :id", nativeQuery = true)
  public Book deleteBook(long id);

  @Query(value = "select * from book", nativeQuery = true)
  public List<Book> getAllBooks();

}
