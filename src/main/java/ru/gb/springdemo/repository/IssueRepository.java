package ru.gb.springdemo.repository;

import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Issue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class IssueRepository {

  private final List<Issue> issues;

  public IssueRepository() {
    this.issues = new ArrayList<>();
    issues.add(new Issue(1, 1));
    issues.add(new Issue(2, 2));
    issues.add(new Issue(3, 3));
  }

  public void save(Issue issue) {
    // insert into ....
    issues.add(issue);
  }

    public Issue getIssue(int id) {
        for (Issue issue : issues) {
            if (issue.getId() == id) {
                return issue;
            }
        }
        return null;
    }

    public boolean readerHasBook(long readerId, int bookAllowed) {
      boolean resIssue = true;
      int tmpCount = 0;
      for (Issue issue : issues) {
          if (issue.getReaderId() == readerId) {
              if (issue.getReturnedAt() != null) {
                  if (tmpCount >= bookAllowed) {
                      resIssue = false;
                  }
                  tmpCount++;
              }
          }
      }
      return resIssue;
    }

    public List<Issue> getIssuesByReader(long id) {
      List<Issue> resIssues = new ArrayList<>();
      for (Issue issue : issues) {
          if (issue.getReaderId() == id) {
              resIssues.add(issue);
          }
      }
      return resIssues;
    }

    public Issue returnBook(long issueId) {
        final Issue issue = issues.stream().filter(it -> Objects.equals(it.getId(), issueId))
                .findFirst()
                .orElse(null);
        if (issue != null) {
            issue.setReturnedAt(LocalDateTime.now());
        }
        return issue;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public List<Long> getBooksIdByReader(Integer id) {
      List<Long> resBooks = new ArrayList<>();
      for (Issue issue : issues) {
          if (issue.getReaderId() == id) {
              if (issue.getReturnedAt() == null) {
                  resBooks.add(issue.getBookId());
              }
          }
      }
      return resBooks;
    }
}
