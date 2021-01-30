package com.sda.spring.boot.rest.service;


import com.sda.spring.boot.rest.dto.BookRequest;
import com.sda.spring.boot.rest.dto.BookResponse;

import java.util.List;

public interface BookService {

    List<BookResponse> findAll();

    BookResponse findById(Long id);

    BookResponse save(BookRequest createBookRequest);

    BookResponse update(Long id, BookRequest updateRequest);

    void delete(Long id);

    // find using filter
    List<BookResponse> findByAuthor(String author);

    // find using derived query
    List<BookResponse> findByAuthorDerived(String author);

    // find using JPQL
    List<BookResponse> findByAuthorQuery(String author);
}
