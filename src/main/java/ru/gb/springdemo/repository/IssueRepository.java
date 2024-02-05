package ru.gb.springdemo.repository;

import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Issue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.gb.springdemo.model.IssueEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public interface IssueRepository extends JpaRepository<IssueEntity, Long>, PagingAndSortingRepository<IssueEntity, Long> {

//  private final List<Issue> issues;
//
//  public IssueRepository() {
//    this.issues = new ArrayList<>();
//    issues.add(new Issue(1, 1));
//    issues.add(new Issue(2, 2));
//    issues.add(new Issue(3, 3));
//  }
    @Query(value = "insert into issue (book_id, reder_id, issued_at) values (:book_id, :reder_id, :issued_at)", nativeQuery = true)
    public void save(long book_id, long reder_id, LocalDateTime issued_at);

    @Query("select i.readerId from IssueEntity i where i.id = :id")
    public Issue getIssue(long id);

    @Query(value = "select * from issues where reder_id = :id", nativeQuery = true)
    public List<Issue> getIssuesByReader(long id);

//    @Query(value = "update issues set returned_at = :returned_at where reder_id = :id", nativeQuery = true)
//    public Issue returnBook(long issueId, LocalDateTime returned_at);

    @Query(value = "select * from issues", nativeQuery = true)
    public List<Issue> getIssues();

    @Query(value = "select book_id from issues where reder_id = :id", nativeQuery = true)
    public List<Long> getBooksIdByReader(Integer id);
}
