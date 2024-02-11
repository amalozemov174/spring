package ru.gb.springdemo.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import jakarta.persistence.*;

@Entity
@Table(name = "reader")
@Data
public class ReaderEntity {

    public static long sequence = 1L;
    @Id
    private final long id;
    @Column(name = "name")
    private final String name;

//  public Reader(String name) {
//    this(sequence++, name);
//  }

}