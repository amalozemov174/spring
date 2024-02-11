package ru.gb.springdemo.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Reader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.gb.springdemo.model.ReaderEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public interface ReaderRepository extends JpaRepository<ReaderEntity, Long>, PagingAndSortingRepository<ReaderEntity, Long> {

  //private final List<Reader> readers;

//  public ReaderRepository() {
//    this.readers = new ArrayList<>();
//  }
//
//  @PostConstruct
//  public void generateData() {
//    readers.addAll(List.of(
//      new ru.gb.springdemo.model.Reader("Игорь"), new ru.gb.springdemo.model.Reader("Павел"), new ru.gb.springdemo.model.Reader("Петя")
//    ));
//  }
  @Query("select ReaderEntity from ReaderEntity r where r.id = :id")
  public Reader getReaderById(long id);

  @Query(value = "insert into reader (name) values (:name)", nativeQuery = true)
  public Reader createReader(String name);

  @Query(value = "delete from reader where id = :id", nativeQuery = true)
  public Reader deleteReader(long id);

  @Query(value = "select * from reader", nativeQuery = true)
  public List<Reader> getReaders();

}