package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.api.IssueRequest;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class IssuerService {

  // спринг это все заинжектит
  private final BookRepository bookRepository;
  private final ReaderRepository readerRepository;
  private final IssueRepository issueRepository;
  @Value("${application.max-allowed-books}")
  private int bookAllowed;

  public Issue issue(IssueRequest request) {
    if (bookRepository.getBookById(request.getBookId()) == null) {
      throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
    }
    if (readerRepository.getReaderById(request.getReaderId()) == null) {
      throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
    }
    if (issueRepository.readerHasBook(request.getReaderId(), bookAllowed)) {
      throw new NoSuchElementException("У читателя есть книги на руках \"" + request.getReaderId() + "\"");
    }
    // можно проверить, что у читателя нет книг на руках (или его лимит не превышает в Х книг)

    Issue issue = new Issue(request.getBookId(), request.getReaderId());
    issueRepository.save(issue);
    return issue;
  }

    public Issue getIssue(int id) {
      final Issue issue = issueRepository.getIssue(id);
      if(issue != null) {
        return issue;
      }
      throw new NoSuchElementException("Выдача с таким идентификатором не найдена \"" + id + "\"");
    }

    public List<Issue> getIssuesByReader(long id) {
      List<Issue> issues = issueRepository.getIssuesByReader(id);
      return issues;
    }

  public Issue issue(long issueId) {
    final Issue issue = issueRepository.returnBook(issueId);;
    if(issue != null) {
      return issue;
    }
    throw new NoSuchElementException("Выдача с таким идентификатором не найдена \"" + issueId + "\"");
  }

    public List<Issue> getAllIssues() {
      return issueRepository.getIssues();
    }

  public List<Long> getBooksIdByReader(Integer id) {
    return issueRepository.getBooksIdByReader(id);
  }
}
