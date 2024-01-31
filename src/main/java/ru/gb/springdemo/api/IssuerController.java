package ru.gb.springdemo.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.service.IssuerService;

import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/issue")
public class IssuerController {

  private final IssuerService service;

  public IssuerController(IssuerService service) {
    this.service = service;
  }

  @PutMapping("{issueId}")
  public ResponseEntity<Issue> returnBook(@PathVariable(value="issueId") String id) {
    // найти в репозитории выдачу и проставить ей returned_at
    final Issue issue;
    try {
      issue = service.issue(Long.parseLong(id));
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @PostMapping
  public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest request) {
    log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());
    final Issue issue;
    try {
      issue = service.issue(request);
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.status(HttpStatus.CONFLICT).body(issue);
  }

  @GetMapping("{id}")
  public ResponseEntity<Issue> getIssue(@PathVariable(value="id") String id) {
    final Issue issue;
    try {
      issue = service.getIssue(Integer.parseInt(id));
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(issue);
  }

}
