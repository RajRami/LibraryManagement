package com.example.librarymanagement;

import java.time.LocalDate;

/**
 * This class represent the information about books
 */
public class Book {
    private String bookName;
    private String authorName;
    private LocalDate publishedDate;
    private String issuerName;
    private String issuerContact;
    private LocalDate issuedDate;

    /**
     * This is a constructor to add a new book
     *
     * @param bookName   as the first argument
     * @param authorName as the second argument
     */
    public Book(String bookName, String authorName, LocalDate publishedDate) {
        setBookName(bookName);
        setAuthorName(authorName);
        setPublishedDate(publishedDate);
    }

    /**
     * This is a constructor to issue a book
     *
     * @param bookName      as the first argument
     * @param authorName    as the second argument
     * @param issuerName    as the third argument to get issuer's name
     * @param issuerContact as the fourth argument to get issuer's name
     * @param issuedDate    as the fifth argument to get current date
     */
    public Book(String bookName, String authorName, LocalDate publishedDate, String issuerName, String issuerContact, LocalDate issuedDate) {
        setBookName(bookName);
        setAuthorName(authorName);
        setPublishedDate(publishedDate);
        setIssuerName(issuerName);
        setIssuerContact(issuerContact);
        setIssuedDate(issuedDate);
    }

    /**
     * Getter method for bookName
     *
     * @return bookName
     */
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setIssuerContact(String issuerContact) {
        if (issuerContact.matches("\\(?[2-9]\\d{2}\\)?[-\\s]?[2-9]\\d{2}[-\\s]?\\d{4}")) {
            this.issuerContact = issuerContact;
        } else {
            throw new IllegalArgumentException("Please enter valid 10 digit number");
        }
    }

    public String getIssuerName() {
        return issuerName;
    }

    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }

    public String getIssuerContact() {
        return issuerContact;
    }

    public LocalDate getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(LocalDate issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        if (publishedDate.isBefore(LocalDate.now())) {
            this.publishedDate = publishedDate;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
