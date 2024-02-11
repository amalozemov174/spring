package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderService {

    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final ReaderRepository readerRepository;
    @Autowired
    private final IssueRepository issueRepository;


    public Reader getReader(long id) {
        return readerRepository.getReaderById(id);
    }

    public Reader createReader(String name) {
        return readerRepository.createReader(name);
    }

    public Reader deleteReader(long id) {
        return readerRepository.deleteReader(id);
    }

    public List<Reader> getAllReaders() {
        return readerRepository.getReaders();
    }
}
