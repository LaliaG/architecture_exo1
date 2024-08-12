package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class BookService {

    public void createBook(String title, String author) {
        System.out.println("Creating a book: " + title + " by " + author);
    }

    public void deleteBook(Long bookId) {
        System.out.println("Deleting book with ID: " + bookId);
    }

    public String getBook(Long bookId) {
        return "Fetching book with ID: " + bookId;
    }
}
