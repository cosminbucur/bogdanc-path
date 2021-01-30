package com.sda.spring.boot.rest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.spring.boot.rest.dto.BookMapper;
import com.sda.spring.boot.rest.dto.BookRequest;
import com.sda.spring.boot.rest.dto.BookResponse;
import com.sda.spring.boot.rest.exception.NotFoundException;
import com.sda.spring.boot.rest.model.Book;
import com.sda.spring.boot.rest.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServicePro implements BookService {

    private static final Logger log = LoggerFactory.getLogger(BookServicePro.class);

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookServicePro(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<BookResponse> findAll() {
        log.debug("finding all books");

        // find books
        // convert book to response (dto)
        // return list of dtos
        return bookRepository.findAll().stream()
                .map(book -> bookMapper.toDto(book))
                .collect(Collectors.toList());
    }

    @Override
    public BookResponse findById(Long id) {
        log.debug("finding book by id: {}", id);

        return bookRepository.findById(id)
                .map(book -> bookMapper.toDto(book))
                .orElseThrow(() -> new NotFoundException("book not found"));
    }

    @Override
    public BookResponse save(BookRequest createBookRequest) {
        log.debug("saving book: {}", createBookRequest);

        // check if book already exists
        boolean exists = bookRepository.findByTitle(createBookRequest.getTitle()).isPresent();
        if (exists) {
            throw new NotFoundException("duplicate book");
        }

        // convert request to entity
        Book book = bookMapper.toEntity(createBookRequest);

        // save
        Book newBook = bookRepository.save(book);

        // convert to response
        return bookMapper.toDto(newBook);
    }

    @Override
    public BookResponse update(Long id, BookRequest updateRequest) {
        log.debug("updating book with id: {} and request body: {}", id, updateRequest);

        return bookRepository.findById(id)
                .map(book -> bookMapper.toEntity(book, updateRequest))
                .map(book -> bookRepository.save(book))
                .map(book -> bookMapper.toDto(book))
                .orElseThrow(() -> new NotFoundException("book not found"));
    }

    @Override
    public void delete(Long id) {
        log.debug("deleting book with id: {}", id);

        bookRepository.findById(id)
                .map(book -> {
                    bookRepository.deleteById(id);
                    return book;
                })
                .orElseThrow(() -> new NotFoundException("book not found"));
    }

    // find using filter
    @Override
    public List<BookResponse> findByAuthor(String author) {
        log.debug("finding books by author: {}", author);

        return bookRepository.findAll()
                .stream()
                .filter(book -> book.getAuthor().equals(author))
                .map(book -> bookMapper.toDto(book))
                .collect(Collectors.toList());
    }

    // find using derived query
    @Override
    public List<BookResponse> findByAuthorDerived(String author) {
        log.debug("finding books by author: {}", author);
        return bookRepository.findByAuthor(author)
                .stream()
                .filter(book -> book.getAuthor().equals(author))
                .map(book -> bookMapper.toDto(book))
                .collect(Collectors.toList());
    }

    // find using JPQL
    @Override
    public List<BookResponse> findByAuthorQuery(String author) {
        log.debug("finding books by author: {}", author);

        return bookRepository.findByAuthorQuery(author)
                .stream()
                .map(book -> bookMapper.toDto(book))
                .collect(Collectors.toList());
    }

}
