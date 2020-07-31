package ru.waytosky.entity;

public class Book {

    private int isbn;

    public Book(int isbn) {
        this.isbn = isbn;
    }

    public String isbn() {
        return String.valueOf(isbn);
    }
}
