package ru.gb.springdemo.model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Запись о факте выдачи книги (в БД)
 */
@Data
public class Issue {

  public static long sequence = 1L;
  private final long id;
  private final long bookId;
  private final long readerId;

  /**
   * Дата выдачи
   */
  //private final LocalDateTime timestamp;
  private final LocalDateTime issuedAt;
  private LocalDateTime returnedAt;

  public Issue(long bookId, long readerId) {
    this.id = sequence++;
    this.bookId = bookId;
    this.readerId = readerId;
    this.issuedAt = LocalDateTime.now();
  }

}
