package ru.gb.springdemo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.service.IssuerService;
import ru.gb.springdemo.service.ReaderService;

import java.util.List;

@RestController
@RequestMapping("/reader")
public class ReaderController {

    @Autowired
    private ReaderService readerService;

    @Autowired
    private IssuerService issuerService;

    @GetMapping("/{id}")
    public ResponseEntity<Reader> getReader(@PathVariable(value="id") String id) {
        Reader reader = readerService.getReader(Long.parseLong(id));
        if (reader == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(reader);
    }

    @GetMapping("/{id}/issue")
    public ResponseEntity<List<Issue>> getIssuesForReader(@PathVariable(value="id") String id) {
        Reader reader = readerService.getReader(Long.parseLong(id));
        if (reader == null) {
            return ResponseEntity.notFound().build();
        }
        List<Issue> issues = issuerService.getIssuesByReader(Long.parseLong(id));
        if (issues == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(issues);
    }

    @PostMapping
    public ResponseEntity<Reader> createReader(@RequestBody ReaderRequest readerRequest) {
        Reader reader = readerService.createReader(readerRequest.getName());
        if (reader == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(reader);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reader> deleteReader(@PathVariable(value="id") String id) {
        Reader reader = readerService.deleteReader(Long.parseLong(id));
        if (reader == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(reader);
    }

}