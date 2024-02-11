package ru.gb.springdemo.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import jakarta.persistence.*;

@Data
public class Reader {
  public static long sequence = 1L;
  private final long id;
  private final String name;
}