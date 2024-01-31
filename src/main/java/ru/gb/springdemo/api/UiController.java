package ru.gb.springdemo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.service.BookService;
import ru.gb.springdemo.service.IssuerService;
import ru.gb.springdemo.service.ReaderService;

import java.util.List;

@Controller
@RequestMapping("/ui")
public class UiController {

    @Autowired
    private BookService bookService;
    @Autowired
    private ReaderService readerService;
    @Autowired
    private IssuerService issuerService;

    @GetMapping("/books")
    public String books(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/readers")
    public String readers(Model model) {
        List<Reader> readers = readerService.getAllReaders();
        model.addAttribute("readers", readers);
        return "readers";
    }

    @GetMapping("/reader/{id}")
    public String getReadersBooks(@PathVariable(value="id") Integer id, Model model) {
        Reader reader = readerService.getReader(id);
        model.addAttribute("reader", reader);
        List<Long> booksId = issuerService.getBooksIdByReader(id);
        List<Book> books = bookService.getReaderBooks(booksId);
        model.addAttribute("books", books);
        return "reader";
    }

    @GetMapping("/issues")
    public String issues(Model model) {
        List<Issue> issues = issuerService.getAllIssues();
        model.addAttribute("issues", issues);
        return "issues";
    }

}
