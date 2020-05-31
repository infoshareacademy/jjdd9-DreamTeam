package com.infoshareacademy.dreamteam.service;

import com.infoshareacademy.dreamteam.domain.entity.Author;
import com.infoshareacademy.dreamteam.repository.AuthorRepository;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class AuthorService {
    @EJB
    private AuthorRepository authorRepository;

    public void save(Author author) {
        authorRepository.save(author);
    }
    public Author update(Author author) {
        return authorRepository.update(author);
    }
}