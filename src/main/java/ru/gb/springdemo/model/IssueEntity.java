package ru.gb.springdemo.model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Запись о факте выдачи книги (в БД)
 */
@Entity
@Table(name = "issue")
@Data
public class IssueEntity {

    public static long sequence = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private final long id;
    @Column(name = "book_id")
    private final long bookId;
    @Column(name = "reader_id")
    private final long readerId;

    /**
     * Дата выдачи
     */
    //private final LocalDateTime timestamp;
    @Column(name = "issued_at")
    private final LocalDateTime issuedAt;
    @Column(name = "returned_at")
    private LocalDateTime returnedAt;

    public IssueEntity(long bookId, long readerId) {
        this.id = sequence++;
        this.bookId = bookId;
        this.readerId = readerId;
        this.issuedAt = LocalDateTime.now();
    }

}
