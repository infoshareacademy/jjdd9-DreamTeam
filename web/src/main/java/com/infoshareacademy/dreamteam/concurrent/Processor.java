package com.infoshareacademy.dreamteam.concurrent;

import com.infoshareacademy.dreamteam.domain.api.BookDetailsPlain;
import com.infoshareacademy.dreamteam.domain.api.BookPlain;
import com.infoshareacademy.dreamteam.domain.entity.Book;
import com.infoshareacademy.dreamteam.mapper.BookMapper;
import com.infoshareacademy.dreamteam.service.BookService;
import org.apache.http.client.HttpResponseException;

import java.util.List;

public class Processor implements Runnable {

    private List<BookPlain> bookPlains;
    private BookMapper bookMapper;
    private BookService bookService;

    public Processor(List<BookPlain> booksPlain, BookMapper bookMapper, BookService bookService) {
        this.bookPlains = booksPlain;
        this.bookMapper = bookMapper;
        this.bookService = bookService;
    }

    public void run() {
        for (BookPlain bookPlain : bookPlains) {
            BookDetailsPlain bookDetailsPlain;
            try {
                bookDetailsPlain = bookService.parseBookDetailsFromApi(bookPlain.getHref());
            } catch (HttpResponseException e) {
                continue;
            }
            Book book = bookMapper.mapPlainToEntity(bookDetailsPlain);
            book = bookMapper.mapPlainAudioToEntity(bookPlain, book);
            bookService.save(book);
        }
    }
}
